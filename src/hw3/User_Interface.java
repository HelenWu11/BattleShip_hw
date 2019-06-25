package hw3;


import java.awt.EventQueue;
import java.awt.FlowLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFileChooser;

import java.awt.GridBagLayout;
import java.awt.GridLayout;

import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.KeyStroke;
import javax.swing.filechooser.FileNameExtensionFilter;

import javax.swing.JMenu;
import java.awt.Button;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.io.IOException;
import java.awt.event.ActionEvent;
import javax.swing.JButton;


public class User_Interface {

	private JFrame BattleGame;
	public JPanel panel;
	public JPanel enemy_panel;
	public JPanel my_panel;
	public JPanel e_panel;
	
	private GameProcess data;
	private int current_tick;
	private JLabel ttot;
	private String go_t="";
	private JPanel grid_all;
	private JPanel all_ships;
	private Color background = Color.white;
	private Start_Application app;
	private Action saveAction;
	private Action saveAsAction;
	private JCheckBoxMenuItem readonlyItem;
	private JPopupMenu popup;
	private JFrame configframe;
	private JFrame tickrangeframe;
	private JFrame aboutframe;
	private JFrame instruframe;
	
	
	//all ships 
	private Battleship Battle = new Battleship();
	private Cruiser cruiser = new Cruiser();
	private Destroyer destroyer = new Destroyer();
	private Submarine submarine = new Submarine();
	   
	
	public JPanel create_ships_grid(Battleship Battle, Cruiser cruiser, Destroyer destroyer, Submarine submarine) {
		JPanel return_panel = new JPanel();
		return_panel.setLayout(new BoxLayout(return_panel, BoxLayout.Y_AXIS));
		//Battleship part
		JLabel instructor = new JLabel();
		instructor.setText("<html>*Rule: <br> By clicking buttons of different types"
				+ "of ships to set your ships on your grid."
				+ "<br>You only have limit amouts of each kind of ship and you can change the number of "
				+ "them in the configuration panel.</html>");
		instructor.setAlignmentX(Component.CENTER_ALIGNMENT);
		instructor.setBackground(Color.DARK_GRAY);
		return_panel.add(Box.createVerticalStrut(5));
		return_panel.add(instructor);
		JPanel battle_panel = new JPanel();
		battle_panel.setLayout(new BoxLayout(battle_panel, BoxLayout.X_AXIS));
		JLabel battle_label = new JLabel("Total Battleship: "+Battle.getNum()+"   "); 
		JButton battle_button = new JButton();
		battle_button.setBackground(Battle.getColor());
		JLabel b_label_size = new JLabel("Size: "+Battle.getSize());
		b_label_size.setAlignmentX(Component.CENTER_ALIGNMENT);
		battle_label.setAlignmentX(Component.CENTER_ALIGNMENT);
		battle_button.setAlignmentX(Component.CENTER_ALIGNMENT);
		battle_panel.add(battle_label);
		battle_panel.add(battle_button);
		battle_panel.add(b_label_size);
		return_panel.add(battle_panel);
		return_panel.add(Box.createVerticalStrut(5));
		
		//cruiser part
		JPanel cruiser_panel = new JPanel();
		cruiser_panel.setLayout(new BoxLayout(cruiser_panel, BoxLayout.X_AXIS));
		JLabel cruiser_label = new JLabel("Total Cruiser: "+ cruiser.getNum()+"   "); 
		JButton cruiser_button = new JButton();
		cruiser_button.setBackground(cruiser.getColor());
		JLabel c_label_size = new JLabel("Size: "+cruiser.getSize());
		c_label_size.setAlignmentX(Component.CENTER_ALIGNMENT);
		cruiser_label.setAlignmentX(Component.CENTER_ALIGNMENT);
		cruiser_button.setAlignmentX(Component.CENTER_ALIGNMENT);
		cruiser_panel.add(cruiser_label);
		cruiser_panel.add(cruiser_button);
		cruiser_panel.add(c_label_size);
		return_panel.add(cruiser_panel);
		return_panel.add(Box.createVerticalStrut(5));
		
		//destroyer part
		JPanel destroyer_panel = new JPanel();
		destroyer_panel.setLayout(new BoxLayout(destroyer_panel, BoxLayout.X_AXIS));
		JLabel destroyer_label = new JLabel("Total Destroyer: "+ destroyer.getNum()+"   "); 
		JButton destroyer_button = new JButton();
		destroyer_button.setBackground(destroyer.getColor());
		JLabel d_label_size = new JLabel("Size: "+destroyer.getSize());
		d_label_size.setAlignmentX(Component.CENTER_ALIGNMENT);
		destroyer_label.setAlignmentX(Component.CENTER_ALIGNMENT);
		destroyer_button.setAlignmentX(Component.CENTER_ALIGNMENT);
		destroyer_panel.add(destroyer_label);
		destroyer_panel.add(destroyer_button);
		destroyer_panel.add(d_label_size);
		return_panel.add(destroyer_panel);
		return_panel.add(Box.createVerticalStrut(5));
		
		//destroyer part
		JPanel submarine_panel = new JPanel();
		submarine_panel.setLayout(new BoxLayout(submarine_panel, BoxLayout.X_AXIS));
		JLabel submarine_label = new JLabel("Total Submarine: "+ submarine.getNum()+"   "); 
		JButton submarine_button = new JButton();
		submarine_button.setBackground(submarine.getColor());
		JLabel s_label_size = new JLabel("Size: "+submarine.getSize());
		s_label_size.setAlignmentX(Component.CENTER_ALIGNMENT);
		submarine_label.setAlignmentX(Component.CENTER_ALIGNMENT);
		submarine_button.setAlignmentX(Component.CENTER_ALIGNMENT);
		submarine_panel.add(submarine_label);
		submarine_panel.add(submarine_button);
		submarine_panel.add(s_label_size);
		return_panel.add(submarine_panel);
		return_panel.add(Box.createVerticalStrut(5));
		
		return return_panel;
	}
	
	
	public JFrame getBattleGame() {
		return BattleGame;
	}
	
	
	/**
	* The color action sets the background of the frame to a given color.
	*/
	class ColorAction extends AbstractAction
	{
		public ColorAction(String name,Color c)
		{
			putValue(Action.NAME, name);
//			putValue(Action.SMALL_ICON, icon);
			putValue(Action.SHORT_DESCRIPTION, name + " background");
			putValue("Color", c);
		}
		
		public void actionPerformed(ActionEvent event)
		{
			Color c = (Color) getValue("Color");
			panel.setBackground(c);
			background = c;
		}
	//end of tool bar
	
		
	}
	
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
	
	public Color return_c(int survive_tick) {
		
		return Color.getHSBColor(13.0f/256, 14.0f/256, 10f/(survive_tick+10f));

	}
	

	/**
	 * Create the application.
	 */
	public User_Interface(Start_Application app) {
		this.app = app;
		initialize();
	}
	//302/256 && 95/256  0.25~1
	public void create_grid(GameProcess data) {
		if(panel!=null) {
			BattleGame.getContentPane().remove(panel);
		}
		///////////grid////////////////////
		//create user panel
		my_panel = new JPanel();
		my_panel.setLayout(new BoxLayout(my_panel, BoxLayout.Y_AXIS));
		JLabel my_panel_label = new JLabel("Grid");
		my_panel_label.setAlignmentX(Component.CENTER_ALIGNMENT);
		//present scores and players information
		JLabel my_infor_label = new JLabel("Player1:                         ");
		my_infor_label.setAlignmentX(Component.CENTER_ALIGNMENT);
		JLabel my_infor_score = new JLabel("Player1's score:                 ");
		my_infor_score.setAlignmentX(Component.CENTER_ALIGNMENT);
				
		panel = create_grid_panel(data);
		my_panel.add(my_panel_label);
		my_panel.add(Box.createVerticalStrut(20));
		my_panel.add(my_infor_label);
		my_panel.add(Box.createVerticalStrut(20));
		my_panel.add(my_infor_score);
		my_panel.add(Box.createVerticalStrut(20));
		my_panel.add(panel);
  
	
		
		//create enemy panel
		e_panel = new JPanel();
		e_panel.setLayout(new BoxLayout(e_panel, BoxLayout.Y_AXIS));
		JLabel enemy_panel_label = new JLabel("Enemy's Grid");
		enemy_panel_label.setAlignmentX(Component.CENTER_ALIGNMENT);
		  //player2

  		JLabel enemy_infor_label = new JLabel("Player2:                  ");
  		enemy_infor_label.setAlignmentX(Component.CENTER_ALIGNMENT);
  		JLabel enemy_infor_score = new JLabel("Player2's score:          ");
  		enemy_infor_score.setAlignmentX(Component.CENTER_ALIGNMENT);
		enemy_panel = create_grid_panel(data);
		e_panel.add(enemy_panel_label);
		e_panel.add(Box.createVerticalStrut(20));
		e_panel.add(enemy_infor_label);
		e_panel.add(Box.createVerticalStrut(20));
  	    e_panel.add(enemy_infor_score);
  	    e_panel.add(Box.createVerticalStrut(20));
		e_panel.add(enemy_panel);
		
		this.grid_all = new JPanel();
		grid_all.add(my_panel, BorderLayout.WEST);
		grid_all.add(e_panel, BorderLayout.EAST);
		
		BattleGame.getContentPane().add(grid_all);
	}
	
	public JPanel create_grid_panel(GameProcess data) {
		int[][] survive_tick = data.getSurvive().get(current_tick);
		JPanel panel = new JPanel(new GridLayout(data.getRowCount(),data.getColCount(),1,1));
		panel.setBackground(background);
		for(int i=0; i<data.getRowCount()*data.getColCount(); i++) {
			int ss = data.getSurvive().get(current_tick)[i/data.getColCount()][i%data.getColCount()];
			JButton btn = new JButton(Integer.toString(ss));
			btn.setBackground(Color.white);
			final int ii = i;
			if(data.getGrid().get(current_tick)[ii/data.getColCount()][ii%data.getColCount()]==0) 
				btn.setBackground(Color.white);
			else if(data.getGrid().get(current_tick)[ii/data.getColCount()][ii%data.getColCount()]==1) {
				btn.setBackground(return_c(survive_tick[ii/data.getColCount()][ii%data.getColCount()]));
			    ss = data.getSurvive().get(current_tick)[ii/data.getColCount()][ii%data.getColCount()];
				btn.setText(Integer.toString(ss));
			}
			btn.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					int[][] survive_tick = data.getSurvive().get(current_tick);
					// TODO Auto-generated method stub
					//if (live.data[][] == 0) {
						//btn.setBackground(Color.white);
						//live.data[][] = 1;
					//}
					//else {
		
					if(data.getGrid().get(current_tick)[ii/data.getColCount()][ii%data.getColCount()]==0) {
						
						btn.setBackground(return_c(survive_tick[ii/data.getColCount()][ii%data.getColCount()]));
						data.getGrid().get(current_tick)[ii/data.getColCount()][ii%data.getColCount()]=1;

					}
					else if(data.getGrid().get(current_tick)[ii/data.getColCount()][ii%data.getColCount()]==1) {
						btn.setBackground(Color.white);
						data.getGrid().get(current_tick)[ii/data.getColCount()][ii%data.getColCount()]=0;
			
					}
//					
				}

		public JPanel create_grid_panel(GameProcess data) {
				int[][] survive_tick = data.getSurvive().get(current_tick);
				JPanel panel = new JPanel(new GridLayout(data.getRowCount(),data.getColCount(),1,1));
				panel.setBackground(background);
				for(int i=0; i<data.getRowCount()*data.getColCount(); i++) {
					int ss = data.getSurvive().get(current_tick)[i/data.getColCount()][i%data.getColCount()];
					JButton btn = new JButton(Integer.toString(ss));
					btn.setBackground(Color.white);
					final int ii = i;
					if(data.getGrid().get(current_tick)[ii/data.getColCount()][ii%data.getColCount()]==0) 
						btn.setBackground(Color.white);
					else if(data.getGrid().get(current_tick)[ii/data.getColCount()][ii%data.getColCount()]==1) {
						btn.setBackground(return_c(survive_tick[ii/data.getColCount()][ii%data.getColCount()]));
					    ss = data.getSurvive().get(current_tick)[ii/data.getColCount()][ii%data.getColCount()];
						btn.setText(Integer.toString(ss));
					}
					btn.addActionListener(new ActionListener() {
						
						@Override
						public void actionPerformed(ActionEvent e) {
							int[][] survive_tick = data.getSurvive().get(current_tick);
							// TODO Auto-generated method stub
							//if (live.data[][] == 0) {
								//btn.setBackground(Color.white);
								//live.data[][] = 1;
							//}
							//else {
				
							if(data.getGrid().get(current_tick)[ii/data.getColCount()][ii%data.getColCount()]==0) {
								
								btn.setBackground(return_c(survive_tick[ii/data.getColCount()][ii%data.getColCount()]));
								data.getGrid().get(current_tick)[ii/data.getColCount()][ii%data.getColCount()]=1;
		
							}
							else if(data.getGrid().get(current_tick)[ii/data.getColCount()][ii%data.getColCount()]==1) {
								btn.setBackground(Color.white);
								data.getGrid().get(current_tick)[ii/data.getColCount()][ii%data.getColCount()]=0;
					
							}
		//					
						}
						
					});
					
				    //btn.setPreferredSize(new Dimension(30, 30));
				    panel.add(btn);
				}
				
				panel.setPreferredSize(new Dimension(500, 500));
				return panel;
				
			}
		
	});
		
	    //btn.setPreferredSize(new Dimension(30, 30));
	    panel.add(btn);
	}
	
	panel.setPreferredSize(new Dimension(500, 500));
	return panel;
	
}
	
	
	/**
	 * 
	 * @param data classtype GameLife to store informations about cells
	 * @param current_tick number of the tick that want to present on the grid
	 */
	public void update_grid(GameProcess data, int current_tick) {
		int[][] survive_tick = data.getSurvive().get(current_tick);
		if(panel!=null) {
			BattleGame.getContentPane().remove(panel);
		}
		
		panel = new JPanel(new GridLayout(data.getRowCount(),data.getColCount(),2,2));
		panel.setBackground(background);
		for(int i=0; i<data.getRowCount()*data.getColCount(); i++) {
			int ss = data.getSurvive().get(current_tick)[i/data.getColCount()][i%data.getColCount()];
			JButton btn = new JButton(Integer.toString(ss));
			btn.setBackground(Color.white);
			final int ii = i;
			if(data.getGrid().get(current_tick)[ii/data.getColCount()][ii%data.getColCount()]==0) 
				btn.setBackground(Color.white);
			else if(data.getGrid().get(current_tick)[ii/data.getColCount()][ii%data.getColCount()]==1) {
				btn.setBackground(return_c(survive_tick[ii/data.getColCount()][ii%data.getColCount()]));	
				ss = data.getSurvive().get(current_tick)[ii/data.getColCount()][ii%data.getColCount()];
				btn.setText(Integer.toString(ss));
			}
			btn.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					int[][] survive_tick = data.getSurvive().get(current_tick);
				
					if(data.getGrid().get(current_tick)[ii/data.getColCount()][ii%data.getColCount()]==0) {
						btn.setBackground(return_c(survive_tick[ii/data.getColCount()][ii%data.getColCount()]));
						data.getGrid().get(current_tick)[ii/data.getColCount()][ii%data.getColCount()]=1;

					}
					else if(data.getGrid().get(current_tick)[ii/data.getColCount()][ii%data.getColCount()]==1) {
						btn.setBackground(Color.white);
						data.getGrid().get(current_tick)[ii/data.getColCount()][ii%data.getColCount()]=0;
			
					}
				}
				
			});
			
		    panel.add(btn);
		}
		
		panel.setPreferredSize(new Dimension(500, 500));
		
		BattleGame.getContentPane().add(panel, BorderLayout.WEST);
	}
	
	


	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		current_tick = 0;
		data = new GameProcess();
		BattleGame = new JFrame();
		BattleGame.setTitle("BattleShip Game");
		BattleGame.setExtendedState(JFrame.MAXIMIZED_BOTH);

		BattleGame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		BattleGame.getContentPane().setLayout(new BoxLayout(BattleGame.getContentPane(), BoxLayout.Y_AXIS));
		//BattleGame.setVisible(true);
		

		//add menu bar
		JMenuBar menuBar = new JMenuBar();
		BattleGame.setJMenuBar(menuBar);
		JButton button_e = new JButton("Exit");
		button_e.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				try {
					if(app.getServer()!=null)
						app.getServer().close_server();
				} catch (IOException i) {
					// TODO Auto-generated catch block
					i.printStackTrace();
				}
				try {
					if(app.getClient()!=null)
						app.getClient().close_client();
				} catch (IOException i) {
					// TODO Auto-generated catch block
					i.printStackTrace();
				}
				BattleGame.setVisible(false);
			}
		});
		
		menuBar.add(button_e);
		
		JButton start = new JButton("Start");
		start.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					int new_start[][] = data.getGrid().get(current_tick);
					data.getGrid().clear();
					data.getGrid().add(new_start);
					int new_survive[][] = data.getSurvive().get(current_tick);
					data.getSurvive().clear();
					data.getSurvive().add(new_survive);
					data.transform(data.getscore(),data.getGrid().get(0), data.getRowCount(), data.getColCount(), data.getOP());
					current_tick = data.getscore();
					create_grid(data);
					BattleGame.revalidate();
					go_t = "";
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
            }
			
		});
		
		//end of toolbar
		
		//====================
		//display informations for all ships
		all_ships = new JPanel();
		all_ships = create_ships_grid(Battle, cruiser, destroyer, submarine);
		BattleGame.add(all_ships,BorderLayout.NORTH);
		
//	    goto_tick.add(choose_t,BorderLayout.CENTER);
//	 // up down button
//	    JPanel updown = new JPanel();
//		updown.setLayout(new BoxLayout(updown, BoxLayout.Y_AXIS));
//	    JButton plus = new JButton(" + ");
//	    updown.add(plus);
//		plus.addActionListener(new ActionListener() {
//
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				int go_to_tick =0;
//				if(go_t.contentEquals("")) {
//					go_t = choose_t.getText();
//					if(go_t.contentEquals("")) {
//						JOptionPane.showMessageDialog(null, "Please pick a tick number!", "ERROR",JOptionPane.WARNING_MESSAGE);  
//					}
//					else {
//						go_to_tick = Integer.parseInt(go_t)+1;
//						
//						if(go_to_tick>current_tick) {	
//							JOptionPane.showMessageDialog(null, "Tick out of range!", "ERROR",JOptionPane.WARNING_MESSAGE);  	
//						}
//						else {
//							go_t = Integer.toString(go_to_tick);
//							choose_t.setText(go_t);
//							update_grid(data,go_to_tick);
//							BattleGame.revalidate();
//						}
//						
//					}
//				}
//				else {
//					go_to_tick = Integer.parseInt(go_t)+1;
//					if(go_to_tick>current_tick) {	
//						JOptionPane.showMessageDialog(null, "Tick out of range!", "ERROR",JOptionPane.WARNING_MESSAGE);  	
//					}
//					else {
//						go_t = Integer.toString(go_to_tick);
//						choose_t.setText(go_t);
//						update_grid(data,go_to_tick);
//						BattleGame.revalidate();
//					}
//				}
//				
//				
//				
//            }
//			
//		});
//
//
//	    JButton minus = new JButton(" -- ");
//	    updown.add(minus);
//	    minus.addActionListener(new ActionListener() {
//
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				int go_to_tick =0;
//				if(go_t.contentEquals("")) {
//					go_t = choose_t.getText();
//					if(go_t.contentEquals("")) {
//						JOptionPane.showMessageDialog(null, "Please pick a tick number!", "ERROR",JOptionPane.WARNING_MESSAGE);  
//					}
//					else {
//						go_to_tick = Integer.parseInt(go_t)-1;
//						if(go_to_tick<0||go_to_tick>current_tick) {	
//							JOptionPane.showMessageDialog(null, "Tick out of range!", "ERROR",JOptionPane.WARNING_MESSAGE);  	
//						}
//						else {
//							go_t = Integer.toString(go_to_tick);
//							choose_t.setText(go_t);
//							update_grid(data,go_to_tick);
//							BattleGame.revalidate();
//						}
//					}
//				}
//				else {
//					go_to_tick = Integer.parseInt(go_t)-1;
//					if(go_to_tick<0||go_to_tick>current_tick) {	
//						JOptionPane.showMessageDialog(null, "Tick out of range!", "ERROR",JOptionPane.WARNING_MESSAGE);  	
//					}
//					else {
//						go_t = Integer.toString(go_to_tick);
//						choose_t.setText(go_t);
//						update_grid(data,go_to_tick);
//						BattleGame.revalidate();
//					}
//				}
//			
//            }
//			
//		});
//	    
//	    JButton select_t = new JButton("<html>Go!<br>Alt+Enter</html>");
//	   select_t.setMnemonic(KeyEvent.VK_ENTER); 
//	  
//	    select_t.addActionListener(new ActionListener() {
//
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				int go_to_tick =0;
//				String go_t = choose_t.getText();
//				
//				
//				if(go_t.contentEquals("")) {
//					JOptionPane.showMessageDialog(null, "Please pick a tick number!", "ERROR",JOptionPane.WARNING_MESSAGE);  
//				}
//				else {
//					go_to_tick = Integer.parseInt(go_t);
//					if(current_tick!=data.getTick()) {
//						JOptionPane.showMessageDialog(null, "Need press Start to begin ticks first!", "ERROR",JOptionPane.WARNING_MESSAGE);  
//					}
//					else {
//						if(go_to_tick>data.getTick()) {
//							JOptionPane.showMessageDialog(null, "Tick number should smaller than total tick number!", "ERROR",JOptionPane.WARNING_MESSAGE);  
//						}
//						else {
//							update_grid(data,go_to_tick);
//							BattleGame.revalidate();
//						}
//						
//					}
//				}
//            }
//			
//		});
	    
	    

	
		//create button grid
		//===========================
		create_grid(data);
		//=========
		
		
	}
	

	
	
}
	
