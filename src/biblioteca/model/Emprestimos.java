package biblioteca.model;

import biblioteca.enums.StatusEmprestimos;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

public class Emprestimos {

    //essa classe conecta o Usuário com o Livro

    private String id;                              //UUID gerado automaticamente
    private String isbnLivro;                       //referência ao Livro (não guarde o objeto inteiro, só o ISBN)
    private String idUsuario;                       //referência ao Usuário
    private LocalDate dataEmprestimo;               //quando foi emprestado
    private LocalDate dataPrevistaDevolucao;        //emprestimo +14 dias -> regra
    private LocalDate dataDevolucaoReal;            // começa "null", preenchida quando devolvido
    private StatusEmprestimos status;               //"ATIVO" ou "DEVOLVIDO"

    public Emprestimos(String isbnLivro, String idUsuario, LocalDate dataEmprestimo, LocalDate dataPrevistaDevolucao, StatusEmprestimos status) {
        this.id = UUID.randomUUID().toString();
        this.isbnLivro = isbnLivro;
        this.idUsuario = idUsuario;
        this.dataEmprestimo = dataEmprestimo;
        this.dataPrevistaDevolucao = dataPrevistaDevolucao;
        this.dataDevolucaoReal = null;
        this.status = status;
    }

    public String getId() {return id;}
    public String getIsbnLivro() {return isbnLivro;}
    public String getIdUsuario() {return idUsuario;}
    public LocalDate getDataEmprestimo() {return dataEmprestimo;}
    public LocalDate getDataPrevistaDevolucao() {return dataPrevistaDevolucao;}
    public LocalDate getDataDevolucaoReal() {return dataDevolucaoReal;}
    public StatusEmprestimos getStatus() {return status;}

    public void setId(String id) {this.id = id;}
    public void setIsbnLivro(String isbnLivro) {this.isbnLivro = isbnLivro;}
    public void setIdUsuario(String idUsuario) {this.idUsuario = idUsuario;}
    public void setDataEmprestimo(LocalDate dataEmprestimo) {this.dataEmprestimo = dataEmprestimo;}
    public void setDataPrevistaDevolucao(LocalDate dataPrevistaDevolucao) {this.dataPrevistaDevolucao = dataPrevistaDevolucao;}
    public void setDataDevolucaoReal(LocalDate dataDevolucaoReal) {this.dataDevolucaoReal = dataDevolucaoReal;}
    public void setStatus(StatusEmprestimos status) {this.status = status;}

    @Override
    public String toString() {

        String linha = "-".repeat(30);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMM yyyy");

        return String.format(
                "Id: %s%n" +
                "ISBN do Livro: %s%n" +
                "Id do Usuário: %s%n" +
                "Data de Empréstimo: %s%n" +
                "Data Prevista de Devolução: %s%n" +
                "Data de Devolução: %s%n" +
                "Status: %s%n" +
                "%s",
                id, isbnLivro, idUsuario,
                dataEmprestimo.format(formatter), dataPrevistaDevolucao.format(formatter),
                (dataDevolucaoReal != null ? dataDevolucaoReal.format(formatter) : "Em Aberto"),
                status, linha);
    }
}
