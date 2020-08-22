package PaooGame.Input;

import PaooGame.Game;
import PaooGame.States.PlayState;
import PaooGame.States.State;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;


/*! \class MouseInput implements MouseListener, MouseMotionListener
    \brief Gestioneaza miscarile mouse-ului.

 */
public class MouseInput implements MouseListener, MouseMotionListener {

    private static int mx;              /*!< Referinta catre coordonata mouse-ului de pe axa Ox.*/
    private static int my;              /*!< Referinta catre coordonata mouse-ului de pe axa Oy.*/
    private boolean clickPressed;       /*!< flag ce se schimba in momentul in care se apasa click stanga.*/


    /*! \fn public KeyManager()
        \brief Constructorul clasei.
    */
    public MouseInput() {
        System.out.println("MouseInput created");
    }


    /*! \fn public void mouseClicked(MouseEvent mouseEvent)
    \brief Eventul de mouseClicked
    */
    @Override
    public void mouseClicked(MouseEvent mouseEvent) {
    }


    /*! \fn public void mousePressed(MouseEvent mouseEvent)
        \brief Eventul de apasare a unui click
    */
    @Override
    public void mousePressed(MouseEvent mouseEvent) {
        mx = mouseEvent.getX();
        my = mouseEvent.getY();

        if (mouseEvent.getButton() == MouseEvent.BUTTON1) {
            clickPressed = true;
        }

        try {
            if (clickPressed == true) {
                // Menu STATE
                if (State.GetState() == Game.getMenuState()) {
                    // Play
                    if (mx >= 430 && mx <= 770) {
                        if (my >= 353 && my <= 440) {
                            State.SetState(Game.getPlayState());
                        }
                    }

                    // Help
                    if (mx >= 430 && mx < 770) {
                        if (my >= 455 && my <= 545) {
                            State.SetState(Game.getHelpState());
                        }
                    }

                    // Settings
                    if (mx >= 430 && mx < 770) {
                        if (my >= 560 && my <= 650) {
                            clickPressed = false;
                            State.SetState(Game.getSettingsState());
                        }
                    }

                    // Quit
                    if (mx >= 430 && mx < 770) {
                        if (my >= 665 && my <= 755) {
                            System.exit(1);
                        }
                    }
                }


                // Settings STATE
                if (State.GetState() == Game.getSettingsState()) {
                    // Resolution
                    if (mx >= 430 && mx <= 770) {
                        if (my >= 460 && my <= 550) {
//                        System.out.println("Pressed Resolution button");
                        }
                    }

                    // Load
                    if (mx >= 430 && mx < 770) {
                        if (my >= 575 && my <= 660) {
                            if (clickPressed == true) {
                                System.out.println("LoadState");
                                State.setLoadFlag(true);
                                State.SetState(Game.getLoadState());
                            }
                        }
                    }

                    // Menu
                    if (mx >= 430 && mx < 770) {
                        if (my >= 687 && my <= 775) {
                            State.SetState(Game.getMenuState());
                        }
                    }
                }


                // Help STATE
                if (State.GetState() == Game.getHelpState()) {
                    // Menu
                    if (mx >= 430 && mx < 770) {
                        if (my >= 665 && my <= 755) {
                            State.SetState(Game.getMenuState());
                        }
                    }
                }


                // Resume STATE
                if (State.GetState() == Game.getResumeState()) {
                    State.setResumeFlag(true);
                    // yes button
                    if (mx >= 430 && mx <= 770) {
                        if (my >= 460 && my <= 550) {
                            State.SetState(Game.getPlayState());
                            PlayState.setStartTime(System.nanoTime());
                        }
                    }

                    // no button
                    if (mx >= 430 && mx < 770) {
                        if (my >= 687 && my <= 775) {
                            State.SetState(Game.getMenuState());
                        }
                    }

                    // save button
                    if (mx >= 430 && mx < 770) {
                        if (my >= 575 && my <= 660) {
                            State.setSaveFlag(true);
                            State.SetState(Game.getSaveState());
                        }
                    }
                }


                // Save STATE
                if (State.GetState() == Game.getSaveState()) {
                    // Menu
                    if (mx >= 430 && mx < 770) {
                        if (my >= 665 && my <= 755) {
                            State.SetState(Game.getMenuState());
                        }
                    }
                }


                // Finish STATE
                if (State.GetState() == Game.getFinishState()) {
                    // Menu
                    if (mx >= 430 && mx < 770) {
                        if (my >= 650 && my <= 738) {
                            State.SetState(Game.getMenuState());
                        }
                    }
                }


                // GameOver STATE
                if (State.GetState() == Game.getGameOverState()) {
                    // Menu
                    if (mx >= 430 && mx < 770) {
                        if (my >= 650 && my <= 738) {
                            State.SetState(Game.getMenuState());
                        }
                    }
                }
            }
        } catch (Exception e) {
            System.err.println(e);
        }
    }

    /*! \fn public void mouseReleased(MouseEvent mouseEvent)
    \brief Eventul de mouseReleased.
    */
    @Override
    public void mouseReleased(MouseEvent mouseEvent) {
        if (mouseEvent.getButton() == MouseEvent.BUTTON1) {
            clickPressed = false;
        }
    }


    /*! \fn public void mouseEntered(MouseEvent mouseEvent)
    \brief Eventul de mouseEntered.
    */
    @Override
    public void mouseEntered(MouseEvent mouseEvent) {
    }


    /*! \fn public void mouseExited(MouseEvent mouseEvent)
    \brief Eventul de mouseExited.
    */
    @Override
    public void mouseExited(MouseEvent mouseEvent) {
    }


    /*! \fn public void mouseDragged(MouseEvent mouseEvent)
    \brief Eventul de mouseDragged.
    */
    @Override
    public void mouseDragged(MouseEvent mouseEvent) {

    }


    /*! \fn public void mouseMoved(MouseEvent mouseEvent)
    \brief Eventul de mouseMoved.
    */
    @Override
    public void mouseMoved(MouseEvent mouseEvent) {

        mx = mouseEvent.getX();
        my = mouseEvent.getY();
    }

    /*! \fn public int getMx()
    \brief Returneaza pozitina mouse pe axa Ox.
    */
    public int getMx() {
        return mx;
    }


    /*! \fn public int getMy()
    \brief Returneaza pozitina mouse pe axa Oy.
    */
    public int getMy() {
        return my;
    }
}
