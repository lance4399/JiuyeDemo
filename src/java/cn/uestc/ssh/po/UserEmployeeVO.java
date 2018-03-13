package cn.jiuye.entity;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import cn.jiuye.validGroup.ValidGroup1;
import cn.jiuye.validGroup.ValidGroup2;

public class UserEmployeeVO {

	private int id;
	private String username;
	private String password;
	private String email;
	private String role;
	private String registtime;
	private String realName; 
	private String gender;
	private String birth;
	private String phoneNumber;
	private String school;
	private String major;	
	private String xueli;
	private String ziwoPingjia;
	private String pic;

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
	public String getRealName() {
		return realName;
	}
	public void setRealName(String realName) {
		this.realName = realName;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getBirth() {
		return birth;
	}
	public void setBirth(String birth) {
		this.birth = birth;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getSchool() {
		return school;
	}
	public void setSchool(String school) {
		this.school = school;
	}
	public String getMajor() {
		return major;
	}
	public void setMajor(String major) {
		this.major = major;
	}
	public String getXueli() {
		return xueli;
	}
	public void setXueli(String xueli) {
		this.xueli = xueli;
	}
	public String getZiwoPingjia() {
		return ziwoPingjia;
	}
	public void setZiwoPingjia(String ziwoPingjia) {
		this.ziwoPingjia = ziwoPingjia;
	}
	public String getPic() {
		return pic;
	}
	public void setPic(String pic) {
		this.pic = pic;
	}
	@Override
	public String toString() {
		return "UserEmployeeVO [id=" + id + ", username=" + username
				+ ", password=" + password + ", email=" + email + ", role="
				+ role + ", registtime=" + registtime + ", realName="
				+ realName + ", gender=" + gender + ", birth=" + birth
				+ ", phoneNumber=" + phoneNumber + ", school=" + school
				+ ", marjor=" + major + ", xueli=" + xueli + ", ziwoPingjia="
				+ ziwoPingjia  + ", pic=" + pic + "]";
	}
	
	
}
