/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

/**
 *
 * @author Maria Paula
 */
public class Aluno {
    private int id, classy_token;
    private String nome, rgm, situacao;

    public String getSituacao() {
        return situacao;
    }

    public void setSituacao(String situacao) {
        this.situacao = situacao;
    }
    
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getClassy_token() {
        return classy_token;
    }

    public void setClassy_token(int classy_token) {
        this.classy_token = classy_token;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getRgm() {
        return rgm;
    }

    public void setRgm(String rgm) {
        this.rgm = rgm;
    }
    
    
}
