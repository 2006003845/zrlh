package com.zrong.Android.entity;

public class Province {
	private String pid;//ʡ��ID
	private String pname;//ʡ������
	private int coordX;//��ͼ�ϵ��������
	private int coordY;
	private String level;
	
	public Province(){
		
	}
	
	
	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}


	public Province(String pid,String pname,int x,int y){
		this.pid = pid;
		this.pname = pname;
		this.coordY = x;
		this.coordY = y;
	}

	public String getPid() {
		return pid;
	}

	public void setPid(String pid) {
		this.pid = pid;
	}

	public String getPname() {
		return pname;
	}

	public void setPname(String pname) {
		this.pname = pname;
	}

	public int getCoordX() {
		return coordX;
	}

	public void setCoordX(int coordX) {
		this.coordX = coordX;
	}

	public int getCoordY() {
		return coordY;
	}

	public void setCoordY(int coordY) {
		this.coordY = coordY;
	}
	
	@Override
	public String toString() {
		
		return this.pname;
	}
	

}
