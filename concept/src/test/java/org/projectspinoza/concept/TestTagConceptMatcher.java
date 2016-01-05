package org.projectspinoza.concept;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PowerMockIgnore;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.projectspinoza.concept.models.ConceptNet;
import org.projectspinoza.concept.models.Relation;
import org.projectspinoza.concept.models.SurfaceText;
import org.projectspinoza.concept.utils.DataExtractor;

@PowerMockIgnore("javax.management.*")
@RunWith(PowerMockRunner.class)
@PrepareForTest({ DataExtractor.class })
public class TestTagConceptMatcher {
    List<Relation> expected = new ArrayList<Relation>();
    Map<String, Object> edge = new HashMap<String, Object>();
    ObjectMapper mapper = new ObjectMapper();
    SurfaceText surfaceText, surfaceText1;
    Relation relation, relation1;
    Object edgeObject, edgeObject1;
    String jsonStr, jsonStr1;

    @Mock
    ConceptNet conceptNet;

    @Before
    public void setup() throws JsonParseException, JsonMappingException,
            IOException {
        PowerMockito.mockStatic(DataExtractor.class);
        conceptNet = Mockito.mock(ConceptNet.class);

        jsonStr = "{\"context\":\"/ctx/all\",\"dataset\":\"/d/dbpedia/en\",\"end\":\"/c/en/germany\","
                + "\"features\":[\"/c/en/bmw/r/AtLocation-\",\"/c/en/bmw-/c/en/germany\","
                + "\"-/r/AtLocation/c/en/germany\"],\"id\":\"/e/aa24fc09702027794c11c5ba5442275e3cda9127\","
                + "\"license\":\"/l/CC/By-SA\",\"rel\":\"/r/AtLocation\",\"source_uri\":"
                + "\"/or/[/and/[/s/activity/omcs/commons_manual_entry/,/s/contributor/omcs/Yahma/]/,"
                + "/s/dbpedia/3.7/]\",\"sources\":[\"/s/activity/omcs/commons_manual_entry\","
                + "\"/s/contributor/omcs/Yahma\",\"/s/dbpedia/3.7\"],\"start\":\"/c/en/bmw\","
                + "\"surfaceText\":\"Youarelikelytofind[[aBMW]]in[[Germany]].\",\"uri\":\"/a/[/r/AtLocation/,"
                + "/c/en/bmw/,/c/en/germany/]\",\"weight\":1.8073549220576042}";

        jsonStr1 = "{\"context\":\"/ctx/all\",\"dataset\":\"/d/globalmind\",\"end\":\"/c/en/coffee\","
                + "\"features\":[\"/c/en/cappuccino/r/IsA-\",\"/c/en/cappuccino-/c/en/coffee\","
                + "\"-/r/IsA/c/en/coffee\"],\"id\":\"/e/2a7c6c523ad5449f2464571ae88fa1196d5165cf\","
                + "\"license\":\"/l/CC/By-SA\",\"rel\":\"/r/IsA\",\"source_uri\":"
                + "\"/or/[/and/[/s/activity/globalmind/assert/,/s/contributor/omcs/maybe/]/,"
                + "/s/umbel/2013/]\",\"sources\":[\"/s/activity/globalmind/assert\","
                + "\"/s/contributor/omcs/maybe\",\"/s/umbel/2013\"],\"start\":\"/c/en/cappuccino\","
                + "\"surfaceText\":\"Kindsof[[coffee]]:[[cappuccino]]\",\"uri\":\"/a/[/r/IsA/,/c/en/cappuccino/,"
                + "/c/en/coffee/]\",\"weight\":2.321928094887362}";

        edgeObject = new ObjectMapper().readValue(jsonStr, Object.class);
        edgeObject1 = new ObjectMapper().readValue(jsonStr1, Object.class);

        surfaceText = new SurfaceText("start", "YouarelikelytofindaBMWinGermany.", 1.80735);
        surfaceText1 = new SurfaceText("end", "Kindsofcoffee:cappuccino", 2.32192);
        relation = new Relation("AtLocation", surfaceText);
        relation1 = new Relation("IsA", surfaceText1);
        expected.add(relation);
        expected.add(relation1);
    }

    @Test
    public void testCase_1() {
        List<Object> edges = new ArrayList<Object>();
        edges.add(edgeObject);
        Mockito.when(conceptNet.getEdges()).thenReturn(edges);
        TagConceptMatcher conceptMatcher = new TagConceptMatcher();
        List<Relation> result = conceptMatcher.getRelations("bmw", conceptNet);

        assertEquals(expected.get(0).getRelType(), result.get(0).getRelType());
        assertEquals(expected.get(0).getSurfaceTextStart().getSurfaceText(),
                result.get(0).getSurfaceTextStart().getSurfaceText());
        assertEquals(expected.get(0).getSurfaceTextEnd(), result.get(0)
                .getSurfaceTextEnd());
    }

    @Test
    public void testCase_2() {
        List<Object> edges = new ArrayList<Object>();
        edges.add(edgeObject1);
        Mockito.when(conceptNet.getEdges()).thenReturn(edges);
        TagConceptMatcher conceptMatcher = new TagConceptMatcher();
        List<Relation> result1 = conceptMatcher.getRelations("coffee", conceptNet);

        assertEquals(expected.get(1).getRelType(), result1.get(0).getRelType());
        assertEquals(expected.get(1).getSurfaceTextStart(), result1.get(0)
                .getSurfaceTextStart());
        assertEquals(expected.get(1).getSurfaceTextEnd().getSurfaceText(),
                result1.get(0).getSurfaceTextEnd().getSurfaceText());
    }
}
