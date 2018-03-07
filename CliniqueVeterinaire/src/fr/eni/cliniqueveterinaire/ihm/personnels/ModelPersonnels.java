package fr.eni.cliniqueveterinaire.ihm.personnels;

import java.util.List;

import javax.swing.table.AbstractTableModel;
import fr.eni.cliniqueveterinaire.bo.Personnels;

public class ModelPersonnels extends AbstractTableModel{
	
	private List<Personnels> listPersonnels;
	private final String[] entetes = { "Nom Prenom", "Role", "Mot de passe"};
	
	public ModelPersonnels(List<Personnels> list) {	
		this.listPersonnels=list;	
	}
	
	@Override
	public int getColumnCount() {
		return entetes.length;
	}

	@Override
	public String getColumnName(int columnIndex) {
		return entetes[columnIndex];
	}
	
	@Override
	public int getRowCount() {
		return listPersonnels.size();
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		
		switch (columnIndex) {
			case 0:
				// Nom prenom
			return listPersonnels.get(rowIndex).getNom()+" "+listPersonnels.get(rowIndex).getPrenom();
	
			case 1:
				// Role
				return listPersonnels.get(rowIndex).getRole();
			case 2:
				// Mot de passe
				return "***********";

			default:
				throw new IllegalArgumentException();
		}
	}

	public void setDataChanged(List<Personnels> data){
		this.listPersonnels = data;
		fireTableDataChanged();
	}
}
