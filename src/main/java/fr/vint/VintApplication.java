package fr.vint;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Point d'entrée de l'application Vint.
 * <p>
 * L'annotation {@code @SpringBootApplication} active l'auto-configuration de Spring Boot
 * et le scan des composants (controllers, services, repositories...) dans le package
 * {@code fr.vint} et ses sous-packages.
 * </p>
 *
 * @author Justine, Pascal, Pacôme
 */
@SpringBootApplication
public class VintApplication {

    /**
     * Lance le serveur Tomcat embarqué et démarre l'appli.
     *
     * @param args arguments de la ligne de commande (pas utilisés)
     */
    public static void main(String[] args) {
        SpringApplication.run(VintApplication.class, args);
    }
}
