package PaooGame.Graphics;

import java.awt.image.BufferedImage;

/*! \class Assets
    \brief Clasa incarca fiecare element grafic necesar jocului.

    Game assets include tot ce este folosit intr-un joc: imagini, sunete, harti etc.
 */
public class Assets
{
        /// Referinte catre miscarile caracterului
    public static BufferedImage[] heroUp    = new BufferedImage[6];     /*!< Referinta catre animatia caracterului cand se apasa tasta W.*/
    public static BufferedImage[] heroRight = new BufferedImage[6];     /*!< Referinta catre animatia caracterului cand se apasa tasta D.*/
    public static BufferedImage[] heroLeft  = new BufferedImage[6];     /*!< Referinta catre animatia caracterului cand se apasa tasta A.*/
    public static BufferedImage[] heroDown  = new BufferedImage[6];     /*!< Referinta catre animatia caracterului cand se apasa tasta S.*/

        /// Referinte catre elementele grafice (dale) utilizate in joc.
    public static BufferedImage wall;                                   /*!< Referinta catre dale de tip perete.*/
    public static BufferedImage grass;                                  /*!< Referinta catre dale de tip iarba.*/
    public static BufferedImage portal;                                 /*!< Referinta catre dale de tip portal.*/
    public static BufferedImage lava;                                   /*!< Referinta catre dale de tip lava.*/
    public static BufferedImage coin;                                   /*!< Referinta catre dale de tip banut.*/
    public static BufferedImage speedPotion;                            /*!< Referinta catre dale de tip potiune de viteza.*/


    /*! \fn public static void Init()
        \brief Functia initializaza referintele catre elementele grafice utilizate.

        Aceasta functie poate fi rescrisa astfel incat elementele grafice incarcate/utilizate
        sa fie parametrizate. Din acest motiv referintele nu sunt finale.
     */
    public static void Init()
    {
            /// Se creaza temporar un obiect SpriteSheet initializat prin intermediul clasei ImageLoader
        SpriteSheet character           = new SpriteSheet(ImageLoader.LoadImage("/textures/Character.png"));
        SpriteSheet items               = new SpriteSheet(ImageLoader.LoadImage("/textures/items.png"));

            /// Se obtin subimaginile corespunzatoare elementelor necesare.
            /// Animatie pentru mers in jos
        heroDown[0] = character.crop(0,0);
        heroDown[1] = character.crop(1,0);
        heroDown[2] = character.crop(2,0);
        heroDown[3] = character.crop(3,0);
        heroDown[4] = character.crop(4,0);
        heroDown[5] = character.crop(5,0);

            /// Animatie pentru mers in sus
        heroUp[0]   = character.crop(0,1);
        heroUp[1]   = character.crop(1,1);
        heroUp[2]   = character.crop(2,1);
        heroUp[3]   = character.crop(3,1);
        heroUp[4]   = character.crop(4,1);
        heroUp[5]   = character.crop(5,1);

            /// Animatie pentru stanga
        heroLeft[0] = character.crop(0,2);
        heroLeft[1] = character.crop(1,2);
        heroLeft[2] = character.crop(2,2);
        heroLeft[3] = character.crop(3,2);
        heroLeft[4] = character.crop(4,2);
        heroLeft[5] = character.crop(5,2);

            /// Animatie pentru Dreapta
        heroRight[0]= character.crop(0,3);
        heroRight[1]= character.crop(1,3);
        heroRight[2]= character.crop(2,3);
        heroRight[3]= character.crop(3,3);
        heroRight[4]= character.crop(4,3);
        heroRight[5]= character.crop(5,3);

            /// Dale pentru mapa
        grass       = items.crop(0,0);
        lava        = items.crop(1,0);
        portal      = items.crop(2,0);
        speedPotion = items.crop(3,0);
        wall        = items.crop(4,0);
        coin        = items.crop(5,0);

    }
}
