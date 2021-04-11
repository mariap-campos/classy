/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controle;

import dao.AlunoDAO;
import dao.ProvaDAO;
import dao.ClassyDAO;
import dao.ForumDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelo.Aluno;
import modelo.Prova;
import modelo.Classy;
import modelo.Forum;
import util.ConverteDate;
import util.SeparateSubject;

/**
 *
 * @author Maria Paula
 */
@WebServlet(name = "ControleAluno", urlPatterns = {"/ControleAluno"})
public class ControleAluno extends HttpServlet {

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
            if ("Cadastrar".equals(acao)) {
                int classy_token = Integer.parseInt(request.getParameter("id"));
                Aluno aluno = new Aluno();
                aluno.setNome(request.getParameter("txtNomeAluno"));
                aluno.setRgm(request.getParameter("txtRGM"));
                aluno.setClassy_token(classy_token);
               
                AlunoDAO adao = new AlunoDAO();
                adao.cadastrar(aluno);
                
                request.setAttribute("title", "Aluno adicionado com sucesso!");
                request.setAttribute("mensagem", "Clique na aba 'Alunos' para ver todos os alunos dessa turma");
                request.setAttribute("tipo", "Listar");
                request.getRequestDispatcher("success.jsp").forward(request, response);
            } else if ("Apagar".equals(acao)) {
                Aluno aluno = new Aluno();
                int id = Integer.parseInt(request.getParameter("id"));
                int classy_id = Integer.parseInt(request.getParameter("classy_id"));
                
                aluno.setId(id);
                AlunoDAO adao = new AlunoDAO();
                adao.apagar(aluno);     
                
                request.setAttribute("title", "Aluno apagado com sucesso!");
                request.setAttribute("mensagem", "Voltando a lista");
                request.setAttribute("classy", classy_id);
                request.setAttribute("tipo", "Aluno");
                request.getRequestDispatcher("success.jsp").forward(request, response);
            } else if ("abrirForm".equals(acao)) {
                int id = Integer.parseInt(request.getParameter("id"));
          
                Aluno aluno = new Aluno();
                aluno.setId(id);
                
                AlunoDAO cdao = new AlunoDAO();
                Aluno alunoBuscar = new Aluno();
                alunoBuscar = cdao.consultarPorId(aluno);
                   
                request.setAttribute("aluno", alunoBuscar);
                request.getRequestDispatcher("atualizarAluno.jsp").forward(request, response);
            } else if ("Atualizar".equals(acao)) {
                Aluno aluno = new Aluno();
                int id = Integer.parseInt(request.getParameter("id"));
                int id_classy = Integer.parseInt(request.getParameter("id_classy"));
                
                aluno.setId(id);
                aluno.setNome(request.getParameter("txtNomeAluno"));
                aluno.setRgm(request.getParameter("txtRgm"));
                aluno.setSituacao(request.getParameter("txtSituacao"));
               
                
                AlunoDAO cdao = new AlunoDAO();
                cdao.atualizar(aluno);
                
                request.setAttribute("title", "Informações do aluno atualizadas!");
                request.setAttribute("mensagem", "Voltando a página de listagem.");
                request.setAttribute("classy", id_classy);
                request.setAttribute("tipo", "Aluno");
                request.getRequestDispatcher("success.jsp").forward(request, response);
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
