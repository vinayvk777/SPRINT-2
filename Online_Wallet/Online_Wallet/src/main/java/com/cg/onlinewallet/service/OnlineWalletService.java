package com.cg.onlinewallet.service;

import com.cg.onlinewallet.entities.WalletUser;

public interface OnlineWalletService {
	
	String registerUser(WalletUser user);
	
	Integer login(String loginName, String password);
}
