package fr.eni.cliniqueveterinaire.bo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class AppliTestBO 
{

	public static void main(String[] args) 
	{
		//RENDEZ-VOUS
		Date dateRDV = new Date(); 
		Agendas unRDV = new Agendas(1, 2, dateRDV);
		System.out.println("********** Création d'un rendez-vous **********");
		System.out.println(unRDV.toString());
		System.out.println("---------------------------------------------------------------");
		
		//ANIMAL
		Animaux unAnimal = new Animaux(1, 12, "Labrador", "Doggo", "M", "Sable", "*codetatoo*", "RAS (antecedents)", "Chien", false);
		System.out.println("********** Création d'une fiche animale **********");
		System.out.println(unAnimal.toString());
		System.out.println("---------------------------------------------------------------");
		
		//CLIENT
		Clients unClient = new Clients(21, "Wayne", "Bruce", "Manoir", "Sous-sol", "85458", "Arkham", "15486468484", "une assurance", "batman@gmail.com", "RAS (remarques)", false);
		System.out.println("********** Création d'une fiche client **********");
		System.out.println(unClient.toString());
		System.out.println("---------------------------------------------------------------");
		
		//PERSONNELS
		List<String> listRole = new ArrayList<String>();
		listRole.add("vet");
		Personnels unPerso = new Personnels(54, "Norris", "Chuck","NORRIS_C", "Norris60", listRole ,false);
		System.out.println("********** Création d'une fiche personnel **********");
		System.out.println(unPerso.toString());
		System.out.println("---------------------------------------------------------------");
		
		//RACES
		Races uneRace = new Races("Labrador", "Chien");
		System.out.println("********** Création d'une race **********");
		System.out.println(uneRace.toString());
		System.out.println("---------------------------------------------------------------");		
	}

}
