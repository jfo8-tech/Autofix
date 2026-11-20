package service;

import dao.ClienteDAO;
import model.Cliente;

import java.sql.SQLException;

public class ClienteService {

    private final ClienteDAO clienteDAO;

    public ClienteService() {
        this.clienteDAO = new ClienteDAO();
    }

    // Cadastrar cliente
    public void cadastrar(Cliente cliente) throws SQLException {

        validarCliente(cliente);

        if (clienteDAO.buscarPorCpf(cliente.getCpf()) != null) {
            throw new IllegalArgumentException(
                    "Este CPF já está cadastrado!"
            );
        }

        clienteDAO.cadastrar(cliente);
    }

    // Listar clientes
    public java.util.List<Cliente> listar() throws SQLException {
        return clienteDAO.listar();
    }

    // Buscar cliente por ID
    public Cliente buscarPorId(int id) throws SQLException {

        if (id <= 0) {
            throw new IllegalArgumentException("ID inválido!");
        }

        return clienteDAO.buscarPorId(id);
    }

    // Atualizar cliente
    public void atualizar(Cliente cliente) throws SQLException {

        validarCliente(cliente);

        if (cliente.getIdCliente() <= 0) {
            throw new IllegalArgumentException("ID do cliente inválido!");
        }

        Cliente existente = clienteDAO.buscarPorCpf(cliente.getCpf());

        if (existente != null
                && existente.getIdCliente() != cliente.getIdCliente()) {
            throw new IllegalArgumentException(
                    "Este CPF já pertence a outro cliente!"
            );
        }

        clienteDAO.atualizar(cliente);
    }

    // Excluir cliente
    public void excluir(int id) throws SQLException {

        if (id <= 0) {
            throw new IllegalArgumentException("ID inválido!");
        }

        clienteDAO.excluir(id);
    }

    // Validar dados do cliente
    private void validarCliente(Cliente cliente) {

        if (cliente == null) {
            throw new IllegalArgumentException("Cliente não informado!");
        }

        if (cliente.getNome() == null
                || cliente.getNome().trim().isEmpty()) {
            throw new IllegalArgumentException("Nome obrigatório!");
        }

        if (cliente.getCpf() == null
                || cliente.getCpf().trim().isEmpty()) {
            throw new IllegalArgumentException("CPF obrigatório!");
        }

        if (cliente.getEmail() != null
                && !cliente.getEmail().trim().isEmpty()
                && !cliente.getEmail().matches(
                        "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$")) {
            throw new IllegalArgumentException("E-mail inválido!");
        }

        if (cliente.getDataCadastro() == null) {
            throw new IllegalArgumentException(
                    "Data de cadastro obrigatória!"
            );
        }
    }
}
