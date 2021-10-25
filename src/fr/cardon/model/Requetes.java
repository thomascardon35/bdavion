package fr.cardon.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.util.ArrayList;

import fr.cardon.bdavion.AccesBD;

public class Requetes {
	
	public static ArrayList<Avion> getAllAvions() throws SQLException{
		
		ArrayList<Avion> avions = new ArrayList();
		String requete = "SELECT * FROM avion";
		ResultSet resultat = AccesBD.executerQuery(requete);
		
		
		while(resultat.next()) {
			
			Avion avion = new Avion();
			avion.setId(resultat.getInt("AV_ID"));
			avion.setConstructeur(resultat.getString("AV_CONST"));
			avion.setModele(resultat.getString("AV_MODELE"));
			avion.setCapacite(resultat.getInt("AV_CAPACITE"));
			avion.setSite(resultat.getString("AV_SITE"));
			avions.add(avion);
			
		}
		return avions;
	}
	
	
	public static ArrayList<Pilote> getAllPilotes() throws SQLException{
		ArrayList<Pilote> pilotes = new ArrayList();
		String req = "SELECT * FROM pilote";
		ResultSet result = AccesBD.executerQuery(req);
		
		result.next();
		Pilote pilote = new Pilote();
		pilote.setId(result.getInt("PI_ID"));
		pilote.setNom(result.getString("PI_NOM"));
		pilote.setSite(result.getString("PI_SITE"));
		pilotes.add(pilote);
		
		
		return pilotes;
		
	}
	
	public static Avion getAvionById(int id) throws SQLException {
		String req = "SELECT * FROM avion WHERE AV_ID =" + id;
		ResultSet resultat = AccesBD.executerQuery(req);

		Avion avion = new Avion();
		
		resultat.next();
		avion.setId(resultat.getInt("AV_ID"));
		avion.setConstructeur(resultat.getString("AV_CONST"));
		avion.setModele(resultat.getString("AV_MODELE"));
		avion.setCapacite(resultat.getInt("AV_CAPACITE"));
		avion.setSite(resultat.getString("AV_SITE"));
		
		
		return avion;
		
	}
	
	public static Pilote getPiloteById(int id) throws SQLException {
		String req = "SELECT * FROM pilote WHERE PI_ID =" + id;
		ResultSet resultat = AccesBD.executerQuery(req);

		Pilote pilote = new Pilote();
		
		while(resultat.next()) {
			pilote.setId(resultat.getInt("PI_ID"));
			pilote.setNom(resultat.getString("PI_NOM"));
			pilote.setSite(resultat.getString("PI_SITE"));
		}
		
		return pilote;
		
	}
	
	public static ArrayList<Vol> getAllVols() throws SQLException{
		ArrayList<Vol> vols = new ArrayList();
		String req = "SELECT * FROM vol";
		ResultSet result = AccesBD.executerQuery(req);
		
		while(result.next()) {
			Vol vol = new Vol();
			vol.setId(result.getString("VO_ID"));
			vol.setAvion(Requetes.getAvionById(result.getInt("VO_AVION")));
			vol.setPilote(Requetes.getPiloteById(result.getInt("VO_PILOTE")));
			vol.setSiteDepart(result.getString("VO_SITE_DEPART"));
			vol.setSiteArrivee(result.getString("VO_SITE_ARRIVEE"));
			vol.setHeureDepart(result.getTime("VO_HEURE_DEPART"));
			vol.setHeureArrivee(result.getTime("VO_HEURE_ARRIVEE"));
			vols.add(vol);
		}
		
		return vols;
		
	}
	
	
	public static void ajouterAvion(Avion avion) throws SQLException {
		
		/*	*	*	*	*	*	*	*	*	*	*	*	*	*
		 * 													*
		 * 1ere possibilité peu sécurisée (injection sql)	*
		 * 													*
		 *	*	*	*	*	*	*	*	*	*	*	*	*	*/
		
//		String req = "INSERT INTO avion VALUES (" + avion.getId() + ",'" 
//				+ avion.getConstructeur() + "','" + avion.getModele() + "'," 
//				+ avion.getCapacite() + ",'" + avion.getSite() + "')"  ;
//		
//		AccesBD.executerUpdate(req);
		
		
		
		/*	*	*	*	*	*	*	*	*	*	*	*	*	*
		 * 													*
		 * 			2ème possibilité sans boucle 			*
		 * 													*
		 *	*	*	*	*	*	*	*	*	*	*	*	*	*/
		
//		PreparedStatement PreparedStatement = AccesBD.getConnection().prepareStatement(req);
//		PreparedStatement.setInt(1, avion.getId());
//		PreparedStatement.setString(2, avion.getConstructeur());
//		PreparedStatement.setString(3, avion.getModele());
//		PreparedStatement.setInt(4, avion.getCapacite());
//		PreparedStatement.setString(5, avion.getSite());
//		PreparedStatement.executeUpdate();
		
		
		
		String req = "INSERT INTO avion VALUES (?,?,?,?,?)";
		Object params[]= {avion.getId(),avion.getConstructeur(),avion.getModele(),
						  avion.getCapacite(),avion.getSite()};
		AccesBD.executerUpdate(req, params);
		
	}
	
	
	public static void  ajouterPilote(Pilote pilote) throws SQLException{
        PreparedStatement PreparedStatement = AccesBD.getConnection().prepareStatement("INSERT INTO PILOTE VALUES (? , ? , ?)");
        PreparedStatement.setInt(1, pilote.getId());
        PreparedStatement.setString(2, pilote.getNom());
        PreparedStatement.setString(3, pilote.getSite());
        PreparedStatement.executeUpdate();

    }
	
	public static void  ajouterVol(Vol vol) throws SQLException{
        PreparedStatement PreparedStatement = AccesBD.getConnection().prepareStatement("INSERT INTO vol VALUES (? , ? , ? , ? , ? , ? , ?)");
        

        PreparedStatement.setString(1, vol.getId());
        PreparedStatement.setInt(2, vol.getAvion().getId());
        PreparedStatement.setInt(3, vol.getPilote().getId());
        PreparedStatement.setString(4, vol.getSiteDepart());
        PreparedStatement.setString(5, vol.getSiteArrivee());
        PreparedStatement.setTime(6, vol.getHeureDepart());
        PreparedStatement.setTime(7, vol.getHeureArrivee());
        PreparedStatement.executeUpdate();
		
		
	}
	
	
	public static void supprimerAvionById(int id) throws SQLException {
		PreparedStatement PreparedStatement = AccesBD.getConnection().prepareStatement("DELETE FROM avion WHERE AV_ID = (?)");
		
        PreparedStatement.setInt(1, id);
        PreparedStatement.executeUpdate();
	
	}
	
	
	
	public static void main(String[] args) throws SQLException{
		
//		for (Avion avion : Requetes.getAllAvions()) {
//			System.out.println(avion);
//		}
		
//		for (Pilote pilote : Requetes.getAllPilotes()) {
//			System.out.println(pilote);
//			
//		}
		
//		for (Vol vol : Requetes.getAllVols()) {
//			System.out.println(vol);
//		}
		
//		System.out.println(Requetes.getAvionById(111));

//		ajouterAvion(new Avion (114, "BOEING","B828",250, "Nice"));
		
//		ajouterPilote(new Pilote(10, "Tristan", "Rennes"));
		
//		ajouterVol(new Vol("IT112",getAvionById(108),getPiloteById(5),Time.valueOf("07:00:00"),
//				   Time.valueOf("09:30:00"),"Nantes", "Nice"));
		
//		supprimerAvionById(111);
		
	}

}
