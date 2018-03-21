package org.example.users.model;

public class SecurityMetadata {

	private Long id;
	private String resource;
	private String resourceType;
	private String name;
	private String attribute;
	private String enable;
	private String memo;

	@Override
	public String toString() {
		return "SecurityMetadata [id=" + id + ", resource=" + resource
				+ ", resourceType=" + resourceType + ", name=" + name
				+ ", attribute=" + attribute + ", enable=" + enable + ", memo="
				+ memo + "]";
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getResource() {
		return resource;
	}

	public void setResource(String resource) {
		this.resource = resource;
	}

	public String getResourceType() {
		return resourceType;
	}

	public void setResourceType(String resourceType) {
		this.resourceType = resourceType;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAttribute() {
		return attribute;
	}

	public void setAttribute(String attribute) {
		this.attribute = attribute;
	}

	public String getEnable() {
		return enable;
	}

	public void setEnable(String enable) {
		this.enable = enable;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

}
