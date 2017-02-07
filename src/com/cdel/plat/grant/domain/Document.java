package com.cdel.plat.grant.domain;

import java.io.Serializable;

/**
 * 修改权限对象
 * 
 * @author dell
 * 
 */
@SuppressWarnings("serial")
public class Document implements Serializable {

	private String action;
	private String privilegeName;
	private String[] priCheckbox;// 每个节点的查看、操作权限
	private Boolean showCheckbox;// 是否要显示复选框
	private Integer treeType;// 节点类型
	private Integer parentID;// 父节点
	private Integer privilegeID1;// 查看ID
	private Integer privilegeID2;// 操作ID
	private Boolean isDisable1;// 查看是否可用
	private Boolean isDisable2;// 操作是否可用

	public Document(String action, String privilegeName, Integer treeType,
			Integer parentID, Integer privilegeID1, Integer privilegeID2,
			Boolean showCheckbox) {
		super();
		this.action = action;
		this.privilegeName = privilegeName;
		this.treeType = treeType;
		this.parentID = parentID;
		this.privilegeID1 = privilegeID1;
		this.privilegeID2 = privilegeID2;
		this.showCheckbox = showCheckbox;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public String getPrivilegeName() {
		return privilegeName;
	}

	public void setPrivilegeName(String privilegeName) {
		this.privilegeName = privilegeName;
	}

	public String[] getPriCheckbox() {
		return priCheckbox;
	}

	public void setPriCheckbox(String[] priCheckbox) {
		this.priCheckbox = priCheckbox;
	}

	public Boolean getShowCheckbox() {
		return showCheckbox;
	}

	public void setShowCheckbox(Boolean showCheckbox) {
		this.showCheckbox = showCheckbox;
	}

	public Integer getPrivilegeID1() {
		return privilegeID1;
	}

	public void setPrivilegeID1(Integer privilegeID1) {
		this.privilegeID1 = privilegeID1;
	}

	public Integer getPrivilegeID2() {
		return privilegeID2;
	}

	public void setPrivilegeID2(Integer privilegeID2) {
		this.privilegeID2 = privilegeID2;
	}

	public Integer getParentID() {
		return parentID;
	}

	public void setParentID(Integer parentID) {
		this.parentID = parentID;
	}

	public Integer getTreeType() {
		return treeType;
	}

	public void setTreeType(Integer treeType) {
		this.treeType = treeType;
	}

	public Boolean getIsDisable1() {
		return isDisable1;
	}

	public void setIsDisable1(Boolean isDisable1) {
		this.isDisable1 = isDisable1;
	}

	public Boolean getIsDisable2() {
		return isDisable2;
	}

	public void setIsDisable2(Boolean isDisable2) {
		this.isDisable2 = isDisable2;
	}

}
