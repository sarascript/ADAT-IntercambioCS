package principal;

import java.io.IOException;
import java.util.HashMap;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

import auxiliares.ApiRequests;
import modelo.Gatos;

public class AccesoJSONRemoto {

	ApiRequests encargadoPeticiones;
	private String SERVER_PATH, GET_Gatos, SET_Gatos, DELETE_Gato,
			DELETE_Gatos, UPDATE_Gato;

	public AccesoJSONRemoto() {

		encargadoPeticiones = new ApiRequests();

		SERVER_PATH = "http://localhost/xampp/gatos/";
		GET_Gatos = "leeGatos.php";
		SET_Gatos = "escribirGato.php";
		DELETE_Gato = "eliminarGato.php";
		DELETE_Gatos = "eliminarGatos.php";
		UPDATE_Gato = "actualizarGato.php";

	}

	public HashMap<Integer, Gatos> leeGatos() {

		HashMap<Integer, Gatos> auxhm = new HashMap<Integer, Gatos>();

		try {

			String url = SERVER_PATH + GET_Gatos;

			String response = encargadoPeticiones.getRequest(url);

			JSONObject respuesta = (JSONObject) JSONValue.parse(response.toString());

			if (respuesta == null) {
				System.out.println("El json recibido no es correcto. Finaliza la ejecuci�n");
				System.exit(-1);
			} else {
				String estado = (String) respuesta.get("estado");
				if (estado.equals("ok")) {
					JSONArray array = (JSONArray) respuesta.get("gatosdata");

					if (array.size() > 0) {

						Gatos nuevogato;
						int id;
						String nombre;
						String raza;
						String color;

						for (int i = 0; i < array.size(); i++) {
							JSONObject row = (JSONObject) array.get(i);
							id = Integer.parseInt(row.get("id").toString());
							nombre = row.get("nombre").toString();
							raza = row.get("raza").toString();
							color = row.get("color").toString();
							nuevogato = new Gatos(id, nombre, raza, color);
							auxhm.put(id, nuevogato);
						}

					} else {
						System.out.println("Acceso JSON Remoto - No hay datos que tratar");
						System.out.println();
					}

				} else { 

					System.out.println("Ha ocurrido un error en la busqueda de datos");
					System.out.println("Error: " + (String) respuesta.get("error"));
					System.out.println("Consulta: " + (String) respuesta.get("query"));

					// System.exit(-1);

				}
			}

		} catch (Exception e) {
			System.out.println("Ha ocurrido un error en la busqueda de datos");

			e.printStackTrace();

			System.exit(-1);
		}

		return auxhm;
	}

	public void anadirGatoJSON(Gatos auxGato) {

		try {
			JSONObject objGato = new JSONObject();
			JSONObject objPeticion = new JSONObject();

			objGato.put("nombre", auxGato.getNombre());
			objGato.put("raza", auxGato.getRaza());
			objGato.put("color", auxGato.getColor());

			objPeticion.put("peticion", "add");
			objPeticion.put("gatosAnnadir", objGato);

			String json = objPeticion.toJSONString();

			String url = SERVER_PATH + SET_Gatos;

			String response = encargadoPeticiones.postRequest(url, json);

			System.out.println(response);

			JSONObject respuesta = (JSONObject) JSONValue.parse(response.toString());

			if (respuesta == null) {
				System.out.println("El json recibido no es correcto. Finaliza la ejecuci�n");
				System.exit(-1);
			} else {
				String estado = (String) respuesta.get("estado");
				if (estado.equals("ok")) {

					System.out.println("Almacenado gato enviada por JSON Remoto");

				} else {
					System.out.println("Acceso JSON REMOTO - Error al almacenar los datos");
					System.out.println("Error: " + (String) respuesta.get("error"));
					System.out.println("Consulta: " + (String) respuesta.get("query"));

					System.exit(-1);

				}
			}
		} catch (Exception e) {
			System.out.println(
					"Excepcion desconocida. Traza de error comentada en el m�todo 'annadirGato' de la clase JSON REMOTO");
			e.printStackTrace();
			System.out.println("Fin ejecuci�n");
			System.exit(-1);
		}

	}

	public void borrarGato(Gatos auxBorrarGato) {
		try {
			JSONObject objGato = new JSONObject();
			JSONObject objPeticion = new JSONObject();

			objGato.put("id", auxBorrarGato.getId());

			objPeticion.put("peticion", "delete");
			objPeticion.put("borrarGato", objGato);

			String json = objPeticion.toJSONString();

			System.out.println("Lanzamos peticion JSON para borrar un gato");

			String url = SERVER_PATH + DELETE_Gato;

			String response = encargadoPeticiones.postRequest(url, json);

			System.out.println("El json que recibimos es: ");

			System.out.println(response);

			JSONObject respuesta = (JSONObject) JSONValue.parse(response.toString());

			if (respuesta == null) {
				System.out.println("El json recibido no es correcto. Finaliza la ejecuci�n");
				System.exit(-1);
			} else {
				String estado = (String) respuesta.get("estado");
				if (estado.equals("ok")) {

					System.out.println("Borrado gato enviada por JSON Remoto");

				} else {

					System.out.println("Acceso JSON REMOTO - Error al almacenar los datos");
					System.out.println("Error: " + (String) respuesta.get("error"));
					System.out.println("Consulta: " + (String) respuesta.get("query"));

					System.exit(-1);

				}
			}
		} catch (Exception e) {
			System.out.println(
					"Excepcion desconocida. Traza de error comentada en el m�todo 'annadirGato' de la clase JSON REMOTO");
			e.printStackTrace();
			System.out.println("Fin ejecuci�n");
			System.exit(-1);
		}
	}

	public void borrarGatos(Gatos auxBorrarGatos) {
		try {
			JSONObject objGato = new JSONObject();
			JSONObject objPeticion = new JSONObject();

			objGato.put("id", auxBorrarGatos.getId());
			objGato.put("nombre", auxBorrarGatos.getNombre());
			objGato.put("raza", auxBorrarGatos.getRaza());
			objGato.put("color", auxBorrarGatos.getColor());

			objPeticion.put("peticion", "deleteAll");
			objPeticion.put("borrarGatos", objGato);

			String json = objPeticion.toJSONString();

			System.out.println("Lanzamos peticion JSON para borrar todos los gatos");

			String url = SERVER_PATH + DELETE_Gatos;

			String response = encargadoPeticiones.postRequest(url, json);

			System.out.println("El json que recibimos es: ");

			System.out.println(response);

			JSONObject respuesta = (JSONObject) JSONValue.parse(response.toString());

			if (respuesta == null) {
				System.out.println("El json recibido no es correcto. Finaliza la ejecuci�n");
				System.exit(-1);
			} else {
				String estado = (String) respuesta.get("estado");
				if (estado.equals("ok")) {

					System.out.println("Borrados gatos enviadas por JSON Remoto");

				} else { 
					System.out.println("Acceso JSON REMOTO - Error al almacenar los datos");
					System.out.println("Error: " + (String) respuesta.get("error"));
					System.out.println("Consulta: " + (String) respuesta.get("query"));

					System.exit(-1);

				}
			}
		} catch (Exception e) {
			System.out.println(
					"Excepcion desconocida. Traza de error comentada en el m�todo 'annadirGatoa' de la clase JSON REMOTO");
			e.printStackTrace();
			System.out.println("Fin ejecuci�n");
			System.exit(-1);
		}
	}

	public void actualizarGato(Gatos auxActualizarGato) {
		try {
			JSONObject objGato = new JSONObject();
			JSONObject objPeticion = new JSONObject();

			objGato.put("id", auxActualizarGato.getId());
			objGato.put("nombre", auxActualizarGato.getNombre());
			objGato.put("raza", auxActualizarGato.getRaza());
			objGato.put("color", auxActualizarGato.getColor());

			objPeticion.put("peticion", "update");
			objPeticion.put("updateGato", objGato);

			String json = objPeticion.toJSONString();

			System.out.println("Lanzamos peticion JSON para actualizar un gato");

			String url = SERVER_PATH + UPDATE_Gato;

			String response = encargadoPeticiones.postRequest(url, json);

			System.out.println("El json que recibimos es: ");

			System.out.println(response);
			
			JSONObject respuesta = (JSONObject) JSONValue.parse(response.toString());

			if (respuesta == null) {
				System.out.println("El json recibido no es correcto. Finaliza la ejecuci�n");
				System.exit(-1);
			} else {
				String estado = (String) respuesta.get("estado");
				if (estado.equals("ok")) {

					System.out.println("Actualizado gato enviada por JSON Remoto");

				} else {
					System.out.println("Acceso JSON REMOTO - Error al almacenar los datos");
					System.out.println("Error: " + (String) respuesta.get("error"));
					System.out.println("Consulta: " + (String) respuesta.get("query"));

					System.exit(-1);

				}
			}
		} catch (Exception e) {
			System.out.println(
					"Excepcion desconocida. Traza de error comentada en el m�todo 'actualizarGato' de la clase JSON REMOTO");
			e.printStackTrace();
			System.out.println("Fin ejecuci�n");
			System.exit(-1);
		}
	}

}
