
package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import logica.Controladora;


@WebServlet(name = "SvEliminarServicios", urlPatterns = {"/SvEliminarServicios"})
public class SvEliminarServicios extends HttpServlet {
    
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
        
        //traemos la id
        int id = Integer.parseInt(request.getParameter("id"));
        controlLog.borrarServicio(id);
        
        //luego de eliminar actualizo mi lista de Servicios
        request.getSession().setAttribute("listaServicios", controlLog.traerServicio());
        
        //Redirecciono a Servicio.jsp
        response.sendRedirect("Servicio.jsp");
    }


    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
