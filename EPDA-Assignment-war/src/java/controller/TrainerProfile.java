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
import models.AgcTrainer;
import models.AgcTrainerFacade;

/**
 *
 * @author TheOne
 */
@WebServlet(name = "TrainerProfile", urlPatterns = {"/TrainerProfile"})
public class TrainerProfile extends HttpServlet {

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
        
        String password = request.getParameter("password");
        String confirmPassword = request.getParameter("confirm_password");
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String phone = request.getParameter("phone");
        
        try (PrintWriter out = response.getWriter()) {
            try{
                HttpSession s = request.getSession(false);
                AgcTrainer c = (AgcTrainer)s.getAttribute("LoginUser");
                if (password != null && !password.trim().isEmpty()) {
                    if (!password.equals(confirmPassword)){
                        throw new Exception("Password and confirm password do not match");
                    }
                    c.setPassword(password);
                }
                c.setName(name);
                c.setEmail(email);
                c.setPhone(phone);
                agcTrainerFacade.edit(c);
                request.getRequestDispatcher("TrainerHome").forward(request, response);
            }catch(Exception e){
                request.setAttribute("errorMessage", "Invalid Input: " + e.getMessage());
                HttpSession s = request.getSession(false);
                AgcTrainer c = (AgcTrainer)s.getAttribute("LoginUser");
                request.setAttribute("user", c);
                request.getRequestDispatcher("trainer-profile.jsp").forward(request,response);
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
        HttpSession s = request.getSession(false);
        if (s == null) {
            System.out.println("Session is null");
        } else {
            AgcTrainer c = (AgcTrainer)s.getAttribute("LoginUser");
            if (c == null) {
                System.out.println("LoginUser attribute is null");
            } else {
                System.out.println("User: " + c);
                request.setAttribute("user", c);
                request.getRequestDispatcher("trainer-profile.jsp").forward(request, response);
            }
        }
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
