package com.cg.onlinewallet.dao;
import com.cg.onlinewallet.entities.WalletAccount;
import com.cg.onlinewallet.entities.WalletUser;

public interface OnlineWalletDao {
	
	WalletUser getUserByEmail(String email);

	boolean checkUserByEmail(String email);

	void saveUser(WalletUser user);

	WalletUser getUser(Integer userId);

	WalletAccount getAccount(Integer accountId);
	
	void saveAccount(WalletAccount account);
}
