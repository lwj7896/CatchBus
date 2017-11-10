package DAO;

import java.util.List;
import org.hibernate.LockMode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

/**
 * A data access object (DAO) providing persistence and search support for Bus
 * entities. Transaction control of the save(), update() and delete() operations
 * can directly support Spring container-managed transactions or they can be
 * augmented to handle user-managed Spring transactions. Each of these methods
 * provides additional information for how to configure it for the desired type
 * of transaction control.
 * 
 * @see DAO.Bus
 * @author MyEclipse Persistence Tools
 */

public class BusDAO extends HibernateDaoSupport {
	private static final Logger log = LoggerFactory.getLogger(BusDAO.class);
	// property constants
	public static final String BNUM = "bnum";
	public static final String BLINE = "bline";

	protected void initDao() {
		// do nothing
	}

	public void save(Bus transientInstance) {
		log.debug("saving Bus instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Bus persistentInstance) {
		log.debug("deleting Bus instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Bus findById(java.lang.Integer id) {
		log.debug("getting Bus instance with id: " + id);
		try {
			Bus instance = (Bus) getHibernateTemplate().get("DAO.Bus", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(Bus instance) {
		log.debug("finding Bus instance by example");
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
		log.debug("finding Bus instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from Bus as model where model."
					+ propertyName + "= ?";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByBnum(Object bnum) {
		return findByProperty(BNUM, bnum);
	}

	public List findByBline(Object bline) {
		return findByProperty(BLINE, bline);
	}

	public List findAll() {
		log.debug("finding all Bus instances");
		try {
			String queryString = "from Bus";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Bus merge(Bus detachedInstance) {
		log.debug("merging Bus instance");
		try {
			Bus result = (Bus) getHibernateTemplate().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Bus instance) {
		log.debug("attaching dirty Bus instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Bus instance) {
		log.debug("attaching clean Bus instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static BusDAO getFromApplicationContext(ApplicationContext ctx) {
		return (BusDAO) ctx.getBean("BusDAO");
	}
}