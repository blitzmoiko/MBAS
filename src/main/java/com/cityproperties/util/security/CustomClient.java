package com.cityproperties.util.security;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class CustomClient
        implements Serializable, UserDetails {

    private static final long serialVersionUID = 1201392234549297485L;

    private long clientId;
    private String password;
    private String username;
    private GrantedAuthority[] authorities = null;

    /**
     * Instantiates a new custom user.
     *
     * @param clientId the clientId
     * @param username the username
     * @param password the password
     * @param authorities the authorities
     */
    public CustomClient(long clientId, String username, String password, GrantedAuthority[] authorities) {
        this.clientId = clientId;
        this.password = password;
        this.username = username;
        this.authorities = authorities;
    }

    public Collection<GrantedAuthority> getAuthorities() {
        Collection<GrantedAuthority> auth = new ArrayList<GrantedAuthority>();
        for (int i = 0; i < authorities.length; i++) {
            auth.add(authorities[i]);
        }
        return auth;
    }

    /**
     * @return the clientId
     */
    public long getClientId() {
        return clientId;
    }

    /**
     * @param clientId the clientId to set
     */
    public void setClientId(long clientId) {
        this.clientId = clientId;
    }

    public String getPassword() {
        return this.password;
    }

    public String getUsername() {
        return this.username;
    }

    /**
     * For convenience the below methods return all true; In a real application
     * it is not desired, however.
     */
    public boolean isAccountNonExpired() {
        return true;
    }

    public boolean isAccountNonLocked() {
        return true;
    }

    public boolean isCredentialsNonExpired() {
        return true;
    }

    public boolean isEnabled() {
        return true;
    }
}