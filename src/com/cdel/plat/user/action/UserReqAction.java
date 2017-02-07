package com.cdel.plat.user.action;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import com.cdel.plat.grant.facade.PrivilegeFacade;
import com.cdel.plat.role.action.RoleOther;
import com.cdel.plat.user.domain.User;
import com.cdel.plat.user.facade.UserFacade;
import com.cdel.util.BaseAction;
import com.cdel.util.Contants;
import com.cdel.util.ExceptionUtil;

/**
 * 用户管理
 * 
 * @author 徐意
 * 
 */
@SuppressWarnings("serial")
@ManagedBean
public class UserReqAction extends BaseAction<User> implements Serializable {

	@ManagedProperty(value = "#{userFacade}")
	private UserFacade userFacade;
	@ManagedProperty(value = "#{roleOther}")
	private RoleOther roleOther;
	@ManagedProperty(value = "#{privilegeFacade}")
	private PrivilegeFacade privilegeFacade;

	private User updateObj = new User();
	private byte flag = -1;
	private byte submitSuccess = 0;// 添加是否成功
	private Integer[] roleIDs;// 所属角色

	/**
	 * 修改状态
	 */
	public void changeStatus(Integer adminID, Integer status) {
		UserAction ua = (UserAction) this.getViewAction("userAction");
		updateObj.setAdminID(adminID);
		Short newStatus;
		if (status == 1) {
			newStatus = 0;
		} else {
			newStatus = 1;
		}
		updateObj.setStatus(newStatus);
		try {
			userFacade.update(updateObj);
			ua.search4U();
		} catch (Exception e) {
			e.printStackTrace();
			this.addErrorMessage("info", FAILINFO);
		}
		this.addMessage("info", SUCESSINFO);
	}

	/**
	 * 打开修改密码页面
	 * 
	 * @param adminID
	 */
	public void changePw(Integer adminID) {
		flag = 10;
		updateObj = userFacade.findByID(adminID);
	}

	/**
	 * 打开添加页面
	 */
	public void add() {
		flag = 0;
		roleOther.setRoleList();
	}

	/**
	 * 打开编辑页面
	 */
	public void update(Integer adminID) {
		flag = 1;
		updateObj = userFacade.findByID(adminID);
		roleOther.setRoleList();
		List<Integer> roleIDList = privilegeFacade.getUserRole(adminID);
		if (roleIDList != null && roleIDList.size() > 0) {
			roleIDs = new Integer[roleIDList.size()];
			for (int i = 0; i < roleIDList.size(); i++) {
				roleIDs[i] = roleIDList.get(i);
			}
		}
	}

	/**
	 * 提交
	 */
	public void updateSubmit() {
		try {
			if (userFacade.checkUser(updateObj, flag, roleIDs)) {
				UserAction ua = (UserAction) this.getViewAction("userAction");
				if (flag == 0) {
					updateObj.setPassWd(updateObj.getNewPassWd());
					updateObj.setLastLogin(new Date());
					updateObj.setPwdModifyTime(new Date());
					updateObj.setLoginIP("127.0.0.1");
					updateObj.setStatus((short) 1);
					updateObj.setCreator(this.getCurrentUserID());
					updateObj.setCreateTime(new Date());
					userFacade.add(updateObj, roleIDs);
					ua.search();
				} else {
					if (flag == 10) {
						updateObj.setPassWd(updateObj.getNewPassWd());
						updateObj.setPwdModifyTime(new Date());
						userFacade.update(updateObj);
					} else {
						userFacade.updateUser(updateObj, roleIDs);
					}
					if (flag != 10) {
						ua.search4U();
					}
				}
				submitSuccess = 1;
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("updateObj(book)=" + updateObj);
			logger.error(ExceptionUtil.getEMsg(e));
			submitSuccess = -1;
		}
		this.addCallbackParam("result", submitSuccess);
	}

	public Map<Short, String> getStatus() {
		return Contants.status;
	}

	public byte getFlag() {
		return flag;
	}

	public void setFlag(byte flag) {
		this.flag = flag;
	}

	public User getUpdateObj() {
		return updateObj;
	}

	public void setUpdateObj(User updateObj) {
		this.updateObj = updateObj;
	}

	public void setUserFacade(UserFacade userFacade) {
		this.userFacade = userFacade;
	}

	public Integer[] getRoleIDs() {
		return roleIDs;
	}

	public void setRoleIDs(Integer[] roleIDs) {
		this.roleIDs = roleIDs;
	}

	public void setRoleOther(RoleOther roleOther) {
		this.roleOther = roleOther;
	}

	public void setPrivilegeFacade(PrivilegeFacade privilegeFacade) {
		this.privilegeFacade = privilegeFacade;
	}

}
