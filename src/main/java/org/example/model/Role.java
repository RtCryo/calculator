package org.example.model;

import lombok.AllArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Set;
import java.util.stream.Collectors;

@AllArgsConstructor
public enum Role {
    USER(Set.of(Permission.USER_READ, Permission.USER_WRITE)),
    DEVELOPER(Set.of(Permission.USER_READ, Permission.USER_WRITE,Permission.DEVELOPERS_READ,Permission.DEVELOPERS_WRITE)),
    ADMIN(Set.of(Permission.USER_READ, Permission.USER_WRITE,Permission.DEVELOPERS_READ, Permission.DEVELOPERS_WRITE, Permission.ADMIN_READ)),
    SUPER_ADMIN(Set.of(Permission.USER_READ, Permission.USER_WRITE,Permission.DEVELOPERS_READ, Permission.DEVELOPERS_WRITE, Permission.ADMIN_READ, Permission.ADMIN_WRITE));

    private final Set<Permission> permissions;

    public Set<Permission> getPermissions() {
        return permissions;
    }

    public Set<SimpleGrantedAuthority> getAuthorities() {
        return getPermissions().stream()
                .map(permission -> new SimpleGrantedAuthority(permission.getPermission()))
                .collect(Collectors.toSet());
    }
}
