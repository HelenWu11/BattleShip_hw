package hw3;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.Socket;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.KeyStroke;
import javax.swing.filechooser.FileNameExtensionFilter;

import hw3.User_Interface.ColorAction;
import hw3.User_Interface.TestAction;



public class Start_Application {
	private JFrame Startfromhere;
	private JFrame available_players;
	private JFrame wait_for_connection;
	private JFrame connect_to_frame;
	private User_Interface user_inter;
//	private User_Interface server_battleship;
//	private User_Interface client_battleship;
	private int portNumber;
	private Server new_server;
	private Client new_client;
	private Start_Application instance;
	private JFrame game_frame;
	
	
	private Action saveAction;
	private Action saveAsAction;
	private JCheckBoxMenuItem readonlyItem;
	private JPopupMenu popup;
	private JFrame configframe;
	private JFrame tickrangeframe;
	private JFrame aboutframe;
	private JFrame instruframe;
	//private JPanel panel;
	private Color background = Color.white;
	private GameProcess data;
	private int current_tick;
//	private JLabel ttot;
//	private String go_t="";
	private JPanel grid_all;
	private JPanel all_ships;
	private JPanel Battle_panel;
	private JPanel Cruiser_panel;
	private JPanel Destroyer_panel;
	private JPanel Submarine_panel;
	private Battleship battle = new Battleship();
	private Cruiser cruiser = new Cruiser();
	private Destroyer destroyer = new Destroyer();
	private Submarine submarine = new Submarine();
	
	
	/**
	* A sample action that prints the action name to System.out
	*/
	class TestAction extends AbstractAction
	{
		public TestAction(String name)
		{
			super(name);
		}
		
		public void actionPerformed(ActionEvent event)
		{
			System.out.println(getValue(Action.NAME) + " selected.");
		}
	}
	
	public User_Interface get_user_inter() {
		return user_inter;
	}
	
//	public User_Interface get_client_battleship() {
//		return client_battleship;
//	}
	
	public Server getServer() {
		return new_server;
	}
	
	public Client getClient() {
		return new_client;
	}
	
	/**
	 * 
	 * @return the connect_to_frame 
	 */
	public JFrame getConnect_to_fram() {
		return connect_to_frame;
	}
	
	public JFrame getWait_for_connection() {
		return wait_for_connection;
	}
	
	/**
	 * 
	 * @param str input string to see whether it's numeric or not
	 * @return true if the string is numeric, false otherwise
	 */
	public static boolean isNumeric(String str){ 
	  if(str==null||str=="") {return false;}
	  for (int i = str.length();--i>=0;){    
		   if (!Character.isDigit(str.charAt(i))){  
			   return false;  
		   }  
	  }  
	  return true;  
	}
	
	public void connection() {
			user_inter = new User_Interface(this);
			user_inter.getBattleGame().setVisible(true);
			


	}
	
	/** 
	 * 
	 * @param name
	 * @param number
	 * @param size
	 * @param color
	 * @return
	 */
	public JPanel configuration_ship(String name,int number, int size,  Color color) {
		JPanel return_panel = new JPanel();
		return_panel.setLayout(new BoxLayout(return_panel, BoxLayout.X_AXIS));
		JLabel ask_num = new JLabel("Set Number of "+name+" : ");
		JTextField in_num = new JTextField("Default: "+ number);
		return_panel.add(ask_num);
		return_panel.add(in_num);
		return_panel.add(Box.createVerticalStrut(10));
		JLabel ask_size = new JLabel("Set Size for "+name+" : ");
		JTextField in_size = new JTextField("Default: "+size);
		return_panel.add(ask_size);
		return_panel.add(in_size);
		return return_panel;
	}
	
	public int set_num(JPanel panel) {
		String input_num = null;
		Object obj =panel.getComponent(2);
	    if (obj instanceof JTextField) {
	        input_num = ((JTextField) obj).getText();
	        
	    }
	    if(isNumeric(input_num))
	    	return Integer.valueOf(input_num);
	    else
	    	return -1;
	}
	
	public int set_size(JPanel panel) {
	    Object obj =panel.getComponent(4);
	    String input_size = null;
	    if (obj instanceof JTextField) {
	        input_size = ((JTextField) obj).getText();
	    }
	    if(isNumeric(input_size)) {
	    	return Integer.valueOf(input_size);
	    }
	    else 
	    	return -1;
	    
	}
	

	/**
	 * Create the application.
	 */
	public Start_Application() {
		initialize();
	}
	
	private void initialize() {
		// TODO Auto-generated method stub
		instance = this;
		user_inter = new User_Interface(this);
		Startfromhere = new JFrame();
		
		Startfromhere.setTitle("Weclome to BattleShip!");
		Startfromhere.setBounds(100, 100, 800, 500);
		
		
		
		
		
		// set up actions
		
		//Action blueAction = new ColorAction("Blue", Color.BLUE);
		
		//Action yellowAction = new ColorAction("Yellow", Color.YELLOW);
		//Action redAction = new ColorAction("Red", Color.RED);
		
		
		

	//=================================================================
	// menu bar
	JMenuBar menuBar = new JMenuBar();
	Startfromhere.setJMenuBar(menuBar);
	
	
	JMenu fileMenu = new JMenu("File");
	menuBar.add(fileMenu);
	JMenuItem newgrid = fileMenu.add(new TestAction("New Grid"));
	newgrid.setAccelerator(KeyStroke.getKeyStroke("ctrl N"));
	//click to clear the grid
	newgrid.addActionListener(new ActionListener() {
	        
	        @Override
	        public void actionPerformed(ActionEvent e) {
	        	int new_start[][] = data.getGrid().get(current_tick);
	        	int new_survive[][] = data.getSurvive().get(current_tick);
	        	for(int i = 0; i<data.getRowCount(); i++) {
	        		for (int j=0; j<data.getColCount(); j++) {
	        			new_start[i][j] = 0;
	        			new_survive[i][j] = 0;
	        		}
	        	}
				data.getGrid().clear();
				data.getGrid().add(new_start);
				data.getSurvive().clear();
				data.getSurvive().add(new_survive);
				current_tick = 0;
				background = Color.white;
				//create_grid(data);
				Startfromhere.revalidate();
	          
	           
	        }
	                    
	    });
	
	
	//to load file
	JMenuItem openItem = fileMenu.add("Load File");
	openItem.setAccelerator(KeyStroke.getKeyStroke("ctrl O"));
	openItem.addActionListener(new ActionListener() {
	    
	    @Override
	    public void actionPerformed(ActionEvent e) {
	    	 JFileChooser chooser = new JFileChooser();
	         FileNameExtensionFilter filter = new FileNameExtensionFilter("text file", "txt");
	         chooser.setFileFilter(filter);
	         int returnVal = chooser.showOpenDialog(null);
	         if(returnVal == JFileChooser.APPROVE_OPTION) {
	//             System.out.println("You chose to open this file: " +
	//                     chooser.getSelectedFile().getName());
	             try {
					int return_value = data.readfile(chooser.getSelectedFile());
					if(return_value == 1) {
						JOptionPane.showMessageDialog(null, "input file with wrong format for grid!", "alert",JOptionPane.ERROR_MESSAGE);
					}
					else if(return_value == 2) {
						JOptionPane.showMessageDialog(null,  "input file has not match column number!", "alert",JOptionPane.ERROR_MESSAGE);
					}
					else if(return_value == 3) {
						JOptionPane.showMessageDialog(null,  "input file has not match row number!", "alert",JOptionPane.ERROR_MESSAGE);
					}
					else {
						current_tick = 0;
						//create_grid(data);
						Startfromhere.revalidate();
					}
					
					
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
	         }
	    }
	                
	});
	
	
	//to save file 
	JMenuItem saveItem = fileMenu.add("Save File");
	saveItem.setAccelerator(KeyStroke.getKeyStroke("ctrl S"));
	saveItem.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			if(tickrangeframe==null) {
				tickrangeframe = new JFrame();
				tickrangeframe.setTitle("Tick Range Selection: ");
				tickrangeframe.setBounds(100, 100, 477, 426);
				tickrangeframe.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
				tickrangeframe.setVisible(true);
				
				GridBagLayout layout = new GridBagLayout();
				tickrangeframe.setLayout(layout);
				
				JPanel p = new JPanel();
				p.setLayout(new BoxLayout(p, BoxLayout.Y_AXIS));
				tickrangeframe.add(p);
				JLabel start_t = new JLabel("Start Saving at Tick:                       ");
				JTextField textst = new JTextField();
				
				p.add(start_t);
				p.add(textst);
				JLabel end_t = new JLabel("End Saving at Tick:                           ");
				JTextField textet = new JTextField();
				
				p.add(end_t);
				p.add(textet);
				
				JPanel sc = new JPanel();
				sc.setLayout(new BoxLayout(sc, BoxLayout.X_AXIS));
				JButton save = new JButton("Save");
				save.addActionListener(new ActionListener() {
		            
		            @Override
		            public void actionPerformed(ActionEvent e) {
		            	 String input_start = textst.getText();
						 String input_end = textet.getText();
		            	 JFileChooser chooser = new JFileChooser();
		                 FileNameExtensionFilter filter = new FileNameExtensionFilter("text file", ".txt");
		                 chooser.setFileFilter(filter);
		                 int returnVal = chooser.showSaveDialog(null);
		                 if(returnVal == JFileChooser.APPROVE_OPTION) {
	//	                     System.out.println("You chose to open this file: " +
	//	                             chooser.getSelectedFile().getName());
		                     	
					
								try {
									
									data.savefile(chooser.getSelectedFile().getParent(), chooser.getSelectedFile().getName(), Integer.parseInt(input_start), Integer.parseInt(input_end));
								} catch (IOException e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								}
								//Startfromhere.revalidate();
								
		                 }
		                 tickrangeframe.setVisible(false);
		            }
		                        
		        });
	
				JButton cancel = new JButton("Cancel");
				cancel.addActionListener(new ActionListener() {
	
					@Override
					public void actionPerformed(ActionEvent e) {
		                tickrangeframe.setVisible(false);
		            }
					
				});
				
				sc.add(save);
				sc.add(cancel);
				
				
				p.add(sc);
				
			}
			
			else {
				tickrangeframe.setVisible(true);
			}
		}	
	});
	
	//to save all file 
	JMenuItem saveAll = fileMenu.add("Save All File");
	saveAll.setAccelerator(KeyStroke.getKeyStroke("ctrl A"));
	saveAll.addActionListener(new ActionListener() {
	    
	    @Override
	    public void actionPerformed(ActionEvent e) {
	    	 JFileChooser chooser = new JFileChooser();
	         FileNameExtensionFilter filter = new FileNameExtensionFilter("text file", ".txt");
	         chooser.setFileFilter(filter);
	         int returnVal = chooser.showSaveDialog(null);
	         if(returnVal == JFileChooser.APPROVE_OPTION) {
	//             System.out.println("You chose to open this file: " +
	//                     chooser.getSelectedFile().getName());
	             
				
					try {
						data.savefile(chooser.getSelectedFile().getParent(), chooser.getSelectedFile().getName(), 0, data.getscore());
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					//Startfromhere.revalidate();
					
	         }
	    }
	                
	});
	
	fileMenu.addSeparator();
	JMenuItem config_menu = fileMenu.add("Configuration");
	config_menu.setAccelerator(KeyStroke.getKeyStroke("ctrl C"));
	config_menu.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			set_configuration();
			
		}
	});
	
	fileMenu.add(saveAsAction);
	fileMenu.addSeparator();
	
	fileMenu.add(new AbstractAction("Exit")
	{
		public void actionPerformed(ActionEvent event)
		{
			System.exit(0);
		}
	});
	
	// demonstrate checkbox and radio button menus
	
	readonlyItem = new JCheckBoxMenuItem("Read-only");
	readonlyItem.addActionListener(new ActionListener()
	{
		public void actionPerformed(ActionEvent event)
		{
			boolean saveOk = !readonlyItem.isSelected();
			saveAction.setEnabled(saveOk);
			saveAsAction.setEnabled(saveOk);
		}
	});
	
	
	
	// demonstrate mnemonics
	
	JMenu helpMenu = new JMenu("Help");
	helpMenu.setMnemonic('H');
	
	JMenuItem aboutItem = new JMenuItem("About");
	aboutItem.setMnemonic('A');
	aboutItem.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			if(aboutframe==null) {
				aboutframe = new JFrame();
				aboutframe.setTitle("About");
				aboutframe.setBounds(100, 100, 477, 426);
				 JLabel a_label = new JLabel("This work is done by Ruoyan Wu and welcome for any suggestions!");
				 aboutframe.add(a_label,BorderLayout.CENTER);
				aboutframe.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
				aboutframe.setVisible(true);
				
				GridBagLayout layout = new GridBagLayout();
				aboutframe.setLayout(layout);
			}
			else {
				aboutframe.setVisible(true);
			}
		}
	});
	helpMenu.add(aboutItem);
	
	// you can also add the mnemonic key to an action
	JMenuItem instruItem = new JMenuItem("Instruction");
	instruItem.setMnemonic('I');
	instruItem.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			if(instruframe==null) {
				JPanel p = new JPanel();
				p.setLayout(new BoxLayout(p, BoxLayout.Y_AXIS));
				instruframe = new JFrame();
				instruframe.setTitle("Instruction");
				instruframe.setBounds(100, 100, 477, 426);
				 JLabel i_label = new JLabel();
				 i_label.setText("<html>Instruction: <br>1. Click new grid in File to clear the grid and remain the size."
				 		+ "<br>2. Clik load file in File to select input file with format like:"
						+ "	<br>6,6<br>" + 
						"			0,0,0,0,0,0<br>" + 
						"			0,1,1,0,0,0<br>" + 
						"			0,1,0,0,0,0<br>" + 
						"			0,0,0,1,1,0<br>" + 
						"			0,0,0,1,1,0<br>" + 
						"			0,0,0,0,0,0"
				 		+ "<br>3. Clik save file in File to save only for a certain range of ticks. "
				 		+ "<br>4. Clik save all File in File to save a grid for all ticks."
				 		+ "<br>5. Clik Configuration to open configuration panel.</html>");
				 JButton got_it = new JButton("Got it");
				 got_it.addActionListener(new ActionListener() {
					 public void actionPerformed(ActionEvent e) {
						 instruframe.setVisible(false);
					 }
				 });
				 p.add(i_label);
				 p.add(got_it);
				 instruframe.add(p);
				 instruframe.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
				 instruframe.setVisible(true);
				
				GridBagLayout layout = new GridBagLayout();
				instruframe.setLayout(layout);
			}
			else {
				instruframe.setVisible(true);
			}
		}
	});
	helpMenu.add(instruItem);
	
	menuBar.add(fileMenu);
	menuBar.add(helpMenu);
	
	// demonstrate pop-ups
	
	
	JPanel menupanel = new JPanel();
	menupanel.setComponentPopupMenu(popup);
	//end of menu bar
	//==========================================================
	
	//Lives live;
	
	//add tool bar
	// populate toolbar
	
	JToolBar bar = new JToolBar();
	Startfromhere.getContentPane().add(bar, BorderLayout.NORTH);
	//JButton button = bar.add(blueAction);
	JButton button = new JButton();
	bar.add(button);
	button.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			set_configuration();
			
		}
	});
	button.setText("Configuration");
	//bar.add(yellowAction);
	
	//bar.add(redAction);
	bar.addSeparator();
	//JButton button_e = bar.add(exitAction);
	//button_e.setText("Exit");
	
		
		
		

		Startfromhere.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Startfromhere.getContentPane().setLayout(new BoxLayout(Startfromhere.getContentPane(), BoxLayout.Y_AXIS));
		//panel that allow user to choose to connect to someone
		JPanel connect_to_other = new JPanel();
		connect_to_other.setLayout(new BoxLayout(connect_to_other, BoxLayout.Y_AXIS));
		JLabel title = new JLabel("Please Select: ", JLabel.CENTER);
		title.setAlignmentX(Component.CENTER_ALIGNMENT);
		title.setFont(new Font("Serif", Font.BOLD, 48));
		title.setForeground(Color.PINK);
		JButton connect_to = new JButton("Connect to another player");
		connect_to.setAlignmentX(Component.CENTER_ALIGNMENT);
		//open the frame for user to choose a player
		connect_to.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				connect_to_frame = new JFrame();
				
				connect_to_frame.setTitle("Connect to another player");
				connect_to_frame.setBounds(100, 100, 300,300);
				connect_to_frame.getContentPane().setLayout(new BorderLayout()); 
				JPanel connect_to_panel = new JPanel();
				connect_to_panel.setLayout(new BoxLayout(connect_to_panel, BoxLayout.Y_AXIS));
				connect_to_frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);

				connect_to_frame.setVisible(true);
				//wfc_panel.setLayout(new BoxLayout(wfc_panel, BoxLayout.Y_AXIS));
				JLabel sc_label = new JLabel("Start connecting...");
				sc_label.setFont(new Font("Serif", Font.BOLD, 25));
				sc_label.setForeground(Color.ORANGE);
				sc_label.setAlignmentX(Component.CENTER_ALIGNMENT);
				
				
				
				JPanel IPA_panel = new JPanel();
				IPA_panel.setLayout(new BoxLayout(IPA_panel, BoxLayout.X_AXIS));
				JLabel IPAddress_label = new JLabel("Please enter an IP Address: ");
				JTextField IPA_text = new JTextField();
				IPA_text.setMaximumSize(new Dimension(80,20));
				IPA_panel.add(IPAddress_label);
				IPA_panel.add(IPA_text);
				IPA_panel.setAlignmentX(Component.CENTER_ALIGNMENT);
				
				JPanel portNum_panel = new JPanel();
				portNum_panel.setLayout(new BoxLayout(portNum_panel, BoxLayout.X_AXIS));
				JLabel portNum_label = new JLabel("Please enter the port number: ");
				JTextField portNum_text = new JTextField();
				portNum_text.setMaximumSize(new Dimension(80,20));
				portNum_panel.add(portNum_label);
				portNum_panel.add(portNum_text);
				portNum_panel.setAlignmentX(Component.CENTER_ALIGNMENT);
				
				JPanel button_panel = new JPanel();
				button_panel.setLayout(new BoxLayout(button_panel, BoxLayout.X_AXIS));
				JButton sc_cancel_button = new JButton("Cancel");
				
				JButton sc_save_button = new JButton("Save");
				button_panel.add(sc_save_button);
				button_panel.add(sc_cancel_button);
				button_panel.setAlignmentX(Component.CENTER_ALIGNMENT);
			
				connect_to_panel.add(sc_label);
				connect_to_panel.add(Box.createVerticalStrut(20));
				
				connect_to_panel.add(IPA_panel);
				connect_to_panel.add(Box.createVerticalStrut(20));
			    
				connect_to_panel.add(portNum_panel);
				connect_to_panel.add(Box.createVerticalStrut(20));
			  
				connect_to_panel.add(button_panel);
				connect_to_panel.add(Box.createVerticalStrut(20));
			    
				connect_to_frame.getContentPane().add(connect_to_panel);
			
				
				sc_cancel_button.addActionListener(new ActionListener() {
					
					public void actionPerformed(ActionEvent e) {
						connect_to_frame.setVisible(false);
					}
				});
				
				sc_save_button.addActionListener(new ActionListener() {
									
					public void actionPerformed(ActionEvent e) {
						String IPA_string = IPA_text.getText();
						String PortNum_string = portNum_text.getText();
						try {
							new_client = new Client(IPA_string,Integer.valueOf(PortNum_string), instance);
						} catch (NumberFormatException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						//connect_to_frame.setVisible(false);
					}
				});
            }
			
		});
		
		
		
		//****panel.add(Box.createHorizontalStrut(gap), BorderLayout.EAST); 
		
		//button to wait for connection
		JButton wait_connect = new JButton("Wait for connection");
		wait_connect.setAlignmentX(Component.CENTER_ALIGNMENT);
		wait_connect.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				//create a server object
				try {
					Server new_server = new Server(instance);
					new_server.start();
					wait_for_connection = new JFrame();
					//Thread t = new Server(port);
//			         t.start();
					
					wait_for_connection.setTitle("Waiting...");
					wait_for_connection.setBounds(100, 100, 300,300);
					wait_for_connection.getContentPane().setLayout(new BorderLayout()); 
					JPanel wait_for_connection_panel = new JPanel();
					wait_for_connection_panel.setLayout(new BoxLayout(wait_for_connection_panel, BoxLayout.Y_AXIS));
					wait_for_connection.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);

					wait_for_connection.setVisible(true);
					//wfc_panel.setLayout(new BoxLayout(wfc_panel, BoxLayout.Y_AXIS));
					JLabel wfc_label = new JLabel("Waiting for Connection...");
					wfc_label.setFont(new Font("Serif", Font.BOLD, 25));
					wfc_label.setForeground(Color.ORANGE);
					wfc_label.setAlignmentX(Component.CENTER_ALIGNMENT);
					
					JLabel IPAddress_label = new JLabel("Your IP Address is: "+new_server.getHostAddress());
					IPAddress_label.setAlignmentX(Component.CENTER_ALIGNMENT);
					
					JLabel portnumber_label = new JLabel("Your Port Number is: "+new_server.getPortName());
					portnumber_label.setAlignmentX(Component.CENTER_ALIGNMENT);
					
					JButton wfc_button = new JButton("Cancel");
					wfc_button.setAlignmentX(Component.CENTER_ALIGNMENT);
				
					wait_for_connection_panel.add(wfc_label);
					wait_for_connection_panel.add(Box.createVerticalStrut(20));
					
					wait_for_connection_panel.add(IPAddress_label);
					wait_for_connection_panel.add(Box.createVerticalStrut(20));
				    
					wait_for_connection_panel.add(portnumber_label);
					wait_for_connection_panel.add(Box.createVerticalStrut(20));
				  
					wait_for_connection_panel.add(wfc_button);
					wait_for_connection_panel.add(Box.createVerticalStrut(20));
				    
					wait_for_connection.getContentPane().add(wait_for_connection_panel);
				
					
					wfc_button.addActionListener(new ActionListener() {
						
						public void actionPerformed(ActionEvent e) {
							try {
								new_server.close_server();
								System.out.println("Cancel connection!\n");
							} catch (IOException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
							wait_for_connection.setVisible(false);
						}
					});
					
					
					
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
            }
			
		});
		
		//button to exit
		JButton exit_button = new JButton("Exit");
		exit_button.setAlignmentX(Component.CENTER_ALIGNMENT);
		exit_button.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Startfromhere.setVisible(false);
			
			}
		});
		
		connect_to_other.add(title);
		connect_to_other.add(Box.createVerticalStrut(20));
		connect_to_other.add(connect_to);
		connect_to_other.add(Box.createVerticalStrut(20));
		connect_to_other.add(wait_connect);
		connect_to_other.add(Box.createVerticalStrut(20));
		connect_to_other.add(exit_button);
		connect_to_other.add(Box.createVerticalStrut(20));
		
		//panel for user to adjust networking settings
		JPanel networking_setting = new JPanel();
		connect_to_other.setLayout(new BoxLayout(connect_to_other, BoxLayout.Y_AXIS));
		JLabel nws = new JLabel("Adjust your networking setting: ", JLabel.LEFT);
		nws.setFont(new Font("Serif", Font.BOLD, 30));
		nws.setForeground(Color.orange);
		networking_setting.add(nws);
		
		
		JPanel port_input = new JPanel();
		port_input.setLayout(new BoxLayout(port_input, BoxLayout.X_AXIS));
		JLabel pi = new JLabel("Please set your port number: ", JLabel.LEFT);
		JTextField pi_text = new JTextField("Default: 6606                            ");
		portNumber = 0;
		pi_text.setMaximumSize(new Dimension(500,400));
		JButton pi_button = new JButton("Save");
		pi_button.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				String pi_input = pi_text.getText();
					if(!isNumeric(pi_input)) {
						 JOptionPane.showMessageDialog(null, "Invalid Port Number!", "ERROR",

					              JOptionPane.ERROR_MESSAGE);
					}
					else {
						portNumber = Integer.valueOf(pi_input);
					}
			}
		});
		
		port_input.add(pi);
		port_input.add(pi_text);
		port_input.add(pi_button);
		networking_setting.add(port_input);	
		
		//==============================
		//ask user to input player's name
		JPanel user_name = new JPanel();
		
		//ask user to input name
		JLabel un_set = new JLabel("Set Player's Name: ", JLabel.LEFT);
		un_set.setAlignmentX(Component.CENTER_ALIGNMENT);
		un_set.setFont(new Font("Serif", Font.BOLD, 30));
		un_set.setForeground(Color.GRAY);
		connect_to_other.add(un_set);
		
		
		user_name.setLayout(new BoxLayout(user_name, BoxLayout.X_AXIS));
		user_name.setAlignmentX(Component.CENTER_ALIGNMENT);
		JLabel un_label = new JLabel("Please enter player's name: ", JLabel.LEFT);
		JTextField un_text = new JTextField("Default: Player ");
		un_text.setMaximumSize(new Dimension(200,50));
		JButton un_button = new JButton("Save");
		un_button.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				String pi_input = pi_text.getText();
					
			}
		});
		
		user_name.add(un_label);
		user_name.add(un_text);
		user_name.add(un_button);
		connect_to_other.add(user_name);	
		
		
		
		Startfromhere.add(networking_setting);
		Startfromhere.add(connect_to_other);
		Startfromhere.setVisible(true);
	}
	
	
	/**
	 * (different UI colors, network settings, size of the grid, number of ships and their sizes, etc.)
	 */
	public void set_configuration() {
		if(configframe==null) {
			configframe = new JFrame();
			configframe.setTitle("configuration panel");
			configframe.setBounds(100, 100, 477, 426);
			configframe.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
			configframe.setVisible(true);
			
			GridBagLayout layout = new GridBagLayout();
			configframe.setLayout(layout);
			//set UI color
	
			

			//Create and populate the panel.
			JPanel p = new JPanel();
			p.setLayout(new BoxLayout(p, BoxLayout.Y_AXIS));
			configframe.add(p);
			//add small panels with different setting 

			JPanel small = new JPanel();
			small.setLayout(new BoxLayout(small, BoxLayout.Y_AXIS));

			JLabel colors = new JLabel("UI Color", JLabel.TRAILING);
		    small.add(colors);
			JComboBox face = new JComboBox<>(new String[] { "Default", "red", "blue", "yellow",
					"pink", "green", "gray"});
			small.add(face);
			p.add(Box.createVerticalStrut(10));
		

			 JLabel adjust_label = new JLabel("Adjust your networking setting: ", JLabel.TRAILING);
		    small.add(adjust_label);
		    JTextField input_networking= new JTextField("Default");
		    small.add(input_networking);
		
			p.add(small);
			p.add(Box.createVerticalStrut(10));   
			
			
			JPanel gridsizepanel = new JPanel();
			gridsizepanel.setLayout(new BoxLayout(gridsizepanel, BoxLayout.X_AXIS));
			JLabel defaultG = new JLabel("Default grid size:   ");
			JTextField gr = new JTextField("Default: 10");
			JLabel xxx = new JLabel("   X   ");
			JTextField gc = new JTextField("Default: 10");
			gridsizepanel.add(defaultG);
			gridsizepanel.add(gr);
			gridsizepanel.add(xxx);
			gridsizepanel.add(gc);
			
			p.add(gridsizepanel);
			p.add(Box.createVerticalStrut(10));
			
			//set size and number for battleship
			Battle_panel = new JPanel();
			Battle_panel.setLayout(new BoxLayout(Battle_panel, BoxLayout.X_AXIS));
			Battle_panel = configuration_ship(battle.getName(),battle.getNum(), battle.getSize(), battle.getColor());
			p.add(Battle_panel);
			p.add(Box.createVerticalStrut(5));
			
			//set size and number for Cruiser
			Cruiser_panel = new JPanel();
			Cruiser_panel.setLayout(new BoxLayout(Cruiser_panel, BoxLayout.X_AXIS));
			Cruiser_panel = configuration_ship(cruiser.getName(),cruiser.getNum(), cruiser.getSize(), cruiser.getColor());
			p.add(Cruiser_panel);
			p.add(Box.createVerticalStrut(5));
			
			//set size and number for Destroyer
			Destroyer_panel = new JPanel();
			Destroyer_panel.setLayout(new BoxLayout(Destroyer_panel, BoxLayout.X_AXIS));
			Destroyer_panel = configuration_ship(destroyer.getName(),destroyer.getNum(), destroyer.getSize(), destroyer.getColor());
			p.add(Destroyer_panel);
			p.add(Box.createVerticalStrut(5));
			
			//set size and number for Submarine
			Submarine_panel = new JPanel();
			Submarine_panel.setLayout(new BoxLayout(Submarine_panel, BoxLayout.X_AXIS));
			Submarine_panel = configuration_ship(submarine.getName(),submarine.getNum(), submarine.getSize(), submarine.getColor());
			p.add(Submarine_panel);
		
		
			
			
		JPanel sc = new JPanel();
		sc.setLayout(new BoxLayout(sc, BoxLayout.X_AXIS));
		JButton save = new JButton("Save");
		save.addActionListener(new ActionListener() {
			
			
			@Override
			public void actionPerformed(ActionEvent e) {
				//set ui color
				if(!((String)face.getSelectedItem()).equals("Default")) {
					
					//"red", "blue", "yellow",
					//"white", "black", "green", "gray"
					String pick_color = (String) face.getSelectedItem();
					System.out.println(pick_color);
					if(pick_color=="red") {
						user_inter.enemy_panel.setBackground(Color.RED);
						background = Color.red;
					}
						
					else if(pick_color=="blue"){
						user_inter.enemy_panel.setBackground(Color.blue);
						background = Color.BLUE;
					}
					else if(pick_color=="yellow")
					{
						user_inter.enemy_panel.setBackground(Color.yellow);
						background = Color.YELLOW;
					}
						
					else if(pick_color=="green"){
						user_inter.enemy_panel.setBackground(Color.green);
						background = Color.GREEN;
					}
					else if(pick_color=="gray"){
						user_inter.enemy_panel.setBackground(Color.gray);
						background = Color.GRAY;
					}
					else if(pick_color=="pink"){
						user_inter.enemy_panel.setBackground(Color.pink);
						background = Color.PINK;
					}
					
					Startfromhere.revalidate();
				}
				
				//set network
			
                String input_n = input_networking.getText();
                
                
                //===============================
                //set default grid size
                String new_rc = gr.getText();
                String new_cc = gc.getText();
                if(!new_rc.contentEquals("Default")||!new_cc.contentEquals("Default")) {
                	int cont = 1;
                	for(int n=0; n<new_rc.length(); n++) {
                		if(!Character.isDigit(new_rc.charAt(n))) {
                			cont=0;
                			break;
                		}
                	}
                	
                	for(int n=0; n<new_cc.length(); n++) {
                		if(!Character.isDigit(new_cc.charAt(n))) {
                			cont=0;
                			break;
                		}
                	}
                	
                	if(cont==0) {
                		JOptionPane.showMessageDialog(null, "Input row or column number is not a valid number!", "ERROR",JOptionPane.WARNING_MESSAGE); 
                	}
                	else {
                		int n_rc = 0;
                        int n_cc = 0;
                        if(new_rc.contentEquals("Default")) {
                        	n_rc = data.getRowCount();
        				}
                        else {
                        	n_rc = Integer.parseInt(new_rc);
                        }
                        
                        if(new_cc.contentEquals("Default")) {
                        	n_cc = data.getColCount();
        				}
                        else {
                        	n_cc = Integer.parseInt(new_cc);
                        }
                        int old_rc = data.getRowCount();
                        int old_cc = data.getColCount();
                        data.SetRowCount(n_rc);
                        data.SetColCount(n_cc);
                        int new_gg[][] = new int[data.getRowCount()][data.getColCount()];
                        for(int i=0; i<Math.min(data.getRowCount(),old_rc); i++) {
                        	for(int j=0; j<Math.min(data.getColCount(),old_cc); j++) {
                        		new_gg[i][j] = data.getGrid().get(current_tick)[i][j];
                        	}
                        }
                        int new_sg[][] = new int[data.getRowCount()][data.getColCount()];
                        for(int i=0; i<Math.min(data.getRowCount(),old_rc); i++) {
                        	for(int j=0; j<Math.min(data.getColCount(),old_cc); j++) {
                        		new_sg[i][j] = data.getSurvive().get(current_tick)[i][j];
                        	}
                        }
                        data.getGrid().clear();
                        data.getGrid().add(new_gg);
                        data.getSurvive().clear();
                        data.getSurvive().add(new_sg);
                        current_tick = 0;
                        user_inter.create_grid(data);
                        //client_battleship.create_grid(data);
                        Startfromhere.revalidate();
                        
                	}
                	
                    
                    
                }
                
              //set all ships
        		int num = set_num(Battle_panel);	
        		int size = set_size(Battle_panel);
        		if(num!=-1)
        			battle.setNum(num);
        		if(size!=-1)
        			battle.setSize(size);
        		
        		num = set_num(Cruiser_panel);	
        		size = set_size(Cruiser_panel);
        		if(num!=-1)
        			cruiser.setNum(num);
        		if(size!=-1)
        			cruiser.setSize(size);
        		
        		num = set_num(Destroyer_panel);
        		size = set_size(Destroyer_panel);
        		if(num!=-1)
        			destroyer.setNum(num);
        		if(size!=-1)
        			destroyer.setSize(size);

        		num = set_num(Submarine_panel);
        		size = set_size(Submarine_panel);
        		if(num!=-1)
        			submarine.setNum(num);
        		if(size!=-1)
        			submarine.setSize(size);
        		
                configframe.setVisible(false);
           }
			
		});
		
		
		
		JButton cancel = new JButton("Cancel");
		cancel.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
                configframe.setVisible(false);
            }
			
		});
		
		sc.add(save);
		sc.add(cancel);
		
		
		p.add(sc);
		
		}
		//if the configuration panel already exist
		else {
			configframe.setVisible(true);
		}
		
	}
	

	/**
	 * 
	 * @param args no input
	 */
	public static void main(String[] args){
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Start_Application window = new Start_Application();
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}

