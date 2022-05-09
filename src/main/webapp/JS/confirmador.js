/**
 * Funcion para confirmar el borrado de registros.
 */
 
 function confirmDelete(){
	
	var respuesta = confirm("¿Estas seguro que deseas Eliminar el registro? Puede eliminar otros registros asocioados.");
	
	if(respuesta == true){
		return true;
	}else{
		return false;
	}
	
}