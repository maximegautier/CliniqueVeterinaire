package fr.eni.cliniqueveterinaire.ihm.agenda;

import java.util.Date;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import fr.eni.cliniqueveterinaire.bo.Agendas;
import fr.eni.cliniqueveterinaire.bo.Animaux;
import fr.eni.cliniqueveterinaire.bo.Clients;
import fr.eni.cliniqueveterinaire.bo.Races;

public class ModelAgendaVet extends AbstractTableModel{
	
	private List<Agendas> listRdv;
	private final String[] entetes = { "Heure", "Nom du client", "Animal", "Race" };
	
	public ModelAgendaVet(List<Agendas> list){
		this.listRdv=list;
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
		return listRdv.size();
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		switch (columnIndex) {

		case 0:
			// Date
			return listRdv.get(rowIndex).getDateRdv();

		case 1:
			// Client
			return listRdv.get(rowIndex).getDateRdv();

		case 2:
			// Animal
			return listRdv.get(rowIndex).getDateRdv();

		case 3:
			// Race
			return listRdv.get(rowIndex).getDateRdv();

		default:
			throw new IllegalArgumentException();
		}
	}
	
	@Override
	public Class<?> getColumnClass(int columnIndex) {
		switch (columnIndex) {
			case 0:
			case 1:
				return Date.class;
	
			case 3:
				return Clients.class;
	
			case 2:
				return Animaux.class;
	
			case 4:
				return Races.class;
	
			default:
				return Object.class;
		}
	}
	

}
