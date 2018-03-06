package fr.eni.cliniqueveterinaire.ihm.animal;

import javax.swing.SwingUtilities;

/* Créé par Erwin DUPUIS */
public class EcranAnimalTest 
{

	public static void main(String[] args) 
	{
		SwingUtilities.invokeLater(new Runnable()
		{
			@Override
			public void run() 
			{
					//EcranAnimalController.getInstance().startApp();
			}	
		});

	}

}
