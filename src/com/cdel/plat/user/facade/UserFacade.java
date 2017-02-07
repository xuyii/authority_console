package com.cdel.plat.user.facade;

import java.io.Serializable;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cdel.plat.grant.facade.PrivilegeFacade;
import com.cdel.plat.user.domain.User;
import com.cdel.util.BaseFacadeImpl;

@SuppressWarnings("serial")
@Service
public class UserFacade extends BaseFacadeImpl<User, Integer> implements
		Serializable {

	@Autowired
	private PrivilegeFacade rivilegeFacade;

	/**
	 * 验证数据
	 *
	 * @param updateObj
	 */
	public boolean checkUser(User updateObj, byte flag, Integer[] roleIDs) {
		if (flag == 10) {
			String newPassWd = updateObj.getNewPassWd();
			if (StringUtils.isBlank(newPassWd)) {
				return addWarnMessage("密码不能为空！");
			}
			String conNewPassWd = updateObj.getConNewPassWd();
			if (StringUtils.isBlank(conNewPassWd)) {
				return addWarnMessage("确认密码不能为空！");
			}
			if (!newPassWd.equals(conNewPassWd)) {
				return addWarnMessage("两次密码输入不一致！");
			}
		} else {
			String realName = updateObj.getRealName();
			if (StringUtils.isBlank(realName)) {
				return addWarnMessage("真实姓名不能为空！");
			}
			String cellPhone = updateObj.getCellPhone();
			if (StringUtils.isBlank(cellPhone)) {
				return addWarnMessage("电话不能为空！");
			}
			if (roleIDs == null || roleIDs.length == 0) {
				return addWarnMessage("请至少选择一个角色！");
			}
			if (flag == 0) {
				if (hasSameUserName(updateObj) > 0) {
					return addWarnMessage("用户名已经存在！");
				}
				String newPassWd = updateObj.getNewPassWd();
				if (StringUtils.isBlank(newPassWd)) {
					return addWarnMessage("密码不能为空！");
				}
				String conNewPassWd = updateObj.getConNewPassWd();
				if (StringUtils.isBlank(conNewPassWd)) {
					return addWarnMessage("确认密码不能为空！");
				}
				if (!newPassWd.equals(conNewPassWd)) {
					return addWarnMessage("两次密码输入不一致！");
				}
			}
		}
		return true;
	}

	/**
	 * 更新用户
	 *
	 * @param updateObj
	 */
	public void updateUser(User updateObj, Integer[] roleIDs) {
		rivilegeFacade.removeUserRoleCon(updateObj.getAdminID());
		this.update(updateObj);
		rivilegeFacade.conUserAndRole(updateObj.getAdminID(), roleIDs);
	}

	/**
	 * 添加用户
	 *
	 * @param updateObj
	 */
	public void add(User updateObj, Integer[] roleIDs) {
		this.add(updateObj);
		rivilegeFacade.conUserAndRole(updateObj.getAdminID(), roleIDs);
	}

	/**
	 * 是否有重名用户
	 *
	 * @param user
	 * @return
	 */
	public Integer hasSameUserName(User user) {
		return this.baseDao.get(user, "hasSameUserName");
	}

}
