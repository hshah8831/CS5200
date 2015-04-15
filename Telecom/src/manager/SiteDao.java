package manager;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import model.Site;

@Path("/site")
public class SiteDao {
	
	EntityManagerFactory factory = Persistence.createEntityManagerFactory("Telecom");
	EntityManager em = null;

	// fetches Site with siteId
	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Site findSite(@PathParam("id") int siteId) 
	{
		em = factory.createEntityManager();
		em.getTransaction().begin();
		Site site = em.find(Site.class, siteId);
		em.getTransaction().commit();
		return site;
	}
	
	// fetches all the sites in Site.
	@GET
	@Path("/")
	@Produces(MediaType.APPLICATION_JSON)
	@SuppressWarnings("unchecked")
	public List<Site> findAllSites() 
	{
		em = factory.createEntityManager();
		return em.createNamedQuery("Site.findAll").getResultList();
		
	}
	
	 //updates a row, with siteId and site, and fetches the updates database.
	@PUT
	@Path("/{id}/{site}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@SuppressWarnings("unchecked")
	public List<Site> updateSite(@PathParam("id") int siteId,@PathParam("site") Site site) 
	{
		List<Site> sites = new ArrayList<Site>();

		em = factory.createEntityManager();
		em.getTransaction().begin();

		site.setId(siteId);
		em.merge(site);
		
		Query query = em.createNamedQuery("Site.findAll");
		sites = query.getResultList();
		
		em.getTransaction().commit();
		em.close();
		return sites;
	}
	
	//removes a row, with siteId, and fetches the updated database
	@DELETE
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	@SuppressWarnings("unchecked")
	public List<Site> removeSite(@PathParam("id") int siteId) 
	{
		List<Site> sites = new ArrayList<Site>();

		Site site = null;
		
		em = factory.createEntityManager();
		em.getTransaction().begin();
		
		site = em.find(Site.class, siteId);
		em.remove(site);
		
		Query query = em.createNamedQuery("Site.findAll");
		sites = query.getResultList();
		
		em.getTransaction().commit();
		em.close();
		
		return sites;
	}
	
	//removes a row, with siteId, and fetches the updated database
	@POST
	@Path("/{site}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@SuppressWarnings("unchecked")
	public List<Site> createSite(@PathParam("site") Site site) 
	{
		List<Site> sites = new ArrayList<Site>();

		em = factory.createEntityManager();
		em.getTransaction().begin();
		
		em.persist(site);
		Query query = em.createNamedQuery("Site.findAll");
		sites = query.getResultList();
		
		em.getTransaction().commit();
		em.close();
		
		return sites;
	}
	
	
//	public static void main(String[] args) 
//	{
//		SiteDao managesite = new SiteDao();
//		
//		Site site = managesite.findSite(1);
//		
//		List<Site> sites = managesite.findAllSites();
//		
//		for (Site temp : sites) {
//			System.out.println(temp.getName());
//		}
//		
//		//System.out.println(site.getName());
//		
//		Site updsite = new Site();
//		updsite.setId(1);
//		updsite.setName("Site 1-upd");
//		updsite.setLatitude(12.23);
//		updsite.setLongitude(23.34);
//		
//		Site site3 = new Site();
//		//site3.setId(3);
//		site3.setName("Site 4");
//		site3.setLatitude(55.23);
//		site3.setLongitude(55.34);
//		
//		
//		for (Site temp : managesite.removeSite(6)) {
//			System.out.println(temp.getName());
//		}
//	}

}
