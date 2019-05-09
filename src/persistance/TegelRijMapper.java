package persistance;
import domein.Tegel;
import domein.TegelRij;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class TegelRijMapper 
{
        public boolean voegGebruikerToe(ArrayList<Tegel> tegelArrayList, TegelRij rij)
    {
        try (Connection conn = DriverManager.getConnection(MapperConfig.JDBC_URL)) {
            PreparedStatement queryNieuweGebruiker = conn.prepareStatement("INSERT INTO tegels VALUES (default,?, ?, ?)");
           
       
            queryNieuweGebruiker.setString(1, tegelArrayList.stream().map(Tegel::toString).collect(Collectors.joining(",")));
            queryNieuweGebruiker.setInt(2, rij.getSpelerIndex());
            queryNieuweGebruiker.setInt(3, rij.getRonde());
            queryNieuweGebruiker.executeUpdate();
            
               
            
            

            return true;
            
        } catch (SQLException ex) {
            for (Throwable t : ex) {
                t.printStackTrace();
            }
            return false;
        }
    }
        public int zoekAlleGebruikers()
    {
        int index = 0;
        try (Connection conn = DriverManager.getConnection(MapperConfig.JDBC_URL)) {
            PreparedStatement queryAlleGebruikers = conn.prepareStatement("SELECT * FROM tegels");
            try (ResultSet rs = queryAlleGebruikers.executeQuery()) {
                while (rs.next()) 
                {
                    index = rs.getInt("spelerIndex");
                    
                }
            }
        } catch (SQLException ex) {
            for (Throwable t : ex) {
                t.printStackTrace();
            }
        }

        return index ;
    }
        public ArrayList<String> zoekAlleTegels()
        {
        ArrayList<String> arrayList2 = new ArrayList<String>();
        
        try (Connection conn = DriverManager.getConnection(MapperConfig.JDBC_URL)) {
            PreparedStatement queryAlleGebruikers = conn.prepareStatement("SELECT * FROM tegels");
            try (ResultSet rs = queryAlleGebruikers.executeQuery()) {
                while (rs.next()) 
                {
                    String c = rs.getString(2);
                    String[] BijgehoudenTegelsArrayList = c.split(",");
                    ArrayList<String> arrayListB = new ArrayList<String>(Arrays.asList(BijgehoudenTegelsArrayList));
                    arrayList2= arrayListB;
                }
            }
        } catch (SQLException ex) {
            for (Throwable t : ex) {
                t.printStackTrace();
            }
        }
            
        return arrayList2;
            
        }
             public void VerwijderTegels()
    {
        try (Connection conn = DriverManager.getConnection(MapperConfig.JDBC_URL)) {
            PreparedStatement queryNieuweGebruiker = conn.prepareStatement("DELETE FROM tegels");
                      
            queryNieuweGebruiker.executeUpdate();


        } catch (SQLException ex) {
            for (Throwable t : ex) {
                t.printStackTrace();
            }
        }
    }
}
        



