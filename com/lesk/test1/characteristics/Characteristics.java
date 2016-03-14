package com.lesk.test1.characteristics;

//import java.sql.Date;
import java.util.Date;

public class Characteristics {
	
	// создаем поля класса.
	private String charType;
	private String charVal;
	private Date charDate;
	
	// геттеры и сеттеры
	public String getCharType() {
		return charType;
	}
	public void setCharType(String charType) {
		this.charType = charType;
	}
	public String getCharVal() {
		return charVal;
	}
	public void setCharVal(String charVal) {
		this.charVal = charVal;
	}
	public Date getCharDate() {
		return charDate;
	}
	public void setCharDate(Date charDate) {
		this.charDate = charDate;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((charDate == null) ? 0 : charDate.hashCode());
		result = prime * result + ((charType == null) ? 0 : charType.hashCode());
		return result;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Characteristics other = (Characteristics) obj;
		if (charDate == null) {
			if (other.charDate != null)
				return false;
		} else if (!charDate.equals(other.charDate))
			return false;
		if (charType == null) {
			if (other.charType != null)
				return false;
		} else if (!charType.equals(other.charType))
			return false;
		return true;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Characteristics [charType=" + charType + ", charVal=" + charVal + ", charDate=" + charDate + "]";
	}
	
		

}
