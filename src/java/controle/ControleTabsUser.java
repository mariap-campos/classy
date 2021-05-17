/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controle;

import dao.AlunoDAO;
import dao.AtividadeDAO;
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
import modelo.Aluno;
import modelo.Atividade;
import modelo.Classy;
import modelo.Forum;
import modelo.Prova;

/**
 *
 * @author Maria Paula
 */
@WebServlet(name = "ControleTabsUser", urlPatterns = {"/ControleTabsUser"})
public class ControleTabsUser extends HttpServlet {

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
                int id = Integer.parseInt(request.getParameter("id"));
                int id_aluno = Integer.parseInt(request.getParameter("id_aluno"));
                
                
                Atividade atividade = new Atividade();
                atividade.setClassy_token(id);
                AtividadeDAO atdao = new AtividadeDAO();
                ArrayList<Atividade> ativBuscar = atdao.consultarHome(atividade);
                
                Prova prova = new Prova();
                prova.setToken_classy(id);
                ProvaDAO pdao = new ProvaDAO();
                ArrayList<Prova> provaBuscar = pdao.consultarHome(prova);
                
                
                AlunoDAO adao = new AlunoDAO();
                Aluno alunoId = new Aluno();
                alunoId.setId(id_aluno);
                
                Aluno aluno = adao.consultarPorId(alunoId);
                
                request.setAttribute("aluno", aluno);
                request.setAttribute("atividades", ativBuscar);
                request.setAttribute("provas", provaBuscar);
                request.getRequestDispatcher("userHome.jsp").forward(request, response);
            } else if ("Atividades".equals(acao)) {
                    int classy_token = Integer.parseInt(request.getParameter("id"));
                    int id_aluno = Integer.parseInt(request.getParameter("id_aluno"));
                    
                    Atividade ativ = new Atividade();
                    ativ.setClassy_token(classy_token);
                
                    dao.AtividadeDAO adao = new dao.AtividadeDAO();
                    ArrayList<Atividade> todosAtividade = new ArrayList<Atividade>();
                    todosAtividade = adao.consultarTodos(ativ);
                    
                    AlunoDAO aldao = new AlunoDAO();
                    Aluno alunoId = new Aluno();
                    alunoId.setId(id_aluno);

                    Aluno aluno = aldao.consultarPorId(alunoId);
                    
                    request.setAttribute("atividades", todosAtividade);
                    request.setAttribute("aluno", aluno);
                    request.getRequestDispatcher("userAtividade.jsp").forward(request, response);
            }  else if ("Provas".equals(acao)) {
                int id = Integer.parseInt(request.getParameter("id"));
                int id_aluno = Integer.parseInt(request.getParameter("id_aluno"));
                
                Prova prova = new Prova();
                prova.setToken_classy(id);
                
                ProvaDAO fdao = new ProvaDAO();
                ArrayList<Prova> todosProva = new ArrayList<Prova>();
                todosProva = fdao.consultarTodos(prova);
                
                AlunoDAO aldao = new AlunoDAO();
                Aluno alunoId = new Aluno();
                alunoId.setId(id_aluno);
                Aluno aluno = aldao.consultarPorId(alunoId);

                request.setAttribute("provas", todosProva);
                request.setAttribute("aluno", aluno);
                request.getRequestDispatcher("userProva.jsp").forward(request, response);
            } else if ("Alunos".equals(acao)) {
                int id = Integer.parseInt(request.getParameter("id"));
                int id_aluno = Integer.parseInt(request.getParameter("id_aluno"));
                
                Aluno forum = new Aluno();
                forum.setClassy_token(id);
                
                AlunoDAO fdao = new AlunoDAO();
                ArrayList<Aluno> todosAlunos = new ArrayList<Aluno>();
                todosAlunos = fdao.consultarTodos(forum);
                
                Aluno alunoId = new Aluno();
                alunoId.setId(id_aluno);
                Aluno aluno = fdao.consultarPorId(alunoId);
                
                request.setAttribute("alunos", todosAlunos);
                request.setAttribute("aluno", aluno);
                request.getRequestDispatcher("userAlunos.jsp").forward(request, response);
            } else if ("Forum".equals(acao)) {
                int id = Integer.parseInt(request.getParameter("id"));
                int id_aluno = Integer.parseInt(request.getParameter("id_aluno"));
                
                
                Forum forum = new Forum();
                forum.setClassy_token(id);
                
                ForumDAO fdao = new ForumDAO();
                ArrayList<Forum> todosPost = new ArrayList<Forum>();
                todosPost = fdao.consultarTodos(forum);
                
                AlunoDAO aldao = new AlunoDAO();
                Aluno alunoId = new Aluno();
                alunoId.setId(id_aluno);
                Aluno aluno = aldao.consultarPorId(alunoId);


                request.setAttribute("posts", todosPost);
                request.setAttribute("aluno", aluno);
                request.getRequestDispatcher("userForum.jsp").forward(request, response);
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
