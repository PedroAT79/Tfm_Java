<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="modelo.Categoria"%>
<%@ page import="java.util.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link href="estilos.css" rel="stylesheet" type="text/css" />
<script src="JS/confirmador.js" type="text/javascript"></script>
<title>Insert title here</title>

</head>
<body>
	<div class="header">
		<%@ include file="/INCLUDES/admin-header.jsp"%>
	</div>
		<h2 class="tituloAlta">GESTION DE CATEGORIAS DE PRODUCTO.</h2>
	<div class="seccionCat" >
		
	<div class="tableCat">
				<table >

					<tr>
						<th>Categoria</th>
						
						<th>Eliminar</th>
					</tr>

					<% ArrayList<Categoria> listaCat = null;
						Categoria cat = null;
					if(request.getAttribute("listaErr")==null){
						listaCat = (ArrayList<Categoria>) request.getAttribute("listadoCat");
					for (int i = 0; i < listaCat.size(); i++) {
						 cat = new Categoria(listaCat.get(i).getIdSubcategoria(), listaCat.get(i).getNombreCategoria());

					%>
					<tr>
						<td><%=cat.getIdSubcategoria()%>-<%=cat.getNombreCategoria()%></td>
						
						<td><a
							href="Sv_CategoriasArticulos?opcion=2&idSubcategoria=<%=cat.getIdSubcategoria()%>" onclick="return confirmDelete()">
								<img
								src="https://img.icons8.com/material/24/fa314a/delete-forever--v2.png" />
						</a></td>
						</a>
						<%
					}
					}else{
						ArrayList<String> listaErr = (ArrayList<String>)request.getAttribute("listaErr");
						for(String item : listaErr){%>
							<tr><td colspan=2><h4><%=item %></h4></td></tr>
						<%}
					}%>
				
				</table>
				</div>
		
				<form class="formCat" action="Sv_CategoriasArticulos" method="post">
					<label class=""><p>Editar Categoria:</p></label>
					<input type="hidden" name="opcion" value="11"> 
					<input type="text" name="idSubcategoriaEdit" placeholder="id Categoria a Editar">  
					<input type="text" name="nombreCategoria" placeholder="Nombre Categoria">
											
						 <input class="" type="submit" value="Editar">
				</form>
				
	
		
				<form class="formCat" action="Sv_CategoriasArticulos" method="post">
					<label class=""><p>Crear Categoria:</p></label>
					<input type="hidden" name="opcion" value="3"> 
					<input type="hidden" name="idSubcategoriaInsert">  
					<input type="text" name="nombreCategoria" placeholder="Nombre Categoria"><br>
					
										
						
						 <input class="" type="submit" value="Crear">
				</form>
				
			
				
				
				
		

	</div>

	<div class="footer">
		<%@ include file="/INCLUDES/footer.jsp"%>

	</div>
</body>
</html>