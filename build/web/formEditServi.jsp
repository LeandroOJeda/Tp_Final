<%-- 
    Document   : formEditServi
    Created on : 19/12/2021, 22:52:54
    Author     : Leandro
--%>

<%@page import="logica.Servicio"%>
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
    <form action="SvModificarServicio" method="get">
        <% HttpSession misesion = request.getSession();
            Servicio servi = (Servicio) misesion.getAttribute("servicio");
            {%>
            
  <section class="form-register">
    <h4>Editar Servicio</h4>
    <input class="controls" type="text" name="nombre" id="nombres" value="<%=servi.getNombre()%>">
    <input class="controls" type="text" name="descripcion" id="descripcion" value="<%= servi.getDescripcion_breve()%>">
    <input class="controls" type="date" name="fecha" id="fecha" value="<%= servi.getFecha_servicio()%>" required>
    <input class="controls" type="text" name="destino" id="destino" value="<%= servi.getDestino_servicio()%>">
    <input class="controls" type="text" name="costo" id="costo" value="<%= servi.getCosto_servicio()%>">
    <input type="hidden" name="id" value="<%=servi.getCodigo_servicio()%>">
    <button class="botons" type="submit"> Guardar cambios</button>
  </section>
            <%}%>
   </form>
</body>
</html>
