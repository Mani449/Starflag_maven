package com.mani.javaprojects.Vo;


/*
 * 	ACCOUNTNO NOT NULL NUMBER        
	NAME               VARCHAR2(50)  
	EMAIL              VARCHAR2(50)  
	ADDRESS            VARCHAR2(100)*/

public class RegisterVO {
private String name,address,email,password,confirmpass;
private int accountno;
private double openBalance;
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public String getAddress() {
	return address;
}
public void setAddress(String address) {
	this.address = address;
}
public String getEmail() {
	return email;
}
public void setEmail(String email) {
	this.email = email;
}
public String getPassword() {
	return password;
}
public void setPassword(String password) {
	this.password = password;
}
public String getConfirmpass() {
	return confirmpass;
}
public void setConfirmpass(String confirmpass) {
	this.confirmpass = confirmpass;
}
public double getOpenBalance() {
	return openBalance;
}
public void setOpenBalance(int openBalance) {
	this.openBalance = openBalance;
}
public int getAccountno() {
	return accountno;
}
public void setAccountno(int accountno) {
	this.accountno = accountno;
}

}
