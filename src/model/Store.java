package model;

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

	public void createStand(String name, int levels, String[] values) {
		if(searchEmpty(stands)!=-1){
			int c=searchEmpty(stands);
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

}
