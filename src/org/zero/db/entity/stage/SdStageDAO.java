package org.zero.db.entity.stage;

import java.util.List;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.zero.db.entity.user.BaseHibernateDAO;

/**
 * A data access object (DAO) providing persistence and search support for
 * SdStage entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see org.zero.db.entity.stage.SdStage
 * @author MyEclipse Persistence Tools
 */

public class SdStageDAO extends BaseHibernateDAO {
	private static final Logger log = LoggerFactory.getLogger(SdStageDAO.class);
	// property constants
	public static final String SD_STAGE_SUBJECT = "sdStageSubject";
	public static final String SD_STAGE_HTML = "sdStageHtml";

	public void save(SdStage transientInstance) {
		log.debug("saving SdStage instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(SdStage persistentInstance) {
		log.debug("deleting SdStage instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public SdStage findById(java.lang.Integer id) {
		log.debug("getting SdStage instance with id: " + id);
		try {
			SdStage instance = (SdStage) getSession().get(
					"org.zero.db.entity.stage.SdStage", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(SdStage instance) {
		log.debug("finding SdStage instance by example");
		try {
			List results = getSession().createCriteria(
					"org.zero.db.entity.stage.SdStage").add(
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
		log.debug("finding SdStage instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from SdStage as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findBySdStageSubject(Object sdStageSubject) {
		return findByProperty(SD_STAGE_SUBJECT, sdStageSubject);
	}

	public List findBySdStageHtml(Object sdStageHtml) {
		return findByProperty(SD_STAGE_HTML, sdStageHtml);
	}

	public List findAll() {
		log.debug("finding all SdStage instances");
		try {
			String queryString = "from SdStage";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public SdStage merge(SdStage detachedInstance) {
		log.debug("merging SdStage instance");
		try {
			SdStage result = (SdStage) getSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(SdStage instance) {
		log.debug("attaching dirty SdStage instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(SdStage instance) {
		log.debug("attaching clean SdStage instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}