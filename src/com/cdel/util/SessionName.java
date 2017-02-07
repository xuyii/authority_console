package com.cdel.util;

/**
 * 类/接口描述: session的属性名
 *
 * @author 张苏磊
 * @version 1.0
 * 创建时间： 2015年11月5日 下午4:53:41
 * JDK版本：sun jdk 1.7
 *********************************更新记录******************************
 * 版本：  <版本号>        修改日期：  <日期>        修改人： <修改人姓名>
 * 修改内容：  <修改内容描述>
 **********************************************************************
 */
public enum SessionName {

	adminID("com_chnedu_plat_grant_adminID"), adminName("com_chnedu_plat_grant_adminUser_name"),
	adminRealName("com_chnedu_plat_grant_admin_real_name"), teacher("class_teacher"),
	image("loginValidate_save_number"), adminRoles("com_chnedu_plat_grant_role"),
	adminAuth("com_chnedu_plat_grant_auth"), currentSiteID("acc_console_siteid"), currentBusinessID("acc_console_businessid"),
	currentMajorID("acc_console_majorid"), currentCourseID("acc_console_courseid"),
	adminPass("com_chnedu_plat_grant_admin_password"),height("com_chnedu_height");

	SessionName(String sessionName) {
		this.sessionName = sessionName;
	}

	private final String sessionName;

	public String getSessionName() {
		return sessionName;
	}

}
