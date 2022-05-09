<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ page import = "java.util.*" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href="estilos.css" rel="stylesheet" type="text/css"/>
    <title>login OilOne-Bike</title>
</head>
<body>
    
    <section class="contenedorIS">
    
    <input class="botonNav" type="button" onclick="history.back()" name="volver atrás" value="VOLVER"></a>
    <a class="botonNav" href="altaUsuario.jsp">REGISTRO</a> 
    <form class="formInicioSesion" action="Sv_Login"  method="POST">
    <input type="hidden" name="opcion" value="1">
    <h3 class="tituloInicioSesion"><img src="https://img.icons8.com/material/24/000000/key--v1.png"/> Inicio de sesion.</h3>
        
        <input  type="text" name="nick" id="usuario" placeholder="usuario" class="accesoLogin" required><br>
       
        <input  type="password" name="password" placeholder="password" class="accesoLogin" required><br>
      
        <button type="submit" class="botonLogin"><img src="https://img.icons8.com/material/12/000000/enter-2--v1.png"/>Entrar</button></br>
                
    </form>
    <%
			
				if(request.getAttribute("listaErr")!= null){
					ArrayList<String> listaErr = (ArrayList<String>)request.getAttribute("listaErr");
					for(String item: listaErr){
						out.print("<div class='mensaje'><h3 class='titulo'>"+item+"</h3></div>");
					}
				}
			%>

    </section>
</body>
</html>