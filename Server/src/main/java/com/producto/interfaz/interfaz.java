package com.producto.interfaz;

    import javax.swing.*;

import com.producto.client.ClienteApp;

import java.awt.event.*;  
    public class interfaz implements ActionListener{  
        JTextField textoNombre,textoCat,textoPre, textoID,textoFiltro;  
        JButton botonInsertar,botonEliminar,botonFiltrar;  
        JTable tabla;
        JScrollPane scrollPane;
        JPanel panel;
        String[] columnNames = {"ID","Producto","Categoria","Precio"};
        JFrame f;

        String lastcat="";

        interfaz(){  
            ClienteApp cliente=new ClienteApp();
            f= new JFrame(); 
            tabla=new JTable(cliente.getProductos(lastcat), columnNames);   
            scrollPane=new JScrollPane(tabla);
            panel=new JPanel();
            panel.add(scrollPane);
            textoNombre=new JTextField("Nombre");  
            textoNombre.setBounds(0,450,150,20);  
            textoCat=new JTextField("Categoria");  
            textoCat.setBounds(175,450,150,20); 
            textoPre=new JTextField("Precio");  
            textoPre.setBounds(350,450,150,20);  
            textoID=new JTextField("Id");  
            textoID.setBounds(0,500,150,20);
            textoFiltro=new JTextField("Filtro");  
            textoFiltro.setBounds(0,550,150,20);     
            botonInsertar=new JButton("Insertar");  
            botonInsertar.setBounds(525,450,100,20);  
            botonEliminar=new JButton("Eliminar");  
            botonEliminar.setBounds(175,500,100,20);
            botonFiltrar=new JButton("Filtrar");  
            botonFiltrar.setBounds(175,550,100,20);  
            botonInsertar.addActionListener(this);  
            botonEliminar.addActionListener(this);  
            botonFiltrar.addActionListener(this);
            f.add(textoNombre);f.add(textoCat);f.add(textoPre);f.add(textoID);f.add(textoFiltro);f.add(botonInsertar);f.add(botonEliminar);f.add(botonFiltrar);f.add(panel);  
            f.setSize(700,700);  
            f.setVisible(true); 
        }         
        public void actionPerformed(ActionEvent e) {  
            ClienteApp cliente=new ClienteApp();
        	String nombre=textoNombre.getText();  
            String categoria=textoCat.getText(); 
            String precio=textoPre.getText(); 
            String id=textoID.getText(); 
            String filtro=textoFiltro.getText();
            String result=null;
 
            if(e.getSource()==botonInsertar){  
            	int precioint=Integer.parseInt(precio);
                cliente.addProducto(nombre, categoria, precioint);
                panel.remove(scrollPane);
                tabla=new JTable(cliente.getProductos(lastcat), columnNames);   
                scrollPane=new JScrollPane(tabla);
                panel.add(scrollPane);
                panel.repaint();
                panel.revalidate();
                f.revalidate();
                f.repaint();

            }else if(e.getSource()==botonEliminar){  
                int idint=Integer.parseInt(id);
                cliente.deleteProducto(idint);
                panel.remove(scrollPane);
                tabla=new JTable(cliente.getProductos(lastcat), columnNames);   
                scrollPane=new JScrollPane(tabla);
                panel.add(scrollPane);
                panel.revalidate();
                panel.repaint();
                f.revalidate();
                f.repaint();
            }  
            else if(e.getSource()==botonFiltrar){  
                lastcat=filtro;    
                panel.remove(scrollPane);
                tabla=new JTable(cliente.getProductos(lastcat), columnNames);   
                scrollPane=new JScrollPane(tabla);
                panel.add(scrollPane);
                panel.revalidate();
                panel.repaint();
                f.revalidate();
                f.repaint();
            } 
        }  
    public static void main(String[] args) {  
        new interfaz(); 
    } }  