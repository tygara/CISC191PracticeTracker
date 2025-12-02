/**
 *  While designing the JSON persistance structure of this program, I ran into a issue I
 *  did not forsee. My plan was to use the Gson, A Java library from Google that automatically
 *  converts Java objects to JSON and JSON back to java objects. Using this libary removes the
 *  need to manually build or parse JSON by hand and handles most of the seralizaiton and 
 *  deserializaiton that would otherwise need to be done by hand. I have experience using 
 *  this library and knew that implementing it into this project would save me alot of time
 *  while also keeping this part of the code as clean as possible. 
 *
 *  However, after many errors and hours of research, I found out that Java 11 and newer 
 *  block libraries like Gson from accessing certain internal fields of java.time.LocalDate
 *  Because of this Gson cannot automatically serialize or deserilalize LocalDate the way it 
 *  can with normal classes. This is why I had to include an adapter class.
 *
 *  This issue is solved by telling Gson exactly how to turn a LocalDate into JSON and how to 
 *  turn JSON back into a LocalDate
 */
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import java.lang.reflect.Type;
import java.time.LocalDate;


/**
 *  Gson adapter for converting {@link LocalDate} values to and from Json 
 *  Adaptter serializes a LocalDate to a standard ISO-8601 string ("2025-01-01") &
 *  deserilaizes that string back into a LocalDate instance
 */
public class LocalDateAdapter implements JsonSerializer<LocalDate>, JsonDeserializer<LocalDate> {



/**
 *  Converts a {@link LocalDate} to a JSON string in ISO-8601 format 
 *  @param src The LocalDate being serialized
 *  @param typeOfSrc The type of object
 *  @param context The serialization context
 *  @return a JsonElement containing the ISO date string
 */
  @Override
  public JsonElement serialize(LocalDate src, Type typeOfSrc, JsonSerializationContext context) {
    return context.serialize(src.toString()); // "2025-01-01"
  }



/**
 *  Converts a JSON string in ISO-8601 format back into a {@link LocalDate}.
 *  @param json The JSON element containing the date string
 *  @param typeOfT the expected type
 *  @param context The deserialization context
 *  @return the parsed LocalDate
 *  @throws JsonParseException if the string cannot be parsed as a LocalDate
 */
  @Override
  public LocalDate deserialize(JsonElement json, Type typeOfT, com.google.gson.JsonDeserializationContext context)
      throws JsonParseException {
    return LocalDate.parse(json.getAsString());
  }
}
