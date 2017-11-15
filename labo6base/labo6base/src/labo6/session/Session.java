package labo6.session;

import labo6.Labo6Main;
import labo6.Ressources.Gender;
import labo6.User;
import labo6.bots.ChatBot;
import labo6.check.CheckUserBehavior;
import labo6.check.CheckUserBehaviorAswrAsk;
import labo6.database.Picture;
import labo6.database.PictureDatabase;
import labo6.database.TextDatabase;
import labo6.database.TextList;
import labo6.database.TextMessage.TextKey;
import labo6.wait.WaitBehavior;
import labo6.wait.WaitBehaviorAsk;

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
    protected ChatBot robot;
    private Labo6Main ui;
    private boolean ended;
    private Thread sleeper;

    public Session(Labo6Main l, User u)
    {
        ui = l;
        human = u;
        ended = false;
        sleeper = Thread.currentThread();
    }

    public void start()
    {
        createChatBot(human, "Other", getSuitablePictures(), Gender.random(), this);

        ui.initBackGround(robot);

        robot.appendMessage(generateGreeting(getSuitableMessage()));

        while (!hasEnded())
        {
            robot.getWait().waitForUser();

            if (robot.getCheck().checkForWakeUp(human.getUI().getText()))
            {
                robot.appendMessage(generateAnswer(getSuitableMessage()));
            }
        }
    }

    /**
     * Création d'un nouveau ChatBot
     * 
     * @param p
     * @param n
     * @param pic
     * @param g
     * @return
     */
    public void createChatBot(User p, String n, Picture pic, Gender g, Session s)
    {
        robot = new ChatBot(p, n, pic, g, s);
        robot.setCheck(createCheckBehavior());
        robot.setWait(createWaitBehavior());
    }

    public CheckUserBehavior createCheckBehavior()
    {
        return new CheckUserBehaviorAswrAsk();
    }

    public WaitBehavior createWaitBehavior()
    {
        return new WaitBehaviorAsk(this, robot);
    }

    /**
     * Génération d'un message aléatoire
     * 
     * @return String Le message généré
     */
    public String generateAnswer(TextList list)
    {
        return list.random().getMessage();
    }

    /**
     * Génération d'un message de bienvenue
     * 
     * @return
     */
    public String generateGreeting(TextList list)
    {
        list.keep(TextKey.isGreeting, true);
        return generateAnswer(list);
    }

    /**
     * Génération d'une liste de message
     * 
     * @return TextList
     */
    public TextList getSuitableMessage()
    {
        return TextDatabase.getAllMessages().clone();
    }

    /**
     * Génération d'une image aléatoire. L'ensemble des images sont autorisées
     * 
     * @return Picture Une image générée
     */
    public Picture getSuitablePictures()
    {
        return PictureDatabase.getAllPictures().random();
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
        createChatBot(human, "Other", getSuitablePictures(), Gender.random(), this);
        ui.initBackGround(robot);
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