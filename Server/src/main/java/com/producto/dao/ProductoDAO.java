package com.producto.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
 
public class ProductoDAO {
    
    public void addProducto(Producto bean){
        Session session = SessionUtil.getSession();        
        Transaction tx = session.beginTransaction();
        addProducto(session,bean);        
        tx.commit();
        session.close();
        
    }
    
    private void addProducto(Session session, Producto bean){
        Producto produto = new Producto();
        
        produto.setName(bean.getName());
        produto.setCategory(bean.getCategory());
        produto.setPrice(bean.getPrice());
        
        session.save(produto);
    }
    
    public List<Producto> getProductos(){
        Session session = SessionUtil.getSession();    
        Query query = session.createQuery("from Producto");
        List<Producto> productos =  query.list();
        session.close();
        return productos;
    }

    public List<Producto> getProductosCategory(String category){
        Session session = SessionUtil.getSession();    
        Query query = session.createQuery("from Producto where category = :category");
        query.setString("category",category);
        List<Producto> productos =  query.list();
        session.close();
        return productos;
    }
 
    public int deleteProducto(int id) {
        Session session = SessionUtil.getSession();
        Transaction tx = session.beginTransaction();
        String hql = "delete from Producto where id = :id";
        Query query = session.createQuery(hql);
        query.setInteger("id",id);
        int rowCount = query.executeUpdate();
        tx.commit();
        session.close();
        return rowCount;
    }
    
    public int updateProducto(int id, Producto pro){
         if(id <=0)  
               return 0;  
         Session session = SessionUtil.getSession();
            Transaction tx = session.beginTransaction();
            String hql = "update Producto set name = :name, category=:category, price=:price where id = :id";
            Query query = session.createQuery(hql);
            query.setInteger("id",id);
            query.setString("name",pro.getName());
            query.setString("category",pro.getCategory());
            query.setInteger("price",pro.getPrice());
            int rowCount = query.executeUpdate();
            tx.commit();
            session.close();
            return rowCount;
    }
}