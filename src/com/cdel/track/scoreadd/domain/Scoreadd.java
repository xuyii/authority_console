package com.cdel.track.scoreadd.domain;

import java.io.Serializable;
import java.util.Date;

import com.chnedu.plat.rad.domain.BaseDomain;

/**
 * 成绩录入
 *
 * @version 1.0
 * @author 徐意
 */
public class Scoreadd extends BaseDomain implements Serializable {

	private static final long serialVersionUID = 4643867997230319575L;
	private Integer sCourseClassID;
	private Integer adminID;
	private Integer courseClassID;
	private Date createTime;
	private String courseClassName;
	private Integer courseID;
	
	
	public String getCourseClassName() {
		return courseClassName;
	}
	public void setCourseClassName(String courseClassName) {
		this.courseClassName = courseClassName;
	}
	public Integer getCourseID() {
		return courseID;
	}
	public void setCourseID(Integer courseID) {
		this.courseID = courseID;
	}
	public Integer getAdminID() {
		return adminID;
	}
	public void setAdminID(Integer adminID) {
		this.adminID = adminID;
	}
	public Integer getCourseClassID() {
		return courseClassID;
	}
	public void setCourseClassID(Integer courseClassID) {
		this.courseClassID = courseClassID;
	}
	public Integer getsCourseClassID() {
		return sCourseClassID;
	}
	public void setsCourseClassID(Integer sCourseClassID) {
		this.sCourseClassID = sCourseClassID;
	}
	
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((sCourseClassID == null) ? 0 : sCourseClassID.hashCode());
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
		Scoreadd other = (Scoreadd) obj;
		if (sCourseClassID == null) {
			if (other.sCourseClassID != null)
				return false;
		} else if (!sCourseClassID.equals(other.sCourseClassID))
			return false;
		return true;
	}


}
