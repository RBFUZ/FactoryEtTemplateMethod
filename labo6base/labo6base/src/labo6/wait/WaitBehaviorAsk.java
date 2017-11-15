package labo6.wait;

import labo6.bots.ChatBot;
import labo6.database.TextList;
import labo6.database.TextMessage.TextKey;
import labo6.session.Session;

public class WaitBehaviorAsk extends WaitBehavior
{
    public WaitBehaviorAsk(Session s, ChatBot c)
    {
        session = s;
        bot = c;
    }

    @Override
    public void waitForUser()
    {
        bot.sleep(3000);

        // Génération d'une question
        TextList list = session.getSuitableMessage();
        list.keep(TextKey.isQuestion, true);

        // Affichage de la question
        bot.appendMessage(session.generateAnswer(list));
    }
}
