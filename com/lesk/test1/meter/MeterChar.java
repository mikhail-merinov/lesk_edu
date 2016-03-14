package com.lesk.test1.meter;


import com.lesk.test1.characteristics.Characteristics;

// ������� �����, ����������� �� ��������
public class MeterChar extends Characteristics {

	private Meter_Id meterId;
	
	
	
	// ����������� � ���������� �������
	public MeterChar(Meter_Id meterId){
		this.meterId=meterId;
	}

	// ������� �������
	public Meter_Id getMeterId() {
		return meterId;
	}
//	public void setMeterId(Meter_Id meterId) {
//		this.meterId = meterId;
//	}




	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((meterId == null) ? 0 : meterId.hashCode());
		return result;
	}




	// �������������� ����� ������
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		MeterChar other = (MeterChar) obj;
		if (meterId == null) {
			if (other.meterId != null)
				return false;
		} else if (!meterId.equals(other.meterId))
			return false;
		return true;
	}

	// �������������� ������
	@Override
	public String toString() {
		return "MeterChar [meterId=" + meterId + ", toString()=" + super.toString() + "]";
	}
		
}
