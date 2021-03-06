package it.polito.tdp.simulatoreNBA.controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.ResourceBundle;

import it.polito.tdp.simulatoreNBA.model.Match;
import it.polito.tdp.simulatoreNBA.model.Model;
import it.polito.tdp.simulatoreNBA.model.Player;
import it.polito.tdp.simulatoreNBA.model.PlayerAVGStats;
import it.polito.tdp.simulatoreNBA.model.Team;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class FinalsController {
	
	Model model;
	Stage stage;
	
	

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField txtWestWinner;
    
    @FXML
    private TableView<PlayerAVGStats> tableWest;

    @FXML
    private TableColumn<PlayerAVGStats, String> columnPlayerWest;
    
    @FXML
    private TableColumn<PlayerAVGStats, Integer> columnGameWest;

    @FXML
    private TableColumn<PlayerAVGStats, Double> columnPointsWest;

    @FXML
    private TableColumn<PlayerAVGStats, Double> columnAssistsWest;

    @FXML
    private TableColumn<PlayerAVGStats, Double> columnStopWest;

    @FXML
    private TableColumn<PlayerAVGStats, Double> columnStealsWest;

    @FXML
    private TextField txtEastWinner;
    
    @FXML
    private TableView<PlayerAVGStats> tableEast;

    @FXML
    private TableColumn<PlayerAVGStats, String> columnPlayerEast;
    
    @FXML
    private TableColumn<PlayerAVGStats, Integer> columnGameEast;

    @FXML
    private TableColumn<PlayerAVGStats, Double> columnPointsEast;

    @FXML
    private TableColumn<PlayerAVGStats, Double> columnAssistsEast;

    @FXML
    private TableColumn<PlayerAVGStats, Double> columnStopEast;

    @FXML
    private TableColumn<PlayerAVGStats, Double> columnStealsEast;

    @FXML
    private TextField txtChamp;

    @FXML
    private TextArea txtAreaResults;
    
    @FXML
    private Button btnStats;

    @FXML
    void doSimulaFinals(ActionEvent event) {
    	
    	if(model.getGlobalWinner() == null) {
    		
    		txtAreaResults.clear();
        	txtChamp.clear();
        	
        	Team champ = model.SimulationWinner(model.getWestWinner(), model.getEastWinner());
        	
        	model.setGlobalWinner(champ);
        	
        	txtChamp.appendText(champ.getName());
        	
        	List<PlayerAVGStats> westStats = new ArrayList<PlayerAVGStats>();
    		
    		for(Player p : model.getWestWinner().getPlayers()) {
    			westStats.add(model.avgByPlayer(p));
    		}
    		
    		Collections.sort(westStats); //prima chi ha segnato pi� punti
    		
    		ObservableList<PlayerAVGStats> values = FXCollections.observableArrayList(westStats);
    		tableWest.setItems(values);
    		
    		List<PlayerAVGStats> eastStats = new ArrayList<PlayerAVGStats>();
    		
    		for(Player p : model.getEastWinner().getPlayers()) {
    			eastStats.add(model.avgByPlayer(p));
    		}
    		
    		Collections.sort(eastStats);//prima chi ha segnato pi� punti
    		
    		ObservableList<PlayerAVGStats> valuesE = FXCollections.observableArrayList(eastStats);
    		tableEast.setItems(valuesE);
    		
    		txtAreaResults.appendText("<<< RISULTATI PARTITE >>>\n *** Vincente: " + champ.getName() +" ***\n");
    		txtAreaResults.appendText(model.getSeriesMapWest().get(8).toString() + "\n");
    		
        	List<Match> finals = model.getSeriesMapWest().get(8).getMatches();
    		
        	for(Match m : finals) {
        		txtAreaResults.appendText(m.toString() + "\n");
        	}
    		
    		txtAreaResults.appendText("\nMiglior Giocatore delle finali:\n");
    		txtAreaResults.appendText(model.findMVP().getName().toUpperCase());
        	
        	
        	
    		
    	}else {
    		
    		txtAreaResults.appendText("\nAttenzione: � gi� stato decretato il vincitore.");
    	}
    	
    	btnStats.setDisable(false);
    	
    	

    }
    
    @FXML
    void doViewStats(ActionEvent event) {
    	
    	try {
    		
    		FXMLLoader loader = new FXMLLoader(getClass().getResource("StatsForGame.fxml"));
			BorderPane root = (BorderPane) loader.load();
			Scene scene = new Scene(root);
			
			if(txtChamp.getText().equals("")) {
				txtAreaResults.appendText("Prima di accedere alle statistiche, � necessario simulare la partita.");
				return;
			}
			
			StatsController controller = loader.getController();
			controller.setModel(model, stage, "finale");
			
			Stage s= new Stage();
			
			s.setScene(scene);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			s.setTitle("Statistiche Serie");
			s.show();
			
			
		
		
			
		} catch(Exception e) {
			e.printStackTrace();
		}

    }

    @FXML
    void initialize() {
        assert txtWestWinner != null : "fx:id=\"txtWestWinner\" was not injected: check your FXML file 'GoToFinals.fxml'.";
        assert tableWest != null : "fx:id=\"tableWest\" was not injected: check your FXML file 'GoToFinals.fxml'.";
        assert columnPlayerWest != null : "fx:id=\"columnPlayerWest\" was not injected: check your FXML file 'GoToFinals.fxml'.";
        assert columnGameWest != null : "fx:id=\"columnGameWest\" was not injected: check your FXML file 'GoToFinals.fxml'.";
        assert columnPointsWest != null : "fx:id=\"columnPointsWest\" was not injected: check your FXML file 'GoToFinals.fxml'.";
        assert columnAssistsWest != null : "fx:id=\"columnAssistsWest\" was not injected: check your FXML file 'GoToFinals.fxml'.";
        assert columnStopWest != null : "fx:id=\"columnStopWest\" was not injected: check your FXML file 'GoToFinals.fxml'.";
        assert columnStealsWest != null : "fx:id=\"columnStealsWest\" was not injected: check your FXML file 'GoToFinals.fxml'.";
        assert txtEastWinner != null : "fx:id=\"txtEastWinner\" was not injected: check your FXML file 'GoToFinals.fxml'.";
        assert tableEast != null : "fx:id=\"tableEast\" was not injected: check your FXML file 'GoToFinals.fxml'.";
        assert columnPlayerEast != null : "fx:id=\"columnPlayerEast\" was not injected: check your FXML file 'GoToFinals.fxml'.";
        assert columnGameEast != null : "fx:id=\"columnGameEast\" was not injected: check your FXML file 'GoToFinals.fxml'.";
        assert columnPointsEast != null : "fx:id=\"columnPointsEast\" was not injected: check your FXML file 'GoToFinals.fxml'.";
        assert columnAssistsEast != null : "fx:id=\"columnAssistsEast\" was not injected: check your FXML file 'GoToFinals.fxml'.";
        assert columnStopEast != null : "fx:id=\"columnStopEast\" was not injected: check your FXML file 'GoToFinals.fxml'.";
        assert columnStealsEast != null : "fx:id=\"columnStealsEast\" was not injected: check your FXML file 'GoToFinals.fxml'.";
        assert txtChamp != null : "fx:id=\"txtChamp\" was not injected: check your FXML file 'GoToFinals.fxml'.";
        assert txtAreaResults != null : "fx:id=\"txtAreaResults\" was not injected: check your FXML file 'GoToFinals.fxml'.";
        assert btnStats != null : "fx:id=\"btnStats\" was not injected: check your FXML file 'GoToFinals.fxml'.";

        
        columnPlayerWest.setCellValueFactory(new PropertyValueFactory<PlayerAVGStats, String>("name"));
        columnGameWest.setCellValueFactory(new PropertyValueFactory<PlayerAVGStats, Integer>("ngames"));
        columnPointsWest.setCellValueFactory(new PropertyValueFactory<PlayerAVGStats, Double>("point"));      
        columnAssistsWest.setCellValueFactory(new PropertyValueFactory<PlayerAVGStats, Double>("assist"));
        columnStopWest.setCellValueFactory(new PropertyValueFactory<PlayerAVGStats, Double>("rebounds")); 
        columnStealsWest.setCellValueFactory(new PropertyValueFactory<PlayerAVGStats, Double>("block")); 
        
        columnPlayerEast.setCellValueFactory(new PropertyValueFactory<PlayerAVGStats, String>("name"));
        columnGameEast.setCellValueFactory(new PropertyValueFactory<PlayerAVGStats, Integer>("ngames"));
        columnPointsEast.setCellValueFactory(new PropertyValueFactory<PlayerAVGStats, Double>("point"));      
        columnAssistsEast.setCellValueFactory(new PropertyValueFactory<PlayerAVGStats, Double>("assist"));
        columnStopEast.setCellValueFactory(new PropertyValueFactory<PlayerAVGStats, Double>("rebounds"));
        columnStealsEast.setCellValueFactory(new PropertyValueFactory<PlayerAVGStats, Double>("block")); 

    }
    
    public void setModel(Model model, Stage stage) {
		this.model = model;
		this.stage = stage;
		txtEastWinner.appendText(model.getEastWinner().getName());
		txtWestWinner.appendText(model.getWestWinner().getName());
	}
}
