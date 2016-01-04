package org.projectspinoza.concept;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PowerMockIgnore;
import org.powermock.modules.junit4.PowerMockRunner;
import org.projectspinoza.concept.utils.DataExtractor;

@PowerMockIgnore("javax.management.*")
@RunWith(PowerMockRunner.class)
public class TestTagConceptMatcher {

    
    @Before
    public void setup() {
        PowerMockito.mockStatic(DataExtractor.class);
    }
}
