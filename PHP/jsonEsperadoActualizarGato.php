<?php

$arrEsperado = array();
$arrGatosEsperado = array();

$arrEsperado["peticion"] = "update";
$arrGatosEsperado["id"] = "";
$arrGatosEsperado["nombre"] = "";
$arrGatosEsperado["raza"] = "";
$arrGatosEsperado["color"] = "";

$arrEsperado["updatePeli"] = $arrGatosEsperado;


function JSONCorrectoActualizar($recibidoprod){
	
	$auxCorrecto = false;
	
	if(isset($recibidoprod["peticion"]) && $recibidoprod["peticion"] ="update" && isset($recibidoprod["updateGato"])){
		
		$auxgato = $recibidoprod["updateGato"];
		if(isset($auxgato["id"]) && isset($auxgato["nombre"]) && isset($auxgato["raza"]) && isset($auxgato["color"])){
			$auxCorrecto = true;
		}
		
	}
	
	
	return $auxCorrecto;
	
}
?>	
