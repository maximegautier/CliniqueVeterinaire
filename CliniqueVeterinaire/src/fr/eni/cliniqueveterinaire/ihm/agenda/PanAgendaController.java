package fr.eni.cliniqueveterinaire.ihm.agenda;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.JComboBox;

import fr.eni.cliniqueveterinaire.bll.AgendasManager;
import fr.eni.cliniqueveterinaire.bll.BLLException;
import fr.eni.cliniqueveterinaire.bll.PersonnelsManager;
import fr.eni.cliniqueveterinaire.bo.Agendas;
import fr.eni.cliniqueveterinaire.bo.Personnels;

public class PanAgendaController 
{
    //region DECLARATION
	

    //endregion DECLARATION

    //region CTOR


    //endregion CTOR
    
    //region METHODS
    
	public static List<Agendas> remplirTableau(Date jour) throws BLLException{
		return AgendasManager.selectParDate(jour);
	}
	
	public static List<String> remplirComboVeterinaire() throws BLLException{
		List<Personnels> lPersonnel = PersonnelsManager.selectTousVeterinaires();
		
		List<String> lNomPersonnel = new ArrayList();
		
		for (Personnels tmp : lPersonnel)
		{
			lNomPersonnel.add(tmp.getDisplayName());
		}
		
		return lNomPersonnel;
	}
	
    //endregion METHODS

    //region GET/SET


    //endregion GET/SET
}
