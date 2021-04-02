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
public class Classy {
    private String nome, nome_instituicao, materias;
    private int token, id_admin;
    private Date data_inicio, data_final;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getNome_instituicao() {
        return nome_instituicao;
    }

    public void setNome_instituicao(String nome_instituicao) {
        this.nome_instituicao = nome_instituicao;
    }

    public Date getData_inicio() {
        return data_inicio;
    }

    public void setData_inicio(Date data_inicio) {
        this.data_inicio = data_inicio;
    }

    public Date getData_final() {
        return data_final;
    }

    public void setData_final(Date data_final) {
        this.data_final = data_final;
    }

    public String getMaterias() {
        return materias;
    }

    public void setMaterias(String materias) {
        this.materias = materias;
    }

    public int getToken() {
        return token;
    }

    public void setToken(int token) {
        this.token = token;
    }

    public int getId_admin() {
        return id_admin;
    }

    public void setId_admin(int id_admin) {
        this.id_admin = id_admin;
    }
    
    
    
    
    
}
