package org.projectspinoza.ontology;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import org.codehaus.jackson.map.ObjectMapper;
import org.projectspinoza.concept.models.TagConceptNet;

public class OntoConceptResultGenerator {
    
    
    
    public static void getConceptResult(List<TagConceptNet> concepts){
        
    }
    
    public static void generatFile(Object object,String fileName){
        FileWriter fw = null;
        try {
        fw = new FileWriter(new File(fileName+".txt"));
        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(fw, object);
        fw.close();
        }catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            try {
                if (fw != null) {
                    fw.close();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

}
