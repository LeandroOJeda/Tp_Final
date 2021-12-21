
package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import logica.Controladora;
import logica.Venta;

@WebServlet(name = "SvNewVenta", urlPatterns = {"/SvNewVenta"})
public class SvNewVenta extends HttpServlet {
    
    Controladora controlLog = new Controladora();


    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
 
        
        try {
                   Venta ven = new Venta();
        Date hoy = new Date();
        ven.setFecha_venta(hoy);
        ven.setMedio_pago("Contado");
        
        String[] aux=request.getParameterValues("option2"); 
        int servicio = Integer.parseInt(aux[0]);
        aux=request.getParameterValues("opcion1"); 
        int cliente = Integer.parseInt(aux[0]);
        aux=request.getParameterValues("option3"); 
        int empleado = Integer.parseInt(aux[0]);
            //request.getSession().setAttribute("option2", servicio);
            //request.getSession().setAttribute("option1", cliente);
            //request.getSession().setAttribute("option3", empleado);

            controlLog.crearVenta(ven, servicio, cliente, empleado);
        } catch (Exception ex) {
            response.sendRedirect("inicio.jsp");
            Logger.getLogger(SvNewVenta.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
        response.sendRedirect("inicio.jsp");
        }
    }


    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
