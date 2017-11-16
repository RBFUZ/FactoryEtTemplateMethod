package labo6.profil;

import labo6.Ressources.Gender;
import labo6.bots.ChatBot;
import labo6.User;
import labo6.check.CheckUserBehavior;
import labo6.check.CheckUserBehaviorSaySomething;
import labo6.database.PictureDatabase;
import labo6.database.PictureList;
import labo6.database.TextDatabase;
import labo6.database.TextList;
import labo6.database.Picture.PictureKey;
import labo6.database.TextMessage.TextKey;
import labo6.session.Session;
import labo6.wait.WaitBehavior;
import labo6.wait.WaitBehaviorSaySomething;

/**
 * Représente un robot de type seduction
 */
public class SeductiveProfile extends Profiler
{
    /**
     * Génération d'un nouveau robot séductif
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
        if (p.getGender() == Gender.male)
            g = Gender.female;
        else if (p.getGender() == Gender.female)
            g = Gender.male;
        else
            g = Gender.unknown;

        // Filtre des photos pour correspondre au genre
        if (g == Gender.male)
            pic.keep(PictureKey.gender, Gender.male);
        else if (g == Gender.female)
            pic.keep(PictureKey.gender, Gender.female);
        else
        {
            // Aucune image n'est du genre inconnu et de type seduction. Pour
            // remédier à ce problème, le critère de seduction n'est plus
            // appliqué sur la liste
            PictureList list = PictureDatabase.getAllPictures();
            list.keep(PictureKey.gender, Gender.unknown);
            pic = list;
        }

        // Filtre photos comics si Japonais
        pic = filterPictureJapan(pic, p.getCountry());

        robot = new ChatBot(p, n, generatePicture(pic), g);
        robot.setCheck(createCheckBehavior());
        robot.setWait(createWaitBehavior());
        session = s;
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
        TextList list = filterMessageCountry(TextDatabase.getAllMessages().clone());
        list.keep(TextKey.isSeductive, true);
        return list;
    }

    /**
     * Génération d'une liste d'images séduisantes
     * 
     * @return Picture
     */
    @Override
    public PictureList getSuitablePictures()
    {
        PictureList list = PictureDatabase.getAllPictures();
        list.keep(PictureKey.isSeductive, true);
        return list;
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