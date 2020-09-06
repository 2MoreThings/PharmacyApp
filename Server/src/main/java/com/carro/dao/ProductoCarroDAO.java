package com.carro.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
 
public class ProductoCarroDAO {
    
    public void addProducto(ProductoCarro bean){
        Session session = SessionUtil.getSession();        
        Transaction tx = session.beginTransaction();
        addProducto(session,bean);        
        tx.commit();
        session.close();
        
    }
    
    private void addProducto(Session session, ProductoCarro bean){
        ProductoCarro produto = new ProductoCarro();
        
        produto.setName(bean.getName());
        produto.setQuantity(bean.getQuantity());
        
        session.save(produto);
    }
    
    public List<ProductoCarro> getProductos(){
        Session session = SessionUtil.getSession();    
        Query query = session.createQuery("from ProductoCarro");
        List<ProductoCarro> productos =  query.list();
        session.close();
        return productos;
    }
 
    public int deleteProducto(int id) {
        Session session = SessionUtil.getSession();
        Transaction tx = session.beginTransaction();
        String hql = "delete from ProductoCarro where id = :id";
        Query query = session.createQuery(hql);
        query.setInteger("id",id);
        int rowCount = query.executeUpdate();
        tx.commit();
        session.close();
        return rowCount;
    }
    
    public int updateProducto(int id, ProductoCarro pro){
         if(id <=0)  
               return 0;  
         Session session = SessionUtil.getSession();
            Transaction tx = session.beginTransaction();
            String hql = "update ProductoCarro set name = :name, quantity=:quantity where id = :id";
            Query query = session.createQuery(hql);
            query.setInteger("id",id);
            query.setString("name",pro.getName());
            query.setInteger("quantity",pro.getQuantity());
            int rowCount = query.executeUpdate();
            tx.commit();
            session.close();
            return rowCount;
    }
}