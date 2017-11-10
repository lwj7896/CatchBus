package service;

import java.util.List;

import org.apache.struts2.ServletActionContext;

import DAO.Relation;
import DAO.RelationDAO;

public class RelationService {
	private Relation relation;
	private List<Relation> relations;
	private RelationDAO relationDAO;
	private int rid;
	private String linename;
	private String position;
	private String sitename;
	private String transfer;
	
	public List<Relation> getRelations() {
		return relations;
	}
	public void setRelations(List<Relation> relations) {
		this.relations = relations;
	}
	public Relation getRelation() {
		return relation;
	}
	public void setRelation(Relation relation) {
		this.relation = relation;
	}
	public RelationDAO getRelationDAO() {
		return relationDAO;
	}
	public void setRelationDAO(RelationDAO relationDAO) {
		this.relationDAO = relationDAO;
	}
	public int getRid() {
		return rid;
	}
	public void setRid(int rid) {
		this.rid = rid;
	}
	public String getLinename() {
		return linename;
	}
	public void setLinename(String linename) {
		this.linename = linename;
	}
	public String getPosition() {
		return position;
	}
	public void setPosition(String position) {
		this.position = position;
	}
	public String getSitename() {
		return sitename;
	}
	public void setSitename(String sitename) {
		this.sitename = sitename;
	}
	public String getTransfer() {
		return transfer;
	}
	public void setTransfer(String transfer) {
		this.transfer = transfer;
	}
	//输出Relation表所有数据
	public String listAllRelation(){
		relations = relationDAO.findAll();
		//System.out.println(relations.get(10).getSiteName());
		return "listAllRelations";
	}
	//根据线路名查询
	public String findbyLineName(){
		System.out.println(linename);
		relations = relationDAO.findByLinename(linename);
		ServletActionContext.getRequest().getSession().setAttribute("linename",
				linename);
		return "listByLineName";
	}
	//根据站点名查询
	/*
	public String findbySiteName(){
		System.out.println(sitename);
		relations = relationDAO.findBySitename(sitename);
		ServletActionContext.getRequest().getSession().setAttribute("sitename",
				sitename);
		return "listByLineName";
	}*/
	//查找线路中转站
	public String findTransfer(){
		System.out.println(linename);
		relations = relationDAO.findTransfer(linename);
		ServletActionContext.getRequest().getSession().setAttribute("linename",
				linename);
		return "listByLineName";
	}
	//删除一行Relation数据
	public String delelteRelation(){
		relation = relationDAO.findById(rid);
		relationDAO.delete(relation);
		return listAllRelation();
	}
	
	//
	public String AddRelation(){
		return "";
	}
	
}
