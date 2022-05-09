<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import = "modelo.Usuario" %>
<%@ page import = "java.util.*" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link href="estilos.css" rel="stylesheet" type="text/css" />
<title>Formulario de Alta Usuario</title>
<!--<script type="text/javascript" src="JS/validarFormularios.js"></script>  -->
</head>
<body>
	<div class="header">
			<%@ include file="/INCLUDES/user-nav.jsp"%>
	</div>
	<div class="cuerpo">
		<h4 class="tituloAlta">REGISTRO DE ALTA DE USUARIO</h4>

		<!--<section class="contenedorTipoUsuario">
         <a href="#" class="tipoUsuario"><h3>Alta Taller</h3></a>
         <a href="#" class="tipoUsuario"><h3>Alta Usuario</h3></a>
     </section>-->
		<section class="contenedorFormulario">
			<form  action="Sv_Usuarios" method="post" name="forma" onsubmit="return validarFormUsuario(this)">
				<input type="hidden" name="opcion" value="1">
				<table class="tablaAltaFormulario" cellspacing="20" cellpadding="3">
					
					<tr>
						<td colspan=2>Tipo de Usuario: Cliente.<input type="hidden" name="idUsuario"></td>
						
						<td><input <%if (nivelAcceso <3){%>type="hidden"<%}else{ %>type="text"<%} %> name="idTipoUsuario" value=2></td></tr>
								
						 <!-- Id usuario> <input type="text" name="idUsuario"></td>
                    <td>Tipo de Usuario</td>
                    <td> <input type="text" name="tipoUsuario"></td> -->
						<td>
					</tr>
					<tr>
								
						<td>Nombre:</td>
						<td><input type="text" name="nombre"></td>
						<td>Apellido:</td>
						<td><input type="text" name="apellidos"></td>
						<td>Telefono:</td>
						<td><input type="text" name="telefono"></td>

					</tr>
					<tr>
						<td>UserName:</td>
						<td><input type="text" name="nick"></td>
						<td>Password</td>
						<td><input type="password" name="password"></td>
						<td>Email:</td>
						<td><input type="text" name="email"></td>
					</tr>
					
					<tr>
						<td>Ciudad:</td>
						<td><input type="text" name="ciudad"></td>
						<td>Direccion (Calle\Numero):</td>
						<td><input type="text" name="direccionEnvio"></td>
						<td>Codigo Postal:</td>
						<td><input type="text" name="codigoPostal"></td>
					</tr>
				</table>
				<input class="submitFormulario" type="submit" value="Registrarse"> 

			</form>
			<%
				if(request.getParameter("ok") != null){
					String ok = request.getParameter("ok");
					out.print("<h5>"+ok+"</h5>");
				}
				if(request.getAttribute("listaErr")!= null){
					ArrayList<String> listaErr = (ArrayList<String>)request.getAttribute("listaErr");
					for(String item: listaErr){
						out.print("<h5>"+item+"</h5>");
					}
				}
			%>
			
		</section>

	</div>
	<div class="footer">
		<%@ include file="/INCLUDES/footer.jsp"%>

	</div>

</body>
</html>