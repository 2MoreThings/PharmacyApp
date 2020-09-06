package com.producto.client;

import org.json.JSONArray;
import org.json.JSONObject;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
 
public class ClienteApp {
	
    private String[][] tabla;
    
    private String lastcat;
	
	public void setTabla(String[][] ID) {
		this.tabla=ID;
	}
	public String[][] getTabla() {
		if(this.tabla==null) {
			this.tabla=getProductos("");
		}
		return this.tabla;
	}
	
    public String[][] getProductos(String cat){
		
        Client c  = Client.create();
        WebResource resource = c.resource("http://localhost:8080/JerseyHibernateApp/webapi/productos/"+cat);
        
        String response = resource.get(String.class);
        JSONArray ja = new JSONArray(response);
		String[][] tablaaux=new String[100][4];
        for(int i=0;i<ja.length();i++) {
            JSONObject element=ja.getJSONObject(i);
            String[] listaux=new String[4];
            listaux[0]=Integer.toString(element.getInt("id"));
            listaux[1]=element.getString("name");
            listaux[2]=element.getString("category");	
            listaux[3]=Integer.toString(element.getInt("price"));
            tablaaux[i]=listaux;
        }
        return tablaaux;
    }
    
    public void addProducto(String name,String categoria,int precio){
    
        String input = "{\"name\":\""+name+"\",\"category\":\""+categoria+"\",\"price\":"+precio+"}";
        Client c  = Client.create();
        
        WebResource resource = c.resource("http://localhost:8080/JerseyHibernateApp/webapi/productos/create");
        
        
        ClientResponse response = resource
                .type("application/json").post(ClientResponse.class, input);
 
        // check response status code
        if (response.getStatus() != 200) {
            throw new RuntimeException("Failed : HTTP error code : "
                    + response.getStatus());
        }
        getProductos(lastcat);
    }
    
    public void deleteProducto(int id){
        Client c  = Client.create();
        WebResource resource = c.resource("http://localhost:8080/JerseyHibernateApp/webapi/productos/delete/"+id);
        ClientResponse response = resource
                .type("application/json").delete(ClientResponse.class);
        getProductos(lastcat);
    }
}