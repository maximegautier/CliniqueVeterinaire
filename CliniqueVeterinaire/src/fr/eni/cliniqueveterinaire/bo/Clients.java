package fr.eni.cliniqueveterinaire.bo;

public class Clients 
{
	//region DECLARATION
	
	private int codeClient;
	private String nomClient;
	private String prenomClient;
	private String adresse1;
	private String adresse2;
	private String codePostal; //6 caractères
	private String ville;
	private String numTel;
	private String assurance;
	private String email;
	private String remarque;
	private boolean archive;	
	
	//endregion DECLARATION
	
	//region CTOR
	
	public Clients(int codeClient, String nomClient, String prenomClient, String adresse1, String adresse2,
			String codePostal, String ville, String numTel, String assurance, String email, String remarque,
			boolean archive) 
	{
		this.codeClient = codeClient;
		this.nomClient = nomClient;
		this.prenomClient = prenomClient;
		this.adresse1 = adresse1;
		this.adresse2 = adresse2;
		this.codePostal = codePostal;
		this.ville = ville;
		this.numTel = numTel;
		this.assurance = assurance;
		this.email = email;
		this.remarque = remarque;
		this.archive = archive;
	}		
	
	public Clients(int codeClient, String nomClient, String prenomClient, boolean archive)
	{
		this.codeClient = codeClient;
		this.nomClient = nomClient;
		this.prenomClient = prenomClient;
		this.archive = archive;
	}
	
	public Clients(String nomClient, String prenomClient, String adresse1, String adresse2,
			String codePostal, String ville, String numTel, String assurance, String email, String remarque,
			boolean archive) 
	{
		this.nomClient = nomClient;
		this.prenomClient = prenomClient;
		this.adresse1 = adresse1;
		this.adresse2 = adresse2;
		this.codePostal = codePostal;
		this.ville = ville;
		this.numTel = numTel;
		this.assurance = assurance;
		this.email = email;
		this.remarque = remarque;
		this.archive = archive;
	}		
	
	public Clients(String nomClient, String prenomClient, boolean archive)
	{
		this.nomClient = nomClient;
		this.prenomClient = prenomClient;
		this.archive = archive;
	}
	
	//endregion CTOR
	
    //region METHODS
    
	@Override
	public String toString() 
	{
		return "Clients [CodeClient=" + codeClient + ", NomClient=" + nomClient + ", PrenomClient=" + prenomClient
				+ ", Adresse1=" + adresse1 + ", Adresse2=" + adresse2 + ", CodePostal=" + codePostal + ", Ville="
				+ ville + ", NumTel=" + numTel + ", Assurance=" + assurance + ", Email=" + email + ", Remarque="
				+ remarque + ", Archive=" + archive + "]";
	}
    
    //endregion METHODS
	
	//region GET/SET
	
	public int getCodeClient() {
		return codeClient;
	}

	public void setCodeClient(int codeClient) {
		codeClient = codeClient;
	}
	public String getNomClient() {
		return nomClient;
	}
	public void setNomClient(String nomClient) {
		nomClient = nomClient;
	}
	public String getPrenomClient() {
		return prenomClient;
	}
	public void setPrenomClient(String prenomClient) {
		prenomClient = prenomClient;
	}
	public String getAdresse1() {
		return adresse1;
	}
	public void setAdresse1(String adresse1) {
		adresse1 = adresse1;
	}
	public String getAdresse2() {
		return adresse2;
	}
	public void setAdresse2(String adresse2) {
		adresse2 = adresse2;
	}
	public String getCodePostal() {
		return codePostal;
	}
	public void setCodePostal(String codePostal) {
		codePostal = codePostal;
	}
	public String getVille() {
		return ville;
	}
	public void setVille(String ville) {
		ville = ville;
	}
	public String getNumTel() {
		return numTel;
	}
	public void setNumTel(String numTel) {
		numTel = numTel;
	}
	public String getAssurance() {
		return assurance;
	}
	public void setAssurance(String assurance) {
		assurance = assurance;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		email = email;
	}
	public String getRemarque() {
		return remarque;
	}
	public void setRemarque(String remarque) {
		remarque = remarque;
	}
	public boolean isArchive() {
		return archive;
	}
	public void setArchive(boolean archive) {
		archive = archive;
	}
	
	//endregion GET/SET
}
