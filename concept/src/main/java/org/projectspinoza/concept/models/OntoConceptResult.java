package org.projectspinoza.concept.models;

import java.util.ArrayList;
import java.util.List;

public class OntoConceptResult {

    private String concept;
    private String relType;
    private List<String> surfaceTexts = new ArrayList<String>();

    public String getConcept() {
        return concept;
    }

    public OntoConceptResult() {

    }

    public OntoConceptResult(String concept,String relType,List<String> surfaceText) {
        this.concept = concept;
        this.relType= relType;
        this.surfaceTexts = surfaceText;
    }

    public void setConcept(String concept) {
        this.concept = concept;
    }

    public String getRelType() {
        return relType;
    }

    public void setRelType(String relType) {
        this.relType = relType;
    }

    public List<String> getSurfaceTexts() {
        return surfaceTexts;
    }

    public void setSurfaceTexts(List<String> surfaceText) {
        this.surfaceTexts = surfaceText;
    }

    @Override
    public String toString() {
        return "OntoConceptResult [concept=" + concept + ", relType=" + relType
                + ", surfaceText=" + surfaceTexts + "]";
    }

}
