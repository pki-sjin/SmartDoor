package org.zero.db.entity.activity;

import java.util.List;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.zero.db.entity.user.BaseHibernateDAO;

/**
 	* A data access object (DAO) providing persistence and search support for SdExUserRelation entities.
 			* Transaction control of the save(), update() and delete() operations 
		can directly support Spring container-managed transactions or they can be augmented	to handle user-managed Spring transactions. 
		Each of these methods provides additional information for how to configure it for the desired type of transaction control. 	
	 * @see org.zero.db.entity.activity.SdExUserRelation
  * @author MyEclipse Persistence Tools 
 */

public class SdExUserRelationDAO extends BaseHibernateDAO  {
	     private static final Logger log = LoggerFactory.getLogger(SdExUserRelationDAO.class);
		//property constants
	public static final String EX_ID = "exId";
	public static final String USER_ID = "userId";
	public static final String JOIN_ID = "joinId";
	public static final String ORDER_ID = "orderId";



    
    public void save(SdExUserRelation transientInstance) {
        log.debug("saving SdExUserRelation instance");
        try {
            getSession().save(transientInstance);
            log.debug("save successful");
        } catch (RuntimeException re) {
            log.error("save failed", re);
            throw re;
        }
    }
    
	public void delete(SdExUserRelation persistentInstance) {
        log.debug("deleting SdExUserRelation instance");
        try {
            getSession().delete(persistentInstance);
            log.debug("delete successful");
        } catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }
    
    public SdExUserRelation findById( java.lang.Integer id) {
        log.debug("getting SdExUserRelation instance with id: " + id);
        try {
            SdExUserRelation instance = (SdExUserRelation) getSession()
                    .get("org.zero.db.entity.activity.SdExUserRelation", id);
            return instance;
        } catch (RuntimeException re) {
            log.error("get failed", re);
            throw re;
        }
    }
    
    
    public List findByExample(SdExUserRelation instance) {
        log.debug("finding SdExUserRelation instance by example");
        try {
            List results = getSession()
                    .createCriteria("org.zero.db.entity.activity.SdExUserRelation")
                    .add(Example.create(instance))
            .list();
            log.debug("find by example successful, result size: " + results.size());
            return results;
        } catch (RuntimeException re) {
            log.error("find by example failed", re);
            throw re;
        }
    }    
    
    public List findByProperty(String propertyName, Object value) {
      log.debug("finding SdExUserRelation instance with property: " + propertyName
            + ", value: " + value);
      try {
         String queryString = "from SdExUserRelation as model where model." 
         						+ propertyName + "= ?";
         Query queryObject = getSession().createQuery(queryString);
		 queryObject.setParameter(0, value);
		 return queryObject.list();
      } catch (RuntimeException re) {
         log.error("find by property name failed", re);
         throw re;
      }
	}

	public List findByExId(Object exId
	) {
		return findByProperty(EX_ID, exId
		);
	}
	
	public List findByUserId(Object userId
	) {
		return findByProperty(USER_ID, userId
		);
	}
	
	public List findByJoinId(Object joinId
	) {
		return findByProperty(JOIN_ID, joinId
		);
	}
	
	public List findByOrderId(Object orderId
	) {
		return findByProperty(ORDER_ID, orderId
		);
	}
	

	public List findAll() {
		log.debug("finding all SdExUserRelation instances");
		try {
			String queryString = "from SdExUserRelation";
	         Query queryObject = getSession().createQuery(queryString);
			 return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	
    public SdExUserRelation merge(SdExUserRelation detachedInstance) {
        log.debug("merging SdExUserRelation instance");
        try {
            SdExUserRelation result = (SdExUserRelation) getSession()
                    .merge(detachedInstance);
            log.debug("merge successful");
            return result;
        } catch (RuntimeException re) {
            log.error("merge failed", re);
            throw re;
        }
    }

    public void attachDirty(SdExUserRelation instance) {
        log.debug("attaching dirty SdExUserRelation instance");
        try {
            getSession().saveOrUpdate(instance);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
    
    public void attachClean(SdExUserRelation instance) {
        log.debug("attaching clean SdExUserRelation instance");
        try {
            getSession().lock(instance, LockMode.NONE);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
}