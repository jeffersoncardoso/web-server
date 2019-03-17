/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servidor;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class HttpRequest {
    
    private String pagina;
    private String metodo;
    private String versao;
    
    public HttpRequest(InputStream in) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(in));
        this.converter(reader);
    }
    
    public String metodo() {
        return this.metodo;
    }
    
    public String pagina() {
        return this.pagina;
    }
    
    private void converter(BufferedReader reader) {
        String s;
        StringBuilder builder = new StringBuilder();
        Map<String,String> http = new HashMap<String,String>();
        
        try {            
            String[] primeiraLinha = reader.readLine().split(" ");
            this.metodo = primeiraLinha[0].trim();
            this.pagina = primeiraLinha[1].trim();
            this.versao = primeiraLinha[2].trim();
            
        } catch (IOException ex) {
            throw new RuntimeException("Ocorreu um erro");
        }
    }
    
}
