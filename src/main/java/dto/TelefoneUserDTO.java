package dto;

public class TelefoneUserDTO {

    private String nome;
    private String numero;
    private String tipo;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    @Override
    public String toString() {
        return "TelefoneUserDTO{" +
                "nome='" + nome + '\'' +
                ", numero='" + numero + '\'' +
                ", tipo='" + tipo + '\'' +
                '}';
    }
}
