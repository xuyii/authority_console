package com.cdel.plat.role.action;

import java.io.Serializable;
import java.util.Date;
import java.util.Map;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import com.cdel.plat.role.domain.Role;
import com.cdel.plat.role.facade.RoleFacade;
import com.cdel.util.BaseAction;
import com.cdel.util.Contants;
import com.cdel.util.ExceptionUtil;

/**
 * 角色管理
 * 
 * @author 徐意
 * 
 */
@SuppressWarnings("serial")
@ManagedBean
public class RoleReqAction extends BaseAction<Role> implements Serializable {

	@ManagedProperty(value = "#{roleFacade}")
	private RoleFacade roleFacade;

	private Role updateObj = new Role();
	private byte flag = -1;
	private byte submitSuccess = 0;// 添加是否成功

	/**
	 * 打开添加页面
	 */
	public void add() {
		flag = 0;
	}

	/**
	 * 打开编辑页面
	 */
	public void update(Integer roleID) {
		flag = 1;
		updateObj = roleFacade.findByID(roleID);
	}

	/**
	 * 提交
	 */
	public void updateSubmit() {
		try {
			if (roleFacade.checkRole(updateObj)) {
				RoleAction ra = (RoleAction) this.getViewAction("roleAction");
				if (flag == 0) {
					updateObj.setCreator(this.getCurrentUserID());
					updateObj.setCreateTime(new Date());
					roleFacade.add(updateObj);
					ra.search();
				} else {
					roleFacade.update(updateObj);
					ra.search4U();
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

	public Map<Integer, String> getCategorys() {
		return Contants.roleCategorys;
	}

	public byte getFlag() {
		return flag;
	}

	public void setFlag(byte flag) {
		this.flag = flag;
	}

	public Role getUpdateObj() {
		return updateObj;
	}

	public void setUpdateObj(Role updateObj) {
		this.updateObj = updateObj;
	}

	public void setRoleFacade(RoleFacade roleFacade) {
		this.roleFacade = roleFacade;
	}

}
