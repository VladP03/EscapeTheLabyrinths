package PaooGame.States;

import PaooGame.Game;
import PaooGame.Items.Hero;
import PaooGame.Maps.Map;
import PaooGame.RefLinks;
import java.awt.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;


/*! \class SettingsState extends State
    \brief Implementeaza notiunea de settings pentru joc.

    Aici setarile vor trebui salvate/incarcate intr-un/dintr-un fisier/baza de date sqlite.
 */
public class LoadState extends State
{

    private Hero hero = Hero.getInstance(refLink,0,0);              /*!< Singleton.*/


    /*! \fn public SettingsState(RefLinks refLink)
        \brief Constructorul de initializare al clasei.

        \param refLink O referinta catre un obiect "shortcut", obiect ce contine o serie de referinte utile in program.
     */
    public LoadState(RefLinks refLink)
    {
        ///Apel al construcotrului clasei de baza.
        super(refLink);
    }


    /*! \fn public void Update()
        \brief Actualizeaza starea setarilor.
     */
    @Override
    public void Update() {
        if (isLoadFlag() == true) {
            try {
                Connection c = null;
                Statement stmt = null;
                ResultSet rs;
                String sql;

                Class.forName("org.sqlite.JDBC");
                c = DriverManager.getConnection("jdbc:sqlite:Settings.db");
                c.setAutoCommit(false);
                stmt = c.createStatement();

                rs = stmt.executeQuery("SELECT * FROM Save");
                while (rs.next()) {
                    int loadLevel = rs.getInt("Level");
                    int loadWidth = rs.getInt("Width");
                    int loadHeight = rs.getInt("Height");
                    int[][] loadMap = new int[loadHeight][loadWidth];
                    String loadMapString = rs.getString("Map");
                    int loadTime = rs.getInt("Time");
                    double loadHeroPositionX = rs.getDouble("HeroPositionX");
                    double loadHeroPositionY = rs.getDouble("HeroPositionY");
                    int loadHealth = rs.getInt("Health");
                    int loadSpeed = rs.getInt("Speed");
                    int loadMapScore = rs.getInt("MapScore");
                    int loadTotalScore = rs.getInt("TotalScore");

                    String[] stringMap = loadMapString.replaceAll("\n", "").split(" ");
                    int i = 0, j = 0;
                    for (int s = 0; s < stringMap.length; s++) {
                        if (j == loadWidth) {
                            i++;
                            j = 0;
                        }
                        loadMap[i][j++] = Integer.parseInt(stringMap[s]);
                    }

                    // height = numarul de linii
                    // width = numarul de coloane

                    // Pasul 1 -> setam level
                    Map.setLevel(loadLevel);

                    // Pasul 2 -> setam mapa
                    Map.setFlagSpawnedMap1(false);
                    Map.setFlagSpawnedMap2(false);
                    Map.setFlagSpawnedMap3(false);
                    refLink.GetMap().setHeight(loadHeight);
                    refLink.GetMap().setWidth(loadWidth);
                    for (i = 0; i < loadHeight; i++) {
                        for (j = 0; j < loadWidth; j++) {
                            refLink.GetMap().SetMap(i, j, loadMap[i][j]);
                        }
                    }

                    // Pasul 3 -> setam hero positions
                    hero.SetX((float) loadHeroPositionX);
                    hero.SetY((float) loadHeroPositionY);

                    // Pasul 4 -> setam health la hero
                    hero.SetLife(loadHealth);

                    // Pasul 5 -> setam speed la hero
                    hero.SetSpeed(loadSpeed);

                    // Pasul 6 -> setam mapScore la hero
                    hero.setMapScore(loadMapScore);

                    // Pasul 7 -> setam TotalScore
                    hero.setTotalScore(loadTotalScore);

                    // Pasul 8 -> setam timer-ul
                    State.totalTime = loadTime;

                    State.SetState(Game.getPlayState());
                    State.startTime = System.nanoTime();
                }

            } catch (Exception e) {
                System.err.println(e);
            }

            setLoadFlag(false);
            setResetFlag(false);
            setPlayingFlag(false);
            setSaveFlag(false);
        }
    }

    /*! \fn public void Draw(Graphics g)
        \brief Deseneaza (randeaza) pe ecran setarile.

        \param g Contextul grafic in care trebuie sa deseneze starea setarilor pe ecran.
     */
    @Override
    public void Draw(Graphics g) {

    }
}