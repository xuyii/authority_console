package com.cdel.plat.grant.facade;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.TreeNode;
import org.springframework.stereotype.Service;

import com.cdel.plat.grant.domain.Document;
import com.cdel.plat.grant.domain.Privilege;
import com.cdel.util.BaseFacadeImpl;
import com.cdel.util.Contants;
import com.cdel.util.ExceptionUtil;

/**
 * Class description goes here.
 *
 * @version 1.0 2008-1-17
 * @author LiXuFang - j2eeli@chinaacc.com
 */
@Service
@SuppressWarnings("serial")
public class PrivilegeFacade extends BaseFacadeImpl<Privilege, Integer>
		implements Serializable {

	/**
	 * 按角色ID和用户ID查找权限并初始化权限Map
	 *
	 * @param roleIDList
	 */
	@SuppressWarnings(value = { "unchecked", "rawtypes" })
	public List<String> loadAUTH(List<Integer> roleIDList) {
		List<String> result = null;
		Map pri = new HashMap();
		pri.put("roleIDList", roleIDList);
		try {
			List<String> priList = baseDao.findList(pri,
					"getAllPrivilegeRoleAndUser");
			result = new ArrayList<String>();
			if (priList != null && priList.size() > 0) {
				for (int i = 0; i < priList.size(); i++) {
					result.add(priList.get(i).toLowerCase());
				}
			}
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("map=" + pri.toString());
			logger.error(ExceptionUtil.getEMsg(e));
			return null;
		}
	}

	/**
	 * 查找用户有权限的节点,用于左侧树显示
	 *
	 * @param con
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List<Privilege> getPriByUser(Map con) {
		return baseDao.findList(con, "getPriByUser");
	}

	/**
	 * 获取用户所属角色
	 *
	 * @param userID
	 * @return
	 */
	public List<Integer> getUserRole(Integer userID) {
		return this.baseDao.findSomeList(userID, "getUserRole");
	}

	/**
	 * 删除用户所属角色
	 *
	 * @param userID
	 */
	public void removeUserRoleCon(Integer userID) {
		this.baseDao.delete(userID, "removeUserRoleCon");
	}

	/**
	 * 批量关联角色
	 *
	 * @param adminID
	 * @param roleIDs
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void conUserAndRole(Integer adminID, Integer[] roleIDs) {
		if (roleIDs != null && roleIDs.length > 0) {
			Map con = new HashMap();
			con.put("adminID", adminID);
			con.put("roleArray", roleIDs);
			this.baseDao.insertSome(con, "conUserAndRole");
		}
	}

	/**
	 * 创建TreeNode
	 *
	 * @return
	 */
	public TreeNode createDocuments(Short status) {
		TreeNode root = new DefaultTreeNode(new Document("", "设置权限", 1, -3, -2,
				-2, false), null);
		Privilege p = null;
		if (status != null) {
			p = new Privilege();
			p.setStatus(status);
		}
		Map<Integer, List<Document>> docList = getDocList(this.findList(p));

		// 开始递归
		Set<Entry<Integer, String>> set = Contants.systemTypes.entrySet();
		Iterator<Entry<Integer, String>> it = set.iterator();
		while (it.hasNext()) {
			Entry<Integer, String> entry = it.next();
			Integer systemType = entry.getKey();
			TreeNode documents = new DefaultTreeNode(new Document("sys"
					+ systemType, entry.getValue(), 1, -2, -1, -1, false), root);
			createDocuments(docList.get(systemType), -1, documents);
		}
		return root;
	}

	public void createDocuments(List<Document> docList, Integer pID,
			TreeNode documents) {
		if (docList != null) {
			for (Document pdoc : docList) {
				Integer parentID = pdoc.getParentID();
				Integer treeType = pdoc.getTreeType();
				boolean t = true;
				if (parentID.intValue() == pID) {
					if (treeType == 1) {
						t = false;
					}
					TreeNode inner = new DefaultTreeNode(new Document(
							pdoc.getAction(), pdoc.getPrivilegeName(),
							treeType, pID, pdoc.getPrivilegeID1(),
							pdoc.getPrivilegeID2(), t), documents);
					if (treeType == 1) {
						createDocuments(docList, pdoc.getPrivilegeID1(), inner);
					}
				}
			}
		}
	}

	/**
	 * 给生成好的TreeNode赋权限
	 *
	 * @return
	 */
	public void createDocuments(TreeNode root, List<Integer> priList,
			List<Integer> priUserList) {
		Iterator<TreeNode> childNode = root.getChildren().iterator();
		while (childNode.hasNext()) {
			TreeNode node = childNode.next();
			Document doc = (Document) node.getData();
			if (doc.getTreeType() == 1) {
				createDocuments(node, priList, priUserList);
			} else {
				// 默认所有checkbox都是可选的
				doc.setIsDisable1(false);
				doc.setIsDisable2(false);
				String[] priCheckbox = new String[2];
				if (priList.contains(doc.getPrivilegeID1())) {
					priCheckbox[0] = doc.getPrivilegeID1() + "_view";
					if (priUserList != null) {
						doc.setIsDisable1(true);
					}
				}
				if (priList.contains(doc.getPrivilegeID2())) {
					priCheckbox[1] = doc.getPrivilegeID2() + "_opt";
					if (priUserList != null) {
						doc.setIsDisable2(true);
					}
				}
				doc.setPriCheckbox(priCheckbox);
				if (priUserList != null) {
					// 通过用户管理进入权限管理
					if (priUserList.contains(doc.getPrivilegeID1())) {
						priCheckbox[0] = doc.getPrivilegeID1() + "_view";
					}
					if (priUserList.contains(doc.getPrivilegeID2())) {
						priCheckbox[1] = doc.getPrivilegeID2() + "_opt";
					}
				}
			}
		}
	}

	/**
	 * 获取叶子节点的priIDs
	 *
	 * @param root
	 * @return
	 */
	public void getAllPriID(TreeNode root, List<Integer> result) {
		Iterator<TreeNode> childNode = root.getChildren().iterator();
		while (childNode.hasNext()) {
			TreeNode node = childNode.next();
			Document doc = (Document) node.getData();
			if (doc.getTreeType() == 1) {
				getAllPriID(node, result);
			} else {
				String[] priCheckbox = doc.getPriCheckbox();
				if (priCheckbox != null) {
					Integer p1 = null;
					Integer p2 = null;
					for (String s : priCheckbox) {
						if (StringUtils.isNotBlank(s)) {
							if (s.indexOf("_view") > -1) {
								p1 = doc.getPrivilegeID1();
							}
							if (s.indexOf("_opt") > -1) {
								p1 = doc.getPrivilegeID1();
								p2 = doc.getPrivilegeID2();
							}
						}
					}
					if (p1 != null && !result.contains(p1)
							&& !doc.getIsDisable1()) {
						result.add(p1);
					}
					if (p2 != null && !result.contains(p2)
							&& !doc.getIsDisable2()) {
						result.add(p2);
					}
				}
			}
		}
	}

	/**
	 * 返回经过处理的docList，把相同的action合并
	 *
	 * @param p
	 * @return
	 */
	public Map<Integer, List<Document>> getDocList(List<Privilege> pl) {
		Map<Integer, List<Document>> result = null;
		if (pl != null && pl.size() > 0) {
			result = new HashMap<Integer, List<Document>>();
			for (int i = 0; i < pl.size(); i++) {
				Privilege p = pl.get(i);
				Integer systemType = p.getSystemType();
				String url = getAction(p.getActionURL());
				Document doc = new Document(url, p.getPrivilegeName(),
						p.getTreeType(), p.getParentID(), p.getPrivilegeID(),
						p.getNeighborID(), true);
				if (result.containsKey(systemType)) {
					result.get(systemType).add(doc);
				} else {
					List<Document> tmp = new ArrayList<Document>();
					tmp.add(doc);
					result.put(systemType, tmp);
				}
			}
		}
		return result;
	}

	/**
	 * 返回action
	 *
	 * @param actionUrl
	 * @return
	 */
	public String getAction(String actionUrl) {
		if (StringUtils.isNotBlank(actionUrl)) {
			String url = StringUtils.replace(actionUrl, "/view", "");
			url = StringUtils.replace(url, "/operate", "");
			url = StringUtils.replace(url, "/", "");
			return url;
		} else {
			return null;
		}
	}

	/**
	 * 返回角色权限
	 *
	 * @param roleID
	 * @return
	 */
	public List<Integer> getRolePri(Integer roleID) {
		return this.baseDao.findSomeList(roleID, "getRolePri");
	}

	/**
	 * 返回角色权限
	 *
	 * @param roleID
	 * @return
	 */
	public List<Integer> getRolePri(List<Integer> roleIDs) {
		return this.baseDao.findSomeList(roleIDs, "getRolePri2");
	}

	/**
	 * 返回用户权限
	 *
	 * @param roleID
	 * @return
	 */
	public List<Integer> getUserPri(Integer userID) {
		return this.baseDao.findSomeList(userID, "getUserPri");
	}

	/**
	 * 插入角色权限
	 *
	 * @param roleID
	 * @param plist
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void updatePriByRoleID(Integer roleID, List<Integer> plist)
			throws Exception {
		// 删除现有关系
		this.baseDao.delete(roleID, "delPriByRoleID");
		if (plist != null) {
			// 插入新关系
			Map map = new HashMap();
			map.put("roleID", roleID);
			map.put("plist", plist);
			this.baseDao.insertSome(map, "insertPriByRoleID");
		}
	}

	/**
	 * 插入角色权限
	 *
	 * @param roleID
	 * @param plist
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void updateRoleByPriID(Integer pID, Integer[] roleIDs)
			throws Exception {
		// 删除现有关系
		this.baseDao.delete(pID, "delPriByRoleID2");
		if (roleIDs != null && roleIDs.length > 0) {
			// 插入新关系
			Map map = new HashMap();
			map.put("roleList", roleIDs);
			map.put("pID", pID);
			this.baseDao.insertSome(map, "insertPriByRoleID2");
		}
	}

	/**
	 * 插入用户权限
	 *
	 * @param roleID
	 * @param plist
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void updatePriByUserID(Integer userID, List<Integer> plist)
			throws Exception {
		// 删除现有关系
		this.baseDao.delete(userID, "delPriByUserID");
		if (plist != null) {
			// 插入新关系
			Map map = new HashMap();
			map.put("userID", userID);
			map.put("plist", plist);
			this.baseDao.insertSome(map, "insertPriByUserID");
		}
	}

	/**
	 * 插入用户权限
	 *
	 * @param roleID
	 * @param plist
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void updateUserByPriID(Integer pID, Integer[] userlist)
			throws Exception {
		// 删除现有关系
		this.baseDao.delete(pID, "delPriByUserID2");
		if (userlist != null && userlist.length > 0) {
			// 插入新关系
			Map map = new HashMap();
			map.put("pID", pID);
			map.put("userList", userlist);
			this.baseDao.insertSome(map, "insertPriByUserID2");
		}
	}

	/**
	 * 判断该节点下是否有子节点
	 *
	 * @param parentID
	 * @return
	 */
	public Integer getPriListByParentID(Integer parentID) {
		return this.baseDao.get(parentID, "getPriListByParentID");
	}

	/**
	 * 删除叶子节点
	 *
	 * @param privilegeID
	 * @param neighborID
	 */
	public void deleteNodes(Integer privilegeID, Integer neighborID) {
		Integer[] pIds = new Integer[2];
		pIds[0] = privilegeID;
		pIds[1] = neighborID;
		this.baseDao.delete(pIds, "removePriAdminUserRelation");
		this.baseDao.delete(pIds, "removePriRoleRelation");
		this.baseDao.delete(pIds, "deleteNodes");
	}

	public boolean checkPrivilege(Privilege updateObj, byte flag) {
		if (flag == 0) {
			Integer parentID = updateObj.getParentID();
			if (parentID == null) {
				return addWarnMessage("parentID不能为空！");
			}
			Integer treeLevel = updateObj.getTreeLevel();
			if (treeLevel == null) {
				return addWarnMessage("treeLevel不能为空！");
			}
		} else {
			Integer privilegeID = updateObj.getPrivilegeID();
			if (privilegeID == null) {
				return addWarnMessage("privilegeID不能为空！");
			}
		}
		Integer systemType = updateObj.getSystemType();
		if (systemType == null) {
			return addWarnMessage("systemType不能为空！");
		}
		String privilegeName = updateObj.getPrivilegeName();
		if (StringUtils.isBlank(privilegeName)) {
			return addWarnMessage("节点名不能为空！");
		}
		Integer sequence = updateObj.getSequence();
		if (sequence == null ) {
			return addWarnMessage("顺序号不能为空！");
		}
		if (judgePrivilegeNameHasExist(updateObj) > 0) {
			return addWarnMessage("节点名称已经存在！");
		}
		return true;
	}

	public boolean checkPrivilegeYezi(Privilege updateObj, byte flag) {
		if (!checkPrivilege(updateObj, flag)) {
			return false;
		}
		String actionURLStr = updateObj.getActionURLStr2();
		if (StringUtils.isBlank(actionURLStr)) {
			return addWarnMessage("actionURL不能为空！");
		}
		updateObj.setActionURL("/" + updateObj.getActionURLStr2()
				+ Contants.VIEW);
		if (judgePrivilegeActionHasExist(updateObj) > 0) {
			return addWarnMessage("actionURL已经存在！");
		}
		return true;
	}

	/**
	 * 判断是否有重名的记录
	 *
	 * @param major
	 * @return
	 */
	public Integer judgePrivilegeNameHasExist(Privilege p) {
		return this.baseDao.get(p, "judgePrivilegeNameHasExist");
	}

	/**
	 * 判断是否有重actionURL的记录
	 *
	 * @param major
	 * @return
	 */
	public Integer judgePrivilegeActionHasExist(Privilege p) {
		return this.baseDao.get(p, "judgePrivilegeActionHasExist");
	}

	/**
	 * 获取节点关联的角色ID
	 *
	 * @param privilegeID
	 * @return
	 */
	public List<Integer> getPriRole(Integer privilegeID) {
		return this.baseDao.findSomeList(privilegeID, "getPriRole");
	}

	/**
	 * 获取节点关联的用户ID
	 *
	 * @param privilegeID
	 * @return
	 */
	public List<Integer> getPriUser(Integer privilegeID) {
		return this.baseDao.findSomeList(privilegeID, "getPriUser");
	}

	/**
	 * 更新状态
	 *
	 * @param privilegeID
	 * @return
	 */
	public void updatePriTreeNode(Privilege p) {
		this.baseDao.update(p, "updatePriTreeNode");
	}

}
