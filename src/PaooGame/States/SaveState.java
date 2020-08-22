package PaooGame.States;

import PaooGame.Graphics.ImageLoader;
import PaooGame.Items.Character;
import PaooGame.Items.Hero;
import PaooGame.Maps.Map;
import PaooGame.RefLinks;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;



/*! \class SaveState extends State
    \brief Implementeaza notiunea de salvare pentru joc.

    Aici salvarile sunt intr-o baza de date sqlite.
 */
public class SaveState extends State {

    private final BufferedImage image = ImageLoader.LoadImage("/textures/Menu/save/gameSaved.png");                 /*!< Imagine pentru alegerea optiunilor.*/
    private final BufferedImage menuButtonImage = ImageLoader.LoadImage("/textures/Menu/save/gameSavedSelected.png");         /*!< Imagine pentru alegerea optiunii Menu.*/

    private Hero hero = Hero.getInstance(refLink, 0, 0);              /*!< Singleton.*/


    /*! \fn public SaveState(RefLinks refLink)
        \brief Constructorul de initializare al clasei.

        \param refLink O referinta catre un obiect "shortcut", obiect ce contine o serie de referinte utile in program.
     */
    public SaveState(RefLinks refLink) {
        super(refLink);
    }


    /*! \fn public void Update()
        \brief Actualizeaza starea setarilor.
     */
    @Override
    public void Update() {
        if (isSaveFlag() == true) {

            try {
                Connection c = null;
                Statement stmt = null;

                stopTime = System.nanoTime();
                Class.forName("org.sqlite.JDBC");
                c = DriverManager.getConnection("jdbc:sqlite:Settings.db");
                c.setAutoCommit(false);
                stmt = c.createStatement();
                String sql;

//             Cream baza de date
                sql = "CREATE TABLE IF NOT EXISTS Save" +
                        "(Level                     INT                     NOT NULL," +
                        "Map                        CHAR(5000)              NOT NULL," +
                        "Width                      INT                     NOT NULL," +
                        "Height                     INT                     NOT NULL," +
                        "Time                       INT                     NOT NULL," +
                        "HeroPositionX              REAL                    NOT NULL," +
                        "HeroPositionY              REAL                    NOT NULL," +
                        "Health                     INT                     NOT NULL," +
                        "Speed                      INT                     NOT NULL," +
                        "MapScore                   INT                     NOT NULL," +
                        "TotalScore                 INT                     NOT NULL)";
                stmt.executeUpdate(sql);

                int saveLevel = Map.getLevel();

                StringBuilder saveMap = new StringBuilder();

                int width;
                int height;
                if (saveLevel == 1) {
                    width = 20;
                    height = 30;
                    for (int i = 0; i < width; i++) {
                        for (int j = 0; j < height; j++) {
                            saveMap.append(refLink.GetMap().Map1(i, j)).append(" ");
                        }
                        saveMap.append("\n");
                    }
                } else if (saveLevel == 2) {
                    width = 30;
                    height = 41;
                    for (int i = 0; i < width; i++) {
                        for (int j = 0; j < height; j++) {
                            saveMap.append(refLink.GetMap().Map2(i, j)).append(" ");
                        }
                        saveMap.append("\n");
                    }
                } else {
                    width = 30;
                    height = 41;
                    for (int i = 0; i < width; i++) {
                        for (int j = 0; j < height; j++) {
                            saveMap.append(refLink.GetMap().Map3(i, j)).append(" ");
                        }
                        saveMap.append("\n");
                    }
                }


                long saveTime = (stopTime - startTime) / 1_000_000_000;
                int seconds = (int) saveTime;
                int minutes = 0;
                while (seconds >= 60) {
                    minutes++;
                    seconds -= 60;
                }
                System.out.println(minutes + " minutes, " + seconds + " seconds.");

                float saveHeroPositionX = hero.getX();
                float saveHeroPositionY = hero.getY();

                int saveMapScore;
                if (saveLevel == 1) {
                    saveMapScore = hero.getMap1Score();
                } else if (saveLevel == 2) {
                    saveMapScore = hero.getMap2Score();
                } else {
                    saveMapScore = hero.getMap3Score();
                }

                int saveHealth = hero.GetLife();
                int saveSpeed = hero.getSpeed();
                int saveTotalScore = Character.getTotalScore();


                sql = "DELETE FROM Save";
                stmt.executeUpdate(sql);


                sql = "INSERT INTO Save (Level, Map, Width, Height, Time, HeroPositionX, HeroPositionY, Health, Speed, MapScore, TotalScore) " +
                        "VALUES (" + saveLevel + ", '" + saveMap + "', " + refLink.GetMap().getWidth() + ", " + refLink.GetMap().getHeight() + ", " + saveTime + ", "
                        + saveHeroPositionX + "," + saveHeroPositionY + ", " + saveHealth + ", " + saveSpeed + ", " + saveMapScore + ", " + saveTotalScore + ")";
                stmt.executeUpdate(sql);


                System.out.println("Save executed succesfully!");
                stmt.close();
                c.commit();
            } catch (Exception e) {
                System.err.println(e);
            }

            setLoadFlag(false);
            setSaveFlag(false);
            setPlayingFlag(false);
            setResetFlag(false);
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
                if (refLink.GetGame().getMouseInput().getMy() >= 665 && refLink.GetGame().getMouseInput().getMy() <= 755) {
                    g.drawImage(menuButtonImage, 0, 0, null);
                }
            } else {
                g.drawImage(image, 0, 0, null);
            }
        } catch (Exception e) {
            System.err.println(e);
        }
    }
}
