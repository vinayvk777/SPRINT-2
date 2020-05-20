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
	
	
	@RequestMapping("/")
	public String check() {
		return "WORKING";
	}
	
	@PostMapping("/register")
	public ResponseEntity<String> regsiterUser(@RequestBody WalletUser user) {
		String loginName=onlinewalletService.registerUser(user);
		return new ResponseEntity<String>(loginName, HttpStatus.OK);

	}
	
	@GetMapping("/login")
	public ResponseEntity<Integer> login(String email, String password) {
		Integer userId = onlinewalletService.login(email, password);
		return new ResponseEntity<Integer>(userId, HttpStatus.OK);
	}
	
	

}
