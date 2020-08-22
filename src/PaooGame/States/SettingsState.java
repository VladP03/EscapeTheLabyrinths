package PaooGame.States;

import PaooGame.Graphics.ImageLoader;
import PaooGame.RefLinks;

import java.awt.*;
import java.awt.image.BufferedImage;

/*! \class SettingsState extends State
    \brief Implementeaza notiunea de settings pentru joc.
    Aici setarile vor trebui salvate/incarcate intr-un/dintr-un fisier/baza de date sqlite.
 */
public class SettingsState extends State {

    private final BufferedImage image = ImageLoader.LoadImage("/textures/Menu/Settings/settings.png");
    ;                   /*!< Imagine pentru alegerea optiunilor.*/
    private final BufferedImage loadSelection = ImageLoader.LoadImage("/textures/Menu/Settings/settingsLoad.png");                /*!< Imagine pentru alegerea optiunii Load.*/
    private final BufferedImage resolutionSelection = ImageLoader.LoadImage("/textures/Menu/Settings/settingsResolution.png");          /*!< Imagine pentru alegerea optiunii Resolution.*/
    private final BufferedImage menuSelection = ImageLoader.LoadImage("/textures/Menu/Settings/settingsMenu.png");                /*!< Imagine pentru alegerea optiunii Menu.*/


    /*! \fn public SettingsState(RefLinks refLink)
        \brief Constructorul de initializare al clasei.

        \param refLink O referinta catre un obiect "shortcut", obiect ce contine o serie de referinte utile in program.
     */
    public SettingsState(RefLinks refLink) {
        ///Apel al construcotrului clasei de baza.
        super(refLink);
    }


    /*! \fn public void Update()
        \brief Actualizeaza starea setarilor.
     */
    @Override
    public void Update() {

    }


    /*! \fn public void Draw(Graphics g)
        \brief Deseneaza (randeaza) pe ecran setarile.

        \param g Contextul grafic in care trebuie sa deseneze starea setarilor pe ecran.
     */
    @Override
    public void Draw(Graphics g) {
        try {
            g.drawImage(image, 0, 0, null);

            if (refLink.GetGame().getMouseInput().getMx() >= 430 && refLink.GetGame().getMouseInput().getMx() <= 770) {
                if (refLink.GetGame().getMouseInput().getMy() >= 460 && refLink.GetGame().getMouseInput().getMy() <= 550) {
                    g.drawImage(resolutionSelection, 0, 0, null);
                }
            } else {
                g.drawImage(image, 0, 0, null);
            }


            if (refLink.GetGame().getMouseInput().getMx() >= 430 && refLink.GetGame().getMouseInput().getMx() < 770) {
                if (refLink.GetGame().getMouseInput().getMy() >= 575 && refLink.GetGame().getMouseInput().getMy() <= 660) {
                    g.drawImage(loadSelection, 0, 0, null);
                }
            } else {
                g.drawImage(image, 0, 0, null);
            }

            if (refLink.GetGame().getMouseInput().getMx() >= 430 && refLink.GetGame().getMouseInput().getMx() < 770) {
                if (refLink.GetGame().getMouseInput().getMy() >= 687 && refLink.GetGame().getMouseInput().getMy() <= 775) {
                    g.drawImage(menuSelection, 0, 0, null);
                }
            } else {
                g.drawImage(image, 0, 0, null);
            }
        } catch (Exception e) {
            System.err.println(e);
        }
    }
}
