package com.cdel.plat.role.action;

import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import org.primefaces.model.LazyDataModel;
import com.cdel.plat.role.domain.Role;
import com.cdel.plat.role.facade.RoleFacade;
import com.cdel.util.BaseAction;

/**
 * 角色管理
 * 
 * @author 徐意
 * 
 */
@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class RoleAction extends BaseAction<Role> implements Serializable {

	@ManagedProperty(value = "#{roleFacade}")
	private RoleFacade roleFacade;

	private LazyDataModel<Role> page;
	private Role search = new Role();

	@PostConstruct
	public void initAction() {
		this.page = this.roleFacade.findPage(search);
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
		this.page = this.roleFacade.findPage(search);
		this.updateComponent("searchForm:list");
	}

	public LazyDataModel<Role> getPage() {
		return page;
	}

	public void setPage(LazyDataModel<Role> page) {
		this.page = page;
	}

	public Role getSearch() {
		return search;
	}

	public void setSearch(Role search) {
		this.search = search;
	}

	public void setRoleFacade(RoleFacade roleFacade) {
		this.roleFacade = roleFacade;
	}

}