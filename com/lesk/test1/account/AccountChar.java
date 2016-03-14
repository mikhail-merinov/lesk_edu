package com.lesk.test1.account;

import java.sql.Date;

import com.lesk.test1.characteristics.Characteristics;

public class AccountChar extends Characteristics{
	
	private  Account_Id AccountId;
	
	//������� � �������
	/**
	 * ����� ��� ��������� �������� �����
	 * @return ���������� �� �� - ����������� ��������
	 */
	public Account_Id getAccountId() {
		return AccountId;
	}
	/**
	 * ������������� �� �������� �����
	 * @param accountId �� �������� ����� - ������� ���������
	 */
	public void setAccountId(Account_Id accountId) {
		AccountId = accountId;
	}
	
	
	
	
	@Deprecated
	// ����������� � ���������� Account ��
	public AccountChar(Account_Id accId){
		this.AccountId=accId;
	}
	
	//������������ �����������
	public AccountChar (Account_Id AccountId, Date acctCharDt, String charType, String charVal){
		this.AccountId= AccountId;
		this.setCharDate(acctCharDt);
		this.setCharType(charType);
		this.setCharVal(charVal);
	}
	
	
	public AccountChar(Account_Id id, java.util.Date acctCharDt, String charType, String charVal) {
		// TODO Auto-generated constructor stub
		this.AccountId= id;
		this.setCharDate(acctCharDt);
		this.setCharType(charType);
		this.setCharVal(charVal);
	}
	
	@Override
	public String toString() {
		return "AccountChar [AccountId=" + AccountId + ", toString()=" + super.toString() + "]";
	}
	
	
	
	//��������������� hash � ������
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((AccountId == null) ? 0 : AccountId.hashCode());
		return result;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		AccountChar other = (AccountChar) obj;
		if (AccountId == null) {
			if (other.AccountId != null)
				return false;
		} else if (!AccountId.equals(other.AccountId))
			return false;
		return true;
	}
	
		
}
