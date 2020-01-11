package main.java.utilities;

import com.google.gson.Gson;
import com.google.gson.internal.LinkedTreeMap;
import com.google.gson.reflect.TypeToken;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

/**
 * This class read the json file and map it to the domain class we are going to use in our tests
 * @author Jason Campos on 1/9/2020
 */
public class ReadJson {

    public ArrayList<Object> readFile(DocumentsPaths documentsPaths) {
        JSONParser jsonParser = new JSONParser();
        ArrayList<Object> list;
        ArrayList<Object> listElements = new ArrayList<>();
        try {
            FileReader reader = new FileReader(documentsPaths.getFileName());
            JSONArray jsonArray = (JSONArray) jsonParser.parse(reader);
            list =  new Gson().fromJson(jsonArray.toString(),
                    new TypeToken<ArrayList<Object>>(){}.getType());
            for (Object element: list) {
                Constructor<?> constructor;
                constructor = documentsPaths.getDomainType().getConstructor(LinkedTreeMap.class);
                Object object;
                object = constructor.newInstance(new Object[] { element });
                listElements.add(object);
            }
        } catch (ParseException |IOException | InstantiationException |
                InvocationTargetException | NoSuchMethodException | IllegalAccessException e) {
            e.printStackTrace();
        }
        return listElements;
    }
}
