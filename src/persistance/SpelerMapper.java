package persistance;

import domein.Speler;
import domein.Tegel;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class SpelerMapper
{
    public boolean voegGebruikerToe(ArrayList<Speler> speler)
    {
        try (Connection conn = DriverManager.getConnection(MapperConfig.JDBC_URL)) {
            PreparedStatement queryNieuweGebruiker = conn.prepareStatement("INSERT INTO spelers VALUES (default,?,?,?,?,?)");
            for (Speler object : speler) 
            {
            queryNieuweGebruiker.setString(1, object.getSpelerNaam());
            queryNieuweGebruiker.setString(2, object.getDobbelsteenWaarden().stream().map(Object::toString).collect(Collectors.joining(",")));
            queryNieuweGebruiker.setString(3, object.getGekozenWaarden().stream().map(Object::toString).collect(Collectors.joining(",")));
            queryNieuweGebruiker.setString(4, object.getBijgehoudenTegels().stream().map(Object::toString).collect(Collectors.joining(",")));
            //queryNieuweGebruiker.setString(5, object.getTegelNummers().stream().map(Object::toString).collect(Collectors.joining(",")));
            queryNieuweGebruiker.setInt(5, object.getAantalDobbelstenen());
            queryNieuweGebruiker.executeUpdate();
            
            }
            
            

            return true;
        } catch (SQLException ex) {
            for (Throwable t : ex) {
                t.printStackTrace();
            }
            return false;
        }
    }
    public ArrayList<Speler> zoekAlleGebruikers()
    {
        ArrayList<Speler> spelers = new ArrayList<>();
        ArrayList<Tegel> tegels = new ArrayList<>();
        

        try (Connection conn = DriverManager.getConnection(MapperConfig.JDBC_URL)) {
            PreparedStatement queryAlleGebruikers = conn.prepareStatement("SELECT * FROM spelers");
            try (ResultSet rs = queryAlleGebruikers.executeQuery()) {
                
                while (rs.next()) {
                    String naam = rs.getString("spelerNaam");
                    Speler speler = new Speler();
                    int aantal = rs.getInt("aantalDobbelstenen");
                    
                    String s = rs.getString(3);
                    String[] WaardeArrayList = s.split(",");
                    ArrayList<String> arrayList = new ArrayList<String>(Arrays.asList(WaardeArrayList));
                    
                    String b = rs.getString(4);
                    String[] GekozeWaardeArrayList = b.split(",");
                    ArrayList<String> arrayListG = new ArrayList<String>(Arrays.asList(GekozeWaardeArrayList));
                    
                    String c = rs.getString(5);
                    String[] BijgehoudenTegelsArrayList = c.split(",");
                    ArrayList<String> arrayListB = new ArrayList<String>(Arrays.asList(BijgehoudenTegelsArrayList));

                    try {
                        for (int i = 0; i < arrayListB.size(); i++) 
                    {
                        Tegel tegel = new Tegel(Integer.valueOf(arrayListB.get(i)));
                        tegels.add(tegel);
                      
                    } 
                    } catch (Exception e) {
                    }
                   
                    
                    speler.setSpelerNaam(naam);
                    speler.setAantalDobbelstenen(aantal);
                    speler.setGekozenWaarden(arrayListG);
                    speler.setDobbelsteenWaarden(arrayList);
                    speler.setBijgehoudenTegels(tegels);
                    spelers.add(speler);
                }
            }
        } catch (SQLException ex) {
            for (Throwable t : ex) {
                t.printStackTrace();
            }
        }

        return spelers;
    }
     public void VerwijderSpelers()
    {
        try (Connection conn = DriverManager.getConnection(MapperConfig.JDBC_URL)) {
            PreparedStatement queryNieuweGebruiker = conn.prepareStatement("DELETE FROM spelers");
                      
            queryNieuweGebruiker.executeUpdate();


        } catch (SQLException ex) {
            for (Throwable t : ex) {
                t.printStackTrace();
            }
        }
    
 /*  public ArrayList<Tegel> geefalletegelsArrayList(){
    
        ArrayList<Tegel> arrayList2 = new ArrayList<String>();

        try (Connection conn = DriverManager.getConnection(MapperConfig.JDBC_URL)) {
            PreparedStatement queryAlleGebruikers = conn.prepareStatement("SELECT * FROM spelers");
            try (ResultSet rs = queryAlleGebruikers.executeQuery()) {
                
                while (rs.next()) 
                {
                    String c = rs.getString(5);
                    String[] BijgehoudenTegelsArrayList = c.split(",");
                    ArrayList<String> arrayListB = new ArrayList<String>(Arrays.asList(BijgehoudenTegelsArrayList));
                    arrayList2 = arrayListB;
                }
            }
        } catch (SQLException ex) {
            for (Throwable t : ex) {
                t.printStackTrace();
            }
        }

        return arrayList2 ;
        
   
        }*/
    }
}

