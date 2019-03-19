package servidor;

import java.io.IOException;
import java.net.Socket;
import java.nio.file.NoSuchFileException;

public class Index {
            
    public static void main(String[] args) {
        Servidor servidor = new Servidor();
        while(true) {
            Socket socket = servidor.ouvir();
            servidor.responder(socket);
        }
    }
    
}
