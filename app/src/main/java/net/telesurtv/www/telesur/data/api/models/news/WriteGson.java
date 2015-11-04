package net.telesurtv.www.telesur.data.api.models.news;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.HashMap;
import java.util.Map;
import java.util.SortedSet;
import java.util.TreeSet;

/**
 * Created by Jhordan on 25/09/15.
 */
public class WriteGson {

    public static void main(String[] args) {


        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("1", "P");
        hashMap.put("4", "D");
        hashMap.put("3", "M");
        hashMap.put("2", "L");
        hashMap.put("5", "C");

        String counter = "";


        SortedSet<String> keys = new TreeSet<>(hashMap.keySet());
        for (String valor : keys) {

            counter+= hashMap.get(valor);

            System.out.println( hashMap.get(valor));

            // do something
        }
        System.out.println(counter);

     /*   Gson gson = new GsonBuilder()
                .excludeFieldsWithoutExposeAnnotation()
                .create();

        String json = gson.toJson(new Book());
        System.out.println(json);

        Gson gsonNonExcluded = new Gson();
        String jsonNonExcluded = gsonNonExcluded.toJson(new Book());
        System.out.println(jsonNonExcluded);*/
    }
}