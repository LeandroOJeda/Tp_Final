
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


@WebServlet(name = "SvCliente", urlPatterns = {"/SvCliente"})
public class SvCliente extends HttpServlet {
    
    Controladora controlLog = new Controladora();


    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        

    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            String nombre = request.getParameter("nombre");
            String apellido = request.getParameter("apellido");
            String celular = request.getParameter("celular");
            String direccion = request.getParameter("direccion");
            String dni = request.getParameter("dni");
            String email = request.getParameter("email");
            String fecha = request.getParameter("fechanac");
            SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
            Date fechaNac = formato.parse(fecha);
            String nacionalidad = request.getParameter("nacionalidad");
            
            request.getSession().setAttribute("nombre", nombre);
            request.getSession().setAttribute("apellido", apellido);
            request.getSession().setAttribute("celular", celular);
            request.getSession().setAttribute("direccion", direccion);
            request.getSession().setAttribute("dni", dni);
            request.getSession().setAttribute("email", email);
            request.getSession().setAttribute("fechanac", fechaNac);
            request.getSession().setAttribute("nacionalidad", nacionalidad);
            
            controlLog.crearCliente(nombre, apellido, celular, direccion, dni, email, fechaNac, nacionalidad);
            response.sendRedirect("inicio.jsp");
        } catch (ParseException ex) {
            Logger.getLogger(SvCliente.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }


    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
