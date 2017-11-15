package labo6.bots;

import labo6.Ressources.Gender;
import labo6.User;
import labo6.database.Picture;
import labo6.session.Session;

public abstract class ChatBot extends User
{
    // L'utilisateur avec lequel le robot est en communication.
    private User peer;
    protected static String oldMessage;
    protected static Session session;

    public ChatBot(User p, String n, Picture pic, Gender g, Session s)
    {
        super(n, pic, g);
        peer = p;
        session = s;
    }

    public void sleep(int time)
    {
        try
        {
            Thread.sleep(time);
        }
        catch (InterruptedException e)
        {
        }
    }

    public void appendMessage(String msg)
    {
        getUI().appendMessage(msg);
    }

    public User getPeer()
    {
        return peer;
    }

    /**
     * Se robot se reveille ou non
     * 
     * @param message
     * @return boolean
     */
    public abstract boolean checkForWakeUp(String message);
    public abstract void waitForUser();
}
