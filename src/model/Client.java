package model;

public class Client {
	//Es la cedula/codigo mencionado en el enunciado
	private int code;
	
	//String de los juegos que el cliente quiere comprar 
	private String[] games;

	public Client(String[] values) {
		code=Integer.parseInt(values[0]);
		games=new String[values.length-1];
		for(int c=1;c<values.length;c++) {
			games[c-1]=values[c];
		}
	}
	
	public Client(int code, String[] games) {
		this.code = code;
		this.games = games;
	}
	

	public int getCode() {
		return code;
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
	
}
