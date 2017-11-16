package labo6.wait;

import labo6.bots.ChatBot;
import labo6.profil.Profiler;

public abstract class WaitBehavior
{
    protected Profiler profiler;
    protected ChatBot bot;

    public abstract void waitForUser();
}
