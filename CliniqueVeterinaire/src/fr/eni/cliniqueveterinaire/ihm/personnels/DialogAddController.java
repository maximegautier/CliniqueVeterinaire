package fr.eni.cliniqueveterinaire.ihm.personnels;


public class DialogAddController {
	
	private static DialogAddController instance;
	
	private DialogAddController()
	{
		
	}
	
	public static DialogAddController getInstance()
	{
		if ( DialogAddController.instance == null)
		{
			DialogAddController.instance = new DialogAddController();
		}
		return DialogAddController.instance;
	}
}
