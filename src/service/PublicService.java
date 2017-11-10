package service;

import java.util.List;

import DAO.Line;
import DAO.LineDAO;
import DAO.Relation;
import DAO.RelationDAO;
import DAO.Site;
import DAO.SiteDAO;

public class PublicService {
	private List<Line> lines;
	private List<Site> sites;
	private Line line;
	private Site site;
	private LineDAO lineDAO;
	private SiteDAO siteDAO;
	private Relation relation;
	private List<Relation> relations;
	private List<Relation> starrelations;
	private List<Relation> endrelations;
	private RelationDAO relationDAO;
	private String star;
	private String end;
	private int starid;
	private int endid;
	
	
	private boolean C=false;
	
	public Line getLine() {
		return line;
	}
	public void setLine(Line line) {
		this.line = line;
	}
	public Site getSite() {
		return site;
	}
	public void setSite(Site site) {
		this.site = site;
	}
	public Relation getRelation() {
		return relation;
	}
	public void setRelation(Relation relation) {
		this.relation = relation;
	}
	public List<Relation> getRelations() {
		return relations;
	}
	public void setRelations(List<Relation> relations) {
		this.relations = relations;
	}
	public RelationDAO getRelationDAO() {
		return relationDAO;
	}
	public void setRelationDAO(RelationDAO relationDAO) {
		this.relationDAO = relationDAO;
	}
	public String getStar() {
		return star;
	}
	public void setStar(String star) {
		this.star = star;
	}
	public String getEnd() {
		return end;
	}
	public void setEnd(String end) {
		this.end = end;
	}
	public boolean isC() {
		return C;
	}
	public void setC(boolean c) {
		C = c;
	}
	public List getLines() {
		return lines;
	}
	public void setLines(List lines) {
		this.lines = lines;
	}
	public List getSites() {
		return sites;
	}
	public void setSites(List sites) {
		this.sites = sites;
	}
	public LineDAO getLineDAO() {
		return lineDAO;
	}
	public void setLineDAO(LineDAO lineDAO) {
		this.lineDAO = lineDAO;
	}
	public SiteDAO getSiteDAO() {
		return siteDAO;
	}
	public void setSiteDAO(SiteDAO siteDAO) {
		this.siteDAO = siteDAO;
	}
	
	
	public String listLineNames(){
		lines = lineDAO.findAll();
		return "listAllLineName";
	}
	//直达路线
	public boolean onebus(){
		int transfer=0;
		int[] num = new int[1];
		//查询起始站所在线路
		starrelations = relationDAO.findBySitename(starid);
		int[] compare1 = new int[starrelations.size()];
		for(int i=0;i<starrelations.size();i++){
			compare1[i] =  starrelations.get(i).getLine().getLid();
		}
		//查询终点站所在路线
		endrelations = relationDAO.findBySitename(endid);
		int[] compare2 = new int[endrelations.size()];
		for(int i=0;i<endrelations.size();i++){
			compare2[i] =  endrelations.get(i).getLine().getLid();
		}
		//判断是否在同一条线路上
		for(int i=0;i<compare1.length;i++){
			for(int j=0;j<compare2.length;j++){
				if(compare1[i]==compare2[j]){
					C=true;
					transfer = compare1[i];
					num[0] = Math.abs(starrelations.get(i).getPosition()-endrelations.get(j).getPosition());
					break;
				}
			}
			if(C)
				break;
		}
		if(C!=false){
			System.out.println(PString(starid, transfer, endid, num[0]));
			return C;
		}else{
			//System.out.println("没有直达线路");
			return false;
		}
	}
	//转乘一次
	public String twebus(){
		String line=null;
		int[] num = new int[2];
		//查询起始站所在线路的所有中转站，并存入数组compare
		starrelations = relationDAO.findTeansferBysite(starid);
		int[] compare1 = new int[starrelations.size()];
		for(int i=0;i<starrelations.size();i++){
			compare1[i] =  starrelations.get(i).getSite().getSid();
		}
		//查询终点站所在线路的所有中转站，并存入数组compare
		endrelations = relationDAO.findTeansferBysite(endid);
		int[]compare2 = new int[endrelations.size()];
		for(int i=0;i<endrelations.size();i++){
			compare2[i] =  endrelations.get(i).getSite().getSid();
		}
		//判断两路线是否有同一中转站，有则输出
		for(int i=0;i<compare1.length;i++){
			for(int j=0;j<compare2.length;j++){
				if(compare1[i]==compare2[j]){
					C=true;
					
					relation = relationDAO.findSitename(starid, starrelations.get(i).getLine().getLid());
					num[0] = Math.abs(relation.getPosition()-starrelations.get(i).getPosition());
					relation = relationDAO.findSitename(endid, endrelations.get(i).getLine().getLid());
					num[1] = Math.abs(relation.getPosition()-endrelations.get(i).getPosition());
					line = PString(starid, starrelations.get(i).getLine().getLid(), compare1[i], num[0]);
					line += ";"+PString(compare1[i], endrelations.get(i).getLine().getLid(), endid, num[1]);
					break;
				}
			}
			if(C)
				break;
		}
		if(C!=false){
			System.out.println(line);
			return "buslines";
		}else{
			//System.out.println("无法通过一次转乘到达目的地");
			return null;
		}
	}
	//转乘两次
	public boolean threebus(){
		String line=null;
		int[] num = new int[3];
		//查询起始站所在路线的中转站，并存入数组
		starrelations = relationDAO.findTeansferBysite(starid);
		int[][] compare01 = new int[starrelations.size()][];
		int[] compare1 = new int[starrelations.size()];
		for(int i=0;i<starrelations.size();i++){
			compare1[i] =  starrelations.get(i).getSite().getSid();
		}
		//查询各中转站所连接的线路，并存入数组
		for(int A=0;A<compare01.length;A++){
			starrelations = relationDAO.findBySitename(compare1[A]);
			compare01[A] = new int[starrelations.size()];
			for(int i=0;i<starrelations.size();i++){
				compare01[A][i] =  starrelations.get(i).getLine().getLid();
			}
		}
		//查询终点站所在路线的中转站，并存入数组
		endrelations = relationDAO.findTeansferBysite(endid);
		int[][] compare02 = new int[endrelations.size()][];
		int[] compare2 = new int[endrelations.size()];
		for(int i=0;i<endrelations.size();i++){
			compare2[i] =  endrelations.get(i).getSite().getSid();
		}
		//查询各中转站所连接的线路，并存入数组
		for(int A=0;A<compare01.length;A++){
		endrelations = relationDAO.findBySitename(compare2[A]);
			compare02[A] = new int[endrelations.size()];
			for(int i=0;i<endrelations.size();i++){
				compare02[A][i] =  endrelations.get(i).getLine().getLid();
			}
		}
		//判断是否连通，有侧输出
		for(int a=0;a<compare01.length;a++){
			for(int b=0;b<compare02.length;b++){
				for(int i=0;i<compare01[a].length;i++){
						for(int j=0;j<compare02[b].length;j++){
						if(compare01[a][i]==compare02[b][j]){
							C=true;
							num[0] = Math.abs(starrelations.get(i).getPosition()-endrelations.get(j).getPosition()-1);
							line =  turnbus(starid,compare1[a]);
							line += ";"+PString(compare1[a], compare01[a][i], compare2[b], num[0]);
							line += ";"+turnbus(compare2[b],endid);
							break;
						}
					}
				if(C)
						break;
				}
				if(C)
					break;
			}
			if(C)
				break;
		}
		if(C!=false){
			System.out.println(line);
			return C;
		}else{
			//System.out.println("没有直达线路");
			return false;
		}	
	}
	
	//直达、转乘一次、二次集合方法
	public String findbusline(){
		C = false;
		site = (Site) siteDAO.findBySitename(star);
		starid = site.getSid();
		site = (Site) siteDAO.findBySitename(end);
		endid = site.getSid();
		
		onebus();
		if(!C){
			twebus();
			if(!C){
				threebus();
				if(!C)
					System.out.println("需要转车两次以上。");
				
			}
		}
		if(C)
			return "buslines";
		else
			return null;
	}
	
	//输出同一线路上的两个站点的乘坐信息
	public String turnbus(int star,int end){
		C=false;
		int transfer=0;
		String line=null;
		int[] num = new int[1];
		List<Relation> relations1 = relationDAO.findBySitename(star);
		int[] comp1 = new int[relations1.size()];
		for(int i=0;i<relations1.size();i++){
			comp1[i] =  relations1.get(i).getLine().getLid();
		}
		List<Relation> relations2 = relationDAO.findBySitename(end);
		int[] comp2 = new int[relations2.size()];
		for(int i=0;i<relations2.size();i++){
			comp2[i] =  relations2.get(i).getLine().getLid();
		}
		for(int i=0;i<comp1.length;i++){
			for(int j=0;j<comp2.length;j++){
				if(comp1[i]==comp2[j]){
					C=true;
					transfer = comp1[i];
					num[0] = Math.abs(relations1.get(i).getPosition()-relations2.get(j).getPosition()-1);
					line = PString(star, transfer, end, num[0]);
					break;
				}
			}
			if(C)
				break;
		}
		if(C!=false){
			return line;
		}else{
			//System.out.println("没有直达线路");
			return "没有直达线路";
		}
	}
	
	//从ID转换为name输出
	public String PString(int sid,int lid,int eid,int num){
		String star = siteDAO.findById(sid).getSiteName();
		String line = lineDAO.findById(lid).getLineName();
		String end = siteDAO.findById(eid).getSiteName();
		return star+"乘坐"+line+"经过"+num+"站到达"+end;
	}
}
