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
		if(login.getUser_Name().equals(r.getUser_Name()) && login.getPassword().equals(r.getPassword())) {
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
	public User updateUser(User user,long uid) throws RecordNotFoundException{
//		public  YourObject getYourObject(Long id) {
//		    if (id == null) {
//		        return null;
//		    }
//		    return objectRepository.findById(id).isPresent() ? 
//		        objectRepository.findById(id).get(): null;
//		 }
		User prod = userrepo.findById(uid)
				.orElseThrow(() -> new RecordNotFoundException("User not found with id:"+uid)); 
	    if(prod.getUid()!=0) {
	      prod.setFirst_Name(user.getFirst_Name());
	      prod.setLast_Name(user.getLast_Name());
	      prod.setEmail(user.getEmail());
	      prod.setPassword(user.getPassword());
	      prod.setPhoneno(user.getPhoneno());
	      prod.setGender(user.getGender());
	      prod.setAddresses(user.getAddresses());
	    }
	    else
	    {
	      throw new RecordNotFoundException("Not found");
	    }
	    userrepo.save(prod);
	    return prod;
	}
//	public User findUserById(Long id) throws RecordNotFoundException {
//	//	public  YourObject getYourObject(Long id) {
////		    if (id == null) {
////		        return null;
////		    }
////		    return objectRepository.findById(id).isPresent() ? 
////		        objectRepository.findById(id).get(): null;
////		 }
//		  Optional<User> prod = userrepo.findById(id);
//		  if(id==null) {
//			  return null;
//		  }
//		  else if(prod.isPresent()){
//			  return userrepo.findById(id).get();
//			  
//		  }
//		  else {
//			  return null;
//		  }
//		  
////	    if(prod.isPresent()) {
////	      return prod.get();
////	    }
////	    else {
////	    	throw new RecordNotFoundException("Not found");
////	   }
////	}
////	}
//	}
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
	public User findUserById(long id) throws RecordNotFoundException {
		// TODO Auto-generated method stub
		 Optional<User> prod = userrepo.findById(id);
		  
	  
	    if(prod.isPresent()) {
	    	return prod.get();
	    }
	    else {
	    	throw new RecordNotFoundException("Not found");
	   }
		
	}
}
