package labo6.check;

public abstract class CheckUserBehavior
{
    protected String oldMessage;
    
    public abstract boolean checkForWakeUp(String message);
}
