package fr.eni.cliniqueveterinaire.ihm.personnels;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.util.List;

import javax.swing.JTable;

import fr.eni.cliniqueveterinaire.bll.BLLException;
import fr.eni.cliniqueveterinaire.bo.Personnels;

public class TablePersonnels extends JTable{

	private ModelPersonnels modelPersonnels;
	
	public TablePersonnels(List<Personnels> list) throws BLLException{
		modelPersonnels = new ModelPersonnels(list);
		this.setModel(modelPersonnels);
	}
	
	public void setData(List<Personnels> data) {
		modelPersonnels.setDataChanged(data);
	}
	
}
