package gov.uk.dvla.osg.postcode.tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import gov.uk.dvla.osg.postcode.Postcode;

class FormatPostcodeTests {
    
    @Test
    void Format_NoSpacesWithoutZeros_CorrectlyFormatted() {
        String testStr = "SA67JL";
        assertEquals("SA6 7JL", Postcode.Format(testStr));
        
        testStr = "S67JL";
        assertEquals("S6 7JL", Postcode.Format(testStr));
                
        testStr = "SAA67JL";
        assertEquals("SAA6 7JL", Postcode.Format(testStr));
        
        testStr = "SAA6A7JL";
        assertEquals("SAA6A 7JL", Postcode.Format(testStr));        
    }

    @Test
    void Format_NoSpacesWithZeros_CorrectlyFormatted() {
        
        String testStr = "SA067JL";
        assertEquals("SA6 7JL", Postcode.Format(testStr));
        
        testStr = "S067JL";
        assertEquals("S6 7JL", Postcode.Format(testStr));
        
        testStr = "S06A7JL";
        assertEquals("S6A 7JL", Postcode.Format(testStr));
        
        testStr = "SA06A7JL";
        assertEquals("SA6A 7JL", Postcode.Format(testStr));
    }
    
    @Test
    void Format_Spaces_CorrectlyFormatted() {
        String testStr = "SA6 7JL";
        assertEquals("SA6 7JL", Postcode.Format(testStr));
        
        testStr = "S67JL";
        assertEquals("S6 7JL", Postcode.Format(testStr));
        
        testStr = "S06 7JL";
        assertEquals("S6 7JL", Postcode.Format(testStr));
        
        testStr = "SA06 7JL";
        assertEquals("SA6 7JL", Postcode.Format(testStr));
        
        testStr = "SAA6 7JL";
        assertEquals("SAA6 7JL", Postcode.Format(testStr));
        
        testStr = "SAA6A 7JL";
        assertEquals("SAA6A 7JL", Postcode.Format(testStr));
        
        testStr = "SA06A 7JL";
        assertEquals("SA6A 7JL", Postcode.Format(testStr));
    }
    
    @Test
    void Format_TooShort_NoChange() {
        String testStr = "SA6";
        assertEquals("SA6", Postcode.Format(testStr));
        
        testStr = "SA67";
        assertEquals("SA67", Postcode.Format(testStr));
        
        testStr = "SA67J";
        assertEquals("SA 67J", Postcode.Format(testStr));
    }
    
    @Test
    void Format_DummyPC_ReturnsBlank() {
        String testStr = "WALES";
        assertEquals("", Postcode.Format(testStr));
    }
    
    @Test
    void Format_AsteriskInPC_AsteriskRemoved() {
        
        String testStr = "SA*67JL";
        assertEquals("SA6 7JL", Postcode.Format(testStr));
        
        testStr = "S*67JL";
        assertEquals("S6 7JL", Postcode.Format(testStr));
        
        testStr = "S*6A7JL";
        assertEquals("S6A 7JL", Postcode.Format(testStr));
        
        testStr = "SA*6A7JL";
        assertEquals("SA6A 7JL", Postcode.Format(testStr));
        
        testStr = "SA6**";
        assertEquals("SA 6", Postcode.Format(testStr));
    }
    
}
