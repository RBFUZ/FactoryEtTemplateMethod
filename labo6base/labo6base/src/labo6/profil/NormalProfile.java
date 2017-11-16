package labo6.profil;

import labo6.User;
import labo6.Ressources.Gender;
import labo6.bots.ChatBot;
import labo6.check.CheckUserBehavior;
import labo6.check.CheckUserBehaviorAswrAsk;
import labo6.database.Picture;
import labo6.database.PictureDatabase;
import labo6.database.TextDatabase;
import labo6.database.TextList;
import labo6.database.TextMessage.TextKey;
import labo6.session.Session;
import labo6.wait.WaitBehavior;
import labo6.wait.WaitBehaviorAsk;

public class NormalProfile extends Profiler
{
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
}
