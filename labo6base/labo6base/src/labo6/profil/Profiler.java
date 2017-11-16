package labo6.profil;

import labo6.User;
import labo6.bots.ChatBot;
import labo6.Ressources.Gender;
import labo6.check.CheckUserBehavior;
import labo6.database.Picture;
import labo6.database.TextList;
import labo6.session.Session;
import labo6.wait.WaitBehavior;

/**
 * Représente le profil d'un robot
 */
public abstract class Profiler
{
    protected ChatBot robot;
    protected Session session;

    /**
     * Génération d'un message de bienvenue
     * 
     * @param list
     * @return String
     */
    public abstract String generateGreeting(TextList list);

    /**
     * Génération d'un message aléatoire
     * 
     * @param list
     * @return String
     */
    public abstract String generateAnswer(TextList list);

    /**
     * Génération d'une image
     * 
     * @return Picture
     */
    public abstract Picture getSuitablePictures();

    /**
     * Génération d'une liste de message
     * 
     * @return TextList
     */
    public abstract TextList getSuitableMessage();

    /**
     * Gestion de l'attente
     * 
     * @return WaitBehavior
     */
    public abstract WaitBehavior createWaitBehavior();

    /**
     * Gestion du comportement (reveil)
     * 
     * @return CheckUserBehavior
     */
    public abstract CheckUserBehavior createCheckBehavior();

    /**
     * Génération d'un nouveau robot
     * 
     * @param p
     * @param n
     * @param pic
     * @param g
     */
    public void createChatBot(User p, String n, Picture pic, Gender g, Session s)
    {
        robot = new ChatBot(p, n, pic, g);
        robot.setCheck(createCheckBehavior());
        robot.setWait(createWaitBehavior());
        session = s;
    }

    /**
     * @return the robot
     */
    public ChatBot getRobot()
    {
        return robot;
    }
}