package controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class Redes {
	
	public void ip ( String SO_name ) {
		
		StringBuffer sb   =   new StringBuffer ( );
		SistemaOperacional tc = new SistemaOperacional ( );
		
		if ( SO_name.contains ( "windows" )) sb.append ( "ipconfig" );
		else if ( SO_name.contains ( "linux" )) sb.append ( "ifconfig" );
		else return; // error not supported os
		
		Process process = tc.callProcess ( sb.toString ( ));
		
		if ( process == null ) return;
		
		InputStream fluxo = process.getInputStream ( );
		InputStreamReader leitor = new InputStreamReader ( fluxo );
		BufferedReader buffer = new BufferedReader ( leitor );
		
		String adaptador = "";
		
		String ln;
		try {
			ln = buffer.readLine ( );
			if ( SO_name.contains ( "window" )) {
				while ( ln != null ) {
					
					if ( ln.contains ( "Adaptador" )) adaptador = ln;
					if ( ln.contains ( "IPv4")) System.out.println ( adaptador + "\n" + ln );
					ln = buffer.readLine ( );
				}
			} else if ( SO_name.contains( "linux" )) {
				while ( ln != null ) {
                    if ( ln.contains ( "inet ")) {
                        String s[] = ln.trim().split(" ");
                        System.out.println( s[0] + " " + s[1]);
                    }
                    ln = buffer.readLine ( );
                }
			} else {
				return; // not supported so
			}
			fluxo.close  ( );
			leitor.close ( );
			buffer.close ( );
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void ping ( String SO_name ) {
		
		StringBuffer sb   =   new StringBuffer ( );
		SistemaOperacional tc = new SistemaOperacional ( );
		
		if ( SO_name.contains ( "windows" )) sb.append ( "ping -n 10 www.google.com" );
		else if ( SO_name.contains ( "linux" )) sb.append ( "ping -c 10 www.google.com" );
		else return; // error not supported os
		
		Process process = tc.callProcess ( sb.toString ( ));
		
		if ( process == null ) return;
		
		InputStream fluxo = process.getInputStream ( );
		InputStreamReader leitor = new InputStreamReader ( fluxo );
		BufferedReader buffer = new BufferedReader ( leitor );
		
		String ln;
		try {
			ln = buffer.readLine ( );
			
			if ( SO_name.contains ( "window" )) {
				while ( ln != null ) {
					
					int index = ln.indexOf ( "dia =" );
					if ( index != -1 ) System.out.println ( "Média =" + ln.substring ( index + 5, ln.length ( )));
					ln = buffer.readLine ( );
				}
			} else if ( SO_name.contains ( "linux" )) {
				while ( ln != null){
                    if ( ln.contains ( "rtt ")) {
                        String s[] = ln.trim().split("/");
                        System.out.println("avg --> " + s[4] + "ms");
                    }
                    ln = buffer.readLine ( );
                }
			} else {
				return; // not supported so
			}
			
			fluxo.close  ( );
			leitor.close ( );
			buffer.close ( );
		} catch ( IOException e ) { e.printStackTrace ( ); }
	}
}