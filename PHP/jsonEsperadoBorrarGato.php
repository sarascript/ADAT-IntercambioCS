<?php

$arrEsperado = array();
$arrGatosEsperado = array();

$arrEsperado["peticion"] = "delete";

$arrGatosEsperado["id"] = "";


$arrEsperado["borrarGato"] = $arrGatosEsperado;


function JSONCorrectoBorrar($recibidoprod){
	
	$auxCorrecto = false;
	
	if(isset($recibidoprod["peticion"]) && $recibidoprod["peticion"] ="delete" && isset($recibidoprod["borrarGato"])){
		
		$auxgato = $recibidoprod["borrarGato"];
		if(isset($auxgato["id"])){
			$auxCorrecto = true;
		}
		
	}
	
	
	return $auxCorrecto;
	
}
?>	
