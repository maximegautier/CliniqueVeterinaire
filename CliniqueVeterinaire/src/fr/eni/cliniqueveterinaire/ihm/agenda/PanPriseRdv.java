package fr.eni.cliniqueveterinaire.ihm.agenda;

import javax.swing.JPanel;

public class PanPriseRdv extends JPanel
{
    //region DECLARATION

	private static PanPriseRdv instance;

    //endregion DECLARATION

    //region CTOR

	private PanPriseRdv()
	{		
	}

    //endregion CTOR
    
    //region METHODS
    
    
    //endregion METHODS

    //region GET/SET

	public static JPanel getInstance()
	{
		if(PanPriseRdv.instance == null)
		{
			PanPriseRdv.instance = new PanPriseRdv();
		}
		return PanPriseRdv.instance;
	}

    //endregion GET/SET
}
