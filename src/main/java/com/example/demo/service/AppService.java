package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.UnaryOperator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.demo.exception.RecordNotFoundException;
import com.example.demo.model.Address;
import com.example.demo.model.Login;
import com.example.demo.model.User;
import com.example.demo.repository.AddressRepository;
import com.example.demo.repository.UserRepository;


@Service
public class AppService implements AppServiceInt{
	@Autowired
	private UserRepository userrepo;
	@Autowired
	private AddressRepository addrepo;
	
	List<User> lst=new ArrayList<User>();
	public boolean loginValid(Login login) {
		for(User r:lst) {
			System.out.println(r);
		if(login.getUser_Name().equals(r.getUserName()) && login.getPassword().equals(r.getPassword())) {
			return true;
		}
		}
		return false;
	}
	public void addUser(User registration) {
		lst.add(registration);
		//System.out.println(registration.getPassword());
		System.out.println(lst);
	}
	public User updateUser(User user,int uid) throws RecordNotFoundException{

		
			User users = userrepo.findById(uid)
					.orElseThrow(() -> new RecordNotFoundException("User not found with id:"+uid)); 
		    if(users.getUid()!=0) {
		      users.setFirst_Name(user.getFirst_Name());
		      users.setLast_Name(user.getLast_Name());
		      users.setEmail(user.getEmail());
		      users.setPassword(user.getPassword());
		      users.setPhoneno(user.getPhoneno());
		      users.setGender(user.getGender());
		      users.setAddresses(user.getAddresses());
		    }
		    else
		    {
		      throw new RecordNotFoundException("Not found");
		    }
		    userrepo.save(users);
		    return users;
		}
		
		
	
	
	public Address updateuseraddress(Address address,long aid)throws RecordNotFoundException{
		Address add=addrepo.findById(aid).get();
		if(add.getAid()!=0) {
			add.setCity(address.getCity());
			add.setState(address.getState());
			add.setPincode(address.getPincode());
			add.setStreet(address.getStreet());
			
		}
		else {
				throw new RecordNotFoundException("Not found");
			 }
			    addrepo.save(add);
			    return add;
	}
	@Override
	public User findUserById(int id) throws RecordNotFoundException {
		// TODO Auto-generated method stub
		 Optional<User> users = userrepo.findById(id);
		  
	  
	    if(users.isPresent()) {
	    	return users.get();
	    }
	    else {
	    	throw new RecordNotFoundException("Not found");
	   }
		
	}
}
