package persistencia;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import logica.Cliente;
import logica.Empleado;
import logica.Servicio;
import logica.Usuario;
import logica.Venta;
import persistencia.exceptions.NonexistentEntityException;

public class ControladoraPersistencia {

    EmpleadoJpaController empleJPA = new EmpleadoJpaController();
    UsuarioJpaController usuJPA = new UsuarioJpaController();
    ClienteJpaController clieJPA = new ClienteJpaController();
    ServicioJpaController serviJPA = new ServicioJpaController();
    VentaJpaController ventaJPA = new VentaJpaController();

    public void crearEmpleado(Empleado emple, Usuario usu) {

        usuJPA.create(usu);
        empleJPA.create(emple);
    }

    public List<Usuario> traerUsuarios() {
        return usuJPA.findUsuarioEntities();

    }

    //Empleados
    public List<Empleado> traerEmpleado() {

        return empleJPA.findEmpleadoEntities();
    }

    public Empleado buscarEmpleado(int id) {
        return empleJPA.findEmpleado(id);
    }

    public void modificarEmpleado(Empleado emple) {

        try {
            empleJPA.edit(emple);
        } catch (Exception ex) {
            Logger.getLogger(ControladoraPersistencia.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void borrarEmpleado(int id) {
        try {
            empleJPA.destroy(id);
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(ControladoraPersistencia.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
//Clientes

    public void crearCliente(Cliente clien) {

        clieJPA.create(clien);

    }

    public List<Cliente> traerClientes() {

        return clieJPA.findClienteEntities();
    }

    public void borrarCliente(int id) {
        
        try {
            clieJPA.destroy(id);
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(ControladoraPersistencia.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
    public void modificarCliente(Cliente clien) {

        try {
            clieJPA.edit(clien);
        } catch (Exception ex) {
            Logger.getLogger(ControladoraPersistencia.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
    public Cliente buscarCliente(int id) {
        return clieJPA.findCliente(id);
    }
    
    //Servicios
    
    public void crearServicio(Servicio servi) {

        serviJPA.create(servi);

    }
    
    public List<Servicio> traerServicio() {

        return serviJPA.findServicioEntities();
    }

    public void borrarServicio(int id) {
        
        try {
            serviJPA.destroy(id);
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(ControladoraPersistencia.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
    public void modificarServicio(Servicio servi) {

        try {
            serviJPA.edit(servi);
        } catch (Exception ex) {
            Logger.getLogger(ControladoraPersistencia.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
    public Servicio buscarServicio(int id) {
        return serviJPA.findServicio(id);
    }
    
    //Registro de Ventas
    
    public void crearVenta(Venta vent) {

        ventaJPA.create(vent);

    }
    
    public Venta ultimaVenta(){
        List<Venta> ulti =ventaJPA.findVentaEntities();
        return ulti.get(ulti.size() -1 );
    
    }
    public void actualizarServicio(Servicio servicio) throws Exception{
        serviJPA.edit(servicio);
    }    
    
    public void actualizarCliente(Cliente cliente) throws Exception{
        clieJPA.edit(cliente);    
    }
        
    public void actualizarEmpleado(Empleado empleado) throws Exception{
            empleJPA.edit(empleado);
    }
    
}
