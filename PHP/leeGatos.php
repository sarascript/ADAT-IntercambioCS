<?php

require 'bbdd.php';
    
$arrMensaje = array();

$query = "SELECT * FROM gatos";

$result = $conn->query ( $query );

if (isset ( $result ) && $result) {
	
	if ($result->num_rows > 0) {
		
		$arrgatos = array();
		
		while ( $row = $result->fetch_assoc () ) {

			$arrgato = array();
			
			$arrgato["id"] = $row["id"];
			$arrgato["nombre"] = $row["nombre"];
			$arrgato["raza"] = $row["raza"];
			$arrgato["color"] = $row["color"];
			$arrgatos[] = $arrgato;
		}
		

		$arrMensaje["estado"] = "ok";
		$arrMensaje["gatosdata"] = $arrgatos;
		
		
	} else {
		
		$arrMensaje["estado"] = "ok";
		$arrMensaje["gatosdata"] = [];
	}
	
} else {
	
	$arrMensaje["estado"] = "error";
	$arrMensaje["mensaje"] = "SE HA PRODUCIDO UN ERROR AL ACCEDER A LA BASE DE DATOS";
	$arrMensaje["error"] = $conn->error;
	$arrMensaje["query"] = $query;
	
}

$mensajeJSON = json_encode($arrMensaje,JSON_PRETTY_PRINT);

echo $mensajeJSON;

$conn->close ();

?>
