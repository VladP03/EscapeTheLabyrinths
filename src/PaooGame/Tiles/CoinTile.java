package PaooGame.Tiles;

import PaooGame.Graphics.Assets;


/*! \class CoinTile extends Tile
    \brief Abstractizeaza notiunea de dala de tip banut.
 */
public class CoinTile extends Tile
{



    /*! \fn public CoinTile(int id)
        \brief Constructorul de initializare al clasei

        \param id Id-ul dalei util in desenarea hartii.
     */
    public CoinTile(int id)
    {
            /// Apel al constructorului clasei de baza
        super(Assets.coin, id);
    }



    /*! \fn public boolean IsSolid()
        \brief Returneaza proprietatea de dala solida (supusa coliziunilor) sau nu.
    */
    @Override
    public boolean IsSolid()
    {
        return false;
    }
}
