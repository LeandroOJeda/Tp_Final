package logica;

import java.util.Date;
import java.util.List;
import persistencia.ControladoraPersistencia;

public class Controladora {

    ControladoraPersistencia controlPersis = new ControladoraPersistencia();

    public void crearEmpleado(String nombre, String apellido, String cargo, double sueldo, String nombreUsu, String contrasenia) {

        Empleado emple = new Empleado();
        Usuario usu = new Usuario();

        emple.setNombre(nombre);
        emple.setApellido(apellido);
        emple.setCargo(cargo);
        emple.setSueldo(sueldo);

        usu.setContrasenia(contrasenia);
        usu.setNombreUsu(nombreUsu);

        emple.setUsu(usu);

        controlPersis.crearEmpleado(emple, usu);

    }

    public boolean verificarUsuario(String usuario, String contra) {
        List<Usuario> listaUsuarios = controlPersis.traerUsuarios();

        if (listaUsuarios != null) {
            for (Usuario nombreUsu : listaUsuarios) {
                if (nombreUsu.getNombreUsu().equals(usuario) && nombreUsu.getContrasenia().equals(contra)) {
                    return true;
                }
            }
        }

        return false;
    }
    
    //Empleados
    public List<Empleado> traerEmpleados (){
    return controlPersis.traerEmpleado();
    }
    
    public Empleado buscarEmpleado (int id){
    return controlPersis.buscarEmpleado(id);
    }
    
    public void modificarEmpleado(Empleado emple) {
        controlPersis.modificarEmpleado(emple);
    }
    
    public void borrarEmpleado (int id){
        controlPersis.borrarEmpleado(id);
    }
    
    //Clientes
   
    public void crearCliente(String nombre, String apellido, String celular, String direccion, String dni, String email, Date fechaNac, String nacionalidad) {  
    
        Cliente clien = new Cliente();
        
        clien.setNombre(nombre);
        clien.setApellido(apellido);
        clien.setCelular(celular);
        clien.setDireccion(direccion);
        clien.setDni(dni);
        clien.setEmail(email);
        clien.setFecha_nac(fechaNac);
        clien.setNacionalidad(nacionalidad);
        
        controlPersis.crearCliente(clien);
    
    }

    
        public List<Cliente> traerClientes (){
    return controlPersis.traerClientes();
        }
        
        public void borrarCliente (int id){
        controlPersis.borrarCliente(id);
    }
        
        public void modificarCliente(Cliente clien) {
        controlPersis.modificarCliente(clien);
    }
        
        public Cliente buscarCliente (int id){
    return controlPersis.buscarCliente(id);
    }
        
        //Servicios

    public void crearServicio(String nombre, String descripcion, String destino, Date fechaNac, double costo) {
        
        Servicio servi = new Servicio();
        
        servi.setNombre(nombre);
        servi.setCosto_servicio(costo);
        servi.setDescripcion_breve(descripcion);
        servi.setDestino_servicio(destino);
        servi.setFecha_servicio(fechaNac);
        
        controlPersis.crearServicio(servi);
    }
    
    public List<Servicio> traerServicio (){
    return controlPersis.traerServicio();
        }
        
        public void borrarServicio (int id){
        controlPersis.borrarServicio(id);
    }
        
        public void modificarServicio(Servicio servi) {
        controlPersis.modificarServicio(servi);
    }
        
        public Servicio buscarServicio (int id){
    return controlPersis.buscarServicio(id);
    }

    public void crearVenta(Venta ven, int servicio, int cliente, int empleado) throws Exception {
        
        controlPersis.crearVenta(ven);
        Venta ult = controlPersis.ultimaVenta();
        Servicio serv = controlPersis.buscarServicio(servicio);
        Cliente cli = controlPersis.buscarCliente(cliente);
        Empleado emp = controlPersis.buscarEmpleado(empleado);
        
        serv.agregarVenta(ult);
        controlPersis.actualizarServicio(serv);
        cli.agregarVenta(ult);
        controlPersis.actualizarCliente(cli);
        emp.agregarVenta(ult);
        controlPersis.actualizarEmpleado(emp);
        
        
    }   
}
