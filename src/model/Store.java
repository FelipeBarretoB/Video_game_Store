package model;

import dataStructureQueue.Queue;

public class Store {

	//numero de cajeros
	private int numCashRegister;

	//numero de estanterias 
	private int numOfStands;

	private Stand[] stands;

	private int numOfClients;

	private Client[] clients;

	public Store(){};

	public Store(int numCashRegister, int numOfStands) {
		this.numCashRegister = numCashRegister;
		this.numOfStands = numOfStands;
		stands= new Stand[numOfStands];
		numOfClients=-1;
		clients=null;
	}

	// Getters and Setters
	
	public int getNumCashRegister() {
		return numCashRegister;
	}

	public void setNumCashRegister(int numCashRegister) {
		this.numCashRegister = numCashRegister;
	}

	public int getNumOfStands() {
		return numOfStands;
	}

	public void setNumOfStands(int numOfStands) {
		this.numOfStands = numOfStands;
	}

	public Stand[] getStands() {
		return stands;
	}

	public void setStands(Stand[] stands) {
		this.stands = stands;
	}

	public void setNumOfClients(int c) {
		numOfClients=c;
	}

	// Methods
	
	public void createStand(String name, int levels, String[] values) {
		int c=searchEmpty(stands);
		if(c!=-1){
			stands[c]= new Stand(name, levels, values);
			stands[c].hash();
		}
	}

	public int searchEmpty( Object[] array) {
		boolean found =false;
		int spot=-1;
		for(int c=0; c< array.length && !found;c++) {
			if(array[c]==null) {
				spot=c;
				found=true;
			}
		}
		return spot;
	}

	public void createClientList() {
		clients=new Client[numOfClients];
	}

	public void addClients(String clientData) {
		if(searchEmpty(clients)!=-1){
			int c=searchEmpty(clients);
			clients[c]=new Client(clientData.split(" "));
		}
	}

	public String printClients() {
		String print="";
		for(int c=0;c<clients.length;c++) {
			print+=clients[c].print();
		}
		return print;
	}

	public String printStands() {
		String print="";
		for(int c=0;c<stands.length;c++) {
			print+=stands[c].print();
		}
		return print;
	}

	public void orderClientLists() {
		for(int c=0; c< clients.length;c++) {
			String[] games=clients[c].getGames();
			clients[c].setGames(orderInStands(games));
			clients[c].setGameQueue(addElementsToQueue(clients[c].getGames()));
		}
	}

	//Encuentra donde esta cada juego por estante y por piso
	public String[] orderInStands(String [] games) {
		int [] standOrder= new int[games.length];
		int [] levels= new int[games.length];
		for(int z=0;z<games.length;z++) {
			boolean found=false;
			for(int c=0;c<stands.length&& !found;c++) {
				for(int i=0;i<stands[c].getLevels().length&& !found;i++) {
					if(Integer.parseInt(games[z])== stands[c].getLevels()[i].getKey()) {
						levels[z]=i;
						standOrder[z]=c;
						found=true;
					}
				}
			}
		}
		games=orderInStands(games, standOrder, levels);
		return games;
	}

	//Vuelve la lista organizada a una queue 
	public Queue<Integer> addElementsToQueue(String [] games){
		Queue<Integer> gameQueue= new Queue<>();
		for(int c=0;c<games.length;c++) {
			gameQueue.add(Integer.parseInt(games[c]));
		}
		return gameQueue;
	}

	//Organiza la lista de compra del cliente 
	private String[] orderInStands(String [] games, int[] order, int[] levels) {
		int n = order.length;
		for (int i = 1; i < n; ++i) {
			int intTemp=levels[i];
			String temp=games[i];
			int key = order[i];
			int j = i - 1;
			while (j >= 0 && order[j] > key) {
				levels[j+1] = levels[j];
				games[j+1] = games[j];
				order[j + 1] = order[j];
				j = j - 1;
			}
			levels[j+1]=intTemp;
			games[j+1]= temp;
			order[j + 1] = key;
		}

		n = levels.length;
		for (int i = 0; i < n-1; i++)
			for (int j = 0; j < n-i-1; j++) {
				if (levels[j] > levels[j+1]&& order[j]==order[j+1]){
					String stringTemp=games[j];
					games[j]=games[j+1];
					games[j+1]=stringTemp;
					int temp = levels[j];
					levels[j] = levels[j+1];
					levels[j+1] = temp;
				}
			}
		return games;
	}

	//Le da el tiempo que se demoraron los clientes en la tienda, esta es una función de la posición(pos) de en donde llegaron + cuantos juegos tienen que buscar en las estanterias (Stack.size())
	public void sortTime(Client client, int pos) {
		client.setTime(pos+client.getGameStack().size());
	}


	public void stackGames() {
		for(int c=0;c<clients.length;c++) {
			searchGame(clients[c]);
			sortTime(clients[c], c);
		}
	}

	//Organiza las stacks de los juegos
	public void searchGame(Client client) {
		boolean stop=false;
		client.setGames(null);
		String newGames="";
		for(int c=0;c<stands.length&& !stop;c++) {
			for(int i=0;i<stands[c].getLevels().length && !stop;i++) {
				if(client.getGameQueue().isEmpty()) {
					stop=true;	
				}else if(client.getGameQueue().peek()== stands[c].getLevels()[i].getKey()) {
					if(stands[c].getLevels()[i].getQuantity()==0) {
						client.getGameQueue().poll();
					}else {
						newGames+=client.getGameQueue().peek()+" ";
						client.getGameStack().add(client.getGameQueue().poll());
						stands[c].getLevels()[i].setQuantity(stands[c].getLevels()[i].getQuantity()-1);
					}
				}
			}
		}
		client.setGames(newGames.split(" "));
	}

	public void orderClientsByTime() {
		for(int c=0;c<clients.length-1;c++) {
			for(int i=c;i <clients.length-c-1;i++) {
				if(clients[i].getTime()>clients[i+1].getTime()) {
					Client temp = clients[i];
					clients[i] = clients[i+1];
					clients[i+1] = temp;
				}
			}
		}
	}

	//lugar donde se paga lol
	public String cashRegisters() {
		Queue<Client> gameQueue= new Queue<>();
		Client[] clientsInRegisters=new Client[numCashRegister];
		String print="";
		for(int c=0;c<clients.length;c++) {
			gameQueue.add(clients[c]);
		}
		do{
			for(int c=0;c<clientsInRegisters.length;c++) {
				if(clientsInRegisters[c]==null) {
					if(!gameQueue.isEmpty()) {
						clientsInRegisters[c]=gameQueue.poll();
					}
				}else if(clientsInRegisters[c].getGameStack().isEmpty()) {	
					print+=clientsInRegisters[c].getCode()+" "+clientsInRegisters[c].getPrice()+"\n"+clientsInRegisters[c].printGames()+"\n";
					clientsInRegisters[c]=null;
				}else if(!clientsInRegisters[c].getGameStack().isEmpty()) {
					boolean found=false;
					for(int j=0;j<stands.length&& !found;j++) {
						for(int i=0;i<stands[j].getLevels().length&& !found;i++) {
							if(clientsInRegisters[c].getGameStack().peek()==stands[j].getLevels()[i].getKey()) {
								clientsInRegisters[c].setPrice(clientsInRegisters[c].getPrice()+stands[j].getLevels()[i].getPrice());
								clientsInRegisters[c].getGameStack().pop();
								found=true;
							}
						}

					}
				}
			}
		}while(!checkForArrayOfNull(clientsInRegisters) || !gameQueue.isEmpty());
		return print;
	}
	
	private boolean checkForArrayOfNull(Client[] test) {
		boolean empty = true;
		for(int c=0;c<test.length;c++) {
			if(test[c]!=null) {
				empty=false;
			}
		}
		return empty;
	}
}
