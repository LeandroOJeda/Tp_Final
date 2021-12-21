
package logica;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

@Entity
public class PaqueteServicio implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int codigo_paquete;
    
    @Basic
    private List<PaqueteServicio> lista_servicios_incluidos;
    private double costo_paquete;
    
    @ManyToMany
    private List<Servicio> listaServicio;
    
    @OneToMany
    private List<Venta> listaVentas;

    public PaqueteServicio() {
    }

    public PaqueteServicio(int codigo_paquete, List<PaqueteServicio> lista_servicios_incluidos, double costo_paquete, List<Servicio> listaServicio, List<Venta> listaVentas) {
        this.codigo_paquete = codigo_paquete;
        this.lista_servicios_incluidos = lista_servicios_incluidos;
        this.costo_paquete = costo_paquete;
        this.listaServicio = listaServicio;
        this.listaVentas = listaVentas;
    }

    public int getCodigo_paquete() {
        return codigo_paquete;
    }

    public void setCodigo_paquete(int codigo_paquete) {
        this.codigo_paquete = codigo_paquete;
    }

    public List<PaqueteServicio> getLista_servicios_incluidos() {
        return lista_servicios_incluidos;
    }

    public void setLista_servicios_incluidos(List<PaqueteServicio> lista_servicios_incluidos) {
        this.lista_servicios_incluidos = lista_servicios_incluidos;
    }

    public double getCosto_paquete() {
        return costo_paquete;
    }

    public void setCosto_paquete(double costo_paquete) {
        this.costo_paquete = costo_paquete;
    }

    public List<Servicio> getListaServicio() {
        return listaServicio;
    }

    public void setListaServicio(List<Servicio> listaServicio) {
        this.listaServicio = listaServicio;
    }

    public List<Venta> getListaVentas() {
        return listaVentas;
    }

    public void setListaVentas(List<Venta> listaVentas) {
        this.listaVentas = listaVentas;
    }

    

    
    
    
    
}
