package labo6.session;

import labo6.Labo6Main;
import labo6.User;
import labo6.Ressources.Gender;
import labo6.bots.ChatBot;
import labo6.bots.ImpatientChatBot;
import labo6.bots.PatientChatBot;
import labo6.database.Picture;
import labo6.database.Picture.PictureKey;
import labo6.database.PictureDatabase;
import labo6.database.PictureList;
import labo6.database.TextDatabase;
import labo6.database.TextList;
import labo6.database.TextMessage.TextKey;

/**
 * Représente des messages séduisants
 */
public class SeductionSession extends Session
{
    /**
     * Constructeur de confort
     * 
     * @param l
     * @param u
     */
    public SeductionSession(Labo6Main l, User u)
    {
        super(l, u);
    }

    @Override
    public String generateAnswer(TextList list)
    {
        return list.random().getMessage();
    }

    /**
     * Génération du message du bienvenue
     */
    @Override
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
    @Override
    public TextList getSuitableMessage()
    {
        TextList list = TextDatabase.getAllMessages().clone();
        list.keep(TextKey.isSeductive, true);
        return list;
    }

    /**
     * Génération d'une image séduisante
     * 
     * @return Picture
     */
    @Override
    public Picture getSuitablePictures()
    {
        PictureList list = PictureDatabase.getAllPictures();
        list.keep(PictureKey.isSeductive, true);
        return list.random();
    }

    /**
     * Création d'un nouveau ImpatientChatBot
     * 
     * @param p
     * @param n
     * @param pic
     * @param g
     * @return ChatBot
     */
    public ChatBot createChatBot(User p, String n, Picture pic, Gender g, Session s)
    {
        return new ImpatientChatBot(p, n, pic, g, s);
    }
}