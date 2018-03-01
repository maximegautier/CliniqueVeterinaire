package fr.eni.cliniqueveterinaire.bo;

public class Personnels 
{
    //region DECLARATION
	
	private int codePers;
	private String nom;
	private String prenom;
	private String login;
	private String motPasse;
	private String role;
	private boolean archive;
	
    //endregion DECLARATION

    //region CTOR

	public Personnels(int codePers, String nom, String prenom, String login, String motPasse, String role, boolean archive) 
	{
		this.codePers = codePers;
		this.nom = nom;
		this.prenom = prenom;
		this.login = login;
		this.motPasse = motPasse;
		this.role = role;
		this.archive = archive;
	}
	public Personnels(String nom, String prenom, String login, String motPasse, String role,boolean archive) 
	{
		this.nom = nom;
		this.prenom = prenom;
		this.login = login;
		this.motPasse = motPasse;
		this.role = role;
		this.archive = archive;
	}

    //endregion CTOR
	
    //region METHODS
    
	@Override
	public String toString() 
	{
		return "Personnels [CodePers=" + codePers + ", Nom=" + nom + ", MotPasse=" + motPasse + ", Role=" + role
				+ ", Archive=" + archive + "]";
	}	
    
    //endregion METHODS

    //region GET/SET

	public int getCodePers() {
		return codePers;
	}

	public void setCodePers(int codePers) {
		codePers = codePers;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		nom = nom;
	}
	public String getMotPasse() {
		return motPasse;
	}
	public void setMotPasse(String motPasse) {
		motPasse = motPasse;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		role = role;
	}
	public boolean getArchive() {
		return archive;
	}
	public void setArchive(boolean archive) {
		archive = archive;
	}
	public String getPrenom() {
		return prenom;
	}
	public void setPrenom(String prenom) {
		prenom = prenom;
	}
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		login = login;
	}
	
	

    //endregion GET/SET
}
