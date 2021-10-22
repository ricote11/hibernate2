package Principal;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;


import com.serbatic.course.datamodel.utils.HibernateUtil;

import EjercicioHibernate.EjercicioHibernate.Departamento;
import EjercicioHibernate.EjercicioHibernate.Empleado;
import HibernateDAO.DepartamentoDAO;
import HibernateDAO.EmpleadoDAO;


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
				DepartamentoDAO.insertClient(session, numProviders);
				logger.info("insertar proveedor");
				// Recuperamos y listamos proveedores
				List<Departamento> providers = DepartamentoDAO.getAllClients(session);
				logger.info(String.format("%1$s: number of providers = %2$s.", methodName, providers.size()));
				providers.stream().forEach(x -> logger.info(String.format("%1$s: ---> %2$s.", methodName, x.toString())));
				
				// Actualizamos proveedor 1
				Departamento provider = providers.stream()
	  										 .filter(x -> x.getCodigo() == codigo)
	  										 .findFirst()
	  										 .orElse(null);
				int numEmpl = 7;
				EmpleadoDAO.insertEmpleado(session, numEmpl);
				logger.info("insertar empleados");
				// Recuperamos y listamos proveedores
				List<Empleado> provs = EmpleadoDAO.getAllEmpleado(session);
				logger.info(String.format("%1$s: number of providers = %2$s.", methodName, provs.size()));
				providers.stream().forEach(x -> logger.info(String.format("%1$s: ---> %2$s.", methodName, x.toString())));
				
				// Actualizamos proveedor 1
				Empleado prov = provs.stream()
	  										 .filter(x -> x.getCodigo() == codigo)
	  										 .findFirst()
	  										 .orElse(null);
				
			
		
				tx.commit();
			
	}
		catch (Exception e) {
			  if (tx != null) {
			    tx.rollback();
			  }
				logger.error(String.format("%1$s: error when inserting registries.", methodName), e);
				logger.error("El sistema ha fallado");
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
