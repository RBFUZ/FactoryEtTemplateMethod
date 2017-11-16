package labo6.session;

import labo6.Labo6Main;
import labo6.Ressources.Gender;
import labo6.User;
import labo6.profil.NormalProfile;
import labo6.profil.Profiler;

/*
 * Cette classe repr�sente une session d'un utilisateur humain avec un ou
 * plusieurs robots. La session se termine lorsqu'on d�tecte que l'utilisateur
 * humain s'est d�connect� (change de pays ou de genre, via les boutons "PAYS"
 * et "GENRE").
 */

public class Session
{
    private final static String NORMAL_SESSION = "normal";
    private final static String SEDUCTION_SESSION = "seduction";
    private final static String CASUAL_SESSION = "casual";

    private User human;
    private Labo6Main ui;
    private boolean ended;
    private Thread sleeper;
    protected static Profiler profiler;

    public Session(Labo6Main l, User u)
    {
        ui = l;
        human = u;
        ended = false;
        sleeper = Thread.currentThread();
    }

    public void start()
    {
        createProfiler();

        profiler.createChatBot(human, "Other", profiler.getSuitablePictures(), Gender.random(), this);

        ui.initBackGround(profiler.getRobot());

        profiler.getRobot().appendMessage(profiler.generateGreeting(profiler.getSuitableMessage()));

        while (!hasEnded())
        {
            profiler.getRobot().getWait().waitForUser();

            if (profiler.getRobot().getCheck().checkForWakeUp(human.getUI().getText()))
            {
                profiler.getRobot().appendMessage(profiler.generateAnswer(profiler.getSuitableMessage()));
            }
        }
    }

    /**
     * Création d'un profil normal
     */
    public void createProfiler()
    {
        profiler = new NormalProfile();
    }

    /**
     * Création du type de session correspondant
     * 
     * @param sessionType
     * @param humanUser
     * @param labo6Main
     * 
     * @param type
     * @return
     */
    public static Session createSession(String sessionType, Labo6Main labo6Main, User humanUser)
    {
        switch (sessionType)
        {
            case NORMAL_SESSION:
                return new Session(labo6Main, humanUser);
            case SEDUCTION_SESSION:
                return new SeductionSession(labo6Main, humanUser);
            case CASUAL_SESSION:
                return new CasualSession(labo6Main, humanUser);
            default:
                throw new IllegalArgumentException("Wrong session type: " + sessionType);
        }
    }

    /*
     * Appel� par le bouton SUIVANT
     */
    public void changeChatBot()
    {
        profiler.createChatBot(human, "Other", profiler.getSuitablePictures(), Gender.random(), this);
        ui.initBackGround(profiler.getRobot());
    }

    public synchronized void end()
    {
        ended = true;
        sleeper.interrupt();
    }

    private synchronized boolean hasEnded()
    {
        return ended;
    }

    /**
     * @return the normalSession
     */
    public static String getNormalSession()
    {
        return NORMAL_SESSION;
    }
}