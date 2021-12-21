package logica;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import logica.PaqueteServicio;
import logica.Venta;

@Generated(value="EclipseLink-2.7.7.v20200504-rNA", date="2021-12-19T23:14:05")
@StaticMetamodel(Servicio.class)
public class Servicio_ { 

    public static volatile SingularAttribute<Servicio, Date> fecha_servicio;
    public static volatile SingularAttribute<Servicio, Double> costo_servicio;
    public static volatile ListAttribute<Servicio, PaqueteServicio> listaPaquetes;
    public static volatile SingularAttribute<Servicio, Integer> codigo_servicio;
    public static volatile ListAttribute<Servicio, Venta> listaVentas;
    public static volatile SingularAttribute<Servicio, String> nombre;
    public static volatile SingularAttribute<Servicio, String> descripcion_breve;
    public static volatile SingularAttribute<Servicio, String> destino_servicio;

}