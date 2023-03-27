package org.example;

import java.net.InetSocketAddress;
import com.sun.net.httpserver.HttpServer;

// Déclaration de la classe Main
public class Main {
    // Méthode main, point d'entrée du programme
    public static void main(String[] args) throws Exception {
        // Création d'une instance de HttpServer écoutant sur le port 8080
        HttpServer server = HttpServer.create(new InetSocketAddress(8000), 0);
        // Association du contexte "/" à un objet de type MyHandler qui implémente HttpHandler
        server.createContext("/", new MyHandler());
        // Définition de l'exécuteur à null (utilisation de l'exécuteur par défaut)
        server.setExecutor(null);
        // Démarrage du serveur
        server.start();
    }
}