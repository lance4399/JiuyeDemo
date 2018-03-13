package cn.jiuye.entity;

public class PostedJobVO {
	private int id;
	private String jobName;//not null
	private String accepted;
	private String checked;
	private String postedTime;
	public String getJobName() {
		return jobName;
	}
	public void setJobName(String jobName) {
		this.jobName = jobName;
	}
	
	public String getPostedTime() {
		return postedTime;
	}
	public void setPostedTime(String postedTime) {
		this.postedTime = postedTime;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getAccepted() {
		return accepted;
	}
	public void setAccepted(String accepted) {
		this.accepted = accepted;
	}
	public String getChecked() {
		return checked;
	}
	public void setChecked(String checked) {
		this.checked = checked;
	}
	@Override
	public String toString() {
		return "PostedJobVO [id=" + id + ", jobName=" + jobName + ", accepted="
				+ accepted + ", checked=" + checked + ", postedTime="
				+ postedTime + "]";
	}


	
}
