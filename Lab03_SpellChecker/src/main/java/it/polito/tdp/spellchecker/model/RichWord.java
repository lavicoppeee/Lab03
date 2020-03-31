package it.polito.tdp.spellchecker.model;

public class RichWord {

	/**
	 * Ogni istanza della classe è caratterizzata da 
	 * una parola e da un booleano
	 * che conferma se la parola è giusta oppure no.
	 * 
	 * MA QUI NON SI CONTROLLA LA CORRETEZZA DELLA PAROLA. 
	 */
	private String parola;
	private boolean correct;
	
	public RichWord(String parola, boolean correct) {
		super();
		this.parola = parola;
		this.correct = correct;
	}
	
	public RichWord(String parola) {
		this.parola=parola;
	}

	public String getParola() {
		return parola;
	}

	public void setParola(String parola) {
		this.parola = parola;
	}

	public boolean isCorrect() {
		return correct;
	}

	public void setCorrect(boolean correct) {
		this.correct = correct;
	}
	
	
}
