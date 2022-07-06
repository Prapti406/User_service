package com.example.demo;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.model.Address;
import com.example.demo.repository.AddressRepository;

@SpringBootTest
@TestMethodOrder(OrderAnnotation.class)
public class AddressJunit {
    
    @Autowired
     AddressRepository addrepo;
    
    @Test
    @Order(1)
    public void putAddress() {
        Address add= new Address();
        add.setAid(106L);
        add.setStreet("rvs");
        add.setCity("hyd");
        add.setState("tp");
        add.setPincode(59991);
        addrepo.save(add);
        assertNotNull(addrepo.findById(106L).get());
    }
    
    @Test
    @Order(2)
    public void getAddress() {
        List<Address> add=  addrepo.findAll();
        assertThat(add).size().isGreaterThan(0);
        
    }
    
    @Test
    @Order(3)
    public void getAddById() {
        Address add= addrepo.findById(108L).get();
        assertEquals("lajpat road", add.getStreet());
        
    }
    @Test
    @Order(4)
    public void updateAddress() {
        Address add= addrepo.findById(106L).get();
        add.setState("Ap");
        addrepo.save(add);
    assertNotEquals("tp", addrepo.findById(106L).get().getState());
    }
    
    @Test
    @Order(5)
    public void deleteAddress() {
        addrepo.deleteById(106L);
        assertThat(addrepo.existsById(106L)).isFalse();    
    }

}
