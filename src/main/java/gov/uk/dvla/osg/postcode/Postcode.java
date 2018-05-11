package gov.uk.dvla.osg.postcode;

import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;


/**
 * Utility class for validating and formatting UK Postcodes.
 * @author OSG
 */
public class Postcode {
    
    private static String inCode = "";
    private static String outCode = "";

    /**
     * Formats a postcode by removing non-required zeros (e.g. S01 1JJ => S1 1JJ), ensuring that outcode and incode parts
     * are separated by a space (e.g. S11JJ => S1 1JJ) and converting the string to uppercase (e.g. s1 1jj => S1 1JJ).
     * Note: this does not validate the postcode. If validation is required, use Postcode.validate() before using this method.
     * @param str
     * @return formatted postcode
     */
    public static String Format(String str) {
        
        str = StringUtils.deleteWhitespace(str).toUpperCase();
        
        if (str.length() <= 4) {
            return str;
        }
        
        if (StringUtils.equalsAnyIgnoreCase(str, new String[] {"AA22","AA88", "AA89", "AA90", "AA91", "WALES", "BT00"})) {
            return "";
        }

        // Split postcode into outward and inward parts
        int outEnd = str.length() - 3;
        outCode = str.substring(0, outEnd);
        inCode = str.substring(outEnd);
       
        // Remove zeros: A01 1AA -> A1 1AA
        if (outCode.length() == 3 && NumberUtils.isCreatable(outCode.substring(1)) && StringUtils.mid(outCode, 1, 1).equals("0")) {
            outCode = StringUtils.mid(outCode, 0, 1) + StringUtils.mid(outCode, 2, 1);
        }
        
        // Remove zeros: AA01 1AA -> AA1 1AA
        if (outCode.length() == 4 && NumberUtils.isCreatable(outCode.substring(2)) && StringUtils.mid(outCode, 2, 1).equals("0")) {
            outCode = StringUtils.mid(outCode, 0, 2) + StringUtils.mid(outCode, 3, 1);
        }

        // Remove zeros: A01A 1AA -> A1A 1AA
        if (outCode.length() == 4 && !NumberUtils.isCreatable(StringUtils.mid(outCode, 1, 3)) && StringUtils.mid(outCode, 1, 1).equals("0")) {
            outCode = StringUtils.mid(outCode, 0, 1) + StringUtils.mid(outCode, 2, 2);
        }

        // Remove zeros: AA01A 1AA -> AA1A 1AA
        if (outCode.length() == 5 && !NumberUtils.isCreatable(StringUtils.mid(outCode, 4, 1)) && StringUtils.mid(outCode, 2, 1).equals("0")) {
            outCode = StringUtils.mid(outCode, 0, 2) + StringUtils.mid(outCode, 3, 2);
        }

        String temp = outCode + " " + inCode;
        return temp.replace("*", "");
    }
    
    /**
     * Validates the supplied string to check if it conforms to Royal Mail rules for a valid postcode format.
     * Postcodes can be formatted with a space or without.
     * @param postcode
     * @return true if valid
     */
    public static boolean validate(String postcode) {
        
        //'official' regex, once used by Roayl Mail
        final String regex = " (GIR 0AA)|((([A-Z-[QVX]][0-9][0-9]?)|(([A-Z-[QVX]][A-Z-[IJZ]][0-9][0-9]?)|(([A-Z-[QVX]][0-9][A-HJKSTUW])|([A-Z-[QVX]][A-Z-[IJZ]][0-9][ABEHMNPRVWXY]))))\\s?[0-9][A-Z-[CIKMOV]]{2})";
        Pattern pattern = Pattern.compile(regex);
        
        return pattern.matcher(postcode).matches();
    }
}