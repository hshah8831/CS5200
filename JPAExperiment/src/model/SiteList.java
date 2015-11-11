package model;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class SiteList {

	private List<Site> sites;

	@XmlElement(name="site")
	public List<Site> getSites() {
		return sites;
	}

	public SiteList(List<Site> sites) {
		super();
		this.sites = sites;
	}

	public void setSites(List<Site> sites) {
		this.sites = sites;
	}

	public SiteList() {
		super();
	}
}
