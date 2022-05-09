/**
 * Validar formulario articulos
 */
 
 
function validarFormArticulo(formArticulo){
	
	var estadoConservacion = formArticulo.estadoConservacion;
	if(estadoConservacion.value == ""){
		alert("Debe seleccionar un Estado");
		estadoConservacion.focus();
		estadoConservacion.select();
		return false
	}
	var subcategoria = formArticulo.idsubcategoria;
	if(idSubcategoria.value == ""){
		alert("Debe seleccionar una Categoria.")
		subcategoria.focus();
		subcategoria.select();
		return false;
	}
	var marca = formArticulo.marca;
	if(marca.value == ""){
		alert("Debe introducir la marca del Articulo.")
		marca.focus();
		marca.select();
	}
	var cantidad = formArticulo.cantidad;
	if(cantidad.value == "" || cantidad.value == 0){
		cantidad.focus();
		cantidad.select();
		alert("Debe introducir cantidad diferente a 0.")
	}
	var precioArticulo = formArticulo.precioArticulo;
	if(precio.value == "" || precio.value == 0){
		precioArticulo.focus();
		precioArticulo.select();
		alert("Debe introducir un precio valido 0.00")
	}
// Formulario valido;
	alert("Formulario valido, enviando datos al Servidor");
	return true;
	
	
}

