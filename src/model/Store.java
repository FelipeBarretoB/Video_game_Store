package model;

public class Store {

	//numero de cajeros
	private int numCashRegister;

	//numero de estanterias 
	private int numOfStands;

	private Stand[] stands;

	public Store(){};

	public Store(int numCashRegister, int numOfStands) {
		this.numCashRegister = numCashRegister;
		this.numOfStands = numOfStands;
		stands= new Stand[numOfStands];
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

	public void CreateStand(String name, int levels, String[] values) {
		if(searchEmpty()!=-1){
			int c=searchEmpty();
			stands[c]= new Stand(name, levels, values);
			stands[c].hash();
		}
	}

	public int searchEmpty() {
		boolean found =false;
		int spot=-1;
		for(int c=0; c< stands.length && !found;c++) {
			if(stands[c]==null) {
				spot=c;
				
			}
		}
		return spot;
	}

}
