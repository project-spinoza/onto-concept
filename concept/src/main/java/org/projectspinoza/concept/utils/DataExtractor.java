package org.projectspinoza.concept.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URL;

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;
import org.projectspinoza.concept.models.ConceptNet;

public class DataExtractor {

    static ObjectMapper mapper;

    public static ConceptNet getCurlData(String keyword) {
        URL url;
        StringBuilder response = new StringBuilder();
        try {
            url = new URL("http://conceptnet5.media.mit.edu/data/5.3/c/en/"+ keyword);
            String line;
            BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream(), "UTF-8"));
            if (reader.toString() == "") {
                System.out.println("No result found");
                System.exit(0);
            }
            while ((line = reader.readLine()) != null) {
                response.append(line);
            }
            reader.close();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        String conceptNetData = response.toString();
        
        return parse(conceptNetData);
    }

    public static ConceptNet parse(String response) {
        ConceptNet conepts = new ConceptNet();
        mapper = new ObjectMapper();
        try {
            conepts = mapper.readValue(response, new TypeReference<ConceptNet>() {});
        } catch (Exception e) {
            e.printStackTrace();
        }
        return conepts;
    }
}
