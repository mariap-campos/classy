/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.sql.Date;

/**
 *
 * @author Maria Paula
 */
public class Forum {
    private  String postagem, assunto, nome, user_nome, imagem;
    private Date data_postagem;
    private int user_id, id, classy_token;

    public String getImagem() {
        return imagem;
    }

    public void setImagem(String imagem) {
        this.imagem = imagem;
    }
    
    public String getUser_nome() {
        return user_nome;
    }

    public void setUser_nome(String user_nome) {
        this.user_nome = user_nome;
    }
     

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
    
    

    public int getClassy_token() {
        return classy_token;
    }

    public void setClassy_token(int classy_token) {
        this.classy_token = classy_token;
    }
    
    

    public String getPostagem() {
        return postagem;
    }

    public void setPostagem(String postagem) {
        this.postagem = postagem;
    }

    public String getAssunto() {
        return assunto;
    }

    public void setAssunto(String assunto) {
        this.assunto = assunto;
    }

    public Date getData_postagem() {
        return data_postagem;
    }

    public void setData_postagem(Date data_postagem) {
        this.data_postagem = data_postagem;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    
}
