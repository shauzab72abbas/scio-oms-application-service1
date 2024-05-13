package ca.cn.scio.application.util;

import java.io.IOException;

import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.auth.oauth2.IdTokenCredentials;
import com.google.auth.oauth2.IdTokenProvider;

import ca.cn.scio.application.exception.SystemException;

@Component
public class AuthUtil {

    public HttpHeaders generateAuthorizationHeader(String baseUrl) {
        HttpHeaders headers = new HttpHeaders() ;
        String token = null;
        try {
            token = generateAuthToken(baseUrl);
        }catch(IOException exception){
            throw new SystemException() ;
        }
        headers.set("Authorization" , "Bearer "+token) ;
        return headers;

    }
    public String generateAuthToken(String recievingServiceUrl) throws IOException {
        GoogleCredentials credentials = GoogleCredentials.getApplicationDefault();
        if (!(credentials instanceof IdTokenProvider)) {
            throw new IllegalArgumentException("Credentials are not an instance of IdTokenProvider.");
        }
        IdTokenCredentials tokenCredential =
                IdTokenCredentials.newBuilder()
                        .setIdTokenProvider((IdTokenProvider) credentials)
                        .setTargetAudience(recievingServiceUrl)
                        .build();

        return tokenCredential.refreshAccessToken().getTokenValue();

    }
}
