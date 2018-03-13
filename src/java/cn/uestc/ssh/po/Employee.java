package cn.jiuye.entity;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

@Entity
@Table(name="employee")
public class Employee implements java.io.Serializable{
	private static final long serialVersionUID = 1L;
	private int id;
	private String realName; 
	private String gender;
	private String birth;
	private String phoneNumber;
	private String school;
	private String major;	
	private String xueli;
	private String ziwoPingjia;
	private String pic;
	private User user;
	
	@OneToOne
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO) 
	@Column( nullable = false)
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getRealName() {
		return realName;
	}
	public void setRealName(String realName) {
		this.realName = realName;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
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
	public String getBirth() {
		return birth;
	}
	public void setBirth(String birth) {
		this.birth = birth;
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
	
	
	

}
