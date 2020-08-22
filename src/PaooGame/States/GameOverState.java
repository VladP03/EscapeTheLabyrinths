package PaooGame.States;

import PaooGame.Graphics.ImageLoader;
import PaooGame.RefLinks;
import java.awt.*;
import java.awt.image.BufferedImage;


/*! \class  GameOverState extends State
    \brief Implementeaza notiunea de game over pentru joc.
 */
public class GameOverState extends State {

    private final BufferedImage image = ImageLoader.LoadImage("/textures/Menu/gameOver/gameOver.png");                  /*!< Imagine pentru alegerea optiunilor.*/
    private final BufferedImage menuButtonImage = ImageLoader.LoadImage("/textures/Menu/gameOver/gameOverSelected.png");          /*!< Imagine pentru alegerea optiunii de menu.*/


    /*! \fn public GameOverState(RefLinks refLink)
        \brief Constructorul de initializare al clasei.

        \param refLink O referinta catre un obiect "shortcut", obiect ce contine o serie de referinte utile in program.
    */
    public GameOverState(RefLinks refLink) {
        super(refLink);
    }


    /*! \fn public void Update()
        \brief Actualizeaza starea setarilor.
    */
    @Override
    public void Update() {
        setResetFlag(true);
        setPlayingFlag(false);
        setLoadFlag(false);
        setSaveFlag(false);
    }


    /*! \fn public void Draw(Graphics g)
        \brief Deseneaza (randeaza) pe ecran setarile.

        \param g Contextul grafic in care trebuie sa deseneze starea setarilor pe ecran.
    */
    @Override
    public void Draw(Graphics g) {

        try {
            g.drawImage(image, 0, 0, null);

            if (refLink.GetGame().getMouseInput().getMx() >= 430 && refLink.GetGame().getMouseInput().getMx() < 770) {
                if (refLink.GetGame().getMouseInput().getMy() >= 665 && refLink.GetGame().getMouseInput().getMx() <= 755) {
                    g.drawImage(menuButtonImage, 0, 0, null);
                }
            }
        } catch (
                Exception e) {
            System.err.println(e);
        }
    }
}