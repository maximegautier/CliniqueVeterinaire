package fr.eni.cliniqueveterinaire.ihm.personnels;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.util.List;

import javax.swing.JTable;

import fr.eni.cliniqueveterinaire.bll.BLLException;
import fr.eni.cliniqueveterinaire.bo.Agendas;
import fr.eni.cliniqueveterinaire.bo.Personnels;
import fr.eni.cliniqueveterinaire.ihm.agenda.ModelAgendaVet;

public class TablePersonnels extends JTable{

private ModelPersonnels modelPersonnels;
	
	public TablePersonnels(List<Personnels> list) throws BLLException{
		setPreferredScrollableViewportSize(new Dimension(this.getPreferredSize().width-30,500));
		modelPersonnels = new ModelPersonnels(list);
		this.setModel(modelPersonnels);
		
		setRowHeight(50);  
        setBackground(new Color(238,238,238));
		setShowGrid(false);
		setFont(new Font("Arial", Font.BOLD, 15));
		setFillsViewportHeight(true);
	}
	
}
