package labo6.session;

import labo6.Labo6Main;
import labo6.User;
import labo6.check.CheckUserBehavior;
import labo6.check.CheckUserBehaviorHearing;
import labo6.database.Picture;
import labo6.database.PictureDatabase;
import labo6.database.PictureList;
import labo6.database.TextDatabase;
import labo6.database.TextList;
import labo6.database.Picture.PictureKey;
import labo6.database.TextMessage.TextKey;
import labo6.profil.CasualProfile;
import labo6.wait.WaitBehavior;
import labo6.wait.WaitBehaviorNohing;

/**
 * Représente une session casual
 */
public class CasualSession extends Session
{
    /**
     * Constructeur de confort
     * 
     * @param l
     * @param u
     */
    public CasualSession(Labo6Main l, User u)
    {
        super(l, u);
    }

    @Override
    public void createProfiler()
    {
        profiler = new CasualProfile();
    }
}