/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import models.AgcClass;
import models.AgcClassFacade;
import models.AgcCustomer;
import models.AgcPaymentFacade;

/**
 *
 * @author TheOne
 */
@WebServlet(name = "CustomerClass", urlPatterns = {"/CustomerClass"})
public class CustomerClass extends HttpServlet {

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
        
        HttpSession s = request.getSession(false);
        AgcCustomer c = (AgcCustomer)s.getAttribute("LoginUser");
        String customerId = c.getId();
        request.setAttribute("customerId", customerId);

        String searchQuery = request.getParameter("search");
        List<AgcClass> classList;
        if (searchQuery != null && !searchQuery.isEmpty()) {
            classList = agcClassFacade.findAllClass().stream()
                .filter(agcClass -> agcClass.getName().toLowerCase().contains(searchQuery.toLowerCase()))
                .collect(Collectors.toList());
        } else {
            classList = agcClassFacade.findAllClass();
        }

        List<String> paidClassIds = classList.stream()
            .filter(agcClass -> agcPaymentFacade.paymentExists(customerId, agcClass.getId()))
            .map(AgcClass::getId)
            .collect(Collectors.toList());

        List<String> rejectedClassIds = agcPaymentFacade.findAllPayment().stream()
            .filter(payment -> payment.getCustomerId().equals(customerId) && payment.getStatus().equals("reject"))
            .map(payment -> payment.getClassId())
            .collect(Collectors.toList());

        request.setAttribute("classList", classList);
        request.setAttribute("paidClassIds", paidClassIds);
        request.setAttribute("rejectedClassIds", rejectedClassIds);

        request.getRequestDispatcher("customer-class.jsp").include(request, response);
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
