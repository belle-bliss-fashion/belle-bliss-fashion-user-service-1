package com.belle_bliss_fashions_user_service_1.config;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.fasterxml.jackson.databind.util.Converter;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

@Component
public class JwtAuthConvertor implements Converter<Jwt, AbstractAuthenticationToken>, org.springframework.core.convert.converter.Converter<Jwt, AbstractAuthenticationToken> {
    @Override
    public AbstractAuthenticationToken convert(Jwt jwt) {
        Collection<GrantedAuthority> roles = extractAuthorities(jwt);
        return new JwtAuthenticationToken(jwt, roles);
    }

    public Collection<GrantedAuthority> extractAuthorities(Jwt jwt){
        if (jwt.getClaim("realm_access") != null){
            Map<String , Object> realmAccess = jwt.getClaim("realm_access");
            ObjectMapper objectMapper = new ObjectMapper();
            List<String> keycloakRoles = objectMapper.convertValue(realmAccess.get("roles"),
                    new TypeReference<List<String>>() {
                    }) ;
            ArrayList<GrantedAuthority> roles = new ArrayList<>();
            for (String kr: keycloakRoles){
                roles.add(new SimpleGrantedAuthority(kr));
            }
            return roles;
        }
        return new ArrayList<>();
    }

    @Override
    public JavaType getInputType(TypeFactory typeFactory) {
        return null;
    }

    @Override
    public JavaType getOutputType(TypeFactory typeFactory) {
        return null;
    }
}
