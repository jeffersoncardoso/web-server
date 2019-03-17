/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servidor;

import java.io.IOException;
import java.io.OutputStream;

/**
 *
 * @author jeffe
 */
public class HttpResponse {
    
    private OutputStream output;
    
    HttpResponse(OutputStream output) throws IOException {
        this.output = output;
        
        this.output.write(("HTTP/1.1 ").getBytes());
    }
    
    public void enviar(int statusCode, byte[] body, String contentType) throws IOException {
        this.enviarStatus(statusCode);
        this.enviarCorpo(body, contentType);
        
        this.output.close();
    }
    
    private void enviarStatus(int code) throws IOException {
        switch(code)
        {
            case 200:
                this.output.write(("200 OK").getBytes());
                break;
        }
    }
    
    private void enviarCorpo(byte[] body, String contentType) throws IOException {
        this.output.write(("Content-Type: " + contentType + "\n").getBytes());
        this.output.write(("\n").getBytes());
        
        this.output.write(body);
    }
    
}
