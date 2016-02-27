package com.lesk.test1.runner;
//TESTTESTTEST
import java.sql.Date;

import com.lesk.test1.meter.Meter;
import com.lesk.test1.meter.MeterChar;
import com.lesk.test1.meterRead.MeterRead;
import com.lesk.test1.meterRead.MeterReadChar;
import com.lesk.test1.meterRead.Source;

public class RunnerDZ {

	public static void main(String[] args) {
		
		Date d=Date.valueOf("2016-03-01");
		Date d1=Date.valueOf("2016-02-29");
		
		System.out.println("____________________________________________________________________________ ДЗ Михаила____________________");
		// новый прибор
		Meter mtr = new Meter("12345");
		mtr.On(d);
		Meter mtr1 = new Meter("8888");
		mtr1.On(d1);
		
		
		//----------------------------------------------------------   ХАРАКТЕРИСТИКИ ПУ ----------------------------------
		// новые характеристики
		MeterChar mtrChar = new MeterChar(mtr.getId());
		mtrChar.setCharDate(d);
		mtrChar.setCharType("PRD-POV");
		mtrChar.setCharVal("555");
				
		MeterChar mtrChar1 = new MeterChar(mtr.getId());
		mtrChar1.setCharDate(d1);
		mtrChar1.setCharType("PRD-POV");
		mtrChar1.setCharVal("555777");
		
		MeterChar mtrChar2 = new MeterChar(mtr.getId());
		mtrChar2.setCharDate(d1);
		mtrChar2.setCharType("D-VYP-PU");
		mtrChar2.setCharVal("01.01.2003");
		
		// добавление характеристик
		mtr.addMtrChar(mtrChar);
		mtr.addMtrChar(mtrChar1);
		mtr.addMtrChar(mtrChar2);
		mtr1.addMtrChar(mtrChar);
		
		// печать характеристик
		mtr.printMtrChar();
		mtr.printSortMtrChar("PRD-POV");
		
		//поиск характеристики определенного типа на дату
		System.out.println(mtr.retMtrChat(d1, "D-VYP-PU"));
		
		//----------------------------------------------------------- ХАРАКТЕРИСТИКИ ПОКАЗАНИЙ------------------------------------
		System.out.println("---------------------------------------ХАРАКТЕРИСТИКИ ПОКАЗАНИй ПУ");
		
		Source src=new Source(1,"KONTR");
		Source src1=new Source(2,"KONTR");
		Source src2=new Source(10,"KONTR");
		
		// создаем показание
		MeterRead mr = new MeterRead();
		mr.setMrDt(d);
		mr.setSource(src);
		mr.setVal(6700);
		System.out.println(mr);
		

		// создаем характеристики ППУ
		MeterReadChar mrChar = new MeterReadChar(mr.getId());
		mrChar.setCharDate(d);
		mrChar.setCharType("ORIG_MR");
		mrChar.setCharVal("1");
		System.out.println(mrChar);
		MeterReadChar mrChar1 = new MeterReadChar(mr.getId());
		mrChar1.setCharDate(d1);
		mrChar1.setCharType("ORIG_MR");
		mrChar1.setCharVal("2");
		System.out.println(mrChar1);
		
		MeterReadChar mrChar2 = new MeterReadChar(mr.getId());
		mrChar2.setCharDate(d1);
		mrChar2.setCharType("FALENAME");
		mrChar2.setCharVal("PrSBL020216.dbf");
		System.out.println(mrChar2);
		
		// добавляем характеристику
		mr.addMrChar(mrChar);
		mr.addMrChar(mrChar1);
		mr.addMrChar(mrChar2);
		
		// печать характеристик ППУ
		mr.printMrChar();
		mr.printSortMrChar("ORIG_MR");
		
		
		// вывод отсортированного списка показаний по приоритету в диапазоне дат
		Meter mtr5 = new Meter("0001");
		mtr5.On(Date.valueOf("2010-01-01"));
		mtr5.addMeterRead(Date.valueOf("2016-01-01"), 10, src2);
		mtr5.addMeterRead(Date.valueOf("2015-01-01"), 15, src1);
		mtr5.addMeterRead(Date.valueOf("2016-02-01"), 20, src);
		
		
		mtr5.printMRs();
		mtr5.printMrByDiapSortByPriority(Date.valueOf("2015-12-31"), Date.valueOf("2016-12-31"));
		

	}

}
