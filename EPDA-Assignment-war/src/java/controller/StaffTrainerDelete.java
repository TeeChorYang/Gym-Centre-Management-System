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
import models.AgcComments;
import models.AgcCommentsFacade;
import models.AgcFeedback;
import models.AgcFeedbackFacade;
import models.AgcPayment;
import models.AgcPaymentFacade;
import models.AgcTrainer;
import models.AgcTrainerFacade;
import models.CustomerHasClass;
import models.CustomerHasClassFacade;

/**
 *
 * @author TheOne
 */
@WebServlet(name = "StaffTrainerDelete", urlPatterns = {"/StaffTrainerDelete"})
public class StaffTrainerDelete extends HttpServlet {

    @EJB
    private AgcPaymentFacade agcPaymentFacade;

    @EJB
    private AgcCommentsFacade agcCommentsFacade;

    @EJB
    private AgcClassFacade agcClassFacade;

    @EJB
    private CustomerHasClassFacade customerHasClassFacade;

    @EJB
    private AgcFeedbackFacade agcFeedbackFacade;

    @EJB
    private AgcTrainerFacade agcTrainerFacade;

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

        AgcTrainer trainer = agcTrainerFacade.find(id);
        if (trainer != null) {
            List<AgcClass> classes = agcClassFacade.findByTrainerId(id);
            for (AgcClass agcClass : classes) {
                List<AgcComments> comments = agcCommentsFacade.findByClassId(agcClass.getId());
                for (AgcComments comment : comments) {
                    agcCommentsFacade.remove(comment);
                }

                List<AgcFeedback> feedbacks = agcFeedbackFacade.findByClassId(agcClass.getId());
                for (AgcFeedback feedback : feedbacks) {
                    agcFeedbackFacade.remove(feedback);
                }

                List<CustomerHasClass> customerClasses = customerHasClassFacade.findByClassId(agcClass.getId());
                for (CustomerHasClass customerClass : customerClasses) {
                    customerHasClassFacade.remove(customerClass);
                }
                
                List<AgcPayment> payments = agcPaymentFacade.findByClassId(agcClass.getId());
                for (AgcPayment payment : payments) {
                    agcPaymentFacade.updateStatus(payment, "class removed");
                }

                agcClassFacade.remove(agcClass);
            }
            agcTrainerFacade.remove(trainer);
        }
        response.sendRedirect("StaffTrainerInformation");

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
