package com.cdel.plat.grant.action;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.primefaces.event.NodeSelectEvent;
import org.primefaces.model.TreeNode;
import org.springframework.util.StringUtils;

import com.cdel.plat.grant.domain.Document;
import com.cdel.plat.grant.domain.Privilege;
import com.cdel.plat.grant.facade.PrivilegeFacade;
import com.cdel.util.BaseAction;

/**
 * @author 徐意
 *
 */
@ManagedBean
@ViewScoped
@SuppressWarnings("serial")
public class PrivilegeAction extends BaseAction<Privilege> implements
		Serializable {

	@ManagedProperty(value = "#{privilegeFacade}")
	private PrivilegeFacade privilegeFacade;

	private TreeNode root;
	private TreeNode selectedNode;
	private Privilege showObj;
	private boolean yezi;// 是否叶子节点

	@PostConstruct
	public void init() {
		search();
	}

	public void search() {
		root = privilegeFacade.createDocuments(null);
	}

	/**
	 * 选中节点
	 * 
	 * @param event
	 */
	public void onNodeSelect(NodeSelectEvent event) {
		Document doc = (Document) event.getTreeNode().getData();
		if (doc.getTreeType().intValue() == 1) {
			// 非叶子节点
			yezi = false;
		} else {
			// 叶子节点
			yezi = true;
		}
		showObj = privilegeFacade.findByID(doc.getPrivilegeID1());
		if (showObj == null) {
			showObj = new Privilege();
			showObj.setPrivilegeID(doc.getPrivilegeID1());
			showObj.setPrivilegeName(doc.getPrivilegeName());
			showObj.setSystemType(Integer.parseInt(StringUtils.replace(
					doc.getAction(), "sys", "")));
			showObj.setTreeLevel(-1);
		}
	}

	public TreeNode getRoot() {
		return root;
	}

	public TreeNode getSelectedNode() {
		return selectedNode;
	}

	public void setSelectedNode(TreeNode selectedNode) {
		this.selectedNode = selectedNode;
	}

	public void setPrivilegeFacade(PrivilegeFacade privilegeFacade) {
		this.privilegeFacade = privilegeFacade;
	}

	public boolean isYezi() {
		return yezi;
	}

	public void setYezi(boolean yezi) {
		this.yezi = yezi;
	}

	public Privilege getShowObj() {
		return showObj;
	}

	public void setShowObj(Privilege showObj) {
		this.showObj = showObj;
	}

}
