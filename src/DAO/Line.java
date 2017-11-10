package DAO;

import java.util.HashSet;
import java.util.Set;

/**
 * Line entity. @author MyEclipse Persistence Tools
 */

public class Line implements java.io.Serializable {

	// Fields

	private Integer lid;
	private String lineName;
	private String start;
	private String end;
	private Set relations = new HashSet(0);

	// Constructors

	/** default constructor */
	public Line() {
	}

	/** minimal constructor */
	public Line(String lineName, String start, String end) {
		this.lineName = lineName;
		this.start = start;
		this.end = end;
	}

	/** full constructor */
	public Line(String lineName, String start, String end, Set relations) {
		this.lineName = lineName;
		this.start = start;
		this.end = end;
		this.relations = relations;
	}

	// Property accessors

	public Integer getLid() {
		return this.lid;
	}

	public void setLid(Integer lid) {
		this.lid = lid;
	}

	public String getLineName() {
		return this.lineName;
	}

	public void setLineName(String lineName) {
		this.lineName = lineName;
	}

	public String getStart() {
		return this.start;
	}

	public void setStart(String start) {
		this.start = start;
	}

	public String getEnd() {
		return this.end;
	}

	public void setEnd(String end) {
		this.end = end;
	}

	public Set getRelations() {
		return this.relations;
	}

	public void setRelations(Set relations) {
		this.relations = relations;
	}

}