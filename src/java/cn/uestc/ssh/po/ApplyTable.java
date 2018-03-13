package cn.jiuye.entity;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="applytable")
public class ApplyTable implements java.io.Serializable{
	private static final long serialVersionUID = 1L;
	private int id;
	private String checked;
	private String accepted;
	private String datetime;
	private Job job;
	private Employee employee;
	
	@OneToOne
	public Job getJob() {
		return job;
	}
	
	public void setJob(Job job) {
		this.job=job;
	}
	@OneToOne
	public Employee getEmployee() {
		return employee;
	}
	public void setEmployee(Employee employee) {
		this.employee = employee;
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

	public String getChecked() {
		return checked;
	}
	public void setChecked(String checked) {
		this.checked = checked;
	}

	public String getAccepted() {
		return accepted;
	}
	public void setAccepted(String accepted) {
		this.accepted = accepted;
	}

	public String getDatetime() {
		return datetime;
	}
	public void setDatetime(String datetime) {
		this.datetime = datetime;
	}
	
	
	
	
	/*public boolean equals(Object obj){
		if (this==obj){
			return true;
		}
		if(obj!=null&&obj.getClass()==Employee_Apply.class){
			Employee_Apply target=(Employee_Apply)obj;
			return target.getEmail().equals(getEmail())&&target.getUsername().equals(getUsername());//target.getId()==(getId())&&
		}
		return false;
	}*/
}
