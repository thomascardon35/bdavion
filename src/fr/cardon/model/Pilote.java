package fr.cardon.model;

public class Pilote {
	
	private int id;
	private String nom;
	private String site;
	

	public Pilote(){super();}
	
	
	public Pilote(int id, String nom, String site) {
		super();
		this.id = id;
		this.nom = nom;
		this.site = site;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getSite() {
		return site;
	}
	public void setSite(String site) {
		this.site = site;
	}
	@Override
	public String toString() {
		return "Pilote id : " + id + ", nom : " + nom + ", site : " + site;
	}
	
	
	
}
