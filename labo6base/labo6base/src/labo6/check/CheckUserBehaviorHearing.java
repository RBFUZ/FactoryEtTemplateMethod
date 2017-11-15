package labo6.check;

public class CheckUserBehaviorHearing extends CheckUserBehavior
{
    /**
     * Constructeur par défaut
     */
    public CheckUserBehaviorHearing()
    {
        oldMessage = "";
    }

    @Override
    public boolean checkForWakeUp(String message)
    {
        boolean result = false;

        if (!message.equals("") && message.equals(oldMessage))
            return true;

        oldMessage = message;
        return result;
    }
}