package cn.jiuye.entity;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

@Entity
@Table(name="job")
public class Job implements java.io.Serializable{
	private static final long serialVersionUID = 1L;
	private int id;
	private String jobName;//not null
	private String workPlace;//not null
	private String jobType;//IT或行政
	private String workType;//全职或兼职
	private float salary;
	private String positionDescription;
	private String xueli;//not null
	private Company company;
	private String postedTime;
	
	
	@ManyToOne//(cascade=CascadeType.ALL)
	@JoinColumn(name="company_id")
	public Company getCompany() {
		return company;
	}
	public void setCompany(Company company) {
		this.company = company;
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
	
	public String getWorkType() {
		return workType;
	}
	public void setWorkType(String workType) {
		this.workType = workType;
	}

	public String getWorkPlace() {
		return workPlace;
	}
	public void setWorkPlace(String workPlace) {
		this.workPlace = workPlace;
	}
	public String getJobType() {
		return jobType;
	}
	public void setJobType(String jobType) {
		this.jobType = jobType;
	}
	public float getSalary() {
		return salary;
	}
	public void setSalary(float salary) {
		this.salary = salary;
	}

	public String getPositionDescription() {
		return positionDescription;
	}
	public void setPositionDescription(String positionDescription) {
		this.positionDescription = positionDescription;
	}
	
	public String getJobName() {
		return jobName;
	}
	public void setJobName(String jobName) {
		this.jobName = jobName;
	}
	
	@Column( nullable = false)
	public String getXueli() {
		return xueli;
	}
	public void setXueli(String xueli) {
		this.xueli = xueli;
	}
	public String getPostedTime() {
		return postedTime;
	}
	public void setPostedTime(String postedTime) {
		this.postedTime = postedTime;
	}
	@Override
	public String toString() {
		return "Job [id=" + id + ", jobName=" + jobName + ", workPlace="
				+ workPlace + ", jobType=" + jobType + ", workType=" + workType
				+ ", salary=" + salary + ", positionDescription="
				+ positionDescription + ", xueli=" + xueli + ", company="
				+ company + ", postedTime=" + postedTime + "]";
	}

	
}
