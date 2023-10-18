/**
* <h1>PostgreSQL JDBC</h1>
* Dans ce projet, nous établissons une connexion simple à notre base de données 
* PostgreSQL nommée "Vente" pour obtenir les valeurs de la table "clients" à 
* des fins de démonstration de l'utilisation de la bibliothèque JDBC.
*
* @author   Adonis Rebano
* @version  1.0
* @since    18/10/2023
*/

package com.postgresjdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.sql.DataSource;
import org.postgresql.ds.PGSimpleDataSource;

public class App {
    public static void main(String[] args) throws Exception {

        DataSource dataSource = createDataSource();
        Connection conn = dataSource.getConnection();
        getData(conn);
    }

    private static DataSource createDataSource() {
        final String url =
                "jdbc:postgresql://localhost:5432/Vente?user=postgres&password=admin";
        final PGSimpleDataSource dataSource = new PGSimpleDataSource();
        dataSource.setUrl(url);
        return dataSource;
    }

    private static void getData(Connection conn) throws SQLException {
        PreparedStatement stmt = conn.prepareStatement("select * from client");
        ResultSet rs = stmt.executeQuery();
    
        System.out.println("+-----------+-----------------+-----------------+-------------------+--------------------+");
        System.out.println("|   ID      |      Nom        |   Telephone     | Type du client    | Nombre de la Commande |");
        System.out.println("+-----------+-----------------+-----------------+-------------------+--------------------+");
    
        while (rs.next()) {
            long id = rs.getLong("id_client");
            String nom = rs.getString("nom");
            String telephone = rs.getString("telephone");
            String typeClient = rs.getString("typeclient");
            int nombreCommande = rs.getInt("nombrecommande");
    
            System.out.printf("| %9d | %15s | %15s | %17s | %20d |\n", id, nom, telephone, typeClient, nombreCommande);
        }
    
        System.out.println("+-----------+-----------------+-----------------+-------------------+--------------------+");
    }
}
