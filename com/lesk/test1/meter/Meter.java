package com.lesk.test1.meter;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import com.lesk.test1.meterRead.MeterRead;
import com.lesk.test1.meterRead.Source;

public class Meter {

	private String badgeNumber; // ����� ��������
	private Meter_Id id;
	private Date receiveDt; // ���� ���������
	private Date retireDt; // ���� ������
	private int digitsLeft; // ����������� �����
	private int digitsRight;	// ����������� ������
	private double regConst=1; // ����������� �������������
	private List<MeterRead> mrList;  /// ��������� (��������� ��)
	private List<MeterChar> mtrCharList; // ��������� (������ ������������� ��)
	
	
	public String getBadgeNumber() {
		return badgeNumber;
	}
	public void setBadgeNumber(String badgeNumber) {
		this.badgeNumber = badgeNumber;
	}
	public Meter_Id getId() {
		return id;
	}
	public void setId(Meter_Id id) {
		this.id = id;
	}
	public Date getReceiveDt() {
		return receiveDt;
	}
	public void setReceiveDt(Date installDt) {
		this.receiveDt = installDt;
	}
	public Date getRetireDt() {
		return retireDt;
	}
	public void setRetireDt(Date removalDt) {
		this.retireDt = removalDt;
	}
	public int getdigitsLeft() {
		return digitsLeft;
	}
	public void setdigitsLeft(int nbrLft) {
		this.digitsLeft = nbrLft;
	}
	public int getDigitsRight() {
		return digitsRight;
	}
	public void setDigitsRight(int nbrRgt) {
		this.digitsRight = nbrRgt;
	}
	public double getRegConst() {
		return regConst;
	}
	public void setRegConst(double regConst) {
		this.regConst = regConst;
	}
	
	// �����������
	public Meter(){
		this.id = new Meter_Id();
		System.out.println("Meter. ����������� �� ���������. ID: "+ id.getId());
	}
	
	// ���������� � �����������
	public Meter(String badgeNumber){
		this.id = new Meter_Id(badgeNumber);
		setBadgeNumber(badgeNumber);
		System.out.println("Meter. ����������� � ���������� String. ������ ������ �����. ID "+id.getId()+", �����: "+badgeNumber);
	}
	
	// ��������� ��. ��� ��������� ��������� ���� ���� ��������� ������, �� �������������, ����� �������, ��� ������� ��� ����������
	// ����� �������������� ��������� ��������������� � �������������������
	public void On(Date d){
		if(getReceiveDt()==null){
			setReceiveDt(d);
			System.out.println("Meter installed. Install Date= "+d);
			mrList = new ArrayList<MeterRead>();
			mtrCharList = new ArrayList<MeterChar>();
		} else 
		{
			System.out.println("������� ��� ����������!");
		}
	}
	
	public void Off(Date d){
		if(	(getRetireDt()==null) && 
			(getReceiveDt()!=null) && 
			(d.compareTo(getReceiveDt())>0)){
			setRetireDt(d);
			System.out.println("������� ����. ���� ������: "+d);
		}
	}
	
	@Override // ��������� (��������, ��� ��� ������������� �����)
	public String toString() {
		return "Meter [id=" + id + ", badgeNumber=" + badgeNumber + "]";
	}
	
	//																	������ � �����������
	// �������� ��������� �� ����� ����, ��������, ��������
	public void addMeterRead(Date dt, double val, Source src){
		MeterRead mr = new MeterRead();
		mr.setMrDt(dt);
		mr.setSource(src);
		mr.setVal(val);
		addMeterRead(mr);
	}
	
	// �������� ��������� �� ������� ��������� mr
	public void addMeterRead(MeterRead mr){
			if(mrList.contains(mr)){
				System.out.println("����� ��������� ��� ����.");
				return;
			}
			mrList.add(mr);
		}
	
	// ������� ���������
	public void removeMeterRead(MeterRead mr){
				mrList.remove(mr);
	}
	
	// ������ ���� ���������
	public void printMRs(){
		System.out.println("---------------------------------------------��� ���������");
		for (MeterRead mr:mrList){
			System.out.println(mr);
		}
		System.out.println("---------------------------------------------");
	}
	
	// �������� ��������� �� ����
	public MeterRead getMeterReadByDate(Date d){
		for (MeterRead mr:mrList){
			if (mr.getMrDt().equals(d)){
				return mr;
			}	
		}
		return null;
	}
	
	public List<MeterRead> getAllMeterReads(){
		return this.mrList;
	}
	
	// �������� ����� ��������� ���������
	public MeterRead getLastMeterRead(){
		return Collections.max(mrList, new CustomComparator());
	}
	
	// ����� ������ ��������� � ����������� �� ���� ���������.
	public void printSortMRs(){
		// �����, ��� �������� �� ����� ��������� ��������� � � �������� ����� �������.
		Collections.sort(mrList, new CustomComparator());
		System.out.println("---------------------------------------------");
		for (MeterRead mr:mrList){
			System.out.println(mr);
		}
		System.out.println("---------------------------------------------");
	}
	
	// ��� ���������� ��������� ��������� �����
	public class CustomComparator implements Comparator<MeterRead>{

		@Override
		public int compare(MeterRead o1, MeterRead o2) {
			// TODO Auto-generated method stub
			return o1.getMrDt().compareTo(o2.getMrDt());
		}
	}
	
	
	public void printMrByDiapSortByPriority(Date mrDt1, Date mrDt2){
		Collections.sort(mrList, new CustomComparatorPriority());
		System.out.println("��������������� ��������� �� ���������� � ���������: "+ mrDt1+" - "+mrDt2);
		for(MeterRead mr:mrList){
			if(mr.getMrDt().getTime()<=mrDt2.getTime() & mr.getMrDt().getTime()>=mrDt1.getTime()){
				System.out.println(mr);
			}
		}
	}
	
	// ��� ���������� �� ����������
	public class CustomComparatorPriority implements Comparator<MeterRead>{

		@Override
		public int compare(MeterRead o1, MeterRead o2) {
			// TODO Auto-generated method stub
			//return o1.getSource().compareTo(o2.getSource());
			if(o1.getSource().getPriority()>o2.getSource().getPriority()){
				return 1;
			} else if(o1.getSource().getPriority()<o2.getSource().getPriority()){
				return -1;
			} else{
				return 0;
			}
			//return 0;
		}
		
	}
	
	//																			������ � ����������������
	
	// �������� �������������� ��� ������
	public void addMtrChar(MeterChar mtrChar){
		if(mtrCharList.contains(mtrChar)){
			System.out.println("�������������� ����������. ���������� ����������!");
		} else if(!this.id.equals(mtrChar.getMeterId())){
			System.out.println("�� �� �� ������������� �� �� ��������������");
		} else {
			mtrCharList.add(mtrChar);
			System.out.printf("��������� ��������������: �� �� - %s, ���� - %s, ��� - %s, �������� %s \n", mtrChar.getMeterId().getId(), mtrChar.getCharDate(), mtrChar.getCharType(), mtrChar.getCharVal());
		}
	}
	
	// �������� �������������� �� �����
	public void addMtrChar(Meter_Id id, Date charDate, String charType, String charVal){
		MeterChar mc = new MeterChar(id);
		mc.setCharDate(charDate);
		mc.setCharType(charType);
		mc.setCharVal(charVal);
		addMtrChar(mc);
	}
	
	// ������� ��������������
	public void removeMtrChar(MeterChar mc){
		mtrCharList.remove(mc);
	}
	
	// ����� ������ ������������� (��� ����������)
	public void printMtrChar(){
		System.out.println("--------------------������ ������ ������������� ��� ����������");
		for(MeterChar mc:mtrCharList){
			System.out.println(mc);
		}
		System.out.println("--------------------------------------------------------------------------------");
	}
	
	// ����� ���������������� �� ���� ������ ������������� ������������� ����
	public void printSortMtrChar(String charType){
		// ��������� ���� �� ���� ��������� ��������� ����� �������������������
		Collections.sort(mtrCharList, new CustomComparatorChar());
		System.out.println("--------------------��������������� �� ���� ������ ������������� ����: "+charType);
		for(MeterChar mc:mtrCharList){
			if(mc.getCharType().equals(charType)){
				System.out.println(mc);
			}
		}
		System.out.println("--------------------------------------------------------------------------------");
	}
		
	// ������� �������������� ���������� ���� �� ��������� ����
	public MeterChar retMtrChat(Date charDate, String charType){
		for(MeterChar mc:mtrCharList ){
			if(mc.getCharDate().equals(charDate) && mc.getCharType().equals(charType)){
				return mc;
			}
		}
		return null;
	}
	
	// ��� ���������� ������������� �� ����
		public class CustomComparatorChar implements Comparator<MeterChar>{

		@Override
		public int compare(MeterChar o1, MeterChar o2) {
			// TODO Auto-generated method stub
			return o1.getCharDate().compareTo(o2.getCharDate());
		}
		}
	
	
}
