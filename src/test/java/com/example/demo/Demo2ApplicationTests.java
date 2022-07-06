package com.example.demo;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;


@SpringBootTest
@TestMethodOrder(OrderAnnotation.class)
class Demo2ApplicationTests {
    
    @Autowired
    UserRepository urepo;
    
    @Test
    @Order(1)
    public void putData() {
        User us= new User();
        us.setUid(3);
        us.setFirst_Name("abc");
        us.setLast_Name("def");
        us.setUserName("ad@mail.com");
        us.setEmail("pp@mail.com");
        us.setPassword("ruchi");
        us.setGender("F");
        us.setPhoneno(12345);
        urepo.save(us);
        assertNotNull(urepo.findById(3).get());
    }
    @Test
    @Order(2)
    public void getData() {
        List<User> list=  urepo.findAll();
        assertThat(list).size().isGreaterThan(0);
        
    }
    @Test
    @Order(3)
    public void getDataById() {
        User us= urepo.findById(3).get();
        assertEquals("ruchi", us.getPassword());
    }
    @Test
    @Order(4)
    public void updateData() {
        User user= urepo.findById(3).get();
        user.setEmail("pa@mail.com");
        urepo.save(user);
    assertNotEquals("Sahibganj", urepo.findById(3).get().getEmail());
    }
    @Test
    @Order(5)
    public void deleteData() {
        urepo.deleteById(3);
        assertThat(urepo.existsById(3)).isFalse();
        
    }
}