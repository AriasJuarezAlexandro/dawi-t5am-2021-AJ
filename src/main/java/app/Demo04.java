package app;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import model.Usuario;

public class Demo04 {

	public static void main(String[] args) {
		
		//obtener laconexion
		EntityManagerFactory fabrica = Persistence.createEntityManagerFactory("mysql");
		
		//crea los dao usando la fabrica
		EntityManager em = fabrica.createEntityManager();
		
		//proceso buscar un usuario
		Usuario u = em.find(Usuario.class, 5);//devuelve el objeto usuario segun la PK
		
		if(u==null){
			System.out.println("codigo NO existe");
		}
		else{
			System.out.println("Bienvenido: " + u.getNombre());
			System.out.println(u);
		} 
			
		em.close();
	}
}
