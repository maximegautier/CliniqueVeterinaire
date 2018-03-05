package fr.eni.cliniqueveterinaire.ihm.agenda;

import java.util.Date;
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
		try {
			animal = AnimauxManager.selectAnimal(listRdv.get(rowIndex).getCodeAnimal());
			System.out.println(animal);
		} catch (BLLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		switch (columnIndex) {
			case 0:
				// Date
				return listRdv.get(rowIndex).getDateRdv();
	
			case 1:
				// Client
				try {
					
					Clients client = ClientsManager.getInstance().selectById(animal.getCodeClient());
					return client.getNomClient() + " " + client.getPrenomClient();
				} catch (BLLException e) {
					// TODO Auto-generated catch block
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
	
}
