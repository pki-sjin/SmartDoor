package org.zero.db.entity.ticket;

import java.util.List;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.zero.db.entity.user.BaseHibernateDAO;

/**
 	* A data access object (DAO) providing persistence and search support for SdTicketType entities.
 			* Transaction control of the save(), update() and delete() operations 
		can directly support Spring container-managed transactions or they can be augmented	to handle user-managed Spring transactions. 
		Each of these methods provides additional information for how to configure it for the desired type of transaction control. 	
	 * @see org.zero.db.entity.ticket.SdTicketType
  * @author MyEclipse Persistence Tools 
 */

public class SdTicketTypeDAO extends BaseHibernateDAO  {
	     private static final Logger log = LoggerFactory.getLogger(SdTicketTypeDAO.class);
		//property constants
	public static final String CODE = "code";
	public static final String DESCRIPTION = "description";
	public static final String SYSRECORD = "sysrecord";
	public static final String VALID = "valid";
	public static final String REMARK = "remark";



    
    public void save(SdTicketType transientInstance) {
        log.debug("saving SdTicketType instance");
        try {
            getSession().save(transientInstance);
            log.debug("save successful");
        } catch (RuntimeException re) {
            log.error("save failed", re);
            throw re;
        }
    }
    
	public void delete(SdTicketType persistentInstance) {
        log.debug("deleting SdTicketType instance");
        try {
            getSession().delete(persistentInstance);
            log.debug("delete successful");
        } catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }
    
    public SdTicketType findById( java.lang.Integer id) {
        log.debug("getting SdTicketType instance with id: " + id);
        try {
            SdTicketType instance = (SdTicketType) getSession()
                    .get("org.zero.db.entity.ticket.SdTicketType", id);
            return instance;
        } catch (RuntimeException re) {
            log.error("get failed", re);
            throw re;
        }
    }
    
    
    public List findByExample(SdTicketType instance) {
        log.debug("finding SdTicketType instance by example");
        try {
            List results = getSession()
                    .createCriteria("org.zero.db.entity.ticket.SdTicketType")
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
      log.debug("finding SdTicketType instance with property: " + propertyName
            + ", value: " + value);
      try {
         String queryString = "from SdTicketType as model where model." 
         						+ propertyName + "= ?";
         Query queryObject = getSession().createQuery(queryString);
		 queryObject.setParameter(0, value);
		 return queryObject.list();
      } catch (RuntimeException re) {
         log.error("find by property name failed", re);
         throw re;
      }
	}

	public List findByCode(Object code
	) {
		return findByProperty(CODE, code
		);
	}
	
	public List findByDescription(Object description
	) {
		return findByProperty(DESCRIPTION, description
		);
	}
	
	public List findBySysrecord(Object sysrecord
	) {
		return findByProperty(SYSRECORD, sysrecord
		);
	}
	
	public List findByValid(Object valid
	) {
		return findByProperty(VALID, valid
		);
	}
	
	public List findByRemark(Object remark
	) {
		return findByProperty(REMARK, remark
		);
	}
	

	public List findAll() {
		log.debug("finding all SdTicketType instances");
		try {
			String queryString = "from SdTicketType";
	         Query queryObject = getSession().createQuery(queryString);
			 return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	
    public SdTicketType merge(SdTicketType detachedInstance) {
        log.debug("merging SdTicketType instance");
        try {
            SdTicketType result = (SdTicketType) getSession()
                    .merge(detachedInstance);
            log.debug("merge successful");
            return result;
        } catch (RuntimeException re) {
            log.error("merge failed", re);
            throw re;
        }
    }

    public void attachDirty(SdTicketType instance) {
        log.debug("attaching dirty SdTicketType instance");
        try {
            getSession().saveOrUpdate(instance);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
    
    public void attachClean(SdTicketType instance) {
        log.debug("attaching clean SdTicketType instance");
        try {
            getSession().lock(instance, LockMode.NONE);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
}