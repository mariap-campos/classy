/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controle;

import dao.AlunoDAO;
import dao.ClassyDAO;
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
import modelo.Prova;

/**
 *
 * @author Maria Paula
 */
@WebServlet(name = "ControleFiltros", urlPatterns = {"/ControleFiltros"})
public class ControleFiltros extends HttpServlet {

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
            if ("Todos".equals(acao)) {
                    int classy_token = Integer.parseInt(request.getParameter("id"));
                    int id_aluno = Integer.parseInt(request.getParameter("id_aluno"));
                    Classy classy = new Classy();
                    classy.setToken(classy_token);
                    
                    Atividade ativ = new Atividade();
                    ativ.setClassy_token(classy_token);
                
                    dao.AtividadeDAO cdao = new dao.AtividadeDAO();
                    ArrayList<Atividade> todosAtividade = new ArrayList<Atividade>();
                    todosAtividade = cdao.consultarTodos(ativ);
                    
                    ClassyDAO dao = new ClassyDAO();
                    Classy classyBuscar = new Classy();
                    classyBuscar = dao.consultarPorId(classy);
                    
                    AlunoDAO aldao = new AlunoDAO();
                    Aluno alunoId = new Aluno();
                    alunoId.setId(id_aluno);

                    Aluno aluno = aldao.consultarPorId(alunoId);
                    
                    request.setAttribute("aluno", aluno);
                    request.setAttribute("atividades", todosAtividade);
                    request.setAttribute("classy", classyBuscar);
                    request.getRequestDispatcher("userAtividade.jsp").forward(request, response);
            }  else if ("10 dias seguintes".equals(acao)) {
                    int classy_token = Integer.parseInt(request.getParameter("id"));
                    int id_aluno = Integer.parseInt(request.getParameter("id_aluno"));
                    Classy classy = new Classy();
                    classy.setToken(classy_token);
                    
                    Atividade ativ = new Atividade();
                    ativ.setClassy_token(classy_token);
                
                    dao.AtividadeDAO cdao = new dao.AtividadeDAO();
                    ArrayList<Atividade> todosAtividade = new ArrayList<Atividade>();
                    todosAtividade = cdao.consultar10(ativ);
                    
                    ClassyDAO dao = new ClassyDAO();
                    Classy classyBuscar = new Classy();
                    classyBuscar = dao.consultarPorId(classy);
                    
                    AlunoDAO aldao = new AlunoDAO();
                    Aluno alunoId = new Aluno();
                    alunoId.setId(id_aluno);

                    Aluno aluno = aldao.consultarPorId(alunoId);
                    
                    request.setAttribute("aluno", aluno);
                    request.setAttribute("atividades", todosAtividade);
                    request.setAttribute("classy", classyBuscar);
                    request.getRequestDispatcher("userAtividade.jsp").forward(request, response);
            } else if ("Atrasadas".equals(acao)) {
                    int classy_token = Integer.parseInt(request.getParameter("id"));
                    int id_aluno = Integer.parseInt(request.getParameter("id_aluno"));
                    Classy classy = new Classy();
                    classy.setToken(classy_token);
                    
                    Atividade ativ = new Atividade();
                    ativ.setClassy_token(classy_token);
                
                    dao.AtividadeDAO cdao = new dao.AtividadeDAO();
                    ArrayList<Atividade> todosAtividade = new ArrayList<Atividade>();
                    todosAtividade = cdao.consultarAtrasadas(ativ);
                    
                    ClassyDAO dao = new ClassyDAO();
                    Classy classyBuscar = new Classy();
                    classyBuscar = dao.consultarPorId(classy);
                    
                    AlunoDAO aldao = new AlunoDAO();
                    Aluno alunoId = new Aluno();
                    alunoId.setId(id_aluno);

                    Aluno aluno = aldao.consultarPorId(alunoId);
                    
                    request.setAttribute("aluno", aluno);
                    request.setAttribute("atividades", todosAtividade);
                    request.setAttribute("classy", classyBuscar);
                    request.getRequestDispatcher("userAtividade.jsp").forward(request, response);
            } else if ("Filtrar".equals(acao)) {
                    int classy_token = Integer.parseInt(request.getParameter("id"));
                    int id_aluno = Integer.parseInt(request.getParameter("id_aluno"));
                    
                    Classy classy = new Classy();
                    classy.setToken(classy_token);
                    
                    Atividade ativ = new Atividade();
                    ativ.setClassy_token(classy_token);
                    ativ.setMateria(request.getParameter("txtMateria"));
                
                    dao.AtividadeDAO cdao = new dao.AtividadeDAO();
                    ArrayList<Atividade> todosAtividade = new ArrayList<Atividade>();
                    todosAtividade = cdao.consultarPorMateria(ativ);
                    
                    ClassyDAO dao = new ClassyDAO();
                    Classy classyBuscar = new Classy();
                    classyBuscar = dao.consultarPorId(classy);
                    AlunoDAO aldao = new AlunoDAO();
                    Aluno alunoId = new Aluno();
                    alunoId.setId(id_aluno);

                    Aluno aluno = aldao.consultarPorId(alunoId);
                    
                    request.setAttribute("aluno", aluno);
                    request.setAttribute("atividades", todosAtividade);
                    request.setAttribute("classy", classyBuscar);
                    request.getRequestDispatcher("userAtividade.jsp").forward(request, response);
            }  else if ("Filtre".equals(acao)) {
                    int classy_token = Integer.parseInt(request.getParameter("id"));
                    int id_aluno = Integer.parseInt(request.getParameter("id_aluno"));
                    
                    Classy classy = new Classy();
                    classy.setToken(classy_token);
                    
                    Prova prova = new Prova();
                    prova.setToken_classy(classy_token);
                    prova.setMateria(request.getParameter("txtMateria"));
                
                    ProvaDAO cdao = new ProvaDAO();
                    ArrayList<Prova> todosProva = new ArrayList<Prova>();
                    todosProva = cdao.consultarPorMateria(prova);
                    
                    ClassyDAO dao = new ClassyDAO();
                    Classy classyBuscar = new Classy();
                    classyBuscar = dao.consultarPorId(classy);
                    AlunoDAO aldao = new AlunoDAO();
                    Aluno alunoId = new Aluno();
                    alunoId.setId(id_aluno);

                    Aluno aluno = aldao.consultarPorId(alunoId);
                    
                    request.setAttribute("aluno", aluno);
                    request.setAttribute("provas", todosProva);
                    request.setAttribute("classy", classyBuscar);
                    request.getRequestDispatcher("userProva.jsp").forward(request, response);
            } else if ("Todas as Provas".equals(acao)) {
                    int classy_token = Integer.parseInt(request.getParameter("id"));
                    int id_aluno = Integer.parseInt(request.getParameter("id_aluno"));
                    Classy classy = new Classy();
                    classy.setToken(classy_token);
                    
                    Prova prova = new Prova();
                    prova.setToken_classy(classy_token);
                
                    ProvaDAO cdao = new ProvaDAO();
                    ArrayList<Prova> todosProva = new ArrayList<Prova>();
                    todosProva = cdao.consultarTodos(prova);
                    
                    ClassyDAO dao = new ClassyDAO();
                    Classy classyBuscar = new Classy();
                    classyBuscar = dao.consultarPorId(classy);
                    AlunoDAO aldao = new AlunoDAO();
                    Aluno alunoId = new Aluno();
                    alunoId.setId(id_aluno);

                    Aluno aluno = aldao.consultarPorId(alunoId);
                    
                    request.setAttribute("aluno", aluno);
                    request.setAttribute("provas", todosProva);
                    request.setAttribute("classy", classyBuscar);
                    request.getRequestDispatcher("userProva.jsp").forward(request, response);
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
