package it.polito.tdp.spellchecker;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.*;

import it.polito.tdp.spellchecker.model.Dictionary;
import it.polito.tdp.spellchecker.model.RichWord;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;

public class FXMLController {

	private Dictionary dictionary;
	private List<String> inputTextList;
	
	
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ComboBox<String> btnChoice;

    @FXML
    private TextArea txtControll;

    @FXML
    private Button btnCheck;

    @FXML
    private TextArea txtResult;

    @FXML
    private Label txtError;

    @FXML
    private Button btnReset;

    @FXML
    private Label txtTime;
/**
 * Seleziona la lingua, nel caso in cui non sia fatto ricordagli di selezionarla
 * @param event
 */
    @FXML
	void doChoice(ActionEvent event) {

		if (btnChoice.getValue() != null) {
			
			//quando svolge l'azione li imposto a false
			txtControll.setDisable(false);
			btnCheck.setDisable(false);
			btnReset.setDisable(false);
			txtResult.setDisable(false);
			txtControll.clear();
			txtResult.clear();

		} else {

			txtResult.setDisable(true);
			btnCheck.setDisable(true);
			btnReset.setDisable(true);
			txtControll.setDisable(true);
			txtControll.setText("Seleziona lingua!");
		}
	}

    @FXML
    void doClearText(ActionEvent event) {
    	txtControll.clear();
    	txtResult.clear();
    	
    }

    @FXML
    void doSpellCheck(ActionEvent event) {
    	
    	txtResult.clear();
    	inputTextList=new LinkedList<String>();
    	
    	//controllo se ha selezionato la lingua
    	if(btnChoice.getValue()==null) {
    		txtControll.setText("Seleziona lingua");
    		return;
    	}
    	
    	//controllo se il dizionario è caricato bene
    	if(!dictionary.loadDictionary(btnChoice.getValue())) {
    		txtControll.setText("Errore nel caricamento del Dizionario");
    		return;
    	}
    	
    	String input=txtControll.getText();
    	
    	//controllo se ho inserito il testo da corregere
    	if(input.isEmpty()) {
    		txtControll.setText("Inserisci testo da controllare!");
    		return;
    	}
    	
    	//elimino tutti i caratteri strani
    	input=input.replaceAll("\n", " ");
    	input=input.replaceAll("[.,\\/#!$%\\^&\\*;:{}=\\-_`~()\\[\\]]", "");
    	
    	//levo lo spazio
    	StringTokenizer st= new StringTokenizer(input, " ");
    	//aggiungo tutte le parole alla lista 
    	while(st.hasMoreTokens()) {
    		inputTextList.add(st.nextToken());
    	}
    	
    	//inizio del tempo operazione
    	long start=System.nanoTime();
    	
    	List<RichWord> outputTextList;
    	
    	outputTextList=dictionary.spellCheckText(inputTextList);
    	
    	//fine tempo operazione
    	long end=System.nanoTime();
    	
    	int numErr=0;
    	
    	//elenco di stringhe
    	StringBuilder richText=new StringBuilder();
    	
    	//Stampa tutte le parole sbagliate a capo 
    	for(RichWord r: outputTextList) {
    		if(!r.isCorrect()) {
    			numErr++;
    			richText.append(r.getParola()+"\n");
    		}
    	}
    	
    	
    	txtResult.setText(richText.toString());
    	txtError.setText(numErr+" errors ");
    	txtTime.setText((end-start)/1E9 +" seconds");
    	
    }

    @FXML
    void initialize() {
        assert btnChoice != null : "fx:id=\"btnChoice\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtControll != null : "fx:id=\"txtControll\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnCheck != null : "fx:id=\"btnCheck\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtError != null : "fx:id=\"txtError\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnReset != null : "fx:id=\"btnReset\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtTime != null : "fx:id=\"txtTime\" was not injected: check your FXML file 'Scene.fxml'.";

    }

	public void setModel(Dictionary model) {
		// TODO Auto-generated method stub
		
		txtControll.setDisable(true);
		txtControll.setText("Seleziona lingua!");
		
		txtResult.setDisable(true);
		btnChoice.getItems().addAll("English","Italian");
		
		btnCheck.setDisable(true);
		btnReset.setDisable(true);
		
		this.dictionary=model;
	}
}



