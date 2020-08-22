package PaooGame.Items;

import java.awt.image.BufferedImage;


/*! \class Animation
    \brief Gestioneaza imaginea caracterului care se va afisa pe ecran.

    \brief Aceasta clasa nu imi apartine, de aici m-am inspirat: https://www.youtube.com/watch?v=kzgNCEWUqUs&t=539s

 */
public class Animation {

    private int speed;                          /*!< Viteza cu care se schimba animatia de la caracter.*/
    private int frames;                         /*!< Numarul de imagini procesate.*/

    private int index = 0;                      /*!< Animatia pe care o preia caracterul sa fie prima imagine incarcata in vectorul de imagini.*/
    private int count = 0;                      /*!< Animatia care trebuie afisata.*/

    private BufferedImage[] animation;          /*!< Tablou unidimensional cu imaginile unei animatii.*/
    private BufferedImage currentAnimation;     /*!< Animatia care se afiseaza pe ecran.*/


    /*! \fn public Animation(int speed, BufferedImage ... args)
        \brief Incarca in variabila animation toate animatiile caracterului pentru o tasta.

        \param speed viteza cu care se va trece de la o animatie la alta.
        \param args toate animatiile care se vor afisa la ecran in directia in care merge caracterul.
     */
    public Animation(int speed, BufferedImage ... args) {
        this.speed = speed;

        animation = new BufferedImage[args.length];
        for (int i=0;i<args.length;i++) {
            animation[i] = args[i];
        }

        currentAnimation = animation[0];
        frames = args.length;
    }


    /*! \fn public void runAnimation()
    \brief Metoda prin care se afiseaza pe ecran imaginea respectiva a caracterului.
    */
    public void runAnimation() {
        index++;
        if (index > speed) {
            index = 0;
            nextFrame();
        }
    }


    /*! \fn private void nextFrame()
    \brief Urmatoare imagine pe care currentAnimation o primeste.
    */
    private void nextFrame() {

        for (int j=0; j < frames; j++) {
            if (count == j) {
                currentAnimation = animation[j];
            }
        }

        count++;
        if (count > frames)
            count = 0;
    }


    /*! \fn public BufferedImage getCurrentAnimation()
    \brief Returneaza animatia curenta a caracterului.
    */
    public BufferedImage getCurrentAnimation() {
        return currentAnimation;
    }
}
