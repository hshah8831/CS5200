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
import javax.xml.bind.*;
import javax.xml.transform.*;
import javax.xml.transform.stream.*;

import java.io.*;

import model.Site;
import model.Sites;

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
	
	public void exportSiteDatabaseToXmlFile(Sites sites, String xmlFileName) {
		File xmlFile = new File(xmlFileName);
		try {
			JAXBContext jaxb = JAXBContext.newInstance(Sites.class);
			Marshaller marshaller = jaxb.createMarshaller();
			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			marshaller.marshal(sites, xmlFile);
		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	public void convertXmlFileToOutputFile(
			String directorsXmlFileName,
			String outputFileName,
			String xsltFileName)
	{
		File inputXmlFile = new File(directorsXmlFileName);
		File outputXmlFile = new File(outputFileName);
		File xsltFile = new File(xsltFileName);
		
		StreamSource source = new StreamSource(inputXmlFile);
		StreamSource xslt    = new StreamSource(xsltFile);
		StreamResult output = new StreamResult(outputXmlFile);
		
		TransformerFactory factory = TransformerFactory.newInstance();
		try {
			Transformer transformer = factory.newTransformer(xslt);
			transformer.transform(source, output);
		} catch (TransformerConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TransformerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		SiteDao dao = new SiteDao();
		
		Site site = dao.findSite(1);
		System.out.println(site.getName());
		
		List<Site> sites = dao.findAllSites();
		for(Site sit:sites) {
			System.out.println(sit.getName());
		}
		
		Sites theSites = new Sites();
		theSites.setSites(sites);
		
		dao.exportSiteDatabaseToXmlFile(theSites, "xml/sites.xml");
		
		dao.convertXmlFileToOutputFile("xml/sites.xml", "xml/sites.html", "xml/sites2html.xslt");
		dao.convertXmlFileToOutputFile("xml/sites.xml", "xml/equipments.html", "xml/sites2equipment.xslt");
	}
}
