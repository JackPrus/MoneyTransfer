package by.prus.moneytransfer.security;


import by.prus.moneytransfer.model.entity.Authority;
import by.prus.moneytransfer.model.entity.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;

/**
 * The object returns by Spring Security when call 'loadUserByUsername(String email)' method.
 */
public class UserPrincipal implements UserDetails {

    private static final long serialVersionUID = 587972456817255L;

    private User userEntity;
    private String userId;

    public UserPrincipal(User userEntity) {
        this.userEntity = userEntity;
        this.userId = String.valueOf(userEntity.getId());
    }


    //Допустим каждый пользователь имеет максимальный объем возможностей
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return new HashSet<GrantedAuthority>(Arrays.asList(
                new SimpleGrantedAuthority(Authority.READ.name()),
                new SimpleGrantedAuthority(Authority.WRITE.name()),
                new SimpleGrantedAuthority(Authority.MONEYSEND.name())));
    }

    @Override
    public String getPassword() {
        return this.userEntity.getPassword();
    }

    @Override
    public String getUsername() {
        return this.userEntity.getName();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

}
