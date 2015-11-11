package dao;

import java.io.File;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

import model.Site;
import model.SiteList;

public class SiteDAO 
{
	EntityManagerFactory factory = Persistence.createEntityManagerFactory("Test-Telecom");
	EntityManager em = null;

	public Site findSite(int siteId) 
	{
		em = factory.createEntityManager();
		em.getTransaction().begin();
		Site site = em.find(Site.class, siteId);
		em.getTransaction().commit();
		return site;
	}
	
	@SuppressWarnings("unchecked")
	public List<Site> findAllSites() 
	{
		em = factory.createEntityManager();
		return em.createNamedQuery("Site.findAll").getResultList();
	}
	
	public void exportSiteDatabaseToXmlFile(SiteList siteList, String xmlFileName) 
	{
		try 
		{
			JAXBContext jaxb = JAXBContext.newInstance(SiteList.class);
			Marshaller marshaller = jaxb.createMarshaller();
			marshaller.marshal(siteList, new File(xmlFileName));
		} catch (JAXBException e) 
		{
			e.printStackTrace();
		}
	}

	public void convertXmlFileToOutputFile(String inputXmlFileName, String outputXmlFileName, String xsltFileName)
	{
		File inputFile = new File(inputXmlFileName);
		File xltFile = new File(xsltFileName);
		File outputFile = new File(outputXmlFileName);
		
		StreamSource inputSource = new StreamSource(inputFile);
		StreamSource xltSource = new StreamSource(xltFile);
		StreamResult outputResult = new StreamResult(outputFile);
		
		TransformerFactory factory = TransformerFactory.newInstance();
		try {
			Transformer transformer = factory.newTransformer(xltSource);
			transformer.transform(inputSource, outputResult);
		} catch (TransformerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public static void main(String[] args) 
	{
		SiteDAO dao = new SiteDAO();
		List<Site> sites = dao.findAllSites();
		SiteList sitelist = new SiteList();
		sitelist.setSites(sites);
//		dao.exportSiteDatabaseToXmlFile(sitelist, "sites.xml");
		
		dao.convertXmlFileToOutputFile("sites.xml", "sites2equipment.html", "sites2equipment.xsl");

	}
}
