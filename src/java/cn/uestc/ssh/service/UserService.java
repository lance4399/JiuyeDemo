package cn.jiuye.service;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.jiuye.exception.CustomException;
import cn.jiuye.dao.IUserDAO;
import cn.jiuye.dao.UserDAO;
import cn.jiuye.entity.Gonggao;
import cn.jiuye.entity.Job;
import cn.jiuye.entity.User;
import cn.jiuye.entity.UserCompanyVO;
import cn.jiuye.entity.UserEmployeeVO;

@Service
public class UserService implements IUserService {
	
	private IUserDAO userDao;	
	
	@Resource
	public final void setUserDao(UserDAO userDao) {
		this.userDao = userDao;
	}

	public final IUserDAO getUserDao() {
		return userDao;
	}

	@Override
	public void regist(User user) throws CustomException {
		userDao.insert(user);
		System.out.println("regist service invoked!");
	}

	@Override
	public User login(User user) throws CustomException {
			
		User loginUser=userDao.login(user);
		System.out.println("login service invoked!");
		
		  if(loginUser==null){
			throw new CustomException("用户名或密码错误!");
		}

	 return loginUser;
		
	}
	//AJAX识别用户名是否存在
	@Override
	public User ajaxQueryByName(String username) {	 
		User checkedUser =userDao.ajaxQueryByName(username);
		return checkedUser;
	}

	//管理员查询用户
	@Override
	public List<User> getUser(String role) {
		return userDao.getUser(role);
	}

	
	@Override
	public List<User> getAllUser() {
		return userDao.getAllUser();
	}

	@Override
	public void addGonggao(Gonggao gg, int id) {
			userDao.addGonggao(gg, id);	
	}

	@Override
	public List<Gonggao> getGonggaoByUserId(int id) {
		
		return userDao.getGonggaoByUserId(id);
	}


}
