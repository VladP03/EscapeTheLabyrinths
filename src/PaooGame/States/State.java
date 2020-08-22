package PaooGame.States;

import java.awt.*;
import PaooGame.RefLinks;

/*! \class State
    \brief Implementeaza notiunea abstracta de stare a jocului/programului.

    Un joc odata ce este lansat in executie nu trebuie "sa arunce jucatorul direct in lupta", este nevoie de
    un meniu care sa contine optiuni: New Game, Load Game, Settings, About etc. Toate aceste optiuni nu sunt altceva
    decat stari ale programului (jocului) ce trebuiesc incarcate si afisate functie de starea curenta.
 */
public abstract class State
{

    ///Urmatoarele atribute sunt statice pentru a evita dealocarea spatiului de memorie la trecerea dintr-o stare in alta.
    private static State previousState  = null;         /*!< Referinta catre starea anterioara a jocului.*/
    private static State currentState   = null;         /*!< Referinta catre starea curenta a jocului: game, meniu, settings, about etc.*/
    protected RefLinks refLink;

    private static boolean resetFlag;                   /*!< Flag pentru optiunea de reset a jocului.*/
    private static boolean playingFlag;                 /*!< Flag pentru optiunea de a vedea ca jocul se afla in modul play.*/
    private static boolean finishFlag = true;           /*!< Flag pentru optiunea de a vedea daca jocul a ajuns in modul finish.*/
    private static boolean loadFlag;                    /*!< Flag pentru optiunea de load.*/
    private static boolean saveFlag;                    /*!< Flag pentru optiunea de save.*/
    private static boolean resumeFlag;

    protected static long startTime = 0;                /*!< Atribut pentru contorizarea timpului in modul play.*/
    protected static long stopTime = 0;                 /*!< Atribut pentru contorizarea timpului in modul play.*/
    protected static long totalTime;                    /*!< Atribut pentru valoarea finala a timpului petrecut in modul play.*/

    protected long seconds = 0;                         /*!< Atribut pentru conversia timpului total in secunde*/
    protected long minutes = 0;                         /*!< Atribut pentru conversia timpului total in minute.*/



    /*! \fn public State(RefLinks refLink)
        \brief Constructorul aferent clasei.
        \param refLink pentru scurtatura de referinte.
     */
    public State(RefLinks refLink)
    {
        this.refLink = refLink;
    }



        ///Metoda abstracta destinata actualizarii starii curente
    public abstract void Update();



        ///Metoda abstracta destinata desenarii starii curente
    public abstract void Draw(Graphics g);



    /*! \fn public static void SetState(State state)
        \brief Seteaza starea curenta a jocului.

        \param state Noua stare a programului (jocului).
     */
    public static void SetState(State state)
    {
        previousState = currentState;
        currentState = state;
    }



    /*! \fn public static State GetState()
        \brief Returneaza starea curenta a jocului.
    */
    public static State GetState()
    {
        return currentState;
    }



    /*! \fn public static void getPreviousState()
        \brief Seteaza starea anterioara a jocului.
     */
    public static State getPreviousState() {
        return previousState;
    }



    /*! \fn public static boolean getResetFlag()
        \brief Returneaza starea flag-ului de reset.
    */
    public static boolean getResetFlag() {
        return resetFlag;
    }



    /*! \fn public static void setResetFlag(boolean resetFlag)
        \brief Seteaza starea flag-ului de reset.

        \param boolean Noua stare a flag-ului de reset.
    */
    public static void setResetFlag(boolean resetFlag) {
        State.resetFlag = resetFlag;
    }


    /*! \fn public static boolean isPlayingFlag()
        \brief Returneaza starea flag-ului de play.
    */
    public static boolean isPlayingFlag() {
        return playingFlag;
    }



    /*! \fn public static void setResetFlag(boolean playingFlag)
        \brief Seteaza starea flag-ului de play.

        \param boolean Noua stare a flag-ului de play.
    */
    public static void setPlayingFlag(boolean playingFlag) {
        State.playingFlag = playingFlag;
    }



    /*! \fn public static boolean isFinishFlag()
        \brief Returneaza starea flag-ului de finish.
    */
    public static boolean isFinishFlag() {
        return finishFlag;
    }



    /*! \fn public static void setFinishFlag(boolean finishFlag)
        \brief Seteaza starea flag-ului de finish.

        \param boolean Noua stare a flag-ului de finish.
    */
    public static void setFinishFlag(boolean finishFlag) {
        State.finishFlag = finishFlag;
    }



    /*! \fn public static boolean isLoadFlag()
        \brief Returneaza starea flag-ului de load.
    */
    public static boolean isLoadFlag() {
        return loadFlag;
    }



    /*! \fn public static void setLoadFlag(boolean loadFlag)
        \brief Seteaza starea flag-ului de load.

        \param boolean Noua stare a flag-ului de load.
    */
    public static void setLoadFlag(boolean loadFlag) {
        State.loadFlag = loadFlag;
    }



    /*! \fn public static void isSaveFlag()
        \brief Seteaza starea flag-ului de save.
    */
    public static boolean isSaveFlag() {
        return saveFlag;
    }


    /*! \fn public static void setSaveFlag(boolean saveFlag)
        \brief Seteaza starea flag-ului de save.

        \param boolean Noua stare a flag-ului de save.
    */
    public static void setSaveFlag(boolean saveFlag) {
        State.saveFlag = saveFlag;
    }


    /*! \fn public static void setStartTime(long startTime)
        \brief Setam timpul de inceput pe 0, metoda este fosita pentru reset.

        \param long startTime noul timp de inceput.
    */
    public static void setStartTime(long startTime) {
        State.startTime = startTime;
    }


    /*! \fn public static void setStopTime(long stopTime)
        \brief Setam timpul de sfarsit pe 0, metoda este fosita pentru reset.

        \param long stopTime noul timp de sfarsit.
    */
    public static void setStopTime(long stopTime) {
        State.stopTime = stopTime;
    }


    /*! \fn public static void setStopTime(long stopTime)
        \brief Setam timpul total pe 0, metoda este fosita pentru reset.

        \param long totalTime noul timp total.
    */
    public static void setTotalTime(long totalTime) {
        State.totalTime = totalTime;
    }


    /*! \fn public static boolean isResumeFlag()
        \brief Verifica valoarea flag-ului de resume.
    */
    public static boolean isResumeFlag() {
        return resumeFlag;
    }


    /*! \fn public static void setResumeFlag(boolean resumeFlag)
        \brief Seteaza valoarea flag-ului de resume.

        \param boolean resumeFlag reprezinta noua valoare a flag-ului de resume.
    */
    public static void setResumeFlag(boolean resumeFlag) {
        State.resumeFlag = resumeFlag;
    }
}
