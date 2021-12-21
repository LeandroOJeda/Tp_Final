
package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import logica.Controladora;
import logica.Servicio;


@WebServlet(name = "SvModificarServicio", urlPatterns = {"/SvModificarServicio"})
public class SvModificarServicio extends HttpServlet {
    
        Controladora controlLog = new Controladora();


    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
            try {
                int id = Integer.parseInt(request.getParameter("id"));
                String nombre = request.getParameter("nombre");
                String descripcion = request.getParameter("descripcion");
                String destino = request.getParameter("destino");
                String fecha = request.getParameter("fecha");
                SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
                Date fechaNac = formato.parse(fecha);
                double costo = Double.parseDouble(request.getParameter("costo"));
                
                Servicio servi = controlLog.buscarServicio(id);
                servi.setNombre(nombre);
                servi.setDescripcion_breve(descripcion);
                servi.setDestino_servicio(destino);
                servi.setFecha_servicio(fechaNac);
                servi.setCosto_servicio(costo);
                
                
                
                controlLog.modificarServicio (servi);
                
                request.getSession().setAttribute("listaServicios", controlLog.traerServicio());
                response.sendRedirect("Servicio.jsp");
            } catch (ParseException ex) {
                Logger.getLogger(SvModificarServicio.class.getName()).log(Level.SEVERE, null, ex);
            }
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
         int id = Integer.parseInt(request.getParameter("id"));
        Servicio servi = controlLog.buscarServicio(id);

        HttpSession misesion = request.getSession();
        misesion.setAttribute("servicio", servi);
        response.sendRedirect("formEditServi.jsp");
    }


    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
