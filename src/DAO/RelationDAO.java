package DAO;

import java.util.List;
import org.hibernate.LockMode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

/**
 * A data access object (DAO) providing persistence and search support for
 * Relation entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see DAO.Relation
 * @author MyEclipse Persistence Tools
 */

public class RelationDAO extends HibernateDaoSupport {
	private static final Logger log = LoggerFactory
			.getLogger(RelationDAO.class);
	// property constants
	public static final String POSITION = "position";
	public static final String TRANSFER = "transfer";

	protected void initDao() {
		// do nothing
	}

	public void save(Relation transientInstance) {
		log.debug("saving Relation instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Relation persistentInstance) {
		log.debug("deleting Relation instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Relation findById(java.lang.Integer id) {
		log.debug("getting Relation instance with id: " + id);
		try {
			Relation instance = (Relation) getHibernateTemplate().get(
					"DAO.Relation", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(Relation instance) {
		log.debug("finding Relation instance by example");
		try {
			List results = getHibernateTemplate().findByExample(instance);
			log.debug("find by example successful, result size: "
					+ results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}

	public List findByProperty(String propertyName, Object value) {
		log.debug("finding Relation instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from Relation as model where model."
					+ propertyName + "= ?";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}
	//
	public List findByLinename(String linename) {
		StringBuffer buffer = new StringBuffer();
		buffer.append("from Relation r where r.lineName='");
		buffer.append(linename+"' order by position");
		try {
			return find(buffer.toString());
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	//
	public List findBySitename(int starid) {
		StringBuffer buffer = new StringBuffer();
		buffer.append("from Relation r where r.site=");
		buffer.append(starid);
		try {
			//System.out.println(buffer.toString());
			return find(buffer.toString());
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	//
		public Relation findSitename(int site,int line) {
			StringBuffer buffer = new StringBuffer();
			buffer.append("from Relation r where r.site=");
			buffer.append(site+" and r.line="+line+"");
			try {
				//System.out.println(buffer.toString());
				
				return find(buffer.toString()).get(0);
			} catch (Exception e) {
				e.printStackTrace();
				return null;
			}
		}
	
	//
	public List findTransfer(String linename) {
	StringBuffer buffer = new StringBuffer();
	buffer.append("from Relation r where r.transfer=true and r.lineName='");
	buffer.append(linename+"'");
		try {
			return find(buffer.toString());
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	//
	public List findTeansferBysite(int sid){
	List<Relation> relations = findBySitename(sid);
	StringBuffer buffer = new StringBuffer();
	buffer.append("from Relation r where r.transfer=true and (r.line=");
		for(int i=0;i<relations.size();i++){
			buffer.append(relations.get(i).getLine().getLid());
			if(i+1==relations.size()){
				buffer.append(")");
				break;
			}
			else
				buffer.append(" or r.transfer=true and r.line=");
		
		}
		try {
			return find(buffer.toString());
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}


	public List findByPosition(Object position) {
		return findByProperty(POSITION, position);
	}

	public List findByTransfer(Object transfer) {
		return findByProperty(TRANSFER, transfer);
	}

	public List findAll() {
		log.debug("finding all Relation instances");
		try {
			String queryString = "from Relation";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Relation merge(Relation detachedInstance) {
		log.debug("merging Relation instance");
		try {
			Relation result = (Relation) getHibernateTemplate().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Relation instance) {
		log.debug("attaching dirty Relation instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Relation instance) {
		log.debug("attaching clean Relation instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
	/**
	 * HQL鏌ヨ
	 * 
	 * @param hql
	 */
	public List<Relation> find(final String hql) throws Exception {
		getHibernateTemplate().setCacheQueries(true);
		return getHibernateTemplate().find(hql);
	}


	public static RelationDAO getFromApplicationContext(ApplicationContext ctx) {
		return (RelationDAO) ctx.getBean("RelationDAO");
	}
}