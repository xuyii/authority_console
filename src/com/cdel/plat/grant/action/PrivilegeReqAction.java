package com.cdel.plat.grant.action;

import java.io.Serializable;
import java.util.Date;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import com.cdel.plat.grant.domain.Privilege;
import com.cdel.plat.grant.facade.PrivilegeFacade;
import com.cdel.plat.role.action.RoleOther;
import com.cdel.util.BaseAction;
import com.cdel.util.Contants;
import com.cdel.util.ExceptionUtil;

/**
 * 
 * 权限树管理
 * 
 * @author 徐意
 * 
 */
@SuppressWarnings("serial")
@ManagedBean
public class PrivilegeReqAction extends BaseAction<Privilege> implements
		Serializable {

	@ManagedProperty(value = "#{privilegeFacade}")
	private PrivilegeFacade privilegeFacade;
	@ManagedProperty(value = "#{roleOther}")
	private RoleOther roleOther;

	private byte submitSuccess = 0;// 添加是否成功
	private byte flag = -1;
	private Privilege updateObj = new Privilege();

	/**
	 * 删除节点
	 */
	public void deleteSubmit() {
		PrivilegeAction pa = (PrivilegeAction) this
				.getViewAction("privilegeAction");
		try {
			Integer privilegeID = pa.getShowObj().getPrivilegeID();
			Integer treeType = pa.getShowObj().getTreeType();
			if (privilegeID == null || privilegeID == 0) {
				addWarnMessage("msg", "privilegeID不能为空！");
				return;
			}
			if (treeType == null || treeType == 0) {
				addWarnMessage("msg", "treeType不能为空！");
				return;
			}
			if (treeType == 1) {
				// 分支
				int count = privilegeFacade.getPriListByParentID(privilegeID);
				if (count > 0) {
					addWarnMessage("msg", "不能删除该分支，请先删除下面的节点！");
					return;
				}
				privilegeFacade.delete(privilegeID);
			} else {
				Integer neighborID = pa.getShowObj().getNeighborID();
				if (neighborID == null || neighborID == 0) {
					addWarnMessage("msg", "neighborID不能为空！");
					return;
				}
				// 节点
				privilegeFacade.deleteNodes(privilegeID, neighborID);
			}
			pa.search();
			this.updateComponent("searchForm:tree");
			submitSuccess = 1;
		} catch (Exception e) {
			e.printStackTrace();
			submitSuccess = -1;
		}
		this.addCallbackParam("result", submitSuccess);
	}

	/**
	 * 添加分支
	 */
	public void add() {
		flag = 0;
		PrivilegeAction pa = (PrivilegeAction) this
				.getViewAction("privilegeAction");
		updateObj.setParentID(pa.getShowObj().getPrivilegeID());
		updateObj.setTreeLevel(pa.getShowObj().getTreeLevel() + 1);
		updateObj.setSystemType(pa.getShowObj().getSystemType());
	}

	/**
	 * 添加叶子
	 */
	public void addYezi() {
		add();
		roleOther.setRoleList();
	}

	/**
	 * 修改分支
	 */
	public void update() {
		flag = 1;
		PrivilegeAction pa = (PrivilegeAction) this
				.getViewAction("privilegeAction");
		updateObj.setPrivilegeID(pa.getShowObj().getPrivilegeID());
		updateObj.setPrivilegeName(pa.getShowObj().getPrivilegeName());
		updateObj.setDescription(pa.getShowObj().getDescription());
		updateObj.setSequence(pa.getShowObj().getSequence());
		updateObj.setSystemType(pa.getShowObj().getSystemType());
	}

	/**
	 * 修改叶子
	 */
	public void updateYezi() {
		update();
		PrivilegeAction pa = (PrivilegeAction) this
				.getViewAction("privilegeAction");
		updateObj.setActionURL(pa.getShowObj().getActionURL());
		Integer neighborID = pa.getShowObj().getNeighborID();
		updateObj.setNeighborID(neighborID);
	}

	/**
	 * 提交分支
	 */
	public void updateSubmit() {
		try {
			if (privilegeFacade.checkPrivilege(updateObj, flag)) {
				PrivilegeAction pa = (PrivilegeAction) this
						.getViewAction("privilegeAction");
				if (flag == 0) {
					updateObj.setCreator(this.getCurrentUserID());
					updateObj.setCreateTime(new Date());
					updateObj.setTreeType(1);
					privilegeFacade.add(updateObj);
				} else {
					privilegeFacade.update(updateObj);
				}
				pa.search();
				this.updateComponent("searchForm:tree");
				submitSuccess = 1;
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("updateObj(privilege)=" + updateObj);
			logger.error(ExceptionUtil.getEMsg(e));
			submitSuccess = -1;
		}
		this.addCallbackParam("result", submitSuccess);
	}

	/**
	 * 提交叶子
	 */
	public void updateSubmitYezi() {
		try {
			if (privilegeFacade.checkPrivilegeYezi(updateObj, flag)) {
				PrivilegeAction pa = (PrivilegeAction) this
						.getViewAction("privilegeAction");
				Integer newPID = null;
				Integer newNID = null;
				if (flag == 0) {
					updateObj.setCreator(this.getCurrentUserID());
					updateObj.setCreateTime(new Date());
					updateObj.setTreeType(2);
					updateObj.setActionURL("/" + updateObj.getActionURLStr2()
							+ Contants.VIEW);
					privilegeFacade.add(updateObj);
					newPID = updateObj.getPrivilegeID();
					updateObj.setTreeType(3);
					updateObj.setActionURL("/" + updateObj.getActionURLStr2()
							+ Contants.OPT);
					updateObj.setNeighborID(newPID);
					privilegeFacade.add(updateObj);
					newNID = updateObj.getPrivilegeID();
					updateObj.setNeighborID(newNID);
					updateObj.setPrivilegeID(newPID);
					privilegeFacade.update(updateObj);
				} else {
					newPID = updateObj.getPrivilegeID();
					newNID = updateObj.getNeighborID();
					privilegeFacade.update(updateObj);
				}
				pa.search();
				this.updateComponent("searchForm:tree");
				submitSuccess = 1;
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("updateObj(privilege)=" + updateObj);
			logger.error(ExceptionUtil.getEMsg(e));
			submitSuccess = -1;
		}
		this.addCallbackParam("result", submitSuccess);
	}

	/**
	 * 修改状态
	 */
	public void changeStatus() {
		PrivilegeAction pa = (PrivilegeAction) this
				.getViewAction("privilegeAction");
		Privilege p = pa.getShowObj();
		int treeType = p.getTreeType();
		int newStatus;
		int optStatus = treeType + 1;
		int newOptStatus;
		if (treeType > 10) {
			newStatus = treeType - 10;
			newOptStatus = optStatus - 10;
		} else {
			newStatus = treeType + 10;
			newOptStatus = optStatus + 10;
		}
		try {
			p.setTreeType(newStatus);
			Privilege newp = new Privilege();
			newp.setPrivilegeID(p.getPrivilegeID());
			newp.setTreeType(newStatus);
			privilegeFacade.updatePriTreeNode(newp);
			newp.setPrivilegeID(p.getNeighborID());
			newp.setTreeType(newOptStatus);
			privilegeFacade.updatePriTreeNode(newp);
		} catch (Exception e) {
			e.printStackTrace();
			this.addErrorMessage("info", FAILINFO);
		}
		this.addMessage("info", SUCESSINFO);
	}

	public Privilege getUpdateObj() {
		return updateObj;
	}

	public void setUpdateObj(Privilege updateObj) {
		this.updateObj = updateObj;
	}

	public void setPrivilegeFacade(PrivilegeFacade privilegeFacade) {
		this.privilegeFacade = privilegeFacade;
	}

	public byte getFlag() {
		return flag;
	}

	public void setFlag(byte flag) {
		this.flag = flag;
	}

	public void setRoleOther(RoleOther roleOther) {
		this.roleOther = roleOther;
	}

}
