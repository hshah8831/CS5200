package model;

import java.io.Serializable;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import java.util.List;


/**
 * The persistent class for the site database table.
 * 
 */
@XmlRootElement
@Entity
@NamedQuery(name="Site.findAll", query="SELECT s FROM Site s")
public class Site implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	private double latitude;

	private double longitude;

	private String name;

	//bi-directional many-to-one association to Tower
	@OneToMany(mappedBy="site")
	private List<Tower> towers;

	public Site() {
	}

	@XmlAttribute
	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@XmlAttribute
	public double getLatitude() {
		return this.latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	@XmlAttribute
	public double getLongitude() {
		return this.longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	@XmlAttribute
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@XmlElement(name="tower")
	public List<Tower> getTowers() {
		return this.towers;
	}

	public void setTowers(List<Tower> towers) {
		this.towers = towers;
	}

	public Tower addTower(Tower tower) {
		getTowers().add(tower);
		tower.setSite(this);

		return tower;
	}

	public Tower removeTower(Tower tower) {
		getTowers().remove(tower);
		tower.setSite(null);

		return tower;
	}

}