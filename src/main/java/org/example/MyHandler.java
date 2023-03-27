package org.example;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

// La classe CustomHandler implémente l'interface HttpHandler, qui définit la méthode "handle"
// pour gérer les requêtes HTTP
// Définition de la classe MyHandler qui implémente HttpHandler
class MyHandler implements HttpHandler {
    // Méthode handle pour gérer les requêtes HTTP
    @Override
    public void handle(HttpExchange t) throws IOException {
        // Obtention du chemin de la requête
        String path = t.getRequestURI().getPath();
        String response;
        // Gestion du cas où le chemin est "/" ou "/index.html"
        if ("/".equals(path) || "/index.html".equals(path) ) {
            response = "index.html";
        }
        else if ("/styles.css".equals(path)) {
            response = "styles.css";
        }
        // Gestion du cas où le chemin est "/suite.html"
        else if ("/formulaires.html".equals(path)) {
            response = "formulaires.html";
        }
        else if ("/formulaires.css".equals(path)) {
            response = "formulaires.css";
        }
        else if ("/mentionslegales.html".equals(path)) {
            response = "mentionslegales.html";
        }
        // Gestion du cas où le chemin n'est pas reconnu (404)
        else {
            // Envoi d'une réponse avec un code 404
            t.sendResponseHeaders(404, 0);
            // Fermeture de la connexion
            t.close();
            // Sortie de la méthode
            return;
        }
        // Obtention du flux d'entrée associé au fichier de réponse
        InputStream inputStream = getClass().getResourceAsStream("/" + response);
        // Création d'un tableau de bytes pour stocker le contenu du fichier de réponse
        byte[] bytes = new byte[inputStream.available()];
        // Lecture du contenu du fichier de réponse dans le tableau de bytes
        inputStream.read(bytes);
        // Envoi d'une réponse avec un code 200 et la longueur du contenu de la réponse
        t.sendResponseHeaders(200, bytes.length);
        // Obtention du flux de sortie pour écrire la réponse
        OutputStream outputStream = t.getResponseBody();
        // Écriture du contenu de la réponse dans le flux de sortie
        outputStream.write(bytes);
        // Fermeture du flux de sortie
        outputStream.close();
    }
}
