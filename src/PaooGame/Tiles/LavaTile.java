package PaooGame.Tiles;

import PaooGame.Graphics.Assets;


/*! \class LavaTile extends Tile
    \brief Abstractizeaza notiunea de dala de tip lava.
 */
public class LavaTile extends Tile
{



    /*! \fn public LavaTile(int id)
        \brief Constructorul de initializare al clasei

        \param id Id-ul dalei util in desenarea hartii.
     */
    public LavaTile(int id)
    {
        /// Apel al constructorului clasei de baza
        super(Assets.lava, id);
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