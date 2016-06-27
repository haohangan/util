package com.grgbanking.aptoto.fastdfs;

public class FastFileLoc {
	private String fname;
	private String fpath;
	
	public FastFileLoc(){}
	
	public FastFileLoc(String fname, String fpath) {
		super();
		this.fname = fname;
		this.fpath = fpath;
	}


	public String getFname() {
		return fname;
	}

	public void setFname(String fname) {
		this.fname = fname;
	}

	public String getFpath() {
		return fpath;
	}

	public void setFpath(String fpath) {
		this.fpath = fpath;
	}

}
