package gov.uk.dvla.osg.postcode.tests;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.jupiter.api.Test;

import gov.uk.dvla.osg.postcode.Postcode;

class FormatPostcodeTests {
    
    List<String> zips = new ArrayList<String>();

    @Before
    private void Setup() {
        
      //Valid ZIP codes
      zips.add("SW1W 0NY");  
      zips.add("PO16 7GZ");  
      zips.add("GU16 7HF");  
      zips.add("L1 8JQ");  

      //Invalid ZIP codes
      zips.add("Z1A 0B1");
      zips.add("A1A 0B11");
    }
    
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
    
    @Test
    void Validate_ValidPostcodeList_AllReturnTrue() {
        for (String zip : zips)
        {
            assertFalse(Postcode.validate(zip));
        }
    }
}
