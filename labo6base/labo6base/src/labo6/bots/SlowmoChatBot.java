package labo6.bots;

import labo6.Ressources.Gender;
import labo6.User;
import labo6.database.Picture;
import labo6.database.TextDatabase;
import labo6.database.TextList;
import labo6.database.TextMessage.TextKey;
import labo6.session.Session;

/**
 * Robot un peu dur de la feuille
 */
public class SlowmoChatBot extends ChatBot
{
    /**
     * Constructeur de confort
     * 
     * @param p
     * @param n
     * @param pic
     * @param g
     */
    public SlowmoChatBot(User p, String n, Picture pic, Gender g, Session s)
    {
        super(p, n, pic, g, s);
    }

    @Override
    public boolean checkForWakeUp(String message)
    {
        boolean result = false;

        if (!message.equals("") && message.equals(oldMessage))
            return true;

        oldMessage = message;
        return result;
    }
    
    @Override
    public void waitForUser()
    {
        sleep(2000);
    }
}