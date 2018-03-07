package fr.eni.cliniqueveterinaire.ihm.agenda;

import java.text.DateFormat;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import fr.eni.cliniqueveterinaire.bll.AnimauxManager;
import fr.eni.cliniqueveterinaire.bll.BLLException;
import fr.eni.cliniqueveterinaire.bll.ClientsManager;
import fr.eni.cliniqueveterinaire.bo.Agendas;
import fr.eni.cliniqueveterinaire.bo.Animaux;
import fr.eni.cliniqueveterinaire.bo.Clients;

public class ModelAgendaVet extends AbstractTableModel{
	
	private List<Agendas> listRdv;
	private final String[] entetes = { "Heure", "Nom du client", "Animal", "Race" };
	
	public ModelAgendaVet(List<Agendas> list) throws BLLException
	{
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
		Animaux animal = null;
		int a = listRdv.get(rowIndex).getCodeAnimal();
		try {
			animal = AnimauxManager.selectAnimal(a);
		} catch (BLLException e1) {
			e1.printStackTrace();
		}
		
		switch (columnIndex) {
			case 0:
				// Date
				DateFormat shortDateFormat = DateFormat.getDateTimeInstance(DateFormat.SHORT,DateFormat.SHORT);
				return shortDateFormat.format(listRdv.get(rowIndex).getDateRdv());
	
			case 1:
				// Client
				try {
					Clients client = ClientsManager.getInstance().selectById(animal.getCodeClient());
					
					return client.getNomClient() + " " + client.getPrenomClient();
				} catch (BLLException e) {
					e.printStackTrace();
				}
			case 2:
				// Animal
				return animal.getNomAnimal();
	
			case 3:
				// Race
				return animal.getRace();
	
			default:
				throw new IllegalArgumentException();
		}
	}
	
	public void setDataChanged(List<Agendas> data){
		this.listRdv = data;
		fireTableDataChanged();
	}
	
}
