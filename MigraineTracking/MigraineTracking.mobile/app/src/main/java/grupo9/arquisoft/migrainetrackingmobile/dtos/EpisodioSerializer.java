package grupo9.arquisoft.migrainetrackingmobile.dtos;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import java.lang.reflect.Type;

/**
 * Created by henryfvargas on 16/05/15.
 */
public final class EpisodioSerializer implements JsonSerializer<EpisodioDolorDTO>
{
    @Override
    public JsonElement serialize(EpisodioDolorDTO src, Type typeOfSrc, JsonSerializationContext context)
    {
        JsonObject object=new JsonObject();
        object.add("id",context.serialize(src.getId()));
        object.add("fecha",context.serialize(src.getFecha()));
        object.add("localizacion",context.serialize(src.getLocalizacion()));
        object.add("intesidad",context.serialize(src.getIntensidad()));
        object.add("hoursSlept",context.serialize(src.getHoursSlept()));
        object.add("cedulaPaciente",context.serialize(src.getCedulaPaciente()));
        object.add("sintomas",context.serialize(src.getSintomas()));
        object.add("catalizadores",context.serialize(src.getCatalizadores()));
        object.add("medicamentos",context.serialize(src.getMedicamentos()));
        return object;
    }
}
