
package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import logica.Controladora;
import logica.Empleado;


@WebServlet(name = "SvModificarEmple", urlPatterns = {"/SvModificarEmple"})
public class SvModificarEmple extends HttpServlet {
    
    Controladora controlLog = new Controladora();


    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String nombre = request.getParameter("nombre");
        String apellido = request.getParameter("apellido");
        String cargo = request.getParameter("cargo");
        double sueldo = Double.parseDouble(request.getParameter("sueldo"));
        
        Empleado emple = controlLog.buscarEmpleado(id);
        emple.setNombre(nombre);
        emple.setApellido(apellido);
        emple.setCargo(cargo);
        emple.setSueldo(sueldo);
        
        
        controlLog.modificarEmpleado (emple);
        
        request.getSession().setAttribute("listaEmpleados", controlLog.traerEmpleados());
        response.sendRedirect("empleados.jsp");
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        int id = Integer.parseInt(request.getParameter("id"));
        Empleado emple = controlLog.buscarEmpleado(id);
        
        HttpSession misesion = request.getSession();
        misesion.setAttribute("empleado", emple);
        response.sendRedirect("formEditEmple.jsp");
        
    }


    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
