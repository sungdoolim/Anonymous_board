package com.zx9cv.staris.dao;


public class UserVO {
	
	private String Sname;
	private String Sid;
	private String Spw;
	private String Snum;
	private String Smajor;   
	private int Sgrade; 	
	private int Siscouncil;


	public void setSname(String sname) {
		Sname = sname;
	}
	public String getSname() {
		return Sname;
	}
	public String getSid() {
		return Sid;
	}
	public void setSid(String sid) {
		Sid = sid;
	}
	public String getSpw() {
		return Spw;
	}
	public void setSpw(String spw) {
		//Spw = crt.CryptoSHA3(spw, 224);
		Spw = spw;
	}
	public String getSnum() {
		return Snum;
	}
	public void setSnum(String snum) {
		Snum = snum;
	}
	public String getSmajor() {
		return Smajor;
	}
	public void setSmajor(String smajor) {
		Smajor = smajor;
	}
	public int getSgrade() {
		return Sgrade;
	}
	public void setSgrade(int sgrade) {
		Sgrade = sgrade;
	}
	public int getSiscouncil() {
		return Siscouncil;
	}
	public void setSiscouncil(int siscouncil) {
		Siscouncil = siscouncil;
	}
	
	
	
}