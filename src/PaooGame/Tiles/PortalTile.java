package PaooGame.Tiles;

import PaooGame.Graphics.Assets;


/*! \class PortalTile extends Tile
    \brief Abstractizeaza notiunea de dala de tip portal.
 */
public class PortalTile extends Tile
{



    /*! \fn public PortalTile(int id)
        \brief Constructorul de initializare al clasei

        \param id Id-ul dalei util in desenarea hartii.
     */
    public PortalTile(int id)
    {
        /// Apel al constructorului clasei de baza
        super(Assets.portal, id);
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