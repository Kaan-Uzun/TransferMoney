package com.transfermoney.project.TransferMoney.DataAccess;

import java.util.List;
import javax.persistence.EntityManager;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.transfermoney.project.TransferMoney.Entities.Account;

//JPA

@Repository
public class HibernateAccountDal implements IAccountDal {
	
	
	//Create a new EntityManager for hibernate processes
	private EntityManager entityManager;

	@Autowired
	public HibernateAccountDal(EntityManager entityManager) {
		this.entityManager = entityManager;
	}
	
	@Override
	@Transactional
	public List<Account> getAll() {
		Session session = entityManager.unwrap(Session.class);
		List<Account> accounts = session.createQuery("from Account", Account.class).getResultList();
		return accounts;
	}
		
	@Override
	@Transactional
	public void add(Account account) {
		Session session = entityManager.unwrap(Session.class);
		session.saveOrUpdate(account);
	}

	@Override
	@Transactional
	public void update(Account account) {
		Session session = entityManager.unwrap(Session.class);
		session.saveOrUpdate(account);	
	}

	@Override
	@Transactional
	public void delete(Account account) {
		Session session = entityManager.unwrap(Session.class);
		Account accountToDelete = session.get(Account.class, account.getId());
		session.delete(accountToDelete);
	}

	@Override
	@Transactional
	public Account getById(int accountId) {
		Session session = entityManager.unwrap(Session.class);
		return session.get(Account.class, accountId);
	}
}
