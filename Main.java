import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Main implements Runnable, ActionListener{

  // Class Variables  
  JButton[] numberButtons;
  JButton clearButton;
  JButton enterButton;

  JTextField numberDisplay;

  JPanel mainPanel;
  JPanel topPanel; 
  JPanel buttonPanel;

  //create the variable to hold the current Combo
  String combo = "";
  final String CORRECT_COMBO = "1234";

  // Method to assemble our GUI
  public void run(){
    // Creats a JFrame that is 800 pixels by 600 pixels, and closes when you click on the X
    JFrame frame = new JFrame("Combo Lock");
    // Makes the X button close the program
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    // makes the windows 800 pixel wide by 600 pixels tall
    frame.setSize(800,600);
    // shows the window
    frame.setVisible(true);
 
    
    //create a main frame with a border layout
    mainPanel = new JPanel();
    topPanel = new JPanel();
    buttonPanel = new JPanel();
    //set the layout manager
    mainPanel.setLayout(new BorderLayout());
    buttonPanel.setLayout(new GridLayout(4,3));

    //set the array Size
    numberButtons = new JButton[10];

    //create the number buttons
    for(int i = 0; i < numberButtons.length; i++){
      //create a new button
      JButton button = new JButton("" + i);
      button.addActionListener(this);
      button.setActionCommand("" + i);
      //add that button to the array
      numberButtons[i] = button;
      
    }

    //create the operator buttons
    clearButton = new JButton("Clear");
    clearButton.addActionListener(this);
    clearButton.setActionCommand("clear");
    enterButton = new JButton("Enter");
    enterButton.addActionListener(this);
    enterButton.setActionCommand("enter");

    //create the text display
    numberDisplay = new JTextField("CLOSE");
    //set the size of the number display
    numberDisplay.setPreferredSize(new Dimension(800,100));
    //disable the number display
    numberDisplay.setEnabled(false);

    //add the text display to the panels
    topPanel.add(numberDisplay);

    //add the buttons to the grid
    buttonPanel.add(numberButtons[7]);
    buttonPanel.add(numberButtons[8]);
    buttonPanel.add(numberButtons[9]);
    buttonPanel.add(numberButtons[4]);
    buttonPanel.add(numberButtons[5]);
    buttonPanel.add(numberButtons[6]);
    buttonPanel.add(numberButtons[1]);
    buttonPanel.add(numberButtons[2]);
    buttonPanel.add(numberButtons[3]);
    buttonPanel.add(clearButton);
    buttonPanel.add(numberButtons[0]);
    buttonPanel.add(enterButton);

    //arrange the secondary panels into our main panels
    mainPanel.add(topPanel, BorderLayout.PAGE_START);
    mainPanel.add(buttonPanel, BorderLayout.CENTER);

    //add the main panel to the frame
    frame.add(mainPanel);
  }

  // method called when a button is pressed
  public void actionPerformed(ActionEvent e){
    // get the command from the action
    String command = e.getActionCommand();

    switch(command){
      case "clear":
        //set the display to close
        numberDisplay.setText("CLOSE");
        break;
      case "enter":
        //check if it is not CLOSE OPEN or WRONG PIN
        if(!numberDisplay.getText().equals("OPEN") && !numberDisplay.getText().equals("WRONG PIN") && !numberDisplay.getText().equals("CLOSE")){
          //check if the combo is CORRECT_COMBO
          if(combo.equals(CORRECT_COMBO)){
            numberDisplay.setText("OPEN");
          }else{
            numberDisplay.setText("WRONG PIN");
          }
        }
        break;
      default:
        //check if it is not displaying OPEN or WRONG PIN
        if(!numberDisplay.getText().equals("OPEN") && !numberDisplay.getText().equals("WRONG PIN")){
          //add a number to the current combo
          combo += command;

          //if the display shows "CLOSE" reset it
          if(numberDisplay.getText().equals("CLOSE")){
            numberDisplay.setText("");
          }

          //add a star to the display
          numberDisplay.setText(numberDisplay.getText() + "*");
        }
        break;    
    }

  }

  // Main method to start our program
  public static void main(String[] args){
    // Creates an instance of our program
    Main gui = new Main();
    // Lets the computer know to start it in the event thread
    SwingUtilities.invokeLater(gui);
  }
}
