package model;


import dataStructureQueue.Queue;
import dataStructureStack.Stack;


public class Client {
	//Es la cedula/codigo mencionado en el enunciado
	private int code;
	
	//String de los juegos que el cliente quiere comprar 
	private String[] games;
	
	private Queue<Integer> gameQueue;
	
	private Stack<Integer> gameStack;
	
	private int time;
	
	private double price;

	public Client(String[] values) {
		code=Integer.parseInt(values[0]);
		games=new String[values.length-1];
		for(int c=1;c<values.length;c++) {
			games[c-1]=values[c];
		}
		gameQueue=new Queue<>();
		gameStack=new Stack<>();
		time=0;
		price=0;
	}
	
	public Queue<Integer> getGameQueue() {
		return gameQueue;
	}

	public void setGameQueue(Queue<Integer> gameQueue) {
		this.gameQueue = gameQueue;
	}

	public Client(int code, String[] games) {
		this.code = code;
		this.games = games;
	}
	

	public int getCode() {
		return code;
	}
	
	public String[] getGames(){
		return games;
	}
	
	public void setGames(String [] games) {
		this.games=games;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String print() {
		String print="";
		print=code+" ";
		for(int c=0;c< games.length;c++) {
			print+=games[c]+" ";
		}
		
		return print;
	}

	public Stack<Integer> getGameStack() {
		return gameStack;
	}

	public void setGameStack(Stack<Integer> gameStack) {
		this.gameStack = gameStack;
	}

	public int getTime() {
		return time;
	}

	public void setTime(int time) {
		this.time = time;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}
	
	public String printGames() {
		String print="";
		for(int c=0;c< games.length;c++) {
			print+=games[c]+" ";
		}
		return print;
	}
	
}
