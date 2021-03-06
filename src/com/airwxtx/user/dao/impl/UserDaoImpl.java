package com.airwxtx.user.dao.impl;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.airwxtx.user.dao.UserDao;
import com.airwxtx.user.entity.User;
import com.airwxtx.utils.BaseDaoSupport;

@Repository
public class UserDaoImpl extends BaseDaoSupport implements UserDao {

	@SuppressWarnings("unchecked")
	@Override
	public List<User> findUserByName(String username) {
		// TODO Auto-generated method stub
		String hql = "FROM User u WHERE u.username = :username";
		return (List<User>) this.getHibernateTemplate().findByNamedParam(hql, "username", username);
	}

	@Override
	public void saveOrUpdateUser(User user) {
		// TODO Auto-generated method stub
		this.getHibernateTemplate().saveOrUpdate(user);
	}

	@Override
	public void updatePasswordWithUsername(String username, String password) {
		String hql = "UPDATE User SET password = ? WHERE username = ?";
		this.getHibernateTemplate().bulkUpdate(hql, password, username);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<User> findUserByNameOrRoleWithPage(String username, String role, int page, int pageSize) {
		// TODO Auto-generated method stub
		DetachedCriteria query = createDetachedCriteriaWithUsernameOrRole(username, role);
		return (List<User>) this.getHibernateTemplate().findByCriteria(query, (page - 1) * pageSize, pageSize);
	}

	@SuppressWarnings("unchecked")
	@Override
	public int countUserWithNameOrRole(String username, String role) {
		// TODO Auto-generated method stub
		DetachedCriteria query = createDetachedCriteriaWithUsernameOrRole(username, role)
				.setProjection(Projections.rowCount());
		List<Object> ans = (List<Object>) this.getHibernateTemplate().findByCriteria(query);
		return Integer.parseInt(ans.get(0).toString());
	}

	private DetachedCriteria createDetachedCriteriaWithUsernameOrRole(String username, String role) {
		DetachedCriteria query = DetachedCriteria.forClass(User.class);
		if (username != null && !username.equals("")) {
			query.add(Restrictions.ilike("username", username, MatchMode.ANYWHERE));
		}
		if (role != null && !role.equals("")) {
			query.add(Restrictions.ilike("role", role, MatchMode.EXACT));
		}
		return query;
	}

	@Override
	public void refreshFreeze() {
		// TODO Auto-generated method stub
		String hql = "UPDATE User SET freezeCount = 0";
		this.getHibernateTemplate().bulkUpdate(hql);
	}

	@SuppressWarnings("unchecked")
	@Override
	public int findUserFreezeCount(String username) {
		// TODO Auto-generated method stub
		String hql = "SELECT u.freezeCount FROM User u WHERE u.username = :username";
		List<Object> ans = (List<Object>) this.getHibernateTemplate().findByNamedParam(hql, "username", username);
		return Integer.parseInt(ans.get(0).toString());
	}

	@Override
	public void userFreezeCountInc(String username) {
		// TODO Auto-generated method stub
		String hql = "UPDATE User SET freezeCount = freezeCount + 1 WHERE username = ?";
		this.getHibernateTemplate().bulkUpdate(hql, username);
	}

}
