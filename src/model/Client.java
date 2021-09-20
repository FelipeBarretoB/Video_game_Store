package model;

public class Client {
	//Es la cedula/codigo mencionado en el enunciado
	private int code;
	
	//String de los juegos que el cliente quiere comprar 
	private String games;

	
	
	public Client(int code, String games) {
		this.code = code;
		this.games = games;
	}
	

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getGames() {
		return games;
	}

	public void setGames(String games) {
		this.games = games;
	}
	
	
}
