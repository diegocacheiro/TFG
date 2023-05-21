package com.usc.datos;

import java.io.Serializable;

public class Measures implements Serializable{
	private String time;
	private Double va;
	private Integer bm;
	
	public Measures(String time, Double va, Integer bm) {
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
	public Double getVa() {
		return va;
	}
	public void setVa(Double va) {
		this.va = va;
	}
	public Integer getBm() {
		return bm;
	}
	public void setBm(Integer bm) {
		this.bm = bm;
	}
	@Override
	public String toString() {
		return "Measures [time=" + time + ", va=" + va + ", bm=" + bm + ", getTime()=" + getTime() + ", getVa()="
				+ getVa() + ", getBm()=" + getBm() + ", getClass()=" + getClass() + ", hashCode()=" + hashCode()
				+ ", toString()=" + super.toString() + "]";
	}
	
	
}
