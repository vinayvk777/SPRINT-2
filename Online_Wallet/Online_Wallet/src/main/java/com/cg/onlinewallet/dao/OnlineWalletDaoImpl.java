package com.cg.onlinewallet.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import com.cg.onlinewallet.entities.WalletAccount;
import com.cg.onlinewallet.entities.WalletUser;

@Repository
public class OnlineWalletDaoImpl implements OnlineWalletDao {
	
	@PersistenceContext
    private EntityManager entityManager;

	public OnlineWalletDaoImpl() {
		// TODO Auto-generated constructor stub
	}
	
		
	@Override
	public void saveUser(WalletUser user) {
		// TODO Auto-generated method stub
		entityManager.persist(user);
		}
	
	@Override
	public void saveAccount(WalletAccount account) {
		entityManager.persist(account);
	}
	
	@Override
	public WalletAccount getAccount(Integer accountId) {
		WalletAccount account = entityManager.find(WalletAccount.class, accountId);
		return account;
	}
	
	@Override
	public WalletUser getUser(Integer userId) {
		WalletUser user = entityManager.find(WalletUser.class, userId);
		return user;
	}
	
	/*********************************************************************************************************************
	* Method: checkUserByEmail
	* Description: To check that whether a user is present with given email or not
	* @param email:User's email
	* @returns Boolean: true if the user is present with entered email, otherwise false
	* Created By - Vinay Kumar Singh
	***********************************************************************************************************************/
	
	
	@Override
	public boolean checkUserByEmail(String email) {
		String Qstr = "SELECT user.email FROM WalletUser user WHERE user.email= :email";
		TypedQuery<String> query = entityManager.createQuery(Qstr, String.class).setParameter("email", email);
		try {
			query.getSingleResult();
		} catch (Exception ex) {
			return false;
		}
		return true;
	}
	

	/*********************************************************************************************************************
	* Method: getUserByEmail
	* Description: To access the user with the given email
	* @param email:User's email
	* @returns user: It will return the user present with entered email
	* Created By - Vinay Kumar Singh
	***********************************************************************************************************************/
	
	
	@Override
	public WalletUser getUserByEmail(String email) {
		String Qstr = "SELECT user FROM WalletUser user WHERE user.email= :email";
		TypedQuery<WalletUser> query = entityManager.createQuery(Qstr, WalletUser.class).setParameter("email",
				email);
		return query.getSingleResult();
	}
}
