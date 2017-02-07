package com.cdel.plat.user.action;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.primefaces.model.LazyDataModel;
import com.cdel.plat.role.action.RoleOther;
import com.cdel.plat.user.domain.User;
import com.cdel.plat.user.facade.UserFacade;
import com.cdel.util.BaseAction;

/**
 * 用户管理
 *
 * @author 徐意
 *
 */
@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class UserAction extends BaseAction<User> implements Serializable {

	@ManagedProperty(value = "#{userFacade}")
	private UserFacade userFacade;
	@ManagedProperty(value = "#{roleOther}")
	private RoleOther roleOther;

	private LazyDataModel<User> page;
	private User search = new User();

	@PostConstruct
	public void initAction() {
		roleOther.setRoleList();
		this.page = this.userFacade.findPage(search);
		super.heighti2 = super.calHeight(11.5f / 20);
	}

	/**
	 * 查询
	 */
	public void search() {
		pageTable.setFirst(0);
		search4U();
	}

	/**
	 * 查询
	 */
	public void search4U() {
		this.page = this.userFacade.findPage(search);
		this.updateComponent("searchForm:list");
	}

	public LazyDataModel<User> getPage() {
		return page;
	}

	public void setPage(LazyDataModel<User> page) {
		this.page = page;
	}

	public User getSearch() {
		return search;
	}

	public void setSearch(User search) {
		this.search = search;
	}

	public void setUserFacade(UserFacade userFacade) {
		this.userFacade = userFacade;
	}

	public void setRoleOther(RoleOther roleOther) {
		this.roleOther = roleOther;
	}

}