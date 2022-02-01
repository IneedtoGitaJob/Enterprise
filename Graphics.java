import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.TimeZone;
import java.util.Calendar;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

/*  Name:  Andreas Hinsch
Course: CNT 4714 – Spring 2022 
Assignment title: Project 1 – Event-driven Enterprise Simulation 
Date: Sunday January 30, 2022 
*/ 

public class Graphics {
	//A set of Helper variables that move values between action listeners
	static String Potential_List;
	static double Potential_Total = 0;
	static int Potential_Nums;
	
	//Number of different Items
	static int Num_Items = 1;
	
	//Quantity of Items
	static int Num_Total = 0;
	
	
	//Total price
	static double Total = 0;
	
	
	//List of items to be bought
	static List<String> String_List = new ArrayList<>();
	
	//Writer to the output
	
	static PrintWriter out = null;
public static void main(String[] args){
	
	//Create JFrame
	JFrame frame = new JFrame("CNT 4714 – Project 1 – Spring 2022");
	//frame.getContentPane();
	
	//Create JPanel
	JPanel Pan = new JPanel();
	//Set layout to null so we can manually place Objects
	Pan.setLayout(null);
 
	//Opens Inventory File this will vary between Users
	File file = new File("C:\\Users\\DustlessSteak\\eclipse-workspace\\CNT 4714 – Project 1 – Spring 2022\\src\\inventory.txt");
  
	//Opens file to be written to
	//BufferedWriter out = null;
	try {
	    FileWriter f = new FileWriter("C:\\Users\\DustlessSteak\\eclipse-workspace\\CNT 4714 – Project 1 – Spring 2022\\src\\transactions.txt", true);
	    out = new PrintWriter(f);
	}

	catch (IOException e) {
	    System.err.println("Error: " + e.getMessage());
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	//Contains All Text implementation
	
	
	//Create Text
	JLabel Input_ID = new JLabel("Enter item ID for item #"+ Num_Items+":");
	JLabel Input_Quantity = new JLabel("Enter quantity for item #"+ Num_Items+":");
	JLabel Input_Details = new JLabel("Details for Item #"+ Num_Items+":");
	JLabel Input_Total = new JLabel("Order Subtotal for "+ Num_Total+" item(s):");
	
	//Get a good dimension size for the user since we are only bounded by the longest text we will use that one
	Dimension size2 = Input_Total.getPreferredSize();
	//Set text location
	Input_ID.setBounds(100, 100, size2.width, size2.height);
	Input_Quantity.setBounds(100, 150, size2.width, size2.height);
	Input_Details.setBounds(100, 200, size2.width, size2.height);
	Input_Total.setBounds(100, 250, size2.width, size2.height);
	
	//Add text to Pan
	Pan.add(Input_ID);
	Pan.add(Input_Quantity);
	Pan.add(Input_Details);
	Pan.add(Input_Total);
	
	
	
		
	
	
	
	
	
	
	
	//Contains All TextField implementation
	
	
	//Create TextFields
	JTextField Input_ID_Text = new JTextField(80);
	JTextField Input_Quantity_Text = new JTextField(80);
	JTextField Input_Details_Text = new JTextField(80);
	JTextField Input_Total_Text = new JTextField(80);
	
	//Sets certain text fields to uneditable
	Input_Details_Text.setEditable(false);
	Input_Total_Text.setEditable(false);
	
	//Get a good dimension size for the user since all text should be the same size this is only called once
	Dimension size3 = Input_ID_Text.getPreferredSize();
	Input_ID_Text.setBounds(300, 100, size3.width, size3.height);
	Input_Quantity_Text.setBounds(300, 150, size3.width, size3.height);
	Input_Details_Text.setBounds(300, 200, size3.width, size3.height);
	Input_Total_Text.setBounds(300, 250, size3.width, size3.height);
	
	//Add textFields to Pan
	Pan.add(Input_ID_Text);
	Pan.add(Input_Quantity_Text);
	Pan.add(Input_Details_Text);
	Pan.add(Input_Total_Text);
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	//Contains All Button implementation
	
	
	
		//Create Buttons
		JButton Process_Item_Button = new JButton("Process Item#"+Num_Items);
		JButton Confirm_Item_Button = new JButton("Confirm Item#"+Num_Items);
		JButton View_Item_Button = new JButton("View order");
		JButton FinishOrder_Item_Button = new JButton("Finish Order");
		JButton NewOrder_Item_Button = new JButton("New Order");
		JButton Exit_Button = new JButton("Exit");
		
		//Adds OnClick Functionality to all Buttons
		
		//Process Item
		Process_Item_Button.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				//Get texts of both input fields
				String Input_ID_Value = Input_ID_Text.getText();
				String Input_Quantity = Input_Quantity_Text.getText();
                
				//If a field is empty report an error
				if(Input_ID_Value.isEmpty() || Input_Quantity.isEmpty() ) {
			        JOptionPane.showMessageDialog(frame, "Please enter values into both fields", "Nile Dot Com-ERROR", 1);
				}
				//If both fields have a value in them then pass those values into Find_ID
				else {
					String Str[] = Find_ID(file, Input_ID_Value);
					//If The value is present in the list
					if(Str != null) {
						//If the item is in stock
						if(Str[2].contains("true")) {
							Process_Item_Button.setEnabled(false);
							Graphics.Potential_Nums = Integer.valueOf(Input_Quantity);
							//Find the correct discount amount
							double discount;
                            if(Integer.valueOf(Input_Quantity) >4) {
                            	discount = .1;
                            	if(Integer.valueOf(Input_Quantity) >9) {
                            		discount = .15; 
                            		if(Integer.valueOf(Input_Quantity) >14) {
                            			discount = .2; 
                                	}	
                            	}
                            }
                            else {discount = 0;}
                            Input_Details.setText("Details for Item #"+ Num_Items+":");
                            //Simple truncation to display the percentage as an integer
                            int Display = (int) (discount*100);
                            
                            //Truncates the final value of the number of products
                            
                            BigDecimal b = new BigDecimal(( Integer.valueOf( Input_Quantity ) * Double.valueOf( Str[3] ) )*(Math.abs(discount-1)));
                            BigDecimal Display2 = b.setScale(2,RoundingMode.FLOOR);
                            Graphics.Potential_Total = Display2.doubleValue();
                           
                            //Set the Details in the Details textBox
							Input_Details_Text.setText
							        (
									Str[0]
									+
									Str[1]
									+
									" $"
									+
									Str[3]
									+
									" "
									+
									Input_Quantity
									+
									" "
									+
									Display
									+
									"% $ "
									+
									(Display2 )
									);
							Graphics.Potential_List = (String)Input_Details_Text.getText();
							Confirm_Item_Button.setEnabled(true);
						}
						//If the item is not in stock Disable buttons and clear input boxes
						else {
							Input_ID_Text.setText("");
							Input_Quantity_Text.setText("");
							Input_Details_Text.setText("");
							Input_Total_Text.setText("");
							Confirm_Item_Button.setEnabled(false);
							View_Item_Button.setEnabled(false);
							FinishOrder_Item_Button.setEnabled(false);
							JOptionPane.showMessageDialog(frame, "Item ID "+Input_ID_Value+" not in Stock", "Nile Dot Com-ERROR", 1);
						}
						
					}
					//If the value is not present in the list report an error and clear fields
					else {
						Input_ID_Text.setText("");
						Input_Quantity_Text.setText("");
						Input_Details_Text.setText("");
						Input_Total_Text.setText("");
						JOptionPane.showMessageDialog(frame, "Item ID "+Input_ID_Value+" not in file", "Nile Dot Com-ERROR", 1);
					}
					
				}
			}
		});
		
		//Confirm Item
		Confirm_Item_Button.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				//Reset text fields and Allow the user to either Finish the order review their order or add another item
				Input_ID_Text.setText("");
				Input_Quantity_Text.setText("");

				//Reset Buttons
				View_Item_Button.setEnabled(true);
				FinishOrder_Item_Button.setEnabled(true);
				Process_Item_Button.setEnabled(true);
				Confirm_Item_Button.setEnabled(false);
				
				//Add the item to the list of items to be bought
				String_List.add(Graphics.Potential_List);
				
				JOptionPane.showMessageDialog(frame, "Item #"+Num_Items+" Accepted. Added to your cart", "Nile Dot Com-Item Confirmed", 1);
				//Add 1 to items and refresh buttons
				Graphics.Num_Items++;
				Process_Item_Button.setText("Process Item#"+Num_Items);
				Confirm_Item_Button.setText("Confirm Item#"+Num_Items);
				
				//Refresh buttons and adds to Num_Total
				Graphics.Num_Total += Potential_Nums;
				Input_ID.setText("Enter item ID for item #"+ Num_Items+":");
				Input_Quantity.setText("Enter quantity for item #"+ Num_Items+":");
				Input_Total.setText("Order Subtotal for "+ Num_Total+" item(s):");
				
				//Update Total TextField
				Total += Potential_Total;
				Input_Total_Text.setText(Double.toString(Graphics.Total));
				
				
				
			}
		});
		
		//View Items
		View_Item_Button.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				//Iterate through String_List and print each item detail that the customer is buying
				StringBuffer sb = new StringBuffer();
				for(int x = 0; x < String_List.size();x++) {
					sb.append((x+1)+". "+String_List.get(x)+"\n");
				}
				JOptionPane.showMessageDialog(frame,sb, "Nile Dot Com-Current Shopping Cart Status", 1);
			}
		});
		
		//Finish order
		FinishOrder_Item_Button.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				//Set certain fields and buttons off
				Input_ID_Text.setEditable(false);
				Input_Quantity_Text.setEditable(false);
				Process_Item_Button.setEnabled(false);
				Confirm_Item_Button.setEnabled(false);
				FinishOrder_Item_Button.setEnabled(false);
				
				//Gets the users time zone and Date
				Calendar Cal = Calendar.getInstance();
				  TimeZone timezone = Cal.getTimeZone();
				   DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/YY HH:mm:ss");  
				   LocalDateTime now = LocalDateTime.now(); 
				   
				   
					//Iterate through String_List and print each item detail that the customer is buying
					StringBuffer sb = new StringBuffer();
					for(int x = 0; x < String_List.size();x++) {
						sb.append((x+1)+". "+String_List.get(x)+"\n");
					}
					
				JOptionPane.showMessageDialog(frame,(dtf.format(now)+" "+timezone.getDisplayName())+"\n"+"Number of Line Items: "+String_List.size()+"\n"+"Item#/ ID/ Title/ Price/ Qty/ Disc%/ Subtotal:\n\n"+sb+"\n\n\n"+"Order subtotal: $"+Total, "Nile Dot Com-Final Invoice", 1);
				Writes_To_File();
			}
		});
		
		//Empties all text boxes so the user can create a new order
		NewOrder_Item_Button.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				//Resets the state of the program to original
				Num_Items = 1;
				Num_Total = 0;
				Total = 0;
				String_List = new ArrayList<>();
				
				Process_Item_Button.setText("Process Item#"+Num_Items);
				Confirm_Item_Button.setText("Confirm Item#"+Num_Items);
				Input_ID.setText("Enter item ID for item #"+ Num_Items+":");
				Input_Quantity.setText("Enter quantity for item #"+ Num_Items+":");
				Input_Details.setText("Details for Item #"+ Num_Items+":");
				Input_Total.setText("Order Subtotal for "+ Num_Total+" item(s):");
				
				Input_ID_Text.setText("");
				Input_Quantity_Text.setText("");
				Input_Details_Text.setText("");
				Input_Total_Text.setText("");
				
				Process_Item_Button.setEnabled(true);
				Confirm_Item_Button.setEnabled(false);
				View_Item_Button.setEnabled(false);
				FinishOrder_Item_Button.setEnabled(false);
				Input_ID_Text.setEditable(true);
				Input_Quantity_Text.setEditable(true);
			}
		});
		
		//Exits the program
		Exit_Button.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				 System.exit(1);
			}
		});
		
		//Sets certain Buttons to Inactive
		Confirm_Item_Button.setEnabled(false);
		View_Item_Button.setEnabled(false);
		FinishOrder_Item_Button.setEnabled(false);
		
		//Get a good dimension size for the user since all buttons should be the same size this is only called once
		Dimension size = Process_Item_Button.getPreferredSize();
		
		//Set Button Location and size
		Process_Item_Button.setBounds(25, 300, size.width, size.height);
		Confirm_Item_Button.setBounds(150, 300, size.width, size.height);
		View_Item_Button.setBounds(275, 300, size.width, size.height);
		FinishOrder_Item_Button.setBounds(400, 300, size.width, size.height);
		NewOrder_Item_Button.setBounds(525, 300, size.width, size.height);
		Exit_Button.setBounds(650, 300, size.width, size.height);
		
		//Add buttons
		Pan.add(Process_Item_Button);
		Pan.add(Confirm_Item_Button);
		Pan.add(View_Item_Button);
		Pan.add(FinishOrder_Item_Button);
		Pan.add(NewOrder_Item_Button);
		Pan.add(Exit_Button);
	
	
	
	
	
	
	
	//Set Frame Settings
	
	//Exit Frame when closed
    frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    //Add Pan to Frame
    frame.add(Pan);
    //Set to fullscreen at first
    frame.setSize(1200, 500);
    //Make visible
    frame.setVisible(true);
	
}

public static void Writes_To_File() {
	
	//Gets the users time zone and Date data
	Calendar Cal = Calendar.getInstance();
	  TimeZone timezone = Cal.getTimeZone();
	   DateTimeFormatter dtf = DateTimeFormatter.ofPattern("ddMMYYYYHHmm");  
	   DateTimeFormatter dtf2 = DateTimeFormatter.ofPattern("MM/dd/YYYY");
	   DateTimeFormatter dtf3 = DateTimeFormatter.ofPattern("HH:mm:ss");
	   DateTimeFormatter dtf4 = DateTimeFormatter.ofPattern("a");
	   LocalDateTime now = LocalDateTime.now(); 
	   
	StringBuffer sb = new StringBuffer();
	for(int x = 0; x < String_List.size();x++) {
		
		sb.append(dtf.format(now)+", "+String_List.get(x)+", "+dtf2.format(now)+","+dtf3.format(now)+dtf4.format(now)+" "+timezone.getDisplayName()+"\n");
	}
	out.println(sb);
	out.close();
}

//Returns StringArray if the Input_ID is in Inventory or null if it is not
public static String[] Find_ID(File file, String Input_ID){
Scanner Reads;
try {
	Reads = new Scanner(file);
	//Read until comma
	Reads.useDelimiter(",|\\n");
	//Reads the entire file until end
	while(Reads.hasNextLine()) {
		//Read until comma
		String STR = Reads.next();
		//If we find the correct ID else skip to the next line as we only need to read IDs here
		if(STR.equals(Input_ID)) {
			//Read into the array the line
			String[] STR_Array = {STR,Reads.next(),Reads.next(),Reads.next()};
			Reads.close();
			return STR_Array;
		}
		else {
			Reads.nextLine();
		}
	}
  //If the File cant be found end
} catch (FileNotFoundException e) {
	e.printStackTrace();
	System.exit(0);
}
	
return null;	
}

}
