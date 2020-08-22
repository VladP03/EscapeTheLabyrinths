package PaooGame.Graphics;

import PaooGame.Items.Item;
import PaooGame.RefLinks;
import PaooGame.Tiles.Tile;


/*! \class GameCamera
    \brief Clasa cu rolul de a centra GameWindows-ul pe caracter atunci cand acesta isi schimba pozitia.
 */
public class GameCamera {

    private float xOffset;          /*!< Referinta catre coordonata x.*/
    private float yOffset;          /*!< Referinta catre coordonata y.*/
    private RefLinks refLinks;      /*!< Referinta catre un obiect a carui sarcina este doar de a retine diverse referinte pentru a fi usor accesibile.*/


    /*! \fn public GameCamera(RefLinks refLinks,float xOffset, float yOffset)
        \brief Pozitioneaza camera cu eroul in mijloc

        \param xOffset pozitia initiala a camerei pe axa Ox.
        \param yOffset pozitia initiala a camerei pe axa Oy.
        \param reflink incarca referintele.
     */
    public GameCamera(RefLinks refLinks,float xOffset, float yOffset) {
        this.refLinks = refLinks;
        this.xOffset = xOffset;
        this.yOffset = yOffset;
    }


    /*! \fn public void centerOnCharacter(Item myHero)
        \brief Actualizeaza camera cand caracterul isi schimba pozitia

        \param myHero caracterul pe care se va centra camera.
    */
    public void centerOnCharacter(Item myHero) {
        xOffset = myHero.GetX() - (float)(refLinks.GetGame().GetWidth()/2);
        yOffset = myHero.GetY() - (float)(refLinks.GetGame().GetHeight()/2);
        checkBlankSpace();
    }


    /*! \fn public void checkBlankSpace()
        \brief Verifica sa nu arate colturile albe cand caracterul se afla intr-o pozitie in care acesta nu se poate afla in mijlocul ecranului.
    */
    public void checkBlankSpace() {
        if (xOffset < 0) {
            xOffset = 0;
        } else if (xOffset > refLinks.GetMap().getWidth() * Tile.TILE_WIDTH - refLinks.GetGame().GetWidth()) {
            xOffset = refLinks.GetMap().getWidth() * Tile.TILE_WIDTH - refLinks.GetGame().GetWidth();
        }

        if (yOffset < 0) {
            yOffset = 0;
        } else if (yOffset > refLinks.GetMap().getHeight() * Tile.TILE_HEIGHT - refLinks.GetGame().GetHeight()) {
            yOffset = refLinks.GetMap().getHeight() * Tile.TILE_HEIGHT - refLinks.GetGame().GetHeight();
         }
    }


    /*! \fn public float getxOffset()
        \brief Returneaza pozitia camerei pe axa Ox.
     */
    public float getxOffset() {
        return xOffset;
    }


    /*! \fn public float getyOffset()
        \brief Returneaza pozitia camerei pe axa Oy.
     */
    public float getyOffset() {
        return yOffset;
    }

}
