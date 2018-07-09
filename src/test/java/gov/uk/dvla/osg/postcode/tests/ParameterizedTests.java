package gov.uk.dvla.osg.postcode.tests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import gov.uk.dvla.osg.address.PostcodeFormatter;

@RunWith(Parameterized.class)
public class ParameterizedTests {

    private String input;
    private boolean expected;

    @Parameters
    public static List<Object[]> data() {
        return Arrays.asList(new Object[][] { 
            {"M1 1AA",   true  }, 
            {"M60 1NW",  true  }, 
            {"CR2 6XH",  true  },
            {"DN55 1PT", true  }, 
            {"W1A 1HQ",  true  },
            {"EC1A 1BB", true  },
            {"Z1A 0B1",  false }, 
            {"aWC2H 7LT",false },
            {"WC2H 7LTa",false },
            {"WC2H",     false },
            {"M11AA",    true  }, 
            {"M601NW",   true  }, 
            {"CR26XH",   true  },
            {"DN551PT",  true  }, 
            {"W1A1HQ",   true  },
            {"EC1A1BB",  true  },
            {"Z1A0B1",   false }, 
            {"aWC2H7LT", false },
            {"WC2H7LTa", false }
         });
    }
    
    public ParameterizedTests(String input, boolean expected) {
        this.input = input;
        this.expected = expected;
    }
    
    @Test
    public void test() {
        assertEquals(expected, PostcodeFormatter.validate(input), "Testing: " + input);
    }
}
