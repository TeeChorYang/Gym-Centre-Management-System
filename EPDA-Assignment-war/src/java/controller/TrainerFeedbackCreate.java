/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.util.Date;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import models.AgcFeedback;
import models.AgcFeedbackFacade;
import models.AgcTrainer;

/**
 *
 * @author TheOne
 */
@WebServlet(name = "TrainerFeedbackCreate", urlPatterns = {"/TrainerFeedbackCreate"})
public class TrainerFeedbackCreate extends HttpServlet {

    @EJB
    private AgcFeedbackFacade agcFeedbackFacade;

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
        AgcTrainer c = (AgcTrainer)s.getAttribute("LoginUser");
        request.setAttribute("trainerId", c.getId()); 
        request.setAttribute("trainerName", c.getName());

        request.getRequestDispatcher("trainer-feedback-create.jsp").include(request, response);
        
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
        String customerId = request.getParameter("id");
        String classId = request.getParameter("classId");

        request.setAttribute("customerId", customerId);
        request.setAttribute("classId", classId);

        request.getRequestDispatcher("trainer-feedback-create.jsp").forward(request, response); 
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
            String customerId = request.getParameter("customerId");
            String classId = request.getParameter("classId");
            HttpSession session = request.getSession(false);
            AgcTrainer trainer = (AgcTrainer) session.getAttribute("LoginUser");
            String trainerId = trainer.getId();
            String feedback = request.getParameter("feedback");

            try {
               AgcFeedback agcFeedback = new AgcFeedback();
               agcFeedback.setCustomerId(customerId);
               agcFeedback.setClassId(classId);
               agcFeedback.setTrainerId(trainerId);
               agcFeedback.setFeedback(feedback);

               java.util.Date utilDate = new java.util.Date();
               java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
               agcFeedback.setFeedbackDate(sqlDate);

               agcFeedbackFacade.create(agcFeedback);
               session.setAttribute("successMessage", "Feedback saved successfully");
               response.sendRedirect("TrainerFeedbackCreate?id="+customerId+"&classId="+classId); 
           } catch(Exception e) {
                request.setAttribute("errorMessage", e.getMessage());
                request.getRequestDispatcher("TrainerFeedbackCreate?id="+customerId+"&classId="+classId).include(request, response);
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
