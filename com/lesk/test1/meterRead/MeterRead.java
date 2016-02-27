package com.lesk.test1.meterRead;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;


public class MeterRead {
	
	private MeterRead_Id id;
	private Date mrDt;
	private Source source;
	private double val;
	private List<MeterReadChar> mrCharList;
	
	public MeterRead_Id getId() {
		return id;
	}
//	public void setId(MeterRead_Id id) {
//		this.id = id;
//	}
	public Date getMrDt() {
		return mrDt;
	}
	public void setMrDt(Date mrDt) {
		this.mrDt = mrDt;
	}
	public Source getSource() {
		return source;
	}
	public void setSource(Source source) {
		this.source = source;
	}
	public double getVal() {
		return val;
	}
	public void setVal(double val) {
		this.val = val;
	}
	
	
	// ����������� ��� ����������
	public MeterRead(){
		this.id=new MeterRead_Id();
		this.mrCharList = new ArrayList<MeterReadChar>();
	}
	
	// �������������� ����� toString, ������� ���������� �������� ����������
	// ������� ������������� generate toString()
	@Override
	public String toString() {
		return "MeterRead [id=" + id + ", mrDt=" + mrDt + ", source=" + source + ", val=" + val + "]";
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((mrDt == null) ? 0 : mrDt.hashCode());
		result = prime * result + ((source == null) ? 0 : source.hashCode());
		return result;
	}
	
	// �������������� ����� equals ��� ������ ��������� �� ����.		
	// �.�. ���� ��������� �� ���� ����, � � ���������� ����������, ��� �������� �����������.
	// ��������� ������������� generate hashCode() and Equals()
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MeterRead other = (MeterRead) obj;
		if (mrDt == null) {
			if (other.mrDt != null)
				return false;
		} else if (!mrDt.equals(other.mrDt))
			return false;
		if (source == null) {
			if (other.source != null)
				return false;
		} else if (!source.equals(other.source))
			return false;
		return true;
	}
	
	//---------------------------------------------------------------- ������ � ���������������� ���
	
	//�������� �������������� � ������
	public void addMrChar(MeterReadChar mrc){
		if(mrCharList.contains(mrc)){
			System.out.println("�������������� ��� ����������!");
			return;
		} else if(!this.id.equals(mrc.getMeterReadId())){
			System.out.println("�� ��� �� ������������� �� ��� ��������������!");
		} else{
			mrCharList.add(mrc);
			System.out.printf("��������� �������������� ���: �� ��� - %s, ���� - %s, ��� - %s, �������� %s \n", mrc.getMeterReadId().getId(), mrc.getCharDate(), mrc.getCharType(), mrc.getCharVal());
		}
	}
	
	//�������� �������������� �� �����
	public void addMrChar(MeterRead_Id mrId, Date mrDt, String charType, String charVal){
		MeterReadChar mrc = new MeterReadChar(mrId);
		mrc.setCharDate(mrDt);
		mrc.setCharType(charType);
		mrc.setCharVal(charVal);
		addMrChar(mrc);
	}

	// �������� ��������������
	public void removeMrChar(MeterReadChar mrc){
		mrCharList.remove(mrc);
	}
	
	// ������� �������������� ������������� ���� �� ����
	public MeterReadChar retMrChar(Date mrDt, String charType){
		for(MeterReadChar mrc:mrCharList){
			if(mrc.getCharDate().equals(mrDt) && mrc.getCharType().equals(charType)){
				return mrc;
			}
		}
		return null;
	}
	
	// ������ ������� ������ ������������� ��� ����������
	public void printMrChar(){
		System.out.println("---------------------����� ������� ������ ������������� ��� ��� ����������----------");
		for(MeterReadChar mrc:mrCharList){
			System.out.println(mrc);
		}
		System.out.println("------------------------------------------------------------------------------------");
	}
	
	// ������ ���������������� ������ ������������� ���������� ����
	public void printSortMrChar(String mrCharType){
		System.out.println("---------------------����� ������� ������ ������������� ��� ���������� �� :"+mrCharType);
		Collections.sort(mrCharList, new CustomComparatorChar());
		for(MeterReadChar mrc:mrCharList){
			if(mrc.getCharType().equals(mrCharType)){
				System.out.println(mrc);
			}
		}
		System.out.println("---------------------------------------------------------------------------------------");
	}
	
	// ���������� �������������
	public class CustomComparatorChar implements Comparator<MeterReadChar>{

		@Override
		public int compare(MeterReadChar o1, MeterReadChar o2) {
			// TODO Auto-generated method stub
			return o1.getCharDate().compareTo(o2.getCharDate());
		}
		
	}
	
	
	
}
