package br.com.infoserver.testccee.view;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.image.BufferedImage;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import br.com.infoserver.testccee.commons.Constants;
import br.com.infoserver.testccee.service.FuncionarioService;

public class MainGUIDesktop implements ActionListener, PropertyChangeListener { 

	JPanel cards; 

	JButton systemButton;
	JButton classButton;
	JButton sequenceButton;

	String systemButtonLabel = "System";
	String classButtonLabel = "Class Diagram";
	String sequenceButtonLabel = "Sequence Diagram";
	
	String name;
	String database;
	String user;
	String password;
	
	JTable table;
	FuncionariosTableModel model;
	
	JTextArea console;

	private FuncionarioService funcionarioService = new FuncionarioService();
	
	public FuncionarioService getFuncionarioService() {
		return funcionarioService;
	}

	public void setFuncionarioService(FuncionarioService funcionarioService) {
		this.funcionarioService = funcionarioService;
	}
	
	public void addComponentToPane(Container pane) {

		systemButton = new JButton(systemButtonLabel);
		systemButton.addActionListener(this);

		classButton  = new JButton(classButtonLabel);
		classButton.addActionListener(this);

		sequenceButton  = new JButton(sequenceButtonLabel);
		sequenceButton.addActionListener(this);
		
		console = new JTextArea();
		console.setBackground(Color.WHITE);
		console.setForeground(Color.BLUE);			
		console.setBounds(500,152,380,180);
		console.setEditable(true);
		console.setEnabled(true);
		console.setAutoscrolls(true);
		
		JScrollPane sp = new JScrollPane(console);
		sp.setBounds(500,120+30+2,380,180);
		
		table = new JTable();
		table.setModel(new FuncionariosTableModel());
		table.setBounds(20,200,460,130);
		
		JScrollPane spTable = new JScrollPane();
		spTable.setViewportView(table);
		spTable.setBounds(20,200,460,130);
		
		readInformation();
		
		JLabel nameLabel = new JLabel( new StringBuilder("name:").append(this.name).toString() );
		nameLabel.setBounds(500,30,100,10);
		JLabel dateLabel = new JLabel( new StringBuilder("date:").append(Constants.DEFAULT_DATETIME).toString() );
		dateLabel.setBounds(500,45,200,10);
		JLabel databaseLabel = new JLabel(new StringBuilder("database:").append(this.database).toString());
		databaseLabel.setBounds(500,70,400,12);
		JLabel userLabel  = new JLabel(new StringBuilder("user:").append(this.user).toString());
		userLabel.setBounds(500,86,400,12);
		JLabel passwordLabel  = new JLabel(new StringBuilder("password:").append(this.password).toString());
		passwordLabel.setBounds(500,101,400,12);
		
		systemButton.setBounds(30, 152, 140, 30);
		classButton.setBounds(180, 152, 140, 30);
		sequenceButton.setBounds(330, 152, 150, 30);
			
		BufferedImage logoTitle = null;
		try {
			logoTitle = ImageIO.read(new File("./resources/logoTitle.png"));
			JLabel logoTitleLabel = new JLabel(new ImageIcon( logoTitle ));
			logoTitleLabel.setBounds(0, 0, 500, 150);
			pane.add(logoTitleLabel, BorderLayout.PAGE_START);
		} catch (IOException e) {
			String msg = "Wrong installation. The executable file must be parallel to resources/ directory. The application will try to run anyway.";
			JOptionPane.showMessageDialog(new JFrame(),msg,"Error",JOptionPane.ERROR_MESSAGE);
		}
		pane.setLayout(null);
		pane.add(systemButton);
		pane.add(classButton);
		pane.add(sequenceButton);
		pane.add(nameLabel);
		pane.add(dateLabel);
		pane.add(databaseLabel);
		pane.add(userLabel);
		pane.add(passwordLabel);
		pane.add(sp);
		pane.add(spTable);
		
	}

	public void itemStateChanged(ItemEvent evt) {
		CardLayout cl = (CardLayout)(cards.getLayout());
		cl.show(cards, (String)evt.getItem());
	}

	public void readInformation(){
		
		try { 
			FileReader file = new FileReader(new File("./resources/information.txt")); 
			BufferedReader readedFile = new BufferedReader(file); 
			String line = readedFile.readLine();  
			while (line != null) { 
				//System.out.printf("%s\n", line );
				 
				name = line.substring(0,line.indexOf(":")).equals("name") ? line.substring(line.indexOf(":")+1,line.length()) : name ;
				database = line.substring(0,line.indexOf(":")).equals("database") ? line.substring(line.indexOf(":")+1,line.length()) : database ;
				user = line.substring(0,line.indexOf(":")).equals("user") ? line.substring(line.indexOf(":")+1,line.length()) : user ;
				password = line.substring(0,line.indexOf(":")).equals("password") ? line.substring(line.indexOf(":")+1,line.length()) : password ;
				
				line = readedFile.readLine();  
				} 
			file.close(); 
			} catch (IOException e) { 
				System.err.printf("Erro na abertura do arquivo: %s.\n", e.getMessage()); 
				System.out.println();  
			}						
		}
	
	/**
	 * Create the GUI and show it.  For thread safety,
	 * this method should be invoked from the
	 * event dispatch thread.
	 */
	private static void createAndShowGUI() {

		JFrame frame = new JFrame(Constants.APP_NAME+" - "+Constants.APP_VERSION);
	
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		MainGUIDesktop gui = new MainGUIDesktop();
		gui.addComponentToPane(frame.getContentPane());

//		ImageIcon icon = new ImageIcon("./resources/testccee.png");
//		frame.setIconImage(icon.getImage());

		frame.pack(); 
		frame.setSize(900, 400);
		frame.setResizable(false);
		frame.setVisible(true);	

	}

	public static void main(String[] args) {

		try {
			UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
		} catch (UnsupportedLookAndFeelException ex) {
			ex.printStackTrace();
		} catch (IllegalAccessException ex) {
			ex.printStackTrace();
		} catch (InstantiationException ex) {
			ex.printStackTrace();
		} catch (ClassNotFoundException ex) {
			ex.printStackTrace();
		}

		UIManager.put("swing.boldMetal", Boolean.FALSE);

		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				createAndShowGUI();
			}
		});				
	}

	@Override
	public void actionPerformed(ActionEvent e) {	
		try {
			if(e.getSource() == systemButton){
				model = new FuncionariosTableModel(this.getFuncionarioService().selectEmployeeByNetSalary());
				table.setModel(model);
				table.setAutoCreateRowSorter(true);
				
			}	
		} catch (Exception ex){
			ex.printStackTrace();
		}
	}

	@Override
	public void propertyChange(PropertyChangeEvent arg0) {
//		if (!done){
//			progressBar.setString("running...");
//			progressBar.setIndeterminate(true);
//		} else {
//			progressBar.setIndeterminate(false);
//			done = false;
//		}
	}

}