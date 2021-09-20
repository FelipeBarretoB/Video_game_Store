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
			int pointer= Integer.parseInt(levelValues[0])%levels.length;
			boolean added= false;
			int i=0;
			while(!added && i<levels.length) {
				if(levels[(pointer+i)%levels.length]== null) {
					levels[(pointer+i)%levels.length]= new Hashtable(Integer.parseInt(levelValues[0]), Double.parseDouble(levelValues[1]), Integer.parseInt(levelValues[0]));
					added = true;
				}else {
					i++;
				}
			}

		}
	}
}
