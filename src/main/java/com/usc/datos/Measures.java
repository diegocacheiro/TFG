package com.usc.datos;

public class Measures {
	private String time;
	private String va;
	private String bm;
	
	public Measures(String time, String va, String bm) {
		super();
		this.time = time;
		this.va = va;
		this.bm = bm;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getVa() {
		return va;
	}
	public void setVa(String va) {
		this.va = va;
	}
	public String getBm() {
		return bm;
	}
	public void setBm(String bm) {
		this.bm = bm;
	}
	@Override
	public String toString() {
		return "Measures [time=" + time + ", va=" + va + ", bm=" + bm + ", getTime()=" + getTime() + ", getVa()="
				+ getVa() + ", getBm()=" + getBm() + ", getClass()=" + getClass() + ", hashCode()=" + hashCode()
				+ ", toString()=" + super.toString() + "]";
	}
	
	
}
