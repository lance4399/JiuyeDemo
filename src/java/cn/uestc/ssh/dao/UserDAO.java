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

import cn.jiuye.entity.Company;
import cn.jiuye.entity.Gonggao;
import cn.jiuye.entity.Job;
import cn.jiuye.entity.JobVO;
import cn.jiuye.entity.User;
import cn.jiuye.entity.UserCompanyVO;
import cn.jiuye.entity.UserEmployeeVO;
import cn.jiuye.exception.CustomException;
@Repository
public class UserDAO  implements IUserDAO  {
	
	public SessionFactory sessionFactory;
				
	@Resource
	public final void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	public final SessionFactory getSessionFactory() {
		return sessionFactory;
	}
	
	private String timeFormat(){
		return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
	}
	//用户注册
	@Override
	public void insert(User user) throws CustomException {
		Session session = sessionFactory.openSession();
		user.setRegisttime(timeFormat());
		Transaction tx = session.beginTransaction();
		session.save(user);	
		tx.commit();
	    session.close();		
		System.out.println("DAO invoked!");
	}	
	//用户登录校验
	@Override
	@SuppressWarnings("unchecked")
	public User login(User user) throws CustomException {
		User loginUser=null;
		String hql="from User where username=:username and password=:password and role=:role";
		Session session =sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
	
		List<User> list=(List<User>) session.createQuery(hql).setString("username",user.getUsername())
				.setString("password",user.getPassword()).setString("role", user.getRole()).list();
		if(list !=null && list.size() >0 ){
			System.out.println("查询到不为空");
			 loginUser= list.get(0);
			
		}else if(loginUser==null ){
			throw new CustomException("用户名密码错误");
			}
		tx.commit();
		session.close();
		System.out.println(" login DAO invoked!");
		return loginUser;
	 		
	}
	
	//ajax校验用户名是否为空
	@SuppressWarnings("unchecked")
	@Override
	public User ajaxQueryByName(String username) {
		String hql="from User where username =:username";
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		List<User> list=(List<User>) session.createQuery(hql).setString("username", username).list();		
		tx.commit();
		session.close();
		System.out.println("执行了ajax查询");
		
		if(list !=null && list.size() >0 ){
			System.out.println("查询到不为空");
			return list.get(0);
		}
		
		return null;
	}
	
	//管理员由角色查询用户
	@SuppressWarnings("unchecked")
	@Override
	public List<User> getUser(String role) {
		String hql="from User where role = :role";
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		List<User> list= session.createQuery(hql).setString("role", role).list();

		tx.commit();
		session.close();
		System.out.println("执行了queryUser查询");
		return list;
	}

	@Override
	public List<User> getAllUser() {
		String hql="from User";
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		List<User> list= session.createQuery(hql).list();
		tx.commit();
		session.close();
		System.out.println("执行了queryUser查询");
		return list;
	}

	@Override
	public void addGonggao(Gonggao gg, int id) {
		User u=new User();
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();

		u.setId(id);
		gg.setUser(u);
		gg.setPostedTime( timeFormat());
		session.save(gg);
		tx.commit();
		session.close();
		System.out.println("执行了addGonggao");
		
	}

	@Override
	public List<Gonggao> getGonggaoByUserId(int id) {
		String hql="from Gonggao g where g.user=:id";
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		List<Gonggao> list= session.createQuery(hql).setInteger("id",id).list();
		tx.commit();
		session.close();
		System.out.println("执行了getGonggaoByUserId查询");
		return list;

	}
}
