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

	/*********************************************************************************************************************
	 * Method: registerUser
	 * Description: Register and create an account for the user
	 * @param user: object containing data about user
	 * @throws UnauthorizedAccessException: it is raised if the user is already present
	 * Created By - Vinay Kumar Singh
	 * 
	 ***********************************************************************************************************************/
	
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
	
	/*********************************************************************************************************************
	 * Method: login 
	 * Description: To Validate the user data
	 * @param email: User's email
	 * @param password: User's password
	 * @returns Integer: gives userId with the loginName if no exceptions occurs
	 * @throws UnauthorizedAccessException: it occurs if the account with loginName is not an active user
	 * @throws InvalidException: it occurs if the account with loginName is a admin type account
	 * @throws ValidationException: it occurs if the password dosen't matches with the user's stored password
	 * Created By - Vinay Kumar Singh
	 * 
	 ***********************************************************************************************************************/

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
}
