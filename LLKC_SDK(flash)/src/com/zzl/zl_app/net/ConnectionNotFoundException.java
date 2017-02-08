package com.zzl.zl_app.net;

import java.io.IOException;

public class ConnectionNotFoundException extends IOException
{
    /**
	 * 
	 */
	private static final long serialVersionUID = 3904184630725775101L;

	public ConnectionNotFoundException( String message )
    {
        super( message );
    }
}
