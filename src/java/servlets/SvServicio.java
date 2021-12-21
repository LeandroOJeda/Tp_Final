
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
import logica.Controladora;


@WebServlet(name = "SvServicio", urlPatterns = {"/SvServicio"})
public class SvServicio extends HttpServlet {
    
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
            String nombre = request.getParameter("nombre");
            String descripcion = request.getParameter("descripcion");
            String destino = request.getParameter("destino");
            String fecha = request.getParameter("fechanac");
            SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
            Date fechaNac = formato.parse(fecha);
            double costo = Double.parseDouble(request.getParameter("costo"));
            
            request.getSession().setAttribute("nombre", nombre);
            request.getSession().setAttribute("descripcion", descripcion);
            request.getSession().setAttribute("destino", destino);
            request.getSession().setAttribute("fechanac", fechaNac);
            request.getSession().setAttribute("costo", costo);
            
            controlLog.crearServicio(nombre, descripcion, destino, fechaNac, costo);
            response.sendRedirect("inicio.jsp");
        } catch (ParseException ex) {
            Logger.getLogger(SvServicio.class.getName()).log(Level.SEVERE, null, ex);
        }
    }


    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
