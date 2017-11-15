package labo6.wait;

import labo6.bots.ChatBot;
import labo6.session.Session;

public abstract class WaitBehavior
{
    protected Session session;
    protected ChatBot bot;

    public abstract void waitForUser();
}
