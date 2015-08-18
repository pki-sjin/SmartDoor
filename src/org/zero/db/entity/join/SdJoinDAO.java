package org.zero.db.entity.join;

import java.util.List;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.zero.db.entity.user.BaseHibernateDAO;

/**
 * A data access object (DAO) providing persistence and search support for
 * SdJoin entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see org.zero.db.entity.join.SdJoin
 * @author MyEclipse Persistence Tools
 */

public class SdJoinDAO extends BaseHibernateDAO {
	private static final Logger log = LoggerFactory.getLogger(SdJoinDAO.class);
	// property constants
	public static final String CODE = "code";
	public static final String DESCRIPTION = "description";
	public static final String SYSRECORD = "sysrecord";
	public static final String VALID = "valid";
	public static final String REMARK = "remark";

	public void save(SdJoin transientInstance) {
		log.debug("saving SdJoin instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(SdJoin persistentInstance) {
		log.debug("deleting SdJoin instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public SdJoin findById(java.lang.Integer id) {
		log.debug("getting SdJoin instance with id: " + id);
		try {
			SdJoin instance = (SdJoin) getSession().get(
					"org.zero.db.entity.join.SdJoin", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(SdJoin instance) {
		log.debug("finding SdJoin instance by example");
		try {
			List results = getSession().createCriteria(
					"org.zero.db.entity.join.SdJoin").add(
					Example.create(instance)).list();
			log.debug("find by example successful, result size: "
					+ results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}

	public List findByProperty(String propertyName, Object value) {
		log.debug("finding SdJoin instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from SdJoin as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByCode(Object code) {
		return findByProperty(CODE, code);
	}

	public List findByDescription(Object description) {
		return findByProperty(DESCRIPTION, description);
	}

	public List findBySysrecord(Object sysrecord) {
		return findByProperty(SYSRECORD, sysrecord);
	}

	public List findByValid(Object valid) {
		return findByProperty(VALID, valid);
	}

	public List findByRemark(Object remark) {
		return findByProperty(REMARK, remark);
	}

	public List findAll() {
		log.debug("finding all SdJoin instances");
		try {
			String queryString = "from SdJoin";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public SdJoin merge(SdJoin detachedInstance) {
		log.debug("merging SdJoin instance");
		try {
			SdJoin result = (SdJoin) getSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(SdJoin instance) {
		log.debug("attaching dirty SdJoin instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(SdJoin instance) {
		log.debug("attaching clean SdJoin instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}