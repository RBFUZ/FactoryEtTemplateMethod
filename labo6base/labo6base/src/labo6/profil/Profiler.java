package labo6.profil;

import labo6.User;
import labo6.bots.ChatBot;
import labo6.Ressources.Country;
import labo6.Ressources.Gender;
import labo6.check.CheckUserBehavior;
import labo6.database.Picture;
import labo6.database.PictureList;
import labo6.database.TextList;
import labo6.database.Picture.PictureKey;
import labo6.database.TextMessage.Language;
import labo6.database.TextMessage.TextKey;
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
    public abstract PictureList getSuitablePictures();

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
     * @param s
     */
    public abstract void createChatBot(User p, String n, PictureList pic, Gender g, Session s);

    /**
     * Filtre le language en fonction du pays d'origine de l'interlocuteur
     * 
     * @param list
     * @return TextList
     */
    public TextList filterMessageCountry(TextList list)
    {
        if (robot.getPeer().getCountry().equals(Country.Japan)
                || robot.getPeer().getCountry().equals(Country.UnitedStates))
            list.keep(TextKey.language, Language.english);
        else
            list.keep(TextKey.language, Language.french);

        return list;
    }
    
    public TextList filterMessageCountryReverse(TextList list)
    {
        if (robot.getPeer().getCountry().equals(Country.Japan)
                || robot.getPeer().getCountry().equals(Country.UnitedStates))
            list.keep(TextKey.language, Language.french);
        else
            list.keep(TextKey.language, Language.english);

        return list;
    }

    /**
     * Filtre et retourne que des comics
     * 
     * @param list
     * @param country
     * @return PictureList
     */
    public PictureList filterPictureJapan(PictureList list, Country country)
    {
        if (country.equals(Country.Japan))
            list.keep(PictureKey.isComic, true);

        return list;
    }

    /**
     * Retourne une picture random d'une liste prédéfini
     * 
     * @param list
     * @return Picture
     */
    public Picture generatePicture(PictureList list)
    {
        return list.random();
    }

    /**
     * @return the robot
     */
    public ChatBot getRobot()
    {
        return robot;
    }
}