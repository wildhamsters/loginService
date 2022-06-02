package org.wildhamsters.loginservice;

import java.util.Optional;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

/**
 * @author Piotr Chowaniec
 */
@Converter
class GrantedAuthorityConverter implements AttributeConverter<GrantedAuthority, String> {

    @Override
    public String convertToDatabaseColumn(GrantedAuthority authority) {
        return Optional.ofNullable(authority)
                .map(GrantedAuthority::getAuthority)
                .orElse(null);
    }

    @Override
    public GrantedAuthority convertToEntityAttribute(String role) {
        return Optional.ofNullable(role)
                .map(SimpleGrantedAuthority::new)
                .orElse(null);
    }
}
