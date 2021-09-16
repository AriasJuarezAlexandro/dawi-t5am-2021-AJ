package app;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import model.Producto;

public class Demo1 {
	
	public static void main(String[] args) {
		
		EntityManagerFactory fabrica = Persistence.createEntityManagerFactory("mysql");
		
		EntityManager em = fabrica.createEntityManager();
		
		Producto p = new Producto();
		p.setIdprod("P0036");
		p.setDescripcion("Jamesito");
		p.setStock(10);
		p.setPrecio(2.00);
		p.setIdcategoria(1);
		p.setEstado(1);
		
		em.getTransaction().begin();
		em.persist(p); //para registrar
		em.getTransaction().commit();
		System.out.println("Registro OK");
		em.close();
	}
}
