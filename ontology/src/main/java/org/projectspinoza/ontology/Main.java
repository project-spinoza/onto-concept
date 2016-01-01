package org.projectspinoza.ontology;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.projectspinoza.concept.TagConceptMatcher;
import org.projectspinoza.concept.models.TagConceptNet;

public class Main {
    private static Logger log = LogManager.getRootLogger();
    public static Set<String> tweetTags = new HashSet<String>();
    static TermOntologyMatcher termOntologyMatcher;
    
    public static void main(String[] args) {
        if (args.length != 2) {
            log.error("Please provide two arguments, e.g tweets.txt ontologies.json");
            System.exit(0);
        }
        log.info("Initializing ontologies!");
        termOntologyMatcher = new TermOntologyMatcher(args[0], args[1]);
        Object onotolgy = termOntologyMatcher.getOntology();
        OntoConceptResultGenerator.generatFile(onotolgy,"ontology");
        tweetTags = termOntologyMatcher.getConceptTweetTags();
        log.info("Done with Ontology!");
        log.info("Initializing conceptNet...");
        List<TagConceptNet> concepts = new TagConceptMatcher(tweetTags).getConcepts();
        OntoConceptResultGenerator.generatFile(concepts,"conceptNet");
        log.info("Done with conceptNet!");
    }
}
