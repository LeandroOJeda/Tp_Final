package logica;

import java.util.List;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import logica.Servicio;
import logica.Venta;

@Generated(value="EclipseLink-2.7.7.v20200504-rNA", date="2021-12-19T23:14:05")
@StaticMetamodel(PaqueteServicio.class)
public class PaqueteServicio_ { 

    public static volatile SingularAttribute<PaqueteServicio, Double> costo_paquete;
    public static volatile SingularAttribute<PaqueteServicio, Integer> codigo_paquete;
    public static volatile SingularAttribute<PaqueteServicio, List> lista_servicios_incluidos;
    public static volatile ListAttribute<PaqueteServicio, Venta> listaVentas;
    public static volatile ListAttribute<PaqueteServicio, Servicio> listaServicio;

}