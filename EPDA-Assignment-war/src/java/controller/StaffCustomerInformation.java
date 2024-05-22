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
import models.AgcCustomer;
import models.AgcCustomerFacade;

/**
 *
 * @author TheOne
 */
@WebServlet(name = "StaffCustomerInformation", urlPatterns = {"/StaffCustomerInformation"})
public class StaffCustomerInformation extends HttpServlet {

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
        
        String searchQuery = request.getParameter("search");
        List<AgcCustomer> customerList;
        if (searchQuery != null && !searchQuery.isEmpty()) {
            customerList = agcCustomerFacade.findAllCustomer().stream()
                .filter(customer -> customer.getName().toLowerCase().contains(searchQuery.toLowerCase())
                    || customer.getId().toLowerCase().contains(searchQuery.toLowerCase())
                    || customer.getEmail().toLowerCase().contains(searchQuery.toLowerCase())
                    || customer.getPhone().toLowerCase().contains(searchQuery.toLowerCase()))
                .collect(Collectors.toList());
        } else {
            customerList = agcCustomerFacade.findAllCustomer();
        }

        request.setAttribute("customerList", customerList);

        request.getRequestDispatcher("staff-customer-information.jsp").include(request, response);
        
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
