package fr.eni.cliniqueveterinaire.bo;

public class Animaux 
{
    //region DECLARATION

	private int CodeAnimal;
	private int CodeClient;
	private String Race;
	private String NomAnimal;
	private String Sexe; // 1 caractère
	private String Couleur; 
	private String Espece;
	private String Tatouage;
	private String Antecedents;
	private boolean Archive;

    //endregion DECLARATION

    //region CTOR

	public Animaux(int codeAnimal, int codeClient, String race, String nomAnimal, String sexe, String couleur,
			String espece, String tatouage, String antecedents, boolean archive) 
	{
		this.CodeAnimal = codeAnimal;
		this.CodeClient = codeClient;
		this.Race = race;
		this.NomAnimal = nomAnimal;
		this.Sexe = sexe;
		this.Couleur = couleur;
		this.Espece = espece;
		this.Tatouage = tatouage;
		this.Antecedents = antecedents;
		this.Archive = archive;
	}
	
	public Animaux(int codeAnimal, int codeClient, String race, String nomAnimal, String sexe,
			String espece, boolean archive) 
	{
		this.CodeAnimal = codeAnimal;
		this.CodeClient = codeClient;
		this.Race = race;
		this.NomAnimal = nomAnimal;
		this.Sexe = sexe;
		this.Espece = espece;
		this.Archive = archive;
	}
	
	public Animaux(int codeClient, String race, String nomAnimal, String sexe, String couleur,
			String espece, String tatouage, String antecedents, boolean archive) 
	{
		this.CodeClient = codeClient;
		this.Race = race;
		this.NomAnimal = nomAnimal;
		this.Sexe = sexe;
		this.Couleur = couleur;
		this.Espece = espece;
		this.Tatouage = tatouage;
		this.Antecedents = antecedents;
		this.Archive = archive;
	}
	
	public Animaux(int codeClient, String race, String nomAnimal, String sexe,
			String espece, boolean archive) 
	{
		this.CodeClient = codeClient;
		this.Race = race;
		this.NomAnimal = nomAnimal;
		this.Sexe = sexe;
		this.Espece = espece;
		this.Archive = archive;
	}

    //endregion CTOR
	
    //region METHODS
    
	@Override
	public String toString()
	{
		return "Animaux [CodeAnimal=" + CodeAnimal + ", CodeClient=" + CodeClient + ", Race=" + Race + ", NomAnimal="
				+ NomAnimal + ", Sexe=" + Sexe + ", Couleur=" + Couleur + ", Espece=" + Espece + ", Tatouage="
				+ Tatouage + ", Antecedents=" + Antecedents + ", Archive=" + Archive + "]";
	}
    
    //endregion METHODS

    //region GET/SET

	public int getCodeAnimal() {
		return CodeAnimal;
	}

	public void setCodeAnimal(int codeAnimal) {
		CodeAnimal = codeAnimal;
	}

	public int getCodeClient() {
		return CodeClient;
	}

	public void setCodeClient(int codeClient) {
		CodeClient = codeClient;
	}

	public String getRace() {
		return Race;
	}

	public void setRace(String race) {
		Race = race;
	}

	public String getNomAnimal() {
		return NomAnimal;
	}

	public void setNomAnimal(String nomAnimal) {
		NomAnimal = nomAnimal;
	}

	public String getSexe() {
		return Sexe;
	}

	public void setSexe(String sexe) {
		Sexe = sexe;
	}

	public String getCouleur() {
		return Couleur;
	}

	public void setCouleur(String couleur) {
		Couleur = couleur;
	}

	public String getEspece() {
		return Espece;
	}

	public void setEspece(String espece) {
		Espece = espece;
	}

	public String getTatouage() {
		return Tatouage;
	}

	public void setTatouage(String tatouage) {
		Tatouage = tatouage;
	}

	public String getAntecedents() {
		return Antecedents;
	}

	public void setAntecedents(String antecedents) {
		Antecedents = antecedents;
	}

	public boolean isArchive() {
		return Archive;
	}

	public void setArchive(boolean archive) {
		Archive = archive;
	}

    //endregion GET/SET
}
