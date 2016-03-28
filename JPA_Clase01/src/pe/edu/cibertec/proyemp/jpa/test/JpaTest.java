package pe.edu.cibertec.proyemp.jpa.test;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import pe.edu.cibertec.proyemp.jpa.domain.Departamento;
import pe.edu.cibertec.proyemp.jpa.domain.Empleado;


public class JpaTest {
	
	private EntityManager manager;
	
	public JpaTest(EntityManager manager) {
		this.manager = manager;
	}
	
	public static void main(String[] args) {
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("persistenceUnit");
		EntityManager manager = factory.createEntityManager();
		JpaTest test = new JpaTest(manager);
		
		EntityTransaction tx = manager.getTransaction();
		tx.begin();
		try {
			test.crearEmpleados2();
		} catch (Exception e) {
			e.printStackTrace();
		}
		tx.commit();

		test.listarEmpleados();

		System.out.println(".. done");
		
	    test.mostrarEMpleadoId1();

		
	}
	
	private void crearEmpleados() {
		int nroDeEmpleados = manager.createQuery("Select a From Empleado a", Empleado.class).getResultList().size();
		if (nroDeEmpleados == 0) {
			Departamento departamento = new Departamento("Java");
			manager.persist(departamento);

			manager.persist(new Empleado("Bob",departamento));
			manager.persist(new Empleado("Mike",departamento));

		}
	}
	
	private void crearEmpleados2() {
		Departamento departamento = new Departamento("Java");
		
		Empleado emp1 = new Empleado("Bob");
		Empleado emp2 = new Empleado("Mike");
		
		List<Empleado> empleados = new ArrayList<Empleado>();
		empleados.add(emp1);
		empleados.add(emp2);
		
		
		
		departamento.setEmpleados(empleados);
		manager.persist(departamento);
	}
	
	private void listarEmpleados() {
		List<Empleado> resultList = 
				manager.createQuery("Select a From Empleado a", Empleado.class).getResultList();
		System.out.println("nro de empleados:" + resultList.size());
		for (Empleado next : resultList) {
			System.out.println("siguiente empleado: " + next);
		}
	}
	
	private void mostrarEMpleadoId1(){
	//	Empleado emp = manager.createQuery(""+"Select a From Empleado a where a.id=1",
	//			Empleado.class).getSingleResult();
		Empleado emp = manager.find(Empleado.class,new Long(1));
		System.out.println(emp);
		
	}


}
