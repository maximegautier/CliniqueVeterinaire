package fr.eni.cliniqueveterinaire.ihm.agenda;

import java.util.List;

import javax.swing.JTable;

import fr.eni.cliniqueveterinaire.bo.Agendas;

public class tableAgendaVet extends JTable{

	private ModelAgendaVet modelTable;
	
	public tableAgendaVet(List<Agendas> list){
		this.setModel(new ModelAgendaVet(list));
	}
	
}
