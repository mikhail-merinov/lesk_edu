package com.lesk.test1.meterRead;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import com.lesk.test1.characteristics.CharsException;


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
	
	
	// конструктор без параметров
	public MeterRead(){
		this.id=new MeterRead_Id();
		this.mrCharList = new ArrayList<MeterReadChar>();
	}
	
	// переопределяем метод toString, который возвращает описание экземпляра
	// генерим автоматически generate toString()
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
	
	// переопределяем метод equals для сверки показаний по дате.		
	// т.е. если показания на одну дату, и с одинаковым источником, они считаюся одинаковыми.
	// генерится автоматически generate hashCode() and Equals()
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
	
	//---------------------------------------------------------------- РАБОТА С ХАРАКТЕРИСТИКАМИ ППУ
	
	//добавить характеристику в список
	public void addMrChar(MeterReadChar mrc) throws CharsException{
		if(mrCharList.contains(mrc)){
			//System.out.println("Характеристика уже существует!");
			//return;
			throw new CharsException(mrc);
		} else if(!this.id.equals(mrc.getMeterReadId())){
			System.out.println("ИД ППУ не соответствует ИД ППУ характеристики!");
		} else{
			mrCharList.add(mrc);
			System.out.printf("Добавлена характеристика ППУ: ИД ППУ - %s, Дата - %s, тип - %s, значение %s \n", mrc.getMeterReadId().getId(), mrc.getCharDate(), mrc.getCharType(), mrc.getCharVal());
		}
	}
	
	//добавить характеристику по полям
	public void addMrChar(MeterRead_Id mrId, Date mrDt, String charType, String charVal){
		MeterReadChar mrc = new MeterReadChar(mrId,mrDt,charType,charVal);
		//MeterReadChar mrc = new MeterReadChar(mrId);
		//mrc.setCharDate(mrDt);
		//mrc.setCharType(charType);
		//mrc.setCharVal(charVal);
		try {
		addMrChar(mrc);
		} catch (CharsException e) {
			e.printStackTrace();
		}
	}

	// удаление характеристики
	public void removeMrChar(MeterReadChar mrc){
		mrCharList.remove(mrc);
	}
	
	// возврат характеристики определенного типа на дату
	public MeterReadChar retMrChar(Date mrDt, String charType){
		for(MeterReadChar mrc:mrCharList){
			if(mrc.getCharDate().equals(mrDt) && mrc.getCharType().equals(charType)){
				return mrc;
			}
		}
		return null;
	}
	
	// печать полного списка характеристик без сортировки
	public void printMrChar(){
		System.out.println("---------------------Вывод полного списка характеристик ППУ без сортировки----------");
		for(MeterReadChar mrc:mrCharList){
			System.out.println(mrc);
		}
		System.out.println("------------------------------------------------------------------------------------");
	}
	
	// печать отсортированного списка характеристик указанного типа
	public void printSortMrChar(String mrCharType){
		System.out.println("---------------------Вывод полного списка характеристик ППУ сортировка по :"+mrCharType);
		Collections.sort(mrCharList, new CustomComparatorChar());
		for(MeterReadChar mrc:mrCharList){
			if(mrc.getCharType().equals(mrCharType)){
				System.out.println(mrc);
			}
		}
		System.out.println("---------------------------------------------------------------------------------------");
	}
	
	// сортировка характеристик
	public class CustomComparatorChar implements Comparator<MeterReadChar>{

		@Override
		public int compare(MeterReadChar o1, MeterReadChar o2) {
			// TODO Auto-generated method stub
			return o1.getCharDate().compareTo(o2.getCharDate());
		}
		
	}
	
	
	
}
