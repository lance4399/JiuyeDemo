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
import cn.jiuye.entity.Company;
import cn.jiuye.entity.Employee;
import cn.jiuye.entity.Job;
import cn.jiuye.entity.JobVO;
import cn.jiuye.entity.PostedJobVO;
import cn.jiuye.entity.User;
import cn.jiuye.entity.UserCompanyVO;
import cn.jiuye.entity.UserEmployeeVO;
@Repository
public class EmployerDAO implements IEmployerDAO{
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
	
	//查询企业信息
	@Override
	public UserCompanyVO getCompanyInfoById(int id) {
		System.out.println("进入getCompanyInfoById(id)查询,将要验证的信息为 ："+id);
		UserCompanyVO returnOne=null;
		String sql="SELECT c.id, c.companyName, c.hangye, c.phoneNumber,u.email,c.address,c.ziwoPingjia "
				+ "FROM User u,Company c WHERE u.id=c.user_id AND u.id=:id";	
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		List<Object[]> list=(List<Object[]>)session.createSQLQuery(sql).setInteger("id", id).list(); 	
		tx.commit();
		session.close();
		List<UserCompanyVO> VOlist = new ArrayList<UserCompanyVO>(); 
		if(list !=null && list.size() >0 ){
		for(int i=0; i<list.size(); i++){  
			 Object[] obs=list.get(i);  
			 UserCompanyVO vo = new UserCompanyVO();  
			 vo.setId((Integer)obs[0]);  
			 vo.setCompanyName((String)obs[1]);  
			 vo.setHangye((String)obs[2]);  
			 vo.setPhoneNumber((String)obs[3]);  
			 vo.setEmail((String)obs[4]);  
			 vo.setAddress((String)obs[5]);  
			 vo.setZiwoPingjia((String)obs[6]);  
			 VOlist.add(vo);  
			 //System.out.println(vo.toString());
			}  
			returnOne=VOlist.get(0);
		}
		System.out.println("执行了loginFromCompany(id)查询");
		return returnOne;
	}
	
	//若用户为企业，则由UserID查询其companyID;
	public int getCompanyIdByUserID(int id){
		String sql="select c.id from company c ,user u where c.user_id=u.id and u.id=?";
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		int companyId=(int) session.createSQLQuery(sql).setInteger(0, id).uniqueResult(); 
		tx.commit();
		session.close();
		System.out.println("getCompanyIdByUserID(id)查询到的companyId为:"+companyId);
		return companyId;
		
	}
	
	//编辑公司信息(第一次操作是将信息插入数据库，第二次以后则为更新)
	@Override
	public void updateCompanyInfo(UserCompanyVO companyVO) {
		Transaction tx=null;
		String name=companyVO.getCompanyName();
		String Hangye=companyVO.getHangye();
		String phoneNumber=companyVO.getPhoneNumber();
		String address=companyVO.getAddress();
		String ziwoPingjia=companyVO.getZiwoPingjia();
		int userId=companyVO.getId();
		Session session = sessionFactory.openSession();
		tx = session.beginTransaction();	
		UserCompanyVO cvo=getCompanyInfoById(userId);
		System.out.println("updateCompanyInfo中执行了getCompanyInfoById(id)查询");
	
		if (cvo != null){				
			System.out.println("cvo不为空");			
			int companyId=getCompanyIdByUserID(userId);
			
			System.out.println("updateCompanyInfo中执行了getCompanyIdByUserID(id)查询");
			Company c = (Company) session.get(Company.class,companyId );
			
			if(name!=null && name!=""){
				c.setCompanyName(name);
			}
			if(Hangye!=null && Hangye!=""){
				c.setHangye(Hangye);
			}
			if(phoneNumber!=null &&phoneNumber!=""){
				c.setPhoneNumber(phoneNumber);
			}
			if(address!=null &&address!=""){
				c.setAddress(address);
			}
			if(ziwoPingjia!=null &&ziwoPingjia!=""){
				c.setZiwoPingjia(ziwoPingjia);
			}			
			session.update(c); 
		}else{
			System.out.println("cvo为空,user_id:"+userId);
			User u=new User();
			u.setId(userId);
			Company c=new Company();
			c.setUser(u);
			c.setCompanyName(name);
			c.setHangye(Hangye);
			c.setPhoneNumber(phoneNumber);
			c.setAddress(address);
			c.setZiwoPingjia(ziwoPingjia);
			session.save(c);
			
		}
		tx.commit();
		session.close();	
	}

	//发布岗位
	@Override
	public void addJobItem(Job job,int userId) {
		
		Session session = sessionFactory.openSession();
		job.setPostedTime(timeFormat());
		Transaction tx = session.beginTransaction();
		int companyId=getCompanyIdByUserID(userId);
		Company c=new Company();
		c.setId(companyId);
		job.setCompany(c);
		session.save(job);	
		tx.commit();
	    session.close();
	}
	@Override
	public List<Job> getJobItemByCompanyId(int userId) {
		
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		int companyId=getCompanyIdByUserID(userId);	
		String hql="from Job j where j.company=?";
		System.out.println("即将开始执行getJobItemByCompanyID的sql查询");
		List<Job> jobItemList=( List<Job>)session.createQuery(hql).setInteger(0, companyId).list(); 
		for(int i=0; i<jobItemList.size(); i++){  
			 Job job=jobItemList.get(i);
			 System.out.println("第"+i+"条job:"+job.getJobName());
			}  
		System.out.println("执行了getJob查询");
		tx.commit();
	    session.close();
		return jobItemList;
	}

	@Override
	public List<ApplyTable> getApplytableItemByJobId(int jobId) {
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		//int companyId=getCompanyIdByUserID(jobId);	
		String sql="select a.* from applytable a where a.job_id=?";
		String hql="from ApplyTable a where a.job=:jobId";
		System.out.println("即将开始执行getApplytableItemByJobId的hql查询");
		//List<ApplyTable> TableList=( List<ApplyTable>)session.createSQLQuery(sql).setInteger(0, jobId).list(); 
		List<ApplyTable> TableList=( List<ApplyTable>)session.createQuery(hql).setInteger("jobId", jobId).list(); 
		for(int i=0; i<TableList.size(); i++){  
			ApplyTable at=TableList.get(i);
			 System.out.println("第"+i+"条申请记录的时间为:"+at.getDatetime());
			}  
		System.out.println("执行了getApplytableItemByJobId查询");
		tx.commit();
	    session.close();
		return TableList;

	}

	//根据ApplyTable中的employee_id字段查找user_id
	public int getUserIdByResumeID(int employeeId){
		String sql="select DISTINCT e.user_id from employee e,applytable a where e.id=a.employee_id and a.employee_id=?";
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		int userId=(int) session.createSQLQuery(sql).setInteger(0, employeeId).uniqueResult(); 
		tx.commit();
		session.close();
		System.out.println("getCompanyIdByUserID(id)查询到的userId为:"+userId);
		return userId;
	}
	
	@Override
	public UserEmployeeVO getResumeByID(int employeeId) {

		int userId=getUserIdByResumeID(employeeId);	
		System.out.println("进入getResumeByID(id)查询,将要验证的信息为 ："+userId);
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		UserEmployeeVO returnOne=null;
		String sql="SELECT e.id, e.realName, e.gender, e.birth, e.phoneNumber, u.email, e.school, e.xueli, e.major, e.ziwoPingjia, e.pic "
				+ "FROM user u,employee e WHERE u.id=e.user_id AND u.id=:id";	
		List<Object[]> list=(List<Object[]>)session.createSQLQuery(sql).setInteger("id", userId).list(); 				
		
		tx.commit();
		Transaction tx2 = session.beginTransaction();
		ApplyTable at=(ApplyTable) session.get(ApplyTable.class, employeeId);
		if(!at.getChecked().equals("YES")){
			at.setChecked("YES");
		}
			session.update(at);
		tx2.commit();
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
			 System.out.println(vo.toString());
			}  
			returnOne=VOlist.get(0);
		}
		System.out.println("执行了getResumeByID(id)查询");
		return returnOne;
	}

	@Override
	public void getAcceptedByResumeId(int resumeId) {
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();

		ApplyTable at=(ApplyTable) session.get(ApplyTable.class, resumeId);
		if(!at.getAccepted().equals("YES")){
			at.setAccepted("YES");
		}		
		session.update(at);	
		tx.commit();
		//session.update(at);	
		session.close();
		
	}
	
	
}
