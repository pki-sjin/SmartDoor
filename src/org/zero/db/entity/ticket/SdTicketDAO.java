package org.zero.db.entity.ticket;

import java.sql.Timestamp;
import java.util.List;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.zero.db.entity.user.BaseHibernateDAO;

/**
 	* A data access object (DAO) providing persistence and search support for SdTicket entities.
 			* Transaction control of the save(), update() and delete() operations 
		can directly support Spring container-managed transactions or they can be augmented	to handle user-managed Spring transactions. 
		Each of these methods provides additional information for how to configure it for the desired type of transaction control. 	
	 * @see org.zero.db.entity.ticket.SdTicket
  * @author MyEclipse Persistence Tools 
 */

public class SdTicketDAO extends BaseHibernateDAO  {
	     private static final Logger log = LoggerFactory.getLogger(SdTicketDAO.class);
		//property constants
	public static final String CODE = "code";
	public static final String EX_ID = "exId";
	public static final String NAME = "name";
	public static final String DESCRIPTION = "description";
	public static final String PRICE = "price";
	public static final String TYPE_ID = "typeId";
	public static final String SYSRECORD = "sysrecord";
	public static final String VALID = "valid";
	public static final String REMARK = "remark";



    
    public void save(SdTicket transientInstance) {
        log.debug("saving SdTicket instance");
        try {
            getSession().save(transientInstance);
            log.debug("save successful");
        } catch (RuntimeException re) {
            log.error("save failed", re);
            throw re;
        }
    }
    
	public void delete(SdTicket persistentInstance) {
        log.debug("deleting SdTicket instance");
        try {
            getSession().delete(persistentInstance);
            log.debug("delete successful");
        } catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }
    
    public SdTicket findById( java.lang.Integer id) {
        log.debug("getting SdTicket instance with id: " + id);
        try {
            SdTicket instance = (SdTicket) getSession()
                    .get("org.zero.db.entity.ticket.SdTicket", id);
            return instance;
        } catch (RuntimeException re) {
            log.error("get failed", re);
            throw re;
        }
    }
    
    
    public List findByExample(SdTicket instance) {
        log.debug("finding SdTicket instance by example");
        try {
            List results = getSession()
                    .createCriteria("org.zero.db.entity.ticket.SdTicket")
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
      log.debug("finding SdTicket instance with property: " + propertyName
            + ", value: " + value);
      try {
         String queryString = "from SdTicket as model where model." 
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
	
	public List findByExId(Object exId
	) {
		return findByProperty(EX_ID, exId
		);
	}
	
	public List findByName(Object name
	) {
		return findByProperty(NAME, name
		);
	}
	
	public List findByDescription(Object description
	) {
		return findByProperty(DESCRIPTION, description
		);
	}
	
	public List findByPrice(Object price
	) {
		return findByProperty(PRICE, price
		);
	}
	
	public List findByTypeId(Object typeId
	) {
		return findByProperty(TYPE_ID, typeId
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
		log.debug("finding all SdTicket instances");
		try {
			String queryString = "from SdTicket";
	         Query queryObject = getSession().createQuery(queryString);
			 return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	
    public SdTicket merge(SdTicket detachedInstance) {
        log.debug("merging SdTicket instance");
        try {
            SdTicket result = (SdTicket) getSession()
                    .merge(detachedInstance);
            log.debug("merge successful");
            return result;
        } catch (RuntimeException re) {
            log.error("merge failed", re);
            throw re;
        }
    }

    public void attachDirty(SdTicket instance) {
        log.debug("attaching dirty SdTicket instance");
        try {
            getSession().saveOrUpdate(instance);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
    
    public void attachClean(SdTicket instance) {
        log.debug("attaching clean SdTicket instance");
        try {
            getSession().lock(instance, LockMode.NONE);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
}