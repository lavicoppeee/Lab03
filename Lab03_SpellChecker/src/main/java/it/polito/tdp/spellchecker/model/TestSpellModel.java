package it.polito.tdp.spellchecker.model;



public class TestSpellModel {


	
	private void run() {
		
		Dictionary dizionario=new Dictionary();
		dizionario.loadDictionary("Italian");
		
		System.out.println(dizionario);
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		TestSpellModel test=new TestSpellModel();
		test.run();
	}

}
