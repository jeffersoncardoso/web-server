/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servidor;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author jeffe
 */
public class Servidor {
    
    private Socket socket;
    private String diretorio = "C:/Users/jeffe/Desktop/Projetos Feevale/servidor-web/servidor/src/servidor/www/";

    public Servidor() {
        
        
    }
    
    public Socket ouvir() {
        try {
            ServerSocket server = new ServerSocket(8080);
            return server.accept();
        } catch (IOException ex) {
            throw new RuntimeException("Ocorreu um erro");
        }
    }
    
    public byte[] getRecurso(String recurso) throws IOException, FileNotFoundException {
        
        String arquivo = recurso.equals("/") ? "index.html" : recurso;
        
        Path path = Paths.get(diretorio + arquivo);
        Files.probeContentType(path);
        return Files.readAllBytes(path);
    }
    
    public String getTipoRecurso(String recurso) throws IOException, FileNotFoundException {
        
        String arquivo = recurso.equals("/") ? "index.html" : recurso;
        
        Path path = Paths.get(diretorio + arquivo);
        return Files.probeContentType(path);
    }
    
    
}
