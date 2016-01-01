package org.projectspinoza.concept;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.projectspinoza.concept.models.ConceptNet;
import org.projectspinoza.concept.models.Relation;
import org.projectspinoza.concept.models.SurfaceText;
import org.projectspinoza.concept.models.TagConceptNet;
import org.projectspinoza.concept.utils.DataExtractor;

public class TagConceptMatcher {
    
    private static Logger log = LogManager.getRootLogger();
    private Set<String> tags = new HashSet<String>();

    public TagConceptMatcher(Set<String> tags) {
        this.tags = tags;
    }

    public List<TagConceptNet> getConcepts() {
        
        List<TagConceptNet> tagConcepts = new ArrayList<TagConceptNet>();
        for (String tag : tags) {
            log.debug("Runing ConceptNet for "+tag+"...");
            ConceptNet conceptNet = DataExtractor.getCurlData(tag);
            List<Relation> conceptRelations = fetchAttributes(conceptNet, tag);
            tagConcepts.add(new TagConceptNet(tag, conceptRelations));
        }
       return tagConcepts;
    }

    @SuppressWarnings("unchecked")
    public List<Relation> fetchAttributes(ConceptNet conceptnet, String word) {

        //
        List<Relation> relations = new ArrayList<Relation>();
        for (Object edge : conceptnet.getEdges()) {
            try {
                Map<String, Object> attributes = (Map<String, Object>) edge;
                String rel = (String) attributes.get("rel");
                String start = (String) attributes.get("start");
                String end = (String) attributes.get("end");
                Double weight = (Double) attributes.get("weight");
                String text = (String) attributes.get("surfaceText");
                if (text != null) {
                    text = text.replaceAll("[\\[\\]]", "");
                    rel = rel.replace("/r/", "");
                    start = start.replace("/c/en/", "");
                    end = end.replace("/c/en/", "");
                    SurfaceText sentence = null;
                    if (start.contains(word)) {
                        sentence = new SurfaceText("start", text, weight);
                    } else if (end.contains(word)) {
                        sentence = new SurfaceText("end", text, weight);
                    }
                    log.info("Adding relation of type "+rel+"...");
                    if (relations.size() == 0) {
                        relations.add(new Relation(rel, sentence));
                    } else {
                        addToRelationList(relations,new Relation(rel, sentence));
                    }
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        return relations;
    }

    public void addToRelationList(List<Relation> relations, Relation other) {
       
        for (int i = 0; i < relations.size(); i++) {
            if (relations.get(i).getRelType().equals(other.getRelType())) {
                if (other.getSurfaceTextStart() != null) {
                    relations.get(i).setSurfaceTextStart(other.getSurfaceTextStart());
                } else {
                    relations.get(i).setSurfaceTextEnd(other.getSurfaceTextEnd());
                }
                return;
            } 
        }
        relations.add(other);
    }
}
