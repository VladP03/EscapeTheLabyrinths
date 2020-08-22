package PaooGame.Tiles;

import PaooGame.Graphics.Assets;


/*! \class SpeedPotionTile extends Tile
    \brief Abstractizeaza notiunea de dala de tip potiune de viteza.
 */
public class SpeedPotionTile extends Tile
{



    /*! \fn public SpeedPotionTile(int id)
        \brief Constructorul de initializare al clasei

        \param id Id-ul dalei util in desenarea hartii.
     */
    public SpeedPotionTile(int id)
    {
            /// Apel al constructorului clasei de baza
        super(Assets.speedPotion, id);
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
