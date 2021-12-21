<%-- 
    Document   : formEditClien
    Created on : 19/12/2021, 19:24:20
    Author     : Leandro
--%>

<%@page import="logica.Cliente"%>
<%@page import="logica.Empleado"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <meta http-equiv="X-UA-Compatible" content="ie=edge">
  <link rel="stylesheet" href="css/Empleado.css">
  <title>Formulario Registro</title>
</head>
<body>
    <form action="SvModificarCliente" method="get">
        <% HttpSession misesion = request.getSession();
            Cliente clien = (Cliente) misesion.getAttribute("cliente");
            {%>
            
  <section class="form-register">
    <h4>Editar Cliente</h4>
    <input class="controls" type="text" name="nombre" id="nombres" value="<%=clien.getNombre()%>">
    <input class="controls" type="text" name="apellido" id="apellidos" value="<%= clien.getApellido()%>">
    <input class="controls" type="text" name="celular" id="celular" value="<%= clien.getCelular()%>">
    <input class="controls" type="text" name="direccion" id="direccion" value="<%= clien.getDireccion() %>">
    <input class="controls" type="text" name="dni" id="dni" value="<%=clien.getDni()%>">
    <input class="controls" type="text" name="email" id="email" value="<%=clien.getEmail()%>">
    <input class="controls" type="date" name="fechanac" id="fechanac" value="<%= clien.getFecha_nac()%>" required>
    <input class="controls" type="text" name="nacionalidad" id="nacionalidad" value="<%= clien.getNacionalidad()%>">
    <input type="hidden" name="id" value="<%=clien.getIdPersona()%>">
    <button class="botons" type="submit"> Guardar cambios</button>
  </section>
            <%}%>
   </form>
</body>
</html>
