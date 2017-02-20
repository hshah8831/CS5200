package edu.neu.cs5200.chaanda.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import edu.neu.cs5200.chaanda.models.Role;


public class RoleDAO {
 public static void main(String[] args)
	 {
		 EntityManagerFactory emf = Persistence.createEntityManagerFactory("Test-Chaanda");
		 EntityManager em = emf.createEntityManager();
		 Role role = new Role();
		 role = em.find(Role.class , 1);
		 System.out.println(role.getRoleName());
	 }
}
