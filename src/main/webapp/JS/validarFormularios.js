/**
 * Validador de formulario:
 */

 
 function validarFormUsuario(forma){
	
	var nombre = forma.nombre;
	if(nombre.value == ""){
		alert ("Debes de introducir tu nombre");
		nombre.focus();
		nombre.select();
		return false;
	}
	var apellidos = forma.apellidos;
	if(apellidos.value == ""){
		alert ("Debes de introducir tu Apellido");
		apellidos.focus();
		apellidos.select();
		return false;
	}
	var nick = forma.nick;
		if(nick.value == "" || nick.value.length<4){
		alert("Debe de introducir un Username al menos de 4 caracteres");
		nick.focus();
		nick.select();
		return false;
	}
	
	var password = forma.password;
	if(password.value == "" || password.value.length < 4){
		alert("Debe proporcionar una clave al menos de 4 caracteres.");
		password.focus();
		password.select();
		return false;
	}
	
	var email = forma.email;
	if(email.value == ""){
		alert("Debe proporcionar un email valido.");
		email.focus();
		email.select();
		return false;
	}

// Formulario valido;
	alert("Formulario valido, enviando datos al Servidor");
	return true;
	
}

function validarFormArticulo(formArticulo){
	
	var estadoConservacion = formArticulo.estadoConservacion;
	if(estadoConservacion.value == "" || estadoConservacion.value == "Elegir una opcion" ){
		alert("Debe seleccionar un Estado");
		estadoConservacion.focus();
		estadoConservacion.select();
		return false
	}
	var subcategoria = formArticulo.idsubcategoria;
	if(idSubcategoria.value == "" || idSubcategoria.value == 0 ){
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






