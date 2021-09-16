package app;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import model.Usuario;

public class Demo03 {
	
	public static void main(String[] args) {
		//obtener laconexion
		EntityManagerFactory fabrica = Persistence.createEntityManagerFactory("mysql");
		//crea los dao usando la fabrica
		EntityManager em = fabrica.createEntityManager();
		//proceso eliminar un nuevo usuario
		Usuario u = new Usuario();
		u.setCodigo(10);
		
		//para reg, act, eli = transaccion
		em.getTransaction().begin();
		em.remove(u); //para eliminar
		em.getTransaction().commit();
		System.out.println("Eliminacion OK");
		em.close();
	}

}
