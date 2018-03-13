package cn.jiuye.service;
import java.util.List;

import cn.jiuye.entity.Employee;
import cn.jiuye.entity.Gonggao;
import cn.jiuye.entity.Job;
import cn.jiuye.entity.User;
import cn.jiuye.entity.UserCompanyVO;
import cn.jiuye.entity.UserEmployeeVO;
import cn.jiuye.exception.CustomException;

public interface IUserService {

	public void regist(User user) throws CustomException;

	public User login(User user) throws Exception;

	public User ajaxQueryByName(String username);

	public List<User> getUser(String role);

	public List<User> getAllUser();

	public void addGonggao(Gonggao gg, int id);

	public List<Gonggao> getGonggaoByUserId(int id);

}