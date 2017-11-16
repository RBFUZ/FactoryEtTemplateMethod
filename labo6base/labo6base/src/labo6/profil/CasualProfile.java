package labo6.profil;

import labo6.Ressources.Gender;
import labo6.bots.ChatBot;
import labo6.User;
import labo6.check.CheckUserBehavior;
import labo6.check.CheckUserBehaviorHearing;
import labo6.database.PictureDatabase;
import labo6.database.PictureList;
import labo6.database.TextDatabase;
import labo6.database.TextList;
import labo6.database.Picture.PictureKey;
import labo6.database.TextMessage.TextKey;
import labo6.session.Session;
import labo6.wait.WaitBehavior;
import labo6.wait.WaitBehaviorNohing;

/**
 * Représente un robot de type casual
 */
public class CasualProfile extends Profiler
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
        list.keep(TextKey.isSeductive, false);
        return list;
    }

    /**
     * Génération d'une liste d'images non séduisantes
     * 
     * @return PictureList
     */
    @Override
    public PictureList getSuitablePictures()
    {
        PictureList list = PictureDatabase.getAllPictures();
        list.keep(PictureKey.isSeductive, false);
        return list;
    }

    @Override
    public CheckUserBehavior createCheckBehavior()
    {
        return new CheckUserBehaviorHearing();
    }

    @Override
    public WaitBehavior createWaitBehavior()
    {
        return new WaitBehaviorNohing(this, robot);
    }
}