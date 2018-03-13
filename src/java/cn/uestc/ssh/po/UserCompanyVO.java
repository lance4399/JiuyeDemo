package cn.jiuye.entity;

public class UserCompanyVO {

	private int id;//
	private String username;
	private String password;
	private String email;//
	private String role;
	private String registtime;
	private String companyName;//
	private String Hangye;//
	private String phoneNumber;//
	private String ziwoPingjia; //
	private String address;//

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	

	public final String getUsername() {
		return username;
	}
	public final void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public final String getEmail() {
		return email;
	}
	public final void setEmail(String email) {
		this.email = email;
	}
	public final String getRole() {
		return role;
	}
	public final void setRole(String role) {
		this.role = role;
	}
	public final String getRegisttime() {
		return registtime;
	}
	public final void setRegisttime(String registtime) {
		this.registtime = registtime;
	}
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public String getHangye() {
		return Hangye;
	}
	public void setHangye(String hangye) {
		Hangye = hangye;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getZiwoPingjia() {
		return ziwoPingjia;
	}
	public void setZiwoPingjia(String ziwoPingjia) {
		this.ziwoPingjia = ziwoPingjia;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	@Override
	public String toString() {
		return "UserCompanyVO [id=" + id + ", username=" + username
				+ ", password=" + password + ", email=" + email + ", role="
				+ role + ", registtime=" + registtime + ", companyName="
				+ companyName + ", Hangye=" + Hangye + ", phoneNumber="
				+ phoneNumber + ", ziwoPingjia=" + ziwoPingjia + ", address="
				+ address + "]";
	}
	
}
