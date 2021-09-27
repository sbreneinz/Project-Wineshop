/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wineshop.jaxws;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.ejb.Stateless;
import javax.jws.WebMethod;
import javax.jws.WebService;

@WebService
@Stateless
public class Product {

    // Get all products
    @WebMethod(operationName = "getProducts")
    public String getAllProducts() {
        StringBuilder productStringList = new StringBuilder();
        
        try (Connection conn = DriverManager.getConnection("jdbc:derby://localhost:1527/WineShopDB", "root", "sbreneinz")){
            Statement stmt = conn.createStatement();
            String query = "SELECT * FROM PRODUCT";
            ResultSet rs = stmt.executeQuery(query);
            while(rs.next()) {
                int id = rs.getInt(1);
                String name = rs.getString(2);
                String description = rs.getString(3);
                double price = rs.getDouble(4);
                String type = rs.getString(5);
                int year = rs.getInt(6);
                String country = rs.getString(7);
                String region = rs.getString(8);
                String producer = rs.getString(9);
                productStringList.append("Id: " + id +
                                    " Name: " + name + 
                                    " Description: " + description + 
                                    " Price: " + String.valueOf(price) + 
                                    " Type: " + type +
                                    " Year: " + String.valueOf(year) +
                                    " Country: " + country +
                                    " Region: " + region + 
                                    " Producer: " + producer +
                                    " \n");
            }
        } catch (Exception e) {
            
        }
        return productStringList.toString();
    }
    
    // Get Product by Product ID
    @WebMethod(operationName = "getProductById")
    public String getProductById(String productId) {
        String product = "";
        
        try (Connection conn = DriverManager.getConnection("jdbc:derby://localhost:1527/WineShopDB", "root", "sbreneinz")){
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM PRODUCT WHERE product_id = " + Integer.valueOf(productId));
            ResultSet rs = stmt.executeQuery();
            while(rs.next()) {
                int id = rs.getInt(1);
                String name = rs.getString(2);
                String description = rs.getString(3);
                double price = rs.getDouble(4);
                String type = rs.getString(5);
                int year = rs.getInt(6);
                String country = rs.getString(7);
                String region = rs.getString(8);
                String producer = rs.getString(9);
                product = "########################\n"
                        + "Id: " + String.valueOf(id) + "\n" 
                        + "Name: " + name + "\n"
                        + "Description: " + description + "\n"
                        + "Price: " + String.valueOf(price) + "€\n"
                        + "Type: " + type + "\n"
                        + "Year: " + String.valueOf(year) + "\n"
                        + "Country: " + country + "\n"
                        + "Region: " + region + "\n"
                        + "Producer: " + producer + "\n"
                        + "########################";
            }
        } catch (Exception e) {
            
        }
        return product;
    }
    
    // Get product by Name  
    @WebMethod(operationName = "getProductsByName")
    public String getProductByName(String productName) {
        StringBuilder productStringList = new StringBuilder();
        
        try (Connection conn = DriverManager.getConnection("jdbc:derby://localhost:1527/WineShopDB", "root", "sbreneinz")){
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM PRODUCT WHERE Prod_Name = ?");
            stmt.setString(1, productName);
            ResultSet rs = stmt.executeQuery();
            
            while(rs.next()) {
                int id = rs.getInt(1);
                String name = rs.getString(2);
                String description = rs.getString(3);
                double price = rs.getDouble(4);
                String type = rs.getString(5);
                int year = rs.getInt(6);
                String country = rs.getString(7);
                String region = rs.getString(8);
                String producer = rs.getString(9);
                productStringList.append("Id: " + id +
                                    " Name: " + name + 
                                    " Description: " + description + 
                                    " Price: " + String.valueOf(price) + 
                                    " Type: " + type +
                                    " Year: " + String.valueOf(year) +
                                    " Country: " + country +
                                    " Region: " + region + 
                                    " Producer: " + producer +
                                    " \n");
            }
        } catch (Exception e) {
            
        }
        return productStringList.toString();
    }
}
