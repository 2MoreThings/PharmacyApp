package com.producto.resource;
 
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
 
import com.producto.dao.Producto;
import com.producto.dao.ProductoDAO;

 
@Path("/productos")
public class MyResource {
 
    @GET
    @Produces("application/json")
    public List<Producto> getProducto() {
        ProductoDAO dao = new ProductoDAO();
        List productos = dao.getProductos();
        return productos;
    }
 
    @GET
    @Path("/{cat}")
    public List<Producto> getProCat(@PathParam("cat") String cat){
        ProductoDAO dao = new ProductoDAO();
        List productos = dao.getProductosCategory(cat);
        return productos;
    }

    @POST
    @Path("/create")
    @Consumes("application/json")
    public Response addProducto(Producto prod){
        prod.setName(prod.getName());
        prod.setCategory(prod.getCategory());
        prod.setPrice(prod.getPrice());
                
        ProductoDAO dao = new ProductoDAO();
        dao.addProducto(prod);
        
        return Response.ok().build();
    }
    
    @PUT
    @Path("/update/{id}")
    @Consumes("application/json")
    public Response updateProducto(@PathParam("id") int id, Producto prod){
        ProductoDAO dao = new ProductoDAO();
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
        ProductoDAO dao = new ProductoDAO();
        int count = dao.deleteProducto(id);
        if(count==0){
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
        return Response.ok().build();
    }
}