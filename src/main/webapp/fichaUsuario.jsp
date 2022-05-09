<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="controlador.*"%>
<%@ page import="modelo.*"%>
<%@ page import="java.util.*" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link href="estilos.css" rel="stylesheet" type="text/css" />
<title>Ficha de Usuario</title>
</head>
<body>
	<div class="header">
	
		
		<%@ include file="/INCLUDES/admin-header.jsp"%>

	</div>
	<%
	Usuario usu = (Usuario) request.getAttribute("usuario");
	
	%>

	<div class="tituloCard">
		<h2>FICHA DE USUARIO:</h2>
		<h3>
			Usuario:
			<%=usu.getNombre()%> <%=usu.getApellidos()%></h3>
	</div>
	<div class="tablaEdicion">
			<form action="Sv_Usuarios" method="post">
				<input type="hidden" name="opcion" value="1">
				
			<table>
				
					<tr>
					<input type="hidden" name="idTipoUsuario" value="<%=usu.getTipoUsuario() %>"> 
					<td>Id Usuario:</td><td><input name="idUsuario" value="<%=usu.getIdUsuario()%>"></td>
					<td>UserName:</td><td><input name="nick" value="<%=usu.getNick()%>"></td>
					<td>Password:</td><td><input name="password" value="<%=usu.getPassword()%>"></td>
					</tr>
			
				<tr>
					<td>Nombre:</td><td><input name="nombre" value="<%=usu.getNombre()%>"></td>
					<td>Apellidos:</td><td><input name="apellidos" value="<%=usu.getApellidos()%>"></td>
					<td>Rol Usuario:</td><td><input <%if(nivelAcceso <3){ %>type="hidden"<%}else{ %>type="text"<%} %> name="idTipoUsuario" value=<%=usu.getTipoUsuario() %>></td>
				</tr><tr>
					<td>Email:</td><td><input name="email" value="<%=usu.getEmail()%>"></td>
					<td>Telefono:</td><td><input name="telefono" value="<%= usu.getTelefono()%>"></td>	
				</tr>
				<tr>
					<td>Direccion:</td><td><input name="direccionEnvio" value="<%=usu.getDireccionEnvio()%>"></td>
					<td>Ciudad:</td><td><input name="ciudad" value="<%=usu.getCiudad()%>"></td>
				</tr><tr>
					<td>Codigo Postal:</td><td><input name="codigoPostal" value="<%=usu.getCodigoPostal()%>"></td>
				</tr>
				

			</table>
				<input class="submitFormulario" type="submit" value="Editar Formulario"></input><br>
				
		</form>
			

	</div>
	<section class="listado">
	<div class="tablaEdicion">
		<table class="formularioTabla" id="formularioTabla">
			<thead>
					<th>Id Orden</th>
					<th>Fecha Orden</th>
					<th>Total a Pagar</th>
					<th>Estado del Pedido</th>
					<%if(nivelAcceso<2){ %>
					<th>Seleccionar Estado</th>
					<th>Editar</th>
				<%} %>
				</thead>
		<%try{
			ArrayList<OrdenDeCompra> lista = (ArrayList<OrdenDeCompra>)request.getAttribute("lista");	
			
			for(int j=0; j<lista.size(); j++){
				OrdenDeCompra oc = new OrdenDeCompra(lista.get(j).getIdOrden(), lista.get(j).getNombreOrden(),
						lista.get(j).getFechaCompra(),lista.get(j).getIdUsuario(),lista.get(j).getMontoTotal(),
						lista.get(j).getEstadoPago());
				
			%>		<form action="Sv_CarritoCompra" method="POST">
					<input type="hidden" name="opcion" value="3">
					<tbody>
					<tr>
					<td><a href="Sv_Filtros?opcion=9&idOrden=<%=oc.getIdOrden()%>"><%=oc.getNombreOrden()%></a></td>
					<td><%=oc.getFechaCompra()%></td>
					<td><%=oc.getMontoTotal()%></td>
					<td><%=oc.getEstadoPago()%></td>
					<%if(nivelAcceso>2){ %>
					<td><select name="estadoPago">
						<option value="Seleccionar" selected>Seleccionar</option>
						<option value="Pendiente" >Pendiente</option>
						<option value="En tramite" >En tramite</option>
						<!-- <option value="Devolucion" >Devolucion</option> -->
						<option value="Cerrada" >Cerrada</option>
					
					</select></td>
					<td>--</td>
					
					<input  type="hidden" name="estadoPago" value="<%=oc.getEstadoPago()%>">
					<input  type="hidden" name="idOrden" value="<%=oc.getIdOrden()%>">
					<input  type="hidden" name="idUsuario" value="<%=oc.getIdUsuario()%>">			
					
					<td><input class="" type="submit" value="Editar">
					</td><!-- obtener articulo para editar -->
					<%} %>
					
					
					</tr>
					
	
				</tbody></form>
			<%}%>
				</table>
				
				
			<% 			
		}catch(Exception e1){%>
		<div class="tituloCard">
		<%
			out.print("Para ver pedidos de Usuario Ir a GESTION DE USUARIOS -- ORDENES DE COMPRA");
			
		}%>
		</div>
		
		</section>
		
	<div class="footer">
		<%@ include file="/INCLUDES/footer.jsp"%>

	</div>



</body>
</html>