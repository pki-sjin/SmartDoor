package org.zero.db.entity.user;

import java.sql.Timestamp;
import java.util.List;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * A data access object (DAO) providing persistence and search support for
 * SdUser entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see org.zero.db.entity.user.SdUser
 * @author MyEclipse Persistence Tools
 */

public class SdUserDAO extends BaseHibernateDAO {
	private static final Logger log = LoggerFactory.getLogger(SdUserDAO.class);
	// property constants
	public static final String SD_USER_NAME = "sdUserName";
	public static final String SD_USER_PASSWORD = "sdUserPassword";
	public static final String SD_USER_DISPLAYNAME = "sdUserDisplayname";
	public static final String SD_USER_COMPANY = "sdUserCompany";
	public static final String SD_USER_POSITION = "sdUserPosition";
	public static final String SD_USER_TEL = "sdUserTel";
	public static final String SD_USER_CELL = "sdUserCell";
	public static final String SD_USER_FAX = "sdUserFax";
	public static final String SD_USER_MAIL = "sdUserMail";
	public static final String SD_USER_TYPE_ID = "sdUserTypeId";

	public void save(SdUser transientInstance) {
		log.debug("saving SdUser instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(SdUser persistentInstance) {
		log.debug("deleting SdUser instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public SdUser findById(java.lang.Integer id) {
		log.debug("getting SdUser instance with id: " + id);
		try {
			SdUser instance = (SdUser) getSession().get(
					"org.zero.db.entity.user.SdUser", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(SdUser instance) {
		log.debug("finding SdUser instance by example");
		try {
			List results = getSession().createCriteria(
					"org.zero.db.entity.user.SdUser").add(
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
		log.debug("finding SdUser instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from SdUser as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findBySdUserName(Object sdUserName) {
		return findByProperty(SD_USER_NAME, sdUserName);
	}

	public List findBySdUserPassword(Object sdUserPassword) {
		return findByProperty(SD_USER_PASSWORD, sdUserPassword);
	}

	public List findBySdUserDisplayname(Object sdUserDisplayname) {
		return findByProperty(SD_USER_DISPLAYNAME, sdUserDisplayname);
	}

	public List findBySdUserCompany(Object sdUserCompany) {
		return findByProperty(SD_USER_COMPANY, sdUserCompany);
	}

	public List findBySdUserPosition(Object sdUserPosition) {
		return findByProperty(SD_USER_POSITION, sdUserPosition);
	}

	public List findBySdUserTel(Object sdUserTel) {
		return findByProperty(SD_USER_TEL, sdUserTel);
	}

	public List findBySdUserCell(Object sdUserCell) {
		return findByProperty(SD_USER_CELL, sdUserCell);
	}

	public List findBySdUserFax(Object sdUserFax) {
		return findByProperty(SD_USER_FAX, sdUserFax);
	}

	public List findBySdUserMail(Object sdUserMail) {
		return findByProperty(SD_USER_MAIL, sdUserMail);
	}

	public List findBySdUserTypeId(Object sdUserTypeId) {
		return findByProperty(SD_USER_TYPE_ID, sdUserTypeId);
	}

	public List findAll() {
		log.debug("finding all SdUser instances");
		try {
			String queryString = "from SdUser";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public SdUser merge(SdUser detachedInstance) {
		log.debug("merging SdUser instance");
		try {
			SdUser result = (SdUser) getSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(SdUser instance) {
		log.debug("attaching dirty SdUser instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(SdUser instance) {
		log.debug("attaching clean SdUser instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}