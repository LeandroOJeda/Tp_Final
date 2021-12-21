
package logica;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
@DiscriminatorValue( value="Empleado" )
public class Empleado extends Persona implements Serializable{
            
        @Basic
        private String cargo;
        private double sueldo;
        
        @OneToOne
        private Usuario usu;
        
        @OneToMany
        List<Venta> listaVenta;

    public Empleado() {
    }

    public Empleado(String cargo, double sueldo, Usuario usu, List<Venta> listaVenta) {
        this.cargo = cargo;
        this.sueldo = sueldo;
        this.usu = usu;
        this.listaVenta = listaVenta;
    }

    public Empleado(String cargo, double sueldo, Usuario usu, List<Venta> listaVenta, int idPersona, String nombre, String apellido, String direccion, String dni, String nacionalidad, String celular, String email, Date fecha_nac) {
        super(idPersona, nombre, apellido, direccion, dni, nacionalidad, celular, email, fecha_nac);
        this.cargo = cargo;
        this.sueldo = sueldo;
        this.usu = usu;
        this.listaVenta = listaVenta;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public double getSueldo() {
        return sueldo;
    }

    public void setSueldo(double sueldo) {
        this.sueldo = sueldo;
    }

    public Usuario getUsu() {
        return usu;
    }

    public void setUsu(Usuario usu) {
        this.usu = usu;
    }

    public List<Venta> getListaVenta() {
        return listaVenta;
    }

    public void setListaVenta(List<Venta> listaVenta) {
        this.listaVenta = listaVenta;
    }
    
    public void agregarVenta(Venta venta){
        this.listaVenta.add(venta);
    }
   

    
    
}
