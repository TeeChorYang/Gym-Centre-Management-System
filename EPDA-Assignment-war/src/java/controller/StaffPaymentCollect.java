/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.io.PrintWriter;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import models.AgcClass;
import models.AgcClassFacade;
import models.AgcPayment;
import models.AgcPaymentFacade;
import models.CustomerHasClass;
import models.CustomerHasClassFacade;

/**
 *
 * @author TheOne
 */
@WebServlet(name = "StaffPaymentCollect", urlPatterns = {"/StaffPaymentCollect"})
public class StaffPaymentCollect extends HttpServlet {

    @EJB
    private AgcClassFacade agcClassFacade;

    @EJB
    private CustomerHasClassFacade customerHasClassFacade;

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
        
        String idStr = request.getParameter("id").trim();
        Integer id = null;
        String status = request.getParameter("status");
        String classId = request.getParameter("classId");
        String customerId = request.getParameter("customerId");

        try (PrintWriter out = response.getWriter()) {
            try{
                id = Integer.parseInt(idStr);
                AgcPayment c = agcPaymentFacade.find(id); 

                c.setStatus(status);

                agcPaymentFacade.edit(c);

                HttpSession s = request.getSession(false);

                if ("collect".equals(status)) {
                    CustomerHasClass chc = new CustomerHasClass();
                    chc.setClassId(classId); 
                    chc.setCustomerId(customerId); 

                    customerHasClassFacade.create(chc); 

                    s.setAttribute("popupMessage", "The payment collected and customer added into the class.");
                } else if ("reject".equals(status)) {
                    s.setAttribute("popupMessage", "The payment is rejected.");
                }

                response.sendRedirect("StaffPaymentInformation");
            }catch(Exception e){
                HttpSession s = request.getSession(false);
                s.setAttribute("errorMessage", "Invalid Input: " + e.getMessage());
                response.sendRedirect("StaffPaymentCollect?id=" + id); 
            }
        }
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
        String idStr = request.getParameter("id");
        Integer id = Integer.parseInt(idStr);
        AgcPayment payment = agcPaymentFacade.find(id);
        AgcClass agcClass = agcClassFacade.find(payment.getClassId());
        double classPrice = agcClass.getPrice();
        double change = payment.getAmount() - agcClass.getPrice();
        request.setAttribute("payment", payment);
        request.setAttribute("classPrice", classPrice);
        request.setAttribute("change", change);
        request.getRequestDispatcher("staff-payment-collect.jsp").forward(request, response);
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
