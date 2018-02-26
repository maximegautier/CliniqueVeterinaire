package fr.eni.cliniqueveterinaire.bo;

public class Races 
{
    //region DECLARATION

	private String Race;
	private String Espece;

    //endregion DECLARATION

    //region CTOR

	public Races(String race, String espece) 
	{
		Race = race;
		Espece = espece;
	}

    //endregion CTOR
	
    //region METHODS
    
	@Override
	public String toString() 
	{
		return "Races [Race=" + Race + ", Espece=" + Espece + "]";
	}	
    
    //endregion METHODS

    //region GET/SET

	public String getRace() {
		return Race;
	}

	public void setRace(String race) {
		Race = race;
	}

	public String getEspece() {
		return Espece;
	}

	public void setEspece(String espece) {
		Espece = espece;
	}

    //endregion GET/SET
}
