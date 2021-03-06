package com.airwxtx.user.dao;

import java.util.List;

import com.airwxtx.user.entity.User;

public interface UserDao {

	/**
	 * 根据用户名查找用户
	 * 
	 * @param username
	 * @return
	 */
	public List<User> findUserByName(String username);

	/**
	 * 创建用户
	 * 
	 * @param user
	 */
	public void saveOrUpdateUser(User user);

	/**
	 * 按用户名或角色分页查询用户
	 * 
	 * @param username
	 * @param role
	 * @param page
	 * @param pageSize
	 * @return
	 */
	public List<User> findUserByNameOrRoleWithPage(String username, String role, int page, int pageSize);

	/**
	 * 统计用户数量
	 * 
	 * @return
	 */
	public int countUserWithNameOrRole(String username, String role);

	/**
	 * 刷新冻结权限
	 */
	public void refreshFreeze();

	/**
	 * 重置对应用户的密码
	 * 
	 * @param username
	 */
	public void updatePasswordWithUsername(String username, String password);

	/**
	 * 查找用户当前冻结次数
	 * 
	 * @param username
	 * @return
	 */
	public int findUserFreezeCount(String username);

	/**
	 * 用户冻结次数加一
	 * 
	 * @param username
	 */
	public void userFreezeCountInc(String username);

}
