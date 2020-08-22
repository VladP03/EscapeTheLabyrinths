package PaooGame.States;

import PaooGame.Graphics.ImageLoader;
import PaooGame.Items.Character;
import PaooGame.Items.Hero;
import PaooGame.RefLinks;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;



/*! \class  FinishState extends State
    \brief Implementeaza notiunea de finish pentru joc.

    Aici unii parametrii sunt salvate intr-o baza de date sql.
 */
public class FinishState extends State {

    private final BufferedImage image = ImageLoader.LoadImage("/textures/Menu/finish/finish.png");              /*!< Imagine pentru alegerea optiunilor.*/
    private final BufferedImage menuButtonImage = ImageLoader.LoadImage("/textures/Menu/finish/finishSelected.png");      /*!< Imagine pentru alegerea optiunii de menu.*/

    private long seconds;                       /*!< Atribut pentru afisarea numarului de seconde petrecute in joc.*/
    private long minutes;                       /*!< Atribut pentru afisarea numarului de minute petrecute in joc.*/


    /*! \fn public GameOverState(RefLinks refLink)
        \brief Constructorul de initializare al clasei.

        \param refLink O referinta catre un obiect "shortcut", obiect ce contine o serie de referinte utile in program.
    */
    public FinishState(RefLinks refLink) {
        super(refLink);
    }


    /*! \fn public void Update()
        \brief Actualizeaza starea setarilor.
    */
    @Override
    public void Update() {
        if (State.isFinishFlag() == true) {
            try {
                Connection c = null;
                Statement stmt = null;
                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd 'at' HH:mm:ss z");
                Date date = new Date(System.currentTimeMillis());

                Class.forName("org.sqlite.JDBC");
                c = DriverManager.getConnection("jdbc:sqlite:Settings.db");
                c.setAutoCommit(false);
                stmt = c.createStatement();
                String sql;

//             Cream baza de date
                sql = "CREATE TABLE IF NOT EXISTS Records" +
                        "(Date               CHAR(30)                NOT NULL," +
                        "Coins               INT                     NOT NULL," +
                        "Health              INT                     NOT NULL," +
                        "Time                CHAR(30)                NOT NULL)";
                stmt.executeUpdate(sql);

                String myTimer = minutes + " minutes, " + seconds + " seconds";

                sql = "INSERT INTO Records (Date, Coins, Health, Time) " +
                        "VALUES ('" + formatter.format(date) + "'," + Character.getTotalScore() + ", " + Hero.getLife() + ", '" + myTimer + "')";
                stmt.executeUpdate(sql);

                stmt.close();
                c.commit();
            } catch (Exception e) {
                System.err.println(e);
            }

//            System.out.println("Data Base created succesfully");
            setFinishFlag(false);

            setResetFlag(false);
            setLoadFlag(false);
            setSaveFlag(false);
            setPlayingFlag(false);
        }
    }


    /*! \fn public void Draw(Graphics g)
        \brief Deseneaza (randeaza) pe ecran setarile.

        \param g Contextul grafic in care trebuie sa deseneze starea setarilor pe ecran.
    */
    @Override
    public void Draw(Graphics g) {

        try {
            g.drawImage(image, 0, 0, null);

            if (refLink.GetGame().getMouseInput().getMx() >= 430 && refLink.GetGame().getMouseInput().getMx() < 770) {
                if (refLink.GetGame().getMouseInput().getMy() >= 665 && refLink.GetGame().getMouseInput().getMx() <= 755) {
                    g.drawImage(menuButtonImage, 0, 0, null);
                }
            }

            g.setColor(Color.WHITE);
            g.setFont(new Font("Arial", Font.BOLD, 50));
            g.drawString("You've earned " + Character.getTotalScore() + " coins", 300, 550);

            if (isPlayingFlag()) {
                stopTime = System.nanoTime();
                long nonNanoTotalTime = (stopTime - startTime) / 1_000_000_000;

                State.totalTime += nonNanoTotalTime;
                seconds = State.totalTime;
                minutes = 0;
                while (seconds >= 60) {
                    minutes++;
                    seconds -= 60;
                }
                State.setPlayingFlag(false);
            }
            g.drawString("Time spent: " + minutes + " minutes, " + seconds + " seconds", 180, 620);
        } catch (Exception e) {
            System.err.println(e);
        }
    }
}