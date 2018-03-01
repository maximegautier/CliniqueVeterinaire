package fr.eni.cliniqueveterinaire.bo;

public class Animaux 
{
    //region DECLARATION

	private int codeAnimal;
	private int codeClient;
	private String race;
	private String nomAnimal;
	private String sexe; // 1 caractère
	private String couleur; 
	private String espece;
	private String tatouage;
	private String antecedents;
	private boolean archive;

    //endregion DECLARATION

    //region CTOR

	public Animaux(int codeAnimal, int codeClient, String race, String nomAnimal, String sexe, String couleur,
			String espece, String tatouage, String antecedents, boolean archive) 
	{
		this.codeAnimal = codeAnimal;
		this.codeClient = codeClient;
		this.race = race;
		this.nomAnimal = nomAnimal;
		this.sexe = sexe;
		this.couleur = couleur;
		this.espece = espece;
		this.tatouage = tatouage;
		this.antecedents = antecedents;
		this.archive = archive;
	}
	
	public Animaux(int codeAnimal, int codeClient, String race, String nomAnimal, String sexe,
			String espece, boolean archive) 
	{
		this.codeAnimal = codeAnimal;
		this.codeClient = codeClient;
		this.race = race;
		this.nomAnimal = nomAnimal;
		this.sexe = sexe;
		this.espece = espece;
		this.archive = archive;
	}
	
	public Animaux(int codeClient, String race, String nomAnimal, String sexe, String couleur,
			String espece, String tatouage, String antecedents, boolean archive) 
	{
		this.codeClient = codeClient;
		this.race = race;
		this.nomAnimal = nomAnimal;
		this.sexe = sexe;
		this.couleur = couleur;
		this.espece = espece;
		this.tatouage = tatouage;
		this.antecedents = antecedents;
		this.archive = archive;
	}
	
	public Animaux(int codeClient, String race, String nomAnimal, String sexe,
			String espece, boolean archive) 
	{
		this.codeClient = codeClient;
		this.race = race;
		this.nomAnimal = nomAnimal;
		this.sexe = sexe;
		this.espece = espece;
		this.archive = archive;
	}

    //endregion CTOR
	
    //region METHODS
    
	@Override
	public String toString()
	{
		return "Animaux [CodeAnimal=" + codeAnimal + ", CodeClient=" + codeClient + ", Race=" + race + ", NomAnimal="
				+ nomAnimal + ", sexe=" + sexe + ", Couleur=" + couleur + ", Espece=" + espece + ", tatouage="
				+ tatouage + ", Antecedents=" + antecedents + ", archive=" + archive + "]";
	}
    
    //endregion METHODS

    //region GET/SET

	public int getCodeAnimal() {
		return codeAnimal;
	}

	public void setCodeAnimal(int codeAnimal) {
		codeAnimal = codeAnimal;
	}

	public int getCodeClient() {
		return codeClient;
	}

	public void setCodeClient(int codeClient) {
		codeClient = codeClient;
	}

	public String getRace() {
		return race;
	}

	public void setRace(String race) {
		race = race;
	}

	public String getNomAnimal() {
		return nomAnimal;
	}

	public void setNomAnimal(String nomAnimal) {
		nomAnimal = nomAnimal;
	}

	public String getsexe() {
		return sexe;
	}

	public void setsexe(String sexe) {
		sexe = sexe;
	}

	public String getCouleur() {
		return couleur;
	}

	public void setCouleur(String couleur) {
		couleur = couleur;
	}

	public String getEspece() {
		return espece;
	}

	public void setEspece(String espece) {
		espece = espece;
	}

	public String gettatouage() {
		return tatouage;
	}

	public void settatouage(String tatouage) {
		tatouage = tatouage;
	}

	public String getAntecedents() {
		return antecedents;
	}

	public void setAntecedents(String antecedents) {
		antecedents = antecedents;
	}

	public boolean getarchive() {
		return archive;
	}

	public void setarchive(boolean archive) {
		archive = archive;
	}

    //endregion GET/SET
}
