package br.inatel.dm110.adapters;

import com.google.gson.*;

import java.lang.reflect.Type;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class LocalDateTimeAdapter implements JsonDeserializer<LocalDateTime> {

    @Override
    public LocalDateTime deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        JsonObject jsonObject = json.getAsJsonObject();
        JsonObject date = jsonObject.getAsJsonObject("date");
        JsonObject time = jsonObject.getAsJsonObject("time");

        LocalDate localDate = LocalDate.of(
                date.get("year").getAsInt(),
                date.get("month").getAsInt(),
                date.get("day").getAsInt()
        );

        LocalTime localTime = LocalTime.of(
                time.get("hour").getAsInt(),
                time.get("minute").getAsInt(),
                time.get("second").getAsInt(),
                time.get("nano").getAsInt()
        );

        return LocalDateTime.of(localDate, localTime);
    }
}
