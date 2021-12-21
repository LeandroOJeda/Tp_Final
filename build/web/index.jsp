<%-- 
    Document   : Login
    Created on : 17/12/2021, 16:26:50
    Author     : Leandro
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
    <meta charset="utf-8">
    <title>Login</title>
    <link rel="stylesheet" href="css/Login.css">
  </head>
  <body>
      <<form action="SvUsuario" method="POST">
    <section class="form-login">
      <h5>Login</h5>
      <input class="controls" type="text" name="user" value="" placeholder="Usuario">
      <input class="controls" type="password" name="pass" value="" placeholder="Contraseña">
      <input class="buttons" type="submit" name="" value="Ingresar">
      <p><a href="empleados.jsp">No tiene cuenta?</a></p>

    </section>
</form>
  </body>
</html>
