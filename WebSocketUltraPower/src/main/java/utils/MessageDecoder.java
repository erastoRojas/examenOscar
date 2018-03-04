/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.websocket.DecodeException;
import javax.websocket.Decoder;
import javax.websocket.EndpointConfig;
import model.Mensaje;

public class MessageDecoder implements Decoder.Text<Mensaje> {
 
    private static final ObjectMapper gson = new ObjectMapper();
 
    @Override
    public Mensaje decode(String mensaje) throws DecodeException {
        Mensaje meta  = null;
        try {
            gson.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
             meta = gson.readValue(mensaje,
              new TypeReference<Mensaje>() {
              });
        } catch (IOException ex) {
            Logger.getLogger(MessageDecoder.class.getName()).log(Level.SEVERE, null, ex);
        }
        return meta;
    }
 
    @Override
    public boolean willDecode(String s) {
        return (s != null);
    }
 
    @Override
    public void init(EndpointConfig endpointConfig) {
        // Custom initialization logic
    }
 
    @Override
    public void destroy() {
        // Close resources
    }
}