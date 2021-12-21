<%-- 
    Document   : Empleado
    Created on : 15/12/2021, 22:28:20
    Author     : Leandro
--%>

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
    <form action="SvModificarEmple" method="get">
        <% HttpSession misesion = request.getSession();
            Empleado emple = (Empleado) misesion.getAttribute("empleado");
            {%>
            
  <section class="form-register">
    <h4>Editar Empleado</h4>
    <input class="controls" type="text" name="nombre" id="nombres" value="<%=emple.getNombre()%>">
    <input class="controls" type="text" name="apellido" id="apellidos" value="<%= emple.getApellido()%>">
    <input class="controls" type="text" name="cargo" id="cargo" value="<%= emple.getCargo()%>">
    <input class="controls" type="text" name="sueldo" id="sueldo" value="<%= emple.getSueldo()%>">
    <input type="hidden" name="id" value="<%=emple.getIdPersona()%>">
    <button class="botons" type="submit"> Guardar cambios</button>
  </section>
            <%}%>
   </form>
</body>
</html>
