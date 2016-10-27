package com.airwxtx.client.dao.impl;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate4.HibernateCallback;
import org.springframework.stereotype.Repository;

import com.airwxtx.client.dao.ClientDao;
import com.airwxtx.client.entity.Client;
import com.airwxtx.utils.BaseDaoSupport;

//@Repository用于标注数据访问组件，即DAO组件
@Repository
public class ClientDaoImpl extends BaseDaoSupport implements ClientDao {

	@SuppressWarnings("unchecked")
	@Override
	public List<Client> searchClientByClientname(String clientname) {
		// TODO Auto-generated method stub
		String hql = "FROM Client c WHERE c.clientName = :clientname";
		return (List<Client>) this.getHibernateTemplate().findByNamedParam(hql, "clientname", clientname);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Client> searchClientByCompany(String company) {
		// TODO Auto-generated method stub
		String hql = "FROM Client c WHERE c.company = :company";
		return (List<Client>) this.getHibernateTemplate().findByNamedParam(hql, "company", company);
	}

	@Override
	public List<Client> listAllClients(int page,int pageSize) {
		// TODO Auto-generated method stub
		return this.getHibernateTemplate().execute(new HibernateCallback<List<Client>>() {
			@SuppressWarnings("unchecked")
			public List<Client> doInHibernate(Session session) throws HibernateException{
				Query query = session.createQuery("FROM Client");
				query.setFirstResult((page - 1) * pageSize);
				query.setMaxResults(pageSize);
				return (List<Client>) query.list();
			}
		});
	}

	@Override
	public void updateClient(Client client) {
		// TODO Auto-generated method stub
		this.getHibernateTemplate().update(client);
	}

	@Override
	public Integer saveClient(Client client) {
		// TODO Auto-generated method stub
		return (Integer) this.getHibernateTemplate().save(client);
	}

	@Override
	public void deleteClient(Client client) {
		// TODO Auto-generated method stub
		this.getHibernateTemplate().delete(client);
	}

	@Override
	public Client getClient(Integer clientId) {
		// TODO Auto-generated method stub
		return this.getHibernateTemplate().get(Client.class, clientId);
	}

}
