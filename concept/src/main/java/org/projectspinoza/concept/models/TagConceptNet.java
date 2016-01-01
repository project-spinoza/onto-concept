package org.projectspinoza.concept.models;

import java.util.ArrayList;
import java.util.List;

public class TagConceptNet {

    private String concept;
    private List<Relation> conceptText;
    
    public TagConceptNet(){
    }
    
    public TagConceptNet(String concept, List<Relation> relations) {
        this.concept = concept;
        this.conceptText = relations;
    }

    public String getConcept() {
        return concept;
    }

    public void setConcept(String concept) {
        this.concept = concept;
    }

    public List<Relation> getConceptText() {
        return conceptText;
    }

    public void setConceptText(List<Relation> relations) {
        this.conceptText = relations;
    }

    public void addConcept(Relation relation) {
        if (conceptText == null) {
            conceptText = new ArrayList<Relation>();
        }
        conceptText.add(relation);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(concept)
        .append(conceptText);
        return sb.toString();
    }

}
