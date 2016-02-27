package com.lesk.test1.runner;

//import java.util.Date;
import java.sql.Date;

import com.lesk.test1.account.Account;
import com.lesk.test1.meter.Meter;
import com.lesk.test1.meter.MeterChar;
import com.lesk.test1.meterRead.MeterRead;
import com.lesk.test1.meterRead.Source;

public class Runner {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		String str = "Hello world";
//		System.out.println(str);
//		Account a = new Account();
//		System.out.println(a.getId());
//		a.setName("Sergio");
//		//a.setId("010803040506");
//		System.out.println("id: "+a.getId()+" name: "+a.getName());
//		
//		Account b = new Account("Misha","555");
//		// два раза пишем getId, т.к тип свойства для класса аккаунт является классом Account_Id
//		System.out.println("id: "+b.getId().getId()+" name: "+b.getName());
//		Date dat1 = Date.valueOf("2015-12-31");
//		b.Activate(dat1);
//		
		Account a = new Account();
		System.out.println("account "+ a);
		
		Date d=Date.valueOf("2016-01-01");
		Date d1=Date.valueOf("2016-02-29");
		
		Meter m = new Meter();
		m.setBadgeNumber("1234567890");
		System.out.println("meter - " +m);
		m.On(d);
		
		Source src=new Source(1,"KONTR");
		Source src1=new Source(1,"LKK");
		m.addMeterRead(Date.valueOf("2016-01-01"), 555, src);
		m.addMeterRead(Date.valueOf("2016-01-31"), 600, src1);
		m.addMeterRead(Date.valueOf("2016-02-29"), 650, src);
		
			
		System.out.println("Список показаний");
		m.printMRs();
		
		// получаем показания на дату
		MeterRead mr = m.getMeterReadByDate(d1);
		System.out.println("Показание на дату: "+d1);
		System.out.println(mr);
		
		// удаляем показание
		m.removeMeterRead(mr);
		
		System.out.println("Список показаний после удаления");
		m.printMRs();
		//Meter m = new Meter("12345567");
		//m.On(d);
		//m.Off(d1);
		
		MeterRead mr1,mr2;
		mr1=new MeterRead(); mr2=new MeterRead();
		mr1.setMrDt(Date.valueOf("2015-11-30"));
		mr1.setVal(100);
		mr1.setSource(src1);
		
		mr2.setMrDt(Date.valueOf("2015-11-30"));
		mr2.setVal(200);
		mr2.setSource(src);
		
		System.out.println("mr1 "+mr1);
		System.out.println("mr2 "+mr2);
		
		if (mr1.equals(mr2)){
			System.out.println("Показания одинаковые");
		} else {
			System.out.println("Показания разные");
		}
		
		// пробуем добавить два показания на одну дату. Проверка метода contains в методе добавления показаний класса Meter.
		m.addMeterRead(Date.valueOf("2016-03-01"), 123, src);
		m.addMeterRead(Date.valueOf("2016-01-05"), 124, src1);
		m.printMRs();
		m.printSortMRs();
		
		System.out.println(m.getLastMeterRead());
		

		
	}
	
	//TODO: Проверить, что нельзя добавить ППУ на неподключенные счетчик.
	//TODO: Проверить, что нельзя вносить показания датой раньше чем дата включения.

}
