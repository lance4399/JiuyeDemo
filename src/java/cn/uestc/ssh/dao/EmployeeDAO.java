package cn.jiuye.dao;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

import cn.jiuye.entity.ApplyTable;
import cn.jiuye.entity.Employee;
import cn.jiuye.entity.Job;
import cn.jiuye.entity.JobVO;
import cn.jiuye.entity.PostedJobVO;
import cn.jiuye.entity.User;
import cn.jiuye.entity.UserEmployeeVO;
@Repository
public class EmployeeDAO implements IEmployeeDAO{
	public SessionFactory sessionFactory;
	
	@Resource
	public final void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	public final SessionFactory getSessionFactory() {
		return sessionFactory;
	}
	
	@SuppressWarnings("unused")
	private String timeFormat(){
		return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
	}
	
	@Override
	public List<JobVO> getJob() {
		String hql="SELECT j.jobName,c.companyName,j.postedTime FROM Job j,Company c WHERE j.company_id=c.id";
		String sql="SELECT j.id,j.jobName,c.companyName,j.postedTime FROM job AS j,company AS c WHERE j.company_id=c.id";
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		List<Object[]> list= session.createSQLQuery(sql).list(); 
		tx.commit();
		session.close();
		List<JobVO> jobVOlist = new ArrayList<JobVO>(); 
		for(int i=0; i<list.size(); i++){  
			 Object[] obs=list.get(i);  
			 JobVO job = new JobVO();  
			 job.setId((Integer)obs[0]);  
			 job.setJobName((String)obs[1]);  
			 job.setCompanyName((String)obs[2]);  
			 job.setPostedTime((String)obs[3]);  
			 jobVOlist.add(job);  
			}  
		System.out.println("执行了getJob查询");
		return jobVOlist;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<JobVO> getJobByName(String jobName) {
		String sql="SELECT j.id,j.jobName,c.companyName,j.postedTime FROM job AS j,company AS c"
				+ " WHERE j.company_id=c.id and j.jobName like :jobName ";
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		List<Object[]> list=  session.createSQLQuery(sql).setString("jobName", "%"+jobName+"%").list();
		tx.commit();
		session.close();
		System.out.println("执行了getJobByName(name)查询");
		List<JobVO> jobVOlist = new ArrayList<JobVO>(); 
		for(int i=0; i<list.size(); i++){  
			 Object[] obs=list.get(i);  
			 JobVO job = new JobVO();  
			 job.setId((Integer)obs[0]);  
			 job.setJobName((String)obs[1]);  
			 job.setCompanyName((String)obs[2]);  
			 job.setPostedTime((String)obs[3]);
			 jobVOlist.add(job);  
			}  
		return jobVOlist;
	}

	@Override
	public Job getJobItemById(int id) {
		String hql="from Job j where j.id=:id";	
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		Job checkedJob=(Job) session.createQuery(hql).setInteger("id",id).uniqueResult();
		tx.commit();
		session.close();
		System.out.println(checkedJob.toString());
		return checkedJob;
	}
	
	//若用户为企业，则由UserID查询其companyID;
	public int getEmployeeIdByUserID(int id){
		String sql="select e.id from employee e ,user u where e.user_id=u.id and u.id=?";
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		int employeeId=(int) session.createSQLQuery(sql).setInteger(0, id).uniqueResult(); 
		tx.commit();
		session.close();
		System.out.println("getCompanyIdByUserID(id)查询到的companyId为:"+employeeId);
		return employeeId;
		
	}
	
	//编辑个人信息(第一次操作是将信息插入数据库，第二次以后则为更新)
	@Override
	public void updatePersonInfo(UserEmployeeVO employeeVO) {
		Transaction tx=null;
		String name=employeeVO.getRealName();
		String gender=employeeVO.getGender();
		String birth=employeeVO.getBirth();
		String phoneNumber=employeeVO.getPhoneNumber();
		String school=employeeVO.getSchool();
		String xueli=employeeVO.getXueli();
		String major=employeeVO.getMajor();
		String ziwopingjia=employeeVO.getZiwoPingjia();
		String pic=employeeVO.getPic();
		
		int userId=employeeVO.getId();
		
		Session session = sessionFactory.openSession();
		tx = session.beginTransaction();	
		UserEmployeeVO evo=getEmployeeInfoById(userId);
		System.out.println("updatePersonInfo中执行了getPersonInfoById(id)查询");
	
		if (evo != null){				
			System.out.println("evo不为空");			
			int employeeId=getEmployeeIdByUserID(userId);		
			System.out.println("updatePersonInfo中执行了getEmployeeIdByUserID(id)查询");
			Employee e = (Employee) session.get(Employee.class,employeeId );		
			if(name!=null && name!=""){
				e.setRealName(name);
			}
			if(gender!=null && gender!=""){
				e.setGender(gender);
			}
			if(phoneNumber!=null &&phoneNumber!=""){
				e.setPhoneNumber(phoneNumber);
			}
			if(birth!=null &&birth!=""){
				e.setBirth(birth);
			}
			if(school!=null &&school!=""){
				e.setSchool(school);
			}	
			if(xueli!=null &&xueli!=""){
				e.setXueli(xueli);
			}
			if(major!=null &&major!=""){
				e.setMajor(major);
			}
			if(ziwopingjia!=null &&ziwopingjia!=""){
				e.setZiwoPingjia(ziwopingjia);
			}		
			if(pic!=null &&pic!=""){
				e.setPic(pic);
			}			
			System.out.println("personInfo即将开始更新....");
			session.update(e); 		
		}else{
			System.out.println("cvo为空,user_id:"+userId);
			User u=new User();
			u.setId(userId);
			Employee e=new Employee();
			e.setUser(u);
			e.setRealName(name);
			e.setGender(gender);	
			e.setPhoneNumber(phoneNumber);
			e.setBirth(birth);
			e.setSchool(school);
			e.setXueli(xueli);
			e.setMajor(major);
			e.setZiwoPingjia(ziwopingjia);
			e.setPic(pic);
			session.save(e);		
		}
		tx.commit();
		session.close();	
		
	}
	
	//查询简历信息
	@Override
	public UserEmployeeVO getEmployeeInfoById(int id) {
		System.out.println("进入getCompanyInfoById(id)查询,将要验证的信息为 ："+id);
		UserEmployeeVO returnOne=null;
		String sql="SELECT e.id, e.realName, e.gender, e.birth, e.phoneNumber, u.email, e.school, e.xueli, e.major, e.ziwoPingjia, e.pic "
				+ "FROM user u,employee e WHERE u.id=e.user_id AND u.id=:id";	
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		List<Object[]> list=(List<Object[]>)session.createSQLQuery(sql).setInteger("id", id).list(); 	
		tx.commit();
		session.close();
		List<UserEmployeeVO> VOlist = new ArrayList<UserEmployeeVO>(); 
		if(list !=null && list.size() >0 ){
		for(int i=0; i<list.size(); i++){  
			 Object[] obs=list.get(i);  
			 UserEmployeeVO vo = new UserEmployeeVO();  
			 vo.setId((Integer)obs[0]);  
			 vo.setRealName((String)obs[1]);  
			 vo.setGender((String)obs[2]);  
			 vo.setBirth((String)obs[3]);  
			 vo.setPhoneNumber((String)obs[4]); 
			 vo.setEmail((String)obs[5]);  
			 vo.setSchool((String)obs[6]);  
			 vo.setXueli((String)obs[7]);  
			 vo.setMajor((String)obs[8]);  
			 vo.setZiwoPingjia((String)obs[9]);  
			 vo.setPic((String)obs[10]);  
			 VOlist.add(vo);  
			 //System.out.println(vo.toString());
			}  
			returnOne=VOlist.get(0);
		}
		System.out.println("执行了getPersonInfoById(id)查询");
		return returnOne;
	}


	@Override
	public void addApplyTable(int jobID,  int userID) {
		Session session = sessionFactory.openSession();
		
		ApplyTable at=new ApplyTable();
		at.setDatetime(timeFormat());
		at.setAccepted("NO");
		at.setChecked("NO");
		Employee e=new Employee();
		e.setId(getEmployeeIdByUserID(userID));
		Job j=new Job();
		j.setId(jobID);
		at.setEmployee(e);
		at.setJob(j);
		Transaction tx = session.beginTransaction();
		session.save(at);
		tx.commit();
		session.close();
		
	}
	
	//由用户id查询其所投递的岗位名单    
	@Override
	public  List<PostedJobVO> getJobListByUserID(int userId) {
		String sql="SELECT j.id ,j.jobName,a.datetime,a.accepted,a.checked FROM job j,applytable a WHERE a.job_id=j.id "
				+ "AND a.employee_id=(SELECT e.id FROM employee e,USER u WHERE u.id=e.user_id AND u.id=?)";
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		List<Object[]> list=  session.createSQLQuery(sql).setInteger(0, userId).list();
		tx.commit();
		session.close();
		List<PostedJobVO> postedjobVOlist = new ArrayList<PostedJobVO>(); 
		for(int i=0; i<list.size(); i++){  
			 Object[] obs=list.get(i);  
			 PostedJobVO postedjobVO = new PostedJobVO();  
			 postedjobVO.setId((Integer)obs[0]);  
			 postedjobVO.setJobName((String)obs[1]);  
			 postedjobVO.setPostedTime((String)obs[2]);  
			 postedjobVO.setAccepted((String)obs[3]);
			 postedjobVO.setChecked((String)obs[4]);
			 postedjobVOlist.add(postedjobVO);  
			 System.out.println(postedjobVO.toString());
			}  
	return postedjobVOlist;
	
	
	}
}
