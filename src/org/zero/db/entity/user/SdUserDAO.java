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
	public static final String CODE = "code";
	public static final String EX_ID = "exId";
	public static final String NAME = "name";
	public static final String PASSWORD = "password";
	public static final String DISPLAYNAME = "displayname";
	public static final String COUNTRY = "country";
	public static final String PROVINCE = "province";
	public static final String CITY = "city";
	public static final String COMPANY = "company";
	public static final String ADDRESS = "address";
	public static final String POSTCODE = "postcode";
	public static final String WEB = "web";
	public static final String POSITION = "position";
	public static final String TEL = "tel";
	public static final String CELL = "cell";
	public static final String FAX = "fax";
	public static final String MAIL = "mail";
	public static final String TYPE_ID = "typeId";
	public static final String SYSRECORD = "sysrecord";
	public static final String VALID = "valid";
	public static final String REMARK = "remark";

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

	public List findByCode(Object code) {
		return findByProperty(CODE, code);
	}

	public List findByExId(Object exId) {
		return findByProperty(EX_ID, exId);
	}

	public List findByName(Object name) {
		return findByProperty(NAME, name);
	}

	public List findByPassword(Object password) {
		return findByProperty(PASSWORD, password);
	}

	public List findByDisplayname(Object displayname) {
		return findByProperty(DISPLAYNAME, displayname);
	}

	public List findByCountry(Object country) {
		return findByProperty(COUNTRY, country);
	}

	public List findByProvince(Object province) {
		return findByProperty(PROVINCE, province);
	}

	public List findByCity(Object city) {
		return findByProperty(CITY, city);
	}

	public List findByCompany(Object company) {
		return findByProperty(COMPANY, company);
	}

	public List findByAddress(Object address) {
		return findByProperty(ADDRESS, address);
	}

	public List findByPostcode(Object postcode) {
		return findByProperty(POSTCODE, postcode);
	}

	public List findByWeb(Object web) {
		return findByProperty(WEB, web);
	}

	public List findByPosition(Object position) {
		return findByProperty(POSITION, position);
	}

	public List findByTel(Object tel) {
		return findByProperty(TEL, tel);
	}

	public List findByCell(Object cell) {
		return findByProperty(CELL, cell);
	}

	public List findByFax(Object fax) {
		return findByProperty(FAX, fax);
	}

	public List findByMail(Object mail) {
		return findByProperty(MAIL, mail);
	}

	public List findByTypeId(Object typeId) {
		return findByProperty(TYPE_ID, typeId);
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