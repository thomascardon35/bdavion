package fr.cardon.model;

import java.sql.Time;

public class Vol{
	private String id;
	private Avion avion;
	private Time heureArrivee;
	private Time heureDepart;
	
	private Pilote pilote;
	private String siteArrivee;
	private String siteDepart;
	
	public Vol(){super();}

	public Vol(String id, Avion avion, Pilote pilote, Time heureDepart, Time heureArrivee, 
			String siteDepart, String siteArrivee) {
		super();
		this.id = id;
		this.avion = avion;
		this.heureArrivee = heureArrivee;
		this.heureDepart = heureDepart;
		this.pilote = pilote;
		this.siteArrivee = siteArrivee;
		this.siteDepart = siteDepart;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Avion getAvion() {
		return avion;
	}

	public void setAvion(Avion avion) {
		this.avion = avion;
	}

	public Time getHeureArrivee() {
		return heureArrivee;
	}

	public void setHeureArrivee(Time heureArrivee) {
		this.heureArrivee = heureArrivee;
	}

	public Time getHeureDepart() {
		return heureDepart;
	}

	public void setHeureDepart(Time heureDepart) {
		this.heureDepart = heureDepart;
	}

	public Pilote getPilote() {
		return pilote;
	}

	public void setPilote(Pilote pilote) {
		this.pilote = pilote;
	}

	public String getSiteArrivee() {
		return siteArrivee;
	}

	public void setSiteArrivee(String siteArrivee) {
		this.siteArrivee = siteArrivee;
	}

	public String getSiteDepart() {
		return siteDepart;
	}

	public void setSiteDepart(String siteDepart) {
		this.siteDepart = siteDepart;
	}

	
	@Override
	public String toString() {
		return "Vol id : " + id + ", avion N° " + avion.getId() + ", Constructeur et Modèle de l'Avion : " + avion.getConstructeur() + " " + avion.getModele() + " " +", Capacité de l'Avion : " + avion.getCapacite() + ", Site de Rattachement de l'avion : " + avion.getSite() + ", heureArrivee: " + heureArrivee + ", heureDepart: " + heureDepart
				+ ", pilote: " + pilote + ", siteArrivee: " + siteArrivee + ", siteDepart: " + siteDepart;
	}
	
	

}
