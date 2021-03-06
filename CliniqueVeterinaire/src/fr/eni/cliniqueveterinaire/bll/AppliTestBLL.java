package fr.eni.cliniqueveterinaire.bll;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import fr.eni.cliniqueveterinaire.bo.Agendas;
import fr.eni.cliniqueveterinaire.bo.Animaux;
import fr.eni.cliniqueveterinaire.bo.Clients;
import fr.eni.cliniqueveterinaire.bo.Personnels;
import fr.eni.cliniqueveterinaire.bo.Races;
import fr.eni.cliniqueveterinaire.dal.DALException;

public class AppliTestBLL {

	public static void main(String[] args) throws BLLException, DALException 
	{	
		// Instanciation du jeu d'essai Personnels
		System.out.println("___________________ Personnels ___________________");
		List<String> listRole = new ArrayList<String>();
		listRole.add("vet");
		List<Personnels> lPersonnels = new ArrayList<>();
		Personnels perso1 = new Personnels(1, "BOSAPIN", "Edmond", "BOSAPIN_J", "1234", listRole, false);
		lPersonnels.add(perso1);
		lPersonnels.add(new Personnels(2,"DECAJOU", "Benoit", "DECAJOU_B", "5678", listRole, false));
		lPersonnels.add(new Personnels(3,"MONFILS", "Thibaut", "MONFILS_T", "5678", listRole, false));
		lPersonnels.add(new Personnels(4,"DEJEU", "Odette", "DEJEU_O", "9101", listRole, false));
		lPersonnels.add(new Personnels(5,"HISSON_MP", "Marie-Paul", "HISSON_P", "1121", listRole, true));
		
		// Ajout du personnel				
		for (Personnels personnel : lPersonnels) {
			PersonnelsManager.ajouter(personnel);
		}
		
		// Affichage du personnels
		System.out.println(PersonnelsManager.selectTousPersonnels());
		
		// Instanciation du jeu d'essai Clients
		System.out.println();
		System.out.println("___________________ Clients ___________________");
		List<Clients> lClients = new ArrayList<>();
		Clients cli1 = new Clients(1, "Wayne", "Bruce", "Manoir", "Sous-sol", "85458", "Arkham", "15486468484", "une assurance", "batman@gmail.com", "RAS (remarques)", false);
		lClients.add(cli1);
		lClients.add(new Clients(2, "Iron", "Man", "Stark Industry", "En haut", "00001", "New York", "15486468485", "MAAF", "tony@yahoo.com", "RAS (remarques)", false));
		lClients.add(new Clients(3, "Iron", "Patriot", "Stark Industry", "En bas", "00001", "New York", "15486468485", "MAAF", "tony@yahoo.com", "RAS (remarques)", true));
		
		for (int i = 0; i<lClients.size();i++)
		{
			  System.out.println(lClients.get(i).toString());
		}
		
		// Instanciation du jeu d'essai Races
		System.out.println();
		System.out.println("___________________ Races ___________________");
		List<Races> lRaces = new ArrayList<>();
		Races race1 = new Races("Chat", "Siamois");
		lRaces.add(race1);
		lRaces.add(new Races("Chien","Labarador"));
		lRaces.add(new Races("Chien","Chihuahua"));
		lRaces.add(new Races("Dinosaure","T-rex"));
		lRaces.add(new Races("Dauphin","Commun"));
		lRaces.add(new Races("Chien","Caniche"));
		
		for (int i = 0; i<lRaces.size();i++)
		{
			  System.out.println(lRaces.get(i).toString());
		}
				
		// Instanciation du jeu d'essai Animaux
		System.out.println();
		System.out.println("___________________ Animaux ___________________");
		List<Animaux> lAnimaux = new ArrayList<>();
		Animaux ani1 = new Animaux(1, 1, "Labrador", "Doggo", "M", "Sable", "Chien", "AZERT", "RAS (antecedents)",  false);
		lAnimaux.add(ani1);
		lAnimaux.add(new Animaux(2,2,"Chihuahua","Tchoupi", "F", "roux", "Chien" ,"ABCDEF","RAS", false));
		lAnimaux.add(new Animaux(3,1,"T-rex","Hector", "M", "vert", "Dinosaure" ,"GHIJKL","Fracture du genou", false));
		lAnimaux.add(new Animaux(4,1,"Commun","Flipper", "M", "gris", "Dauphin" ,"MNOPQR","Absente de nageoire", true));
	
		for(Animaux tmpItem : lAnimaux)
		{
			AnimauxManager.ajouter(tmpItem);
			System.out.println("Ajout� : "+tmpItem.toString());
		}
		
		for (int i = 0; i<lAnimaux.size();i++)
		{
			  System.out.println(lAnimaux.get(i).toString());
		}
		
		// Instanciation du jeu d'essai Agendas
		System.out.println();
		System.out.println("___________________ Agendas ___________________");
		List<Agendas> lAgenda = new ArrayList<>();
		Agendas agenda1 = new Agendas(2,1,new Date(2018,2,3,10,15));
		lAgenda.add(agenda1);
		lAgenda.add(new Agendas(2,2,new Date(2018,2,20,9,0)));
		lAgenda.add(new Agendas(2,3,new Date(2018,5,25,15,0)));
	
		for(Agendas tmpItem : lAgenda)
		{
			AgendasManager.ajouter(tmpItem);
			System.out.println("Ajout� : "+tmpItem.toString());
		}
		
		for (int i = 0; i<lAgenda.size();i++)
		{
			  System.out.println(lAgenda.get(i).toString());
		}
		
		// Modification Personnels
		System.out.println("");
		System.out.println("___________________ Modification MDP ___________________");
		System.out.println("Ancien mot de passe : " + Cryptage.decrypt(perso1.getMotPasse()));
		PersonnelsManager.modificationMotPasse(perso1, "1234", "ABCDEF");
		System.out.println("Nouveau mot de passe : " + Cryptage.encrypt(perso1.getMotPasse()));
		
		// Modification Agenda
		
		// Modification Clients
		
		
		
		// Suppression Rendez-vous
		
		// Suppresion Clients
		
		
		// Suppresion Personnels
		System.out.println("");
		System.out.println("___________________ Suppresion personnel ___________________");
		PersonnelsManager.supprimer(perso1);
		System.out.println("Apres");
		System.out.println(PersonnelsManager.selectTousPersonnels());
	}

}
