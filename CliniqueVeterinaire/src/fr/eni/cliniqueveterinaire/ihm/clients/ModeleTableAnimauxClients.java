package fr.eni.cliniqueveterinaire.ihm.clients;

import java.util.List;

import javax.swing.event.TableModelListener;
import fr.eni.cliniqueveterinaire.bo.*;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableModel;

public class ModeleTableAnimauxClients extends AbstractTableModel{
    private List<Animaux> data;
    private String[] title;

    public ModeleTableAnimauxClients(List<Animaux> liste, String[] title){
        this.data = liste;
        this.title = title;
    }
    
    //Retourne le titre de la colonne et l'indice
    public String getColumnName(int col) {
        return this.title[col];
    }
    
    //Retourne le nombre de colonnes
    public int getColumnCount() {
        return this.title.length;
    }
    
    //Retourne le nombre de lignes
    public int getRowCount() {
        return this.data.size();
    }
    
    //Retourne l'objet à l'intersection de ligne et colonne
    public Object getValueAt(int row, int col) {
        String aRetourner = null;
        switch(col){
        	case 0 : aRetourner = ""+data.get(row).getCodeAnimal();
        			 break;
        	case 1 : aRetourner = data.get(row).getNomAnimal();
        			 break;
        	case 2 : aRetourner = data.get(row).getsexe();
        			 break;
        	case 3 : aRetourner = data.get(row).getCouleur();
        			 break;
        	case 4 : aRetourner = data.get(row).getRace();
        		 	 break;
        	case 5 : aRetourner = data.get(row).getEspece();
        			 break;
        	case 6 : aRetourner = data.get(row).gettatouage();
        			 break;
        }   
    	return aRetourner;
    }
    
   /*
    //Modifier l'objet à l'intersection de ligne et colonne
    public void setValueAt(String value, int row, int col) {
        this.data[row][col] = value;
        switch(col){
    	case 0 : data.set(row, element)(row)=
    			 break;
    	case 1 : aRetourner = data.get(row).getNomAnimal();
    			 break;
    	case 2 : aRetourner = data.get(row).getsexe();
    			 break;
    	case 3 : aRetourner = data.get(row).getCouleur();
    			 break;
    	case 4 : aRetourner = data.get(row).getRace();
    		 	 break;
    	case 5 : aRetourner = data.get(row).getEspece();
    			 break;
    	case 6 : aRetourner = data.get(row).gettatouage();
    			 break;
    }   
    }*/
}
