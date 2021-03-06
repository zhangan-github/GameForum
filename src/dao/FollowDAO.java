package dao;

import java.util.List;
import org.hibernate.LockOptions;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Example;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.transaction.annotation.Transactional;

/**
 * A data access object (DAO) providing persistence and search support for
 * Follow entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see dao.Follow
 * @author MyEclipse Persistence Tools
 */
@Transactional
public class FollowDAO {
	private static final Logger log = LoggerFactory.getLogger(FollowDAO.class);
	// property constants

	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	private Session getCurrentSession() {
		return sessionFactory.getCurrentSession();
	}

	protected void initDao() {
		// do nothing
	}

	public void save(Follow transientInstance) {
		log.debug("saving Follow instance");
		try {
			getCurrentSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Follow persistentInstance) {
		log.debug("deleting Follow instance");
		try {
			getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Follow findById(java.lang.Integer id) {
		log.debug("getting Follow instance with id: " + id);
		try {
			Follow instance = (Follow) getCurrentSession()
					.get("dao.Follow", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(Follow instance) {
		log.debug("finding Follow instance by example");
		try {
			List results = getCurrentSession().createCriteria("dao.Follow")
					.add(Example.create(instance)).list();
			log.debug("find by example successful, result size: "
					+ results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}
	
	public List findByFrom(String fromAccount){
		return findByProperty("guserByFromId.account",fromAccount);
	}
	
	public List findByTarget(String targetAccount){
		return findByProperty("guserByTargetId.account",targetAccount);
	}
	
	public List findByFromAndTarget(String fromAccount,String targetAccount){
		log.debug("finding Follow instance with property: " + fromAccount
				+ ", value: " + fromAccount);
		try {
			String queryString = "from Follow as model where "
					+ "model.guserByFromId.account = ? and guserByTargetId.account = ?";
			Query queryObject = getCurrentSession().createQuery(queryString);
			queryObject.setParameter(0, fromAccount);
			queryObject.setParameter(1, targetAccount);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByProperty(String propertyName, Object value) {
		log.debug("finding Follow instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from Follow as model where model."
					+ propertyName + "= ?";
			Query queryObject = getCurrentSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findAll() {
		log.debug("finding all Follow instances");
		try {
			String queryString = "from Follow";
			Query queryObject = getCurrentSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Follow merge(Follow detachedInstance) {
		log.debug("merging Follow instance");
		try {
			Follow result = (Follow) getCurrentSession()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Follow instance) {
		log.debug("attaching dirty Follow instance");
		try {
			getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Follow instance) {
		log.debug("attaching clean Follow instance");
		try {
			getCurrentSession().buildLockRequest(LockOptions.NONE).lock(
					instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static FollowDAO getFromApplicationContext(ApplicationContext ctx) {
		return (FollowDAO) ctx.getBean("FollowDAO");
	}
}