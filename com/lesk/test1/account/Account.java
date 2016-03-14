package com.lesk.test1.account;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

public class Account {
	//initial commit before HW3
	private List<AccountChar> acctCharList;
	
	@Override
	public String toString() {
		return "Account [id=" + id + ", name=" + name + "]";
	}

	// свойства класса
	private String name;
	private Account_Id id;
	private Date startDt;
	private Date acctCharDt;
	private String passport;
	
	// методы класса Геттеры, Сеттеры
	// можно генерировать автоматически из меню Source > Generate Getters Setters
	
	public Date getAcctCharDt() {
		return acctCharDt;
	}

	public void setAcctCharDt(Date acctCharDt) {
		this.acctCharDt = acctCharDt;
	}
	
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
	
	// -----------------------------------------	
	// Добавляем ИД аккаунта
	public void setId(Account_Id id) {
		this.id = id;
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
		//System.out.println("Account constructor W/O parameters");
		this.id = new Account_Id();
		this.acctCharList = new ArrayList<AccountChar>();
		
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
	
	
	
	
	
	
	//----------------------- Работаем с xарактеристиками
	
	
	//добавить характеристику в список
		public void addAcctChar(AccountChar acctChar){
			if(acctCharList.contains(acctChar)){
				System.out.println("----------- Добавляется характеристика ---->  " + acctChar + " типа "+ acctChar.getCharType());
				System.out.println("Характеристика уже существует!");
				return;
			}  else{
				acctCharList.add(acctChar);
				System.out.println("----------- Добавляется характеристика ---->  " + acctChar + " типа "+ acctChar.getCharType());
				System.out.printf("Добавлена характеристика ППУ: ИД ППУ - %s, Дата - %s, тип - %s, значение %s \n", acctChar.getAccountId().getId(), acctChar.getCharDate(), acctChar.getCharType(), acctChar.getCharVal());
			}
		}
		
		//добавить характеристику по полям
		public void addAcctChar(Date acctCharDt, String charType, String charVal){
			AccountChar acct = new AccountChar(id, acctCharDt, charType, charVal);
			addAcctChar(acct);
		}

		// удаление характеристики
		public void removeAcctChar(AccountChar acct){
			acctCharList.remove(acct);
		}
		
		
		// возврат характеристики определенного типа на дату
		public AccountChar retAcctChar(Date charDate, String charType){
			for(AccountChar acct:acctCharList){
				if(acct.getCharDate().equals(charDate) && acct.getCharType().equals(charType)){
					return acct;
				}
			}
			return null;
		}
		
		
		// печать полного списка характеристик без сортировки
		public void printAcctChar(){
			System.out.println();
			System.out.println("------Вывод полного списка характеристик аккаунта без сортировки----------");
			for(AccountChar acct:acctCharList){
				System.out.println(acct);
			}
			System.out.println("------------------------------------");
			System.out.println();
		}
		
		
		// печать отсортированного списка характеристик указанного типа
		// печатает в строку - будет инет - поправлю
		public void printSortAcctChar(String acctCharType){
			Collections.sort(acctCharList, new Comparator<AccountChar>() {
				public int compare(AccountChar o1, AccountChar o2) {
						return o1.toString().compareTo(o2.toString());
				}
			});
			System.out.println("---------------------Вывод отсортированного списка характеристик аккаунта ----------");
			System.out.println(acctCharList);	
		}

}
