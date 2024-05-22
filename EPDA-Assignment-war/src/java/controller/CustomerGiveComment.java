/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import models.AgcComments;
import models.AgcCommentsFacade;
import models.AgcCustomer;

/**
 *
 * @author TheOne
 */
@WebServlet(name = "CustomerGiveComment", urlPatterns = {"/CustomerGiveComment"})
public class CustomerGiveComment extends HttpServlet {

    @EJB
    private AgcCommentsFacade agcCommentsFacade;

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
        HttpSession s = request.getSession(false);
        AgcCustomer c = (AgcCustomer)s.getAttribute("LoginUser");
        s.setAttribute("customerId", c.getId()); 
        request.setAttribute("customerName", c.getName());

        request.getRequestDispatcher("customer-give-comment.jsp").include(request, response);
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String classId = request.getParameter("id");

        HttpSession s = request.getSession(false);
        s.setAttribute("classId", classId);

        request.getRequestDispatcher("customer-give-comment.jsp").forward(request, response); 
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
            HttpSession session = request.getSession(false);
            String classId = request.getParameter("classId");
            
            AgcCustomer c = (AgcCustomer)session.getAttribute("LoginUser");
            String customerId = c.getId();
            String comment = request.getParameter("comment");

            try {
               AgcComments agcComments = new AgcComments();
               agcComments.setCustomerId(customerId);
               agcComments.setClassId(classId);
               agcComments.setComment(comment);

               java.util.Date utilDate = new java.util.Date();
               java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
               agcComments.setCommentDate(sqlDate);

               agcCommentsFacade.create(agcComments);
               session.setAttribute("successMessage", "Comment saved successfully");
               response.sendRedirect("CustomerGiveComment?id="+classId); 
           } catch(Exception e) {
                request.setAttribute("errorMessage", e.getMessage());
                request.getRequestDispatcher("CustomerGiveComment?id="+classId).include(request, response);
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
