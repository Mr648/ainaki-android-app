package apps.sffa.com.ainaki.util;

/**
 * Created by Diako on 26/05/2018.
 */

public class ValidationRegex {

    public  static  final String REGEX_UserEMAIL ="^.*$";
    public  static  final String REGEX_UserPASS ="^.*$";
    public  static  final String REGEX_UserPHONE ="^.*$";

    public static final String REGEX_PHONE
            = "^(0|\\+98)?([ ]|,|-|[()]){0,2}9[1|2|3|4]([ ]|,|-|[()]){0,2}(?:[0-9]([ ]|,|-|[()]){0,2}){8}$";
    public static final String REGEX_VERIFICATIONCODE
            = "^(0-9){4}$";


}
