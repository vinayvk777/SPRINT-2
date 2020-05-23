package com.cg.onlinewallet.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cg.onlinewallet.entities.WalletUser;
import com.cg.onlinewallet.service.OnlineWalletService;
@CrossOrigin(origins="*")
@RestController
public class OnlineWalletController {
	
	@Autowired
	public OnlineWalletService onlinewalletService;

	
	public OnlineWalletController() {
		
		// TODO Auto-generated constructor stub
	}
	
	/*********************************************************************************************************************
	* Method:registerUser
	* Description: to register a new user
	* @param user: User's Details
	* @returns Entity:After registering the user it will return the email of the user.
	* Created By-Vinay Kumar Singh
	* 
	*********************************************************************************************************************/

	
	@PostMapping("/register")
	public ResponseEntity<String> regsiterUser(@RequestBody WalletUser user) {
		String loginName=onlinewalletService.registerUser(user);
		return new ResponseEntity<String>(loginName, HttpStatus.OK);

	}
	
	/********************************************************************************************************************
	* Method:login
	* Description: for login into the application
	* @param email: User's email
	* @param password: User's password
	* @returns Entity:After login it will return the userId
	* Created By-Vinay Kumar Singh
	* 
	*********************************************************************************************************************/
	
	@GetMapping("/login")
	public ResponseEntity<Integer> login(String email, String password) {
		Integer userId = onlinewalletService.login(email, password);
		return new ResponseEntity<Integer>(userId, HttpStatus.OK);
	}
}


