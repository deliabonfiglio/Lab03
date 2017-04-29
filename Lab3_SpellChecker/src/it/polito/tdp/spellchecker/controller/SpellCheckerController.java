/**
 * Sample Skeleton for 'SpellChecker.fxml' Controller Class
 */

package it.polito.tdp.spellchecker.controller;
import javafx.scene.control.TextArea;
import java.net.URL;
import java.util.*;

import it.polito.tdp.spellchecker.model.Dictionary;
import it.polito.tdp.spellchecker.model.RichWord;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;

public class SpellCheckerController {
	private Dictionary dizionario;

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="cmbLang"
    private ComboBox<String> cmbLang; // Value injected by FXMLLoader

    @FXML // fx:id="txtArea"
    private TextArea txtArea; // Value injected by FXMLLoader
    
    @FXML // fx:id="btnCheck"
    private Button btnCheck; // Value injected by FXMLLoader

    @FXML // fx:id="txtWrongWords"
    private TextArea txtWrongWords; // Value injected by FXMLLoader
    @FXML // fx:id="txtQuantiErrori"
    private Label txtQuantiErrori; // Value injected by FXMLLoader

    @FXML // fx:id="btnReset"
    private Button btnReset; // Value injected by FXMLLoader

    @FXML // fx:id="txtTime"
    private Label txtTime; // Value injected by FXMLLoader

    @FXML
    void doCheck(ActionEvent event) {
    	int errori =0;
    	float start= System.nanoTime();
    	
    	//carico il dizionario della lingua selezionata
    	dizionario.loadDictionary(cmbLang.getValue());
    	
    	String testo= txtArea.getText();
    		if(testo.isEmpty()){
    			return;	
    		}
    	
    	StringTokenizer st = new StringTokenizer(testo, " ");
    	List<String> inputList= new ArrayList<String>();
    	
    	while(st.hasMoreTokens()){
    		inputList.add(st.nextToken().replaceAll("[ \\p{Punct}]", "").trim().toLowerCase());
    	}
    	
    	String richText="";
    	
    	for( RichWord parola: dizionario.spellCheckText(inputList)){
    		if(!parola.isCorretto()){
    			errori ++;
				richText += parola.getInput();
				richText += "\n";
    		}
    	}
    	float end= System.nanoTime();
    	
    	txtWrongWords.setText(richText);
    	txtQuantiErrori.setVisible(true);
    	txtQuantiErrori.setText("The text contains "+ errori+ " errors.");   
    	txtTime.setVisible(true);
    	txtTime.setText("Seconds elapsed: " +(end-start)/1e9);
 
    }

    @FXML
    void doReset(ActionEvent event) {
    	txtArea.clear();
    	txtWrongWords.clear();
    	txtQuantiErrori.setVisible(false);
    	txtTime.setVisible(false);
    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert cmbLang != null : "fx:id=\"cmbLang\" was not injected: check your FXML file 'SpellChecker.fxml'.";
        assert txtArea != null : "fx:id=\"txtArea\" was not injected: check your FXML file 'SpellChecker.fxml'.";
        assert btnCheck != null : "fx:id=\"btnCheck\" was not injected: check your FXML file 'SpellChecker.fxml'.";
        assert txtWrongWords != null : "fx:id=\"txtWrongWords\" was not injected: check your FXML file 'SpellChecker.fxml'.";
        assert txtQuantiErrori != null : "fx:id=\"txtQuantiErrori\" was not injected: check your FXML file 'SpellChecker.fxml'.";
        assert btnReset != null : "fx:id=\"btnReset\" was not injected: check your FXML file 'SpellChecker.fxml'.";
        assert txtTime != null : "fx:id=\"txtTime\" was not injected: check your FXML file 'SpellChecker.fxml'.";

        cmbLang.getItems().addAll("English","Italiano");
        this.dizionario = new Dictionary();
    }
}
