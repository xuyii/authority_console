package com.cdel.plat.grant.action;

import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import com.cdel.plat.grant.domain.AdminUser;
import com.cdel.plat.grant.facade.AdminUserFacade;
import com.cdel.util.BaseAction;

/**
 * 个人中心
 * 
 * @author 徐意
 * 
 */
@ManagedBean
@ViewScoped
@SuppressWarnings("serial")
public class AdminUserAction extends BaseAction<AdminUser> implements
		Serializable {

	@ManagedProperty(value = "#{adminUserFacade}")
	private AdminUserFacade adminUserFacade;

	private AdminUser adminUser;

	//构造方法执行之后紧跟着执行@PostConstruct
	@PostConstruct
	public void initUserCenterReqAction() {
		adminUser = adminUserFacade.findByID(this.getCurrentUserID());
	}

	public void setAdminUserFacade(AdminUserFacade adminUserFacade) {
		this.adminUserFacade = adminUserFacade;
	}

	public AdminUser getAdminUser() {
		return adminUser;
	}

	public void setAdminUser(AdminUser adminUser) {
		this.adminUser = adminUser;
	}

}
