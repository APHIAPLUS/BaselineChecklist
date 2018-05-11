/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package checklist;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Geofrey Nyabuto
 */
public class phone_validator extends HttpServlet {

    String phone,message;
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
   message=phone="" ;        
    phone=request.getParameter("phone");
    if((phone.startsWith("070") ||phone.startsWith("071") ||phone.startsWith("072")||phone.startsWith("073")||phone.startsWith("075")||phone.startsWith("077")||phone.startsWith("078"))&&(phone.length()==10))
    {     

    message="The Phone Number is Ok";   
    }
   else if(phone.length()>2 && !(phone.startsWith("070") ||phone.startsWith("071") ||phone.startsWith("072")||phone.startsWith("073")||phone.startsWith("075")||phone.startsWith("077")||phone.startsWith("078"))){
       message="Incorrect Phone number.";
    }
   else if((phone.length()==2 && !phone.startsWith("07")) || phone.length()==1 && !phone.startsWith("0")){
       message="Incorrect Phone number.";
   }

   else{
       message="";
   }  
    out.println(message);
    } finally {            
            out.close();
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP
     * <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP
     * <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}
