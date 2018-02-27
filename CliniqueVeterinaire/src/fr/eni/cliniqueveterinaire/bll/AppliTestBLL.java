package fr.eni.cliniqueveterinaire.bll;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import fr.eni.cliniqueveterinaire.bo.Agendas;
import fr.eni.cliniqueveterinaire.bo.Animaux;
import fr.eni.cliniqueveterinaire.bo.Clients;
import fr.eni.cliniqueveterinaire.bo.Personnels;
import fr.eni.cliniqueveterinaire.bo.Races;

public class AppliTestBLL {

	public static void main(String[] args) 
	{	
		// Instanciation du jeu d'essai Personnels
		List<Personnels> lPersonnels = new ArrayList<>();
		Personnels perso1 = new Personnels(1,"BOSAPIN_E","1234","adm",false);
		lPersonnels.add(perso1);
		lPersonnels.add(new Personnels(2,"DECAJOU_B","5678","vet",false));
		lPersonnels.add(new Personnels(3,"MONFILS_T","5678","ass",false));
		lPersonnels.add(new Personnels(4,"DEJEU_O","9101","sec",false));
		lPersonnels.add(new Personnels(5,"HISSON_MP","1121","sec",true));
		
		// Instanciation du jeu d'essai Clients
		List<Clients> lClients = new ArrayList<>();
		Clients cli1 = new Clients(1, "Wayne", "Bruce", "Manoir", "Sous-sol", "85458", "Arkham", "15486468484", "une assurance", "batman@gmail.com", "RAS (remarques)", false);
		lClients.add(cli1);
		lClients.add(new Clients(2, "Iron", "Man", "Stark Industry", "En haut", "00001", "New York", "15486468485", "MAAF", "tony@yahoo.com", "RAS (remarques)", false));
		lClients.add(new Clients(3, "Iron", "Patriot", "Stark Industry", "En bas", "00001", "New York", "15486468485", "MAAF", "tony@yahoo.com", "RAS (remarques)", true));
		
		// Instanciation du jeu d'essai Races
		List<Races> lRaces = new ArrayList<>();
		Races race1 = new Races("Chat", "Siamois");
		lRaces.add(race1);
		lRaces.add(new Races("Chien","Labarador"));
		lRaces.add(new Races("Chien","Chihuahua"));
		lRaces.add(new Races("Dinosaure","T-rex"));
		lRaces.add(new Races("Dauphin","Commun"));
		lRaces.add(new Races("Chien","Caniche"));
				
		// Instanciation du jeu d'essai Animaux
		List<Animaux> lAnimaux = new ArrayList<>();
		Animaux ani1 = new Animaux(1, 1, "Labrador", "Doggo", "M", "Sable", "*codetatoo*", "RAS (antecedents)", "Chien", false);
		lAnimaux.add(ani1);
		lAnimaux.add(new Animaux(2,2,"Chihuahua","Tchoupi", "F", "roux", "Chien" ,"ABCDEF","RAS", false));
		lAnimaux.add(new Animaux(3,1,"T-rex","Hector", "M", "vert", "Dinosaure" ,"GHIJKL","Fracture du genou", false));
		lAnimaux.add(new Animaux(4,1,"Commun","Flipper", "M", "gris", "Dauphin" ,"MNOPQR","Absente de nageoire", true));
	
		// Instanciation du jeu d'essai Agendas
		List<Agendas> lAgenda = new ArrayList<>();
		Agendas agenda1 = new Agendas(2,1,new Date(2018,2,3,10,15));
		lAgenda.add(agenda1);
		lAgenda.add(new Agendas(2,2,new Date(2018,2,20,9,0)));
		lAgenda.add(new Agendas(2,3,new Date(2018,5,25,15,0)));
	
		
		// Modification Personnels
		
		// Modification Agenda
		
		// Modification Clients
		
		
		
		// Suppression Rendez-vous
		
		// Suppresion Clients
		
		// Suppresion Personnels
		
	}

}
