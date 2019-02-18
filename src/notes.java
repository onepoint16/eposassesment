//DSSA Assessment plan 2019
//What should be developed:
//* At least 2 event handlers
//* 2 standard libraries
//* Range of operators
//* Selection and repetition constructs
//* Internal documentation and noting
//
//What will be handed in:
//* User interface design
//* The code produced
//* Evidence of the working app
//* Usability test
//* Further recommendations based on usability tests
//
//Stage 1: Design application screens using mock up software and present this to lecturer
//
//Stage 2: Write the user stories (requirements) for this app
//
//Stage 3: Create the application
//
//Stage 4: Create and run test cases that prove your application as working. These should include full screenshots of your working app.
//
//Stage 5: Test and Deploy (Create a List folder and ensure the application runs independently of IDE
//
//Stage 6: Run a usability test with at least 3 users. Summarise and present your findings in a report.
//
//Mission Brief:
//
//Design an electronic point of sale application (EPOS). There must be access for 2 types of user. Sales agent and Manager.
//
//1. Security
//* Login screen to authenticate user on start up. 
//* Only valid users will be granted entry
//2. All user features
//* Users will be able to view their account details
//* User will be able to view their sales reports
//* Total customers served, Average sales cost, Minimum sales cost, Maximum sales cost and Total sales takings
//3. Manager features
//* Should be able to create, update or delete users/products
//* Can perform restart which deletes all sales data from the database
//4. Sales features
//* Select products and checkout
//* At least 9 products will be offered for sale
//* A running total of the order will be displayed on the screen
//* Checkout button to complete the order
//* Orders can be cancelled by the agent.
//
//______________________________________________________________________________________
//
//Code for Sections: Taken from C# DVD takings list
//
//Calcs Class Page:
//
//Creating a list: 
//
//List<decimal> PriceList = new List<decimal>();
//
//Get Price:
//
//        public decimal getPrice(string priceText)
//        {
//            decimal price = decimal.Parse(priceText);
//
//            return price;
//        }
//
//Add Price:
//
//        public void addPrice(decimal price)
//        {
//            PriceList.Add(price);
//        }
//
//Calculation total: 
//
//public decimal calcTotal()
//        { 
//            decimal total = 0;
//
//            for (int i = 0; i < PriceList.Count; i++)
//            {
//                total = total + (PriceList[i]);
//            }
//
//            return total;
//        } 
//
//Count Prices:
//
//        public int countPrices()
//        {
//            return PriceList.Count();
//        }
//
//Calculate Average:
//
//        public decimal calcAvg()
//        {
//            return PriceList.Sum() / PriceList.Count();
//        }
//
//Clear Price List:
//
//        public void clearPriceList()
//        {
//            PriceList.Clear();
//        }
//
//Calculating highest:
//
//public decimal getHighest()
//        {
//            
//            decimal highest = 0;
//            
//            for (int i = 0; i < PriceList.Count; i++)
//            {
//                if (PriceList[i] > highest)
//                {
//                    highest = PriceList[i];
//                }
//            }
//
//            return highest;
//        }
//
//Calculating Lowest:
//
//        public decimal getLowest()
//        {
//           
//            decimal lowest = PriceList[0];
//
//            for (int i = 0; i < PriceList.Count; i++)
//            {
//                if (PriceList[i] < lowest)
//                {
//                    lowest = PriceList[i];
//                }
//            }
//
//            return lowest;
//        }
//
//Form Designer:
//
//Add Button:
//
//        private void btnAdd_Click(object sender, EventArgs e)
//        {
//
//            try
//            {
//                decimal price = doCalcs.getPrice(txtAdd.Text);
//
//                doCalcs.addPrice(price);
//
//                decimal total = doCalcs.calcTotal();
//
//                lblCount.Text = doCalcs.countPrices().ToString();
//
//                lblAvg.Text = doCalcs.calcAvg().ToString("c");
//
//                displayTakings(total);
//
//                lstTakings.Items.Add(price);
//
//                lblHighest.Text = doCalcs.getHighest().ToString("c");
//
//                lblLowest.Text = doCalcs.getLowest().ToString("c");
//
//            }
//            catch (Exception E)
//            {
//                MessageBox.Show("Enter something correct, Stoopid", "Something Right Stoopid");
//                Console.WriteLine(E);
//            }
//
//        }
//
//Display Button:
//
//        public void displayTakings(decimal total)
//            {
//                txtTotal.Text = "Total is " + total.ToString("c");
//
//                txtAdd.Clear();
//                txtAdd.Focus();
//            }
//
//Clear Button:
//
//            private void btnClear_Click(object sender, EventArgs e)
//            {
//                lstTakings.ClearSelected();
//                lblAvg.Text = " ";
//                lblCount.Text = " ";
//                txtAdd.Clear();
//                txtAdd.Focus();
//                lstTakings.Items.Clear();
//                txtTotal.Text = null;
//                doCalcs.clearPriceList();
//                lblHighest.Text = null;
//                lblLowest.Text = null;
//            }
//
//______________________________________________________________________________________
//
//
//Code for section taken from Java programme CRUD
//
//Imports:
//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.sql.Statement;
//import javax.swing.JOptionPane;
//import net.proteanit.sql.DbUtils;
//
//
//Tony’s Pathways:
//    String home = System.getProperty("user.dir");
//    String dbpath = "jdbc:ucanaccess:////"+ home + "/src/asia.accdb”;
//
//Option pane:
//
//            JOptionPane.showMessageDialog(null, "You have successfully added " + firstName + " " + lastName + " from the table", "Added", JOptionPane.INFORMATION_MESSAGE);
//Load table:
//    public void RefreshResults()
//    {
//        try 
//        {
//            Connection con = DriverManager.getConnection(dbpath);
//            Statement stmt = con.createStatement();
//            ResultSet rs = stmt.executeQuery ("SELECT * FROM asia");
//            tblDisplay.setModel(DbUtils.resultSetToTableModel(rs));
//        }
//        catch (SQLException e) 
//        {
//        System.out.println(e);
//        }
//    }
//Put Database items in combobox:
//Put loadCombo(); in Pre adding code in combobox
//Add a loadCombo method:
//    public void loadCombo() {
//         String home = System.getProperty("user.dir");
//    String dbpath = "jdbc:ucanaccess:////"+ home + "/src/asia.accdb";
//    int rows = 0;
//        try 
//            
//        {  
//            comboID.removeAllItems();    
//            Connection con = DriverManager.getConnection (dbpath); 
//            Statement stmt = con.createStatement();
//            ResultSet rs = stmt.executeQuery ("SELECT ID FROM asia");
//            while (rs.next()) 
//            {
//                comboID.addItem(rs.getString("ID"));
//                rows++;
//            }
//        }
//         catch(SQLException e)
//            {
//                System.out.println("SQL exception occured in combo loading = " + e);
//            }
//
//    }
//Then in the combo action performer:
// String code = (String) comboID.getSelectedItem();
//            try 
//                {
//                    Connection con = DriverManager.getConnection (dbpath); 
//                    Statement stmt = con.createStatement();
//                    ResultSet rs = stmt.executeQuery ("SELECT FirstName, LastName FROM asia WHERE ID='"+code+"'");
//                    String first = "FirstName";
//                    String second = "LastName";
//                    while (rs.next()){
//                        txtFirstNameUpdate.setText(rs.getString(first));
//                        txtLastNameUpdate.setText(rs.getString(second));
//                        txtFirstNameAdd.setText(rs.getString(first));
//                        txtLastNameAdd.setText(rs.getString(second));
//                        txtFirstNameDelete.setText(rs.getString(first));
//                        txtLastNameDelete.setText(rs.getString(second));
//                    }
//                }
//            catch(SQLException e)
//                {
//                    System.out.println("SQL exception occured: " + e);
//                }
//
//Update items in database:
//Update method:
//    public void updateTable() {
//        try {
//            Connection con = DriverManager.getConnection(dbpath);
//            Statement stmt = con.createStatement();
//            String firstName = txtFirstNameUpdate.getText();
//            String lastName = txtLastNameUpdate.getText();
//            int idPlace = comboID.getSelectedIndex();
//            String ID = comboID.getItemAt(idPlace);
//            String updateStatement = "UPDATE asia SET FirstName='" + firstName + "', LastName='" + lastName +"' WHERE ID='" + ID + "'";
//            stmt.executeUpdate(updateStatement);
//            JOptionPane.showMessageDialog(null, "You have successfully updated " + firstName + " " + lastName + " from the table", "Update", JOptionPane.INFORMATION_MESSAGE);
//        }
//        catch (SQLException E){
//            System.out.println(E);
//        }
//    }
//
//Update button:
//        try {
//            updateTable();
//            loadCombo();
//            RefreshResults();
//        }
//        catch (Exception E) {
//            System.out.println(E);
//        }
//
//Delete items from database in button:
//
//        try {
//            Connection con = DriverManager.getConnection(dbpath);
//            Statement stmt = con.createStatement();
//            String firstName = txtFirstNameDelete.getText();
//            String lastName = txtLastNameDelete.getText();
//            String del = "DELETE FROM asia WHERE (FirstName='"+firstName+"' AND LastName='"+ lastName +"')";
//            stmt.executeUpdate(del);
//            System.out.println(del);
//            JOptionPane.showMessageDialog(null, "You have successfully deleted " + firstName + " " + lastName + " from the table", "Deleted", JOptionPane.INFORMATION_MESSAGE);
//            loadCombo();
//        }
//        catch (SQLException e){
//            System.out.println(e);
//        }
//
//        RefreshResults();
//
//Add items to database in button:
//
//        try {
//            Connection con = DriverManager.getConnection(dbpath);
//            Statement stmt = con.createStatement();
//            String firstName = txtFirstNameAdd.getText();
//            String lastName = txtLastNameAdd.getText();
//            String add = "INSERT INTO asia(FirstName, LastName) VALUES ('" + firstName + "','" + lastName + "')";
//            stmt.executeUpdate(add);
//            JOptionPane.showMessageDialog(null, "You have successfully added " + firstName + " " + lastName + " from the table", "Added", JOptionPane.INFORMATION_MESSAGE);
//            loadCombo();
//        }
//        catch (SQLException e){
//            System.out.println(e);
//        }
//
//        RefreshResults();
//
//Search for items and display the results in the table in search button:
//
//        String first = txtFirstNameSearch.getText();
//        String second = txtLastNameSearch.getText();
//        if(second.isEmpty()){
//        try 
//            {
//                Connection con = DriverManager.getConnection (dbpath); 
//                Statement stmt = con.createStatement();
//
//                ResultSet rs = stmt.executeQuery ("SELECT * FROM asia WHERE FirstName='" + first + "'");
//                tblDisplay.setModel(DbUtils.resultSetToTableModel(rs));
//                txtFirstNameSearch.setText(null);
//                txtLastNameSearch.setText(null);
//               }
//         catch(SQLException e)
//            {
//                System.out.println("SQL exception occured" + e);
//            }
//        }else if(first.isEmpty()){
//            try 
//            {
//                Connection con = DriverManager.getConnection (dbpath); 
//                Statement stmt = con.createStatement();
//
//                ResultSet rs = stmt.executeQuery ("SELECT * FROM asia WHERE LastName='" + second + "'");
//                tblDisplay.setModel(DbUtils.resultSetToTableModel(rs));
//                txtFirstNameSearch.setText(null);
//                txtLastNameSearch.setText(null);
//               }
//         catch(SQLException e)
//            {
//                System.out.println("SQL exception occured" + e);
//            }
//        }else{
//            try 
//            {
//                Connection con = DriverManager.getConnection (dbpath); 
//                Statement stmt = con.createStatement();
//
//                ResultSet rs = stmt.executeQuery ("SELECT * FROM asia WHERE FirstName='" + first + "' AND LastName='" + second + "'");
//                tblDisplay.setModel(DbUtils.resultSetToTableModel(rs));
//                txtFirstNameSearch.setText(null);
//                txtLastNameSearch.setText(null);
//               }
//         catch(SQLException e)
//            {
//                System.out.println("SQL exception occured" + e);
//            }
//        }
