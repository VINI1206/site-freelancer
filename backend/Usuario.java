package backend;

public class Usuario {
    private String email;
    private String senha;
    private String cpf;
    private String telefone;
    private String linkedin;
    private String curriculo;

    public Usuario(String email, String senha, String cpf, String telefone, String linkedin, String curriculo) {
        this.email = email;
        this.senha = senha;
        this.cpf = cpf;
        this.telefone = telefone;
        this.linkedin = linkedin;
        this.curriculo = curriculo;
    }

    // Getters
    public String getEmail() { return email; }
    public String getSenha() { return senha; }
    public String getCpf() { return cpf; }
    public String getTelefone() { return telefone; }
    public String getLinkedin() { return linkedin; }
    public String getCurriculo() { return curriculo; }
}
