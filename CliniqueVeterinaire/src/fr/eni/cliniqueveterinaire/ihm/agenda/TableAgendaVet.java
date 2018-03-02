package fr.eni.cliniqueveterinaire.ihm.agenda;

import java.util.Date;
import java.util.List;

import javax.swing.JTable;

import fr.eni.cliniqueveterinaire.bo.Agendas;

public class TableAgendaVet extends JTable{

	private ModelAgendaVet modelTable;
	
	public TableAgendaVet(List<Agendas> list){
		modelTable = new ModelAgendaVet(list);
		this.setModel(modelTable);
	}
	
}
