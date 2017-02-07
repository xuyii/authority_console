package com.cdel.plat.grant.domain;

import java.io.Serializable;
import java.util.Date;

import org.apache.commons.lang.StringUtils;

import com.cdel.util.Contants;
import com.cdel.util.DateUtil;
import com.chnedu.plat.rad.domain.BaseDomain;

/**
 * Class description goes here.
 * 
 * @version 1.0 2008-1-17
 * @author LiXuFang - j2eeli@chinaacc.com
 */
public class Privilege extends BaseDomain implements Serializable,
		Comparable<Privilege> {
	private static final long serialVersionUID = -909287480225229712L;

	private Integer privilegeID;
	private String privilegeName;
	private String privilegeCategory;
	private int treeType;
	private Integer parentID;
	private String parentName;
	private int treeLevel;
	private String actionURL;
	private String actionURLStr;
	private Integer neighborID;
	private String description;
	private Integer creator;
	private String username;
	private Date createTime;
	private int sequence;
	private Integer systemType;
	private Integer priType;
	private Integer roleID;
	private Integer adminID;
	private Short status;

	public Integer getPrivilegeID() {
		return privilegeID;
	}

	public void setPrivilegeID(Integer privilegeID) {
		this.privilegeID = privilegeID;
	}

	public String getPrivilegeName() {
		return privilegeName;
	}

	public void setPrivilegeName(String privilegeName) {
		this.privilegeName = privilegeName;
	}

	public String getPrivilegeCategory() {
		return privilegeCategory;
	}

	public void setPrivilegeCategory(String privilegeCategory) {
		this.privilegeCategory = privilegeCategory;
	}

	public int getTreeType() {
		return treeType;
	}

	public void setTreeType(int treeType) {
		this.treeType = treeType;
	}

	public Integer getParentID() {
		return parentID;
	}

	public void setParentID(Integer parentID) {
		this.parentID = parentID;
	}

	public int getTreeLevel() {
		return treeLevel;
	}

	public void setTreeLevel(int treeLevel) {
		this.treeLevel = treeLevel;
	}

	public String getActionURL() {
		return actionURL;
	}

	public void setActionURLStr(String actionURLStr) {
		this.actionURLStr = actionURLStr;
	}

	public String getActionURLStr2() {
		return actionURLStr;
	}

	public String getActionURLStr() {
		if (StringUtils.isBlank(actionURL)) {
			return "";
		}
		actionURLStr = StringUtils.replace(actionURL, Contants.VIEW, "");
		actionURLStr = StringUtils.replace(actionURLStr, Contants.OPT, "");
		actionURLStr = StringUtils.replace(actionURLStr, "/", "");
		return actionURLStr;
	}

	public void setActionURL(String actionURL) {
		this.actionURL = actionURL;
	}

	public Integer getNeighborID() {
		return neighborID;
	}

	public void setNeighborID(Integer neighborID) {
		this.neighborID = neighborID;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
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

	public String getCreateTimeStr() {
		return DateUtil.getNowDateString(createTime, "yyyy-MM-dd");
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public int getSequence() {
		return sequence;
	}

	public void setSequence(int sequence) {
		this.sequence = sequence;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getParentName() {
		return parentName;
	}

	public void setParentName(String parentName) {
		this.parentName = parentName;
	}

	public Integer getSystemType() {
		return systemType;
	}

	public void setSystemType(Integer systemType) {
		this.systemType = systemType;
	}

	public Integer getPriType() {
		return priType;
	}

	public void setPriType(Integer priType) {
		this.priType = priType;
	}

	public Integer getRoleID() {
		return roleID;
	}

	public void setRoleID(Integer roleID) {
		this.roleID = roleID;
	}

	public Integer getAdminID() {
		return adminID;
	}

	public void setAdminID(Integer adminID) {
		this.adminID = adminID;
	}

	public String getShowStatusStr() {
		if (treeType > 10) {
			return "隐藏";
		} else {
			return "显示";
		}
	}

	public Short getStatus() {
		return status;
	}

	public void setStatus(Short status) {
		this.status = status;
	}

	@Override
	public int compareTo(Privilege p) {
		if (this.sequence > p.sequence) {
			return 1;
		} else if (this.sequence < p.sequence) {
			return -1;
		} else {
			return 0;
		}
	}

}