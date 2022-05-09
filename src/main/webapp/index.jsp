<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import= "java.util.*" %>
<%@ page import = "modelo.*" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link href="estilos.css" rel="stylesheet" type="text/css" />
<title>Insert title here</title>
</head>
<script type="text/javascript">
var idName = "mostrar";

var newClassName = "claseVisible";

function mostrar(){
	var claseMostrar = document.getElementBy("idName");
	claseMostrar.className = newClassName;
	
	}

</script>
<body>
	<div class="header">

		<%@ include file="/INCLUDES/user-nav.jsp"%>
		
		</div>
		
		<main class="cuerpoPrincipal">
			<div>
				<h1 class="titulo">TU TIENDA DE CICLISMO CON ARTICULOS NUEVOS Y DE OCASION</h1>
				
			</div>
			
				<div class="seccionPrincipal">
					<div class="categorias">
						<a href="Sv_Filtros?opcion=10&seccion=1" >BICICLETAS<img class="etiquetas" src="IMG/BICICLETA.jpg"></a>
					</div>
					<div class="categorias">
						<a href="Sv_Filtros?opcion=10&seccion=2" >COMPONENTES<img class="etiquetas" src="IMG/componentes.jpg"></a>
					</div>
				
				<div class="categorias">
						<a href="Sv_Filtros?opcion=10&seccion=3" >GADGETS<img class="etiquetas" src="IMG/gadgets.jpg"></a>
					</div>
					
				</div>
			
	
	
			<section class="cajaArticulos">
				<article>
					<h3 class="titulo">COMPRA-VENTA</h3>
					<img src="IMG/compra.png">
					<p>Disponemos de un gran catalogo tanto de bicis nuevas como de KM0 y de ocasion
					visita nuestra tienda y encontraras precios muy atractivos relacion calidad-precio.</p>
				</article>
				<article>
					<h3 class="titulo">TALLERES COLABORADORES</h3>
					<img src="IMG/colaboradores.png">
					<p>Si compras las bici o componente en nuestra tienda dispones de una red de talleres donde
					te la revisano instalan el componente a precio reducido. Si compras una bicicleta de KM0 o 
					de Ocasion ademas tienes derecho a una revision gratuita en el taller asociado de tu zona. </p>
				</article>
				<article>
					<h3 class="titulo">SERVICIOS DISPONIBLES</h3>
					<img src="IMG/trato.png">
					<p>Te entregamos tu compra en 24/48 horas en la direccion que nos digas dentro del territorio
					español, en Europa tardamos 72 horas, ademas tienes un plazo de 1 mes para devolver el producto
					en caso de no quedar satisfecho, siempre y cuando el articulo conserve su embalaje y no tengas desperfectos
					por uso. Puedes revisar tu bicicleta hasta 1 mes despues de haberla recibido en casa, sin coste alguno.</p>
				</article>

			</section>

		</main>

	</div>
	
	
		<%@ include file="/INCLUDES/footer.jsp"%>





</body>
</html>