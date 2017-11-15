package labo6.bots;

import labo6.Ressources.Gender;
import labo6.User;
import labo6.database.Picture;
import labo6.database.TextDatabase;
import labo6.database.TextList;
import labo6.database.TextMessage;
import labo6.database.TextMessage.TextKey;
import labo6.session.Session;

/**
 * Représente un robot qui réagi aux questions
 */
public class PatientChatBot extends ChatBot
{
    /**
     * Constructeur de confort
     * 
     * @param p
     * @param n
     * @param pic
     * @param g
     */
    public PatientChatBot(User p, String n, Picture pic, Gender g, Session s)
    {
        super(p, n, pic, g, s);
    }

    @Override
    public boolean checkForWakeUp(String message)
    {
        if (message.endsWith("?"))
            return true;
        else
            return false;
    }

    @Override
    public void waitForUser()
    {
        sleep(10000);

        // Génération d'une question
        TextList list = session.getSuitableMessage();
        list.keep(TextKey.isQuestion, true);

        // Affichage de la question
        appendMessage(session.generateAnswer(list));
    }
}