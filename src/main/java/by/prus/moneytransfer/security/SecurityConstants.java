package by.prus.moneytransfer.security;

import by.prus.moneytransfer.SpringApplicationContext;

/**
 * Final fields of this class represent behavior of Spring-Security.
 * EXPIRATION_TIME - time of validity of user token
 * PASSWORD_RESET_EXPIRATION_TIME - time of validity link when user do password reset
 * TOKEN_PREFIX - prefix of token in response
 * HEADER_STRING - name of header user log in under
 * SIGN_UP_URL - URL used for common oportynity to create new user even unverified users
 * VERIFICATION_EMAIL_URL - the same but for email verification
 * PASSWORD_REQUEST_RESET_URL - the same but for password reset (because it will be done from other application)
 * PASSWORD_RESET_URL - the same for password. But it will blocked by Secyrity with @PreAuthorize annotation
 * CERTIFICATES_URL - allows to users do actions with certificates (not at all, becase of secured by @PreAuthorize)
 * TAGS_URL - allows to users do actions with tags (not at all, becase of secured by @PreAuthorize)
 */
public class SecurityConstants {

    public static final long EXPIRATION_TIME = 864000000; // 10 days
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String HEADER_STRING = "Authorize";
    public static final String SIGN_UP_URL = "/user";


    public static String getTokenSecret(){
        //Достает инстанс потому что стоит аннотация @Component и в конфигурации достается бин с таким именем.
        AppProperties appProperties = (AppProperties) SpringApplicationContext.getBean("AppProperties");
        return appProperties.getTokenSecret();

    }

}
