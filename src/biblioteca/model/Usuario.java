package biblioteca.model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.UUID;

public class Usuario {


    private String id;
    private String nome;
    private String email;
    private LocalDate dataDeCadastro;

    public Usuario(String nome, String email) {
        this.id = UUID.randomUUID().toString();
        this.nome = formatar(nome);
        this.email = email;
        this.dataDeCadastro = LocalDate.now();
    }

    public String getId() {return id;}
    public String getNome() {return nome;}
    public String getEmail() {return email;}
    public LocalDate getDataDeCadastro() {return dataDeCadastro;}

    public void setId(String id) {this.id = id;}
    public void setNome(String nome) {this.nome = nome;}
    public void setEmail(String email) {this.email = email;}
    public void setDataDeCadastro(LocalDate dataDeCadastro) {this.dataDeCadastro = dataDeCadastro;}

    public String formatar(String name) {

        name = name.replace("'", "").replace("\"", "");

        String[] palavra = name.toLowerCase().split("\\s+");
        StringBuilder formatada = new StringBuilder();

        for(String p : palavra){
            if(!p.isEmpty()){
                formatada.append(Character.toUpperCase(p.charAt(0)))
                        .append(p.substring(1))
                        .append(" ");
            }
        }
        return formatada.toString().trim();
    }

    @Override
    public String toString() {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMM yyyy");

        return String.format(
                "Id: %s | " +
                "Nome: %s | " +
                "E-mail: %s | " +
                "Data de Cadastro: %s",
                id, nome, email, dataDeCadastro.format(formatter));
    }
}
