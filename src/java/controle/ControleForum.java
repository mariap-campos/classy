/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controle;

import dao.AdminDAO;
import dao.AtividadeDAO;
import dao.ClassyDAO;
import dao.ForumDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelo.Admin;
import modelo.Atividade;
import modelo.Classy;
import modelo.Forum;
import util.ConverteDate;

/**
 *
 * @author Maria Paula
 */
@WebServlet(name = "ControleForum", urlPatterns = {"/ControleForum"})
public class ControleForum extends HttpServlet {

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
        try (PrintWriter out = response.getWriter()) {
            String acao = request.getParameter("acao");
            if ("Postar".equals(acao)) {
                int id_admin = Integer.parseInt(request.getParameter("id"));
                int classy_token = Integer.parseInt(request.getParameter("id_classy"));
                
                Forum forum = new Forum();
                forum.setPostagem(request.getParameter("txtPost"));
                forum.setAssunto(request.getParameter("txtAssunto"));
                forum.setAdmin_id(id_admin);
                forum.setClassy_token(classy_token);
                
                ForumDAO cdao = new ForumDAO();
                cdao.postar(forum);
                
                request.setAttribute("title", "Postagem feita!");
                request.setAttribute("mensagem", "Voltando ao fórum");
                request.setAttribute("classy", classy_token);
                request.setAttribute("tipo", "Forum");
                request.getRequestDispatcher("success.jsp").forward(request, response);
            } else if ("Excluir".equals(acao)) {
                Forum forum = new Forum();
                int id = Integer.parseInt(request.getParameter("id"));
                int id_classy = Integer.parseInt(request.getParameter("id_classy"));
                
                forum.setId(id);
                ForumDAO adao = new ForumDAO();
                adao.apagar(forum);     
                
                request.setAttribute("title", "Mensagem apagada com sucesso!");
                request.setAttribute("mensagem", "Voltando ao fórum");
                request.setAttribute("classy", id_classy);
                request.setAttribute("tipo", "Forum");
                request.getRequestDispatcher("success.jsp").forward(request, response);
            }
        } catch (Exception e) {
            System.out.println(e);
//            request.setAttribute("erro", e);
//            request.getRequestDispatcher("erro.jsp").forward(request, response);
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
