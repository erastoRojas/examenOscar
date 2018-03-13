/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.api.client.http.GenericUrl;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpRequestFactory;
import com.google.api.client.http.HttpRequestInitializer;
import com.google.api.client.http.HttpResponse;
import com.google.api.client.http.HttpResponseException;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.UrlEncodedContent;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.GenericJson;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.JsonObjectParser;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.util.GenericData;
import config.Configuration;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Caja;
import model.Cosa;
import model.User;

/**
 *
 * @author DAW
 */
public class CajasDAO {
    HttpTransport HTTP_TRANSPORT = new NetHttpTransport();
    JsonFactory JSON_FACTORY = new JacksonFactory();
    
    HttpRequestFactory requestFactory
          = HTTP_TRANSPORT.createRequestFactory(new HttpRequestInitializer() {
              @Override
              public void initialize(HttpRequest request) {
                  request.setParser(new JsonObjectParser(JSON_FACTORY));
              }
          });

    public GenericData data = new GenericData();
    GenericUrl urlCajas = new GenericUrl("http://localhost:8282/ServidorRestExamenErastoRojas/rest/RestCajas");
    GenericUrl urlCosas = new GenericUrl("http://localhost:8282/ServidorRestExamenErastoRojas/rest/RestCosas");
    ObjectMapper objectMapper = new ObjectMapper();
    

    public List<Caja> getAllCajas(User u) throws IOException {
        List<Caja> cajas = null;
        
        try{
            
        urlCajas.set("user",objectMapper.writeValueAsString(u));
        
        HttpRequest requestGoogle = requestFactory.buildGetRequest(urlCajas);          
        requestGoogle.getHeaders().set("API_KEY", "hola");
        
        HttpResponse response = requestGoogle.execute();
        ObjectMapper mapper = new ObjectMapper();
        cajas = mapper.readValue(response.getContent(), 
                objectMapper.getTypeFactory().constructCollectionType(List.class, Caja.class));

        } catch (HttpResponseException ex) {
            Logger.getLogger(CajasDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(CajasDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return cajas;
    }

    public Caja anadirCajaDAO(User usuario, Caja ca) {
        Caja caja = null;
        
        try {
            
        urlCajas.set("user",objectMapper.writeValueAsString(usuario));
        urlCajas.set("caja",objectMapper.writeValueAsString(ca));
        
        HttpRequest requestGoogle = requestFactory.buildPutRequest(urlCajas, null);          
        requestGoogle.getHeaders().set("API_KEY", "hola");
        
        caja = requestGoogle.execute().parseAs(Caja.class);

        } catch (HttpResponseException ex) {
            Logger.getLogger(CajasDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(CajasDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return caja;
    }
    
    public Caja anadirCosaCajaDAO(User usuario, Cosa cosa, Caja ca) {
        Caja caja = null;
        
        try {
         
        urlCosas.set("user",objectMapper.writeValueAsString(usuario));
        urlCosas.set("caja",objectMapper.writeValueAsString(ca));
        urlCosas.set("cosa",objectMapper.writeValueAsString(cosa));
        
        HttpRequest requestGoogle = requestFactory.buildPutRequest(urlCosas,null);          
        requestGoogle.getHeaders().set("API_KEY", "hola");
        
        HttpResponse response = requestGoogle.execute();
        
        caja = requestGoogle.execute().parseAs(Caja.class);
        
        } catch (HttpResponseException ex) {
            Logger.getLogger(CajasDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(CajasDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return caja;
    }
    
    public List<Cosa> getAllCosas(User usuario, Caja caja) {
        List<Cosa> cosas = null;
        
        try{
            
        urlCosas.set("user",objectMapper.writeValueAsString(usuario));
        urlCosas.set("caja",objectMapper.writeValueAsString(caja));
        
        HttpRequest requestGoogle = requestFactory.buildGetRequest(urlCosas);          
        requestGoogle.getHeaders().set("API_KEY", Configuration.getInstance().getApiKey());
        
        HttpResponse response = requestGoogle.execute();
        ObjectMapper mapper = new ObjectMapper();
        cosas = mapper.readValue(response.getContent(), 
                objectMapper.getTypeFactory().constructCollectionType(List.class, Cosa.class));

        } catch (HttpResponseException ex) {
            Logger.getLogger(UsersDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(UsersDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return cosas;
    }
    
    
    public User actualizarUserDAO(User usuario) {
        User user = null;
        
        try {
        
        ObjectMapper mapper = new ObjectMapper(); 
        data.put("user", mapper.writeValueAsString(usuario));
        
        HttpRequest requestGoogle = requestFactory.buildPostRequest(urlCajas,new UrlEncodedContent(data));          
        requestGoogle.getHeaders().set("API_KEY", Configuration.getInstance().getApiKey());
        
        HttpResponse response = requestGoogle.execute();
        
        user = mapper.readValue(response.getContent(), User.class);
        
        } catch (HttpResponseException ex) {
            Logger.getLogger(UsersDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(UsersDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return user;
    }

    public String borrarUserDAO(User usuario) {
        User user = null;
        
        try {
        
        urlCajas.set("user",objectMapper.writeValueAsString(usuario));
        urlCajas.set("accion", "borrar");
        HttpRequest requestGoogle = requestFactory.buildDeleteRequest(urlCajas);
        requestGoogle.getHeaders().set("API_KEY", Configuration.getInstance().getApiKey());
        
        GenericJson gj = requestGoogle.execute().parseAs(GenericJson.class);
        
        return (String)gj.get("mensaje");
        
        } catch (HttpResponseException ex) {
            Logger.getLogger(UsersDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(UsersDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "error";
    }

    public String borrarUser2DAO(User usuario) {
        User user = null;
        
        try {
        
        urlCajas.set("user",objectMapper.writeValueAsString(usuario));
        urlCajas.set("accion", "borrar2");
        HttpRequest requestGoogle = requestFactory.buildDeleteRequest(urlCajas);
        requestGoogle.getHeaders().set("API_KEY", Configuration.getInstance().getApiKey());
        
        GenericJson gj = requestGoogle.execute().parseAs(GenericJson.class);
        
        return (String)gj.get("mensaje");
        
        } catch (HttpResponseException ex) {
            Logger.getLogger(UsersDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(UsersDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "error";
    }

    public Caja anadirCantidadCosaDAO(User usuario, Cosa cosa, Caja ca, int cantidad) {
        Caja caja = null;
        
        try {
        
        urlCosas.set("user",objectMapper.writeValueAsString(usuario));
        urlCosas.set("caja",objectMapper.writeValueAsString(ca));
        urlCosas.set("cosa",objectMapper.writeValueAsString(cosa));
        urlCosas.set("cantidad",objectMapper.writeValueAsString(cantidad));
        
        HttpRequest requestGoogle = requestFactory.buildPutRequest(urlCosas,null);          
        requestGoogle.getHeaders().set("API_KEY", "hola");
        
        HttpResponse response = requestGoogle.execute();
        
        caja = requestGoogle.execute().parseAs(Caja.class);
        
        } catch (HttpResponseException ex) {
            Logger.getLogger(CajasDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(CajasDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return caja;
    }

    

}
