package com.cdel.plat.grant.action;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import org.primefaces.model.TreeNode;
import com.cdel.plat.grant.domain.Privilege;
import com.cdel.plat.grant.facade.PrivilegeFacade;
import com.cdel.plat.grant.facade.PrivilegeRelFacade;
import com.cdel.util.BaseAction;
import com.cdel.util.ExceptionUtil;

/**修改角色权限
 * @author 徐意
 *
 */
@ManagedBean
@ViewScoped
@SuppressWarnings("serial")
public class PrivilegeModify extends BaseAction<Privilege> implements
		Serializable {

	@ManagedProperty(value = "#{privilegeFacade}")
	private PrivilegeFacade privilegeFacade;
	@ManagedProperty(value = "#{privilegeRelFacade}")
	private PrivilegeRelFacade privilegeRelFacade;

	private TreeNode root;
	private TreeNode selectedNode;
	private Integer roleID;
	private Integer userID;
	private byte submitSuccess = 0;// 添加是否成功

	@PostConstruct
	public void init() {
		if (root == null) {
			root = privilegeFacade.createDocuments((short) 1);
		}
	}

	public void showPri(Integer roleID) {
		this.roleID = roleID;
		privilegeFacade.createDocuments(root, privilegeFacade.getRolePri(roleID), null);
	}

	public void showPriUser(Integer userID) {
		this.userID = userID;
		List<Integer> roleIDs = privilegeFacade.getUserRole(userID);
		privilegeFacade.createDocuments(root,
				privilegeFacade.getRolePri(roleIDs),
				privilegeFacade.getUserPri(userID));
	}

	/**
	 * 提交权限修改
	 */
	public void updateSubmit() {
		List<Integer> plist = new ArrayList<Integer>();
		try {
			privilegeFacade.getAllPriID(root, plist);
			if (roleID != null) {
				privilegeFacade.updatePriByRoleID(roleID, plist);
			}
			submitSuccess = 1;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("plist=" + plist.toString() + ",roleID=" + roleID);
			logger.error(ExceptionUtil.getEMsg(e));
			submitSuccess = -1;
		}
		this.addCallbackParam("result", submitSuccess);
	}

	public TreeNode getRoot() {
		return root;
	}

	public TreeNode getSelectedNode() {
		return selectedNode;
	}

	public void setSelectedNode(TreeNode selectedNode) {
		this.selectedNode = selectedNode;
	}

	public void setPrivilegeFacade(PrivilegeFacade privilegeFacade) {
		this.privilegeFacade = privilegeFacade;
	}

	public Integer getRoleID() {
		return roleID;
	}

	public void setRoleID(Integer roleID) {
		this.roleID = roleID;
	}

	public void setPrivilegeRelFacade(PrivilegeRelFacade privilegeRelFacade) {
		this.privilegeRelFacade = privilegeRelFacade;
	}

	public Integer getUserID() {
		return userID;
	}

	public void setUserID(Integer userID) {
		this.userID = userID;
	}

}
