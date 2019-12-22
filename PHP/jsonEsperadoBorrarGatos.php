<?php

$arrEsperado = array();
$arrGatosEsperado = array();

$arrEsperado["peticion"] = "deleteAll";
$arrGatosEsperado["id"] = "";
$arrGatosEsperado["nombre"] = "";
$arrGatosEsperado["raza"] = "";
$arrGatosEsperado["color"] = "";

$arrEsperado["borrarGatos"] = $arrGatosEsperado;


function JSONCorrectoAnnadir($recibidoprod){
	
	$auxCorrecto = false;
	
	if(isset($recibidoprod["peticion"]) && $recibidoprod["peticion"] ="deleteAll" && isset($recibidoprod["borrarPelis"])){
		
		$auxgato = $recibidoprod["borrarGato"];
		if(isset($auxgato["id"]) && isset($auxgato["nombre"]) && isset($auxgato["raza"]) && isset($auxgato["color"])){
			$auxCorrecto = true;
		}
		
	}
	
	
	return $auxCorrecto;
	
}
?>	
