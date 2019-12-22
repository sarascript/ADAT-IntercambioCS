package principal;

import java.util.HashMap;

import java.util.InputMismatchException;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;

import org.json.simple.JSONObject;

import modelo.Gatos;

public class Intermediario {

	Scanner teclado;
	AccesoJSONRemoto acceso;

	public Intermediario() {
		this.teclado = new Scanner(System.in);
		this.acceso = new AccesoJSONRemoto();
	}

	public void ejecucion() {
		int op = 0;
		boolean salir = false;

		while (!salir) {
			System.out.println();
			System.out.println("........ MENU ........... \n" + ".  0 Salir \n" + ".  1 Leer peliculas  \n"
					+ ".  2 A�adir peliculas \n" + ".  3 Leer Productora \n" + ".  4 A�adir Productora \n"
					+ ".  5 Borrar una pelicula \n" + ".  6 Borrar todas las peliculas \n"
					+ ".  7 Borrar una productora \n" + ".  8 Borrar todas las productoras \n"
					+ ".  9 Actualizar productora \n" + ".  10 Actualizar pelicula \n" + "..........................");
			try {
				op = teclado.nextInt();
				teclado.nextLine();
				System.out.println("OPCION SELECCIONADA:" + op);
				switch (op) {
				case 0:
					salir = true;
					break;
				case 1:
					HashMap<Integer, Gatos> hm = leeGatos();
					pintaGatos(hm);
					break;
				case 2:
					System.out.println("Gatos actuales");
					HashMap<Integer, Gatos> actualesGatos = leeGatos();
					pintaGatos(actualesGatos);
					Gatos auxGato = this.crearGatos();
					acceso.anadirGatoJSON(auxGato);
					break;
				case 5:
					HashMap<Integer, Gatos> verGatos = leeGatos();
					pintaGatos(verGatos);
					Gatos auxBorrarGato = this.borrarGato();
					acceso.borrarGato(auxBorrarGato);
					break;
				case 6:
					Gatos auxBorrarGatos = this.borrarGatos();
					acceso.borrarGatos(auxBorrarGatos);
					break;
				case 10:
					HashMap<Integer, Gatos> verGatosParaActualizar = leeGatos();
					pintaGatos(verGatosParaActualizar);
					Gatos auxActualizarGato = this.actualizarGato();
					acceso.actualizarGato(auxActualizarGato);
					break;
				default:
					System.out.println("Opcion invalida: marque un numero de 0 a 10");
					break;
				}

				// System.exit(1);

			} catch (InputMismatchException e) {
				System.out.println("Dato introducido no valido");
				teclado.next();
			} catch (Exception e) {
				System.out.println(
						"Excepcion desconocida. Traza de error comentada en el m�todo 'ejecucion' de la clase intermediario");

				System.out.println("Fin ejecuci�n");
				System.exit(-1);
			}
		}

	}

	private HashMap<Integer, Gatos> leeGatos() {

		HashMap<Integer, Gatos> hmAux = acceso.leeGatos();

		return hmAux;

	}

	private void pintaGatos(HashMap<Integer, Gatos> hm) {

		for (Entry<Integer, Gatos> entry : hm.entrySet()) {
			System.out.println(entry.getValue());
		}

	}

	private Gatos crearGatos() {
		Scanner sc = new Scanner(System.in);
		System.out.println("Nombre");
		String nombre = sc.nextLine();
		System.out.println("Raza");
		String raza = sc.nextLine();
		System.out.println("Color");
		String color = sc.nextLine();
		sc.nextLine();
		Gatos jAux = new Gatos(nombre, raza, color);

		return jAux;

	}

	private Gatos borrarGato() {
		System.out.println("Id del gato que desea borrar");
		Scanner sc = new Scanner(System.in);
		int idgato = sc.nextInt();
		sc.nextLine();
		Gatos jAux = new Gatos(idgato);
		return jAux;

	}

	private Gatos borrarGatos() {
		Gatos jAux = new Gatos();
		return jAux;

	}

	private Gatos actualizarGato() {
		Scanner sc = new Scanner(System.in);
		System.out.println("ID del gato que desea actualizar");
		int id = sc.nextInt();
		sc.nextLine();
		System.out.println("Nombre nuevo del gato");
		String nombre = sc.nextLine();
		System.out.println("Raza del gato");
		String raza = sc.nextLine();
		System.out.println("Color del gato");
		String color = sc.nextLine();
		sc.nextLine();
		Gatos jAux = new Gatos(id, nombre, raza, color);
		return jAux;
	}
}
