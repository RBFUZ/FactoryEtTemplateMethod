package labo6.profil;

import labo6.User;
import labo6.Ressources.Gender;
import labo6.bots.ChatBot;
import labo6.check.CheckUserBehavior;
import labo6.check.CheckUserBehaviorAswrAsk;
import labo6.database.PictureDatabase;
import labo6.database.PictureList;
import labo6.database.TextDatabase;
import labo6.database.TextList;
import labo6.database.Picture.PictureKey;
import labo6.database.TextMessage.TextKey;
import labo6.session.Session;
import labo6.wait.WaitBehavior;
import labo6.wait.WaitBehaviorAsk;

/**
 * Représente un profil de robot normal
 */
public class NormalProfile extends Profiler
{
    /**
     * Génération d'un nouveau robot normal
     * 
     * @param p
     * @param n
     * @param pic
     * @param g
     */
    @Override
    public void createChatBot(User p, String n, PictureList pic, Gender g, Session s)
    {
        // Modification du sexe du robot en fonction de celui de l'humain
        if (g == Gender.male)
            pic.keep(PictureKey.gender, Gender.male);
        else if (g == Gender.female)
            pic.keep(PictureKey.gender, Gender.female);
        else
            pic.keep(PictureKey.gender, Gender.unknown);

        // Filtre photos comics si Japonais
        pic = filterPictureJapan(pic, p.getCountry());

        robot = new ChatBot(p, n, generatePicture(pic), g);
        robot.setCheck(createCheckBehavior());
        robot.setWait(createWaitBehavior());
        session = s;
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
     * @return String
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
        return filterMessageCountry(TextDatabase.getAllMessages().clone());
    }

    /**
     * Génération d'une list d'image sans filtre
     * 
     * @return PictureList Une image générée
     */
    public PictureList getSuitablePictures()
    {
        return PictureDatabase.getAllPictures();
    }
}