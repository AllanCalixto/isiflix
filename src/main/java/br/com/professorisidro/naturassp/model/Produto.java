package br.com.professorisidro.naturassp.model;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "tbl_produto")
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_produto;

    @Column(name = "nome_produto", length = 100, nullable = false)
    private String nome;

    @Column(name = "detalhe_produto", length = 500)
    private String detalhe;

    @Column(name = "link_foto", length = 255, nullable = false)
    private String linkFoto;

    @Column(name = "preco_produto", nullable = false)
    private double preco;

    @Column(name = "disponivel", nullable = false)
    private int disponivel;

    @ManyToOne
    @JoinColumn(name = "id_categoria")
    private Categoria categoria;


    public Integer getId() {
        return id_produto;
    }

    public void setId(Integer id) {
        this.id_produto = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDetalhe() {
        return detalhe;
    }

    public void setDetalhe(String detalhe) {
        this.detalhe = detalhe;
    }

    public String getLinkFoto() {
        return linkFoto;
    }

    public void setLinkFoto(String linkFoto) {
        this.linkFoto = linkFoto;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public int getDisponivel() {
        return disponivel;
    }

    public void setDisponivel(int disponivel) {
        this.disponivel = disponivel;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Produto produto = (Produto) o;
        return Objects.equals(id_produto, produto.id_produto);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id_produto);
    }
}
