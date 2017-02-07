package com.cdel.plat.user.domain;

import java.io.Serializable;

import com.cdel.plat.grant.domain.AdminUser;
import com.cdel.util.Contants;
import com.cdel.util.DateUtil;

/**
 * 用户管理
 * 
 * @version 1.0 2008-1-17
 * @author LiXuFang - j2eeli@chinaacc.com
 */
@SuppressWarnings("serial")
public class User extends AdminUser implements Serializable {

	public String getStatusStr() {
		if (getStatus() == null) {
			return "";
		}
		return Contants.status.get(getStatus());
	}

	public String getCreateTimeStr() {
		return DateUtil
				.getNowDateString(getCreateTime(), "yyyy-MM-dd HH:mm:ss");
	}

	public String getLastLoginStr() {
		return DateUtil.getNowDateString(getLastLogin(), "yyyy-MM-dd HH:mm:ss");
	}

}
