package modelo;

public class Gatos{
	
	int id;
	String Nombre;
	String Raza;
	String Color;

	public Gatos() {
		
	}
	
	public Gatos(int id, String nombre, String raza, String color){
		this.id = id;
		this.Nombre = nombre;
		this.Raza = raza;
		this.Color = color;
	}
	
	public Gatos(String nombre, String raza, String color){
		this.Nombre = nombre;
		this.Raza = raza;
		this.Color = color;
	}

	public Gatos(int id) {
		this.id = id;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombre() {
		return Nombre;
	}

	public void setNombre(String nombre) {
		this.Nombre = nombre;
	}

	public String getRaza() {
		return Raza;
	}

	public void setRaza(String raza) {
		this.Raza = raza;
	}

	public String getColor() {
		return Color;
	}

	public void setColor(String color) {
		this.Color = color;
	}
	
	public String toString(){
		String aux ="";
		
		aux += "------------------------------------------";
		aux += "\n	ID: " + this.id;
		aux += "\n	NOMBRE: " + this.Nombre;
		aux += "\n	RAZA: " + this.Raza;
		aux += "\n	COLOR: " + this.Color;
		aux += "\n------------------------------------------";
		
		return aux;
	}
}
