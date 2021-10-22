package EjercicioHibernate.EjercicioHibernate;

// default package
// Generated 22 oct 2021 18:16:50 by Hibernate Tools 5.5.7.Final

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.InitialContext;
import org.hibernate.LockMode;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Example;

/**
 * Home object for domain model class Empleado.
 * @see .Empleado
 * @author Hibernate Tools
 */
public class EmpleadoHome {

	private static final Logger logger = Logger.getLogger(EmpleadoHome.class.getName());

	private final SessionFactory sessionFactory = getSessionFactory();

	protected SessionFactory getSessionFactory() {
		try {
			return (SessionFactory) new InitialContext().lookup("SessionFactory");
		} catch (Exception e) {
			logger.log(Level.SEVERE, "Could not locate SessionFactory in JNDI", e);
			throw new IllegalStateException("Could not locate SessionFactory in JNDI");
		}
	}

	public void persist(Empleado transientInstance) {
		logger.log(Level.INFO, "persisting Empleado instance");
		try {
			sessionFactory.getCurrentSession().persist(transientInstance);
			logger.log(Level.INFO, "persist successful");
		} catch (RuntimeException re) {
			logger.log(Level.SEVERE, "persist failed", re);
			throw re;
		}
	}

	public void attachDirty(Empleado instance) {
		logger.log(Level.INFO, "attaching dirty Empleado instance");
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(instance);
			logger.log(Level.INFO, "attach successful");
		} catch (RuntimeException re) {
			logger.log(Level.SEVERE, "attach failed", re);
			throw re;
		}
	}

	public void attachClean(Empleado instance) {
		logger.log(Level.INFO, "attaching clean Empleado instance");
		try {
			sessionFactory.getCurrentSession().lock(instance, LockMode.NONE);
			logger.log(Level.INFO, "attach successful");
		} catch (RuntimeException re) {
			logger.log(Level.SEVERE, "attach failed", re);
			throw re;
		}
	}

	public void delete(Empleado persistentInstance) {
		logger.log(Level.INFO, "deleting Empleado instance");
		try {
			sessionFactory.getCurrentSession().delete(persistentInstance);
			logger.log(Level.INFO, "delete successful");
		} catch (RuntimeException re) {
			logger.log(Level.SEVERE, "delete failed", re);
			throw re;
		}
	}

	public Empleado merge(Empleado detachedInstance) {
		logger.log(Level.INFO, "merging Empleado instance");
		try {
			Empleado result = (Empleado) sessionFactory.getCurrentSession().merge(detachedInstance);
			logger.log(Level.INFO, "merge successful");
			return result;
		} catch (RuntimeException re) {
			logger.log(Level.SEVERE, "merge failed", re);
			throw re;
		}
	}

	public Empleado findById(int id) {
		logger.log(Level.INFO, "getting Empleado instance with id: " + id);
		try {
			Empleado instance = (Empleado) sessionFactory.getCurrentSession().get("Empleado", id);
			if (instance == null) {
				logger.log(Level.INFO, "get successful, no instance found");
			} else {
				logger.log(Level.INFO, "get successful, instance found");
			}
			return instance;
		} catch (RuntimeException re) {
			logger.log(Level.SEVERE, "get failed", re);
			throw re;
		}
	}

	public List findByExample(Empleado instance) {
		logger.log(Level.INFO, "finding Empleado instance by example");
		try {
			List results = sessionFactory.getCurrentSession().createCriteria("Empleado").add(Example.create(instance))
					.list();
			logger.log(Level.INFO, "find by example successful, result size: " + results.size());
			return results;
		} catch (RuntimeException re) {
			logger.log(Level.SEVERE, "find by example failed", re);
			throw re;
		}
	}
}
