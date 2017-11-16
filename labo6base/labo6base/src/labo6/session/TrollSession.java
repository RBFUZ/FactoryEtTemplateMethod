package labo6.session;

import labo6.Labo6Main;
import labo6.User;
import labo6.profil.TrollProfiler;

/**
 * Représente un session troll
 */
public class TrollSession extends Session
{
    /**
     * Constructeur de confort
     * 
     * @param l
     * @param u
     */
    public TrollSession(Labo6Main l, User u)
    {
        super(l, u);
    }

    @Override
    public void createProfiler()
    {
        profiler = new TrollProfiler();
    }
}