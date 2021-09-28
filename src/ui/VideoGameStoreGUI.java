package ui;


import java.io.IOException;
import dataStructureQueue.Queue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import model.Store;

public class VideoGameStoreGUI {
	
	private Store store;
	
	private String total = "";
	
	private String inCase = "";
	
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
    private TextArea txaFinalCase;
	
	@FXML
    private Label labStatus;
	
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
    	txaFinalCase.setText(inCaseProgress());
    	txtCashierAmount.setText(String.valueOf(cashiers));
    	labTotalCases.setText(String.valueOf(totalAmount));
    }

    @FXML
    void addCase(ActionEvent event) {
    	inCase = cashiers + "\n" + shelvesAmount + shelves + "\n" + clientsAmount + clients;
    	total += inCase;
    	totalAmount++;
    	clearAll();
    	txtCashierAmount.setText("");
    	txaFinalCase.setText("");
    	labTotalCases.setText(String.valueOf(totalAmount));
    }
    
    void clearAll() {
    	cashiers = 0;
    	shelvesAmount = 0;
    	clientsAmount = 0;
    	shelves = "";
    	clients = "";
    	inCase = "";
    }
    @FXML
    void finish(ActionEvent event) throws IOException {
    	total = totalAmount + "\n"+total;
    	System.out.println(total);
    	System.out.println(numOfCases(total));
    	labStatus.setText("Resultado final");
    	txaFinalCase.setText(numOfCases(total));    	
    	
    }
    
    public Queue<String> arrayToQueue(String [] array) {
    	Queue<String> queue = new Queue<String>();
    	for (int i = 0; i < array.length; i++) {
			queue.add(array[i]);
		}
    	return queue;
    }
    
    public String numOfCases(String end) throws NumberFormatException, IOException {
    	String[] tem = end.split("\n");
    	String ans = "";
    	Queue<String> problem = arrayToQueue(tem);
    	
    	
		int cases= Integer.parseInt(problem.poll());
		while(cases!=0){
			int cashRegisters = Integer.parseInt(problem.poll());
			int stands = Integer.parseInt(problem.poll());
			store= new Store(cashRegisters, stands);
			while(stands!=0) {
				problem = createNewStand(problem);
				stands--;
			}
			int numOfClients= Integer.parseInt(problem.poll());
			store.setNumOfClients(numOfClients);
			store.createClientList();
			while(numOfClients!=0) {
				store.addClients(problem.poll());
				numOfClients--;
			}
			store.orderClientLists();
			store.stackGames();
			store.orderClientsByTime();
			
			ans += store.cashRegisters();
			cases--;
		}
		return ans;
	}
    
    public Queue <String> createNewStand(Queue<String> stants) throws IOException {
		String nameAndLevels= stants.poll();
		String[] separateNameAndLevel=nameAndLevels.split(" ");
		String name= separateNameAndLevel[0];
		int levels= Integer.parseInt(separateNameAndLevel[1]);
		String[] values= new String[levels];
		for(int c=0; c< values.length;c++) {
			values[c]=stants.poll();
		}
		store.createStand(name, levels, values);
		return stants;
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
    	txaFinalCase.setText(inCaseProgress());
    	txtCashierAmount.setText(String.valueOf(cashiers));
    	labTotalCases.setText(String.valueOf(totalAmount));
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

	public String getTotal() {
		return total;
	}

	public void setTotal(String total) {
		this.total = total;
	}

	public String getInCase() {
		return inCase;
	}

	public void setInCase(String inCase) {
		this.inCase = inCase;
	}

	public String getShelves() {
		return shelves;
	}

	public void setShelves(String shelves) {
		this.shelves = shelves;
	}

	public String getClients() {
		return clients;
	}

	public void setClients(String clients) {
		this.clients = clients;
	}

	public String getTotalGames() {
		return totalGames;
	}

	public void setTotalGames(String totalGames) {
		this.totalGames = totalGames;
	}

	public int getCashiers() {
		return cashiers;
	}

	public void setCashiers(int cashiers) {
		this.cashiers = cashiers;
	}

	public int getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(int totalAmount) {
		this.totalAmount = totalAmount;
	}

	public int getClientsAmount() {
		return clientsAmount;
	}

	public void setClientsAmount(int clientsAmount) {
		this.clientsAmount = clientsAmount;
	}

	public int getShelvesAmount() {
		return shelvesAmount;
	}

	public void setShelvesAmount(int shelvesAmount) {
		this.shelvesAmount = shelvesAmount;
	}
	
    
}
