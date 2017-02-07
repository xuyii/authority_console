package com.cdel.plat.grant.action;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import com.cdel.plat.grant.domain.AdminUser;
import com.cdel.plat.grant.facade.AdminUserFacade;
import com.cdel.plat.grant.facade.PrivilegeFacade;
import com.cdel.util.BaseNoLoginAction;
import com.cdel.util.Contants;
import com.chnedu.plat.rad.util.net.ProxyUtil;

/**登录
 * @author 徐意
 *
 */
@ManagedBean
@SuppressWarnings("serial")
public class LoginAction extends BaseNoLoginAction<LoginAction> implements Serializable {
	
	private String userName;
	private String password;
	private String loginValidate;
	@ManagedProperty(value = "#{privilegeFacade}")
	private PrivilegeFacade privilegeFacade;
	@ManagedProperty(value = "#{adminUserFacade}")
	private AdminUserFacade adminUserFacade;

	/* 登陆 */
	public String login() throws Exception {
		HttpServletRequest request = this.getRequest();
		HttpSession session = request.getSession();
		
		AdminUser u = null;
		String validateNumber = (String) session.getAttribute(Contants.YZ);
		if (validateNumber == null || loginValidate == null
				|| !loginValidate.equals(validateNumber)) {
			request.setAttribute("msg", "验证码错误");
			return "login";
		}
		// 不删除验证码session，可保证刷新时不退出
		// session.removeAttribute(Contants.YZ);
		AdminUser user = new AdminUser();
		user.setAdminName(userName);
		user.setPassWd(password);

		u = adminUserFacade.getUserInfo(user);
		if (u == null) {
			request.setAttribute("msg", "您的用户名或密码错误");
			return "login";
		} else {
			AdminUser au = new AdminUser();
			au.setAdminID(u.getAdminID());
			au.setLastLogin(new Date());
			au.setLoginIP(ProxyUtil.getRemoteIp(request));
			adminUserFacade.updateUserLoginMsgByID(au);

			Integer adminID = u.getAdminID();
			List<Integer> sl = adminUserFacade.getUserRoleID(adminID);

			try {
				session.setAttribute(Contants.ADMIN_USER_ID, adminID);
				session.setAttribute(Contants.ADMIN_USER_ROLES, sl);
				session.setAttribute(Contants.ADMIN_REAL_NAME, u.getRealName());
				session.setAttribute(Contants.ADMIN_USER_NAME, u.getAdminName());
				session.setAttribute(Contants.ADMIN_USER_AUTH, privilegeFacade.loadAUTH(sl));// 查询权限
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			return "/page/grant/busMain?faces-redirect=true";
		}
	}

	/**
	 * 退出
	 *
	 * @return
	 */
	public String logout() {
		HttpSession session = this.getSession();
		session.invalidate();
		return "/login?faces-redirect=true";
	}

	/**
	 * 定时刷新
	 *
	 * @return
	 * @throws Exception
	 */
	public void keepSession() throws Exception {
		System.out.println("刷新后台!");
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getLoginValidate() {
		return loginValidate;
	}

	public void setLoginValidate(String loginValidate) {
		this.loginValidate = loginValidate;
	}

	public void setPrivilegeFacade(PrivilegeFacade privilegeFacade) {
		this.privilegeFacade = privilegeFacade;
	}

	public void setAdminUserFacade(AdminUserFacade adminUserFacade) {
		this.adminUserFacade = adminUserFacade;
	}

}