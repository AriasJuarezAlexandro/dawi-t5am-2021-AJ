package app;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import model.Usuario;

public class Demo06 {

public static void main(String[] args) {
		
		//obtener laconexion
		EntityManagerFactory fabrica = Persistence.createEntityManagerFactory("mysql");
		
		//crea los dao usando la fabrica
		EntityManager em = fabrica.createEntityManager();
		
		//Listar todos los usuarios
		String sql="select u from Usuario u";
		List<Usuario> lstusuarios = em.createQuery(sql, Usuario.class).getResultList();
		System.out.println("nro de usuarios: " +lstusuarios.size());
		for(Usuario u : lstusuarios){
			System.out.println(">>>>"+u);
		}
		
		/*System.out.println("Listado de los usuarios x tipo");
		//Listar todos los usuarios
		String sql2="select u from Usuario u where u.tipo=:xtipo";
		
		TypedQuery<Usuario> query = em.createQuery(sql2, Usuario.class);
		query.setParameter("xtipo", 1);
		
		List<Usuario> lstusuarios2 = em.createQuery(sql2, Usuario.class).setParameter("xtipo", 1).getResultList();
		
		System.out.println("nro de usuarios: " +lstusuarios2.size());
		for(Usuario u : lstusuarios2){
			System.out.println(">>>>"+u);
		}*/
		
		
		System.out.println("Listado de los usuarios x tipo");
		//Listar todos los usuarios
		String sql2="select u from Usuario u where u.tipo=:xtipo";
		
		TypedQuery<Usuario> query = em.createQuery(sql2, Usuario.class);
		query.setParameter("xtipo", 1);
		
		List<Usuario> lstusuarios2 = query.getResultList();
		
		System.out.println("nro de usuarios: " +lstusuarios2.size());
		for(Usuario u : lstusuarios2){
			System.out.println(">>>>"+u);
		}
		
		em.close();
	}
}
