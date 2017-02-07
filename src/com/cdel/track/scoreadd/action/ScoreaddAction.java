package com.cdel.track.scoreadd.action;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.primefaces.model.LazyDataModel;

import com.cdel.track.scoreadd.domain.Scoreadd;
import com.cdel.track.scoreadd.facade.ScoreaddFacade;
import com.cdel.util.BaseAction;

@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class ScoreaddAction extends BaseAction<Scoreadd> implements Serializable {
	@ManagedProperty(value = "#{scoreaddFacade}")
	private ScoreaddFacade scoreaddFacade;

	private LazyDataModel<Scoreadd> page;
	private Scoreadd search = new Scoreadd();

	@PostConstruct
	public void initAction() {
		this.page = this.scoreaddFacade.findPage(search);
		super.heighti2 = super.calHeight(11.5f / 20);
	}

	/**
	 * 查询
	 */
	public void search() {
		pageTable.setFirst(0);
		search4U();
	}

	/**
	 * 查询
	 */
	public void search4U() {
		this.page = this.scoreaddFacade.findPage(search);
		this.updateComponent("searchForm:list");
	}

	public LazyDataModel<Scoreadd> getPage() {
		return page;
	}

	public void setPage(LazyDataModel<Scoreadd> page) {
		this.page = page;
	}

	public Scoreadd getSearch() {
		return search;
	}

	public void setSearch(Scoreadd search) {
		this.search = search;
	}

	public ScoreaddFacade getScoreaddFacade() {
		return scoreaddFacade;
	}

	public void setScoreaddFacade(ScoreaddFacade scoreaddFacade) {
		this.scoreaddFacade = scoreaddFacade;
	}

	
}
