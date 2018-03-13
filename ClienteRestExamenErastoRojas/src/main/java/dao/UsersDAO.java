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
import model.User;

/**
 *
 * @author DAW
 */
public class UsersDAO {
    
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
    GenericUrl urlUsers = new GenericUrl("http://localhost:8282/ServidorRestExamenErastoRojas/rest/RestUsers");
    ObjectMapper objectMapper = new ObjectMapper();
    

    public List<User> getAllUsersDAO() throws IOException {
        List<User> users = null;
        
        try{
   
        HttpRequest requestGoogle = requestFactory.buildGetRequest(urlUsers);          
        requestGoogle.getHeaders().set("API_KEY", "hola"/*Configuration.getInstance().getApiKey()*/);
        
        HttpResponse response = requestGoogle.execute();
        ObjectMapper mapper = new ObjectMapper();
        users = mapper.readValue(response.getContent(), 
                objectMapper.getTypeFactory().constructCollectionType(List.class, User.class));

        } catch (HttpResponseException ex) {
            Logger.getLogger(UsersDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(UsersDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return users;
    }

    public User anadirUserDAO(User usuario) {
        User user = null;
        
        try {
        
        //ObjectMapper mapper = new ObjectMapper(); 
        urlUsers.set("user",objectMapper.writeValueAsString(usuario));
        
        HttpRequest requestGoogle = requestFactory.buildPutRequest(urlUsers,new UrlEncodedContent(data));  
     
        requestGoogle.getHeaders().set("API_KEY", "hola"/*Configuration.getInstance().getApiKey()*/);
        
        user = requestGoogle.execute().parseAs(User.class);
        
        /*
        HttpResponse response = requestGoogle.execute();
        
        user = mapper.readValue(response.getContent(), User.class);
        */
        } catch (HttpResponseException ex) {
            Logger.getLogger(UsersDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(UsersDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return user;
    }

    public User actualizarUserDAO(User usuario) {
        User user = null;
        
        try {
        
        ObjectMapper mapper = new ObjectMapper(); 
        data.put("user", mapper.writeValueAsString(usuario));
        
        HttpRequest requestGoogle = requestFactory.buildPostRequest(urlUsers,new UrlEncodedContent(data));          
        requestGoogle.getHeaders().set("API_KEY", "hola"/*Configuration.getInstance().getApiKey()*/);
        
        //user = requestGoogle.execute().parseAs(User.class);
         
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
        
        urlUsers.set("user",objectMapper.writeValueAsString(usuario));
        urlUsers.set("accion", "borrar");
        HttpRequest requestGoogle = requestFactory.buildDeleteRequest(urlUsers);
        requestGoogle.getHeaders().set("API_KEY", "hola"/*Configuration.getInstance().getApiKey()*/);
        
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
        
        urlUsers.set("user",objectMapper.writeValueAsString(usuario));
        urlUsers.set("accion", "borrar2");
        HttpRequest requestGoogle = requestFactory.buildDeleteRequest(urlUsers);
        requestGoogle.getHeaders().set("API_KEY", "hola"/*Configuration.getInstance().getApiKey()*/);
        
        GenericJson gj = requestGoogle.execute().parseAs(GenericJson.class);
        
        return (String)gj.get("mensaje");
        
        } catch (HttpResponseException ex) {
            Logger.getLogger(UsersDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(UsersDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "error";
    }
    
    
}
