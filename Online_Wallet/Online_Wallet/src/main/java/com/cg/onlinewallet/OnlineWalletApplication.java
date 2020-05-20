package com.cg.onlinewallet;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.cg.onlinewallet.entities.WalletAccount;
import com.cg.onlinewallet.entities.WalletAccount.status;
import com.cg.onlinewallet.entities.WalletUser;
import com.cg.onlinewallet.entities.WalletUser.type;

@Transactional
@SpringBootApplication
public class OnlineWalletApplication implements CommandLineRunner {

	@Autowired
	EntityManager em;
	
	
	public static void main(String[] args) {
		SpringApplication.run(OnlineWalletApplication.class, args);
	}

	
	@Override
	public void run(String... args) throws Exception{
		
		
		WalletAccount wa1 = new WalletAccount(0.00,  status.active);
		WalletAccount wa3 = new WalletAccount(0.00,  status.active);
		WalletAccount wa2 = new WalletAccount(0.00,  status.non_active);
		em.persist(wa1);
		em.persist(wa2);
		em.persist(wa3);
		WalletUser user1=new WalletUser("Vinay", "123", "876543", "email@abc.com",type.user,  wa1);
		WalletUser user2=new WalletUser("kumar", "123", "876543", "email@abc.com",type.user,  wa2);
		WalletUser user3=new WalletUser("singh", "1234", "8765432", "email@abcd.com",type.user,  wa3);
		em.persist(user1);
		em.persist(user2);
		em.persist(user3);
		
	} 
	
	
}
