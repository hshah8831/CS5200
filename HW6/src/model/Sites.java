package model;

import java.util.List;

public class Sites
{
	private List<Site> sites;

	public Sites(List<Site> sites) {
		super();
		this.sites = sites;
	}
	
	public Sites() {
		
	}

	public List<Site> getSites() {
		return sites;
	}

	public void setSites(List<Site> sites) {
		this.sites = sites;
	}
}
