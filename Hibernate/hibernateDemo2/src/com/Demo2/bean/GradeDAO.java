package com.Demo2.bean;

import java.util.List;
import org.hibernate.LockOptions;
import org.hibernate.Query;
import org.hibernate.criterion.Example;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * A data access object (DAO) providing persistence and search support for Grade
 * entities. Transaction control of the save(), update() and delete() operations
 * can directly support Spring container-managed transactions or they can be
 * augmented to handle user-managed Spring transactions. Each of these methods
 * provides additional information for how to configure it for the desired type
 * of transaction control.
 * 
 * @see com.Demo2.bean.Grade
 * @author MyEclipse Persistence Tools
 */
public class GradeDAO extends BaseHibernateDAO {
	private static final Logger log = LoggerFactory.getLogger(GradeDAO.class);
	// property constants
	public static final String GNAME = "gname";
	public static final String GDESC = "gdesc";

	public void save(Grade transientInstance) {
		log.debug("saving Grade instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Grade persistentInstance) {
		log.debug("deleting Grade instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Grade findById(java.lang.Integer id) {
		log.debug("getting Grade instance with id: " + id);
		try {
			Grade instance = (Grade) getSession().get("com.Demo2.bean.Grade",
					id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(Grade instance) {
		log.debug("finding Grade instance by example");
		try {
			List results = getSession().createCriteria("com.Demo2.bean.Grade")
					.add(Example.create(instance)).list();
			log.debug("find by example successful, result size: "
					+ results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}

	public List findByProperty(String propertyName, Object value) {
		log.debug("finding Grade instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from Grade as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByGname(Object gname) {
		return findByProperty(GNAME, gname);
	}

	public List findByGdesc(Object gdesc) {
		return findByProperty(GDESC, gdesc);
	}

	public List findAll() {
		log.debug("finding all Grade instances");
		try {
			String queryString = "from Grade";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Grade merge(Grade detachedInstance) {
		log.debug("merging Grade instance");
		try {
			Grade result = (Grade) getSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Grade instance) {
		log.debug("attaching dirty Grade instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Grade instance) {
		log.debug("attaching clean Grade instance");
		try {
			getSession().buildLockRequest(LockOptions.NONE).lock(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}