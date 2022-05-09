<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import = "controlador.Sv_CarritoCompra" %>
<%@ page import = "controlador.Sv_Filtros" %>
<%@ page import = "dao.*" %>
<%@ page import = "modelo.*" %>
<%@ page import = "java.util.*" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link href="estilos.css" rel="stylesheet" type="text/css" />
<script src="JS/buscador.js"  type="text/javascript"></script>
<title>Insert title here</title>
</head>
<body>
	<div class="header">
		<%@ include file="/INCLUDES/admin-header.jsp"%>
	
	</div>
	
			
	<div class="cuerpo">
		<section class="listado">
			<h2>Listado de Ordenes de Compra</h2>
				<div class="buscador">	
				<label>Buscar:</label>
				<input id="terminoBuscado" type="text" onkeyup="buscador()"/>
				</div>	
				
				
				<table class="formularioTabla" id="formularioTabla">
				<thead>
					<th>Id Orden</th>
					<th>Usuario</th>
					<th>Fecha Orden</th>
					<th>Total a Pagar</th>
					<th>Estado del Pago</th>
					
					
				
				</thead>
				<%
					
					ArrayList<OrdenDeCompra> lista = (ArrayList<OrdenDeCompra>) request.getAttribute("lista");
					if(lista !=null){
					for(int i=0; i<lista.size();i++){
						OrdenDeCompra oc = new OrdenDeCompra( lista.get(i).getIdOrden(), lista.get(i).getNombreOrden(),lista.get(i).getFechaCompra(),
								lista.get(i).getIdUsuario(),lista.get(i).getMontoTotal(),lista.get(i).getEstadoPago());
												
				
				%>	
				<tbody>
					<tr>
					<td><a href="Sv_Filtros?opcion=9&idOrden=<%=oc.getIdOrden()%>"><%=oc.getNombreOrden()%></a></td>
					<td><a href="Sv_Filtros?opcion=8&idUsuario=<%=oc.getIdUsuario()%>"><%=oc.getIdUsuario()%></a></td>
					<td><%=oc.getFechaCompra()%></td>
					<td><%=oc.getMontoTotal()%></td>
					<td><%=oc.getEstadoPago()%></td>
					
					</tr>
					<%} %>
					<%lista.clear();}else{%>
						<div><h3 class="titulo">NO HAY ORDENES DE COMPRA EN BASE DE DATOS</h3></div>
					<%} %>
	
				</tbody>
			
				</table>
				<div class="footer">
		<%@ include file="/INCLUDES/footer.jsp"%>

	</div>
	<!-- <script>
		window.onload = function(){
			link = document.querySelector('#filtroEnlace');
			link.addEventListener('click', function(e){
				 var valor = document.querySelector('#valorInput').value;
				this.href = this.href+valor;
			})
		}
	
	
	</script> -->
	
</body>
</html>