<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="modelo.TipoUsuario"%>
<%@ page import="java.util.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<script src="JS/confirmador.js" type="text/javascript"></script>
<link href="estilos.css" rel="stylesheet" type="text/css" />
<title>Insert title here</title>
</head>
<body>
	<div class="header">
		<%@ include file="/INCLUDES/admin-header.jsp"%>
	</div>
		<h2 class="tituloAlta">GESTION DE ROLES DE USUARIO.</h2>
	
		
	<div class="listaTp">
				<table >

					<tr>
						<th>id.</th>
						<th>Tipo Usuario</th>
						
						<th>Eliminar</th>
					</tr>

					<%ArrayList<TipoUsuario> listaTp=null;
					if(request.getAttribute("listaErr")==null){
					listaTp = (ArrayList<TipoUsuario>) request.getAttribute("listadoTp");
					for (int i = 0; i < listaTp.size(); i++) {
						TipoUsuario tp = new TipoUsuario(listaTp.get(i).getIdTipoUsuario(), listaTp.get(i).getNombreTipoUsuario());
					%>


					<tr>
						<td><%=tp.getIdTipoUsuario()%></td><td><%=tp.getNombreTipoUsuario()%></td>
						
						<td><a href="Sv_Usuarios?opcion=6&idTipoUsuario=<%=tp.getIdTipoUsuario()%>" onclick="return confirmDelete()">
								<img src="IMG/ICONOS/icono-eliminar.png" />
						</a></td>
						</a>
						<%
						}
					}else{
						ArrayList<String> listaErr = (ArrayList<String>)request.getAttribute("listaErr");
						for(String item: listaErr){%>
							<tr><td colspan=3><h4><%=item %></h4></td></tr>
						<%}
						}%>
					
				</table>
				</div>
		<div class= "formTp">
			
				<form  action="Sv_Usuarios" method="post">
					<label class=""><p>Crear / Editar Rol de Usuario:</p></label>
					<input type="hidden" name="opcion" value="7"> <input
						type="text" name="idTipoUsuario" placeholder="id Rol a Editar">  <input type="text" name="nombreTipoUsuario"
						placeholder="Nombre del Rol de Usuario">
						
						<p>Nuevo Rol de Usuario: (Solo) Rol.<br><br>
						Editar Rol de Usuario: Id + Rol</p>
						
						
						 <input class="botonCat" type="submit" value="ENVIAR">
				</form>
	
				
				
		</div>
				
				


	<div class="footer">
		<%@ include file="/INCLUDES/footer.jsp"%>

	</div>
</body>
</html>