package com.lesk.test1.meterRead;

import com.lesk.test1.characteristics.Characteristics;

public class MeterReadChar extends Characteristics {

	private MeterRead_Id meterReadId;

	//геттеры и сеттеры
	public MeterRead_Id getMeterReadId() {
		return meterReadId;
	}

	// конструктор с параметром МР ИД
	public MeterReadChar(MeterRead_Id mrId){
		this.meterReadId=mrId;
	}

		
	// переопределение Стринга
	@Override
	public String toString() {
		return "MeterReadChar [meterReadId=" + meterReadId + ", toString()=" + super.toString() + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((meterReadId == null) ? 0 : meterReadId.hashCode());
		return result;
	}

	// переопределение эквалса
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		MeterReadChar other = (MeterReadChar) obj;
		if (meterReadId == null) {
			if (other.meterReadId != null)
				return false;
		} else if (!meterReadId.equals(other.meterReadId))
			return false;
		return true;
	}
			
}
