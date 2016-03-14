package com.lesk.test1.account;

import com.lesk.test1.id.Simple_Id;

public class Account_Id extends Simple_Id {

	public Account_Id() {
		// TODO Auto-generated constructor stub
	}

	public Account_Id(int range) {
		super(range);
		// TODO Auto-generated constructor stub
	}

	public Account_Id(String id) {
		if (Integer.parseInt(id)>=1000000){
			System.out.println("ИД не может быть больше 1000000!!!");
		}else{
			super.setId(id);
		}
		// TODO Auto-generated constructor stub
	}

	
	
}
