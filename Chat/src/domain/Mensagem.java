/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

/**
 *
 * @author Luiz
 */
public class Mensagem {
    private Long id;
    private Usuario autor;
    private Sala sala;
    private String conteudo;
    private UsuarioSala usuarioSala;

    public UsuarioSala getUsuarioSala() {
        return usuarioSala;
    }

    public void setUsuarioSala(UsuarioSala usuarioSala) {
        this.usuarioSala = usuarioSala;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Usuario getAutor() {
        return autor;
    }

    public void setAutor(Usuario autor) {
        this.autor = autor;
    }

    public Sala getSala() {
        return sala;
    }

    public void setSala(Sala sala) {
        this.sala = sala;
    }

    public String getConteudo() {
        return conteudo;
    }

    public void setConteudo(String conteudo) {
        this.conteudo = conteudo;
    }
}
