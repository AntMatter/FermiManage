import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.SwingConstants;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JSeparator;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class FermiManage_CheckOUT_Window
{

	JFrame frmFermimanage;
	private JTextField txtBookNumber;
	private JTextField txtBookTitle;
	private JTextField txtStudentIdNumber;
	private JTextField txtFirstName;
	private JTextField txtLastName;
	private JLabel lblStudentIdNumber;
	private JLabel lblBookNumber;
	LibSoft libby = new LibSoft();

	Book book = new Book(null, null);
	User user = new User(null, null, null);

	/**
	 * Launch the application.
	 */

	int formMode = 0;
	Transaction tran;
	private JLabel lblStatus;
	private JTextField txtIsbnNumber;
	private JLabel lblIsbnNumber;
	private JTable table;

	/*
	 * Main method creates a new window, then initializes it
	 */
	public static void main(String[] args)
	{
		EventQueue.invokeLater(new Runnable()
		{
			public void run()
			{
				try
				{
					FermiManage_CheckOUT_Window window = new FermiManage_CheckOUT_Window();
					window.frmFermimanage.setVisible(true);
				} catch (Exception e)
				{
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public FermiManage_CheckOUT_Window()
	{

		try
		{
			File dir = new File("Data");
			dir.mkdir();
			File f = new File("Data/library.txt");
			f.createNewFile();
			f = new File("Data/users.txt");
			f.createNewFile();
			f = new File("Data/library.txt");
			f.createNewFile();
			f = new File("Data/transactions.txt");
			f.createNewFile();
			f = new File("Data/completed_transactions.txt");
			f.createNewFile();
		} catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize()
	{
		frmFermimanage = new JFrame();
		frmFermimanage.setTitle("FermiManage");
		frmFermimanage.setBounds(100, 100, 1118, 415);
		frmFermimanage.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		JLabel lblLibresoft = new JLabel("FermiManage");
		lblLibresoft.setFont(new Font("Trebuchet MS", Font.BOLD, 18));

		JLabel lblCheckOut = new JLabel("");
		lblCheckOut.setFont(new Font("Trebuchet MS", Font.PLAIN, 18));

		JButton btnCheckOut = new JButton("Submit");

		/*
		 * Checks to see if the mouse has been clicked inside text box before
		 * highlighting the text inside
		 */
		txtBookNumber = new JTextField();
		txtBookNumber.addMouseListener(new MouseAdapter()
		{
			@Override
			public void mouseClicked(MouseEvent e)
			{
				txtBookNumber.selectAll();
			}
		});

		/*
		 * Checks to see if an the enter key is pressed on text box, used to submit data
		 * for verification and to move to the next text box
		 */
		txtBookNumber.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{

				// Searches for the transaction in the file using the transactionList in libby
				tran = libby.transactionList.searchForTransaction(txtBookNumber.getText());

				// Make sure transaction exists
				if (tran != null)
					book = libby.library.searchForBook(tran.getBookID());
				if (book == null || tran == null)
				{

					// The transaction does not exist, Set window to Check out mode
					txtIsbnNumber.setEditable(true);
					txtIsbnNumber.setEnabled(true);
					txtStudentIdNumber.setEditable(true);
					txtStudentIdNumber.setEnabled(true);
					lblCheckOut.setText("Check Out");
					btnCheckOut.setText("Check Out");

					txtIsbnNumber.requestFocusInWindow();
					txtIsbnNumber.selectAll();
				}

				else
				{
					// transaction and book found, Set to check in mode
					txtBookTitle.setText(book.getBookName());
					txtIsbnNumber.setText(tran.getBookID());
					txtStudentIdNumber.setText(tran.getUserID());
					txtBookTitle.setEditable(false);
					txtBookTitle.setEnabled(false);
					txtFirstName.setEnabled(false);
					txtFirstName.setEditable(false);
					txtLastName.setEnabled(false);
					txtLastName.setEditable(false);
					txtStudentIdNumber.setEditable(false);
					txtStudentIdNumber.setEnabled(false);
					txtIsbnNumber.setEnabled(false);
					txtIsbnNumber.setEditable(false);

					lblCheckOut.setText("Check In");
					btnCheckOut.setText("Check In");

				}

				String userID = txtStudentIdNumber.getText();

				user = libby.userList.searchForUser(userID);
				if (user == null)
				{
					// This is an error that should never occur
					System.out.println("Error, user is null in check in mode");

				} else
				{
					// AutoFill text boxes
					txtFirstName.setText(user.getFirstName());
					txtLastName.setText(user.getLastName());

				}

			}
		});

		txtBookNumber.setColumns(10);

		/*
		 * Event Listener for Enter Key for the BookTitle text box
		 */
		txtBookTitle = new JTextField();
		txtBookTitle.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				// Sets the cursor in the next window and highlights the text in that window
				txtStudentIdNumber.requestFocusInWindow();
				txtStudentIdNumber.selectAll();
			}
		});
		txtBookTitle.setEnabled(false);
		txtBookTitle.setEditable(false);
		txtBookTitle.setText("Book Title");
		txtBookTitle.setColumns(10);

		txtStudentIdNumber = new JTextField();
		txtStudentIdNumber.setEnabled(false);
		txtStudentIdNumber.setEditable(false);

		/*
		 * Checks to see if the mouse has been clicked inside text box before
		 * highlighting the text inside
		 */
		txtStudentIdNumber.addMouseListener(new MouseAdapter()
		{
			@Override
			public void mouseClicked(MouseEvent e)
			{
				txtStudentIdNumber.selectAll();
			}
		});

		txtStudentIdNumber.setColumns(10);

		txtFirstName = new JTextField();
		/*
		 * Event Listener for Enter Key for FirstName text box
		 * 
		 */
		txtFirstName.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				txtLastName.requestFocusInWindow();
				txtLastName.selectAll();
			}
		});

		txtFirstName.setEnabled(false);
		txtFirstName.setEditable(false);
		txtFirstName.setText("First Name");
		txtFirstName.setColumns(10);

		txtLastName = new JTextField();

		txtLastName.setEnabled(false);
		txtLastName.setText("Last Name");
		txtLastName.setEditable(false);
		txtLastName.setColumns(10);
		/*
		 * Event Listener for the submit button on the form
		 */
		btnCheckOut.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				/*
				 * Check In Button Pressed
				 * 
				 */

				/*
				 * The way the form decides what mode to use is decided by seeing what sections
				 * of the form are editable. Check in should always have only the txtBookNumber
				 * feild editable while Check out can have a number of editable sections, with
				 * the txtStudentIdNumber always being editable
				 */
				if (!txtStudentIdNumber.isEditable())
				{
					/*
					 * Check to make sure no items are null
					 * 
					 * This prevents user end crashes
					 */
					if (tran != null && book != null && user != null)
					{
						// Checks in the book and moves transaction to the completed file
						libby.checkIn(book, user, tran.getuniqueID());
						lblStatus.setText("Success, Book was checked back in");
						lblStatus.setForeground(Color.GREEN);

						// Resets all text boxes back to default values and moves focus back to the top
						// of the form
						txtBookTitle.setText("Book Title");
						txtIsbnNumber.setText("Book ISBN Number");
						txtFirstName.setText("First Name");
						txtLastName.setText("Last Name");
						txtStudentIdNumber.setText("Student ID Number");
						txtBookNumber.setText("");
						lblCheckOut.setText("");
						btnCheckOut.setText("Submit");
						txtBookNumber.requestFocusInWindow();

					} else
					{
						/*
						 * Failed check in for null values, should only happen if file is corrupted
						 */
						lblStatus.setText("Error Information is null or does not exist, Please verify file integrity");
						lblStatus.setForeground(Color.RED);
					}

					/*
					 * Because the txtStudentIdNumber is not editable, we must be in check out mode
					 */
				} else
				{
					// Prevents user side crash by checking null values
					if (txtBookNumber != null)
					{

						/*
						 * Creates a user in the file based on user inputs
						 */
						if (txtFirstName.isEditable() && txtStudentIdNumber != null && txtFirstName != null
								&& txtLastName != null)
						{
							user = libby.newUser(txtStudentIdNumber.getText(), txtFirstName.getText(),
									txtLastName.getText());
						}
						/*
						 * Creates book in the file based on user inputs
						 */
						if (txtBookTitle.isEditable() && txtIsbnNumber != null && txtBookTitle != null)
						{
							book = libby.newBook(txtIsbnNumber.getText(), txtBookTitle.getText());
						}

						// More null value checks
						if (book != null && user != null)
						{
							/*
							 * Checks to see if the book is already checked out This error should not occur
							 * unless the checkIn section is broken
							 */
							if (!libby.checkBookStatus(book, user, txtBookNumber.getText()))
							{
								// FAILURE, DISPLAY CHECKED OUT MESSAGE
								lblStatus.setText("Failure, All copies of this book are currently checked out");
								lblStatus.setForeground(Color.RED);
							} else
							/*
							 * If book is not checked out, then we can check it out to the user
							 */
							{

								// Adds transaction info to the transactions.txt file
								libby.transactionList.addTransaction(new Transaction(book.getBookID(), user.getUserId(),
										book.getBookName(), txtBookNumber.getText()));
								libby.updateLibrary(book, 1);

								// SUCCESS DISPLAY GREEN LINE
								lblStatus.setText("Success, Book Was Checked Out");
								lblStatus.setForeground(Color.GREEN);

								// Resets forms to original state, including editability
								txtBookNumber.setText("");
								txtBookTitle.setText("Book Title");
								txtFirstName.setText("First Name");
								txtLastName.setText("Last Name");
								txtStudentIdNumber.setText("");
								txtIsbnNumber.setText("");
								txtBookTitle.setEditable(false);
								txtBookTitle.setEnabled(false);
								txtFirstName.setEnabled(false);
								txtFirstName.setEditable(false);
								txtLastName.setEnabled(false);
								txtLastName.setEditable(false);
								txtStudentIdNumber.setEditable(false);
								txtStudentIdNumber.setEnabled(false);
								txtIsbnNumber.setEnabled(false);
								txtIsbnNumber.setEditable(false);
								lblCheckOut.setText("");
								btnCheckOut.setText("Submit");
								txtBookNumber.requestFocusInWindow();

							}
						} else
						// This usually only occurs if a field is left blank
						{
							lblStatus.setText("Failure, Please fill out all required inputs");
							lblStatus.setForeground(Color.RED);
						}
					}
				}
			}
		});

		/*
		 * Enter Key listener for studentIdNumber text box
		 */
		txtStudentIdNumber.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				// Searches for user
				String userID = txtStudentIdNumber.getText();
				user = libby.userList.searchForUser(userID);
				// if not found, create new user
				if (user == null)
				{
					txtFirstName.setEnabled(true);
					txtFirstName.setEditable(true);
					txtLastName.setEnabled(true);
					txtLastName.setEditable(true);

					txtFirstName.requestFocusInWindow();
					txtFirstName.selectAll();

				} else
				{
					// Autofill
					txtFirstName.setText(user.getFirstName());
					txtLastName.setText(user.getLastName());

					btnCheckOut.requestFocusInWindow();

				}
			}
		});

		lblStudentIdNumber = new JLabel("Student ID Number");

		lblBookNumber = new JLabel("Book Number");

		lblStatus = new JLabel("");
		lblStatus.setForeground(Color.GREEN);
		lblStatus.setBackground(Color.GREEN);
		lblStatus.setHorizontalAlignment(SwingConstants.CENTER);
		lblStatus.setFont(new Font("Trebuchet MS", Font.PLAIN, 15));

		txtIsbnNumber = new JTextField();
		txtIsbnNumber.setEnabled(false);
		txtIsbnNumber.setEditable(false);

		/*
		 * Mouse Click Event for txtISBNnumber
		 */
		txtIsbnNumber.addMouseListener(new MouseAdapter()
		{
			@Override
			public void mouseClicked(MouseEvent e)
			{
				txtIsbnNumber.selectAll();
			}
		});

		/*
		 * Enter key event for ISBN Number text box
		 */
		txtIsbnNumber.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{

				String id = txtIsbnNumber.getText();
				book = libby.library.searchForBook(id);
				// Create new book
				if (book == null)
				{
					txtBookTitle.setEditable(true);
					txtBookTitle.setEnabled(true);
					txtBookTitle.requestFocusInWindow();
					txtBookTitle.selectAll();
				} else
				// Autofill
				{
					txtBookTitle.setText(book.getBookName());
					txtStudentIdNumber.requestFocusInWindow();
					txtStudentIdNumber.selectAll();
				}
			}
		});
		txtIsbnNumber.setColumns(10);

		lblIsbnNumber = new JLabel("ISBN Number");
		
		JSeparator separator = new JSeparator();
		separator.setOrientation(SwingConstants.VERTICAL);
		separator.setBackground(new Color(0, 0, 0));
		separator.setForeground(new Color(0, 0, 0));
		
		JLabel lblListOfChecked = new JLabel("List of Checked out Books");
		lblListOfChecked.setFont(new Font("Trebuchet MS", Font.BOLD, 18));
		
		JButton btnUpdate = new JButton("Update");
		btnUpdate.addActionListener(new ActionListener() {
			//Update Button Pressed
			public void actionPerformed(ActionEvent e) {
				//String[] columnNames = {"Book Number",  "Book Title", "Book ISBN", "Student ID", "First Name", "Last Name", "Date Checked Out"};
				((DefaultTableModel)table.getModel()).setRowCount(0);
				ArrayList<String[]> transactions = libby.transactionList.getAllOpenTransactions();
				if(transactions.size() > 0)
				{
				String[][] data = new String[transactions.size()][transactions.get(0).length];
				 
				for(int i = 0; i < transactions.size();i++)
				{
				//	System.out.println(Arrays.toString(transactions.get(i)));
				//	System.out.println(transactions.get(i).length);
				//	System.out.println(i);
					for(int j =0; j<transactions.get(i).length-1; j++)
					{
						System.out.println(transactions.get(i)[j]);
						data[i][j] = transactions.get(i)[j];
					}
					String[] tmp = transactions.get(i);
					User s = libby.userList.searchForUser(tmp[1]);
					((DefaultTableModel)table.getModel()).addRow(new String[]{tmp[3], tmp[0], tmp[2],tmp[1],s.getFirstName(),s.getLastName(),tmp[4]});
				}
				
				}
			}
		});
		
		JScrollPane scrollPane = new JScrollPane();
		
		JScrollPane scrollPane_1 = new JScrollPane();
		GroupLayout groupLayout = new GroupLayout(frmFermimanage.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(10)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(200)
							.addComponent(lblLibresoft))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(216)
							.addComponent(lblCheckOut))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(41)
							.addComponent(lblBookNumber)
							.addGap(95)
							.addComponent(lblIsbnNumber, GroupLayout.PREFERRED_SIZE, 140, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(41)
							.addComponent(txtBookNumber, GroupLayout.PREFERRED_SIZE, 153, GroupLayout.PREFERRED_SIZE)
							.addGap(6)
							.addComponent(txtIsbnNumber, GroupLayout.PREFERRED_SIZE, 140, GroupLayout.PREFERRED_SIZE)
							.addGap(6)
							.addComponent(txtBookTitle, GroupLayout.PREFERRED_SIZE, 161, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(41)
							.addComponent(lblStudentIdNumber))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(41)
							.addComponent(txtStudentIdNumber, GroupLayout.PREFERRED_SIZE, 153, GroupLayout.PREFERRED_SIZE)
							.addGap(6)
							.addComponent(txtFirstName, GroupLayout.PREFERRED_SIZE, 122, GroupLayout.PREFERRED_SIZE)
							.addGap(6)
							.addComponent(txtLastName, GroupLayout.PREFERRED_SIZE, 142, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(255)
							.addComponent(btnCheckOut))
						.addComponent(lblStatus, GroupLayout.PREFERRED_SIZE, 535, GroupLayout.PREFERRED_SIZE))
					.addGap(4)
					.addComponent(separator, GroupLayout.PREFERRED_SIZE, 10, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblListOfChecked)
							.addGap(34)
							.addComponent(btnUpdate))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(scrollPane_1, GroupLayout.PREFERRED_SIZE, 513, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
					.addGap(209))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(29)
							.addComponent(lblLibresoft)
							.addGap(11)
							.addComponent(lblCheckOut)
							.addGap(32)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(lblBookNumber)
								.addComponent(lblIsbnNumber))
							.addGap(6)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(txtBookNumber, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(txtIsbnNumber, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(txtBookTitle, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addGap(22)
							.addComponent(lblStudentIdNumber)
							.addGap(6)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(txtStudentIdNumber, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(txtFirstName, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(txtLastName, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addGap(65)
							.addComponent(btnCheckOut)
							.addGap(18)
							.addComponent(lblStatus, GroupLayout.PREFERRED_SIZE, 56, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(11)
							.addComponent(separator, GroupLayout.PREFERRED_SIZE, 346, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(36)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(lblListOfChecked)
								.addGroup(groupLayout.createSequentialGroup()
									.addGap(2)
									.addComponent(btnUpdate)))
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(scrollPane_1, GroupLayout.PREFERRED_SIZE, 283, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(19, Short.MAX_VALUE))
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap(197, Short.MAX_VALUE)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(177))
		);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Book ID", "ISBN", "Title", "Student ID", "First Name", "Last Name", "Date"
			}
		));
		scrollPane_1.setViewportView(table);
		frmFermimanage.getContentPane().setLayout(groupLayout);
	}
}
