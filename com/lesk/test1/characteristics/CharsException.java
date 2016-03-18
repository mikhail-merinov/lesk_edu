package com.lesk.test1.characteristics;

public class CharsException extends Exception {

	/**
	 * Add a def serialVersionUID
	 */
	private static final long serialVersionUID = 1L;
	
	

	public CharsException(String message) {
		super(message);
	}

	public CharsException(Characteristics c) {
		super("Характеристика " + c + " уже существует!!! Вывот от try/catch EXCEPTION");
	}
	
	
	
	
//	public CharsException2(String message) {
//		super(message);
//	}
//
//	public CharsException2(Characteristics c) {
//		super("Характеристика " + c + " уже существует!!!");
//	}
	
	
}
