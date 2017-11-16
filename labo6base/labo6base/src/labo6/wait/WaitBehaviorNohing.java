package labo6.wait;

import labo6.bots.ChatBot;
import labo6.profil.Profiler;

public class WaitBehaviorNohing extends WaitBehavior
{
    public WaitBehaviorNohing(Profiler p, ChatBot c)
    {
        profiler = p;
        bot = c;
    }

    @Override
    public void waitForUser()
    {
        bot.sleep(2000);
    }
}
