<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import = "modelo.*" %>
<%@ page import = "dao.DaoCategoria" %>
<%@ page import = "java.util.*" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link href="estilos.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="JS/validarFormArticulo.js"></script>
<title>Insert title here</title>
</head>
<body>
	<div class="header">
		<%@ include file="/INCLUDES/admin-header.jsp"%>
	</div>
	<div class="cuerpo">
		<section class="contenedorFormulario">
			<h2 class="tituloAlta">ALTA DE PRODUCTO.</h2>


			<form action="Sv_CategoriasArticulos" method="post"  name="formArticulo"  onsubmit="return validarForm(this)">
			<input type="hidden" name="opcion" value="1">
			
			<table class="tablaAltaFormulario" cellspacing="20" cellpadding="3">



					<tr>
						<td>Estado de Conservacion:</td>
						<td><select name="estadoConservacion">
								<option value="Elegir una opcion" selected>Elegir una opcion.</option>
								<option value="Nuevo">Nuevo.</option>
								<option value="KM.0">KM 0.</option>
								<option value="OCASION">Ocasion.</option></td>
					
					</tr>

					<tr>

						<!-- <td>Categoria de Producto:</td> 
               <td> <select name="categoria"> 
                <option selected>Elegir una opcion</option>
                <option value="1">Bicicletas.</option>
                <option value="2">Componentes.</option>
                <option value="3">Electronica y/o gadgets.</option>
                <option value="4">Otros: ropa, cascos, etc.</option>
            </td> -->
						<td>Subcategoria:</td>
						<td><select name="idSubcategoria">
						<option value=0 selected>Elegir una opcion</option>
							<% ArrayList<Categoria> listaCategorias = DaoCategoria.getInstance().listarCategorias();
								
								for( int i=0 ; i<listaCategorias.size(); i++){
							%>
							<option value="<%=listaCategorias.get(i).getIdSubcategoria()%>"><%= listaCategorias.get(i).getNombreCategoria() %></option><%}%>
							
					</tr>
					<input type="hidden" name="idArticulo">
					<tr>
						<td colspan=1>Breve descripcion del articulo a modo de
							titulo del anuncio:</td>

						<td colspan=3><textArea  cols=40 rows=1 type="text" name="nombreArticulo"></textArea></td>
					</tr>
					<tr>
						<td>Marca:</td>
						<td><input type="text" name="marca"></td>
						<td>Modelo:</td>
						<td><input type="text" name="modelo"></td>
					</tr>
					<tr>
						<td>Cantidad disponible:</td>
						<td>
						<input type="text" name="cantidad">
						</td>
						<td>Precio por unidad (Euros):</td>
						<td><input type="text" name="precioArticulo"></td>
					</tr>
					<tr>
						<td colspan="4">Descripcion pormenorizada del articulo:</td>
					</tr>
					<tr>
						<td colspan=4><textarea rows="4" cols="90" name="descripcion" class="descripcion"></textarea></td>

					</tr>
					
				</table>

				
				<input class="submitFormulario" type="submit" value="INSERTAR">
				
			
			</form>
			<%
			
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