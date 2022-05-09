<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page import = "controlador.Sv_Usuarios" %>
<%@ page import = "dao.*" %>
<%@ page import = "modelo.*" %>
<%@ page import = "java.util.*" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link href="estilos.css" rel="stylesheet" type="text/css" />
<script src="JS/buscador.js"  type="text/javascript"></script>
<script src="JS/confirmador.js" type="text/javascript"></script>
<title>Insert title here</title>
</head>
<body>
	<div class="header">
		<%@ include file="/INCLUDES/admin-header.jsp"%>
	
	</div>
		
	<div class="cuerpo">
		<section class="listado">
			<h2>Lista de Usuarios Registrados.</h2>
				
				<div class="buscador">
				<label>Buscar:</label>
				<input id="terminoBuscado" type="text" onkeyup="buscador()"/>			
				</div>
					
					<table  id="formularioTabla" class="formularioTabla">
					<thead>
					<th>Id.</th>
					<th>Rol</th>
					<th>Nombre y Apellidos</th>
					<th>Username</th>
					<th>Email</th>
					<th>Ver /Editar</th>
					<th>Borrar</th>
					</thead>
					<%
						ArrayList<Usuario> listaUsuarios = (ArrayList<Usuario>)request.getAttribute("listaUsuarios");
						TipoUsuario tp = null;
							for(int i=0; i<listaUsuarios.size(); i++){
								tp = new TipoUsuario(listaUsuarios.get(i).getTipoUsuario());
								 tp.obtenerTipoUsuario(listaUsuarios.get(i).getTipoUsuario());
							Usuario usu = new Usuario (listaUsuarios.get(i).getIdUsuario(),
										listaUsuarios.get(i).getNombre(),
										listaUsuarios.get(i).getApellidos(),
										listaUsuarios.get(i).getNick(),
										listaUsuarios.get(i).getPassword(),
										listaUsuarios.get(i).getEmail(),
										listaUsuarios.get(i).getCiudad(),
										listaUsuarios.get(i).getTelefono(),
										listaUsuarios.get(i).getTipoUsuario(),
										listaUsuarios.get(i).getDireccionEnvio(),
										listaUsuarios.get(i).getCodigoPostal()
										);
					%>
					<tbody>
					<tr>
					<td><%=usu.getIdUsuario()%></td>
					<td><%=tp.getNombreTipoUsuario() %></td>
					<td><a href="Sv_Filtros?opcion=8&idUsuario=<%=usu.getIdUsuario()%>"><%=usu.getNombre()%> <%=usu.getApellidos()%></a></td>
					<td><%=usu.getNick()%></td>
					<td><%=usu.getEmail()%></td>
					
					<td><a href="Sv_Usuarios?opcion=3&idUsuario=<%=usu.getIdUsuario()%>"><img src="IMG/ICONOS/icono-editar.png"/></a></td><!-- obtener articulo para editar -->
					<td><a href="Sv_Usuarios?opcion=4&idUsuario=<%=usu.getIdUsuario()%>" onclick="return confirmDelete()"><img src="IMG/ICONOS/icono-eliminar.png"/></a></td><!-- eliminar -->
					
					</tr>
					<%} %>
					<%listaUsuarios.clear(); %>	
					</tbody>	
				</table>
				
				<div class="footer">
		<%@ include file="/INCLUDES/footer.jsp"%>

	</div>
		
</body>
</html>