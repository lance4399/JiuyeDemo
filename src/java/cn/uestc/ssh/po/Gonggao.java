package cn.jiuye.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="gonggao")
public class Gonggao implements java.io.Serializable{
	private static final long serialVersionUID = 1L;
	private int id;
	private String title; 
	private String content;
	private String postedTime;
	private User user;
	
	@ManyToOne//(cascade=CascadeType.ALL)
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
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getPostedTime() {
		return postedTime;
	}
	public void setPostedTime(String postedTime) {
		this.postedTime = postedTime;
	}
	@Override
	public String toString() {
		return "Gonggao [id=" + id + ", title=" + title + ", content="
				+ content + ", postedTime=" + postedTime + ", user=" + user
				+ "]";
	}
	

}
