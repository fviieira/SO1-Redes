package controller;

import java.io.IOException;

public class SistemaOperacional {

	public String os ( ) {
		
		return System.getProperty ( "os.name" ).toLowerCase ( );
	}
	
	public Process callProcess ( String url ) {
		Runtime runtime = Runtime.getRuntime ( );
		StringBuffer sb = new StringBuffer   ( );
		Process process = null;
		
		sb.append ( url );
		
		try {
			
			process = runtime.exec ( sb.toString ( ));
		} catch ( Exception e ) {
			
			String message = e.getMessage ( );
			if ( message.contains ( "740" )) { // process need admin perm
				
				sb.insert ( 0, "cmd /c " );
				try {System.out.println ( "d" ); process = runtime.exec ( sb.toString ( )); }
				catch (IOException e1) { e1.printStackTrace(); }
			} else { e.printStackTrace ( ); }
		} return process;
	}
}
