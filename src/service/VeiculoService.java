package service;

import dao.VeiculoDAO;
import model.Veiculo;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;

public class VeiculoService {

    private final VeiculoDAO veiculoDAO;

    public VeiculoService() {
        this.veiculoDAO = new VeiculoDAO();
    }

    // Cadastrar veículo
    public void cadastrar(Veiculo veiculo) throws SQLException {

        validarVeiculo(veiculo);

        if (veiculo.getPlaca() != null
                && !veiculo.getPlaca().trim().isEmpty()
                && veiculoDAO.buscarPorPlaca(veiculo.getPlaca()) != null) {

            throw new IllegalArgumentException(
                    "Esta placa já está cadastrada!"
            );
        }

        veiculoDAO.cadastrar(veiculo);
    }

    // Listar veículos
    public List<Veiculo> listar() throws SQLException {
        return veiculoDAO.listar();
    }

    // Buscar veículo por ID
    public Veiculo buscarPorId(int id) throws SQLException {

        if (id <= 0) {
            throw new IllegalArgumentException("ID inválido!");
        }

        return veiculoDAO.buscarPorId(id);
    }

    // Buscar veículo por placa
    public Veiculo buscarPorPlaca(String placa) throws SQLException {

        if (placa == null || placa.trim().isEmpty()) {
            throw new IllegalArgumentException("Placa obrigatória!");
        }

        return veiculoDAO.buscarPorPlaca(placa);
    }

    // Atualizar veículo
    public void atualizar(Veiculo veiculo) throws SQLException {

        validarVeiculo(veiculo);

        if (veiculo.getIdVeiculo() <= 0) {
            throw new IllegalArgumentException("ID do veículo inválido!");
        }

        if (veiculo.getPlaca() != null
                && !veiculo.getPlaca().trim().isEmpty()) {

            Veiculo existente =
                    veiculoDAO.buscarPorPlaca(veiculo.getPlaca());

            if (existente != null
                    && existente.getIdVeiculo() != veiculo.getIdVeiculo()) {

                throw new IllegalArgumentException(
                        "Esta placa pertence a outro veículo!"
                );
            }
        }

        veiculoDAO.atualizar(veiculo);
    }

    // Excluir veículo
    public void excluir(int id) throws SQLException {

        if (id <= 0) {
            throw new IllegalArgumentException("ID inválido!");
        }

        veiculoDAO.excluir(id);
    }

    // Validar dados do veículo
    private void validarVeiculo(Veiculo veiculo) {

        if (veiculo == null) {
            throw new IllegalArgumentException("Veículo não informado!");
        }

        if (veiculo.getMarca() == null
                || veiculo.getMarca().trim().isEmpty()) {
            throw new IllegalArgumentException("Marca obrigatória!");
        }

        if (veiculo.getModelo() == null
                || veiculo.getModelo().trim().isEmpty()) {
            throw new IllegalArgumentException("Modelo obrigatório!");
        }

        if (veiculo.getAnoFabricacao() <= 0) {
            throw new IllegalArgumentException("Ano de fabricação inválido!");
        }

        if (veiculo.getQuilometragem() < 0) {
            throw new IllegalArgumentException(
                    "Quilometragem não pode ser negativa!"
            );
        }

        if (veiculo.getPreco() == null
                || veiculo.getPreco().compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException(
                    "O preço deve ser maior que zero!"
            );
        }

        if (veiculo.getCategoria() == null
                || veiculo.getCategoria().trim().isEmpty()) {
            throw new IllegalArgumentException("Categoria obrigatória!");
        }

        if (veiculo.getCombustivel() == null
                || veiculo.getCombustivel().trim().isEmpty()) {
            throw new IllegalArgumentException("Combustível obrigatório!");
        }

        if (veiculo.getDisponibilidade() == null
                || veiculo.getDisponibilidade().trim().isEmpty()) {
            throw new IllegalArgumentException(
                    "Disponibilidade obrigatória!"
            );
        }
    }
}
