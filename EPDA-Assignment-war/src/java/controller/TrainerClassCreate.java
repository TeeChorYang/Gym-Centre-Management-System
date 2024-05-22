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
import models.AgcTrainer;

/**
 *
 * @author TheOne
 */
@WebServlet(name = "TrainerClassCreate", urlPatterns = {"/TrainerClassCreate"})
public class TrainerClassCreate extends HttpServlet {

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
        
        String name = request.getParameter("name");
        HttpSession session = request.getSession(false);
        AgcTrainer trainer = (AgcTrainer) session.getAttribute("LoginUser");
        String trainerId = trainer.getId();
        String priceString = request.getParameter("price");
        double price;
        try {
            price = Double.parseDouble(priceString);
        } catch (NumberFormatException e) {
            request.setAttribute("errorMessage", "Price must be a number.");
            request.getRequestDispatcher("trainer-class-create.jsp").include(request, response);
            return;
        }
        String description = request.getParameter("description");

        try (PrintWriter out = response.getWriter()) {
            agcClassFacade.create(new AgcClass(name, trainerId, price, description, agcClassFacade));   
            response.sendRedirect("TrainerClass");

        } catch(Exception e) {
            request.setAttribute("errorMessage", e.getMessage());
            request.getRequestDispatcher("trainer-class-create.jsp").include(request, response);
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