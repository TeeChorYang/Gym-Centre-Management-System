/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
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
import models.CustomerHasClass;
import models.CustomerHasClassFacade;

/**
 *
 * @author TheOne
 */
@WebServlet(name = "CustomerClassJoined", urlPatterns = {"/CustomerClassJoined"})
public class CustomerClassJoined extends HttpServlet {

    @EJB
    private CustomerHasClassFacade customerHasClassFacade;

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

        String searchQuery = request.getParameter("search");
        List<CustomerHasClass> customerClasses = customerHasClassFacade.findByCustomerId(customerId);
        List<AgcClass> classList = new ArrayList<>();
        for (CustomerHasClass customerClass : customerClasses) {
            AgcClass agcClass = agcClassFacade.find(customerClass.getClassId());
            if (agcClass != null) {
                if (searchQuery != null && !searchQuery.isEmpty()) {
                    if (agcClass.getName().toLowerCase().contains(searchQuery.toLowerCase())
                        || agcClass.getId().toLowerCase().contains(searchQuery.toLowerCase())) {
                        classList.add(agcClass);
                    }
                } else {
                    classList.add(agcClass);
                }
            }
        }

        request.setAttribute("classList", classList);

        request.getRequestDispatcher("customer-class-joined.jsp").include(request, response);
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
