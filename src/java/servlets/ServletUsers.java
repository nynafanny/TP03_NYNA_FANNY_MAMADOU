/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collection;
import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import utilisations.modeles.Utilisateur;
import utilisations.modeles.Utilisateur;
import utilisations.modeles.gestionnaireUtilisateurs;


/**
 *
 * @author NYNA FANNY
 */
public class ServletUsers extends HttpServlet {

    @EJB
    private gestionnaireUtilisateurs gestionnaireUtilisateurs;

    
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
        // Pratique pour décider de l'action à faire
        String action = request.getParameter("action");
        String forwardTo = "";
        String message = "";
        
         if (action != null) {
            if (action.equals("listerLesUtilisateurs")) {
                Collection<Utilisateur> liste = gestionnaireUtilisateurs.getAllUsers();
                request.setAttribute("listeDesUsers", liste);
                forwardTo = "index.jsp?action=listerLesUtilisateurs";
                message = "Liste des utilisateurs";
            } else if (action.equals("creerUtilisateursDeTest")) {
                gestionnaireUtilisateurs.creerUtilisateursDeTest();
                Collection<Utilisateur> liste = gestionnaireUtilisateurs.getAllUsers();
                request.setAttribute("listeDesUsers", liste);
                forwardTo = "index.jsp?action=listerLesUtilisateurs";
                message = "Liste des utilisateurs";
            } else  if (action . equals ( "creerUnUtilisateur" )) { 
                createutilisateur(request); 
                Collection< Utilisateur >  liste = gestionnaireUtilisateurs.getAllUsers(); 
                request.setAttribute ("listeDesUsers" , liste); 
                forwardTo =  " index.jsp? action = listerLesUtilisateurs " ; 
                message  =  " Liste des utilisateurs " ; 

                }
            else {
                forwardTo = "index.jsp?action=todo";
                message = "La fonctionnalité pour le paramètre " + action + " est à implémenter !";
            }
        }
         
        RequestDispatcher dp = request.getRequestDispatcher(forwardTo + "&message=" + message);
        dp.forward(request, response);
        // Après un forward, plus rien ne peut être exécuté après !
        
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
    
    public void createutilisateur(HttpServletRequest request){
       String nom, prenom, login;
       nom=request.getParameter("nom");
       prenom=request.getParameter("prenom");
       login=request.getParameter("login");  
       gestionnaireUtilisateurs.creeUtilisateur(nom,prenom, login);
   }

}
   
    