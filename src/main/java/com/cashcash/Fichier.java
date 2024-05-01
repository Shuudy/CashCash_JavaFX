package com.cashcash;

import java.io.*;

/**
 * Gère la lecture et l'écriture de fichiers.
 */
public class Fichier {
	private BufferedWriter fW;
	private BufferedReader fR;

	private char mode;

	/**
     * Ouvre un fichier en mode lecture ou écriture.
     *
     * @param nomDuFichier Le nom du fichier à ouvrir.
     * @param s            Le mode d'ouverture du fichier ('R' pour lecture, 'W' pour écriture).
     * @throws IOException En cas d'erreur lors de l'ouverture du fichier.
     */
	public void ouvrir(String nomDuFichier, String s) throws IOException {
		mode = (s.toUpperCase()).charAt(0);
		File f = new File(nomDuFichier);

		if (mode == 'R' || mode == 'L') {
			fR = new BufferedReader(new FileReader(f));
		} else if (mode == 'W' || mode == 'E') {
			fW = new BufferedWriter(new FileWriter(f));
		}
	}
	
	/**
     * Lit une ligne à partir du fichier.
     *
     * @return La ligne lue depuis le fichier.
     * @throws IOException En cas d'erreur lors de la lecture du fichier.
     */
	public String lire() throws IOException {
		String ligne = fR.readLine();
		return ligne;
	}
	
	/**
     * Écrit une ligne dans le fichier.
     *
     * @param ligne La ligne à écrire dans le fichier.
     * @throws IOException En cas d'erreur lors de l'écriture dans le fichier.
     */
	public void ecrire(String ligne) throws IOException {
		if (ligne != null) {
			fW.write(ligne, 0, ligne.length());
			fW.newLine();
		}
	}
	
	/**
     * Écrit un nombre dans une ligne dans le fichier.
     *
     * @param nb Le nombre à écrire dans le fichier.
     * @throws IOException En cas d'erreur lors de l'écriture dans le fichier.
     */
	public void ecrire(int nb) throws IOException {
		String ligne = "";
		ligne = String.valueOf(nb);
		
		if (ligne != null) {
			fW.write(ligne, 0, ligne.length());
			fW.newLine();
		}
	}
	
	/**
     * Ferme le fichier.
     *
     * @throws IOException En cas d'erreur lors de la fermeture du fichier.
     */
	public void fermer() throws IOException {
		if (mode == 'R' || mode == 'L') {
			fR.close();
		} else if (mode == 'W' || mode == 'E') {
			fW.close();
		}
	}
}