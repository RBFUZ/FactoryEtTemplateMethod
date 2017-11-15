package labo6.wait;

import labo6.bots.ChatBot;
import labo6.session.Session;

public class WaitBehaviorNohing extends WaitBehavior
{
    public WaitBehaviorNohing(Session s, ChatBot c)
    {
        session = s;
        bot = c;
    }

    @Override
    public void waitForUser()
    {
        bot.sleep(2000);
    }
}
