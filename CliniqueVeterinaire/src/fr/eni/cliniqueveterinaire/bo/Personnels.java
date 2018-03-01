package fr.eni.cliniqueveterinaire.bo;

public class Personnels 
{
    //region DECLARATION
	
	private int CodePers;
	private String Nom;
	private String Prenom;
	private String Login;
	private String MotPasse;
	private String Role;
	private boolean Archive;
	
    //endregion DECLARATION

    //region CTOR

	public Personnels(int codePers, String nom, String Prenom, String Login, String motPasse, String role,boolean archive) 
	{
		this.CodePers = codePers;
		this.Nom = nom;
		this.Prenom = Prenom;
		this.Login = Login;
		this.MotPasse = motPasse;
		this.Role = role;
		this.Archive = archive;
	}
	public Personnels(String nom, String Prenom, String Login, String motPasse, String role,boolean archive) 
	{
		this.Nom = nom;
		this.Prenom = Prenom;
		this.Login = Login;
		this.MotPasse = motPasse;
		this.Role = role;
		this.Archive = archive;
	}

    //endregion CTOR
	
    //region METHODS
    
	@Override
	public String toString() 
	{
		return "Personnels [CodePers=" + CodePers + ", Nom=" + Nom + ", MotPasse=" + MotPasse + ", Role=" + Role
				+ ", Archive=" + Archive + "]";
	}	
    
    //endregion METHODS

    //region GET/SET

	public int getCodePers() {
		return CodePers;
	}

	public void setCodePers(int codePers) {
		CodePers = codePers;
	}
	public String getNom() {
		return Nom;
	}
	public void setNom(String nom) {
		Nom = nom;
	}
	public String getMotPasse() {
		return MotPasse;
	}
	public void setMotPasse(String motPasse) {
		MotPasse = motPasse;
	}
	public String getRole() {
		return Role;
	}
	public void setRole(String role) {
		Role = role;
	}
	public boolean getArchive() {
		return Archive;
	}
	public void setArchive(boolean archive) {
		Archive = archive;
	}
	public String getPrenom() {
		return Prenom;
	}
	public void setPrenom(String prenom) {
		Prenom = prenom;
	}
	public String getLogin() {
		return Login;
	}
	public void setLogin(String login) {
		Login = login;
	}
	
	

    //endregion GET/SET
}
