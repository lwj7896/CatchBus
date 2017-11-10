package DAO;

import java.util.List;
import java.util.Set;
import org.hibernate.LockMode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

/**
 * A data access object (DAO) providing persistence and search support for Line
 * entities. Transaction control of the save(), update() and delete() operations
 * can directly support Spring container-managed transactions or they can be
 * augmented to handle user-managed Spring transactions. Each of these methods
 * provides additional information for how to configure it for the desired type
 * of transaction control.
 * 
 * @see DAO.Line
 * @author MyEclipse Persistence Tools
 */

public class LineDAO extends HibernateDaoSupport {
	private static final Logger log = LoggerFactory.getLogger(LineDAO.class);
	// property constants
	public static final String LINE_NAME = "lineName";
	public static final String START = "start";
	public static final String END = "end";

	protected void initDao() {
		// do nothing
	}

	public void save(Line transientInstance) {
		log.debug("saving Line instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Line persistentInstance) {
		log.debug("deleting Line instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Line findById(java.lang.Integer id) {
		log.debug("getting Line instance with id: " + id);
		try {
			Line instance = (Line) getHibernateTemplate().get("DAO.Line", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(Line instance) {
		log.debug("finding Line instance by example");
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
		log.debug("finding Line instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from Line as model where model."
					+ propertyName + "= ?";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByLineName(Object lineName) {
		return findByProperty(LINE_NAME, lineName);
	}

	public List findByStart(Object start) {
		return findByProperty(START, start);
	}

	public List findByEnd(Object end) {
		return findByProperty(END, end);
	}

	public List findAll() {
		log.debug("finding all Line instances");
		try {
			String queryString = "from Line";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Line merge(Line detachedInstance) {
		log.debug("merging Line instance");
		try {
			Line result = (Line) getHibernateTemplate().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Line instance) {
		log.debug("attaching dirty Line instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Line instance) {
		log.debug("attaching clean Line instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static LineDAO getFromApplicationContext(ApplicationContext ctx) {
		return (LineDAO) ctx.getBean("LineDAO");
	}
}