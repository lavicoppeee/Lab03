package it.polito.tdp.spellchecker.model;

import java.util.*;
import java.io.*;

public class Dictionary {

	private List<String> dictionary;
	private String language;

	public Dictionary() {
		super();
	}
	
/**
 * Carica il file del dizionario a seconda della lingua.
 * 1. Controlla se il dizionario è vuotp e se le lingua corrisponde
 * 2. Dichiara la lista
 * 3.Legge il file ed aggiunge le parole in minuscolo
 * 4.Ordina 
 * 5.chiude e controlla
 * 
 * @param language, LINGUA DEL DIZIONARIO PASSATA DA PARAMETRO
 * @return true se il dizionario è caricato correttamente 
 *         false se il dizionario non è stato caricato correttamente 
 */
	
	public boolean loadDictionary(String language) {

		if (dictionary != null && this.language.equals(language))
			return true;

		dictionary = new ArrayList<String>();
		this.language = language;

		try {
			FileReader fr = new FileReader("src/main/resources/" + language + ".txt");
			BufferedReader br = new BufferedReader(fr);
			String word;

			while ((word = br.readLine()) != null) {
				dictionary.add(word.toLowerCase());
			}
			Collections.sort(dictionary);
			br.close();
			System.out.println("Dizionario di " + language + " caricato e contiene" + dictionary.size() + " parole.");
			return true;

		} catch (IOException e) {

			System.out.println("Errore nella lettura del file");
			return false; 
		}

	}
	/**
	 * Per ogni elemento di input list controllare se la parola 
	 * è presente nel dizionario e se è corretta
	 * 
	 * @param inputTextList parole inserite
	 * @return lista di parole 
	 */
	
	public List<RichWord> spellCheckText(List<String> inputTextList){
		List<RichWord> word= new ArrayList<RichWord>();
		
		for(String s: inputTextList) {
			RichWord r= new RichWord(s);
			if(dictionary.contains(s.toLowerCase())) {
				r.setCorrect(true);
			}else {
				r.setCorrect(false);
			}
			word.add(r); 
		}
		return word;
	}

	
	public List<RichWord> spellCheckTextLinear(List<String> inputTextList){
		List<RichWord> word= new ArrayList<RichWord>();
		
		for(String s: inputTextList) {
			RichWord r= new RichWord(s);
			
			boolean found=false;
			
			for(String w: dictionary) {
				if(w.equalsIgnoreCase(s)) {
					found=true;
					break;
				}
			}
			if(found) {
				r.setCorrect(true);
			}else {
				r.setCorrect(false);
			}
			word.add(r); 
		}
		return word;
	}

	public List<RichWord> spellCheckTextDichotomic(List<String> inputTextList) {

		List<RichWord> parole = new ArrayList<RichWord>();

		for (String str : inputTextList) {

			RichWord richWord = new RichWord(str);
			if (binarySearch(str.toLowerCase()))
				richWord.setCorrect(true);
			else
				richWord.setCorrect(false);
			parole.add(richWord);
		}

		return parole;
	}

	private boolean binarySearch(String stemp) {
		int inizio = 0;
		int fine = dictionary.size();

		while (inizio != fine) {
			int medio = inizio + (fine - inizio) / 2;
			if (stemp.compareToIgnoreCase(dictionary.get(medio)) == 0) {
				return true;
			} else if (stemp.compareToIgnoreCase(dictionary.get(medio)) > 0) {
				inizio = medio + 1;
			} else {
				fine = medio;
			}
		}

		return false;
	}
	
}
