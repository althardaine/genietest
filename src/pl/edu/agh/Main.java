package pl.edu.agh;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.LinkedList;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JProgressBar;

public class Main {
	
	private String[] professions = new String[] {
		"Mechanik",
		"Architekt",
		"Informatyk",
		"Sprzedawca",
		"Kierowca",
		"Nauczyciel",
		"Kucharz",
		"Rolnik",
		"Lekarz"
	};
	private String[] features = new String[] {
		"Otwarty",
		"Cierpliwy",
		"Szczery",
		"Dokladny",
		"Ambitny",
		"Impulsywny",
		"Spostrzegawczy"
	};
	private LinkedList<JProgressBar> res = new LinkedList<JProgressBar>();

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Main window = new Main();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Main() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		LinkedList<JCheckBox> profs = new LinkedList<JCheckBox>();
		
		JButton btnLicz = new JButton("Licz");
		btnLicz.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				Calculator calculator = new Calculator(profs, features);
				HashMap<String, Double> results = calculator.getResults();
				for (String node : results.keySet()) {
					System.out.println(node + " " + results.get(node));
				}
				clearResults();
				showResults(results);
			}
			
		});
		btnLicz.setBounds(4, 180, 89, 23);
		frame.getContentPane().add(btnLicz);
		
		int i = 0;
		for(String profession : professions) {
			JCheckBox checkBox = new JCheckBox(profession);
			checkBox.setBounds(0, i, 100, 23);
			i += 19;
			profs.add(checkBox);
			frame.getContentPane().add(checkBox);
		}
		
		i = 0;
		for(String feature : features) {
			JLabel label = new JLabel(feature);
			label.setBounds(120, i, 100, 14);
			i += 19;
			frame.getContentPane().add(label);
		}
	}

	private void clearResults() {
		for(JProgressBar bar : res) {
			frame.getContentPane().remove(bar);
		}
		res = new LinkedList<JProgressBar>();
	}
	
	private void showResults(HashMap<String, Double> results) {
		int i = 0;
		for(String feature : features) {
			JProgressBar bar = new JProgressBar();
			bar.setBounds(240, i, 100, 14);
			bar.setMaximum(0);
			bar.setMaximum(100);
			int value = (int)(results.get(feature) * 100);
			bar.setValue(value);
			bar.setStringPainted(true);
			i += 19;
			frame.getContentPane().add(bar);
			res.add(bar);
		}
		frame.getContentPane().revalidate();
		frame.validate();
		frame.repaint();
	}
}
