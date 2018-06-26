package org.example.module01.model;
//select ud.id, ud.uid, ud.nickname, ud.address  from p_users_detail ud where uid = ?";
public class UserInfo {
    private Long id;

    private Long uid;
    
    private String nickname;

    private String address;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Long getUid() {
		return uid;
	}

	public void setUid(Long uid) {
		this.uid = uid;
	}

	@Override
	public String toString() {
		return "UserInfo [id=" + id + ", nickname=" + nickname + ", address=" + address + "]";
	}

    
}