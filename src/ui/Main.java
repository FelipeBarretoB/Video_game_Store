package ui;
/*
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import model.Store;
*/
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;



//Buenas como veras, hay muchas cosas documentadas, todo lo que este documentado aqu�, es lo que hicimos primero, es decir, el programa por consola, este deber�a funcionar si des documentas todo, as� que si quieres dale
//Adem�s, usamos los mismos m�todos que usamos para la GUI, as� que tambien los puedes revisar por aqu�, si quieres. En todo caso, que tengas un buen d�a persona que est� leyendo mi p�sima ortograf�a.
public class Main extends Application {
	
	private VideoGameStoreGUI videoGameStoreGUI;
	/*
	private BufferedReader br;
	private BufferedWriter bw;
	private Store store;
	*/

	public Main() {
		videoGameStoreGUI = new VideoGameStoreGUI();
		/*
		store= new Store();
		br= new BufferedReader(new InputStreamReader(System.in));
		bw= new BufferedWriter(new OutputStreamWriter(System.out));
		*/
	}
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		
		FXMLLoader fxmlLoader= new FXMLLoader(getClass().getResource("mainPage.fxml"));
		fxmlLoader.setController(videoGameStoreGUI);
		Parent root= fxmlLoader.load();
		Scene scene = new Scene(root);
		primaryStage.setScene(scene);
		primaryStage.setTitle("VIDEOGAME STORE");
		primaryStage.show();
		videoGameStoreGUI.loadMainPage();
		
	}
	
	public static void main(String[] args) {
		launch(args); 
		/*
		Main ui= new Main();
		
		try {
			ui.numOfCases();
			
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		*/
	}
	/*
	// El numero de casos que se entra por consola, determina cuantos casos van a entrar
	public void numOfCases() throws NumberFormatException, IOException {
		int cases= Integer.parseInt(br.readLine());
		while(cases!=0){
			int cashRegisters = Integer.parseInt(br.readLine());
			int stands = Integer.parseInt(br.readLine());
			store= new Store(cashRegisters, stands);
			while(stands!=0) {
				createNewStand();
				stands--;
			}
			int numOfClients= Integer.parseInt(br.readLine());
			store.setNumOfClients(numOfClients);
			store.createClientList();
			while(numOfClients!=0) {
				store.addClients(br.readLine());
				numOfClients--;
			}
			store.orderClientLists();
			store.stackGames();
			store.orderClientsByTime();
			
			bw.write(store.cashRegisters());
			bw.flush();
			cases--;
		}
	}

	public void createNewStand() throws IOException {
		String nameAndLevels= br.readLine();
		String[] separateNameAndLevel=nameAndLevels.split(" ");
		String name= separateNameAndLevel[0];
		int levels= Integer.parseInt(separateNameAndLevel[1]);
		String[] values= new String[levels];
		for(int c=0; c< values.length;c++) {
			values[c]=br.readLine();
		}
		store.createStand(name, levels, values);
	}
	
	
	
	public void test() throws IOException {
		bw.write(store.printStands());
		bw.write(store.printClients());
		bw.flush();
	}
	*/
}


