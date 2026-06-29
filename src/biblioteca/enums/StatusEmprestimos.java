package biblioteca.enums;

import java.util.Scanner;

public enum StatusEmprestimos {

    ATIVO("Ativo"),
    DEVOLVIDO("Devolvido");

    private String valor;

    StatusEmprestimos(String valor){
        this.valor = valor;
    };

    public static StatusEmprestimos statusEmprestimos(Scanner scanner, String status) {
        String statusVerificado = status;
        while (true) {
            for(StatusEmprestimos statusEmprestimos : values()) {
                if(statusEmprestimos.getValor().equalsIgnoreCase(statusVerificado)) {
                    return statusEmprestimos;
                }
            }
            System.out.println("Status Inválido.");
            System.out.println("Status: ");
            statusVerificado = scanner.next();
        }
    }

    public String getValor() {
        return valor;
    }

    @Override
    public String toString() {
        return this.valor;
    }
}
