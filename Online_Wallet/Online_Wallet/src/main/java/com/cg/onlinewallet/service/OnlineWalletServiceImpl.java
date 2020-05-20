package com.cg.onlinewallet.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.onlinewallet.dao.OnlineWalletDao;
import com.cg.onlinewallet.entities.WalletAccount;
import com.cg.onlinewallet.entities.WalletUser;
import com.cg.onlinewallet.entities.WalletAccount.status;
import com.cg.onlinewallet.entities.WalletUser.type;
import com.cg.onlinewallet.exceptions.InvalidException;
import com.cg.onlinewallet.exceptions.UnauthorizedAccessException;
import com.cg.onlinewallet.exceptions.ValidationException;

@Service
public class OnlineWalletServiceImpl implements OnlineWalletService {

	
	public OnlineWalletServiceImpl() {
		// TODO Auto-generated constructor stub
	}
	
	@Autowired
	private OnlineWalletDao onlineWalletSprint2Dao;


	@Override
	public Integer login(String email, String password) {
		if(!onlineWalletSprint2Dao.checkUserByEmail(email))
			throw new UnauthorizedAccessException("No User exist for this email address. Kindly Register");
		WalletUser user = onlineWalletSprint2Dao.getUserByEmail(email);
		WalletAccount account = user.getAccountDetail();
		if (account.getUserStatus() == status.non_active)
			throw new UnauthorizedAccessException("Your Account is not Activated");
		if (user.getUserType() == type.admin)
			throw new InvalidException("You are not authorized to login from here");
		if (user.getPassword().equals(password) == false)
			throw new ValidationException("The LoginName and password Combination does not match");
		return user.getUserID();
	}
	
	@Override
	public String registerUser(WalletUser user) {
		// TODO Auto-generated method stub
		if (onlineWalletSprint2Dao.checkUserByEmail(user.getEmail()) == true)
			throw new UnauthorizedAccessException("A User already exist with same email address");
		WalletAccount account = new WalletAccount(0.00, status.non_active);
		user.setAccountDetail(account);
		onlineWalletSprint2Dao.saveAccount(account);
		onlineWalletSprint2Dao.saveUser(user);
		return user.getEmail();
	}

}
