package servidor;

import java.io.IOException;
import java.net.Socket;
import java.nio.file.NoSuchFileException;

public class Index {
            
    public static void main(String[] args) {
        try {
            Servidor servidor = new Servidor();
            
            Socket socket = servidor.ouvir();
            HttpRequest request = new HttpRequest(socket.getInputStream());
            HttpResponse response = new HttpResponse(socket.getOutputStream());
            
            try{
                
                byte[] recurso = servidor.getRecurso(request.pagina());
                String tipo = servidor.getTipoRecurso(request.pagina());
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
    
}
