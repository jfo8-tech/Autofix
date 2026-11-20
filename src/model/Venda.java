package model;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Venda {

    private int idVenda;
    private int idCliente;
    private int idFuncionario;
    private int idVeiculo;
    private LocalDate dataVenda;
    private BigDecimal preco;
    private String pagamento;
    private String venda;

    // CONSTRUTOR VAZIO
    public Venda() {
    }

    // CONSTRUTOR COMPLETO
    public Venda(int idVenda, int idCliente, int idFuncionario,
            int idVeiculo, LocalDate dataVenda, BigDecimal preco,
            String pagamento, String venda) {

        this.idVenda = idVenda;
        this.idCliente = idCliente;
        this.idFuncionario = idFuncionario;
        this.idVeiculo = idVeiculo;
        this.dataVenda = dataVenda;
        this.preco = preco;
        this.pagamento = pagamento;
        this.venda = venda;
    }

    // GETTERS E SETTERS

    public int getIdVenda() {
        return idVenda;
    }

    public void setIdVenda(int idVenda) {
        this.idVenda = idVenda;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public int getIdFuncionario() {
        return idFuncionario;
    }

    public void setIdFuncionario(int idFuncionario) {
        this.idFuncionario = idFuncionario;
    }

    public int getIdVeiculo() {
        return idVeiculo;
    }

    public void setIdVeiculo(int idVeiculo) {
        this.idVeiculo = idVeiculo;
    }

    public LocalDate getDataVenda() {
        return dataVenda;
    }

    public void setDataVenda(LocalDate dataVenda) {
        this.dataVenda = dataVenda;
    }

    public BigDecimal getPreco() {
        return preco;
    }

    public void setPreco(BigDecimal preco) {
        this.preco = preco;
    }

    public String getPagamento() {
        return pagamento;
    }

    public void setPagamento(String pagamento) {
        this.pagamento = pagamento;
    }

    public String getVenda() {
        return venda;
    }

    public void setVenda(String venda) {
        this.venda = venda;
    }
}
