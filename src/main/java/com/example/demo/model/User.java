package com.example.demo.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@Data
@Entity
@Table(name="user_table")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class User {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long uid;
	@NotEmpty
	@Size(max=10,message="Username not less than 2")
	@Column(name="first_Name")
	private String first_Name;
	@NotEmpty
	@Size(max=10,message="Username not less than 2")
	@Column(name="last_Name")
	private String last_Name;
	@Column(name="user_Name")
	private String user_Name;
	@Email(message="Please enter a valid email!!!!")
	@Column(name="Email")
	private String email;
	
	@Column(name="Password")
	private String password;
	@Column(name="phoneno")
	private int phoneno;
	@Column(name="gender")
	private String gender;
	
	@OneToMany(targetEntity = Address.class  ,cascade = CascadeType.ALL)
	@JoinColumn(name = "usadd_fk", referencedColumnName = "uid")
	private List<Address> addresses;
  }