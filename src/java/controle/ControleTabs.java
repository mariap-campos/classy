/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controle;

import dao.AdminDAO;
import dao.AlunoDAO;
import dao.ClassyDAO;
import dao.ForumDAO;
import dao.ProvaDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelo.Admin;
import modelo.Aluno;
import modelo.Atividade;
import modelo.Classy;
import modelo.Forum;
import modelo.Prova;

/**
 *
 * @author Maria Paula
 */
@WebServlet(name = "ControleTabs", urlPatterns = {"/ControleTabs"})
public class ControleTabs extends HttpServlet {

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
            if ("Home".equals(acao)) {
                request.getRequestDispatcher("adminHome.jsp").forward(request, response);
            } else if ("Atividades".equals(acao)) {
                    int classy_token = Integer.parseInt(request.getParameter("id"));
                    
                    Atividade ativ = new Atividade();
                    ativ.setClassy_token(classy_token);
                
                    dao.AtividadeDAO adao = new dao.AtividadeDAO();
                    ArrayList<Atividade> todosAtividade = new ArrayList<Atividade>();
                    todosAtividade = adao.consultarTodos(ativ);
                    
                    request.setAttribute("atividades", todosAtividade);
                    request.getRequestDispatcher("adminAtividade.jsp").forward(request, response);
            } else if ("Forum".equals(acao)) {
                int id = Integer.parseInt(request.getParameter("id"));
                
                Forum forum = new Forum();
                forum.setClassy_token(id);
                
                ForumDAO fdao = new ForumDAO();
                ArrayList<Forum> todosPost = new ArrayList<Forum>();
                todosPost = fdao.consultarTodos(forum);


                request.setAttribute("posts", todosPost);
                request.getRequestDispatcher("adminForum.jsp").forward(request, response);
            } else if ("Alunos".equals(acao)) {
                int id = Integer.parseInt(request.getParameter("id"));
                
                Aluno forum = new Aluno();
                forum.setClassy_token(id);
                
                AlunoDAO fdao = new AlunoDAO();
                ArrayList<Aluno> todosAlunos = new ArrayList<Aluno>();
                todosAlunos = fdao.consultarTodos(forum);


                request.setAttribute("alunos", todosAlunos);
                request.getRequestDispatcher("adminAluno.jsp").forward(request, response);
            } else if ("Provas".equals(acao)) {
                int id = Integer.parseInt(request.getParameter("id"));
                
                Prova prova = new Prova();
                prova.setToken_classy(id);
                
                ProvaDAO fdao = new ProvaDAO();
                ArrayList<Prova> todosProva = new ArrayList<Prova>();
                todosProva = fdao.consultarTodos(prova);


                request.setAttribute("provas", todosProva);
                request.getRequestDispatcher("adminProva.jsp").forward(request, response);
            }
        } catch (Exception e) {
            System.out.println(e);
            request.setAttribute("erro", e);
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
