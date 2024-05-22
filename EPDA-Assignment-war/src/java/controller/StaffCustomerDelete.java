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
import models.AgcComments;
import models.AgcCommentsFacade;
import models.AgcCustomer;
import models.AgcCustomerFacade;
import models.AgcFeedback;
import models.AgcFeedbackFacade;
import models.AgcPayment;
import models.AgcPaymentFacade;
import models.CustomerHasClass;
import models.CustomerHasClassFacade;

/**
 *
 * @author TheOne
 */
@WebServlet(name = "StaffCustomerDelete", urlPatterns = {"/StaffCustomerDelete"})
public class StaffCustomerDelete extends HttpServlet {

    @EJB
    private AgcPaymentFacade agcPaymentFacade;

    @EJB
    private CustomerHasClassFacade customerHasClassFacade;

    @EJB
    private AgcFeedbackFacade agcFeedbackFacade;

    @EJB
    private AgcCommentsFacade agcCommentsFacade;

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
        
        String id = request.getParameter("id").trim();

        AgcCustomer customer = agcCustomerFacade.find(id);
        if (customer != null) {
            List<AgcComments> comments = agcCommentsFacade.findByCustomerId(id);
            for (AgcComments comment : comments) {
                agcCommentsFacade.remove(comment);
            }

            List<AgcFeedback> feedbacks = agcFeedbackFacade.findByCustomerId(id);
            for (AgcFeedback feedback : feedbacks) {
                agcFeedbackFacade.remove(feedback);
            }

            List<CustomerHasClass> customerClasses = customerHasClassFacade.findByCustomerId(id);
            for (CustomerHasClass customerClass : customerClasses) {
                customerHasClassFacade.remove(customerClass);
            }

            List<AgcPayment> payments = agcPaymentFacade.findByCustomerId(id);
            for (AgcPayment payment : payments) {
                agcPaymentFacade.updateStatus(payment, "customer not found");
            }

            agcCustomerFacade.remove(customer);
        }
        response.sendRedirect("StaffCustomerInformation");
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
        processRequest(request, response);
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
