package labo6.wait;

import labo6.bots.ChatBot;
import labo6.session.Session;

public class WaitBehaviorSaySomething extends WaitBehavior
{
    public WaitBehaviorSaySomething(Session s, ChatBot c)
    {
        session = s;
        bot = c;
    }

    @Override
    public void waitForUser()
    {
        bot.sleep(5000);

        // Affichage de la réponse aléatoire
        bot.appendMessage(session.generateAnswer(session.getSuitableMessage()));
    }
}
