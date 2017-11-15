package labo6.session;

import labo6.Labo6Main;
import labo6.User;
import labo6.check.CheckUserBehavior;
import labo6.check.CheckUserBehaviorSaySomething;
import labo6.database.Picture;
import labo6.database.Picture.PictureKey;
import labo6.database.PictureDatabase;
import labo6.database.PictureList;
import labo6.database.TextDatabase;
import labo6.database.TextList;
import labo6.database.TextMessage.TextKey;
import labo6.wait.WaitBehavior;
import labo6.wait.WaitBehaviorSaySomething;

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

    @Override
    public CheckUserBehavior createCheckBehavior()
    {
        return new CheckUserBehaviorSaySomething();
    }

    @Override
    public WaitBehavior createWaitBehavior()
    {
        return new WaitBehaviorSaySomething(this, robot);
    }
}