
/**
 * Sample Skeleton for 'Scene.fxml' Controller Class
 */

package it.polito.tdp.PremierLeague;

import java.net.URL;
import java.util.ResourceBundle;

import it.polito.tdp.PremierLeague.model.Match;
import it.polito.tdp.PremierLeague.model.Model;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class FXMLController {

	private Model model;

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="btnCreaGrafo"
    private Button btnCreaGrafo; // Value injected by FXMLLoader

    @FXML // fx:id="btnGiocatoreMigliore"
    private Button btnGiocatoreMigliore; // Value injected by FXMLLoader

    @FXML // fx:id="btnSimula"
    private Button btnSimula; // Value injected by FXMLLoader

    @FXML // fx:id="cmbMatch"
    private ComboBox<Match> cmbMatch; // Value injected by FXMLLoader

    @FXML // fx:id="txtN"
    private TextField txtN; // Value injected by FXMLLoader

    @FXML // fx:id="txtResult"
    private TextArea txtResult; // Value injected by FXMLLoader

    @FXML
    void doCreaGrafo(ActionEvent event) {
    	txtResult.clear();
    	Match m = cmbMatch.getValue();
    	if(m == null) {
    		txtResult.appendText("Seleziona il match");
    		return;
    	}
    	
    	this.model.creaGrafo(m);
    	
    	txtResult.appendText("Grafo creato!\n");
    	txtResult.appendText("#Vertici: " + this.model.nVertici() + "\n");
    	txtResult.appendText("#Archi: " + this.model.nArchi() + "\n");
    }

    @FXML
    void doGiocatoreMigliore(ActionEvent event) {
    	txtResult.clear();
    	if(this.model.getGrafo() == null) {
    		txtResult.appendText("Crea prima il grafo!");
        	return;
        }
    	
    	txtResult.appendText("Giocatore migliore: \n" + this.model.getMigliore());
    	
    }
    
    @FXML
    void doSimula(ActionEvent event) {
    	txtResult.clear();
    	
    	if(this.model.getGrafo() == null) {
    		txtResult.appendText("Crea prima il grafo!");
        	return;
        }
    	    	
    	try {
    		Match m = cmbMatch.getValue();
        	int N = Integer.parseInt(txtN.getText());
        	
        	String risultato = this.model.simula(m, N);
        	this.txtResult.appendText(risultato);
    		
    	} catch(NumberFormatException n) {
    		this.txtResult.setText("Inserire un intero");
    		return;
    	}
    		
    		
    	
    	

    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert btnCreaGrafo != null : "fx:id=\"btnCreaGrafo\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnGiocatoreMigliore != null : "fx:id=\"btnGiocatoreMigliore\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnSimula != null : "fx:id=\"btnSimula\" was not injected: check your FXML file 'Scene.fxml'.";
        assert cmbMatch != null : "fx:id=\"cmbMatch\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtN != null : "fx:id=\"txtN\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'Scene.fxml'.";

    }
    
    public void setModel(Model model) {
    	this.model = model;
    	this.cmbMatch.getItems().addAll(this.model.getTuttiMatch());
    }
}
