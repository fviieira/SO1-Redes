package view;

import javax.swing.JOptionPane;

import controller.Redes;
import controller.SistemaOperacional;

public class Main {

	public static void main ( String[] args ) {
		
		Redes rc = new Redes ( );
		SistemaOperacional tc = new SistemaOperacional ( );
		String os = tc.os ( );
		
		String options[] = { "Chamar IP", "Chamar PING", "Finalizar aplicação" };
		int option;
		
		while ( true ) {
			
			option = JOptionPane.showOptionDialog (
					null,
					"Qual função quer chamar?", "Escolha uma opção",
					JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null,
					options, options[0]
			);
			
			switch ( option ) {
				case 0: rc.ip ( os ); break;
				case 1: rc.ping ( os ); break;
				default: System.exit ( 0 );
			}
		}
	}
}
