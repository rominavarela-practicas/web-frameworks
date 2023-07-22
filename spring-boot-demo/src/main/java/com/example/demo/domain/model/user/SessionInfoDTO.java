package com.example.demo.domain.model.user;

import lombok.Builder;
import lombok.Data;
import lombok.NonNull;
import org.springframework.security.core.Authentication;

import java.util.List;

@Data
@Builder
public class SessionInfoDTO {

    /**
     * Subject of the JWT.- In the JSON Web Token (JWT) standard,
     * the "sub" (subject) claim is a string that identifies the principal that is
     * the subject of the JWT. This can be a human user, an organization, or a service.
     * @see <a href="https://auth0.com/docs/secure/tokens/json-web-tokens/json-web-token-claims#registered-claims">
     *  json-web-token-claims
     * </a>
     */
    @NonNull
    String subject;

    @NonNull
    List<String> authorities;

    @NonNull
    Authentication authentication;
}
