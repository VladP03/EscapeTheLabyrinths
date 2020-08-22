package PaooGame.Tiles;

import java.awt.*;
import java.awt.image.BufferedImage;

/*! \class Tile
    \brief Retine toate dalele intr-un vector si ofera posibilitatea regasirii dupa un id.
 */
public class Tile
{
    private static final int NO_TILES   = 10;
    public static Tile[] tiles          = new Tile[NO_TILES];                              /*!< Vector de referinte de tipuri de dale.*/

        /// De remarcat ca urmatoarele dale sunt statice si publice. Acest lucru imi permite sa le am incarcate
        /// o singura data in memorie
    private static final TileFactory factory = new TileFactory();                           /*!< Fabrica de dale*/

    public static Tile grass        = factory.makeTile(TileTypes.GRASS,0);              /*!< Dala de tip iarba*/
    public static Tile wall         = factory.makeTile(TileTypes.WALL,5);               /*!< Dala de tip perete*/
    public static Tile portal       = factory.makeTile(TileTypes.PORTAL,9);             /*!< Dala de tip portal*/
    public static Tile lava         = factory.makeTile(TileTypes.LAVA,7);               /*!< Dala de tip lava*/
    public static Tile coin         = factory.makeTile(TileTypes.COIN,1);               /*!< Dala de tip coin*/
    public static Tile speedPotion  = factory.makeTile(TileTypes.SPEEDPOTION, 2);       /*!< Dala de tip speedPotion*/

    public static final int TILE_WIDTH  = 50;                                               /*!< Latimea unei dale.*/
    public static final int TILE_HEIGHT = 50;                                               /*!< Inaltimea unei dale.*/

    protected BufferedImage img;                                                            /*!< Imaginea aferenta tipului de dala.*/
    protected final int id;                                                                 /*!< Id-ul unic aferent tipului de dala.*/



    /*! \fn public Tile(BufferedImage texture, int id)
        \brief Constructorul aferent clasei.

        \param image Imaginea corespunzatoare dalei.
        \param id Id-ul dalei.
     */
    public Tile(BufferedImage image, int idd)
    {
        img = image;
        id = idd;

        tiles[id] = this;
    }



    /*! \fn public void Update()
        \brief Actualizeaza proprietatile dalei.
     */
    public void Update()
    {

    }



    /*! \fn public void Draw(Graphics g, int x, int y)
        \brief Deseneaza in fereastra dala.

        \param g Contextul grafic in care sa se realizeze desenarea
        \param x Coordonata x in cadrul ferestrei unde sa fie desenata dala
        \param y Coordonata y in cadrul ferestrei unde sa fie desenata dala
     */
    public void Draw(Graphics g, int x, int y)
    {
            /// Desenare dala
        g.drawImage(img, x, y, TILE_WIDTH, TILE_HEIGHT, null);
    }



    /*! \fn public boolean IsSolid()
        \brief Returneaza proprietatea de dala solida (supusa coliziunilor) sau nu.
     */
    public boolean IsSolid()
    {
        return false;
    }



    /*! \fn public int GetId()
        \brief Returneaza id-ul dalei.
     */
    public int GetId()
    {
        return id;
    }
}
