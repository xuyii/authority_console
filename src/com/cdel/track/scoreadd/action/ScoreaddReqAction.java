package com.cdel.track.scoreadd.action;

import java.io.Serializable;
import java.util.Date;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;

import com.cdel.track.scoreadd.domain.Scoreadd;
import com.cdel.track.scoreadd.facade.ScoreaddFacade;
import com.cdel.util.BaseAction;
import com.cdel.util.Contants;
import com.cdel.util.ExceptionUtil;

@SuppressWarnings("serial")
@ManagedBean
public class ScoreaddReqAction extends BaseAction<Scoreadd> implements Serializable {
	@ManagedProperty(value = "#{scoreaddFacade}")
	private ScoreaddFacade scoreaddFacade;

	private Scoreadd updateObj = new Scoreadd();
	private byte flag = -1;
	private byte submitSuccess = 0;// 添加是否成功

	/**
	 * 打开添加页面
	 */
	public void add() {
		flag = 0;
	}

	/**
	 * 打开编辑页面
	 */
	public void update(Integer scoreID) {
		flag = 1;
		updateObj = scoreaddFacade.findByID(scoreID);
	}

	/**
	 * 提交
	 */
	public void updateSubmit() {
		try {
			if (scoreaddFacade.checkScoreadd(updateObj)) {
				ScoreaddAction ra = (ScoreaddAction) this.getViewAction("scoreaddAction");
				if (flag == 0) {
					updateObj.setCreateTime(new Date());
					scoreaddFacade.add(updateObj);
					ra.search();
				} else {
					scoreaddFacade.update(updateObj);
					ra.search4U();
				}
				submitSuccess = 1;
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("updateObj(book)=" + updateObj);
			logger.error(ExceptionUtil.getEMsg(e));
			submitSuccess = -1;
		}
		this.addCallbackParam("result", submitSuccess);
	}

	public Map<Integer, String> getCategorys() {
		return Contants.roleCategorys;
	}

	public byte getFlag() {
		return flag;
	}

	public void setFlag(byte flag) {
		this.flag = flag;
	}

	public ScoreaddFacade getScoreaddFacade() {
		return scoreaddFacade;
	}

	public void setScoreaddFacade(ScoreaddFacade scoreaddFacade) {
		this.scoreaddFacade = scoreaddFacade;
	}

	public Scoreadd getUpdateObj() {
		return updateObj;
	}

	public void setUpdateObj(Scoreadd updateObj) {
		this.updateObj = updateObj;
	}

	public byte getSubmitSuccess() {
		return submitSuccess;
	}

	public void setSubmitSuccess(byte submitSuccess) {
		this.submitSuccess = submitSuccess;
	}
	
}

	
