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
	
	private String shelves = "";
	
	private String clients = "";
	
	private String totalGames = "";
	
	private int cashiers = 0;
	
	private int totalAmount = 0;
	
	private int clientsAmount = 0;
	
	private int shelvesAmount = 0;
	
	//strart
	public void loadMainPage(){
		loadPage("createCase.fxml");
	}
	
	@FXML
	private Pane mainPane;
	
	//CreateCase
	
	@FXML
    private TextArea labFinalCase;
	
	@FXML
    private Label labShelvesAmount;

    @FXML
    private Label labClientsAmount;
	
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
    void update(ActionEvent event) {
    	if(!txtCashierAmount.getText().equals("")) {
    		cashiers = Integer.parseInt(txtCashierAmount.getText());
    	}
    	loadPage("createCase.fxml");
    	labShelvesAmount.setText(String.valueOf(shelvesAmount));
    	labClientsAmount.setText(String.valueOf(clientsAmount));
    	labFinalCase.setText(inCaseProgress());
    	txtCashierAmount.setText(String.valueOf(cashiers));
    }

    @FXML
    void addCase(ActionEvent event) {
    	inCase = cashiers + "\n" + shelvesAmount +
    			"\n" + shelves + "\n" + clientsAmount + "\n" + clients;
    	
    	total += inCase;
    	totalAmount++;
    }

    @FXML
    void finish(ActionEvent event) {
    	total = totalAmount + total;
    	System.out.println(total);
    }

    @FXML
    void openAddClient(ActionEvent event) {
    	if(!txtCashierAmount.getText().equals("")) {
    		cashiers = Integer.parseInt(txtCashierAmount.getText());
    	}
    	loadPage("createClients.fxml");
    	labAbleGames.setText(totalGames);	
    	
    }

    @FXML
    void openAddShelve(ActionEvent event) {
    	if(!txtCashierAmount.getText().equals("")) {
    		cashiers = Integer.parseInt(txtCashierAmount.getText());
    	}
    	loadPage("createShelves.fxml");
    }
    
    //createClients

    @FXML
    private TextField txtClientId;

    @FXML
    private TextField txtClientGames;

    @FXML
    private TextArea labAbleGames;
    
    @FXML
    private Label labConfirmClient;
    
    public String[] getColumn(int x, String[][] str) {
    	String[] column = new String[str.length];
    	for (int i = 0; i < column.length; i++) {
			column[i] = str[i][x];
		}
    	return column;
    }
    
    public String[][] stringToMatrix(String s){
    	String[] rows = s.split("\n");
    	String[][] matrix = new String[rows.length][];
    	for (int i = 0; i < rows.length; i++) {
			matrix[i] = rows[i].split(" ");
		}
    	return matrix;
    }
    
    public boolean checkOrder(String[] libary,String[] client) {
    	boolean x = false;
    	for(int i = 0; i<client.length; i++) {
			for(int j = 0; j < libary.length && !x; j++) {
				if(client[i].equals(libary[j])) {
					x = true;
				}
			}
			if(!x) return false;
		}
    	return true;
    }
    
    @FXML
    void addClient(ActionEvent event) {
    	String[] storeGames = getColumn(0,stringToMatrix(totalGames));
    	
    	if(!txtClientId.getText().equals("") && !txtClientGames.getText().equals("")) {
    		String[] clientGames = txtClientGames.getText().split(" ");
    		if(checkOrder(storeGames,clientGames)) {
    			clients += "\n" + txtClientId.getText() + " " + txtClientGames.getText();
            	clientsAmount++;
            	txtClientId.setText("");
            	txtClientGames.setText("");
    		}else {
    			labConfirmClient.setText("Todos los juegos del cliente deben existir en el catálogo de juegos disponibles");
    		}
    	}else {
    		labConfirmClient.setText("Todods los espacios deben estar llenos");
    	}
    	
    }

    @FXML
    void back(ActionEvent event) {
    	loadPage("createCase.fxml");
    	labShelvesAmount.setText(String.valueOf(shelvesAmount));
    	labClientsAmount.setText(String.valueOf(clientsAmount));
    	labFinalCase.setText(inCaseProgress());
    	txtCashierAmount.setText(String.valueOf(cashiers));
    }
    
    public String inCaseProgress() {
    	String s = "";
    	s = cashiers + "\n" + shelvesAmount +
    			"\n" + shelves + "\n" + clientsAmount + "\n" + clients;
    	return s;
    }
    
    //createShelves
    
    @FXML
    private TextField txtShelveName;

    @FXML
    private TextField txtShelveFloors;

    @FXML
    private TextArea txtShelveGames;
    
    @FXML
    private Label labConfirmShelves;

    @FXML
    void addShelve(ActionEvent event) {
    	String shelve = "";
    	String[] games;
    	if(!txtShelveGames.getText().equals("") && !txtShelveName.getText().equals("") && !txtShelveFloors.getText().equals("")) {
    		games = txtShelveGames.getText().split("\n");
    		
    		try {
    			if(games.length == Integer.parseInt(txtShelveFloors.getText())) {
        			shelve = txtShelveName.getText() + " " + txtShelveFloors.getText();
        	    	for(int i = 0; i < games.length ;i++) {
        	    		shelve += "\n" + games[i];
        	    		totalGames += "\n" + games[i];
        	    	}
        	    	shelves += "\n" + shelve;
        	    	shelvesAmount++;
        	    	
        	    	txtShelveName.setText("");
        	    	txtShelveFloors.setText("");
        	    	txtShelveGames.setText("");
        	    	labConfirmShelves.setText("");
        		}else {
        			labConfirmShelves.setText("La cantidad de juegos debe ser igual a la cantidad de pisos");
        		}
    		}catch (NumberFormatException e) {
    			labConfirmShelves.setText("La cantidad de pisos debe ser un número");
    		}
    	}else {
    		labConfirmShelves.setText("Todods los espacioos deben estar llenos");
    	}
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
