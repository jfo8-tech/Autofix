package model;

public class Usuario {

    private int idUsuario;
    private int idFuncionario;
    private String usuario;
    private String senha;
    private String tipo;

    public Usuario() {
    }

    public Usuario(int idFuncionario, String usuario,
            String senha, String tipo) {
        this.idFuncionario = idFuncionario;
        this.usuario = usuario;
        this.senha = senha;
        this.tipo = tipo;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public int getIdFuncionario() {
        return idFuncionario;
    }

    public void setIdFuncionario(int idFuncionario) {
        this.idFuncionario = idFuncionario;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
}
