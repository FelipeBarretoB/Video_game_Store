package ui;

import java.io.BufferedReader;
import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

public class VideoGameStoreGUI {
	
	private String total;
	
	private String inCase;
	
	private String shelves;
	
	private String clients;
	
	private int totalAmount;
	
	private int clientsAmount;
	
	private int shelvesAmount;
	
	//strart
	public void loadMainPage(){
		loadPage("createCase.fxml");
	}
	
	@FXML
	private Pane mainPane;
	
	//CreateCase
	
	@FXML
    private ListView<String> lvShelves;

    @FXML
    private TextField txtCashierAmount;

    @FXML
    private ListView<String> lvGames;

    @FXML
    private Label labTotalCases;

    @FXML
    private ListView<String> lvClients;

    @FXML
    void addCase(ActionEvent event) {
    	inCase = txtCashierAmount.getText() + "\n" + shelvesAmount +
    			"\n" + shelves + "\n" + clientsAmount + "\n" + clients;
    	
    	total += inCase;
    }

    @FXML
    void finish(ActionEvent event) {
    	total = totalAmount + total;
    }

    @FXML
    void openAddClient(ActionEvent event) {
    	loadPage("createClients.fxml");
    }

    @FXML
    void openAddShelve(ActionEvent event) {
    	loadPage("createShelves.fxml");
    }
    
    //createClients

    @FXML
    private TextField txtClientId;

    @FXML
    private TextField txtClientGames;

    @FXML
    private ListView<String> lvGamesInClient;
    
    @FXML
    void addClient(ActionEvent event) {
    	clients += "\n" + txtClientId.getText() + " " + txtClientGames.getText();
    	clientsAmount++;
    }

    @FXML
    void back(ActionEvent event) {
    	loadPage("createCase.fxml");
    }
    
    //createShelves
    
    @FXML
    private TextField txtShelveName;

    @FXML
    private TextField txtShelveFloors;

    @FXML
    private TextArea txtShelveGames;

    @FXML
    void addShelve(ActionEvent event) {
    	String shelve = "";
    	String[] games = txtShelveGames.getText().split("\n");
    	shelve = txtShelveName.getText() + " " + txtShelveName.getText();
    	for(int i = 0; i < games.length ;i++) {
    		shelve += "\n" + games[0];
    	}
    	inCase += shelve;
    	shelvesAmount++;
    }
	
    public void loadPage(String page) {
    	try {
			FXMLLoader fxmlLoader= new FXMLLoader(getClass().getResource(page));
			fxmlLoader.setController(this);
			Parent login;
			login = fxmlLoader.load();
			mainPane.getChildren().setAll(login);
		} catch (IOException e) {
			e.printStackTrace();
		} 
    }
	
}
