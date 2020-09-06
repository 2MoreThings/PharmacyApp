package com.carro.resource;
 
import java.util.List;
 
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
 
import com.carro.dao.ProductoCarro;
import com.carro.dao.ProductoCarroDAO;

 
@Path("/carro")
public class MyResource {
 
    @GET
    @Produces("application/json")
    public List<ProductoCarro> getProducto() {
        ProductoCarroDAO dao = new ProductoCarroDAO();
        List productos = dao.getProductos();
        return productos;
    }
 
    
    @POST
    @Path("/create")
    @Consumes("application/json")
    public Response addProducto(ProductoCarro prod){
        prod.setName(prod.getName());
        prod.setQuantity(prod.getQuantity());
                
        ProductoCarroDAO dao = new ProductoCarroDAO();
        dao.addProducto(prod);
        
        return Response.ok().build();
    }
    
    @PUT
    @Path("/update/{id}")
    @Consumes("application/json")
    public Response updateProducto(@PathParam("id") int id, ProductoCarro prod){
        ProductoCarroDAO dao = new ProductoCarroDAO();
        int count = dao.updateProducto(id, prod);
        if(count==0){
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
        return Response.ok().build();
    }
    
    @DELETE
    @Path("/delete/{id}")
    @Consumes("application/json")
    public Response deleteProducto(@PathParam("id") int id){
        ProductoCarroDAO dao = new ProductoCarroDAO();
        int count = dao.deleteProducto(id);
        if(count==0){
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
        return Response.ok().build();
    }
}