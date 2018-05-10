package org.example.users.vo;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.springframework.security.core.SpringSecurityCoreVersion;

public class MenuNode implements Serializable {
	private static final long serialVersionUID = SpringSecurityCoreVersion.SERIAL_VERSION_UID;
	
	private int id;
	private int parentId;
	private String name;
	private String url;
	private String descrption;
	private String state;
	private int showOrder;
	private Map<String, Object> attr;
//	@JsonIgnore
//	private MenuNode parent;
	private List<MenuNode> children;

	public MenuNode() {

	}
	
	public MenuNode(int id, int parentId, String name, String url) {
		super();
		this.id = id;
		this.parentId = parentId;
		this.name = name;
		this.url = url;
	}

	public MenuNode(int id, int parentId, String name, String url, List<MenuNode> children) {
		super();
		this.id = id;
		this.parentId = parentId;
		this.name = name;
		this.url = url;
		this.children = children;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getParentId() {
		return parentId;
	}

	public void setParentId(int parentId) {
		this.parentId = parentId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getDescrption() {
		return descrption;
	}

	public void setDescrption(String descrption) {
		this.descrption = descrption;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public int getShowOrder() {
		return showOrder;
	}

	public void setShowOrder(int showOrder) {
		this.showOrder = showOrder;
	}

	public Map<String, Object> getAttr() {
		return attr;
	}

	public void setAttr(Map<String, Object> attr) {
		this.attr = attr;
	}
//	@JsonBackReference
//	public MenuNode getParent() {
//		return parent;
//	}
//
//	public void setParent(MenuNode parent) {
//		this.parent = parent;
//	}

	public List<MenuNode> getChildren() {
		return children;
	}

	public void setChildren(List<MenuNode> children) {
		this.children = children;
	}

}
