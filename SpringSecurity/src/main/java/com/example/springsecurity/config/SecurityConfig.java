package com.example.springsecurity.config;


import com.example.springsecurity.service.PersonDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
//public class SecurityConfig extends WebSecurityConfiguration
public class SecurityConfig extends WebSecurityConfigurerAdapter


{

    //    private final AuthenticationProvider authenticationProvider;
//
//    public SecurityConfig(AuthenticationProvider authenticationProvider) {
//        this.authenticationProvider = authenticationProvider;
//    }
    private  final PersonDetailsService personDetailsService;

    @Autowired
    public SecurityConfig(PersonDetailsService personDetailsService) {
        this.personDetailsService = personDetailsService;
    }

//Конфигурация Spring security
@Override
protected void configure(HttpSecurity httpSecurity) throws Exception {

                        //Отключаем защиту от межсайтовой подделки запросов
                        httpSecurity.csrf().disable()

                        //Указываем что все страницы будут защищщены процессом аутентификации
                        .authorizeRequests()

                       // указываем что страница /admin доступна пользователю с ролью ADMIN
                       .antMatchers("/admin").hasRole("ADMIN")

                     // Указыаем что данные страницы доступна все пользователям
                       .antMatchers("/authentication/login","/authentication/registration","/product" ,"/error", "/img/**", "/product/info/{id}").permitAll()
//                     //Указываем что для всех страниц вызываем метод аутентификации
//                     .anyRequest().authenticated()
                       .anyRequest().hasAnyRole("USER", "ADMIN")

                        .and()
                //Указываем на какой настроить фильтр Spring Security будет отправлять нового пользователя
                        .formLogin().loginPage("/authentication/login")
                //Указываем на какой адрес настроить
                        .loginProcessingUrl("/process_login")
                //Указываем на какой адрес направить после успешной аутентификации
                        .defaultSuccessUrl("/index", true)
                //Указываем на какой адрес переход при неверной аутентификации
                        .failureUrl("/authentication/login?error")

                                .and()
                                .logout().logoutUrl("/logout").logoutSuccessUrl("/authentication/login");




}

    // Данный метод позволяет настроить аутентификацию
    protected void configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception{
//        authenticationManagerBuilder.authenticationProvider(authenticationProvider);
        authenticationManagerBuilder.userDetailsService(personDetailsService)
                                    .passwordEncoder(getPasswordEncoder());
    }

    @Bean
    public PasswordEncoder getPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
