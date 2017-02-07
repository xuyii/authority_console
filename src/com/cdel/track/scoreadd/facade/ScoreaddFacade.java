package com.cdel.track.scoreadd.facade;

import java.io.Serializable;

import org.springframework.stereotype.Service;

import com.cdel.track.scoreadd.domain.Scoreadd;
import com.cdel.util.BaseFacadeImpl;

@SuppressWarnings("serial")
@Service
public class ScoreaddFacade extends BaseFacadeImpl<Scoreadd, Integer> implements
		Serializable {

	/**
	 * 验证数据
	 * 
	 * @param updateObj
	 */
	public boolean checkScoreadd(Scoreadd updateObj) {
		String courseClassName = updateObj.getCourseClassName();
		if (courseClassName == null) {
			return addWarnMessage("班级不能为空！");
		}
		return true;
	}

}
