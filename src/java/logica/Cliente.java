package logica;

import java.util.Date;
import java.util.List;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

@Entity
@DiscriminatorValue(value = "Cliente")
public class Cliente extends Persona {

    @OneToMany
    List<Venta> listaVentas;

    public Cliente() {

    }

    public Cliente(List<Venta> listaVentas) {
        this.listaVentas = listaVentas;
    }

    public Cliente(List<Venta> listaVentas, int idPersona, String nombre, String apellido, String direccion, String dni, String nacionalidad, String celular, String email, Date fecha_nac) {
        super(idPersona, nombre, apellido, direccion, dni, nacionalidad, celular, email, fecha_nac);
        this.listaVentas = listaVentas;
    }

    public List<Venta> getListaVentas() {
        return listaVentas;
    }

    public void setListaVentas(List<Venta> listaVentas) {
        this.listaVentas = listaVentas;
    }
    
    public void agregarVenta(Venta venta){
        this.listaVentas.add(venta);
    }



}
