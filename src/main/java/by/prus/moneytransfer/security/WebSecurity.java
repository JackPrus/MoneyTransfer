package by.prus.moneytransfer.security;

import by.prus.moneytransfer.dao.UserDao;
import by.prus.moneytransfer.model.entity.Authority;
import by.prus.moneytransfer.service.interfaces.UserService;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

// позволяет использовать аннотацию @Secured в контроллере.
//prePostEnabled позволяет делать аннотацию @PreAuthorized и @PostAuthorized
@EnableGlobalMethodSecurity(securedEnabled = true, prePostEnabled = true)
@EnableWebSecurity
public class WebSecurity extends WebSecurityConfigurerAdapter {


    private final UserService userDetailsService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final UserDao userRepository;

    public WebSecurity(
            UserService userDetailsService,
            BCryptPasswordEncoder bCryptPasswordEncoder,
            UserDao userRepository
    ){
        this.userDetailsService = userDetailsService;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.userRepository = userRepository;
    }

    //нужен чтобы с конфигурировать некоторые точки входа веб сервиса
    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.csrf().disable().authorizeRequests()
                .antMatchers(HttpMethod.POST, SecurityConstants.SIGN_UP_URL)
                .permitAll()
                .antMatchers("/v2/api-docs", "/configuration/**", "/swagger*/**", "/webjars/**")
                .permitAll()
                //.antMatchers(HttpMethod.GET, "/users/**").authenticated() // т.к. аннотация @Secured выполняет ту же функцию
                .anyRequest().authenticated().and()
                .addFilter(getAutentificationFilter())
                .addFilter(new AuthorizationFilter(authenticationManager(),userRepository))
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        http.headers().frameOptions().disable();
    }

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder);
    }

    public AuthentificationFilter getAutentificationFilter() throws Exception {
        final AuthentificationFilter filter = new AuthentificationFilter(authenticationManager());
        filter.setFilterProcessesUrl("/login");
        return filter;
    }

    // исключения при обработке аутентификейшн фильтром.
    @Override
    public void configure(org.springframework.security.config.annotation.web.builders.WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/v2/api-docs",
                "/configuration/ui",
                "/swagger-resources/**",
                "/configuration/security",
                "/swagger-ui.html",
                "/webjars/**"
                //"/tag/**",
                //"/certificate/**"
                );
    }
}
