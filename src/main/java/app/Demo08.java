package app;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import model.Usuario;

public class Demo08 {
	
        public static void main(String[] args) {
		
		//obtener laconexion
		EntityManagerFactory fabrica = Persistence.createEntityManagerFactory("mysql");
		
		//crea los dao usando la fabrica
		EntityManager em = fabrica.createEntityManager();
		
		//validar un usuario segun su usuario y clave
        String sql2="select u from Usuario u where u.usuario=:xusr and u.clave=:xcla";
		
		TypedQuery<Usuario> query = em.createQuery(sql2, Usuario.class);
		query.setParameter("xusr", "U002@gmail.com");
		query.setParameter("xcla", "10002");
		
		Usuario u = null; 
		try {
			u=query.getSingleResult();
		} catch (Exception e) {
			
		}		
		
		if(u==null){
			System.out.println("codigo NO existe");
		}
		else{
			System.out.println("Bienvenido: " + u.getNombre()+ " " +u.getApellido());
			System.out.println(u);
		} 
			
		em.close();
	}

}
