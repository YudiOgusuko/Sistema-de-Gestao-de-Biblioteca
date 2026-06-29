package biblioteca.util;

import com.google.gson.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class GsonUtil {


    public static final Gson GSON = new GsonBuilder()

            .registerTypeAdapter(LocalDate.class, (JsonSerializer<LocalDate>)
                    (src, type, ctx) -> new JsonPrimitive(src.format(DateTimeFormatter.ISO_LOCAL_DATE)))
            .registerTypeAdapter(LocalDate.class, (JsonDeserializer<LocalDate>)
                    (json, type, ctx) -> LocalDate.parse(json.getAsString(), DateTimeFormatter.ISO_LOCAL_DATE))
            .setPrettyPrinting()
            .create();
}
