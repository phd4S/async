import java.awt.Color;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JProgressBar;
import javax.swing.border.EmptyBorder;

public class �bung1 {
    private static JProgressBar linkerBalken, rechterBalken;
    private static List<Integer> z�hlerSpeicher = new ArrayList<>();

	private static void zeigeFenster(String title) {
        JFrame frame = new JFrame(title);
        frame.setSize(250, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout( new GridLayout(1,2) );
        frame.getRootPane().setBorder( new EmptyBorder(10, 10, 10, 10) );

        linkerBalken = new JProgressBar(JProgressBar.VERTICAL, 0, 100);
        linkerBalken.setValue(0);
        linkerBalken.setForeground(Color.blue);
        
        rechterBalken = new JProgressBar(JProgressBar.VERTICAL, 0, 100);
        rechterBalken.setValue(0);
        rechterBalken.setForeground(Color.magenta);
        
        frame.add(linkerBalken);
        frame.add(rechterBalken);
        frame.setVisible(true);
	}
	
	private static void verbraucheEtwasZeit(int millisec) {
		try {
			Thread.sleep(millisec);
		}
		catch (Exception ignored) {}
	}

	public static void speichereZahl(int z�hlerstand) {
		rechterBalken.setValue(0);
		z�hlerSpeicher.add(z�hlerstand);
		for (int i=1; i <= 25; i++) {
			verbraucheEtwasZeit(10);
			rechterBalken.setValue(i*4);
		}
	}
	
	public static void main(String[] args) {
		zeigeFenster("L�sung 1");
		System.out.println("Jetzt gehts los...");

		int z�hler = 0;
		while (z�hler < 100) {
			int zeit = (int)(Math.random() * 20) + 10;
			verbraucheEtwasZeit(20 + zeit);
			linkerBalken.setValue( Math.min(100, linkerBalken.getValue() + zeit) );
			z�hler += zeit;
			
			// �ndern Sie diesen synchronen Aufruf in einen asynchronen Aufruf um
			// man darf keine Variable benutzen, die sich nach dem Aufruf noch �ndert :/
			int z�hlerKopie = z�hler; 
			new Thread( () -> speichereZahl(z�hlerKopie) ).start();
		}
		
		System.out.println("Fertig!");
		System.out.println(z�hlerSpeicher);
	}
}
