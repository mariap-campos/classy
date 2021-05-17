/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controle;

import dao.ClassyDAO;
import dao.ProvaDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelo.Classy;
import modelo.Prova;
import util.ConverteDate;
import util.SeparateSubject;

/**
 *
 * @author Maria Paula
 */
@WebServlet(name = "ControleProva", urlPatterns = {"/ControleProva"})
public class ControleProva extends HttpServlet {

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
                
                Classy classy = new Classy();
                classy.setToken(classy_token);
                
                ClassyDAO cdao = new ClassyDAO();
                Classy classyBuscar = cdao.consultarPorId(classy);

                Prova prova = new Prova();
                prova.setNome(request.getParameter("txtNomeProva"));
                prova.setMateria(request.getParameter("txtMateria"));
                prova.setToken_classy(classy_token);
                
                ConverteDate conversor = new ConverteDate();
                Date data1 = conversor.getDate(request.getParameter("txtDataProva"));
               
                prova.setData(data1);
                ProvaDAO adao = new ProvaDAO();
                
                
                if (data1.after(classyBuscar.getData_final())) {
                    request.setAttribute("title", "Data da prova inválida!");
                    request.setAttribute("mensagem", "Certifique-se que a data da prova não ultrapassa o semestre escolar.");
                    request.setAttribute("tipo", "Listar");
                    request.getRequestDispatcher("error.jsp").forward(request, response);
                } else {
                    
                    try {
                    adao.cadastrar(prova);              
                    request.setAttribute("title", "Prova marcada com sucesso!");
                    request.setAttribute("mensagem", "Clique na aba 'Provas' para ver todas as provas marcadas.");
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
                
            } else if ("Apagar".equals(acao)) {
                Prova prova = new Prova();
                int id = Integer.parseInt(request.getParameter("id"));
                int id_classy = Integer.parseInt(request.getParameter("id_classy"));
                
                prova.setId(id);
                ProvaDAO adao = new ProvaDAO();
                
                try {
                adao.apagar(prova);     
                request.setAttribute("title", "Prova apagada com sucesso!");
                request.setAttribute("mensagem", "Voltando a página de listagem de suas provas.");
                request.setAttribute("classy", id_classy);
                request.setAttribute("tipo", "Prova");
                request.getRequestDispatcher("success.jsp").forward(request, response);
                } catch (Exception e){
                    System.out.println(e);
                    request.setAttribute("title", "Problemas para excluir o cadastro no banco de dados");
                    request.setAttribute("mensagem", "Tente novamente mais tarde.");
                    request.setAttribute("tipo", "Listar");
                    request.getRequestDispatcher("error.jsp").forward(request, response);
                }
            } else if ("abrirForm".equals(acao)) {
                int id = Integer.parseInt(request.getParameter("id"));
          
                Prova prova = new Prova();
                prova.setId(id);
                
                dao.ProvaDAO cdao = new dao.ProvaDAO();
                Prova provaBuscar = new Prova();
                provaBuscar = cdao.consultarPorId(prova);
                
                   
                request.setAttribute("prova", provaBuscar);
                request.getRequestDispatcher("atualizarProva.jsp").forward(request, response);
            } else if ("Atualizar".equals(acao)) {
                Prova prova = new Prova();
                int id = Integer.parseInt(request.getParameter("id"));
                int id_classy = Integer.parseInt(request.getParameter("id_classy"));
                
                prova.setId(id);
                prova.setNome(request.getParameter("txtNomeProva"));
                prova.setMateria(request.getParameter("txtMateria"));
                
                ConverteDate conversor = new ConverteDate();
                Date data1 = conversor.getDate(request.getParameter("txtDataProva"));
               
                prova.setData(data1);
                ProvaDAO cdao = new ProvaDAO();
                
                try {
                cdao.atualizar(prova);
                request.setAttribute("title", "Informações da prova atualizadas!");
                request.setAttribute("mensagem", "Voltando a página de listagem.");
                request.setAttribute("classy", id_classy);
                request.setAttribute("tipo", "Prova");
                request.getRequestDispatcher("success.jsp").forward(request, response);
                
                } catch (Exception e){
                    System.out.println(e);
                    request.setAttribute("title", "Problemas para atualizar o cadastro no banco de dados");
                    request.setAttribute("mensagem", "Tente novamente mais tarde.");
                    request.setAttribute("tipo", "Listar");
                    request.getRequestDispatcher("error.jsp").forward(request, response);
                }
            }  else if ("Filtrar".equals(acao)) {
                    int classy_token = Integer.parseInt(request.getParameter("id"));
                    
                    Prova prova = new Prova();
                    prova.setToken_classy(classy_token);
                    prova.setMateria(request.getParameter("txtMateria"));
                
                    ProvaDAO cdao = new ProvaDAO();
                    ArrayList<Prova> todosProva = new ArrayList<Prova>();
                    todosProva = cdao.consultarPorMateria(prova);
                    
                    
                    request.setAttribute("provas", todosProva);
                    request.getRequestDispatcher("adminProva.jsp").forward(request, response);
            } else if ("Todos".equals(acao)) {
                    int classy_token = Integer.parseInt(request.getParameter("id"));
                    
                    Prova prova = new Prova();
                    prova.setToken_classy(classy_token);
                
                    ProvaDAO cdao = new ProvaDAO();
                    ArrayList<Prova> todosProva = new ArrayList<Prova>();
                    todosProva = cdao.consultarTodos(prova);
                    
                    request.setAttribute("provas", todosProva);
                    request.getRequestDispatcher("adminProva.jsp").forward(request, response);
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
        try {
            processRequest(request, response);
        } catch (ParseException ex) {
            Logger.getLogger(ControleProva.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(ControleProva.class.getName()).log(Level.SEVERE, null, ex);
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
