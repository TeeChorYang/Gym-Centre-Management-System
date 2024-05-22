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
import models.AgcClass;
import models.AgcClassFacade;
import models.AgcCustomer;
import models.AgcPayment;
import models.AgcPaymentFacade;

/**
 *
 * @author TheOne
 */
@WebServlet(name = "CustomerClassApply", urlPatterns = {"/CustomerClassApply"})
public class CustomerClassApply extends HttpServlet {

    @EJB
    private AgcPaymentFacade agcPaymentFacade;

    @EJB
    private AgcClassFacade agcClassFacade;

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
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
        String classId = request.getParameter("classId");
        AgcClass agcClass = agcClassFacade.find(classId);
        String className = agcClass.getName();
        request.setAttribute("className", className);
        Double classPrice = agcClass.getPrice();
        request.setAttribute("classPrice", classPrice);
        String classDescription = agcClass.getDescription();
        request.setAttribute("classDescription", classDescription);
        request.setAttribute("classId", classId);

        request.getRequestDispatcher("customer-class-apply.jsp").forward(request, response);
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
        
        String classId = request.getParameter("classId");
        String amountString = request.getParameter("amount");
        double amount;

        if (amountString != null && !amountString.isEmpty()) {
            try {
                amount = Double.parseDouble(amountString);

                AgcClass agcClass = agcClassFacade.find(classId);
                Double classPrice = agcClass.getPrice();

                if (amount < classPrice) {
                    request.getSession().setAttribute("errorMessage", "Amount must be higher than class price.");
                    response.sendRedirect("CustomerClassApply?classId=" + classId);
                    return;
                }

                AgcCustomer customer = (AgcCustomer) request.getSession().getAttribute("LoginUser");
                String customerId = customer.getId();

                AgcPayment agcPayment = new AgcPayment();
                agcPayment.setClassId(classId);
                agcPayment.setCustomerId(customerId);
                agcPayment.setAmount(amount);
                agcPayment.setStatus("pending");

                agcPaymentFacade.create(agcPayment);

                double change = amount - classPrice;
                request.getSession().setAttribute("message", "Hi, Thank you for your Payment. Your change is: " + change);

            } catch (NumberFormatException e) {
                request.getSession().setAttribute("errorMessage", "Price must be a number.");
                response.sendRedirect("CustomerClassApply?classId=" + classId);
                return;
            }
        } else {
            request.getSession().setAttribute("errorMessage", "Amount is required.");
            response.sendRedirect("CustomerClassApply?classId=" + classId);
            return;
        }
        response.sendRedirect("CustomerClass");
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
