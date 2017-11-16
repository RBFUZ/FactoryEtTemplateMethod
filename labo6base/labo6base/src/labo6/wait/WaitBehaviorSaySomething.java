package labo6.wait;

import labo6.bots.ChatBot;
import labo6.profil.Profiler;
import labo6.session.Session;

public class WaitBehaviorSaySomething extends WaitBehavior
{
    public WaitBehaviorSaySomething(Profiler p, ChatBot c)
    {
        profiler = p;
        bot = c;
    }

    @Override
    public void waitForUser()
    {
        bot.sleep(1000);

        // Affichage de la réponse aléatoire
        bot.appendMessage(profiler.generateAnswer(profiler.getSuitableMessage()));
    }
}
