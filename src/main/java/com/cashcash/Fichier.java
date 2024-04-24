package com.cashcash;

import java.io.*;

public class Fichier {
	private BufferedWriter fW;
	private BufferedReader fR;

	private char mode;

	public void ouvrir(String nomDuFichier, String s) throws IOException {
		mode = (s.toUpperCase()).charAt(0);
		File f = new File(nomDuFichier);

		if (mode == 'R' || mode == 'L') {
			fR = new BufferedReader(new FileReader(f));
		} else if (mode == 'W' || mode == 'E') {
			fW = new BufferedWriter(new FileWriter(f));
		}
	}
	
	public String lire() throws IOException {
		String ligne = fR.readLine();
		return ligne;
	}
	
	public void ecrire(String ligne) throws IOException {
		if (ligne != null) {
			fW.write(ligne, 0, ligne.length());
			fW.newLine();
		}
	}
	
	public void ecrire(int nb) throws IOException {
		String ligne = "";
		ligne = String.valueOf(nb);
		
		if (ligne != null) {
			fW.write(ligne, 0, ligne.length());
			fW.newLine();
		}
	}
	
	public void fermer() throws IOException {
		if (mode == 'R' || mode == 'L') {
			fR.close();
		} else if (mode == 'W' || mode == 'E') {
			fW.close();
		}
	}
}