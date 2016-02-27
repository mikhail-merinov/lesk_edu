package com.lesk.test1.meter;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import com.lesk.test1.meterRead.MeterRead;
import com.lesk.test1.meterRead.Source;

public class Meter {

	private String badgeNumber; // номер шильдика
	private Meter_Id id;
	private Date receiveDt; // дата установки
	private Date retireDt; // дата снятия
	private int digitsLeft; // разрядность слева
	private int digitsRight;	// разрядность справа
	private double regConst=1; // коэффициент трансформации
	private List<MeterRead> mrList;  /// коллекция (Показания ПУ)
	private List<MeterChar> mtrCharList; // коллекция (список характеристик ПУ)
	
	
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
	
	// конструктор
	public Meter(){
		this.id = new Meter_Id();
		System.out.println("Meter. Конструктор по умолчанию. ID: "+ id.getId());
	}
	
	// контруктор с параметрами
	public Meter(String badgeNumber){
		this.id = new Meter_Id(badgeNumber);
		setBadgeNumber(badgeNumber);
		System.out.println("Meter. Конструктор с параметром String. Создан прибор учета. ID "+id.getId()+", Номер: "+badgeNumber);
	}
	
	// установка ПУ. при установке проверяем если дата установки пустая, то устанавливаем, иначе считаем, что счетчик уже установлен
	// также инициализируем коллекции СписокПоказаний и СписокХарактеристик
	public void On(Date d){
		if(getReceiveDt()==null){
			setReceiveDt(d);
			System.out.println("Meter installed. Install Date= "+d);
			mrList = new ArrayList<MeterRead>();
			mtrCharList = new ArrayList<MeterChar>();
		} else 
		{
			System.out.println("Счетчик уже установлен!");
		}
	}
	
	public void Off(Date d){
		if(	(getRetireDt()==null) && 
			(getReceiveDt()!=null) && 
			(d.compareTo(getReceiveDt())>0)){
			setRetireDt(d);
			System.out.println("Счетчик снят. Дата снятия: "+d);
		}
	}
	
	@Override // аннотация (указание, что это перегружаемый метод)
	public String toString() {
		return "Meter [id=" + id + ", badgeNumber=" + badgeNumber + "]";
	}
	
	//																	РАБОТА С ПОКАЗАНИЯМИ
	// добавить показания по полям дата, значение, источник
	public void addMeterRead(Date dt, double val, Source src){
		MeterRead mr = new MeterRead();
		mr.setMrDt(dt);
		mr.setSource(src);
		mr.setVal(val);
		addMeterRead(mr);
	}
	
	// добавить показания по объекту показания mr
	public void addMeterRead(MeterRead mr){
			if(mrList.contains(mr)){
				System.out.println("Такое показание уже есть.");
				return;
			}
			mrList.add(mr);
		}
	
	// удалить показания
	public void removeMeterRead(MeterRead mr){
				mrList.remove(mr);
	}
	
	// печать всех показаний
	public void printMRs(){
		System.out.println("---------------------------------------------Все показания");
		for (MeterRead mr:mrList){
			System.out.println(mr);
		}
		System.out.println("---------------------------------------------");
	}
	
	// получить показания на дату
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
	
	// получить самое последнее показание
	public MeterRead getLastMeterRead(){
		return Collections.max(mrList, new CustomComparator());
	}
	
	// вывод списка показаний с сортировкой по дате показаний.
	public void printSortMRs(){
		// класс, для которого не нужно создавать экземпляр и у которого много методов.
		Collections.sort(mrList, new CustomComparator());
		System.out.println("---------------------------------------------");
		for (MeterRead mr:mrList){
			System.out.println(mr);
		}
		System.out.println("---------------------------------------------");
	}
	
	// для сортировки создается отдельный класс
	public class CustomComparator implements Comparator<MeterRead>{

		@Override
		public int compare(MeterRead o1, MeterRead o2) {
			// TODO Auto-generated method stub
			return o1.getMrDt().compareTo(o2.getMrDt());
		}
	}
	
	
	public void printMrByDiapSortByPriority(Date mrDt1, Date mrDt2){
		Collections.sort(mrList, new CustomComparatorPriority());
		System.out.println("Отсортированные показания по приоритету в диапазоне: "+ mrDt1+" - "+mrDt2);
		for(MeterRead mr:mrList){
			if(mr.getMrDt().getTime()<=mrDt2.getTime() & mr.getMrDt().getTime()>=mrDt1.getTime()){
				System.out.println(mr);
			}
		}
	}
	
	// для сортировки по приоритету
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
	
	//																			РАБОТА С ХАРАКТЕРИСТИКАМИ
	
	// Добавить характеристику как объект
	public void addMtrChar(MeterChar mtrChar){
		if(mtrCharList.contains(mtrChar)){
			System.out.println("Характеристика существует. Добавление невозможно!");
		} else if(!this.id.equals(mtrChar.getMeterId())){
			System.out.println("ИД ПУ не соответствует ИД ПУ характеристики");
		} else {
			mtrCharList.add(mtrChar);
			System.out.printf("Добавлена характеристика: ИД ПУ - %s, Дата - %s, тип - %s, значение %s \n", mtrChar.getMeterId().getId(), mtrChar.getCharDate(), mtrChar.getCharType(), mtrChar.getCharVal());
		}
	}
	
	// Добавить характеристику по полям
	public void addMtrChar(Meter_Id id, Date charDate, String charType, String charVal){
		MeterChar mc = new MeterChar(id);
		mc.setCharDate(charDate);
		mc.setCharType(charType);
		mc.setCharVal(charVal);
		addMtrChar(mc);
	}
	
	// удалить характеристику
	public void removeMtrChar(MeterChar mc){
		mtrCharList.remove(mc);
	}
	
	// вывод списка характеристик (без сортировки)
	public void printMtrChar(){
		System.out.println("--------------------Полный список характеристик без сортировки");
		for(MeterChar mc:mtrCharList){
			System.out.println(mc);
		}
		System.out.println("--------------------------------------------------------------------------------");
	}
	
	// вывод отсортированного по дате списка характеристик определенного типа
	public void printSortMtrChar(String charType){
		// сортируем лист по дате используя созданный класс кустомКомпараторЧар
		Collections.sort(mtrCharList, new CustomComparatorChar());
		System.out.println("--------------------Отсортированный по дате список характеристик типа: "+charType);
		for(MeterChar mc:mtrCharList){
			if(mc.getCharType().equals(charType)){
				System.out.println(mc);
			}
		}
		System.out.println("--------------------------------------------------------------------------------");
	}
		
	// возврат характеристики указанного типа на указанную дату
	public MeterChar retMtrChat(Date charDate, String charType){
		for(MeterChar mc:mtrCharList ){
			if(mc.getCharDate().equals(charDate) && mc.getCharType().equals(charType)){
				return mc;
			}
		}
		return null;
	}
	
	// для сортировки характеристик по дате
		public class CustomComparatorChar implements Comparator<MeterChar>{

		@Override
		public int compare(MeterChar o1, MeterChar o2) {
			// TODO Auto-generated method stub
			return o1.getCharDate().compareTo(o2.getCharDate());
		}
		}
	
	
}
