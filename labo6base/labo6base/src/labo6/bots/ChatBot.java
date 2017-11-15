package labo6.bots;

import labo6.Ressources.Gender;
import labo6.User;
import labo6.check.CheckUserBehavior;
import labo6.database.Picture;
import labo6.session.Session;
import labo6.wait.WaitBehavior;

public class ChatBot extends User
{
    // L'utilisateur avec lequel le robot est en communication.
    private User peer;
    protected static String oldMessage;
    protected static Session session;

    protected WaitBehavior wait;
    protected CheckUserBehavior check;

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
     * @return the wait
     */
    public WaitBehavior getWait()
    {
        return wait;
    }

    /**
     * @return the check
     */
    public CheckUserBehavior getCheck()
    {
        return check;
    }

    /**
     * @param wait
     *            the wait to set
     */
    public void setWait(WaitBehavior wait)
    {
        this.wait = wait;
    }

    /**
     * @param check
     *            the check to set
     */
    public void setCheck(CheckUserBehavior check)
    {
        this.check = check;
    }
}