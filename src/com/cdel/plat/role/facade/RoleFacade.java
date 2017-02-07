package com.cdel.plat.role.facade;

import java.io.Serializable;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import com.cdel.plat.role.domain.Role;
import com.cdel.util.BaseFacadeImpl;

@SuppressWarnings("serial")
@Service
public class RoleFacade extends BaseFacadeImpl<Role, Integer> implements
		Serializable {

	/**
	 * 验证数据
	 * 
	 * @param updateObj
	 */
	public boolean checkRole(Role updateObj) {
		String roleName = updateObj.getRoleName();
		if (StringUtils.isBlank(roleName)) {
			return addWarnMessage("角色名不能为空！");
		}
		Integer roleCategory = updateObj.getRoleCategory();
		if (roleCategory == null) {
			return addWarnMessage("角色部门不能为空！");
		}
		return true;
	}

}
