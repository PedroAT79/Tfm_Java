

function buscador(){
	var tablaArticulos = document.getElementById('formularioTabla');
	var textoBuscado = document.getElementById('terminoBuscado').value.toLowerCase();
	var celdasPorFila ="";
	var encontrado = false;
	var compararCon="";
	
	
	//Recorremos todas las filas de la tabla con el contenido:
	
	for(var i=1; i<tablaArticulos.rows.length; i++){ //i=1 xq es a partir de la 2º columna, la primera es el encabezado de la columna
		celdasPorFila = tablaArticulos.rows[i].getElementsByTagName('td');
		encontrado = false;
		//Recorremos las celdas de cada fila:
		for(var j=0; j<celdasPorFila.length && !encontrado; j++){
			compararCon = celdasPorFila[j].innerHTML.toLowerCase();
			//Buscamos el texto en el contenido de la celda:
			if(textoBuscado.length == 0 ||compararCon.indexOf(textoBuscado)> -1){
				encontrado = true;
			}
		}
		if(encontrado){
			tablaArticulos.rows[i].style.display = '';
		}else{
			//si no encuentra ninguna coincidencia esconde la fila de la tabla
			tablaArticulos.rows[i].style.display ='none';
		}
	}
}

