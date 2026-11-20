package service;

import dao.VendaDAO;
import model.Venda;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;

public class VendaService {

    private final VendaDAO vendaDAO;

    public VendaService() {
        this.vendaDAO = new VendaDAO();
    }

    public void cadastrar(Venda venda) throws SQLException {
        validarVenda(venda);
        vendaDAO.cadastrar(venda);
    }

    public List<Venda> listar() throws SQLException {
        return vendaDAO.listar();
    }

    public Venda buscarPorId(int id) throws SQLException {
        if (id <= 0) {
            throw new IllegalArgumentException("ID da venda inválido!");
        }

        return vendaDAO.buscarPorId(id);
    }

    public void atualizar(Venda venda) throws SQLException {
        validarVenda(venda);

        if (venda.getIdVenda() <= 0) {
            throw new IllegalArgumentException("ID da venda inválido!");
        }

        vendaDAO.atualizar(venda);
    }

    public void excluir(int id) throws SQLException {
        if (id <= 0) {
            throw new IllegalArgumentException("ID da venda inválido!");
        }

        vendaDAO.excluir(id);
    }

    private void validarVenda(Venda venda) {

        if (venda == null) {
            throw new IllegalArgumentException("Venda não informada!");
        }

        if (venda.getIdCliente() <= 0) {
            throw new IllegalArgumentException("Selecione um cliente válido!");
        }

        if (venda.getIdFuncionario() <= 0) {
            throw new IllegalArgumentException("Selecione um funcionário válido!");
        }

        if (venda.getIdVeiculo() <= 0) {
            throw new IllegalArgumentException("Selecione um veículo válido!");
        }

        if (venda.getDataVenda() == null) {
            throw new IllegalArgumentException("Data da venda obrigatória!");
        }

        if (venda.getPreco() == null
                || venda.getPreco().compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("O preço deve ser maior que zero!");
        }

        if (venda.getPagamento() == null
                || venda.getPagamento().trim().isEmpty()) {
            throw new IllegalArgumentException("Forma de pagamento obrigatória!");
        }

        String status = venda.getVenda();

        if (status == null
                || !(status.equals("concluida")
                || status.equals("pendente")
                || status.equals("cancelada"))) {
            throw new IllegalArgumentException("Status da venda inválido!");
        }
    }
}
