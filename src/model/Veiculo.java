package model;

import java.math.BigDecimal;

public class Veiculo {

    private int idVeiculo;
    private String marca;
    private String modelo;
    private int anoFabricacao;
    private String cor;
    private String placa;
    private int quilometragem;
    private BigDecimal preco;
    private String categoria;
    private String combustivel;
    private String disponibilidade;

    public Veiculo() {
    }

    public Veiculo(int idVeiculo, String marca, String modelo,
            int anoFabricacao, String cor, String placa,
            int quilometragem, BigDecimal preco,
            String categoria, String combustivel,
            String disponibilidade) {

        this.idVeiculo = idVeiculo;
        this.marca = marca;
        this.modelo = modelo;
        this.anoFabricacao = anoFabricacao;
        this.cor = cor;
        this.placa = placa;
        this.quilometragem = quilometragem;
        this.preco = preco;
        this.categoria = categoria;
        this.combustivel = combustivel;
        this.disponibilidade = disponibilidade;
    }

    public int getIdVeiculo() {
        return idVeiculo;
    }

    public void setIdVeiculo(int idVeiculo) {
        this.idVeiculo = idVeiculo;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public int getAnoFabricacao() {
        return anoFabricacao;
    }

    public void setAnoFabricacao(int anoFabricacao) {
        this.anoFabricacao = anoFabricacao;
    }

    public String getCor() {
        return cor;
    }

    public void setCor(String cor) {
        this.cor = cor;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public int getQuilometragem() {
        return quilometragem;
    }

    public void setQuilometragem(int quilometragem) {
        this.quilometragem = quilometragem;
    }

    public BigDecimal getPreco() {
        return preco;
    }

    public void setPreco(BigDecimal preco) {
        this.preco = preco;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getCombustivel() {
        return combustivel;
    }

    public void setCombustivel(String combustivel) {
        this.combustivel = combustivel;
    }

    public String getDisponibilidade() {
        return disponibilidade;
    }

    public void setDisponibilidade(String disponibilidade) {
        this.disponibilidade = disponibilidade;
    }
}
