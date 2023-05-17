package M2_GUI;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.Date;



// AgeCalculator is a Panel builder for calculating age //
public class AgeCalculator extends JPanel implements ActionListener {
	
	
	public static void main(String[] args) {
		
		JFrame ageFrame = new JFrame("Age Calculator");
		
		// create AgeCalculator panel and add it to the frame
		AgeCalculator agePanel = new AgeCalculator();

		ageFrame.add(agePanel);
		
		ageFrame.pack();
		ageFrame.setVisible(true);
		ageFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		ageFrame.setLocation(700,400);

		
	}
	
	
	
	/* Constructor creates GUI components,
	 * and adds GUI components using a GridBagLayout */ 
	
	private JLabel birthDateLabel, currentDateLabel;
	private JLabel yearLabel1, yearLabel2, yearLabel3;
	private JLabel monthLabel, dayLabel, equalLabel1, equalLabel2;	// Labels
	private JTextField birthDateField, currentDateField;
	private JTextField yearField1, yearField2, yearField3, monthField, dayField;  // Displays field text
	private JButton calcButton; // triggers Age calculation
	private JTextArea messageArea; // error message TextArea
	
	
	
	AgeCalculator(){
		
		// the program consists of three panels
		JPanel entryPanel = new JPanel(new GridBagLayout());
		JPanel calculationPanel = new JPanel(new GridBagLayout());
		JPanel root = new JPanel(new BorderLayout());
		
		// Labels Initiation and Settings 
		currentDateLabel = new JLabel("Current Date", SwingConstants.LEFT);
		birthDateLabel = new JLabel("Birth Date");
		yearLabel1 = new JLabel("years,");
		yearLabel2 = new JLabel("months,");
		yearLabel3 = new JLabel("and days.");
		equalLabel1 = new JLabel(" = ");
		equalLabel2 = new JLabel(" = ");
		monthLabel = new JLabel("Months");
		dayLabel = new JLabel("Days");

		// Fields Initiation and Settings 
		currentDateField = new JTextField(15);
		currentDateField.setEditable(true); // also can be modified by users
		currentDateField.setText(LocalDate.now().toString()); // default is today
		
		birthDateField = new JTextField(15);
		birthDateField.setEditable(true);  // users input date of birth
		
		yearField1 = new JTextField(2);
		yearField1.setEditable(false);  // to display calculated age in year-monty-day
		yearField2 = new JTextField(2);
		yearField2.setEditable(false);  // to display calculated age in year-monty-day
		yearField3 = new JTextField(2);
		yearField3.setEditable(false);  // to display calculated age in year-monty-day
		
		monthField = new JTextField(4);
		monthField.setEditable(false);  // to display calculated age in months
		dayField = new JTextField(4);
		dayField.setEditable(false);  // to display calculated age in days
		

		// Button to "calculate" action
		calcButton = new JButton("Calculate");
		calcButton.addActionListener(this);  // button handles event action
		
		// TextArea for messages
		messageArea = new JTextArea(2, 15);
		messageArea.setEditable(false);
		messageArea.setText("Please use <yyyy-mm-dd> format"); // default message
		
		// Adding Components to each panel using GridBagLayout Constraints
		GridBagConstraints layoutConst = new GridBagConstraints();
		layoutConst.insets = new Insets(2, 10, 2, 10);

		layoutConst.gridx = 0;
		layoutConst.gridy = 0;
		entryPanel.add(currentDateLabel, layoutConst);
		layoutConst.gridx = 1;
		layoutConst.gridy = 0;
		entryPanel.add(currentDateField, layoutConst);
		
		layoutConst.gridx = 0;
		layoutConst.gridy = 1;
		entryPanel.add(birthDateLabel, layoutConst);
		layoutConst.gridx = 1;
		layoutConst.gridy = 1;
		entryPanel.add(birthDateField, layoutConst);
		
		layoutConst.insets = new Insets(10, 10, 10, 10);
		layoutConst.gridx = 1;
		layoutConst.gridy = 2;
		entryPanel.add(calcButton, layoutConst);
		
		layoutConst.gridx = 1;
		layoutConst.gridy = 3;
		entryPanel.add(messageArea, layoutConst);
		
		layoutConst.insets = new Insets(2, 10, 2, 10);		
		layoutConst.gridx = 0;
		layoutConst.gridy = 0;
		calculationPanel.add(yearField1, layoutConst);
		layoutConst.gridx = 1;
		layoutConst.gridy = 0;
		calculationPanel.add(yearLabel1, layoutConst);
		layoutConst.gridx = 0;
		layoutConst.gridy = 1;
		calculationPanel.add(yearField2, layoutConst);
		layoutConst.gridx = 1;
		layoutConst.gridy = 1;
		calculationPanel.add(yearLabel2, layoutConst);
		layoutConst.gridx = 0;
		layoutConst.gridy = 2;
		calculationPanel.add(yearField3, layoutConst);
		layoutConst.gridx = 1;
		layoutConst.gridy = 2;
		calculationPanel.add(yearLabel3, layoutConst);
	
		layoutConst.gridx = 2;
		layoutConst.gridy = 1;
		calculationPanel.add(equalLabel1, layoutConst);
		layoutConst.gridx = 3;
		layoutConst.gridy = 1;
		calculationPanel.add(monthField, layoutConst);
		layoutConst.gridx = 4;
		layoutConst.gridy = 1;
		calculationPanel.add(monthLabel, layoutConst);
		
		layoutConst.gridx = 2;
		layoutConst.gridy = 2;
		calculationPanel.add(equalLabel2, layoutConst);
		layoutConst.gridx = 3;
		layoutConst.gridy = 2;
		calculationPanel.add(dayField, layoutConst);
		layoutConst.gridx = 4;
		layoutConst.gridy = 2;
		calculationPanel.add(dayLabel, layoutConst);
		
		// add two panels to root panel
		root.add(entryPanel, "North");
		root.add(calculationPanel, "Center");
		add(root);
		
		// Decorations
		root.setBackground(Color.lightGray);	
		calcButton.setBackground(Color.cyan);
		messageArea.setBackground(Color.white);
	}
	
	/* Classes that implement the ActionListener interface must define
	 * the actionPerformed() method to define 
	 * how the class should react to an action event.
	 * Method is automatically called when an event occurs.
	 */
	@Override
	public void actionPerformed(ActionEvent event) {
		
		String userInput1, userInput2, message;
		int diff_year, diff_month, diff_day;
		
		try {
			// user Input data as String
			userInput1 = currentDateField.getText();
			userInput2 = birthDateField.getText();
			
			// change the String type to LocalDate type
			LocalDate currentDate = LocalDate.parse(userInput1);
			LocalDate userBirthDate = LocalDate.parse(userInput2);
			
			// Display calculated Age in years, months, days to each fields
			Period diff = Period.between(userBirthDate, currentDate); // calculate the difference
			yearField1.setText(Integer.toString(diff.getYears()));
			yearField2.setText(Integer.toString(diff.getMonths()));
			yearField3.setText(Integer.toString(diff.getDays()));
			
			// calculate in Months
			diff_year = diff.getYears(); 
			diff_month = diff.getMonths() + diff_year*12;
			monthField.setText(Integer.toString(diff_month));
			
			// calculate in days
			diff_day = 0;
			for (int i = userBirthDate.getYear(); i < currentDate.getYear(); i++) { // for each year in between
				LocalDate lastDate = LocalDate.parse((String) Integer.toString(i) + "-12-31");
				diff_day += lastDate.getDayOfYear(); // take leap years into account
			}
			diff_day -= userBirthDate.getDayOfYear();
			diff_day += currentDate.getDayOfYear();
			
			dayField.setText(Integer.toString(diff_day));
			
			message = "<Calculated Successfully>";
			
		} catch (Exception e) {
			message = "<Invalid Input>";
		}
		
		messageArea.setText(message);

		
	}
	

}
