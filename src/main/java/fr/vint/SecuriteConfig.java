package fr.vint.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/** 
 * Configure l'outil utilisé pour protéger les mdp (hachage)
 */
@Configuration
public class SecuriteConfig {

    /**
     * Crée l'encodeur BCrypt que spring pourra utiliser dans le projet.
     *
     * @return l'encodeur de mdp (le mdp haché)
     */
    @Bean //conserve objet pour être réutilisable dans les autres classes du projet
    public PasswordEncoder passwordEncoder() {
        //utilise BCrypt pour hacher les mdp 
        return new BCryptPasswordEncoder();
    }
}
