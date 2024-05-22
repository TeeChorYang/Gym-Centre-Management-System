/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.io.PrintWriter;
import static java.lang.System.out;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import models.AgcCustomer;
import models.AgcCustomerFacade;
import models.AgcStaffFacade;
import models.AgcTrainer;
import models.AgcTrainerFacade;

/**
 *
 * @author TheOne
 */
@WebServlet(name = "Register", urlPatterns = {"/Register"})
public class Register extends HttpServlet {

    @EJB
    private AgcStaffFacade agcStaffFacade;

    @EJB
    private AgcTrainerFacade agcTrainerFacade;

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
        
        String userType = request.getParameter("user_type");
        String id = request.getParameter("username");
        String password = request.getParameter("password");
        String confirmPassword = request.getParameter("confirm_password");
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String phone = request.getParameter("phone");
        
        try (PrintWriter out = response.getWriter()) {
            try{
                if ("admin".equals(id)) {
                   throw new Exception("Username already exists. Please choose a different username.");
                }
                if (agcCustomerFacade.find(id) != null || agcTrainerFacade.find(id) != null || agcStaffFacade.find(id) != null) {
                    throw new Exception("Username already exists. Please choose a different username.");
                }

                if (!password.equals(confirmPassword)){
                    throw new Exception("Password and confirm password do not match.");
                }
                if ("Customer".equals(userType)){
                    agcCustomerFacade.create(new AgcCustomer(id,password,name,email,phone,false));
                    request.getSession().setAttribute("message", "Hi " + id + ", Thank you for your registration as a Customer. Please wait for the staff to approve your account.");
                    response.sendRedirect("login.jsp");
                }
                if ("Trainer".equals(userType)){
                    agcTrainerFacade.create(new AgcTrainer(id,password,name,email,phone,false));
                    request.getSession().setAttribute("message", "Hi " + id + ", Thank you for your registration as Trainer. Please wait for the staff to approve your account.");
                    response.sendRedirect("login.jsp");
                }
            }catch(Exception e){
                request.setAttribute("errorMessage",e.getMessage());
                request.getRequestDispatcher("signup.jsp").include(request,response);
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
