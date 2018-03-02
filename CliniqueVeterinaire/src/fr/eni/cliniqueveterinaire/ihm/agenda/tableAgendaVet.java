package fr.eni.cliniqueveterinaire.ihm.agenda;

import javax.swing.JTable;

public class tableAgendaVet extends JTable{

	private ModelAgendaVet modelTable;
	
	public tableAgendaVet(){
		this.setModel(modelTable);
	}
	
}
