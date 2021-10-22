package HibernateDAO;

import java.util.List;

import javax.swing.JOptionPane;

import org.hibernate.Session;


import com.serbatic.course.datamodel.utils.HibernateUtil;
import com.serbatic.course.datamodel.utils.StringUtil;

import EjercicioHibernate.EjercicioHibernate.Departamento;


public class DepartamentoDAO {
	
	  public static void insertClients(Session s, int numClients) {
		    for (int id = 10; id <= numClients; id++) {
		      insertClient(s, id);
		    }
		  }
		  
			public static void insertClient(Session s, int id) {
				int probabilidad = (int)Math.random()*100;
				int codigo = Integer.parseInt(JOptionPane.showInputDialog("introduce un codigo"));
				String nombre = JOptionPane.showInputDialog("Introducir nombre");
				int codResponsable = Integer.parseInt(JOptionPane.showInputDialog("introduce un codigo de responsable"));
			
				
				Departamento departamento = new Departamento(codigo,nombre,codResponsable);
				s.save(departamento);
			}

			// hql queries
			public static List<Departamento> getAllClients() {
				return getAllClients(HibernateUtil.retrieveSession());
			}
			
			public static List<Departamento> getAllClients(Session s) {
				String hQuery = "from Departamento";
				List<Departamento> deparmentList = s.createQuery(hQuery, Departamento.class)
						   	   			           .list();
				return deparmentList;
			}
			
			/*public static Departamento getClient(Session s, int codigo) {
			  String hQuery = " from Departamento d " +
			                  " where d.codigo = :codigo";
			  Departamento departamento = s.createQuery(hQuery, Departamento.class)
			                   .setParameter("codigo", codigo)
			                   .setMaxResults(1)
			                   .uniqueResult();
		    return departamento;
			}
			*/
			public static Departamento getClient(Session s, int codigo) {
				  
			    return s.get(Departamento.class, codigo);
		
			}	
}
