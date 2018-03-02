package fr.eni.cliniqueveterinaire.ihm.agenda;

import java.awt.Dimension;
import java.util.List;
import javax.swing.JTable;

import fr.eni.cliniqueveterinaire.bll.BLLException;
import fr.eni.cliniqueveterinaire.bo.Agendas;

public class TableAgendaVet extends JTable{

	private ModelAgendaVet modelTableVet;
	
	public TableAgendaVet(List<Agendas> list) throws BLLException{
		setPreferredScrollableViewportSize(new Dimension(this.getPreferredSize().width-30,500));
		modelTableVet = new ModelAgendaVet(list);
		this.setModel(modelTableVet);
	}
	
}
