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
@WebServlet(name = "StaffPaymentDelete", urlPatterns = {"/StaffPaymentDelete"})
public class StaffPaymentDelete extends HttpServlet {

    @EJB
    private CustomerHasClassFacade customerHasClassFacade;

    @EJB
    private AgcFeedbackFacade agcFeedbackFacade;

    @EJB
    private AgcCommentsFacade agcCommentsFacade;

    @EJB
    private AgcPaymentFacade agcPaymentFacade;

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
        
        int id = Integer.parseInt(request.getParameter("id").trim());

        AgcPayment payment = agcPaymentFacade.find(id);
        if (payment != null) {
            String customerId = payment.getCustomerId();
            String classId = payment.getClassId();

            CustomerHasClass customerClass = customerHasClassFacade.findByCustomerIdAndClassId(customerId, classId);
            if (customerClass != null) {
                List<AgcComments> comments = agcCommentsFacade.findByCustomerIdAndClassId(customerId, classId);
                for (AgcComments comment : comments) {
                    agcCommentsFacade.remove(comment);
                }

                List<AgcFeedback> feedbacks = agcFeedbackFacade.findFeedbacksByCustomerIdAndClassId(customerId, classId);
                for (AgcFeedback feedback : feedbacks) {
                    agcFeedbackFacade.remove(feedback);
                }

                customerHasClassFacade.remove(customerClass);
            }

            agcPaymentFacade.remove(payment);
        }
        response.sendRedirect("StaffPaymentInformation");
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
