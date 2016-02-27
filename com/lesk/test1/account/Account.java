package com.lesk.test1.account;
import java.util.Date;

public class Account {

	@Override
	public String toString() {
		return "Account [id=" + id + ", name=" + name + "]";
	}

	// свойства класса
	private String name;
	private Account_Id id;
	private Date startDt;
	private String passport;
	
	// методы класса Геттеры, Сеттеры
	// можно генерировать автоматически из меню Source > Generate Getters Setters
	public void setName(String n){
		this.name=n;
	}
	
	public void setPassport(String p){
		this.passport =p;
		this.id = new Account_Id(p); 
	}
	
	public String getPassport(){
		return this.passport;
	}
	
	public String getName(){
		return this.name;
	}
	
	public Account_Id getId(){
		return this.id;
	}
	
//	public void setStartDt(Date dt){
//		this.startDt=dt;
//	}
//	
	public Date getStartDt(){
		return this.startDt;
	}
	
	public void Activate(Date dt){
		System.out.println("Account activated: "+ dt);
		this.startDt = dt;
	}
	
	public void Activate(){
		this.startDt = new Date();
		System.out.println("Account activated: "+ this.startDt);
	}
	
	// конструктор
	public Account(){
		System.out.println("Account constructor W/O parameters");
		this.id = new Account_Id();
		
	}
	// конструктор с параметрами
	// можно генераровать автоматически, как геттеры и сеттеры.
	public Account(String name){
		System.out.println("Account constructor String parameter");
		this.name=name;
		Account_Id acctId = new Account_Id(name);
		this.id = acctId;
		
	}
	
	// конструктор, при котором номер паспорта будет номером ИД
	public Account(String n, String pass){
		System.out.println("Account constructor for Passport");
		this.name = n;
		this.passport = pass;
		this.id = new Account_Id(pass);
	}
	


}
