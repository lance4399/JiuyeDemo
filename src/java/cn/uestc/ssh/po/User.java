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

@Entity
@Table(name="user")
public class User implements java.io.Serializable{
	private static final long serialVersionUID = 1L;
	private int id;
	
	@NotNull(message="{user.username.isNull}",groups={ValidGroup1.class,ValidGroup2.class})
	private String username;
	
	@NotNull(message="{user.password.isNull}",groups={ValidGroup1.class,ValidGroup2.class})
	private String password;
	
	@NotNull(message="{user.email.isNull}",groups={ValidGroup2.class})
	private String email;
	
	@NotNull(message="{user.role.isNull}",groups={ValidGroup1.class,ValidGroup2.class})
	private String role;
	
	private String registtime;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO) 
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	

	@Column( nullable = false)
	public final String getUsername() {
		return username;
	}
	public final void setUsername(String username) {
		this.username = username;
	}
	
	@Column( nullable = false)
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	@Column( nullable = false)
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
	
	
	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", password="
				+ password + ", email=" + email + ", role=" + role
				+ ", registtime=" + registtime + "]";
	}
	
	public boolean equals(Object obj){
		if (this==obj){
			return true;
		}
		if(obj!=null&&obj.getClass()==User.class){
			User target=(User)obj;
			return target.getEmail().equals(getEmail())&&target.getUsername().equals(getUsername());//target.getId()==(getId())&&
		}
		return false;
	}
}
