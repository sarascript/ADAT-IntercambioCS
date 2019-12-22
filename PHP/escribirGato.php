<?php

require 'bbdd.php';
require 'jsonEsperadoGato.php';

$arrMensaje = array();

$parameters = file_get_contents("php://input");

if(isset($parameters)){

	$mensajeRecibido = json_decode($parameters, true);

	if(JSONCorrectoAnnadir($mensajeRecibido)){

		$gato = $mensajeRecibido["gatosAnnadir"];
		
		$nombre = $gato["nombre"];
		$raza = $gato["raza"];
		$color = $gato["color"];
		
		$query  = "INSERT INTO gatos (nombre,raza,color)";
		$query .= "VALUES ('$nombre','$raza','$color')";
		
		$result = $conn->query ( $query );
		
		if (isset ( $result ) && $result) {
			
			$arrMensaje["estado"] = "ok";
			$arrMensaje["mensaje"] = "Gato insertado correctamente";
			
		}else{
			
			$arrMensaje["estado"] = "error";
			$arrMensaje["mensaje"] = "SE HA PRODUCIDO UN ERROR AL ACCEDER A LA BASE DE DATOS";
			$arrMensaje["error"] = $conn->error;
			$arrMensaje["query"] = $query;
			
		}

		
	}else{
		
		$arrMensaje["estado"] = "error";
		$arrMensaje["mensaje"] = "EL JSON NO CONTIENE LOS CAMPOS ESPERADOS";
		$arrMensaje["recibido"] = $mensajeRecibido;
		$arrMensaje["esperado"] = $arrEsperado;
	}

}else{
	
	$arrMensaje["estado"] = "error";
	$arrMensaje["mensaje"] = "EL JSON NO SE HA ENVIADO CORRECTAMENTE";
	
}

$mensajeJSON = json_encode($arrMensaje,JSON_PRETTY_PRINT);

echo $mensajeJSON;

$conn->close ();

die();

?>
