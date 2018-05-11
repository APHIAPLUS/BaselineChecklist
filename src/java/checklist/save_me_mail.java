/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package checklist;

import database.AddTrails;
import database.dbConn;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Geofrey Nyabuto
 */
public class save_me_mail extends HttpServlet {
HttpSession session;
String mail,userid;
int found,mail_id;
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
       session=request.getSession();
    found=mail_id=0;
    mail="";
    
    dbConn conn = new dbConn();
    mail=request.getParameter("mail");
    String m_selector="SELECT id FROM mail";
    conn.rs=conn.st.executeQuery(m_selector);
    if(conn.rs.next()==true){
    mail_id=conn.rs.getInt(1);    
    }
    userid=session.getAttribute("userid").toString();
   AddTrails trails = new AddTrails();
   
    if(mail_id>0){
        String update="UPDATE mail SET mail='"+mail+"' WHERE id='"+mail_id+"' ";
        conn.st.executeUpdate(update);
        session.setAttribute("mail", "<font color=\"green\">Mail Updated Successfully.</font>");
         trails.addTrails(userid, "Updated M&E email address to : "+mail+"");   
    }
    if(mail_id==0){
        String update="INSERT INTO mail SET id='1',mail='"+mail+"'";
        conn.st.executeUpdate(update);
        session.setAttribute("mail", "<font color=\"green\">Mail Saved Successfully.</font>");
   trails.addTrails(userid, "Added M&E email address as : "+mail+"");
    }
    response.sendRedirect("set_mail.jsp");
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
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(save_me_mail.class.getName()).log(Level.SEVERE, null, ex);
        }
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
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(save_me_mail.class.getName()).log(Level.SEVERE, null, ex);
        }
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
