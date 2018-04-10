package org.example.users.vo;

import java.io.Serializable;
import java.util.List;

import org.springframework.security.core.SpringSecurityCoreVersion;

public class UserInfoVO implements Serializable {
	private static final long serialVersionUID = SpringSecurityCoreVersion.SERIAL_VERSION_UID;
	
	private Long id;
	private String nickName;
	private String address;

	private List<String> userRole;

	public UserInfoVO() {

	}

	public UserInfoVO(Long id, String nickName, String address) {
		super();
		this.id = id;
		this.nickName = nickName;
		this.address = address;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public List<String> getUserRole() {
		return userRole;
	}

	public void setUserRole(List<String> userRole) {
		this.userRole = userRole;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

}
