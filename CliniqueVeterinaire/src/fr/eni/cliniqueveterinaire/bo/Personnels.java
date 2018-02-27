package fr.eni.cliniqueveterinaire.bo;

public class Personnels 
{
    //region DECLARATION
	
	private int CodePers;
	private String Nom;
	private String MotPasse;
	private String Role;
	private boolean Archive;
	private String cle;
	
    //endregion DECLARATION

    //region CTOR

	public Personnels(int codePers, String nom, String motPasse, String role,String cle,boolean archive) 
	{
		this.CodePers = codePers;
		this.Nom = nom;
		this.MotPasse = motPasse;
		this.Role = role;
		this.Archive = archive;
		this.cle = cle;
	}
	public Personnels(String nom, String motPasse, String role, String cle, boolean archive) 
	{
		this.Nom = nom;
		this.MotPasse = motPasse;
		this.Role = role;
		this.Archive = archive;
		this.cle = cle;
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
	public String getCle() {
		return cle;
	}
	public void setCle(String cle) {
		cle = cle;
	}
	
	

    //endregion GET/SET
}
