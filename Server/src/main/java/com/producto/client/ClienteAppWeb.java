package com.producto.client;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import org.json.JSONArray;
import org.json.JSONObject;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
 
@ManagedBean(name = "ClienteAppWeb", eager = true)
@SessionScoped
public class ClienteAppWeb implements Serializable{
	private static final long serialVersionUID = 3L;

	@ManagedProperty(value = "#{tabla}")
	private String tabla;
    
    private String lastcat;
	
	public void setTabla(String ID) {
		this.tabla=ID;
	}
	public String getTabla() {
		if(this.tabla==null) {
			this.getProductos("");
		}
		return this.tabla;
	}
	
    public void getProductos(String cat){
		
        Client c  = Client.create();
        WebResource resource = c.resource("http://localhost:8080/JerseyHibernateApp/webapi/productos/"+cat);
        lastcat=cat;
        String response = resource.get(String.class);
        JSONArray ja = new JSONArray(response);
		String tablaaux="<table style=\"width:100%\">\n" + 
        		"  <tr>\n" + 
        		"    <th>Id</th>\n" + 
        		"    <th>Nombre</th>\n" + 
                "    <th>Categoría</th>\n" + 
                "    <th>Precio</th>\n" + 
        		"  </tr>\n";
        for(int i=0;i<ja.length();i++) {
        	JSONObject element=ja.getJSONObject(i);
        	tablaaux=tablaaux+
        			"<tr>\n"+
        			"<td>"+element.getInt("id")+"</td>\n"+
        			"<td>"+element.getString("name")+"</td>\n"+
                    "<td>"+element.getString("category")+"</td>\n"+
                    "<td>"+element.getInt("price")+"</td>\n"+
        			"</tr>\n";	
        }
        tablaaux=tablaaux+"</table>\n";
        this.setTabla(tablaaux);
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