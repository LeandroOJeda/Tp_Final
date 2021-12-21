<%-- 
    Document   : Ventas
    Created on : 20/12/2021, 10:09:13
    Author     : Leandro
--%>

<%@page import="logica.Servicio"%>
<%@page import="java.util.Date"%>
<%@page import="logica.Cliente"%>
<%@page import="java.util.List"%>
<%@page import="logica.Empleado"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>Ventas</title>
        <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Roboto|Varela+Round">
        <link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
        <style>
            body {
                color: #566787;
                background: #045FB4;
                font-family: 'Varela Round', sans-serif;
                font-size: 13px;
            }
            .table-responsive {
                margin: 30px 0;
            }
            .table-wrapper {
                min-width: 1000px;
                background: #fff;
                padding: 20px 25px;
                border-radius: 3px;
                box-shadow: 0 1px 1px rgba(0,0,0,.05);
            }
            .table-title {        
                padding-bottom: 15px;
                background: #435d7d;
                color: #fff;
                padding: 16px 30px;
                margin: -20px -25px 10px;
                border-radius: 3px 3px 0 0;
            }
            .table-title h2 {
                margin: 5px 0 0;
                font-size: 24px;
            }
            .table-title .btn-group {
                float: right;
            }
            .table-title .btn {
                color: #fff;
                float: right;
                font-size: 13px;
                border: none;
                min-width: 50px;
                border-radius: 2px;
                border: none;
                outline: none !important;
                margin-left: 10px;
            }
            .table-title .btn i {
                float: left;
                font-size: 21px;
                margin-right: 5px;
            }
            .table-title .btn span {
                float: left;
                margin-top: 2px;
            }
            table.table tr th, table.table tr td {
                border-color: #e9e9e9;
                padding: 12px 15px;
                vertical-align: middle;
            }
            table.table tr th:first-child {
                width: 60px;
            }
            table.table tr th:last-child {
                width: 100px;
            }
            table.table-striped tbody tr:nth-of-type(odd) {
                background-color: #fcfcfc;
            }
            table.table-striped.table-hover tbody tr:hover {
                background: #f5f5f5;
            }
            table.table th i {
                font-size: 13px;
                margin: 0 5px;
                cursor: pointer;
            }	
            table.table td:last-child i {
                opacity: 0.9;
                font-size: 22px;
                margin: 0 5px;
            }
            table.table td a {
                font-weight: bold;
                color: #566787;
                display: inline-block;
                text-decoration: none;
                outline: none !important;
            }
            table.table td a:hover {
                color: #2196F3;
            }
            table.table td a.edit {
                color: #FFC107;
            }
            table.table td a.delete {
                color: #F44336;
            }
            table.table td i {
                font-size: 19px;
            }
            table.table .avatar {
                border-radius: 50%;
                vertical-align: middle;
                margin-right: 10px;
            }
            .pagination {
                float: right;
                margin: 0 0 5px;
            }
            .pagination li a {
                border: none;
                font-size: 13px;
                min-width: 30px;
                min-height: 30px;
                color: #999;
                margin: 0 2px;
                line-height: 30px;
                border-radius: 2px !important;
                text-align: center;
                padding: 0 6px;
            }
            .pagination li a:hover {
                color: #666;
            }	
            .pagination li.active a, .pagination li.active a.page-link {
                background: #03A9F4;
            }
            .pagination li.active a:hover {        
                background: #0397d6;
            }
            .pagination li.disabled i {
                color: #ccc;
            }
            .pagination li i {
                font-size: 16px;
                padding-top: 6px
            }
            .hint-text {
                float: left;
                margin-top: 10px;
                font-size: 13px;
            }    
            /* Custom checkbox */
            .custom-checkbox {
                position: relative;
            }
            .custom-checkbox input[type="checkbox"] {    
                opacity: 0;
                position: absolute;
                margin: 5px 0 0 3px;
                z-index: 9;
            }
            .custom-checkbox label:before{
                width: 18px;
                height: 18px;
            }
            .custom-checkbox label:before {
                content: '';
                margin-right: 10px;
                display: inline-block;
                vertical-align: text-top;
                background: white;
                border: 1px solid #bbb;
                border-radius: 2px;
                box-sizing: border-box;
                z-index: 2;
            }
            .custom-checkbox input[type="checkbox"]:checked + label:after {
                content: '';
                position: absolute;
                left: 6px;
                top: 3px;
                width: 6px;
                height: 11px;
                border: solid #000;
                border-width: 0 3px 3px 0;
                transform: inherit;
                z-index: 3;
                transform: rotateZ(45deg);
            }
            .custom-checkbox input[type="checkbox"]:checked + label:before {
                border-color: #03A9F4;
                background: #03A9F4;
            }
            .custom-checkbox input[type="checkbox"]:checked + label:after {
                border-color: #fff;
            }
            .custom-checkbox input[type="checkbox"]:disabled + label:before {
                color: #b8b8b8;
                cursor: auto;
                box-shadow: none;
                background: #ddd;
            }
            /* Modal styles */
            .modal .modal-dialog {
                max-width: 400px;
            }
            .modal .modal-header, .modal .modal-body, .modal .modal-footer {
                padding: 20px 30px;
            }
            .modal .modal-content {
                border-radius: 3px;
            }
            .modal .modal-footer {
                background: #ecf0f1;
                border-radius: 0 0 3px 3px;
            }
            .modal .modal-title {
                display: inline-block;
            }
            .modal .form-control {
                border-radius: 2px;
                box-shadow: none;
                border-color: #dddddd;
            }
            .modal textarea.form-control {
                resize: vertical;
            }
            .modal .btn {
                border-radius: 2px;
                min-width: 100px;
            }	
            .modal form label {
                font-weight: normal;
            }	
        </style>
        <script>
            $(document).ready(function () {
                // Activate tooltip
                $('[data-toggle="tooltip"]').tooltip();

                // Select/Deselect checkboxes
                var checkbox = $('table tbody input[type="checkbox"]');
                $("#selectAll").click(function () {
                    if (this.checked) {
                        checkbox.each(function () {
                            this.checked = true;
                        });
                    } else {
                        checkbox.each(function () {
                            this.checked = false;
                        });
                    }
                });
                checkbox.click(function () {
                    if (!this.checked) {
                        $("#selectAll").prop("checked", false);
                    }
                });
            });
        </script>
    </head>
    <body>
   
    <div>
        <div class="container">
            <input type="submit" class="btn btn-success" href="SvNewVenta" value="Vender">
           
            <div class="table-responsive">
                <div class="table-wrapper">
                    <div class="table-title">
                        <div class="row">
                            <div class="col-xs-6">
                                <h2>Seleccione <b>Cliente</b></h2>
                            </div>
                            <div class="col-xs-6">
                                <a href="#addEmployeeModal" class="btn btn-success" data-toggle="modal"><i class="material-icons">&#xE147;</i> <span>Agregar nuevo Cliente</span></a>						
                            </div>
                        </div>
                    </div>
                    <table class="table table-striped table-hover">
                        <thead>
                            <tr>
                                <th>
                                    <span class="custom-checkbox">
					<input type="checkbox" id="selectAll">
					<label for="selectAll"></label>
                                    </span>
                                </th>
                                <th>Id</th>
                                <th>Nombre</th>
                                <th>Apellido</th>
                                <th>celular</th>
                                <th>Direccion</th>
                                <th>DNI</th>
                                <th>Email</th>
                                <th>Fecha de Nacimiento</th>
                                <th>Nacionalidad</th>
                                
                            </tr>
                        </thead>
                        <tbody>
                            
                      <% HttpSession misesion = request.getSession();

                                List<Cliente> listaClientes = (List) request.getSession().getAttribute("listaClientes");
                                for (Cliente clien : listaClientes) {

                            %>
                            <tr>
                                <td><span class="custom-checkbox">
                                        <input type="checkbox" id="checkbox1" name="option1" value="1">
					<label for="checkbox1"></label>
                                    </span>
                                </td>
                                <% int id = clien.getIdPersona();%>
                                <td><%= id%></td>
                                
                                <%String nombre = clien.getNombre();%>
                                <td> <%=nombre%></td>
                                
                                <%String apellido = clien.getApellido();%>
                                <td> <%=apellido%></td>
                                
                                <% String celular = clien.getCelular();%>
                                <td> <%= celular%></td>
                                
                                <% String direccion = clien.getDireccion();%>
                                <td><%= direccion%></td>
                                
                                <% String dni = clien.getDni();%>
                                <td><%= dni%></td>
                                
                                <% String email = clien.getEmail();%>
                                <td><%= email%></td>
                                
                                <% Date fechanac = clien.getFecha_nac();%>
                                <td><%= fechanac %></td>
                                
                                <% String nacionalidad = clien.getNacionalidad();%>
                                <td><%= nacionalidad%></td>
                                
                                <td>
                                    <form action="SvModificarCliente" method="POST">
                                        <input type="hidden" name="id" value="<%= id%>">
                                        <button type="submit" class="edit" data-toggle="modal"><i class="material-icons" data-toggle="tooltip" title="Editar">&#xE254;</i></button>
                                    </form>
                                </td> 
                                <td>
                                    <form action="SvEliminarCliente" method="POST" >
                                        <input type="hidden" name="id" value="<%= id%>">
                                        <button type="submit" class="delete" data-toggle="modal"><i class="material-icons" data-toggle="tooltip" title="Eliminar">&#xE872;</i></button>
                                    </form>
                                </td>
                            </tr> 
                            <% }%>
                        </tbody>
                    </table>
                </div>
            </div>        
        </div>
        <!-- Edit Modal HTML -->
        <div id="addEmployeeModal" class="modal fade">
            <div class="modal-dialog">
                <div class="modal-content">
                    <form action="SvCliente" method="POST">
                        <div class="modal-header">						
                            <h4 class="modal-title">Agregar Cleinte</h4>
                            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                        </div>
                        <div class="modal-body">					
                            <div class="form-group">
                                <label>Nombre</label>
                                <input type="text" name="nombre" class="form-control" required>
                            </div>
                            <div class="form-group">
                                <label>Apellido</label>
                                <input type="text" name="apellido" class="form-control" required>
                            </div>
                            <div class="form-group">
                                <label>Celular</label>
                                <input type="text" name="celular" class="form-control" required>
                            </div>
                            <div class="form-group">
                                <label>Dirección</label>
                                <input type="text" name="direccion" class="form-control" required>
                            </div>	
                            <div class="form-group">
                                <label>DNI</label>
                                <input type="text" name="dni" class="form-control" required>
                            </div>	
                            <div class="form-group">
                                <label>Email</label>
                                <input type="text" name="email" class="form-control" required>
                            </div>
                            <div class="form-group">
                                <label>Fecha de nacimiento</label>
                                <input type="date" name="fechanac" class="form-control" value="2021-12-20" required>
                            </div>
                            <div class="form-group">
                                <label>Nacionalidad</label>
                                <input type="text" name="nacionalidad" class="form-control" required>
                            </div>
                        </div>
                        <div class="modal-footer">
                            <input type="button" class="btn btn-default" data-dismiss="modal" value="Cancelar">
                            <input type="submit" class="btn btn-success" href="clientes.jsp" value="Añadir">
                        </div>
                    </form>
                </div>
            </div>
        </div>
        <div class="container">
            <div class="table-responsive">
                <div class="table-wrapper">
                    <div class="table-title">
                        <div class="row">
                            <div class="col-xs-6">
                                <h2>Seleccione <b>Servicio</b></h2>
                            </div>
                            <div class="col-xs-6">
                                <a href="#addEmployeeModal" class="btn btn-success" data-toggle="modal"><i class="material-icons">&#xE147;</i> <span>Agregar nuevo servicio</span></a>						
                            </div>
                        </div>
                    </div>
                    <table class="table table-striped table-hover">
                        <thead>
                            <tr>
                                <th>
                                    <span class="custom-checkbox">
					<input type="checkbox" id="selectAll">
					<label for="selectAll"></label>
                                    </span>
                                </th>
                                <th>Codigo Servicio</th>
                                <th>Nombre del Servicio</th>
                                <th>Descripcion</th>
                                <th>Destino</th>
                                <th>Fecha del Servicio</th>
                                <th>Costo del Servicio</th>
                                <th>Estado</th>
                                
                                
                            </tr>
                        </thead>
                        <tbody>
                            
                      <% HttpSession misesion1 = request.getSession();

                                List<Servicio> listaServicios = (List) request.getSession().getAttribute("listaServicios");
                                for (Servicio serv : listaServicios) {

                            %>
                            <tr>
                                <td><span class="custom-checkbox">
                                        <input type="checkbox" id="checkbox2" name="option2" value="1" onclick="mostrar(this)">
					<label for="checkbox1"></label>
                                    </span>
                                </td>
                                <% int id = serv.getCodigo_servicio();%>
                                <td><%= id%></td>
                                
                                <%String nombre = serv.getNombre();%>
                                <td> <%=nombre%></td>
                                
                                <%String descripcion = serv.getDescripcion_breve();%>
                                <td> <%=descripcion%></td>
                                
                                <% String destino = serv.getDestino_servicio();%>
                                <td> <%= destino%></td>
                                
                                <% Date fechanac = serv.getFecha_servicio();%>
                                <td><%= fechanac %></td>
                                
                                <% double costo = serv.getCosto_servicio();%>
                                <td><%= costo%></td>
                                
                                <td>
                                    <form action="SvModificarServicio" method="POST">
                                        <input type="hidden" name="id" value="<%= id%>">
                                        <button type="submit" class="edit" data-toggle="modal"><i class="material-icons" data-toggle="tooltip" title="Editar">&#xE254;</i></button>
                                    </form>
                                </td> 
                                
                                <td>
                                    <form action="SvEliminarServicios" method="POST" >
                                        <input type="hidden" name="id" value="<%= id%>">
                                        <button type="submit" class="delete" data-toggle="modal"><i class="material-icons" data-toggle="tooltip" title="Eliminar">&#xE872;</i></button>
                                    </form>
                                </td>
                            </tr> 
                            <% }%>
                        </tbody>
                    </table>
                </div>
            </div>        
        </div>
        <!-- Edit Modal HTML -->
        <div id="addEmployeeModal" class="modal fade">
            <div class="modal-dialog">
                <div class="modal-content">
                    <form action="SvServicio" method="POST">
                        <div class="modal-header">						
                            <h4 class="modal-title">Agregar Servicio</h4>
                            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                        </div>
                        <div class="modal-body">					
                            <div class="form-group">
                                <label>Nombre del Servicio</label>
                                <input type="text" name="nombre" class="form-control" required>
                            </div>
                            <div class="form-group">
                                <label>Descripcion</label>
                                <input type="text" name="descripcion" class="form-control" required>
                            </div>
                            <div class="form-group">
                                <label>Destino</label>
                                <input type="text" name="destino" class="form-control" required>
                            </div>	
                            <div class="form-group">
                                <label>Fecha</label>
                                <input type="date" name="fechanac" class="form-control" value="2021-12-20" required>
                            </div>
                            <div class="form-group">
                                <label>Costo</label>
                                <input type="text" name="costo" class="form-control" required>
                            </div>
                        </div>
                        <div class="modal-footer">
                            <input type="button" class="btn btn-default" data-dismiss="modal" value="Cancelar">
                            <input type="submit" class="btn btn-success" href="clientes.jsp" value="Añadir">
                        </div>
                    </form>
                </div>
            </div>
        </div>
        <div class="container">
            <div class="table-responsive">
                <div class="table-wrapper">
                    <div class="table-title">
                        <div class="row">
                            <div class="col-xs-6">
                                <h2>Seleccione <b>Vendedor</b></h2>
                            </div>
                            <div class="col-xs-6">
                                <a href="#addEmployeeModal" class="btn btn-success" data-toggle="modal"><i class="material-icons">&#xE147;</i> <span>Agregar nuevo Empleado</span></a>						
                            </div>
                        </div>
                    </div>
                    <table class="table table-striped table-hover">
                        <thead>
                            <tr>
                                <th>
                                    <span class="custom-checkbox">
					<input type="checkbox" id="selectAll">
					<label for="selectAll"></label>
                                    </span>
                                </th>
                                <th>Id</th>
                                <th>Nombre</th>
                                <th>Apellido</th>
                            </tr>
                        </thead>
                        <tbody>
                            <% HttpSession misesion2 = request.getSession();

                                List<Empleado> listaEmpleados = (List) request.getSession().getAttribute("listaEmpleados");
                                for (Empleado emple : listaEmpleados) {

                            %>
                            <tr>
                                <td><span class="custom-checkbox">
                                        <input type="checkbox" id="checkbox3" name="option3" value="1">
					<label for="checkbox1"></label>
                                    </span>
                                </td>
                                <% int id = emple.getIdPersona();%>
                                <td><%= id%></td>
                                <%String nombre = emple.getNombre();%>
                                <td> <%=nombre%></td>
                                <%String apellido = emple.getApellido();%>
                                <td> <%=apellido%></td>
                            </tr> 
                            <% }%>
                        </tbody>
                    </table>
                </div>
            </div>        
        </div>
        <!-- Edit Modal HTML -->
        <div id="addEmployeeModal" class="modal fade">
            <div class="modal-dialog">
                <div class="modal-content">
                    <form action="SvEmpleado" method="POST">
                        <div class="modal-header">						
                            <h4 class="modal-title">Agregar Empleado</h4>
                            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                        </div>
                        <div class="modal-body">					
                            <div class="form-group">
                                <label>Nombre</label>
                                <input type="text" name="nombre" class="form-control" required>
                            </div>
                            <div class="form-group">
                                <label>Apellido</label>
                                <input type="text" name="apellido" class="form-control" required>
                            </div>
                            <div class="form-group">
                                <label>Cargo</label>
                                <input type="text" name="cargo" class="form-control" required>
                            </div>
                            <div class="form-group">
                                <label>Sueldo</label>
                                <input type="text" name="sueldo" class="form-control" required>
                            </div>	
                            <div class="form-group">
                                <label>Usuario</label>
                                <input type="text" name="nombreUsu" class="form-control" required>
                            </div>	
                            <div class="form-group">
                                <label>Contraseña</label>
                                <input type="password" name="contrasenia" class="form-control" required>
                            </div>	
                        </div>
                        <div class="modal-footer">
                            <input type="button" class="btn btn-default" data-dismiss="modal" value="Cancelar">
                            <input type="submit" class="btn btn-success" href="empleados.jsp" value="Añadir">
                        </div>
                    </form>
                </div>
            </div>
        </div>

    </div>
    </body>
</html>


