package gov.uk.dvla.osg.postcode;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;

public class Postcode {
    private static String inCode = "";
    private static String outCode = "";

    public static String Format(String str) {
        
        str = StringUtils.deleteWhitespace(str).toUpperCase();
        
        if (str.length() <= 4) {
            return str;
        }

        if (str.equals("AA22") || str.equals("AA88") || str.equals("AA89") || str.equals("AA90") || str.equals("AA91") || str.equals("WALES") || str.equals("BT00")) {
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
}