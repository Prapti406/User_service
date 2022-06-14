package com.example.demo.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.UserDto;
import com.example.demo.exception.RecordNotFoundException;
import com.example.demo.model.User;
import com.example.demo.model.Address;
import com.example.demo.model.Login;
import com.example.demo.repository.AddressRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.AppService;
@RestController
@RequestMapping("/mainapp")
public class UserController {
	@Autowired
	private UserRepository userrepo;
	@Autowired
	private AddressRepository addrepo;
	@Autowired
	private AppService appservice;
	@GetMapping("/loaduser")
	public List <User> sayHello(){
		List<User> lst=userrepo.findAll();
		return lst;
		
	}
	
	@PostMapping("/adduser")
	public ResponseEntity<User> register(@Valid @RequestBody UserDto reg) {
		userrepo.save(reg.getUser());
		appservice.addUser(reg.getUser());
		return new ResponseEntity<User>(reg.getUser(),HttpStatus.CREATED);
		
	}
	
	@GetMapping("/finduser/{uid}")
	public User findUser(@PathVariable("uid")long uid) {
		return userrepo.findById(uid)
				.orElseThrow(() -> new RecordNotFoundException("User not found with id:"+uid)); 
		
	}
	
	@DeleteMapping("/deleteuser/{uid}")
	public String deleteUser(@PathVariable("uid")long uid) throws RecordNotFoundException {
		Optional opt=userrepo.findById(uid);
		if(opt.isPresent()) {
			userrepo.deleteById(uid);
			return "User deleted";
		}
		return "User Not deleted";
	}
	
	//Address APIS
	@PostMapping("/addaddress")
	public String register(@RequestBody Address reg) {
		addrepo.save(reg);
		return "User Address Added";
	}
	@DeleteMapping("/deleteuseraddress/{aid}")
	public String deleteUserAddress(@PathVariable("aid")long aid) throws RecordNotFoundException {
		Optional opt=addrepo.findById(aid);
		if(opt.isPresent()) {
			addrepo.deleteById(aid);
			return "User Address deleted";
		}
		return "User Address Not deleted";
	}
	@PutMapping("/updateuseraddress/{aid}")
	public ResponseEntity<Address> updateUserAddress(@RequestBody Address reg, @PathVariable("aid") long aid) throws RecordNotFoundException{
		Optional opt=addrepo.findById(aid);
//		if(opt.isPresent()) {
//			addrepo.deleteById(aid);
//			addrepo.save(reg);
//			return "Users Address Updated";
//		}
//		return "Users Address Not Updated";
		return new ResponseEntity<Address>(appservice.updateuseraddress(reg, aid),HttpStatus.OK);
	}
	
	@PutMapping("/updateuser/{uid}")
	public ResponseEntity<User> updateUser(@PathVariable("uid")int uid,@RequestBody User user) throws RecordNotFoundException{
		  return new ResponseEntity<User>(appservice.updateUser(user, uid), HttpStatus.OK);
	}

	@GetMapping("/loadaddress")
	public List <Address> LoadAddresses(){
		List<Address> lstadd=addrepo.findAll();
		return lstadd;
		
	}
	@PostMapping("/login")
	public String Login(@RequestBody Login login) {
		if(appservice.loginValid(login)) {
			return "Login Success";
		}
		return "Login Failure";
	}
}
