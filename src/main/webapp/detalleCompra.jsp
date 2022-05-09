<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import = "controlador.*" %>
<%@ page import = "dao.*" %>
<%@ page import = "modelo.*" %>
<%@ page import = "java.util.*" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link href="estilos.css" rel="stylesheet" type="text/css" />
<script src="JS/confirmador.js" type="text/javascript"></script>
<script src="JS/buscador.js"  type="text/javascript"></script>
<title>Insert title here</title>
</head>
<body>
	<div class="header">
		<%@ include file="/INCLUDES/admin-header.jsp"%>
	
	</div>
	
			
	<div class="cuerpo">
		<section class="listado">
			<% ArrayList<DetalleCompra> listaDetalles = (ArrayList<DetalleCompra>) request.getAttribute("listaPorOrden");
				OrdenDeCompra oc = (OrdenDeCompra)request.getAttribute("orden");
				Imagen img = null;
				double total =0;
				
			%>
			<h2>Orden de compra: <%=oc.getNombreOrden()%></h2>
						
				
				<table class="formularioTabla" id="formularioTabla">
				<thead>
					<th>Articulo</th>
					<th>Denominacion</th>
					<th>Precio</th>
					<th>Cantidad</th>
					<th>Subtotal</th>
					<th>Eliminar</th>
				
					
				</thead>
				<%
					
					for(int i=0; i<listaDetalles.size();i++){
						img = new Imagen();
						int idArticulo = listaDetalles.get(i).getIdArticulo();
						img.obtenerUltimaImagen(idArticulo);						
						DetalleCompra dc = new DetalleCompra(listaDetalles.get(i).getIdDetalle(),listaDetalles.get(i).getIdArticulo(),listaDetalles.get(i).getIdOrden(),listaDetalles.get(i).getCantidad(),
								listaDetalles.get(i).getPrecioCompra(),listaDetalles.get(i).getNombreArticulo(),listaDetalles.get(i).getPrecioArticulo());
						
							
								
				
				%>	
				<tbody>
					<tr>
					
					<td><%if(img.getRuta()!=null){ %><img src="<%=img.getRuta()%>"><%}else{ %><img src="fotos/sinImagen.jpg"><%} %></td>
					
					<td><%=dc.getNombreArticulo()%></td>
					<td><%=dc.getPrecioArticulo()%></td>
					<td><%=dc.getCantidad()%></td>
					<td><%=dc.getPrecioCompra()%></td>
					
					
									
					<td><%if(dc.getPrecioCompra()!=0.0){ %><a href="Sv_CarritoCompra?opcion=6&idDetalle=<%=dc.getIdDetalle() %>"  onclick="return confirmDelete()">
					<img src="IMG/ICONOS/icono-eliminar.png"/></a><%}else{ %>Devuelto.<%} %></td><!-- eliminar -->
									
					<%total = total + listaDetalles.get(i).getPrecioCompra(); %>					
					</tr>
					<%} %>
					<th colspan=6>Importe Total =<%= total %>0 Euros</th>
					<%listaDetalles.clear(); %>
					
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