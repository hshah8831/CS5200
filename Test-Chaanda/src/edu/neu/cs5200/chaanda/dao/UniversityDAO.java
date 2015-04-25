package edu.neu.cs5200.chaanda.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import edu.neu.cs5200.chaanda.models.University;

public class UniversityDAO {

	EntityManagerFactory factory = Persistence.createEntityManagerFactory("Test-Chaanda");
	EntityManager em = null;
	
	//Getting registered university
		public List<University> getRegisteredUniversity()
		{
			em = factory.createEntityManager();
			
			@SuppressWarnings("unchecked")
			List<University> lou = em.createQuery( "SELECT u FROM University u WHERE u.registered = 1").getResultList();
						
			return lou;
		}
	// Getting university from name
		@SuppressWarnings("unchecked")
		public List<University> getUniversity(String universityName)
		{
			em = factory.createEntityManager();
			return em.createQuery(
				    "SELECT u FROM University u WHERE u.universityName = :name")
				    .setParameter("name", universityName)
				    .setMaxResults(10)
				    .getResultList();
				
		}
		
		// Getting university from name
				@SuppressWarnings("unchecked")
		public List<University> getUniversityFromName(String universityName)
				{
					em = factory.createEntityManager();
					
					List<University> unis = 
					 em.createQuery(
						    "SELECT u FROM University u WHERE u.universityName = :name")
						    .setParameter("name", universityName)
						    .setMaxResults(10)
						    .getResultList();
					
					return unis;						
				}
		
		// get university details of logged in admin
		@SuppressWarnings("unchecked")
		public University getUniversityforadmin(int personId)
		{
			em = factory.createEntityManager();
		String query = "SELECT u FROM Universityadminmapping um , University u WHERE um.university.universityId = u.universityId AND  "
		+ "um.person.personId LIKE '%"
		+ personId
		+ "%'";
		List<University> unis = em.createQuery(query).getResultList();
		return unis.get(0);

		}
		
		public void registerUniversity(Integer uId , University uni){
			em = factory.createEntityManager();
			em.getTransaction().begin();
			
			uni.setUniversityId(uId);
			
			em.merge(uni);
			em.getTransaction().commit();
			em.close();
			
		}
		
		public static void main(String[] args)
		{
			UniversityDAO dao = new UniversityDAO();
			
			//List<model.University[]> unis = dao.registeredUniversity();
			
//			while (unis.hasNext()) {
//				System.out.println(CrunchifyIterator.next());
//			}
			
			//List<University> unis = dao.getUniversity("NEU");
			//List<University> unis1 = dao.registeredUniversity();
			
//			System.out.println(uni.getUniversityId());
//			System.out.println(uni.getUniversityName());
//			System.out.println(uni.getUniversityAddressId());
//			System.out.println(uni.getBankaccount());

			/*for (University temp : unis) {
				System.out.println(temp.getUniversityId());
				System.out.println(temp.getUniversityName());
				System.out.println(temp.getUniversityAddressId());
				System.out.println(temp.getBankaccount());
			}*/
			
			/*for (University temp : unis1) {
				System.out.println(temp.getUniversityId());
				System.out.println(temp.getUniversityName());
				System.out.println(temp.getUniversityAddressId());
				System.out.println(temp.getBankaccount());
			}*/
			
			University u = dao.getUniversityforadmin(2);
			System.out.println(u.getUniversityName());
		}
}
