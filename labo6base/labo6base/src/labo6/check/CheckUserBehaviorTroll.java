package labo6.check;

public class CheckUserBehaviorTroll extends CheckUserBehavior
{
    public CheckUserBehaviorTroll()
    {
        oldMessage = "";
    }
    
    @Override
    public boolean checkForWakeUp(String message)
    {
        boolean result = false;

        if (!message.equals("") && !message.equals(oldMessage))
            result = true;
        
        oldMessage = message;
        return result;
    }
}