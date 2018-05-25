package artifreload.common.util.artifact.Factory;

public class ErrorNullAttachment extends Exception {

public ErrorNullAttachment ()
{
}

public ErrorNullAttachment (String message)
{
	super (message);
}

public ErrorNullAttachment (Throwable cause)
{
	super (cause);
}

public ErrorNullAttachment (String message, Throwable cause)
{
	super (message, cause);
}
}
