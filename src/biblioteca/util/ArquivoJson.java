package biblioteca.util;

import com.google.gson.Gson;

import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class ArquivoJson {

    public static <T> void salvar(List<T> lista, String caminhoArquivo) {

        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(caminhoArquivo, false))) {
            GsonUtil.GSON.toJson(lista, bufferedWriter);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static <T> List<T> carregar(String caminhoArquivo, Type tipo) {

        File file = new File(caminhoArquivo);

        if(!file.exists()){
            return new ArrayList<>();
        }

        try (BufferedReader br = new BufferedReader(new FileReader(caminhoArquivo))) {

            List<T> lista = GsonUtil.GSON.fromJson(br, tipo);

            return lista != null ? lista : new ArrayList<>();

        } catch (IOException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }

    }
}

