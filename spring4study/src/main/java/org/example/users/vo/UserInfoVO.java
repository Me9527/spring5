package org.example.users.vo;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.springframework.security.core.SpringSecurityCoreVersion;

public class UserInfoVO implements Serializable {
	private static final long serialVersionUID = SpringSecurityCoreVersion.SERIAL_VERSION_UID;

	private Long id;
	private String nickName;
	private String sex;
	private String mobilePhone;
	private String telphone;
	private String qq;
	private String im;
	private String company;
	private String department;
	private String position;
	private String address;
	private Date birthday;
	
	private List<String> userRole;
	private List<MenuNode> menus;

	public UserInfoVO() {

	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getMobilePhone() {
		return mobilePhone;
	}

	public void setMobilePhone(String mobilePhone) {
		this.mobilePhone = mobilePhone;
	}

	public String getTelphone() {
		return telphone;
	}

	public void setTelphone(String telphone) {
		this.telphone = telphone;
	}

	public String getQq() {
		return qq;
	}

	public void setQq(String qq) {
		this.qq = qq;
	}

	public String getIm() {
		return im;
	}

	public void setIm(String im) {
		this.im = im;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
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

	public List<MenuNode> getMenus() {
		return menus;
	}

	public void setMenus(List<MenuNode> menus) {
		this.menus = menus;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	
}
