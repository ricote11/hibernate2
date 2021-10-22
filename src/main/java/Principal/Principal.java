package Principal;

import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.serbatic.course.datamodel.utils.HibernateUtil;

import EjercicioHibernate.EjercicioHibernate.Departamento;
import HibernateDAO.DepartamentoDAO;


public class Principal {
	
	private static Logger logger = LogManager.getLogger(Principal.class);
	static SessionFactory sessionFactory;
	
	public static void main(String[] args) {
		String methodName = Principal.class.getSimpleName() + ".main()";
		logger.info(String.format("%1$s: >>>>>> Main execution started.", methodName));
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;
		int codigo = 1;
		try {
			  // Las opereaciones save/update/delete
				tx = session.beginTransaction();
				
				// Insertamos proveedores
				int numProviders = (int)Math.random()*100;;
				DepartamentoDAO.insertClients(session, numProviders);
				
				// Recuperamos y listamos proveedores
				List<Departamento> providers = DepartamentoDAO.getAllClients(session);
				logger.info(String.format("%1$s: number of providers = %2$s.", methodName, providers.size()));
				providers.stream().forEach(x -> logger.info(String.format("%1$s: ---> %2$s.", methodName, x.toString())));
				
				// Actualizamos proveedor 1
				Departamento provider = providers.stream()
	  										 .filter(x -> x.getCodigo() == codigo)
	  										 .findFirst()
	  										 .orElse(null);
				
	}
		catch (Exception e) {
			  if (tx != null) {
			    tx.rollback();
			  }
				logger.error(String.format("%1$s: error when inserting registries.", methodName), e);
			}
			finally {
				if (session != null) {
					session.close();
				}
			}
			
			// Abrimos nueva sesión y recuperamos el proveedor 1 para comprobar que se ha actualizado
		/*	session = HibernateUtil.getSessionFactory().openSession();
			Departamento modifiedProvider = DepartamentoDAO.getClient(session, codigo);
			logger.info(String.format("%1$s: ---> %2$s.", methodName, modifiedProvider.toString()));
			session.close();
			*/
			logger.info(String.format("%1$s: >>>>>> Main execution finished.", methodName));
		}	
}
