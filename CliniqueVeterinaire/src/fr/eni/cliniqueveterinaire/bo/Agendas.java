package fr.eni.cliniqueveterinaire.bo;

import java.util.Date;

/* Créé par Erwin DUPUIS */
public class Agendas 
{
    //region DECLARATION

	private int CodeVeto;
	private int CodeAnimal;
	private Date DateRdv;

    //endregion DECLARATION

    //region CTOR

	public Agendas(int codeVeto, int codeAnimal, Date dateRdv)
	{
		this.CodeVeto = codeVeto;
		this.CodeAnimal = codeAnimal;
		this.DateRdv = dateRdv;
	}

    //endregion CTOR
	
    //region METHODS
    
	@Override
	public String toString() 
	{
		return "Agendas [CodeVeto=" + CodeVeto + ", CodeAnimal=" + CodeAnimal + ", DateRdv=" + DateRdv + "]";
	}
    
    //endregion METHODS
	
    //region GET/SET

	public int getCodeVeto() {
		return CodeVeto;
	}

	public void setCodeVeto(int codeVeto) {
		CodeVeto = codeVeto;
	}

	public int getCodeAnimal() {
		return CodeAnimal;
	}

	public void setCodeAnimal(int codeAnimal) {
		CodeAnimal = codeAnimal;
	}

	public Date getDateRdv() {
		return DateRdv;
	}

	public void setDateRdv(Date dateRdv) {
		DateRdv = dateRdv;
	}

    //endregion GET/SET
}
