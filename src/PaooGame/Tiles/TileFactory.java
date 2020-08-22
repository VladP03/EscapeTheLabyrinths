package PaooGame.Tiles;


/*! \class TileFactory
    \brief Implementarea sablonului de proiectare de tip Metoda Fabrica.
 */
public class TileFactory {

    /*! \fn public Tile makeTile(TileTypes tileType, int id)
        \brief Returneaza un obiect de tip dala cu id-ul corespunzator.
    */
    public Tile makeTile(TileTypes tileType, int id) {

        switch(tileType) {

            case WALL: return new WallTile(id);
            case GRASS: return new GrassTile(id);
            case PORTAL: return new PortalTile(id);
            case LAVA: return new LavaTile(id);
            case COIN: return new CoinTile(id);
            case SPEEDPOTION: return new SpeedPotionTile(id);

            default: return null;
        }
    }
}
