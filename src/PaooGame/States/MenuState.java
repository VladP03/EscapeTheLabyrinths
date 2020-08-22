package PaooGame.States;

import PaooGame.Game;
import PaooGame.Graphics.ImageLoader;
import PaooGame.RefLinks;

import java.awt.*;
import java.awt.image.BufferedImage;


/*! \class MenuState extends State
    \brief Implementeaza notiunea de menu pentru joc.
 */
public class MenuState extends State {

    private final BufferedImage image = ImageLoader.LoadImage("/textures/Menu/menu/menuImage.png");              /*!< Imagine pentru alegerea optiunilor.*/
    private final BufferedImage quitButtonImage = ImageLoader.LoadImage("/textures/Menu/menu/quitSelected.png");           /*!< Imagine pentru alegerea optiunii de quit.*/
    private final BufferedImage helpButtonImage = ImageLoader.LoadImage("/textures/Menu/menu/helpSelected.png");           /*!< Imagine pentru alegerea optiunii de help.*/
    private final BufferedImage playButtonImage = ImageLoader.LoadImage("/textures/Menu/menu/playSelected.png");           /*!< Imagine pentru alegerea optiunii de play.*/
    private final BufferedImage settingsButtonImage = ImageLoader.LoadImage("/textures/Menu/menu/settingsSelected.png");       /*!< Imagine pentru alegerea optiunii de settings.*/


    /*! \fn public MenuState(RefLinks refLink)
        \brief Constructorul de initializare al clasei.

        \param refLink O referinta catre un obiect "shortcut", obiect ce contine o serie de referinte utile in program.
     */

    public MenuState(RefLinks refLink) {
        ///Apel al constructorului clasei de baza.
        super(refLink);
    }


    /*! \fn public void Update()
        \brief Actualizeaza starea curenta a meniului.
     */
    @Override
    public void Update() {
        if (State.getPreviousState() == Game.getResumeState() || State.getPreviousState() == Game.getFinishState()) {
            State.setResetFlag(true);
        }
        if (State.getPreviousState() == Game.getResumeState()) {
            seconds = 0;
            minutes = 0;
        }

    }


    /*! \fn public void Draw(Graphics g)
        \brief Deseneaza (randeaza) pe ecran starea curenta a meniului.

        \param g Contextul grafic in care trebuie sa deseneze starea jocului pe ecran.
     */
    @Override
    public void Draw(Graphics g) {

        try {
            g.drawImage(image, 0, 0, null);

            if (refLink.GetGame().getMouseInput().getMx() >= 430 && refLink.GetGame().getMouseInput().getMx() <= 770) {
                if (refLink.GetGame().getMouseInput().getMy() >= 353 && refLink.GetGame().getMouseInput().getMy() <= 440) {
                    g.drawImage(playButtonImage, 0, 0, null);
                }
            } else {
                g.drawImage(image, 0, 0, null);
            }


            if (refLink.GetGame().getMouseInput().getMx() >= 430 && refLink.GetGame().getMouseInput().getMx() < 770) {
                if (refLink.GetGame().getMouseInput().getMy() >= 455 && refLink.GetGame().getMouseInput().getMy() <= 545) {
                    g.drawImage(helpButtonImage, 0, 0, null);
                }
            } else {
                g.drawImage(image, 0, 0, null);
            }


            if (refLink.GetGame().getMouseInput().getMx() >= 430 && refLink.GetGame().getMouseInput().getMx() < 770) {
                if (refLink.GetGame().getMouseInput().getMy() >= 560 && refLink.GetGame().getMouseInput().getMy() <= 650) {
                    g.drawImage(settingsButtonImage, 0, 0, null);
                }
            } else {
                g.drawImage(image, 0, 0, null);
            }


            if (refLink.GetGame().getMouseInput().getMx() >= 430 && refLink.GetGame().getMouseInput().getMx() < 770) {
                if (refLink.GetGame().getMouseInput().getMy() >= 665 && refLink.GetGame().getMouseInput().getMx() <= 755) {
                    g.drawImage(quitButtonImage, 0, 0, null);
                }
            } else {
                g.drawImage(image, 0, 0, null);
            }
        } catch (Exception e) {
            System.err.println(e);
        }
    }
}
