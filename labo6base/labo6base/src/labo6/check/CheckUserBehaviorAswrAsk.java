package labo6.check;

public class CheckUserBehaviorAswrAsk extends CheckUserBehavior
{

    @Override
    public boolean checkForWakeUp(String message)
    {
        if (message.endsWith("?"))
            return true;
        else
            return false;
    }
}
