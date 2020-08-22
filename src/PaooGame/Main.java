package PaooGame;

import java.sql.*;


/*! \class Main
    \brief Where the magic begins.
 */
public class Main
{
    public static void main(String[] args)
    {

        Connection c = null;
        Statement stmt = null;

        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:Settings.db");
            c.setAutoCommit(false);
            stmt = c.createStatement();
            String sql;

            // Cream baza de date
            sql = "CREATE TABLE IF NOT EXISTS Settings" +
                    "(Name             Char(10)               NOT NULL,"+
                    "Width             INT                    NOT NULL,"+
                    "Height            INT                    NOT NULL)";
            stmt.executeUpdate(sql);

            sql = "DELETE FROM Settings";
            stmt.executeUpdate(sql);

            sql = "INSERT INTO Settings (Name, Width, Height) " +
                    "VALUES ('Escape the Labyrinths',1200,800)";
            stmt.executeUpdate(sql);


            ResultSet rs = stmt.executeQuery("SELECT * FROM Settings WHERE Name = 'Escape the Labyrinths'");
            while (rs.next()) {
                int Width = rs.getInt("Width");
                int Height = rs.getInt("Height");
                String name = rs.getString("Name");
                Game paooGame = new Game(name, Width, Height);
                paooGame.StartGame();
            }

            stmt.close();
            c.commit();
        } catch (Exception e) {
            System.err.println(e);
        }

    }
}
