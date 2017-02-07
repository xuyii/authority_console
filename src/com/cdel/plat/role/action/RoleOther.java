package com.cdel.plat.role.action;

import java.io.Serializable;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import com.cdel.plat.role.domain.Role;
import com.cdel.plat.role.facade.RoleFacade;
import com.cdel.util.BaseNoLoginAction;

/**
 * 角色管理
 * 
 * @author 徐意
 * 
 */
@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class RoleOther extends BaseNoLoginAction<Role> implements Serializable {

	@ManagedProperty(value = "#{roleFacade}")
	private RoleFacade roleFacade;

	private List<Role> roleList;

	public void setRoleList() {
		this.roleList = roleFacade.findList(0);
	}

	public List<Role> getRoleList() {
		return roleList;
	}

	public void setRoleFacade(RoleFacade roleFacade) {
		this.roleFacade = roleFacade;
	}

}