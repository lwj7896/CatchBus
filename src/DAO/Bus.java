package DAO;

/**
 * Bus entity. @author MyEclipse Persistence Tools
 */

public class Bus implements java.io.Serializable {

	// Fields

	private Integer bid;
	private String bnum;
	private String bline;

	// Constructors

	/** default constructor */
	public Bus() {
	}

	/** full constructor */
	public Bus(String bnum, String bline) {
		this.bnum = bnum;
		this.bline = bline;
	}

	// Property accessors

	public Integer getBid() {
		return this.bid;
	}

	public void setBid(Integer bid) {
		this.bid = bid;
	}

	public String getBnum() {
		return this.bnum;
	}

	public void setBnum(String bnum) {
		this.bnum = bnum;
	}

	public String getBline() {
		return this.bline;
	}

	public void setBline(String bline) {
		this.bline = bline;
	}

}