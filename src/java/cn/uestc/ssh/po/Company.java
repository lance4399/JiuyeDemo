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
@Table(name="company")
public class Company implements java.io.Serializable{
	private static final long serialVersionUID = 1L;
	private int id;
	private String companyName;
	private String Hangye;
	private String phoneNumber;
	private String ziwoPingjia;
	private String address;
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
	
	
	
}
