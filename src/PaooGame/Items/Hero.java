package PaooGame.Items;

import java.awt.*;
import java.awt.image.BufferedImage;
import PaooGame.Graphics.ImageLoader;
import PaooGame.Maps.Map;
import PaooGame.RefLinks;
import PaooGame.Graphics.Assets;
import PaooGame.States.MenuState;
import PaooGame.States.PlayState;
import PaooGame.States.State;



/*! \class Hero extends Character
    \brief Implementeaza notiunea de erou/player (caracterul controlat de jucator).

    Elementele suplimentare pe care le aduce fata de clasa de baza sunt:
        imaginea (acest atribut poate fi ridicat si in clasa de baza)
        deplasarea
        atacul (nu este implementat momentan)
        dreptunghiul de coliziune
 */
public class Hero extends Character {

    private BufferedImage image;                        /*!< Referinta catre imaginea curenta a eroului.*/

    private final Animation playerWalkDown;             /*!< Referinta catre animatiile caracterului la apasarea tastei S.*/
    private final Animation playerWalkUp;               /*!< Referinta catre animatiile caracterului la apasarea tastei W.*/
    private final Animation playerWalkLeft;             /*!< Referinta catre animatiile caracterului la apasarea tastei A.*/
    private final Animation playerWalkRight;            /*!< Referinta catre animatiile caracterului la apasarea tastei D.*/

    private static Hero instance = null;                /*!< Singleton.*/

    private final BufferedImage coins   = ImageLoader.LoadImage("/textures/hero-show/coins.png");     /*!< Imagine pentru afisarea banutilor colectati/totali de pe mapa respectiva.*/
    private final BufferedImage Health3 = ImageLoader.LoadImage("/textures/hero-show/3life.png");     /*!< Imagine pentru afisarea a 3 vieti.*/
    private final BufferedImage Health2 = ImageLoader.LoadImage("/textures/hero-show/2life.png");     /*!< Imagine pentru alegerea a 2 vieti.*/
    private final BufferedImage Health1 = ImageLoader.LoadImage("/textures/hero-show/1life.png");     /*!< Imagine pentru alegerea a unei vieti.*/


    /*! \fn public Hero(RefLinks refLink, float x, float y)
        \brief Constructorul de initializare al clasei Hero.

        \param refLink Referinta catre obiectul shortcut (obiect ce retine o serie de referinte din program).
        \param x Pozitia initiala pe axa X a eroului.
        \param y Pozitia initiala pe axa Y a eroului.
     */
    private Hero(RefLinks refLink, float x, float y) {
        ///Apel al constructorului clasei de baza
        super(refLink, x, y, Character.DEFAULT_CREATURE_WIDTH, Character.DEFAULT_CREATURE_HEIGHT);

        ///Seteaza imaginea de start a eroului
        image = Assets.heroDown[0];

        // Pentru crearea animatiilor movementului
        playerWalkUp    = new Animation(7, Assets.heroUp[0], Assets.heroUp[1], Assets.heroUp[2], Assets.heroUp[3], Assets.heroUp[4],    Assets.heroUp[5]);
        playerWalkDown  = new Animation(7, Assets.heroDown[0], Assets.heroDown[1], Assets.heroDown[2], Assets.heroDown[3], Assets.heroDown[4], Assets.heroDown[5]);
        playerWalkLeft  = new Animation(7, Assets.heroLeft[0], Assets.heroLeft[1], Assets.heroLeft[2], Assets.heroLeft[3], Assets.heroLeft[4], Assets.heroLeft[5]);
        playerWalkRight = new Animation(7, Assets.heroRight[0], Assets.heroRight[1], Assets.heroRight[2], Assets.heroRight[3], Assets.heroRight[4], Assets.heroRight[5]);


        ///Stabilieste pozitia relativa si dimensiunea dreptunghiului de coliziune, starea implicita(normala)
        normalBounds.x = 16;    // default ->16
        normalBounds.y = 16;    // default ->16
        normalBounds.width = 16;    // default ->16
        normalBounds.height = 32;   //default ->32

        ///Stabilieste pozitia relativa si dimensiunea dreptunghiului de coliziune, starea de atac
        attackBounds.x = 10;
        attackBounds.y = 10;
        attackBounds.width = 38;
        attackBounds.height = 38;
    }


    /*! \fn public static Hero getInstance(RefLinks refLink, float x, float y)
       \brief Metoda de creare a unui obiect de tip Hero.

       \param refLink Referinta catre obiectul shortcut (obiect ce retine o serie de referinte din program).
       \param x Pozitia initiala pe axa X a eroului.
       \param y Pozitia initiala pe axa Y a eroului.
    */
    public static Hero getInstance(RefLinks refLink, float x, float y) {

        if (instance == null) {
            instance = new Hero(refLink, x, y);
        } else {
            System.out.println("Hero este singleton si este deja creat!");
        }

        return instance;
    }


    /*! \fn public void Downdate()
        \brief Actualizeaza pozitia si imaginea eroului.
     */
    @Override
    public void Update() {

        if (State.getResetFlag() == true) {
            Reset();
            refLink.GetMap().Reset();
            MenuState.setResetFlag(false);
            System.out.println("Map and Hero reseted");
            PlayState.setStartTime(System.nanoTime());
            PlayState.setStopTime(0);
            PlayState.setTotalTime(0);
        }

        ///Verifica daca a fost apasata o tasta
        GetInput();

        ///Actualizeaza pozitia
        Move();

        refLink.GetGame().GetCameraGame().centerOnCharacter(this);

        ///Actualizeaza imaginea
        if (refLink.GetKeyManager().left == true) {
            playerWalkLeft.runAnimation();
            image = playerWalkLeft.getCurrentAnimation();

        }

        if (refLink.GetKeyManager().right == true) {
            playerWalkRight.runAnimation();
            image = playerWalkRight.getCurrentAnimation();
        }

        if (refLink.GetKeyManager().up == true) {
            playerWalkUp.runAnimation();
            image = playerWalkUp.getCurrentAnimation();
        }

        if (refLink.GetKeyManager().down == true) {
            playerWalkDown.runAnimation();
            image = playerWalkDown.getCurrentAnimation();
        }
    }


    /*! \fn private void GetInput()
        \brief Verifica daca a fost apasata o tasta din cele stabilite pentru controlul eroului.
     */
    private void GetInput() {
        ///Implicit eroul nu trebuie sa se deplaseze daca nu este apasata o tasta
        xMove = 0;
        yMove = 0;

        ///Verificare apasare tasta "sus"
        if (refLink.GetKeyManager().up) {
            yMove = -speed;
        }

        ///Verificare apasare tasta "jos"
        if (refLink.GetKeyManager().down) {
            yMove = speed;
        }

        ///Verificare apasare tasta "left"
        if (refLink.GetKeyManager().left) {
            xMove = -speed;
        }

        ///Verificare apasare tasta "dreapta"
        if (refLink.GetKeyManager().right) {
            xMove = speed;
        }
    }



    /*! \fn public void Draw(Graphics g)
        \brief Randeaza/deseneaza eroul in noua pozitie.
        \brief g Contextul grafic in care trebuie efectuata desenarea eroului.
     */
    @Override
    public void Draw(Graphics g) {
        g.drawImage(image, (int) (x - refLink.GetGame().GetCameraGame().getxOffset()),
                (int) (y - refLink.GetGame().GetCameraGame().getyOffset()), width, height, null);

        try {
            g.drawImage(coins, 0, 0, null);

            g.setColor(Color.YELLOW);
            g.setFont(new Font("Arial", Font.BOLD, 25));
            if (Map.getLevel() == 1) {
                if (map1Score != 5) {
                    g.drawString(map1Score + "/5", 35, 25);
                } else {
                    g.drawString(map1Score + "/5", 35, 25);
                    g.drawString("FIND THE PORTAL", 80, 25);
                }
            } else if (Map.getLevel() == 2) {
                if (map2Score != 7) {
                    g.drawString(map2Score + "/7", 35, 25);
                } else {
                    g.drawString(map2Score + "/7", 35, 25);
                    g.drawString("FIND THE PORTAL", 80, 25);
                }
            } else if (Map.getLevel() == 3) {
                if (map3Score != 10) {
                    g.drawString(map3Score + "/10", 35, 25);
                } else {
                    g.drawString(map3Score + "/10", 35, 25);
                    g.drawString("FIND THE PORTAL", 105, 25);
                }
            }


            if (GetLife() == 3) {
                g.drawImage(Health3, 5, 30, null);
            } else if (GetLife() == 2) {
                g.drawImage(Health2, 5, 30, null);
            } else if (GetLife() == 1) {
                g.drawImage(Health1, 5, 30, null);
            }

        } catch (Exception e) {
            System.err.println("something is wrong in Hero.Draw()");
        }

        ///doar pentru debug daca se doreste vizualizarea dreptunghiului de coliziune altfel se vor comenta urmatoarele doua linii
//        g.setColor(Color.blue);
//        g.fillRect((int)(x + bounds.x), (int)(y + bounds.y), bounds.width, bounds.height);
    }


    /*! \fn public void setMapScore(int x)
    \brief Seteaza scorul in cazul in care se face load la game.
    */
    public void setMapScore(int x) {
        if (Map.getLevel() == 1) {
            map1Score = x;
        } else if (Map.getLevel() == 2) {
            map2Score = x;
        } else if (Map.getLevel() == 3) {
            map3Score = x;
        }
    }


    /*! \fn public void setTotalScore(int x)
    \brief Seteaza scorul total in cazul in care se face load la joc.
    */
    public void setTotalScore(int x) {
        totalScore = x;
    }


    /*! \fn public void SetX(float x)
    \brief Seteaza coordonatele eroului pe axa Ox.
    */
    @Override
    public void SetX(float x) {
        this.x = x;
    }


    /*! \fn public void SetX(float x)
    \brief public void SetY(float y)
    */
    @Override
    public void SetY(float y) {
        this.y = y;
    }


    /*! \fn public float getX()
    \brief Returneaza pozitia eroului pe axa Ox.
    */
    public float getX() {
        return x;
    }


    /*! \fn public float getY()
    \brief Returneaza pozitia eroului pe axa Oy.
    */
    public float getY() {
        return y;
    }


    /*! \fn public int getMap1Score()
    \brief Returneaza scorul de pe prima mapa.
    */
    public int getMap1Score() {
        return map1Score;
    }


    /*! \fn public int getMap2Score()
    \brief Returneaza scorul de pe a 2-a mapa.
    */
    public int getMap2Score() {
        return map2Score;
    }


    /*! \fn public int getMap3Score()
    \brief Returneaza scorul de pe a 3-a mapa.
    */
    public int getMap3Score() {
        return map3Score;
    }

}
