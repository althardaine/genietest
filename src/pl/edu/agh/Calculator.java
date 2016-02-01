package pl.edu.agh;

import java.util.HashMap;
import java.util.LinkedList;

import javax.swing.JCheckBox;

import smile.Network;

public class Calculator {

	private LinkedList<JCheckBox> profs;
	private Network network = new Network();
	private String[] features;

	public Calculator(LinkedList<JCheckBox> profs, String[] features) {
		this.profs = profs;
		this.features = features;
		network.readFile("eksperty.xdsl");
	}

	public HashMap<String, Double> getResults() {
		HashMap<String, Double> results = new HashMap<String, Double>();
		for (JCheckBox checkBox : profs) {
			String profName = checkBox.getText();
			boolean value = checkBox.isSelected();
			network.setEvidence(profName, value ? 1 : 0);
		}
		network.updateBeliefs();
		
		for (String feature : features) {
			results.put(feature, network.getNodeValue(feature)[1]);
		}
		
		return results;
	}
}
