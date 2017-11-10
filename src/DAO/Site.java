package DAO;

import java.util.HashSet;
import java.util.Set;

/**
 * Site entity. @author MyEclipse Persistence Tools
 */

public class Site implements java.io.Serializable {

	// Fields

	private Integer sid;
	private String siteName;
	private Set relations = new HashSet(0);

	// Constructors

	/** default constructor */
	public Site() {
	}

	/** minimal constructor */
	public Site(String siteName) {
		this.siteName = siteName;
	}

	/** full constructor */
	public Site(String siteName, Set relations) {
		this.siteName = siteName;
		this.relations = relations;
	}

	// Property accessors

	public Integer getSid() {
		return this.sid;
	}

	public void setSid(Integer sid) {
		this.sid = sid;
	}

	public String getSiteName() {
		return this.siteName;
	}

	public void setSiteName(String siteName) {
		this.siteName = siteName;
	}

	public Set getRelations() {
		return this.relations;
	}

	public void setRelations(Set relations) {
		this.relations = relations;
	}

}