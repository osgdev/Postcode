package gov.uk.dvla.osg.postcode.tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import gov.uk.dvla.osg.address.AddressFormatter;

class AddressLineFormatTests {

    @Test
    void Fromat_AlreadyFormatted_NoChange() {
        String line = "11 Evergreen Terrace";
        String result = "11 Evergreen Terrace";
        String formatted = AddressFormatter.formatLine(line);
        assertEquals(formatted, result);
    }

    @Test
    void Fromat_ExtraSpaces_SpacesRemoved() {
        String line = "11  Evergreen  Terrace";
        String result = "11 Evergreen Terrace";
        String formatted = AddressFormatter.formatLine(line);
        assertEquals(formatted, result);
    }
    
    @Test
    void Fromat_WithComma_CommaRemoved() {
        String line = "11, Evergreen Terrace";
        String result = "11 Evergreen Terrace";
        String formatted = AddressFormatter.formatLine(line);
        assertEquals(formatted, result);
    }
    
    @Test
    void Fromat_WithCommaAndExtraSpaces_CorrectlyFormatted() {
        String line = "11,  Evergreen  Terrace   ";
        String result = "11 Evergreen Terrace";
        String formatted = AddressFormatter.formatLine(line);
        assertEquals(formatted, result);
    }
}
