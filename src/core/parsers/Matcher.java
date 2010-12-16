package core.parsers;

import entities.GreekNames;

/**
 *
 * @author Periklis Ntanasis
 */
public class Matcher {
    
    // greek cellphones format
    private static final String CELLPHONEFORMAT = "\\+??3??0??69\\d\\d\\d\\d\\d\\d\\d\\d";

    public static Boolean isCellphone(String word) {
        return word.matches(CELLPHONEFORMAT);
    }

    public static Boolean isName(String word) {
            for(int i=0;i<GreekNames.id.length;i++)
            {
                if(word.toLowerCase().matches((GreekNames.id[i]).toLowerCase()))
                    return true;
            }
        return false;
    }

    public static Boolean isSurName(String word) {
            for(int i=0;i<GreekNames.surname.length;i++)
            {
                if(word.matches((GreekNames.surname[i])))
                    return true;
            }
        return false;
    }

}
