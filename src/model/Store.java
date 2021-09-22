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
			orderInStands(games);
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
		//TODO borrar, esto es una prueba
		for(int c=0;c < games.length;c++) {
			System.out.println("Game "+games[c]+" Stand "+order[c]+" level "+levels[c]);
		}
		
		
		return games;
	}

}
