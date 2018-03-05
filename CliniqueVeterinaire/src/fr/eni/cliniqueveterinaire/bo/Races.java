package fr.eni.cliniqueveterinaire.bo;

public class Races 
{
    //region DECLARATION

	private String race;
	private String espece;

    //endregion DECLARATION

    //region CTOR

	public Races(String race, String espece) 
	{
		this.race = race;
		this.espece = espece;
	}

    //endregion CTOR
	
    //region METHODS
    
	@Override
	public String toString() 
	{
		return race;
	}	
    
    //endregion METHODS

    //region GET/SET

	public String getRace() {
		return race;
	}

	public void setRace(String race) {
		race = race;
	}

	public String getEspece() {
		return espece;
	}

	public void setEspece(String espece) {
		espece = espece;
	}

    //endregion GET/SET
}
