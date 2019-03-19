/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servidor;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 *
 * @author jeffe
 */
public class Servidor {
    
    private ServerSocket serverSocket;
    private Socket socket;
    private String diretorio;

    public Servidor() {
        try {
            this.serverSocket = new ServerSocket(8080);
            this.diretorio = System.getProperty("user.dir").replace("\\", "/") + "/www/";
        } catch (IOException ex) {
            ex.printStackTrace();
            throw new RuntimeException(ex.getMessage());
        }
    }
    
    public Socket ouvir() {
        try {
            return this.serverSocket.accept();
        } catch (IOException ex) {
            ex.printStackTrace();
            throw new RuntimeException(ex.getMessage());
        }
    }
    
    public void responder(Socket socket) {
        try {
            HttpRequest request = new HttpRequest(socket.getInputStream());
            HttpResponse response = new HttpResponse(socket.getOutputStream());

            try{

                byte[] recurso = this.getRecurso(request.pagina());
                String tipo = this.getTipoRecurso(request.pagina());
                response.enviar(200, recurso, tipo);

            }catch(NoSuchFileException ex) {
                response.enviar(404, ("Not Found").getBytes(), "text/html");
            }


            socket.close();
        
        }catch (IOException ex) {
            ex.printStackTrace();
            throw new RuntimeException(ex.getMessage());
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
