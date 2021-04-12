/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controle;

import dao.AtividadeDAO;
import dao.ClassyDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.text.ParseException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelo.Atividade;
import modelo.Classy;
import util.ConverteDate;
import util.SeparateSubject;

/**
 *
 * @author Maria Paula
 */
@WebServlet(name = "ControleAtividade", urlPatterns = {"/ControleAtividade"})
public class ControleAtividade extends HttpServlet {

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
            throws ServletException, IOException, ParseException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            String acao = request.getParameter("acao");
            if ("Cadastrar".equals(acao)) {
                int classy_token = Integer.parseInt(request.getParameter("id"));
                
                modelo.Atividade ativ = new modelo.Atividade();
                ativ.setNome(request.getParameter("txtNome"));
                ativ.setDescricao(request.getParameter("txtDesc"));
                ativ.setProfessor(request.getParameter("txtProfessor"));
                ativ.setMateria(request.getParameter("txtMateria"));
                ativ.setClassy_token(classy_token);
                
                ConverteDate conversor = new ConverteDate();
                Date data1 = conversor.getDate(request.getParameter("txtData"));
               
                ativ.setData_entrega(data1);
                
                Classy classy = new Classy();
                classy.setToken(classy_token);
                
                ClassyDAO cdao = new ClassyDAO();
                Classy classyBuscar = cdao.consultarPorId(classy);
                
                dao.AtividadeDAO adao = new dao.AtividadeDAO();
                
                if (data1.after(classyBuscar.getData_final())) {
                    request.setAttribute("title", "Data de entrega inválida!");
                    request.setAttribute("mensagem", "Certifique-se que a entrega da atividade não ultrapassa o semestre escolar.");
                    request.setAttribute("tipo", "Listar");
                    request.getRequestDispatcher("error.jsp").forward(request, response);
                } else {
                    
                    try {
                    adao.cadastrar(ativ);               
                    request.setAttribute("title", "Atividade criada com sucesso!");
                    request.setAttribute("mensagem", "Clique na aba 'Atividades' para ver todas as atividades.");
                    request.setAttribute("tipo", "Listar");
                    request.getRequestDispatcher("success.jsp").forward(request, response);
                    } catch (Exception e){
                    System.out.println(e);
                    request.setAttribute("title", "Problemas para realizar o cadastro no banco de dados");
                    request.setAttribute("mensagem", "Tente novamente mais tarde.");
                    request.setAttribute("tipo", "Listar");
                    request.getRequestDispatcher("error.jsp").forward(request, response);
                    }
                }
                
            } else if ("Todos".equals(acao)) {
                    int classy_token = Integer.parseInt(request.getParameter("id"));
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
                    
                    request.setAttribute("atividades", todosAtividade);
                    request.setAttribute("classy", classyBuscar);
                    request.getRequestDispatcher("adminAtividade.jsp").forward(request, response);
            } else if ("10 dias seguintes".equals(acao)) {
                    int classy_token = Integer.parseInt(request.getParameter("id"));
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
                    
                    request.setAttribute("atividades", todosAtividade);
                    request.setAttribute("classy", classyBuscar);
                    request.getRequestDispatcher("adminAtividade.jsp").forward(request, response);
            } else if ("Atrasadas".equals(acao)) {
                    int classy_token = Integer.parseInt(request.getParameter("id"));
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
                    
                    request.setAttribute("atividades", todosAtividade);
                    request.setAttribute("classy", classyBuscar);
                    request.getRequestDispatcher("adminAtividade.jsp").forward(request, response);
            } else if ("Filtrar".equals(acao)) {
                    int classy_token = Integer.parseInt(request.getParameter("id"));
                    
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
                    
                    request.setAttribute("atividades", todosAtividade);
                    request.setAttribute("classy", classyBuscar);
                    request.getRequestDispatcher("adminAtividade.jsp").forward(request, response);
            } else if ("abrirForm".equals(acao)) {
                int id = Integer.parseInt(request.getParameter("id"));
          
                Atividade ativ = new Atividade();
                ativ.setId(id);
                
                dao.AtividadeDAO cdao = new dao.AtividadeDAO();
                Atividade ativBuscar = new Atividade();
                ativBuscar = cdao.consultarPorId(ativ);

                
                Classy classy = new Classy();    
                classy.setToken(ativBuscar.getClassy_token());
                ClassyDAO cldao = new ClassyDAO();
                Classy classyBuscar = cldao.consultarPorId(classy);
                
                SeparateSubject separator = new SeparateSubject();    
                String[] materias = separator.splitSubjects(classyBuscar.getMaterias());
                   
                request.setAttribute("atividade", ativBuscar);
                request.setAttribute("materias", materias);
                request.getRequestDispatcher("atualizarAtividade.jsp").forward(request, response);
            } else if ("Atualizar".equals(acao)) {
                Atividade ativ = new Atividade();
                int id = Integer.parseInt(request.getParameter("id"));
                int id_classy = Integer.parseInt(request.getParameter("id_classy"));
                
                ativ.setId(id);
                ativ.setNome(request.getParameter("txtNome"));
                ativ.setDescricao(request.getParameter("txtDesc"));
                ativ.setProfessor(request.getParameter("txtProfessor"));
                ativ.setMateria(request.getParameter("txtMateria"));
                
                ConverteDate conversor = new ConverteDate();
                Date data1 = conversor.getDate(request.getParameter("txtData"));
               
                ativ.setData_entrega(data1);
                
                AtividadeDAO cdao = new AtividadeDAO();
                
                try {
                cdao.atualizar(ativ);     
                request.setAttribute("title", "Informações da Atividade atualizadas!");
                request.setAttribute("mensagem", "Voltando a página de listagem.");
                request.setAttribute("classy", id_classy);
                request.setAttribute("tipo", "Atividade");
                request.getRequestDispatcher("success.jsp").forward(request, response);
                
                } catch (Exception e){
                    System.out.println(e);
                    request.setAttribute("title", "Problemas para atualizar o cadastro no banco de dados");
                    request.setAttribute("mensagem", "Tente novamente mais tarde.");
                    request.setAttribute("tipo", "Listar");
                    request.getRequestDispatcher("error.jsp").forward(request, response);
                }
            } else if ("Apagar".equals(acao)) {
                Atividade atividade = new Atividade();
                int id = Integer.parseInt(request.getParameter("id"));
                int id_classy = Integer.parseInt(request.getParameter("id_classy"));
                
                atividade.setId(id);
                AtividadeDAO adao = new AtividadeDAO();
                
                try {
                adao.apagar(atividade);     
                request.setAttribute("title", "Atividade apagada com sucesso!");
                request.setAttribute("mensagem", "Voltando a página de listagem de suas atividades.");
                request.setAttribute("classy", id_classy);
                request.setAttribute("tipo", "Atividade");
                request.getRequestDispatcher("success.jsp").forward(request, response);
                } catch (Exception e){
                    System.out.println(e);
                    request.setAttribute("title", "Problemas para excluir o cadastro no banco de dados");
                    request.setAttribute("mensagem", "Tente novamente mais tarde.");
                    request.setAttribute("tipo", "Listar");
                    request.getRequestDispatcher("error.jsp").forward(request, response);
                    }
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
        try {
            processRequest(request, response);
        } catch (ParseException ex) {
            Logger.getLogger(ControleAtividade.class.getName()).log(Level.SEVERE, null, ex);
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
        try {
            processRequest(request, response);
        } catch (ParseException ex) {
            Logger.getLogger(ControleAtividade.class.getName()).log(Level.SEVERE, null, ex);
        }
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
