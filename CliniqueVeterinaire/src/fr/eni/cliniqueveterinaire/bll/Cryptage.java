package fr.eni.cliniqueveterinaire.bll;


public class Cryptage {
	
	public static String encrypt(String MotPasse)
	{
		int c;
		String encrypt=""; 
		for (int i=0; i<MotPasse.length();i++)
		{ 
			c=MotPasse.charAt(i)^23;   // XOR 23 
			encrypt=encrypt+(char)c;  
		}
	  return encrypt;
	}
	
	public static String decrypt(String MotPasse)
	{
		int c;
		String decrypt=""; 
		for (int i=0; i<MotPasse.length();i++)
		{ 
			c=MotPasse.charAt(i)^23;
			decrypt=decrypt+(char)c;  
		} 
		return decrypt;
	}
}
