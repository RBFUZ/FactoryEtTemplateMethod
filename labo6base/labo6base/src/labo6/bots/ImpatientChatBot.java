package labo6.bots;

import labo6.Ressources.Gender;
import labo6.User;
import labo6.database.Picture;
import labo6.database.TextDatabase;
import labo6.database.TextList;
import labo6.database.TextMessage.TextKey;
import labo6.session.Session;

/**
 * Représente un robot impatient. Réponds à chaque question
 */
public class ImpatientChatBot extends ChatBot
{
    /**
     * Constructeur de confort
     * 
     * @param p
     * @param n
     * @param pic
     * @param g
     */
    public ImpatientChatBot(User p, String n, Picture pic, Gender g, Session s)
    {
        super(p, n, pic, g, s);
        oldMessage = "";
    }

    @Override
    public boolean checkForWakeUp(String message)
    {
        boolean result = false;

        if (!message.equals("") && !message.equals(oldMessage))
        {
            result = true;
        }

        oldMessage = message;

        return result;
    }

    @Override
    public void waitForUser()
    {
        sleep(1000);

        // Affichage de la réponse aléatoire
        appendMessage(session.generateAnswer(session.getSuitableMessage()));
    }
}