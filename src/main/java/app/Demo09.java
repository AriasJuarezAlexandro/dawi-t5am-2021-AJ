package app;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import model.Usuario;

public class Demo09 {
public static void main(String[] args) {
		
		//obtener laconexion
		EntityManagerFactory fabrica = Persistence.createEntityManagerFactory("mysql");
		
		//crea los dao usando la fabrica
		EntityManager em = fabrica.createEntityManager();
		
		//procedimiento almacenado
        String sql2="{call usp_validaAccesoA(?, ?)}";
		
		//TypedQuery<Usuario> query = em.createQuery(sql2, Usuario.class);
		Query query =em.createNativeQuery(sql2, Usuario.class);
		query.setParameter(1, "U002@gmail.com");
		query.setParameter(2, "10002");
		
		Usuario u = null;
		try {
			u=(Usuario) query.getSingleResult();
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
