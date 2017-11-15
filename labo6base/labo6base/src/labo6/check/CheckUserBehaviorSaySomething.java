package labo6.check;

public class CheckUserBehaviorSaySomething extends CheckUserBehavior
{
    /**
     * Constructeur par défaut
     */
    public CheckUserBehaviorSaySomething()
    {
        oldMessage = "";
    }

    @Override
    public boolean checkForWakeUp(String message)
    {
        boolean result = false;

        if (!message.equals("") && !message.equals(oldMessage))
        {
            result = true;
        }

        oldMessage = message;

        return result;
    }
}
