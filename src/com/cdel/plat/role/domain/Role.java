package com.cdel.plat.role.domain;

import java.io.Serializable;
import java.util.Date;
import com.cdel.util.DateUtil;
import com.chnedu.plat.rad.domain.BaseDomain;

/**
 * 角色管理
 *
 * @version 1.0 2008-1-18
 * @author LiXuFang - j2eeli@chinaacc.com
 */
public class Role extends BaseDomain implements Serializable {

	private static final long serialVersionUID = 4643867997230319575L;
	private Integer roleID;
	private String roleName;
	private Integer roleCategory;
	private Integer creator;
	private Date createTime;

	public Integer getRoleID() {
		return roleID;
	}

	public void setRoleID(Integer roleID) {
		this.roleID = roleID;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public Integer getRoleCategory() {
		return roleCategory;
	}

	public void setRoleCategory(Integer roleCategory) {
		this.roleCategory = roleCategory;
	}

	public Integer getCreator() {
		return creator;
	}

	public void setCreator(Integer creator) {
		this.creator = creator;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getCreateTimeStr() {
		return DateUtil
				.getNowDateString(getCreateTime(), "yyyy-MM-dd HH:mm:ss");
	}
	public String getRoleCategoryAndRoleName(){
		return "("+this.roleCategory+")"+this.roleName;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((roleCategory == null) ? 0 : roleCategory.hashCode());
		result = prime * result + ((creator == null) ? 0 : creator.hashCode());
		result = prime * result + ((roleID == null) ? 0 : roleID.hashCode());
		result = prime * result
				+ ((roleName == null) ? 0 : roleName.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		final Role other = (Role) obj;
		if (roleCategory == null) {
			if (other.roleCategory != null)
				return false;
		} else if (!roleCategory.equals(other.roleCategory))
			return false;
		if (creator == null) {
			if (other.creator != null)
				return false;
		} else if (!creator.equals(other.creator))
			return false;
		if (roleID == null) {
			if (other.roleID != null)
				return false;
		} else if (!roleID.equals(other.roleID))
			return false;
		if (roleName == null) {
			if (other.roleName != null)
				return false;
		} else if (!roleName.equals(other.roleName))
			return false;
		return true;
	}

}
