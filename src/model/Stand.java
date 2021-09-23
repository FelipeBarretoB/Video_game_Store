package model;

public class Stand {
	//Nombre de la estanteria 
	private String name;
	//Pisos de la estanteria 
	private Hashtable[] levels;

	private String[] values;

	public Stand(String name, int levels, String[] values) {
		this.name = name;
		this.levels = new Hashtable[levels];
		this.values=values;
	}

	// va por cada uno de los valores (es decir codigo precio cantidad) y los va añadiendo a pisos disponibles en la estanteria 
	public void hash() {
		for(int c=0; c <values.length;c++) {
			String[] levelValues= values[c].split(" ");
			int pointer= levels.length-1;
			boolean added= false;
			
			while(!added && pointer>=0) {
				if(levels[(pointer)]== null) {
					levels[(pointer)]= new Hashtable(Integer.parseInt(levelValues[0]), Double.parseDouble(levelValues[1]), Integer.parseInt(levelValues[2]));
					added = true;
				}else {
					pointer--;
				}
			}

		}
	}
	
	public String print() {
		String print=name+" "+levels.length+"\n";
		for(int c=0;c< levels.length;c++) {
			print+=levels[c].print()+"\n";
		}
		
		return print;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Hashtable[] getLevels() {
		return levels;
	}

	public void setLevels(Hashtable[] levels) {
		this.levels = levels;
	}

	public String[] getValues() {
		return values;
	}

	public void setValues(String[] values) {
		this.values = values;
	}
	

	
	
}
