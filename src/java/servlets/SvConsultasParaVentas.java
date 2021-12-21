
package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import logica.Cliente;
import logica.Controladora;
import logica.Empleado;
import logica.Servicio;


@WebServlet(name = "SvConsultasParaVentas", urlPatterns = {"/SvConsultasParaVentas"})
public class SvConsultasParaVentas extends HttpServlet {
    
    Controladora controlLog = new Controladora();


    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        //Traigo las listas de clientes, servicios y empleados para relacionarlos luego con las ventas.
        List<Cliente> listaClientes = controlLog.traerClientes();
        HttpSession misesion = request.getSession();
        misesion.setAttribute("listaClientes", listaClientes);

        
        List<Servicio> listaServicios = controlLog.traerServicio();
        HttpSession misesion1 = request.getSession();
        misesion1.setAttribute("listaServicios", listaServicios);
        
        
        List<Empleado> listaEmpleados = controlLog.traerEmpleados();
        HttpSession misesion2 = request.getSession();
        misesion2.setAttribute("listaEmpleados", listaEmpleados);
        response.sendRedirect("Ventas.jsp");
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }


    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
