/*
 * Copyright (c) 2000-2008 CHNEDU.COM. All Rights Reserved.
 *
 * This software is the confidential and proprietary information of 
 * CHNEDU.COM. ("Confidential Information"). You shall not
 * disclose such Confidential Information and shall use it only in
 * accordance with the terms of the license agreement you entered into
 * with CHNEDU.COM.
 *
 */
package com.cdel.plat.grant.domain;

import java.io.Serializable;
import com.chnedu.plat.rad.domain.BaseDomain;

/**
 * Class description 关联节点.
 * 
 * @version 1.0 2008-1-17
 * @author LiXuFang - j2eeli@chinaacc.com
 */
public class PrivilegeRel extends BaseDomain implements Serializable {
	private static final long serialVersionUID = -909287480225229712L;

	private Integer privilegeID;
	private String privilegeName;
	private Integer relPrivilegeID;
	private Integer relNeighborID;
	private String actionURL;

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

	public Integer getRelPrivilegeID() {
		return relPrivilegeID;
	}

	public void setRelPrivilegeID(Integer relPrivilegeID) {
		this.relPrivilegeID = relPrivilegeID;
	}

	public Integer getRelNeighborID() {
		return relNeighborID;
	}

	public void setRelNeighborID(Integer relNeighborID) {
		this.relNeighborID = relNeighborID;
	}

	public String getActionURL() {
		return actionURL;
	}

	public void setActionURL(String actionURL) {
		this.actionURL = actionURL;
	}

}