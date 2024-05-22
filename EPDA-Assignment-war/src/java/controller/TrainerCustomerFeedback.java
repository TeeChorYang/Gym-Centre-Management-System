/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.util.List;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import models.AgcClass;
import models.AgcClassFacade;
import models.AgcCustomer;
import models.AgcCustomerFacade;
import models.AgcFeedback;
import models.AgcFeedbackFacade;

/**
 *
 * @author TheOne
 */
@WebServlet(name = "TrainerCustomerFeedback", urlPatterns = {"/TrainerCustomerFeedback"})
public class TrainerCustomerFeedback extends HttpServlet {

    @EJB
    private AgcClassFacade agcClassFacade;

    @EJB
    private AgcFeedbackFacade agcFeedbackFacade;

    @EJB
    private AgcCustomerFacade agcCustomerFacade;

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
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String customerId = request.getParameter("id");
        String classId = request.getParameter("classId");

        AgcCustomer agcCustomer = agcCustomerFacade.findCustomerById(customerId);
        String customerName = agcCustomer.getName();
        request.setAttribute("customerName", customerName);
        
        AgcClass agcClass = agcClassFacade.findClassById(classId);
        String className = agcClass.getName();
        request.setAttribute("className", className);

        List<AgcFeedback> feedbacks = agcFeedbackFacade.findFeedbacksByCustomerIdAndClassId(customerId, classId);
        
        request.setAttribute("customerId", customerId);
        request.setAttribute("classId", classId);

        request.setAttribute("feedbacks", feedbacks);

        request.getRequestDispatcher("trainer-customer-feedback.jsp").forward(request, response);
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
