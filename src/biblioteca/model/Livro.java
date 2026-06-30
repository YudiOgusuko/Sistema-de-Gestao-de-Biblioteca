package biblioteca.model;

import java.util.UUID;

public class Livro {

    private String isbn;
    private String titulo;
    private String autor;
    private int anoDePublicacao;
    private String genero;
    private boolean disponivel;

    public Livro(String titulo, String autor, int anoDePublicacao, String genero) {
        this.isbn = UUID.randomUUID().toString();
        this.titulo = titulo;
        this.autor = formatar(autor);
        this.anoDePublicacao = anoDePublicacao;
        this.genero = formatar(genero);
        this.disponivel = true;
    }

    public String getIsbn() {return isbn;}
    public String getTitulo() {return titulo;}
    public String getAutor() {return autor;}
    public int getAnoDePublicacao() {return anoDePublicacao;}
    public String getGenero() {return genero;}
    public boolean isDisponivel() {return disponivel;}

    public void setIsbn(String isbn) {this.isbn = isbn;}
    public void setTitulo(String titulo) {this.titulo = titulo;}
    public void setAutor(String autor) {this.autor = autor;}
    public void setAnoDePublicacao(int anoDePublicacao) {this.anoDePublicacao = anoDePublicacao;}
    public void setGenero(String genero) {this.genero = genero;}
    public void setDisponivel(boolean disponivel) {this.disponivel = disponivel;}

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
        return String.format(
                "ISBN: %s | " +
                "Título: %s | " +
                "Autor: %s | " +
                "Ano de Publicação: %d | " +
                "Gênero: %s | " +
                "Disponível: %b ",
                 isbn, titulo, autor, anoDePublicacao, genero, disponivel);
    }
}
