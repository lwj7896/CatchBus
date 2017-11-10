package DAO;

/**
 * Relation entity. @author MyEclipse Persistence Tools
 */

public class Relation implements java.io.Serializable {

	// Fields

	private Integer rid;
	private Site site;
	private Line line;
	private Integer position;
	private Boolean transfer;

	// Constructors

	/** default constructor */
	public Relation() {
	}

	/** full constructor */
	public Relation(Site site, Line line, Integer position, Boolean transfer) {
		this.site = site;
		this.line = line;
		this.position = position;
		this.transfer = transfer;
	}

	// Property accessors

	public Integer getRid() {
		return this.rid;
	}

	public void setRid(Integer rid) {
		this.rid = rid;
	}

	public Site getSite() {
		return this.site;
	}

	public void setSite(Site site) {
		this.site = site;
	}

	public Line getLine() {
		return this.line;
	}

	public void setLine(Line line) {
		this.line = line;
	}

	public Integer getPosition() {
		return this.position;
	}

	public void setPosition(Integer position) {
		this.position = position;
	}

	public Boolean getTransfer() {
		return this.transfer;
	}

	public void setTransfer(Boolean transfer) {
		this.transfer = transfer;
	}

}