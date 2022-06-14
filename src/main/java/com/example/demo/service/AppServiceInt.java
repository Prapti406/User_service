/**
 * 
 */
package com.example.demo.service;

import java.util.List;

import com.example.demo.exception.RecordNotFoundException;
import com.example.demo.model.User;


/**
 * @author ppandey077
 *
 */
public interface AppServiceInt{
	//public List<Product> getAllProducts();
	  
	  public User findUserById(long id)throws RecordNotFoundException;
	  
	  //public Product updateProduct(Product p,long id);
	  
	  //public void deleteProduct(long id);
}
