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
import logica.Cliente;
import logica.Controladora;

@WebServlet(name = "SvModificarCliente", urlPatterns = {"/SvModificarCliente"})
public class SvModificarCliente extends HttpServlet {
    
    Controladora controlLog = new Controladora();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
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
            
            int id = Integer.parseInt(request.getParameter("id"));
            Cliente clien = controlLog.buscarCliente(id);
            
            clien.setNombre(nombre);
            clien.setApellido(apellido);
            clien.setCelular(celular);
            clien.setDireccion(direccion);
            clien.setDni(dni);
            clien.setEmail(email);
            clien.setFecha_nac(fechaNac);
            clien.setNacionalidad(nacionalidad);
            controlLog.modificarCliente(clien);
            
            request.getSession().setAttribute("listaClientes", controlLog.traerClientes());
            response.sendRedirect("clientes.jsp");
        } catch (ParseException ex) {
            Logger.getLogger(SvModificarCliente.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int id = Integer.parseInt(request.getParameter("id"));
        Cliente clien = controlLog.buscarCliente(id);

        HttpSession misesion = request.getSession();
        misesion.setAttribute("cliente", clien);
        response.sendRedirect("formEditClien.jsp");
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
