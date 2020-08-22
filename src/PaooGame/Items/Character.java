package PaooGame.Items;

import PaooGame.Game;
import PaooGame.Maps.Map;
import PaooGame.RefLinks;
import PaooGame.States.State;
import PaooGame.Tiles.Tile;


/*! \class Character extends Item
    \brief Defineste notiunea abstracta de caracter/individ/fiinta din joc.

    Notiunea este definita doar de viata, viteza de deplasare si distanta cu care trebuie sa se
    miste/deplaseze in urma calculelor.
 */
public abstract class Character extends Item {
    public static final int DEFAULT_LIFE = 3;                   /*!< Valoarea implicita a vietii unui caracter.*/
    public static final int DEFAULT_SPEED = 3;                  /*!< Viteza implicita a unu caracter.*/
    public static final int DEFAULT_CREATURE_WIDTH = 50;        /*!< Latimea implicita a imaginii caracterului.*/
    public static final int DEFAULT_CREATURE_HEIGHT = 50;       /*!< Inaltimea implicita a imaginii caracterului.*/

    protected int map1Score;                                    /*!< Scorul obtinut in prima mapa.*/
    protected int map2Score;                                    /*!< Scorul obtinut in cea de a 2-a mapa.*/
    protected int map3Score;                                    /*!< Scorul obtinut in cea de a 3-a mapa.*/
    public static int totalScore;                               /*!< Scorul total obtinut.*/

    protected static int life;                                  /*!< Retine viata caracterului.*/
    protected int speed;                                        /*!< Retine viteza de deplasare caracterului.*/

    protected float xMove;                                      /*!< Retine noua pozitie a caracterului pe axa X.*/
    protected float yMove;                                      /*!< Retine noua pozitie a caracterului pe axa Y.*/


    /*! \fn public Character(RefLinks refLink, float x, float y, int width, int height)
        \brief Constructor de initializare al clasei Character

        \param refLink Referinta catre obiectul shortcut (care retine alte referinte utile/necesare in joc).
        \param x Pozitia de start pa axa X a caracterului.
        \param y Pozitia de start pa axa Y a caracterului.
        \param width Latimea imaginii caracterului.
        \param height Inaltimea imaginii caracterului.
     */
    public Character(RefLinks refLink, float x, float y, int width, int height) {
        ///Apel constructor la clasei de baza
        super(refLink, x, y, width, height);
        //Seteaza pe valorile implicite pentru viata, viteza, distantele de deplasare si scorurile.
        life = DEFAULT_LIFE;
        speed = DEFAULT_SPEED;
        xMove = 0;
        yMove = 0;
        map1Score = 0;
        map2Score = 0;
        map2Score = 0;
        totalScore = 0;
    }


    /*! \fn public void Reset()
    \brief Reseteaza toate valorile pentru caracter pentru un joc nou.
    */
    public void Reset() {
        life = DEFAULT_LIFE;
        speed = DEFAULT_SPEED;
        SetX(50);
        SetY(50);
        xMove = 0;
        yMove = 0;
        map1Score = 0;
        map2Score = 0;
        map3Score = 0;
        totalScore = 0;
    }


    /*! \fn public void Move()
        \brief Modifica pozitia caracterului.
     */
    public void Move() {
        /// Daca life == 0 => game over
        if (life != 0) {
            if (refLink.GetMap().GetTileForCollision((int) x, (int) y) == Tile.lava) {
                life--;
                SetX(50);
                SetY(50);
            } else if (refLink.GetMap().GetTileForCollision((int) x, (int) y) == Tile.coin) {
                refLink.GetMap().gainCoinOrPotion((int) x, (int) y);
                if (Map.getLevel() == 1) {
                    map1Score++;
                    totalScore++;
                } else if (Map.getLevel() == 2) {
                    map2Score++;
                    totalScore++;
                } else if (Map.getLevel() == 3) {
                    map3Score++;
                    totalScore++;
                }
            } else if (refLink.GetMap().GetTileForCollision((int) x, (int) y) == Tile.speedPotion) {
                refLink.GetMap().gainCoinOrPotion((int) x, (int) y);
                SetSpeed(6);
            } else if (refLink.GetMap().GetTileForCollision((int) x, (int) y) != Tile.portal) {
                if (!refLink.GetMap().GetTileForCollision((int) (x + xMove), (int) y).IsSolid()) {
                    MoveX();
                }
                if (!refLink.GetMap().GetTileForCollision((int) (x), (int) (y + yMove)).IsSolid()) {
                    MoveY();
                }

                /// Trecerea la nivelul 2
            } else if (refLink.GetMap().GetTileForCollision((int) x, (int) y) == Tile.portal && Map.getLevel() == 1) {
                Map.setLevel(2);
                SetX(50);
                SetY(50);
                SetSpeed(3);

                /// Trecerea la nivelul 3
            } else if (refLink.GetMap().GetTileForCollision((int) x, (int) y) == Tile.portal && Map.getLevel() == 2) {
                Map.setLevel(3);
                SetX(50);
                SetY(50);
                SetSpeed(3);
            } else if (refLink.GetMap().GetTileForCollision((int) x, (int) y) == Tile.portal && Map.getLevel() == 3) {
                State.SetState(Game.getFinishState());
            }
        } else if (life == 0) {
            State.SetState(Game.getGameOverState());
        }

        if (Map.getLevel() == 1) {
            if (map1Score == 5) {
                refLink.GetMap().SetPortal();
            }
        } else if (Map.getLevel() == 2) {
            if (map2Score == 7) {
                refLink.GetMap().SetPortal();
            }
        } else if (Map.getLevel() == 3) {
            if (map3Score == 10) {
                refLink.GetMap().SetPortal();
            }
        }
    }


    /*! \fn public void MoveX()
        \brief Modifica pozitia caracterului pe axa X.
     */
    public void MoveX() {
        ///Aduna la pozitia curenta numarul de pixeli cu care trebuie sa se deplaseze pe axa X.
        x += xMove;
    }


    /*! \fn public void MoveY()
        \brief Modifica pozitia caracterului pe axa Y.
     */
    public void MoveY() {
        ///Aduna la pozitia curenta numarul de pixeli cu care trebuie sa se deplaseze pe axa Y.
        y += yMove;
    }


    /*! \fn public int GetLife()
        \brief Returneaza viata caracterului.
     */
    public int GetLife() {
        return life;
    }


    /*! \fn public int GetSpeed()
        \brief Returneaza viteza caracterului.
     */
    public float GetSpeed() {
        return speed;
    }


    /*! \fn public void SetLife(int life)
        \brief Seteaza viata caracterului.
     */
    public void SetLife(int life) {
        this.life = life;
    }


    /*! \fn public void SetSpeed(float speed)
        \brief
     */
    public void SetSpeed(int speed) {
        this.speed = speed;
    }


    /*! \fn public float GetXMove()
        \brief Returneaza distanta in pixeli pe axa X cu care este actualizata pozitia caracterului.
     */
    public float GetXMove() {
        return xMove;
    }


    /*! \fn public float GetYMove()
        \brief Returneaza distanta in pixeli pe axa Y cu care este actualizata pozitia caracterului.
     */
    public float GetYMove() {
        return yMove;
    }


    /*! \fn public void SetXMove(float xMove)
        \brief Seteaza distanta in pixeli pe axa X cu care va fi actualizata pozitia caracterului.
     */
    public void SetXMove(float xMove) {
        this.xMove = xMove;
    }


    /*! \fn public void SetYMove(float yMove)
        \brief Seteaza distanta in pixeli pe axa Y cu care va fi actualizata pozitia caracterului.
     */
    public void SetYMove(float yMove) {
        this.yMove = yMove;
    }


    /*! \fn public static int getTotalScore()
        \brief returneaza scorul total.
     */
    public static int getTotalScore() {
        return totalScore;
    }

    /*! \fn public static int getLife()
        \brief returneaza viata.
     */
    public static int getLife() {
        return life;
    }


    /*! \fn public static void setLife(int life)
    \brief Seteaza viata.
    */
    public static void setLife(int life) {
        Character.life = life;
    }


    /*! \fn public static void setLife(int life)
    \brief Returneaza viteza.
    */
    public int getSpeed() {
        return speed;
    }
}

