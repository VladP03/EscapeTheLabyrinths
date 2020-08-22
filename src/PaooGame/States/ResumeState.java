package PaooGame.States;

import PaooGame.Graphics.ImageLoader;
import PaooGame.RefLinks;

import java.awt.*;
import java.awt.image.BufferedImage;

/*! \class ResumeState extends State
    \brief Implementeaza notiunea de resume pentru joc.

    Aici setarile vor trebui salvate/incarcate intr-un/dintr-un fisier/baza de date sqlite.
 */
public class ResumeState extends State {

    private final BufferedImage image = ImageLoader.LoadImage("/textures/Menu/resume/resume.png");                  /*!< Imagine pentru alegerea optiunilor.*/
    private final BufferedImage yesSelected = ImageLoader.LoadImage("/textures/Menu/resume/resumeYesSelected.png");       /*!< Imagine pentru alegerea optiunii Yes.*/
    private final BufferedImage noSelected = ImageLoader.LoadImage("/textures/Menu/resume/resumeNoSelected.png");        /*!< Imagine pentru alegerea optiunii No.*/
    private final BufferedImage saveSelected = ImageLoader.LoadImage("/textures/Menu/resume/resumeSaveSelected.png");      /*!< Imagine pentru alegerea optiunii Save.*/


    /*! \fn public ResumeState(RefLinks refLink)
        \brief Constructorul de initializare al clasei.

        \param refLink O referinta catre un obiect "shortcut", obiect ce contine o serie de referinte utile in program.
     */
    public ResumeState(RefLinks refLink) {
        ///Apel al construcotrului clasei de baza.
        super(refLink);
    }


    /*! \fn public void Update()
        \brief Actualizeaza starea setarilor.
     */
    @Override
    public void Update() {
        if (State.isPlayingFlag() == false) {
            State.setPlayingFlag(false);
            stopTime = System.nanoTime();
            totalTime += (stopTime - startTime) / 1_000_000_000;
            setSaveFlag(true);
        }

        if (isResumeFlag() == true) {
            stopTime = System.nanoTime();
            totalTime += (stopTime - startTime) / 1_000_000_000;
            setResumeFlag(false);
        }
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
                    g.drawImage(yesSelected, 0, 0, null);
                }
            } else {
                g.drawImage(image, 0, 0, null);
            }


            if (refLink.GetGame().getMouseInput().getMx() >= 430 && refLink.GetGame().getMouseInput().getMx() < 770) {
                if (refLink.GetGame().getMouseInput().getMy() >= 687 && refLink.GetGame().getMouseInput().getMy() <= 775) {
                    g.drawImage(noSelected, 0, 0, null);
                }
            } else {
                g.drawImage(image, 0, 0, null);
            }


            if (refLink.GetGame().getMouseInput().getMx() >= 430 && refLink.GetGame().getMouseInput().getMx() < 770) {
                if (refLink.GetGame().getMouseInput().getMy() >= 575 && refLink.GetGame().getMouseInput().getMy() <= 660) {
                    g.drawImage(saveSelected, 0, 0, null);
                }
            } else {
                g.drawImage(image, 0, 0, null);
            }
        } catch (Exception e) {
            System.err.println(e);
        }

    }
}
