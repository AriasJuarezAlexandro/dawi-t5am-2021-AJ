package app;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import model.Usuario;

public class Demo05 {
	
        public static void main(String[] args) {
		
		//obtener laconexion
		EntityManagerFactory fabrica = Persistence.createEntityManagerFactory("mysql");
		
		//crea los dao usando la fabrica
		EntityManager em = fabrica.createEntityManager();
		
		//proceso registrar un nuevo usuario
		Usuario u = em.find(Usuario.class, 10);//devuelve el objeto usuario segun la PK
		
		if(u==null){
			System.out.println("codigo NO existe");
		}
		else{
			em.getTransaction().begin();
			em.remove(u); //para eliminar
			em.getTransaction().commit();
			System.out.println("Eliminacion OK");
		} 
			
		em.close();
	}

}
