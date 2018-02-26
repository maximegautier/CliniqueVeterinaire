package fr.eni.cliniqueveterinaire.bo;

public class Clients 
{
	//region DECLARATION
	
	private int CodeClient;
	private String NomClient;
	private String PrenomClient;
	private String Adresse1;
	private String Adresse2;
	private String CodePostal; //6 caractères
	private String Ville;
	private String NumTel;
	private String Assurance;
	private String Email;
	private String Remarque;
	private boolean Archive;	
	
	//endregion DECLARATION
	
	//region CTOR
	
	public Clients(int codeClient, String nomClient, String prenomClient, String adresse1, String adresse2,
			String codePostal, String ville, String numTel, String assurance, String email, String remarque,
			boolean archive) 
	{
		this.CodeClient = codeClient;
		this.NomClient = nomClient;
		this.PrenomClient = prenomClient;
		this.Adresse1 = adresse1;
		this.Adresse2 = adresse2;
		this.CodePostal = codePostal;
		this.Ville = ville;
		this.NumTel = numTel;
		this.Assurance = assurance;
		this.Email = email;
		this.Remarque = remarque;
		this.Archive = archive;
	}		
	
	public Clients(int codeClient, String nomClient, String prenomClient, boolean archive)
	{
		this.CodeClient = codeClient;
		this.NomClient = nomClient;
		this.PrenomClient = prenomClient;
		this.Archive = archive;
	}
	
	//endregion CTOR
	
    //region METHODS
    
	@Override
	public String toString() 
	{
		return "Clients [CodeClient=" + CodeClient + ", NomClient=" + NomClient + ", PrenomClient=" + PrenomClient
				+ ", Adresse1=" + Adresse1 + ", Adresse2=" + Adresse2 + ", CodePostal=" + CodePostal + ", Ville="
				+ Ville + ", NumTel=" + NumTel + ", Assurance=" + Assurance + ", Email=" + Email + ", Remarque="
				+ Remarque + ", Archive=" + Archive + "]";
	}
    
    //endregion METHODS
	
	//region GET/SET
	
	public int getCodeClient() {
		return CodeClient;
	}

	public void setCodeClient(int codeClient) {
		CodeClient = codeClient;
	}
	public String getNomClient() {
		return NomClient;
	}
	public void setNomClient(String nomClient) {
		NomClient = nomClient;
	}
	public String getPrenomClient() {
		return PrenomClient;
	}
	public void setPrenomClient(String prenomClient) {
		PrenomClient = prenomClient;
	}
	public String getAdresse1() {
		return Adresse1;
	}
	public void setAdresse1(String adresse1) {
		Adresse1 = adresse1;
	}
	public String getAdresse2() {
		return Adresse2;
	}
	public void setAdresse2(String adresse2) {
		Adresse2 = adresse2;
	}
	public String getCodePostal() {
		return CodePostal;
	}
	public void setCodePostal(String codePostal) {
		CodePostal = codePostal;
	}
	public String getVille() {
		return Ville;
	}
	public void setVille(String ville) {
		Ville = ville;
	}
	public String getNumTel() {
		return NumTel;
	}
	public void setNumTel(String numTel) {
		NumTel = numTel;
	}
	public String getAssurance() {
		return Assurance;
	}
	public void setAssurance(String assurance) {
		Assurance = assurance;
	}
	public String getEmail() {
		return Email;
	}
	public void setEmail(String email) {
		Email = email;
	}
	public String getRemarque() {
		return Remarque;
	}
	public void setRemarque(String remarque) {
		Remarque = remarque;
	}
	public boolean isArchive() {
		return Archive;
	}
	public void setArchive(boolean archive) {
		Archive = archive;
	}
	
	//endregion GET/SET
}
