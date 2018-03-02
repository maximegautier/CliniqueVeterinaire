package fr.eni.cliniqueveterinaire.bo;

import java.util.Date;

/* Créé par Erwin DUPUIS */
public class Agendas 
{
    //region DECLARATION

	private int codeVeto;
	private int codeAnimal;
	private Date dateRdv;

    //endregion DECLARATION

    //region CTOR

	public Agendas(int codeVeto, int codeAnimal, Date dateRdv)
	{
		this.codeVeto = codeVeto;
		this.codeAnimal = codeAnimal;
		this.dateRdv = dateRdv;
	}

    //endregion CTOR
	
    //region METHODS
    
	@Override
	public String toString() 
	{
		return "Agendas [CodeVeto=" + codeVeto + ", CodeAnimal=" + codeAnimal + ", DateRdv=" + dateRdv + "]";
	}
    
    //endregion METHODS
	
    //region GET/SET

	public int getCodeVeto() {
		return codeVeto;
	}

	public void setCodeVeto(int codeVeto) {
		this.codeVeto = codeVeto;
	}

	public int getCodeAnimal() {
		return codeAnimal;
	}

	public void setCodeAnimal(int codeAnimal) {
		this.codeAnimal = codeAnimal;
	}

	public Date getDateRdv() {
		return dateRdv;
	}

	public void setDateRdv(Date dateRdv) {
		this.dateRdv = dateRdv;
	}

    //endregion GET/SET
}
