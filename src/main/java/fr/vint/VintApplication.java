package fr.vint;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Point d'entrée de l'application Vint.
 */
@SpringBootApplication
public class VintApplication {

    /**
     * Le main permet de lancer le serveur Tomcat embarqué et démarre l'appli
     *
     * @param args arguments de la ligne de commande 
     */
    public static void main(String[] args) {
        SpringApplication.run(VintApplication.class, args); // Lance l'appli
    }
}
