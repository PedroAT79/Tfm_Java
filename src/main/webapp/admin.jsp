<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link href="estilos.css" rel="stylesheet" type="text/css" />
<title>Insert title here</title>
</head>
<body>
	<div class="header">
			
		<%@ include file="/INCLUDES/admin-header.jsp"%>
		
	</div>
	
	<div class="cuerpo">
		<section class="gestion">
			<div>
			
			</div>
			<div class="admin stock">
		
				
				
				<div class="opcion-titulo">
					<p>GESTION DE ALMACEN</p>
				</div>
				<div class="opcion">
					<a href="altaArticulo.jsp">ALTA DE ARTICULO</a>
				</div>
					<div class="opcion">
					<a href="Sv_CategoriasArticulos?opcion=4">LISTADO ARTICULOS</a>
				</div>
				
				<div class="opcion">
					<a href="Sv_CategoriasArticulos?opcion=7">GESTION CATEGORIAS</a>
				</div>
				
				
		</div>

			<div class="admin usuarios">
				<div class="opcion-titulo">
					<p>GESTION DE USUARIOS</p>
				</div>
				<div class="opcion">
					<a href="altaUsuario.jsp">ALTA DE USUARIO</a>
				</div>
				
				<div class="opcion">
					<a href="Sv_Usuarios?opcion=2">LISTADO USUARIOS</a>
				
				</div>
				<div class="opcion">
					<a href="Sv_Usuarios?opcion=5">GESTION TIPOS USUARIO</a>
				</div>
				<div class="opcion">
					<a href="Sv_Usuarios?opcion=5">RECLAMACIONES</a>
				</div>

			</div>
			<div class="admin ordenes">
				<div class="opcion-titulo">
					<p>GESTION DE ORDENES DE COMPRA</p>
				</div>
				<div class="opcion">
					<a href="Sv_Filtros?opcion=7">ORDENES DE COMPRA</a>
				</div>			
				<div class="opcion">
					<a href="Sv_Filtros?opcion=11&seleccion=1&&estadoPago=Pendiente">ORDENES PENDIENTES 48h+ </a>	
				</div>
				<div class="opcion">
					<a href="Sv_Filtros?opcion=11&seleccion=2&estadoPago=Pendiente">ORDENES PENDIENTES 24h</a>
					
				</div>
				<div class="opcion">
					<a href="Sv_Filtros?opcion=11&seleccion=3&estadoPago=En Tramite">ORDENES EN TRAMITE</a>
				</div>
				<div class="opcion">
					<a href="Sv_Filtros?opcion=11&seleccion=3&estadoPago=En Tramite">TOP VENTAS</a>
				</div>
			

			</div>
		</section>
	</div>

	<div class="footer">
		<%@ include file="/INCLUDES/footer.jsp"%>

	</div>
	<script>
		window.onload = function(){
			link = document.querySelector('#filtroEnlace');
			link.addEventListener('click', function(e){
				 var valor = document.querySelector('#valorInput').value;
				this.href = this.href+valor;
			})
		}
	
	
	</script>
	
</body>
</html>