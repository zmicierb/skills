package com.barysevich.authorization.api;

import com.barysevich.project.tokens.SessionToken;
import com.barysevich.project.tokens.UserToken;

/**
  Результат авторизации
 */
public class AuthorizationResult {

    private Long id;

    private String failReason;

    private UserToken userToken;

    private SessionToken sessionToken;

//    private Set<String> authorities;

    public AuthorizationResult(Long id,
                               UserToken userToken,
                               String failReason,
                               SessionToken sessionToken)
//                               Set<String> authorities
    {
        this.id = id;
        this.failReason = failReason;
        this.userToken = userToken;
        this.sessionToken = sessionToken;
//        this.authorities = authorities;
    }

    public static AuthorizationResult createSuccess(final Long id,
                                                    final UserToken userToken,
                                                    final SessionToken sessionToken)
//                                                    final Set<String> authorities
    {
        return new AuthorizationResult(id, userToken, null, sessionToken);
    }


    public static AuthorizationResult createFailed(final Long id, final String failReason)
    {
        return new AuthorizationResult(id, null, failReason, null);
    }


    public static AuthorizationResult createFailed(final String failReason)
    {
        return new AuthorizationResult(null, null, failReason, null);
    }

    public boolean isSuccess()
    {
        return failReason == null;
    }

    public Long getId() {
        return id;
    }

    public String getFailReason() {
        return failReason;
    }

    public UserToken getUserToken() {
        return userToken;
    }

    public SessionToken getSessionToken() {
        return sessionToken;
    }

//    public Set<String> getAuthorities() {
//        return authorities;
//    }

    @Override
    public String toString() {
        return "AuthorizationResult{" +
                "id=" + id +
                ", failReason='" + failReason + '\'' +
                ", userToken=" + userToken +
                ", sessionToken=" + sessionToken +
//                ", authorities=" + authorities +
                '}';
    }
}
