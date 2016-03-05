package com.lesk.test1.meterRead;

public class Source {
	
	private String sourceDescr;
	private int priority;
	
	public String getSourceDescr() {
		return sourceDescr;
	}
	public void setSourceDescr(String sourceDescr) {
		this.sourceDescr = sourceDescr;
	}
	public int getPriority() {
		return priority;
	}
	
	// конструктор
	public Source(int priority, String sourceDescr){
		if(priority>10){
			this.priority=10;
		}else if(priority<1){
			this.priority=1;
		}else{
			this.priority=priority;
		}
		this.sourceDescr=sourceDescr;
	}
	
	@Override
	public String toString() {
		return "Source [priority=" + priority + ", sourceDescr=" + sourceDescr + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + priority;
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
		Source other = (Source) obj;
		if (priority != other.priority)
			return false;
		return true;
	}
	
//	public int compareTo(Source source) {
//		// TODO Auto-generated method stub
//		if(this.priority<source.priority){
//			return 1;
//		} else if(this.priority>source.priority){
//			return -1;
//		} else{
//			return 0;
//		}
//		
//	}
	
	

}
