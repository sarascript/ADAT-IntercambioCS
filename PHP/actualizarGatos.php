<?php

require 'bbdd.php';
require 'jsonEsperadoActualizarGato.php';

$arrMensaje = array();

$parameters = file_get_contents("php://input");

if(isset($parameters)){

	$mensajeRecibido = json_decode($parameters, true);
	if(JSONCorrectoActualizar($mensajeRecibido)){

		$gato = $mensajeRecibido["updateGato"];
		$id = $id["id"];
		$nombre = $nombre["nombre"];
		$raza = $raza["raza"];
		$color = $color["color"];

		$query  = "UPDATE gatos SET nombre = '$nombre', raza = '$raza', color = '$color' WHERE id = '$id'";
		
		
		$result = $conn->query ( $query );
		
		if (isset ( $result ) && $result) {
			
			$arrMensaje["estado"] = "ok";
			$arrMensaje["mensaje"] = "Gatos actualizada correctamente";
			
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
