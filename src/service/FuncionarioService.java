package service;

import dao.FuncionarioDAO;
import model.Funcionario;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;

public class FuncionarioService {

    private final FuncionarioDAO funcionarioDAO;

    public FuncionarioService() {
        this.funcionarioDAO = new FuncionarioDAO();
    }

    // Cadastrar funcionário
    public void cadastrar(Funcionario funcionario) throws SQLException {

        validarFuncionario(funcionario);

        if (funcionarioDAO.buscarPorCpf(funcionario.getCpf()) != null) {
            throw new IllegalArgumentException(
                    "Este CPF já está cadastrado!"
            );
        }

        funcionarioDAO.cadastrar(funcionario);
    }

    // Listar funcionários
    public List<Funcionario> listar() throws SQLException {
        return funcionarioDAO.listar();
    }

    // Buscar funcionário por ID
    public Funcionario buscarPorId(int id) throws SQLException {

        if (id <= 0) {
            throw new IllegalArgumentException("ID inválido!");
        }

        return funcionarioDAO.buscarPorId(id);
    }

    // Buscar funcionário por CPF
    public Funcionario buscarPorCpf(String cpf) throws SQLException {

        if (cpf == null || cpf.trim().isEmpty()) {
            throw new IllegalArgumentException("CPF obrigatório!");
        }

        return funcionarioDAO.buscarPorCpf(cpf);
    }

    // Atualizar funcionário
    public void atualizar(Funcionario funcionario) throws SQLException {

        validarFuncionario(funcionario);

        if (funcionario.getIdFuncionario() <= 0) {
            throw new IllegalArgumentException(
                    "ID do funcionário inválido!"
            );
        }

        Funcionario existente =
                funcionarioDAO.buscarPorCpf(funcionario.getCpf());

        if (existente != null
                && existente.getIdFuncionario()
                != funcionario.getIdFuncionario()) {

            throw new IllegalArgumentException(
                    "Este CPF já pertence a outro funcionário!"
            );
        }

        funcionarioDAO.atualizar(funcionario);
    }

    // Excluir funcionário
    public void excluir(int id) throws SQLException {

        if (id <= 0) {
            throw new IllegalArgumentException("ID inválido!");
        }

        funcionarioDAO.excluir(id);
    }

    // Validar dados do funcionário
    private void validarFuncionario(Funcionario funcionario) {

        if (funcionario == null) {
            throw new IllegalArgumentException(
                    "Funcionário não informado!"
            );
        }

        if (funcionario.getNome() == null
                || funcionario.getNome().trim().isEmpty()) {
            throw new IllegalArgumentException("Nome obrigatório!");
        }

        if (funcionario.getCpf() == null
                || funcionario.getCpf().trim().isEmpty()) {
            throw new IllegalArgumentException("CPF obrigatório!");
        }

        if (funcionario.getCargo() == null
                || funcionario.getCargo().trim().isEmpty()) {
            throw new IllegalArgumentException("Cargo obrigatório!");
        }

        if (funcionario.getSalario() == null
                || funcionario.getSalario().compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException(
                    "O salário deve ser maior que zero!"
            );
        }

        if (funcionario.getDataAdmissao() == null) {
            throw new IllegalArgumentException(
                    "Data de admissão obrigatória!"
            );
        }
    }
}
