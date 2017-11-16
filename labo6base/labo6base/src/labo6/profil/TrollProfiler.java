package labo6.profil;

import labo6.Ressources.Gender;
import labo6.bots.ChatBot;
import labo6.User;
import labo6.check.CheckUserBehavior;
import labo6.check.CheckUserBehaviorTroll;
import labo6.database.PictureDatabase;
import labo6.database.PictureList;
import labo6.database.TextDatabase;
import labo6.database.TextList;
import labo6.database.TextMessage.TextKey;
import labo6.session.Session;
import labo6.wait.WaitBehavior;
import labo6.wait.WaitBehaviorSaySomething;

/**
 * Représente un profil troll
 */
public class TrollProfiler extends Profiler
{
    private Boolean sameGender = false;

    @Override
    public void createChatBot(User p, String n, PictureList pic, Gender g, Session s)
    {
        // Filtre photos comics si Japonais
        pic = filterPictureJapan(pic, p.getCountry());

        robot = new ChatBot(p, n, generatePicture(pic), g);
        robot.setCheck(createCheckBehavior());
        robot.setWait(createWaitBehavior());
        session = s;
    }

    @Override
    public String generateGreeting(TextList list)
    {
        list.keep(TextKey.isGreeting, true);

        // Test si le genre est identique
        if (robot.getGender() == robot.getPeer().getGender())
            sameGender = true;

        return generateAnswer(list);
    }

    @Override
    public String generateAnswer(TextList list)
    {
        return list.random().getMessage();
    }

    @Override
    public PictureList getSuitablePictures()
    {
        return PictureDatabase.getAllPictures();
    }

    @Override
    public TextList getSuitableMessage()
    {
        TextList list = filterMessageCountryReverse(TextDatabase.getAllMessages().clone());

        list.keep(TextKey.isOffensive, true);

        // Si le robot est du même genre que l'interlocuteur, alors il parle
        // avec des messages séduisants
        if (!sameGender)
            list.keep(TextKey.isSeductive, false);
        else
            list.keep(TextKey.isSeductive, true);

        return list;
    }

    @Override
    public WaitBehavior createWaitBehavior()
    {
        return new WaitBehaviorSaySomething(this, robot);
    }

    @Override
    public CheckUserBehavior createCheckBehavior()
    {
        return new CheckUserBehaviorTroll();
    }
}