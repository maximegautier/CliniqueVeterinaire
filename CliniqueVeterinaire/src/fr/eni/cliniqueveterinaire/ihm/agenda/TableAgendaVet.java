package fr.eni.cliniqueveterinaire.ihm.agenda;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.util.List;
import javax.swing.JTable;

import fr.eni.cliniqueveterinaire.bll.BLLException;
import fr.eni.cliniqueveterinaire.bo.Agendas;
import fr.eni.cliniqueveterinaire.bo.Personnels;

public class TableAgendaVet extends JTable{

	private ModelAgendaVet modelTableVet;
	
	public TableAgendaVet(List<Agendas> list) throws BLLException{
		setPreferredScrollableViewportSize(new Dimension(this.getPreferredSize().width-30,500));
		modelTableVet = new ModelAgendaVet(list);
		this.setModel(modelTableVet);
		
		setRowHeight(30); 
        setBackground(new Color(238,238,238));
		setFont(new Font("Arial",Font.BOLD, 15));
		setFillsViewportHeight(true);
	}
	
	public void setData(List<Agendas> data) {
		modelTableVet.setDataChanged(data);
	}
	
}
