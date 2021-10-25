package fr.cardon.bdavion;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;

import com.mysql.cj.x.protobuf.MysqlxDatatypes.Array;


/** 	on pourrait appeler cette classe GestionJDBC
 * 		son rôle consiste à gérer la connection à la Base
 * 		de données bd-avion ou une autre base de données MySQL.
 */
public class AccesBD {


	private static String utilisateur="root";
	private static String motDePasse="root";
	private static String pilote = "com.mysql.cj.jdbc.Driver";
	private static String url = "jdbc:mysql://localhost:3308/avion?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
	private static Connection connexion=null;

	/**
	 * Méthode qui retourne un objet de type Connection
	 * @return Connection
	 */
	public synchronized static Connection getConnection()
	{
		try
		{
			Class.forName(pilote);
			if (connexion==null) connexion = DriverManager.getConnection(url, utilisateur, motDePasse);


		}
		catch (ClassNotFoundException cnfe)
		{
			System.out.println(cnfe);
			JOptionPane.showMessageDialog(null,"Pilote non valide ou introuvable !","AccesBD",JOptionPane.WARNING_MESSAGE);
		}
		catch (Exception e)
		{
			System.out.println(e);
			JOptionPane.showMessageDialog(null,e.getMessage(),"Connexion à la Base MySQL Impossible !",JOptionPane.WARNING_MESSAGE);
			System.exit(-1);
		}

		return connexion;
	}


	/**
	 *	Méthode d'exécution d'une requête (SELECT) pour renvoyer un objet de type ResultSet
	 *  Cette méthode existe sous le nom : executeQuery()
	 *	@param requete String
	 *	@return resultat ResultSet
	 */
	public static ResultSet executerQuery(String requete) throws SQLException

	{
		/*
		 * 	On déclare un objet de type Statement que l'on nomme instruction. Cet
		 * 	objet soumet la requête à la base de données dans MySQL.
		 * 	On déclare un objet de type ResultSet que l'on nomme resultat. cet objet
		 * 	est retourné pour encapsuler les résultats de la requête. Il va nous permettre
		 * 	de manipuler les résultats de la requête. Les afficher, les stocker dans des objets,...
		 *
		 */

		// Déclaration de nos variables
		Statement statement = null;
		ResultSet resultat = null;

		try {
			/*
			 * Paramètres ajouté pour la gestion des curseurs pour la navigation dans un ResultSet
			 * TYPE_SCROLL_INSENSITIVE :
			 * Cette valeur indique que le curseur peut être déplacé dans les deux sens,
			 * mais aussi arbitrairement (de manière absolue ou relative).
			 * Le terme insensitive indique que le ResultSet est insensible aux modifications
			 * des valeurs dans la base de données. Cela définit en fait une vue statique des données
			 * contenues dans le ResultSet.
			 *
			 * CONCUR_UPDATABLE :
			 * Cette valeur indique que l'on peut modifier les données de la base via le ResultSet.
			 */

			int type = ResultSet.TYPE_SCROLL_SENSITIVE;
			int mode = ResultSet.CONCUR_UPDATABLE;

			/* 	On peut traduire Statement par ordre ou instruction.
			 * 	La méthode createStatement() nous retourne un objet de type Statement.
			 * 	Nous l'avons appelé avec la méthode getConnection() qui nous renvoie
			 * 	un objet de type Connection nommé connexion.
			 * 	Dés lors, nous pouvons utiliser l'objet instruction pour interroger
			 * 	la base de données.
			 *
			 */
			statement = getConnection().createStatement(type,mode);
			/*	Pour cela, il nous suffit d'appeler la méthode executeQuery() en lui passant
			 * 	comme paramètre, la requete que nous voulons exécuter.
			 * 	L'objet resultat contient le résultat de l'exécution de la requête.
			 */
			resultat = statement.executeQuery(requete);

		}
		catch(SQLException sqle)
		{
			System.out.println("Problème dans la requête SQL !");
			sqle.printStackTrace();
		}
		/*finally {
			statement.close();
			resultat.close();
			connexion.close();
		}*/
		return resultat; // retourne un ResultSet


	}

	/**
	 *	Méthode d'exécution d'une requete Update (UPDATE, INSERT, DELETE ou CREATE ou DROP).
	 *  Elle ne renvoie rien
	 *	@param requete String
	 */
	public static void executerUpdate(String requete, Object params[]) throws SQLException{

//		Statement statement = null;
		PreparedStatement PreparedStatement = null;
		 
		try {
//
//			statement = getConnection().createStatement();
//			int i = statement.executeUpdate(requete);
			
			 PreparedStatement = getConnection().prepareStatement(requete);
			 
			 for(int index = 0 ; index < params.length; index++) {
				 PreparedStatement.setObject(index+1, params[index]);
			 }
			 int i = PreparedStatement.executeUpdate();


			if (i==1) // on affiche un message d'information sur l'opération pour le plaisir !

			{
				JOptionPane.showMessageDialog(null, "L'opération a réussie !");
			}
			else
			{
				JOptionPane.showMessageDialog(null, "L'opération a échoué !");
			}

		}

		catch(SQLException sqle)
		{
			System.out.println("Problème dans la requête SQL !");
			sqle.printStackTrace();
		}
//		finally {
//
//			statement.close();
//			connexion.close();
//
//		}

	}

	/**
	 *
	 * @param unUtilisateur String
	 */
	public static void setUtilisateur(String unUtilisateur)
	{
		utilisateur=unUtilisateur;
	}
	/**
	 *
	 * @param unMotDePasse String
	 */
	public static void setMotDePasse(String unMotDePasse)
	{
		motDePasse=unMotDePasse;
	}

	/**
	 *
	 * @return
	 */
	public static String getMotDePasse() {
		return motDePasse;
	}
	/**
	 *
	 * @return
	 */
	public static String getPilote() {
		return pilote;
	}
	/**
	 *
	 * @return
	 */
	public static String getUrl() {
		return url;
	}
	/**
	 *
	 * @return
	 */
	public static String getUtilisateur() {
		return utilisateur;
	}

	/**
	 *  test de la connection avec méthode main()
	 */

	public static void main(String[] args) throws SQLException
	{
		System.out.println("objet connexion = "+AccesBD.getConnection());
		if (AccesBD.connexion!=null)
		{
			JOptionPane.showMessageDialog(null, "ça marche");
			// on teste une requête :
				Statement st = AccesBD.connexion.createStatement();
				ResultSet resultat = st.executeQuery("SELECT * FROM pilote ORDER BY pi_nom");

				while(resultat.next())
				{
					System.out.print(resultat.getInt("pi_id")+"  ");
					System.out.print(resultat.getString("pi_nom")+ "  ");
					System.out.println(resultat.getString("pi_site"));
				}
		}
		else
		{
			JOptionPane.showMessageDialog(null, "ça marche PAS !");
		}
	}

}