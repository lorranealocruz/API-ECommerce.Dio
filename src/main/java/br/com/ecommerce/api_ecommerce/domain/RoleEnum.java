package br.com.ecommerce.api_ecommerce.domain;

import org.springframework.security.core.GrantedAuthority;


public enum RoleEnum implements GrantedAuthority {

    CLIENTE("ROLE_CLIENTE"),
    ADMIN("ROLE_ADMIN");

    private final String authority;

    RoleEnum(String authority) {
        this.authority = authority;
    }
    
    @Override
    public String getAuthority() {
        return authority;
    }
}
