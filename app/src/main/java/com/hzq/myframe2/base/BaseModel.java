package com.hzq.myframe2.base;


import java.io.Serializable;

public class BaseModel implements Serializable {
	private static final long serialVersionUID = 1L;
	private int status = 0;

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}
}
