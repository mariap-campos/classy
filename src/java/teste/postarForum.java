/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package teste;

import dao.ForumDAO;
import modelo.Forum;

/**
 *
 * @author Maria Paula
 */
public class postarForum {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
                Forum forum = new Forum();
                forum.setPostagem("postagem");
                forum.setAssunto("teste");
                forum.setUser_id(22);
                forum.setClassy_token(9);
                
                ForumDAO fdao = new ForumDAO();
                fdao.postar(forum);
    }
    
}
