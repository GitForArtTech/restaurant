package �F�z�������n;

import javax.swing.ButtonGroup;
import java.awt.Graphics;
import javax.swing.ImageIcon;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextPane;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

import java.*;
import java.awt.BorderLayout;
import java.awt.Checkbox;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.net.PortUnreachableException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class DeliciousFrame extends JFrame {
	private final int FRAME_WIDTH = 2000;
	private final int FRAME_HEIGHT = 1000;
	private final int PANEL1_WIDTH = 40;
	private final int PANEL1_HEIGHT = 20;
	private final int AREA_WIDTH = 40;
	private final int AREA_HEIGHT = 20;
	private final int PANEL_WIDTH = 20;
	private final int PANEL_HEIGHT = 10;

	private JButton submitBtn;
	private JButton cleanBtn;
	private JButton SeeMenuBtn;
	private JComboBox<String> requireBox;
	private JComboBox<String> priceBox;

	private JRadioButton outsideTheDormBtn;
	private JRadioButton mcdBtn;
	private JRadioButton stoneBtn;
	private JRadioButton BuildingBtn;
	private JRadioButton taiwanFoodBtn;
	private JRadioButton JapanKoreanFoodBtn;
	private JRadioButton EastenFoodBtn;
	private JRadioButton drinksBtn;
	private JRadioButton buffetBtn;

	private JLabel restaurantNameLabel;
	private JLabel distanceLabel;
	private JLabel chooseCheckBoxLabel;
	private JLabel evaluationLabel;

	private ButtonGroup group;
	private ButtonGroup group1;
	private ButtonGroup group2;
	private Restaurant restaurant;
	private SelectRestaurant select;

	private ArrayList<String> forms;
	private ArrayList<String> Places;
	private ArrayList<String> Money;
	private ArrayList<String> Style;
	private ArrayList<String> RestaurantChoose;
	private ArrayList<String> ResChoosing;
	private JPanel panel;
	private JPanel jPanel;
	private JPanel InfoPanel= new JPanel();
	private ImageIcon imageIcon;
	private File file;
	private JTextPane textPane = new JTextPane();

	// private JTextPane text = null;
	// private JFileChooser fileChooser;

	public DeliciousFrame() throws SQLException {
		setTitle("�F�z�������n");
		setSize(FRAME_WIDTH, FRAME_HEIGHT);
		group1 = new ButtonGroup();

		JMenuBar menuBar = new JMenuBar(); // 2
		setJMenuBar(menuBar);
		menuBar.add(createActionMenu());
		RestaurantChoose = new ArrayList<String>();
		createHomePage(); // 3

	}

	public JPanel createHeadPanel() throws SQLException {
		JPanel panel1 = new JPanel();
		JPanel panel2 = new JPanel();
		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout());
		panel1.setLayout(new GridLayout(1, 5));

		createsubmitBtn();
		createcleanBtn();

		panel1.add(createRequirement());
		panel1.add(createNowPosition());
		panel1.add(createPrice());
		panel1.add(createFoodType());
		panel2.add(submitBtn);
		panel2.add(cleanBtn);

		if (outsideTheDormBtn.isSelected()) {
			mcdBtn.setEnabled(false);
			stoneBtn.setEnabled(false);
		} else if (mcdBtn.isSelected()) {
			outsideTheDormBtn.setEnabled(false);
			stoneBtn.setEnabled(false);
		} else if (stoneBtn.isSelected()) {
			outsideTheDormBtn.setEnabled(false);
			mcdBtn.setEnabled(false);
		}

		panel.add(panel1, BorderLayout.NORTH);
		panel.add(panel2, BorderLayout.CENTER);
		return panel;

	}

	public JPanel createMainPanel() {
		panel = new JPanel();
		// panel.setLayout(new BorderLayout());
		panel.setBorder(new TitledBorder(new EtchedBorder(), "�п�ܱz�ݭn���\�U"));

		panel.add(createInfo());
		// panel.add(createDetailed(),BorderLayout.CENTER);

		return panel;

	}

	public JPanel createResaultPanel() throws SQLException {
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(new EtchedBorder(), "�z��ܪ��\�U��T"));
		// resultTextArea = new JTextArea(AREA_HEIGHT, AREA_WIDTH);
		// resultTextArea.setEditable(false);
		// JScrollPane scrollPane = new JScrollPane(resultTextArea);
		JScrollPane scrollPane = new JScrollPane(textPane);

		/*
		 * Connection connection = SimpleDataSourse.getConnection(); Statement statement
		 * = connection.createStatement(); ResultSet resultSet =
		 * statement.executeQuery("SELECT imgsrc FROM image_src " +
		 * "WHERE Name = '�p�߫}'"); resultTextArea.append(resultSet.getString("imgsrc"));
		 */
		scrollPane.setSize(AREA_WIDTH, AREA_HEIGHT);

		panel.add(scrollPane);

		// panel.add(scrollPane);
		return panel;

	}

	public void createHomePage() throws SQLException {
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(1, 1));
		panel.add(createMainPanel());
		panel.add(createResaultPanel());
		add(createHeadPanel(), BorderLayout.NORTH);
		add(panel, BorderLayout.CENTER);

	}

	public JPanel createRequirement() throws SQLException {
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(new EtchedBorder(), "���\����"));
		panel.setSize(PANEL_HEIGHT, PANEL_WIDTH);
		requireBox = new JComboBox<>();

		requireBox.addItem("�~�a");
		requireBox.addItem("�[��");
		requireBox.addItem("���\");

		panel.add(requireBox);
		return panel;
	}

	public JPanel createNowPosition() throws SQLException {
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(new EtchedBorder(), "�{�b��m"));
		panel.setSize(PANEL_HEIGHT, PANEL_WIDTH);
		panel.setLayout(new GridLayout(2, 2));

		outsideTheDormBtn = new JRadioButton("���~");
		mcdBtn = new JRadioButton("����");
		stoneBtn = new JRadioButton("�ոO");
		BuildingBtn = new JRadioButton("�ͽ��");

		group = new ButtonGroup();
		group.add(outsideTheDormBtn);
		group.add(mcdBtn);
		group.add(stoneBtn);
		group.add(BuildingBtn);

		panel.add(outsideTheDormBtn);
		panel.add(mcdBtn);
		panel.add(stoneBtn);
		panel.add(BuildingBtn);
		return panel;
	}

	public JPanel createPrice() {
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(new EtchedBorder(), "����"));
		panel.setSize(PANEL_HEIGHT, PANEL_WIDTH);
		priceBox = new JComboBox<>();
		priceBox.addItem("50�H�U");
		priceBox.addItem("50~100");
		priceBox.addItem("100~150");
		priceBox.addItem("150~200");
		priceBox.addItem("200~250");
		priceBox.addItem("250~300");
		priceBox.addItem("300~350");
		priceBox.addItem("350~400");
		priceBox.addItem("400�H�W");

		panel.add(priceBox);
		return panel;
	}

	public JPanel createFoodType() {
		JPanel panel = new JPanel();
		group2 = new ButtonGroup();
		panel.setBorder(new TitledBorder(new EtchedBorder(), "����"));
		panel.setSize(PANEL_HEIGHT, PANEL_WIDTH);
		panel.setLayout(new GridLayout(3, 2));
		taiwanFoodBtn = new JRadioButton("�x/��/�䦡");
		JapanKoreanFoodBtn = new JRadioButton("��/��/�F�n�Ȧ�");
		EastenFoodBtn = new JRadioButton("�覡");
		drinksBtn = new JRadioButton("���\/�I��/����");
		
		group2.add(taiwanFoodBtn);
		group2.add(JapanKoreanFoodBtn);
		group2.add(EastenFoodBtn);
		group2.add(drinksBtn);
		
		
		
		panel.add(taiwanFoodBtn);
		panel.add(JapanKoreanFoodBtn);
		panel.add(EastenFoodBtn);
		panel.add(drinksBtn);
		return panel;
	}

	public void createsubmitBtn() throws SQLException {
		submitBtn = new JButton("�e�X");
		class submitListener implements ActionListener {
			public void actionPerformed(ActionEvent e) {
				try {
					findRestaurant();
					//findRestaurant();
				} catch (SQLException e1) {
					// TODO: handle exception
					e1.getMessage();
				}
			}

		}
		submitBtn.addActionListener(new submitListener());
	}

	public void createcleanBtn() {
		cleanBtn = new JButton("�M���w��ݨD");
		class cleanListener implements ActionListener {
			public void actionPerformed(ActionEvent e) {
				try {
					requireBox.setSelectedIndex(0);

					group.clearSelection();
					priceBox.setSelectedIndex(0);
					taiwanFoodBtn.setSelected(false);
					JapanKoreanFoodBtn.setSelected(false);
					EastenFoodBtn.setSelected(false);
					drinksBtn.setSelected(false);
					buffetBtn.setSelected(false);
				} catch (Exception e1) {
					// TODO: handle exception
					e1.getMessage();
				}
			}
		}
		cleanBtn.addActionListener(new cleanListener());

	}

	public JPanel createInfo() {
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(1, 3));
		panel.setPreferredSize(new Dimension(500, 100));
		panel.setBorder(null);

		chooseCheckBoxLabel = new JLabel("���");
		restaurantNameLabel = new JLabel("�\�U�W��");
		evaluationLabel = new JLabel("����");

		panel.add(chooseCheckBoxLabel);
		panel.add(restaurantNameLabel);
		panel.add(evaluationLabel);

		return panel;

	}

	public JMenu createActionMenu() {
		JMenu action = new JMenu("Action");
		action.add(createCleanAllItem());
		return action;

	}

	public JMenuItem createCleanAllItem() {
		JMenuItem text = new JMenuItem("Clean all");
		class CleanALLListener implements ActionListener {
			public void actionPerformed(ActionEvent e) {
				requireBox.setSelectedIndex(0);
				group.clearSelection();
				priceBox.setSelectedIndex(0);
				taiwanFoodBtn.setSelected(false);
				JapanKoreanFoodBtn.setSelected(false);
				EastenFoodBtn.setSelected(false);
				drinksBtn.setSelected(false);
				// resultTextArea.setText("");
				textPane.setText("");
				InfoPanel.removeAll();
				

			}
		}
		text.addActionListener(new CleanALLListener());

		return text;

	}

	public void findRestaurant() throws SQLException {
		Connection connection = SimpleDataSourse.getConnection();
		Statement statement = connection.createStatement();
		if (requireBox.getSelectedIndex()==0) {
			String query = "SELECT Name , star FROM totalinformation WHERE Form = '�~�a' ";
			if (outsideTheDormBtn.isSelected()) {
				query += "AND Place = '���~' ";
				// 50
				if (priceBox.getSelectedItem().equals("50�H�U")) {
					query += "AND under50 = 1 ";
					if (taiwanFoodBtn.isSelected()) {
						query += "AND Style = '�x/��/�䦡' ";
						ResultSet resultSet = statement.executeQuery(query);
						while (resultSet.next()) {
							Choosing(resultSet.getString("Name"), resultSet.getDouble("star"));
							addPanel();
						}
					} else if (JapanKoreanFoodBtn.isSelected()) {
						query += "AND Style = '��/��/�F�n�Ȧ�' ";
						ResultSet resultSet = statement.executeQuery(query);
						while (resultSet.next()) {
							Choosing(resultSet.getString("Name"), resultSet.getDouble("star"));
							addPanel();
						}
					} else if (EastenFoodBtn.isSelected()) {
						query += "AND Style = '�覡' ";
						ResultSet resultSet = statement.executeQuery(query);
						while (resultSet.next()) {
							Choosing(resultSet.getString("Name"), resultSet.getDouble("star"));
							addPanel();
						}
					} else if (drinksBtn.isSelected()) {
						query += "AND Style = '���\/�I��/����' ";
						ResultSet resultSet = statement.executeQuery(query);
						while (resultSet.next()) {
							Choosing(resultSet.getString("Name"), resultSet.getDouble("star"));
							addPanel();
						}
					}
				}
				// 50-100
				else if (priceBox.getSelectedItem().equals("50~100")) {
					query += "AND `50-100` = 1 ";
					if (taiwanFoodBtn.isSelected()) {
						query += "AND Style = '�x/��/�䦡' ";
						ResultSet resultSet = statement.executeQuery(query);

						while (resultSet.next()) {
							Choosing(resultSet.getString("Name"), resultSet.getDouble("star"));
							addPanel();
						}
					} else if (JapanKoreanFoodBtn.isSelected()) {
						query += "AND Style = '��/��/�F�n�Ȧ�' ";
						ResultSet resultSet = statement.executeQuery(query);
						while (resultSet.next()) {
							Choosing(resultSet.getString("Name"), resultSet.getDouble("star"));
							addPanel();
						}
					} else if (EastenFoodBtn.isSelected()) {
						query += "AND Style = '�覡' ";
						ResultSet resultSet = statement.executeQuery(query);
						while (resultSet.next()) {
							Choosing(resultSet.getString("Name"), resultSet.getDouble("star"));
							addPanel();
						}
					} else if (drinksBtn.isSelected()) {
						query += "AND Style = '���\/�I��/����' ";
						ResultSet resultSet = statement.executeQuery(query);
						while (resultSet.next()) {
							Choosing(resultSet.getString("Name"), resultSet.getDouble("star"));
							addPanel();
						}
					}
				}
				// 100-150
				else if (priceBox.getSelectedItem().equals("100~150")) {
					query += "AND `100-150` = 1 ";
					if (taiwanFoodBtn.isSelected()) {
						query += "AND Style = '�x/��/�䦡' ";
						ResultSet resultSet = statement.executeQuery(query);
						while (resultSet.next()) {
							Choosing(resultSet.getString("Name"), resultSet.getDouble("star"));
							addPanel();
						}
					} else if (JapanKoreanFoodBtn.isSelected()) {
						query += "AND Style = '��/��/�F�n�Ȧ�' ";
						ResultSet resultSet = statement.executeQuery(query);
						while (resultSet.next()) {
							Choosing(resultSet.getString("Name"), resultSet.getDouble("star"));
							addPanel();
						}
					} else if (EastenFoodBtn.isSelected()) {
						query += "AND Style = '�覡' ";
						ResultSet resultSet = statement.executeQuery(query);
						while (resultSet.next()) {
							Choosing(resultSet.getString("Name"), resultSet.getDouble("star"));
							addPanel();
						}
					} else if (drinksBtn.isSelected()) {
						query += "AND Style = '���\/�I��/����' ";
						ResultSet resultSet = statement.executeQuery(query);
						while (resultSet.next()) {
							Choosing(resultSet.getString("Name"), resultSet.getDouble("star"));
							addPanel();
						}
					}
				}
				// 150-200
				else if (priceBox.getSelectedItem().equals("150~200")) {
					query += "AND `150-200` = 1 ";
					if (taiwanFoodBtn.isSelected()) {
						query += "AND Style = '�x/��/�䦡' ";
						ResultSet resultSet = statement.executeQuery(query);
						while (resultSet.next()) {
							Choosing(resultSet.getString("Name"), resultSet.getDouble("star"));
							addPanel();
						}
					} else if (JapanKoreanFoodBtn.isSelected()) {
						query += "AND Style = '��/��/�F�n�Ȧ�' ";
						ResultSet resultSet = statement.executeQuery(query);
						while (resultSet.next()) {
							Choosing(resultSet.getString("Name"), resultSet.getDouble("star"));
							addPanel();
						}
					} else if (EastenFoodBtn.isSelected()) {
						query += "AND Style = '�覡' ";
						ResultSet resultSet = statement.executeQuery(query);
						while (resultSet.next()) {
							Choosing(resultSet.getString("Name"), resultSet.getDouble("star"));
							addPanel();
						}
					} else if (drinksBtn.isSelected()) {
						query += "AND Style = '���\/�I��/����' ";
						ResultSet resultSet = statement.executeQuery(query);
						while (resultSet.next()) {
							Choosing(resultSet.getString("Name"), resultSet.getDouble("star"));
							addPanel();
						}
					}
				}
				// 200-250
				else if (priceBox.getSelectedItem().equals("200~250")) {
					query += "AND `200-250` = 1 ";
					if (taiwanFoodBtn.isSelected()) {
						query += "AND Style = '�x/��/�䦡' ";
						ResultSet resultSet = statement.executeQuery(query);
						while (resultSet.next()) {
							Choosing(resultSet.getString("Name"), resultSet.getDouble("star"));
							addPanel();
						}
					} else if (JapanKoreanFoodBtn.isSelected()) {
						query += "AND Style = '��/��/�F�n�Ȧ�' ";
						ResultSet resultSet = statement.executeQuery(query);
						while (resultSet.next()) {
							Choosing(resultSet.getString("Name"), resultSet.getDouble("star"));
							addPanel();
						}
					} else if (EastenFoodBtn.isSelected()) {
						query += "AND Style = '�覡' ";
						ResultSet resultSet = statement.executeQuery(query);
						while (resultSet.next()) {
							Choosing(resultSet.getString("Name"), resultSet.getDouble("star"));
							addPanel();
						}
					} else if (drinksBtn.isSelected()) {
						query += "AND Style = '���\/�I��/����' ";
						ResultSet resultSet = statement.executeQuery(query);
						while (resultSet.next()) {
							Choosing(resultSet.getString("Name"), resultSet.getDouble("star"));
							addPanel();
						}
					}
				}
				// 250-300
				else if (priceBox.getSelectedItem().equals("250~300")) {
					query += "AND `250-300` = 1 ";
					if (taiwanFoodBtn.isSelected()) {
						query += "AND Style = '�x/��/�䦡' ";
						ResultSet resultSet = statement.executeQuery(query);
						while (resultSet.next()) {
							Choosing(resultSet.getString("Name"), resultSet.getDouble("star"));
							addPanel();
						}
					} else if (JapanKoreanFoodBtn.isSelected()) {
						query += "AND Style = '��/��/�F�n�Ȧ�' ";
						ResultSet resultSet = statement.executeQuery(query);
						while (resultSet.next()) {
							Choosing(resultSet.getString("Name"), resultSet.getDouble("star"));
							addPanel();
						}
					} else if (EastenFoodBtn.isSelected()) {
						query += "AND Style = '�覡' ";
						ResultSet resultSet = statement.executeQuery(query);
						while (resultSet.next()) {
							Choosing(resultSet.getString("Name"), resultSet.getDouble("star"));
							addPanel();
						}
					} else if (drinksBtn.isSelected()) {
						query += "AND Style = '���\/�I��/����' ";
						ResultSet resultSet = statement.executeQuery(query);
						while (resultSet.next()) {
							Choosing(resultSet.getString("Name"), resultSet.getDouble("star"));
							addPanel();
						}
					}
				}
				// 300-350
				else if (priceBox.getSelectedItem().equals("300~350")) {
					query += "AND `300-350` = 1 ";
					if (taiwanFoodBtn.isSelected()) {
						query += "AND Style = '�x/��/�䦡' ";
						ResultSet resultSet = statement.executeQuery(query);
						while (resultSet.next()) {
							Choosing(resultSet.getString("Name"), resultSet.getDouble("star"));
							addPanel();
						}
					} else if (JapanKoreanFoodBtn.isSelected()) {
						query += "AND Style = '��/��/�F�n�Ȧ�' ";
						ResultSet resultSet = statement.executeQuery(query);
						while (resultSet.next()) {
							Choosing(resultSet.getString("Name"), resultSet.getDouble("star"));
							addPanel();
						}
					} else if (EastenFoodBtn.isSelected()) {
						query += "AND Style = '�覡' ";
						ResultSet resultSet = statement.executeQuery(query);
						while (resultSet.next()) {
							Choosing(resultSet.getString("Name"), resultSet.getDouble("star"));
							addPanel();
						}
					} else if (drinksBtn.isSelected()) {
						query += "AND Style = '���\/�I��/����' ";
						ResultSet resultSet = statement.executeQuery(query);
						while (resultSet.next()) {
							Choosing(resultSet.getString("Name"), resultSet.getDouble("star"));
							addPanel();
						}
					}
				}
				// 350-400
				else if (priceBox.getSelectedItem().equals("350~400")) {
					query += "AND `350-400` = 1 ";
					if (taiwanFoodBtn.isSelected()) {
						query += "AND Style = '�x/��/�䦡' ";
						ResultSet resultSet = statement.executeQuery(query);
						while (resultSet.next()) {
							Choosing(resultSet.getString("Name"), resultSet.getDouble("star"));
							addPanel();
						}
					} else if (JapanKoreanFoodBtn.isSelected()) {
						query += "AND Style = '��/��/�F�n�Ȧ�' ";
						ResultSet resultSet = statement.executeQuery(query);
						while (resultSet.next()) {
							Choosing(resultSet.getString("Name"), resultSet.getDouble("star"));
							addPanel();
						}
					} else if (EastenFoodBtn.isSelected()) {
						query += "AND Style = '�覡' ";
						ResultSet resultSet = statement.executeQuery(query);
						while (resultSet.next()) {
							Choosing(resultSet.getString("Name"), resultSet.getDouble("star"));
							addPanel();
						}
					} else if (drinksBtn.isSelected()) {
						query += "AND Style = '���\/�I��/����' ";
						ResultSet resultSet = statement.executeQuery(query);
						while (resultSet.next()) {
							Choosing(resultSet.getString("Name"), resultSet.getDouble("star"));
							addPanel();
						}
					}
				}
				// 400up
				else if (priceBox.getSelectedItem().equals("400�H�W")) {
					query += "AND `above400` = 1 ";
					if (taiwanFoodBtn.isSelected()) {
						query += "AND Style = '�x/��/�䦡' ";
						ResultSet resultSet = statement.executeQuery(query);
						while (resultSet.next()) {
							Choosing(resultSet.getString("Name"), resultSet.getDouble("star"));
							addPanel();
						}
					} else if (JapanKoreanFoodBtn.isSelected()) {
						query += "AND Style = '��/��/�F�n�Ȧ�' ";
						ResultSet resultSet = statement.executeQuery(query);
						while (resultSet.next()) {
							Choosing(resultSet.getString("Name"), resultSet.getDouble("star"));
							addPanel();
						}
					} else if (EastenFoodBtn.isSelected()) {
						query += "AND Style = '�覡' ";
						ResultSet resultSet = statement.executeQuery(query);
						while (resultSet.next()) {
							Choosing(resultSet.getString("Name"), resultSet.getDouble("star"));
							addPanel();
						}
					} else if (drinksBtn.isSelected()) {
						query += "AND Style = '���\/�I��/����' ";
						ResultSet resultSet = statement.executeQuery(query);
						while (resultSet.next()) {
							Choosing(resultSet.getString("Name"), resultSet.getDouble("star"));
							addPanel();
						}
					}
				}

			}
			
			else if (mcdBtn.isSelected()) {
				query += "AND Place = '����'";
				if (priceBox.getSelectedItem().equals("50�H�U")) {
					query += "AND under50 = 1 ";
					if (taiwanFoodBtn.isSelected()) {
						query += "AND Style = '�x/��/�䦡' ";
						ResultSet resultSet = statement.executeQuery(query);
						while (resultSet.next()) {
							Choosing(resultSet.getString("Name"), resultSet.getDouble("star"));
							addPanel();
						}
					} else if (JapanKoreanFoodBtn.isSelected()) {
						query += "AND Style = '��/��/�F�n�Ȧ�' ";
						ResultSet resultSet = statement.executeQuery(query);
						while (resultSet.next()) {
							Choosing(resultSet.getString("Name"), resultSet.getDouble("star"));
							addPanel();
						}
					} else if (EastenFoodBtn.isSelected()) {
						query += "AND Style = '�覡' ";
						ResultSet resultSet = statement.executeQuery(query);
						while (resultSet.next()) {
							Choosing(resultSet.getString("Name"), resultSet.getDouble("star"));
							addPanel();
						}
					} else if (drinksBtn.isSelected()) {
						query += "AND Style = '���\/�I��/����' ";
						ResultSet resultSet = statement.executeQuery(query);
						
						while (resultSet.next()) {
							Choosing(resultSet.getString("Name"), resultSet.getDouble("star"));
							addPanel();
						}
					}
				}
				// 50-100 test
				else if (priceBox.getSelectedItem().equals("50~100")) {
					query += "AND `50-100` = 1 ";
					if (taiwanFoodBtn.isSelected()) {
						query += "AND Style = '�x/��/�䦡' ";
						ResultSet resultSet = statement.executeQuery(query);
						while (resultSet.next()) {
							Choosing(resultSet.getString("Name"), resultSet.getDouble("star"));
							addPanel();
						}
					} else if (JapanKoreanFoodBtn.isSelected()) {
						query += "AND Style = '��/��/�F�n�Ȧ�' ";
						ResultSet resultSet = statement.executeQuery(query);
						while (resultSet.next()) {
							Choosing(resultSet.getString("Name"), resultSet.getDouble("star"));
							addPanel();
						}
					} else if (EastenFoodBtn.isSelected()) {
						query += "AND Style = '�覡' ";
						ResultSet resultSet = statement.executeQuery(query);
						while (resultSet.next()) {
							Choosing(resultSet.getString("Name"), resultSet.getDouble("star"));
							addPanel();
						}
					} else if (drinksBtn.isSelected()) {
						query += "AND Style = '���\/�I��/����' ";
						ResultSet resultSet = statement.executeQuery(query);
						
						while (resultSet.next()) {
							Choosing(resultSet.getString("Name"), resultSet.getDouble("star"));
							addPanel();
						}
					}
				}
				// 100-150
				else if (priceBox.getSelectedItem().equals("100~150")) {
					query += "AND `100-150` = 1 ";
					if (taiwanFoodBtn.isSelected()) {
						query += "AND Style = '�x/��/�䦡' ";
						ResultSet resultSet = statement.executeQuery(query);
						while (resultSet.next()) {
							Choosing(resultSet.getString("Name"), resultSet.getDouble("star"));
							addPanel();
						}
					} else if (JapanKoreanFoodBtn.isSelected()) {
						query += "AND Style = '��/��/�F�n�Ȧ�' ";
						ResultSet resultSet = statement.executeQuery(query);
						while (resultSet.next()) {
							Choosing(resultSet.getString("Name"), resultSet.getDouble("star"));
							addPanel();
						}
					} else if (EastenFoodBtn.isSelected()) {
						query += "AND Style = '�覡' ";
						ResultSet resultSet = statement.executeQuery(query);
						while (resultSet.next()) {
							Choosing(resultSet.getString("Name"), resultSet.getDouble("star"));
							addPanel();
						}
					} else if (drinksBtn.isSelected()) {
						query += "AND Style = '���\/�I��/����' ";
						ResultSet resultSet = statement.executeQuery(query);
						while (resultSet.next()) {
							Choosing(resultSet.getString("Name"), resultSet.getDouble("star"));
							addPanel();
						}
					}
				}
				// 150-200
				else if (priceBox.getSelectedItem().equals("150~200")) {
					query += "AND `150-200` = 1 ";
					if (taiwanFoodBtn.isSelected()) {
						query += "AND Style = '�x/��/�䦡' ";
						ResultSet resultSet = statement.executeQuery(query);
						while (resultSet.next()) {
							Choosing(resultSet.getString("Name"), resultSet.getDouble("star"));
							addPanel();
						}
					} else if (JapanKoreanFoodBtn.isSelected()) {
						query += "AND Style = '��/��/�F�n�Ȧ�' ";
						ResultSet resultSet = statement.executeQuery(query);
						while (resultSet.next()) {
							Choosing(resultSet.getString("Name"), resultSet.getDouble("star"));
							addPanel();
						}
					} else if (EastenFoodBtn.isSelected()) {
						query += "AND Style = '�覡' ";
						ResultSet resultSet = statement.executeQuery(query);
						while (resultSet.next()) {
							Choosing(resultSet.getString("Name"), resultSet.getDouble("star"));
							addPanel();
						}
					} else if (drinksBtn.isSelected()) {
						query += "AND Style = '���\/�I��/����' ";
						ResultSet resultSet = statement.executeQuery(query);
						while (resultSet.next()) {
							Choosing(resultSet.getString("Name"), resultSet.getDouble("star"));
							addPanel();
						}
					}
				}
				// 200-250
				else if (priceBox.getSelectedItem().equals("200~250")) {
					query += "AND `200-250` = 1 ";
					if (taiwanFoodBtn.isSelected()) {
						query += "AND Style = '�x/��/�䦡' ";
						ResultSet resultSet = statement.executeQuery(query);
						while (resultSet.next()) {
							Choosing(resultSet.getString("Name"), resultSet.getDouble("star"));
							addPanel();
						}
					} else if (JapanKoreanFoodBtn.isSelected()) {
						query += "AND Style = '��/��/�F�n�Ȧ�' ";
						ResultSet resultSet = statement.executeQuery(query);
						while (resultSet.next()) {
							Choosing(resultSet.getString("Name"), resultSet.getDouble("star"));
							addPanel();
						}
					} else if (EastenFoodBtn.isSelected()) {
						query += "AND Style = '�覡' ";
						ResultSet resultSet = statement.executeQuery(query);
						while (resultSet.next()) {
							Choosing(resultSet.getString("Name"), resultSet.getDouble("star"));
							addPanel();
						}
					} else if (drinksBtn.isSelected()) {
						query += "AND Style = '���\/�I��/����' ";
						ResultSet resultSet = statement.executeQuery(query);
						while (resultSet.next()) {
							Choosing(resultSet.getString("Name"), resultSet.getDouble("star"));
							addPanel();
						}
					}
				}
				// 250-300
				else if (priceBox.getSelectedItem().equals("250~300")) {
					query += "AND `250-300` = 1 ";
					if (taiwanFoodBtn.isSelected()) {
						query += "AND Style = '�x/��/�䦡' ";
						ResultSet resultSet = statement.executeQuery(query);
						while (resultSet.next()) {
							Choosing(resultSet.getString("Name"), resultSet.getDouble("star"));
							addPanel();
						}
					} else if (JapanKoreanFoodBtn.isSelected()) {
						query += "AND Style = '��/��/�F�n�Ȧ�' ";
						ResultSet resultSet = statement.executeQuery(query);
						while (resultSet.next()) {
							Choosing(resultSet.getString("Name"), resultSet.getDouble("star"));
							addPanel();
						}
					} else if (EastenFoodBtn.isSelected()) {
						query += "AND Style = '�覡' ";
						ResultSet resultSet = statement.executeQuery(query);
						while (resultSet.next()) {
							Choosing(resultSet.getString("Name"), resultSet.getDouble("star"));
							addPanel();
						}
					} else if (drinksBtn.isSelected()) {
						query += "AND Style = '���\/�I��/����' ";
						ResultSet resultSet = statement.executeQuery(query);
						while (resultSet.next()) {
							Choosing(resultSet.getString("Name"), resultSet.getDouble("star"));
							addPanel();
						}
					}
				}
				// 300-350
				else if (priceBox.getSelectedItem().equals("300~350")) {
					query += "AND `300-350` = 1 ";
					if (taiwanFoodBtn.isSelected()) {
						query += "AND Style = '�x/��/�䦡' ";
						ResultSet resultSet = statement.executeQuery(query);
						while (resultSet.next()) {
							Choosing(resultSet.getString("Name"), resultSet.getDouble("star"));
							addPanel();
						}
					} else if (JapanKoreanFoodBtn.isSelected()) {
						query += "AND Style = '��/��/�F�n�Ȧ�' ";
						ResultSet resultSet = statement.executeQuery(query);
						while (resultSet.next()) {
							Choosing(resultSet.getString("Name"), resultSet.getDouble("star"));
							addPanel();
						}
					} else if (EastenFoodBtn.isSelected()) {
						query += "AND Style = '�覡' ";
						ResultSet resultSet = statement.executeQuery(query);
						while (resultSet.next()) {
							Choosing(resultSet.getString("Name"), resultSet.getDouble("star"));
							addPanel();
						}
					} else if (drinksBtn.isSelected()) {
						query += "AND Style = '���\/�I��/����' ";
						ResultSet resultSet = statement.executeQuery(query);
						while (resultSet.next()) {
							Choosing(resultSet.getString("Name"), resultSet.getDouble("star"));
							addPanel();
						}
					}
				}
				// 350-400
				else if (priceBox.getSelectedItem().equals("350~400")) {
					query += "AND `350-400` = 1 ";
					if (taiwanFoodBtn.isSelected()) {
						query += "AND Style = '�x/��/�䦡' ";
						ResultSet resultSet = statement.executeQuery(query);
						while (resultSet.next()) {
							Choosing(resultSet.getString("Name"), resultSet.getDouble("star"));
							addPanel();
						}
					} else if (JapanKoreanFoodBtn.isSelected()) {
						query += "AND Style = '��/��/�F�n�Ȧ�' ";
						ResultSet resultSet = statement.executeQuery(query);
						while (resultSet.next()) {
							Choosing(resultSet.getString("Name"), resultSet.getDouble("star"));
							addPanel();
						}
					} else if (EastenFoodBtn.isSelected()) {
						query += "AND Style = '�覡' ";
						ResultSet resultSet = statement.executeQuery(query);
						while (resultSet.next()) {
							Choosing(resultSet.getString("Name"), resultSet.getDouble("star"));
							addPanel();
						}
					} else if (drinksBtn.isSelected()) {
						query += "AND Style = '���\/�I��/����' ";
						ResultSet resultSet = statement.executeQuery(query);
						while (resultSet.next()) {
							Choosing(resultSet.getString("Name"), resultSet.getDouble("star"));
							addPanel();
						}
					}
				}
				// 400up
				else if (priceBox.getSelectedItem().equals("400�H�W")) {
					query += "AND `above400` = 1 ";
					if (taiwanFoodBtn.isSelected()) {
						query += "AND Style = '�x/��/�䦡' ";
						ResultSet resultSet = statement.executeQuery(query);
						while (resultSet.next()) {
							Choosing(resultSet.getString("Name"), resultSet.getDouble("star"));
							addPanel();
						}
					} else if (JapanKoreanFoodBtn.isSelected()) {
						query += "AND Style = '��/��/�F�n�Ȧ�' ";
						ResultSet resultSet = statement.executeQuery(query);
						while (resultSet.next()) {
							Choosing(resultSet.getString("Name"), resultSet.getDouble("star"));
							addPanel();
						}
					} else if (EastenFoodBtn.isSelected()) {
						query += "AND Style = '�覡' ";
						ResultSet resultSet = statement.executeQuery(query);
						while (resultSet.next()) {
							Choosing(resultSet.getString("Name"), resultSet.getDouble("star"));
							addPanel();
						}
					} else if (drinksBtn.isSelected()) {
						query += "AND Style = '���\/�I��/����' ";
						ResultSet resultSet = statement.executeQuery(query);
						while (resultSet.next()) {
							Choosing(resultSet.getString("Name"), resultSet.getDouble("star"));
							addPanel();
						}
					}
				}

			}
			else if (stoneBtn.isSelected()) {
				query += "AND Place = '�ոO'";
				if (priceBox.getSelectedItem().equals("50�H�U")) {
					query += "AND under50 = 1 ";
					if (taiwanFoodBtn.isSelected()) {
						query += "AND Style = '�x/��/�䦡' ";
						ResultSet resultSet = statement.executeQuery(query);
						while (resultSet.next()) {
							Choosing(resultSet.getString("Name"), resultSet.getDouble("star"));
							addPanel();
						}
					} else if (JapanKoreanFoodBtn.isSelected()) {
						query += "AND Style = '��/��/�F�n�Ȧ�' ";
						ResultSet resultSet = statement.executeQuery(query);
						while (resultSet.next()) {
							Choosing(resultSet.getString("Name"), resultSet.getDouble("star"));
							addPanel();
						}
					} else if (EastenFoodBtn.isSelected()) {
						query += "AND Style = '�覡' ";
						ResultSet resultSet = statement.executeQuery(query);
						while (resultSet.next()) {
							Choosing(resultSet.getString("Name"), resultSet.getDouble("star"));
							addPanel();
						}
					} else if (drinksBtn.isSelected()) {
						query += "AND Style = '���\/�I��/����' ";
						ResultSet resultSet = statement.executeQuery(query);
						while (resultSet.next()) {
							Choosing(resultSet.getString("Name"), resultSet.getDouble("star"));
							addPanel();
						}
					}
				}
				// 50-100
				else if (priceBox.getSelectedItem().equals("50~100")) {
					query += "AND `50-100` = 1 ";
					if (taiwanFoodBtn.isSelected()) {
						query += "AND Style = '�x/��/�䦡' ";
						ResultSet resultSet = statement.executeQuery(query);
						while (resultSet.next()) {
							Choosing(resultSet.getString("Name"), resultSet.getDouble("star"));
							addPanel();
						}
					} else if (JapanKoreanFoodBtn.isSelected()) {
						query += "AND Style = '��/��/�F�n�Ȧ�' ";
						ResultSet resultSet = statement.executeQuery(query);
						while (resultSet.next()) {
							Choosing(resultSet.getString("Name"), resultSet.getDouble("star"));
							addPanel();
						}
					} else if (EastenFoodBtn.isSelected()) {
						query += "AND Style = '�覡' ";
						ResultSet resultSet = statement.executeQuery(query);
						while (resultSet.next()) {
							Choosing(resultSet.getString("Name"), resultSet.getDouble("star"));
							addPanel();
						}
					} else if (drinksBtn.isSelected()) {
						query += "AND Style = '���\/�I��/����' ";
						ResultSet resultSet = statement.executeQuery(query);
						while (resultSet.next()) {
							Choosing(resultSet.getString("Name"), resultSet.getDouble("star"));
							addPanel();
						}
					}
				}
				// 100-150
				else if (priceBox.getSelectedItem().equals("100~150")) {
					query += "AND `100-150` = 1 ";
					if (taiwanFoodBtn.isSelected()) {
						query += "AND Style = '�x/��/�䦡' ";
						ResultSet resultSet = statement.executeQuery(query);
						while (resultSet.next()) {
							Choosing(resultSet.getString("Name"), resultSet.getDouble("star"));
							addPanel();
						}
					} else if (JapanKoreanFoodBtn.isSelected()) {
						query += "AND Style = '��/��/�F�n�Ȧ�' ";
						ResultSet resultSet = statement.executeQuery(query);
						while (resultSet.next()) {
							Choosing(resultSet.getString("Name"), resultSet.getDouble("star"));
							addPanel();
						}
					} else if (EastenFoodBtn.isSelected()) {
						query += "AND Style = '�覡' ";
						ResultSet resultSet = statement.executeQuery(query);
						while (resultSet.next()) {
							Choosing(resultSet.getString("Name"), resultSet.getDouble("star"));
							addPanel();
						}
					} else if (drinksBtn.isSelected()) {
						query += "AND Style = '���\/�I��/����' ";
						ResultSet resultSet = statement.executeQuery(query);
						while (resultSet.next()) {
							Choosing(resultSet.getString("Name"), resultSet.getDouble("star"));
							addPanel();
						}
					}
				}
				// 150-200
				else if (priceBox.getSelectedItem().equals("150~200")) {
					query += "AND `150-200` = 1 ";
					if (taiwanFoodBtn.isSelected()) {
						query += "AND Style = '�x/��/�䦡' ";
						ResultSet resultSet = statement.executeQuery(query);
						while (resultSet.next()) {
							Choosing(resultSet.getString("Name"), resultSet.getDouble("star"));
							addPanel();
						}
					} else if (JapanKoreanFoodBtn.isSelected()) {
						query += "AND Style = '��/��/�F�n�Ȧ�' ";
						ResultSet resultSet = statement.executeQuery(query);
						while (resultSet.next()) {
							Choosing(resultSet.getString("Name"), resultSet.getDouble("star"));
							addPanel();
						}
					} else if (EastenFoodBtn.isSelected()) {
						query += "AND Style = '�覡' ";
						ResultSet resultSet = statement.executeQuery(query);
						while (resultSet.next()) {
							Choosing(resultSet.getString("Name"), resultSet.getDouble("star"));
							addPanel();
						}
					} else if (drinksBtn.isSelected()) {
						query += "AND Style = '���\/�I��/����' ";
						ResultSet resultSet = statement.executeQuery(query);
						while (resultSet.next()) {
							Choosing(resultSet.getString("Name"), resultSet.getDouble("star"));
							addPanel();
						}
					}
				}
				// 200-250
				else if (priceBox.getSelectedItem().equals("200~250")) {
					query += "AND `200-250` = 1 ";
					if (taiwanFoodBtn.isSelected()) {
						query += "AND Style = '�x/��/�䦡' ";
						ResultSet resultSet = statement.executeQuery(query);
						while (resultSet.next()) {
							Choosing(resultSet.getString("Name"), resultSet.getDouble("star"));
							addPanel();
						}
					} else if (JapanKoreanFoodBtn.isSelected()) {
						query += "AND Style = '��/��/�F�n�Ȧ�' ";
						ResultSet resultSet = statement.executeQuery(query);
						while (resultSet.next()) {
							Choosing(resultSet.getString("Name"), resultSet.getDouble("star"));
							addPanel();
						}
					} else if (EastenFoodBtn.isSelected()) {
						query += "AND Style = '�覡' ";
						ResultSet resultSet = statement.executeQuery(query);
						while (resultSet.next()) {
							Choosing(resultSet.getString("Name"), resultSet.getDouble("star"));
							addPanel();
						}
					} else if (drinksBtn.isSelected()) {
						query += "AND Style = '���\/�I��/����' ";
						ResultSet resultSet = statement.executeQuery(query);
						while (resultSet.next()) {
							Choosing(resultSet.getString("Name"), resultSet.getDouble("star"));
							addPanel();
						}
					}
				}
				// 250-300
				else if (priceBox.getSelectedItem().equals("250~300")) {
					query += "AND `250-300` = 1 ";
					if (taiwanFoodBtn.isSelected()) {
						query += "AND Style = '�x/��/�䦡' ";
						ResultSet resultSet = statement.executeQuery(query);
						while (resultSet.next()) {
							Choosing(resultSet.getString("Name"), resultSet.getDouble("star"));
							addPanel();
						}
					} else if (JapanKoreanFoodBtn.isSelected()) {
						query += "AND Style = '��/��/�F�n�Ȧ�' ";
						ResultSet resultSet = statement.executeQuery(query);
						while (resultSet.next()) {
							Choosing(resultSet.getString("Name"), resultSet.getDouble("star"));
							addPanel();
						}
					} else if (EastenFoodBtn.isSelected()) {
						query += "AND Style = '�覡' ";
						ResultSet resultSet = statement.executeQuery(query);
						while (resultSet.next()) {
							Choosing(resultSet.getString("Name"), resultSet.getDouble("star"));
							addPanel();
						}
					} else if (drinksBtn.isSelected()) {
						query += "AND Style = '���\/�I��/����' ";
						ResultSet resultSet = statement.executeQuery(query);
						while (resultSet.next()) {
							Choosing(resultSet.getString("Name"), resultSet.getDouble("star"));
							addPanel();
						}
					}
				}
				// 300-350
				else if (priceBox.getSelectedItem().equals("300~350")) {
					query += "AND `300-350` = 1 ";
					if (taiwanFoodBtn.isSelected()) {
						query += "AND Style = '�x/��/�䦡' ";
						ResultSet resultSet = statement.executeQuery(query);
						while (resultSet.next()) {
							Choosing(resultSet.getString("Name"), resultSet.getDouble("star"));
							addPanel();
						}
					} else if (JapanKoreanFoodBtn.isSelected()) {
						query += "AND Style = '��/��/�F�n�Ȧ�' ";
						ResultSet resultSet = statement.executeQuery(query);
						while (resultSet.next()) {
							Choosing(resultSet.getString("Name"), resultSet.getDouble("star"));
							addPanel();
						}
					} else if (EastenFoodBtn.isSelected()) {
						query += "AND Style = '�覡' ";
						ResultSet resultSet = statement.executeQuery(query);
						while (resultSet.next()) {
							Choosing(resultSet.getString("Name"), resultSet.getDouble("star"));
							addPanel();
						}
					} else if (drinksBtn.isSelected()) {
						query += "AND Style = '���\/�I��/����' ";
						ResultSet resultSet = statement.executeQuery(query);
						while (resultSet.next()) {
							Choosing(resultSet.getString("Name"), resultSet.getDouble("star"));
							addPanel();
						}
					}
				}
				// 350-400
				else if (priceBox.getSelectedItem().equals("350~400")) {
					query += "AND `350-400` = 1 ";
					if (taiwanFoodBtn.isSelected()) {
						query += "AND Style = '�x/��/�䦡' ";
						ResultSet resultSet = statement.executeQuery(query);
						while (resultSet.next()) {
							Choosing(resultSet.getString("Name"), resultSet.getDouble("star"));
							addPanel();
						}
					} else if (JapanKoreanFoodBtn.isSelected()) {
						query += "AND Style = '��/��/�F�n�Ȧ�' ";
						ResultSet resultSet = statement.executeQuery(query);
						while (resultSet.next()) {
							Choosing(resultSet.getString("Name"), resultSet.getDouble("star"));
							addPanel();
						}
					} else if (EastenFoodBtn.isSelected()) {
						query += "AND Style = '�覡' ";
						ResultSet resultSet = statement.executeQuery(query);
						while (resultSet.next()) {
							Choosing(resultSet.getString("Name"), resultSet.getDouble("star"));
							addPanel();
						}
					} else if (drinksBtn.isSelected()) {
						query += "AND Style = '���\/�I��/����' ";
						ResultSet resultSet = statement.executeQuery(query);
						while (resultSet.next()) {
							Choosing(resultSet.getString("Name"), resultSet.getDouble("star"));
							addPanel();
						}
					}
				}
				// 400up
				else if (priceBox.getSelectedItem().equals("400�H�W")) {
					query += "AND `above400` = 1 ";
					if (taiwanFoodBtn.isSelected()) {
						query += "AND Style = '�x/��/�䦡' ";
						ResultSet resultSet = statement.executeQuery(query);
						while (resultSet.next()) {
							Choosing(resultSet.getString("Name"), resultSet.getDouble("star"));
							addPanel();
						}
					} else if (JapanKoreanFoodBtn.isSelected()) {
						query += "AND Style = '��/��/�F�n�Ȧ�' ";
						ResultSet resultSet = statement.executeQuery(query);
						while (resultSet.next()) {
							Choosing(resultSet.getString("Name"), resultSet.getDouble("star"));
							addPanel();
						}
					} else if (EastenFoodBtn.isSelected()) {
						query += "AND Style = '�覡' ";
						ResultSet resultSet = statement.executeQuery(query);
						while (resultSet.next()) {
							Choosing(resultSet.getString("Name"), resultSet.getDouble("star"));
							addPanel();
						}
					} else if (drinksBtn.isSelected()) {
						query += "AND Style = '���\/�I��/����' ";
						ResultSet resultSet = statement.executeQuery(query);
						while (resultSet.next()) {
							Choosing(resultSet.getString("Name"), resultSet.getDouble("star"));
							addPanel();
						}
					}
				}
			}
			if (BuildingBtn.isSelected()) {
				query += "AND Place = '�ͽ��'";
				if (priceBox.getSelectedItem().equals("50�H�U")) {
					query += "AND under50 = 1 ";
					if (taiwanFoodBtn.isSelected()) {
						query += "AND Style = '�x/��/�䦡' ";
						ResultSet resultSet = statement.executeQuery(query);
						while (resultSet.next()) {
							Choosing(resultSet.getString("Name"), resultSet.getDouble("star"));
							addPanel();
						}
					} else if (JapanKoreanFoodBtn.isSelected()) {
						query += "AND Style = '��/��/�F�n�Ȧ�' ";
						ResultSet resultSet = statement.executeQuery(query);
						while (resultSet.next()) {
							Choosing(resultSet.getString("Name"), resultSet.getDouble("star"));
							addPanel();
						}
					} else if (EastenFoodBtn.isSelected()) {
						query += "AND Style = '�覡' ";
						ResultSet resultSet = statement.executeQuery(query);
						while (resultSet.next()) {
							Choosing(resultSet.getString("Name"), resultSet.getDouble("star"));
							addPanel();
						}
					} else if (drinksBtn.isSelected()) {
						query += "AND Style = '���\/�I��/����' ";
						ResultSet resultSet = statement.executeQuery(query);
						while (resultSet.next()) {
							Choosing(resultSet.getString("Name"), resultSet.getDouble("star"));
							addPanel();
						}
					}
				}
				// 50-100
				else if (priceBox.getSelectedItem().equals("50~100")) {
					query += "AND `50-100` = 1 ";
					if (taiwanFoodBtn.isSelected()) {
						query += "AND Style = '�x/��/�䦡' ";
						ResultSet resultSet = statement.executeQuery(query);
						while (resultSet.next()) {
							Choosing(resultSet.getString("Name"), resultSet.getDouble("star"));
							addPanel();
						}
					} else if (JapanKoreanFoodBtn.isSelected()) {
						query += "AND Style = '��/��/�F�n�Ȧ�' ";
						ResultSet resultSet = statement.executeQuery(query);
						while (resultSet.next()) {
							Choosing(resultSet.getString("Name"), resultSet.getDouble("star"));
							addPanel();
						}
					} else if (EastenFoodBtn.isSelected()) {
						query += "AND Style = '�覡' ";
						ResultSet resultSet = statement.executeQuery(query);
						while (resultSet.next()) {
							Choosing(resultSet.getString("Name"), resultSet.getDouble("star"));
							addPanel();
						}
					} else if (drinksBtn.isSelected()) {
						query += "AND Style = '���\/�I��/����' ";
						ResultSet resultSet = statement.executeQuery(query);
						while (resultSet.next()) {
							Choosing(resultSet.getString("Name"), resultSet.getDouble("star"));
							addPanel();
						}
					}
				}
				// 100-150
				else if (priceBox.getSelectedItem().equals("100~150")) {
					query += "AND `100-150` = 1 ";
					if (taiwanFoodBtn.isSelected()) {
						query += "AND Style = '�x/��/�䦡' ";
						ResultSet resultSet = statement.executeQuery(query);
						while (resultSet.next()) {
							Choosing(resultSet.getString("Name"), resultSet.getDouble("star"));
							addPanel();
						}
					} else if (JapanKoreanFoodBtn.isSelected()) {
						query += "AND Style = '��/��/�F�n�Ȧ�' ";
						ResultSet resultSet = statement.executeQuery(query);
						while (resultSet.next()) {
							Choosing(resultSet.getString("Name"), resultSet.getDouble("star"));
							addPanel();
						}
					} else if (EastenFoodBtn.isSelected()) {
						query += "AND Style = '�覡' ";
						ResultSet resultSet = statement.executeQuery(query);
						while (resultSet.next()) {
							Choosing(resultSet.getString("Name"), resultSet.getDouble("star"));
							addPanel();
						}
					} else if (drinksBtn.isSelected()) {
						query += "AND Style = '���\/�I��/����' ";
						ResultSet resultSet = statement.executeQuery(query);
						while (resultSet.next()) {
							Choosing(resultSet.getString("Name"), resultSet.getDouble("star"));
							addPanel();
						}
					}
				}
				// 150-200
				else if (priceBox.getSelectedItem().equals("150~200")) {
					query += "AND `150-200` = 1 ";
					if (taiwanFoodBtn.isSelected()) {
						query += "AND Style = '�x/��/�䦡' ";
						ResultSet resultSet = statement.executeQuery(query);
						while (resultSet.next()) {
							Choosing(resultSet.getString("Name"), resultSet.getDouble("star"));
							addPanel();
						}
					} else if (JapanKoreanFoodBtn.isSelected()) {
						query += "AND Style = '��/��/�F�n�Ȧ�' ";
						ResultSet resultSet = statement.executeQuery(query);
						while (resultSet.next()) {
							Choosing(resultSet.getString("Name"), resultSet.getDouble("star"));
							addPanel();
						}
					} else if (EastenFoodBtn.isSelected()) {
						query += "AND Style = '�覡' ";
						ResultSet resultSet = statement.executeQuery(query);
						while (resultSet.next()) {
							Choosing(resultSet.getString("Name"), resultSet.getDouble("star"));
							addPanel();
						}
					} else if (drinksBtn.isSelected()) {
						query += "AND Style = '���\/�I��/����' ";
						ResultSet resultSet = statement.executeQuery(query);
						while (resultSet.next()) {
							Choosing(resultSet.getString("Name"), resultSet.getDouble("star"));
							addPanel();
						}
					}
				}
				// 200-250
				else if (priceBox.getSelectedItem().equals("200~250")) {
					query += "AND `200-250` = 1 ";
					if (taiwanFoodBtn.isSelected()) {
						query += "AND Style = '�x/��/�䦡' ";
						ResultSet resultSet = statement.executeQuery(query);
						while (resultSet.next()) {
							Choosing(resultSet.getString("Name"), resultSet.getDouble("star"));
							addPanel();
						}
					} else if (JapanKoreanFoodBtn.isSelected()) {
						query += "AND Style = '��/��/�F�n�Ȧ�' ";
						ResultSet resultSet = statement.executeQuery(query);
						while (resultSet.next()) {
							Choosing(resultSet.getString("Name"), resultSet.getDouble("star"));
							addPanel();
						}
					} else if (EastenFoodBtn.isSelected()) {
						query += "AND Style = '�覡' ";
						ResultSet resultSet = statement.executeQuery(query);
						while (resultSet.next()) {
							Choosing(resultSet.getString("Name"), resultSet.getDouble("star"));
							addPanel();
						}
					} else if (drinksBtn.isSelected()) {
						query += "AND Style = '���\/�I��/����' ";
						ResultSet resultSet = statement.executeQuery(query);
						while (resultSet.next()) {
							Choosing(resultSet.getString("Name"), resultSet.getDouble("star"));
							addPanel();
						}
					}
				}
				// 250-300
				else if (priceBox.getSelectedItem().equals("250~300")) {
					query += "AND `250-300` = 1 ";
					if (taiwanFoodBtn.isSelected()) {
						query += "AND Style = '�x/��/�䦡' ";
						ResultSet resultSet = statement.executeQuery(query);
						while (resultSet.next()) {
							Choosing(resultSet.getString("Name"), resultSet.getDouble("star"));
							addPanel();
						}
					} else if (JapanKoreanFoodBtn.isSelected()) {
						query += "AND Style = '��/��/�F�n�Ȧ�' ";
						ResultSet resultSet = statement.executeQuery(query);
						while (resultSet.next()) {
							Choosing(resultSet.getString("Name"), resultSet.getDouble("star"));
							addPanel();
						}
					} else if (EastenFoodBtn.isSelected()) {
						query += "AND Style = '�覡' ";
						ResultSet resultSet = statement.executeQuery(query);
						while (resultSet.next()) {
							Choosing(resultSet.getString("Name"), resultSet.getDouble("star"));
							addPanel();
						}
					} else if (drinksBtn.isSelected()) {
						query += "AND Style = '���\/�I��/����' ";
						ResultSet resultSet = statement.executeQuery(query);
						while (resultSet.next()) {
							Choosing(resultSet.getString("Name"), resultSet.getDouble("star"));
							addPanel();
						}
					}
				}
				// 300-350
				else if (priceBox.getSelectedItem().equals("300~350")) {
					query += "AND `300-350` = 1 ";
					if (taiwanFoodBtn.isSelected()) {
						query += "AND Style = '�x/��/�䦡' ";
						ResultSet resultSet = statement.executeQuery(query);
						while (resultSet.next()) {
							Choosing(resultSet.getString("Name"), resultSet.getDouble("star"));
							addPanel();
						}
					} else if (JapanKoreanFoodBtn.isSelected()) {
						query += "AND Style = '��/��/�F�n�Ȧ�' ";
						ResultSet resultSet = statement.executeQuery(query);
						while (resultSet.next()) {
							Choosing(resultSet.getString("Name"), resultSet.getDouble("star"));
							addPanel();
						}
					} else if (EastenFoodBtn.isSelected()) {
						query += "AND Style = '�覡' ";
						ResultSet resultSet = statement.executeQuery(query);
						while (resultSet.next()) {
							Choosing(resultSet.getString("Name"), resultSet.getDouble("star"));
							addPanel();
						}
					} else if (drinksBtn.isSelected()) {
						query += "AND Style = '���\/�I��/����' ";
						ResultSet resultSet = statement.executeQuery(query);
						while (resultSet.next()) {
							Choosing(resultSet.getString("Name"), resultSet.getDouble("star"));
							addPanel();
						}
						;
					}
				}
				// 350-400
				else if (priceBox.getSelectedItem().equals("350~400")) {
					query += "AND `350-400` = 1";
					if (taiwanFoodBtn.isSelected()) {
						query += "AND Style = '�x/��/�䦡' ";
						ResultSet resultSet = statement.executeQuery(query);
						while (resultSet.next()) {
							Choosing(resultSet.getString("Name"), resultSet.getDouble("star"));
							addPanel();
						}
					} else if (JapanKoreanFoodBtn.isSelected()) {
						query += "AND Style = '��/��/�F�n�Ȧ�' ";
						ResultSet resultSet = statement.executeQuery(query);
						while (resultSet.next()) {
							Choosing(resultSet.getString("Name"), resultSet.getDouble("star"));
							addPanel();
						}
					} else if (EastenFoodBtn.isSelected()) {
						query += "AND Style = '�覡' ";
						ResultSet resultSet = statement.executeQuery(query);
						while (resultSet.next()) {
							Choosing(resultSet.getString("Name"), resultSet.getDouble("star"));
							addPanel();
						}
					} else if (drinksBtn.isSelected()) {
						query += "AND Style = '���\/�I��/����' ";
						ResultSet resultSet = statement.executeQuery(query);
						while (resultSet.next()) {
							Choosing(resultSet.getString("Name"), resultSet.getDouble("star"));
							addPanel();
						}
					}
				}
				// 400up
				else if (priceBox.getSelectedItem().equals("400�H�W")) {
					query += "AND `above400` = 1 ";
					if (taiwanFoodBtn.isSelected()) {
						query += "AND Style = '�x/��/�䦡' ";
						ResultSet resultSet = statement.executeQuery(query);
						while (resultSet.next()) {
							Choosing(resultSet.getString("Name"), resultSet.getDouble("star"));
							addPanel();
						}
					} else if (JapanKoreanFoodBtn.isSelected()) {
						query += "AND Style = '��/��/�F�n�Ȧ�' ";
						ResultSet resultSet = statement.executeQuery(query);
						while (resultSet.next()) {
							Choosing(resultSet.getString("Name"), resultSet.getDouble("star"));
							addPanel();
						}
					} else if (EastenFoodBtn.isSelected()) {
						query += "AND Style = '�覡' ";
						ResultSet resultSet = statement.executeQuery(query);
						while (resultSet.next()) {
							Choosing(resultSet.getString("Name"), resultSet.getDouble("star"));
							addPanel();
						}
					} else if (drinksBtn.isSelected()) {
						query += "AND Style = '���\/�I��/����' ";
						ResultSet resultSet = statement.executeQuery(query);
						while (resultSet.next()) {
							Choosing(resultSet.getString("Name"), resultSet.getDouble("star"));
							addPanel();
						}
					}
				}

			}

		}
		else if (requireBox.getSelectedIndex()==2) {
			String query1 = "SELECT Name, star FROM totalinformation WHERE Form = '���\'";
			if (outsideTheDormBtn.isSelected()) {
				query1 += "AND Place = '���~'";
				// 50
				if (priceBox.getSelectedItem().equals("50�H�U")) {
					query1 += "AND under50 = 1 ";
					if (taiwanFoodBtn.isSelected()) {
						query1 += "AND Style = '�x/��/�䦡' ";
						ResultSet resultSet = statement.executeQuery(query1);
						while (resultSet.next()) {
							Choosing(resultSet.getString("Name"), resultSet.getDouble("star"));
							addPanel();
						}
					} else if (JapanKoreanFoodBtn.isSelected()) {
						query1 += "AND Style = '��/��/�F�n�Ȧ�' ";
						ResultSet resultSet = statement.executeQuery(query1);
						while (resultSet.next()) {
							Choosing(resultSet.getString("Name"), resultSet.getDouble("star"));
							addPanel();
						}
					} else if (EastenFoodBtn.isSelected()) {
						query1 += "AND Style = '�覡' ";
						ResultSet resultSet = statement.executeQuery(query1);
						while (resultSet.next()) {
							Choosing(resultSet.getString("Name"), resultSet.getDouble("star"));
							addPanel();
						}
					} else if (drinksBtn.isSelected()) {
						query1 += "AND Style = '���\/�I��/����' ";
						ResultSet resultSet = statement.executeQuery(query1);
						while (resultSet.next()) {
							Choosing(resultSet.getString("Name"), resultSet.getDouble("star"));
							addPanel();
						}
					}
				}
				// 50-100
				else if (priceBox.getSelectedItem().equals("50~100")) {
					query1 += "AND `50-100` = 1 ";
					if (taiwanFoodBtn.isSelected()) {
						query1+= "AND Style = '�x/��/�䦡' ";
						ResultSet resultSet = statement.executeQuery(query1);
						while (resultSet.next()) {
							Choosing(resultSet.getString("Name"), resultSet.getDouble("star"));
							addPanel();
						}
					} else if (JapanKoreanFoodBtn.isSelected()) {
						query1 += "AND Style = '��/��/�F�n�Ȧ�' ";
						ResultSet resultSet = statement.executeQuery(query1);
						while (resultSet.next()) {
							Choosing(resultSet.getString("Name"), resultSet.getDouble("star"));
							addPanel();
						}
					} else if (EastenFoodBtn.isSelected()) {
						query1 += "AND Style = '�覡' ";
						ResultSet resultSet = statement.executeQuery(query1);
						while (resultSet.next()) {
							Choosing(resultSet.getString("Name"), resultSet.getDouble("star"));
							addPanel();
						}
					} else if (drinksBtn.isSelected()) {
						query1 += "AND Style = '���\/�I��/����' ";
						ResultSet resultSet = statement.executeQuery(query1);
						while (resultSet.next()) {
							Choosing(resultSet.getString("Name"), resultSet.getDouble("star"));
							addPanel();
						}
					}
				}
				// 100-150
				else if (priceBox.getSelectedItem().equals("100~150")) {
					query1 += "AND `100-150` = 1 ";
					if (taiwanFoodBtn.isSelected()) {
						query1 += "AND Style = '�x/��/�䦡' ";
						ResultSet resultSet = statement.executeQuery(query1);
						while (resultSet.next()) {
							Choosing(resultSet.getString("Name"), resultSet.getDouble("star"));
							addPanel();
						}
					} else if (JapanKoreanFoodBtn.isSelected()) {
						query1 += "AND Style = '��/��/�F�n�Ȧ�' ";
						ResultSet resultSet = statement.executeQuery(query1);
						while (resultSet.next()) {
							Choosing(resultSet.getString("Name"), resultSet.getDouble("star"));
							addPanel();
						}
					} else if (EastenFoodBtn.isSelected()) {
						query1 += "AND Style = '�覡' ";
						ResultSet resultSet = statement.executeQuery(query1);
						while (resultSet.next()) {
							Choosing(resultSet.getString("Name"), resultSet.getDouble("star"));
							addPanel();
						}
					} else if (drinksBtn.isSelected()) {
						query1 += "AND Style = '���\/�I��/����' ";
						ResultSet resultSet = statement.executeQuery(query1);
						while (resultSet.next()) {
							Choosing(resultSet.getString("Name"), resultSet.getDouble("star"));
							addPanel();
						}
					}
				}
				// 150-200
				else if (priceBox.getSelectedItem().equals("150~200")) {
					query1 += "AND `150-200` = 1 ";
					if (taiwanFoodBtn.isSelected()) {
						query1 += "AND Style = '�x/��/�䦡' ";
						ResultSet resultSet = statement.executeQuery(query1);
						while (resultSet.next()) {
							Choosing(resultSet.getString("Name"), resultSet.getDouble("star"));
							addPanel();
						}
					} else if (JapanKoreanFoodBtn.isSelected()) {
						query1 += "AND Style = '��/��/�F�n�Ȧ�' ";
						ResultSet resultSet = statement.executeQuery(query1);
						while (resultSet.next()) {
							Choosing(resultSet.getString("Name"), resultSet.getDouble("star"));
							addPanel();
						}
					} else if (EastenFoodBtn.isSelected()) {
						query1 += "AND Style = '�覡' ";
						ResultSet resultSet = statement.executeQuery(query1);
						while (resultSet.next()) {
							Choosing(resultSet.getString("Name"), resultSet.getDouble("star"));
							addPanel();
						}
					} else if (drinksBtn.isSelected()) {
						query1 += "AND Style = '���\/�I��/����' ";
						ResultSet resultSet = statement.executeQuery(query1);
						while (resultSet.next()) {
							Choosing(resultSet.getString("Name"), resultSet.getDouble("star"));
							addPanel();
						}
					}
				}
				// 200-250
				else if (priceBox.getSelectedItem().equals("200~250")) {
					query1 += "AND `200-250` = 1 ";
					if (taiwanFoodBtn.isSelected()) {
						query1 += "AND Style = '�x/��/�䦡' ";
						ResultSet resultSet = statement.executeQuery(query1);
						while (resultSet.next()) {
							Choosing(resultSet.getString("Name"), resultSet.getDouble("star"));
							addPanel();
						}
					} else if (JapanKoreanFoodBtn.isSelected()) {
						query1 += "AND Style = '��/��/�F�n�Ȧ�' ";
						ResultSet resultSet = statement.executeQuery(query1);
						while (resultSet.next()) {
							Choosing(resultSet.getString("Name"), resultSet.getDouble("star"));
							addPanel();
						}
					} else if (EastenFoodBtn.isSelected()) {
						query1 += "AND Style = '�覡' ";
						ResultSet resultSet = statement.executeQuery(query1);
						while (resultSet.next()) {
							Choosing(resultSet.getString("Name"), resultSet.getDouble("star"));
							addPanel();
						}
					} else if (drinksBtn.isSelected()) {
						query1 += "AND Style = '���\/�I��/����' ";
						ResultSet resultSet = statement.executeQuery(query1);
						while (resultSet.next()) {
							Choosing(resultSet.getString("Name"), resultSet.getDouble("star"));
							addPanel();
						}
					}
				}
				// 250-300
				else if (priceBox.getSelectedItem().equals("250~300")) {
					query1 += "AND `250-300` = 1 ";
					if (taiwanFoodBtn.isSelected()) {
						query1 += "AND Style = '�x/��/�䦡' ";
						ResultSet resultSet = statement.executeQuery(query1);
						while (resultSet.next()) {
							Choosing(resultSet.getString("Name"), resultSet.getDouble("star"));
							addPanel();
						}
					} else if (JapanKoreanFoodBtn.isSelected()) {
						query1 += "AND Style = '��/��/�F�n�Ȧ�' ";
						ResultSet resultSet = statement.executeQuery(query1);
						while (resultSet.next()) {
							Choosing(resultSet.getString("Name"), resultSet.getDouble("star"));
							addPanel();
						}
					} else if (EastenFoodBtn.isSelected()) {
						query1 += "AND Style = '�覡' ";
						ResultSet resultSet = statement.executeQuery(query1);
						while (resultSet.next()) {
							Choosing(resultSet.getString("Name"), resultSet.getDouble("star"));
							addPanel();
						}
					} else if (drinksBtn.isSelected()) {
						query1 += "AND Style = '���\/�I��/����' ";
						ResultSet resultSet = statement.executeQuery(query1);
						while (resultSet.next()) {
							Choosing(resultSet.getString("Name"), resultSet.getDouble("star"));
							addPanel();
						}
					}
				}
				// 300-350
				else if (priceBox.getSelectedItem().equals("300~350")) {
					query1 += "AND `300-350` = 1 ";
					if (taiwanFoodBtn.isSelected()) {
						query1 += "AND Style = '�x/��/�䦡' ";
						ResultSet resultSet = statement.executeQuery(query1);
						while (resultSet.next()) {
							Choosing(resultSet.getString("Name"), resultSet.getDouble("star"));
							addPanel();
						}
					} else if (JapanKoreanFoodBtn.isSelected()) {
						query1 += "AND Style = '��/��/�F�n�Ȧ�' ";
						ResultSet resultSet = statement.executeQuery(query1);
						while (resultSet.next()) {
							Choosing(resultSet.getString("Name"), resultSet.getDouble("star"));
							addPanel();
						}
					} else if (EastenFoodBtn.isSelected()) {
						query1 += "AND Style = '�覡' ";
						ResultSet resultSet = statement.executeQuery(query1);
						while (resultSet.next()) {
							Choosing(resultSet.getString("Name"), resultSet.getDouble("star"));
							addPanel();
						}
					} else if (drinksBtn.isSelected()) {
						query1 += "AND Style = '���\/�I��/����' ";
						ResultSet resultSet = statement.executeQuery(query1);
						while (resultSet.next()) {
							Choosing(resultSet.getString("Name"), resultSet.getDouble("star"));
							addPanel();
						}
					}
				}
				// 350-400
				else if (priceBox.getSelectedItem().equals("350~400")) {
					query1 += "AND `350-400` = 1 ";
					if (taiwanFoodBtn.isSelected()) {
						query1 += "AND Style = '�x/��/�䦡' ";
						ResultSet resultSet = statement.executeQuery(query1);
						while (resultSet.next()) {
							Choosing(resultSet.getString("Name"), resultSet.getDouble("star"));
							addPanel();
						}
					} else if (JapanKoreanFoodBtn.isSelected()) {
						query1 += "AND Style = '��/��/�F�n�Ȧ�' ";
						ResultSet resultSet = statement.executeQuery(query1);
						while (resultSet.next()) {
							Choosing(resultSet.getString("Name"), resultSet.getDouble("star"));
							addPanel();
						}
					} else if (EastenFoodBtn.isSelected()) {
						query1 += "AND Style = '�覡' ";
						ResultSet resultSet = statement.executeQuery(query1);
						while (resultSet.next()) {
							Choosing(resultSet.getString("Name"), resultSet.getDouble("star"));
							addPanel();
						}
					} else if (drinksBtn.isSelected()) {
						query1 += "AND Style = '���\/�I��/����' ";
						ResultSet resultSet = statement.executeQuery(query1);
						while (resultSet.next()) {
							Choosing(resultSet.getString("Name"), resultSet.getDouble("star"));
							addPanel();
						}
					}
				}
				// 400up
				else if (priceBox.getSelectedItem().equals("400�H�W")) {
					query1 += "AND `above400` = 1 ";
					if (taiwanFoodBtn.isSelected()) {
						query1 += "AND Style = '�x/��/�䦡' ";
						ResultSet resultSet = statement.executeQuery(query1);
						while (resultSet.next()) {
							Choosing(resultSet.getString("Name"), resultSet.getDouble("star"));
							addPanel();
						}
					} else if (JapanKoreanFoodBtn.isSelected()) {
						query1 += "AND Style = '��/��/�F�n�Ȧ�' ";
						ResultSet resultSet = statement.executeQuery(query1);
						while (resultSet.next()) {
							Choosing(resultSet.getString("Name"), resultSet.getDouble("star"));
							addPanel();
						}
					} else if (EastenFoodBtn.isSelected()) {
						query1 += "AND Style = '�覡' ";
						ResultSet resultSet = statement.executeQuery(query1);
						while (resultSet.next()) {
							Choosing(resultSet.getString("Name"), resultSet.getDouble("star"));
							addPanel();
						}
					} else if (drinksBtn.isSelected()) {
						query1 += "AND Style = '���\/�I��/����' ";
						ResultSet resultSet = statement.executeQuery(query1);
						while (resultSet.next()) {
							Choosing(resultSet.getString("Name"), resultSet.getDouble("star"));
							addPanel();
						}
					}
				}

			}
			if (mcdBtn.isSelected()) {
				query1 += "AND Place = '����'";
				if (priceBox.getSelectedItem().equals("50�H�U")) {
					query1 += "AND under50 = 1 ";
					if (taiwanFoodBtn.isSelected()) {
						query1+= "AND Style = '�x/��/�䦡' ";
						ResultSet resultSet = statement.executeQuery(query1);
						while (resultSet.next()) {
							Choosing(resultSet.getString("Name"), resultSet.getDouble("star"));
							addPanel();
						}
						
					} else if (JapanKoreanFoodBtn.isSelected()) {
						query1 += "AND Style = '��/��/�F�n�Ȧ�' ";
						ResultSet resultSet = statement.executeQuery(query1);
						while (resultSet.next()) {
							Choosing(resultSet.getString("Name"), resultSet.getDouble("star"));
							addPanel();
						}
					} else if (EastenFoodBtn.isSelected()) {
						query1 += "AND Style = '�覡' ";
						ResultSet resultSet = statement.executeQuery(query1);
						while (resultSet.next()) {
							Choosing(resultSet.getString("Name"), resultSet.getDouble("star"));
							addPanel();
						}
					} else if (drinksBtn.isSelected()) {
						query1 += "AND Style = '���\/�I��/����' ";
						ResultSet resultSet = statement.executeQuery(query1);
						while (resultSet.next()) {
							Choosing(resultSet.getString("Name"), resultSet.getDouble("star"));
							addPanel();
						}
					}
				}
				// 50-100
				else if (priceBox.getSelectedItem().equals("50~100")) {
					query1 += "AND `50-100` = 1 ";
					if (taiwanFoodBtn.isSelected()) {
						query1 += "AND Style = '�x/��/�䦡' ";
						ResultSet resultSet = statement.executeQuery(query1);
						while (resultSet.next()) {
							System.out.println("�쩳���S���i��");
							Choosing(resultSet.getString("Name"), resultSet.getDouble("star"));
						}
					} else if (JapanKoreanFoodBtn.isSelected()) {
						query1 += "AND Style = '��/��/�F�n�Ȧ�' ";
						ResultSet resultSet = statement.executeQuery(query1);
						while (resultSet.next()) {
							Choosing(resultSet.getString("Name"), resultSet.getDouble("star"));
							addPanel();
						}
					} else if (EastenFoodBtn.isSelected()) {
						query1 += "AND Style = '�覡' ";
						ResultSet resultSet = statement.executeQuery(query1);
						while (resultSet.next()) {
							Choosing(resultSet.getString("Name"), resultSet.getDouble("star"));
							addPanel();
						}
					} else if (drinksBtn.isSelected()) {
						query1 += "AND Style = '���\/�I��/����' ";
						ResultSet resultSet = statement.executeQuery(query1);
						while (resultSet.next()) {
							Choosing(resultSet.getString("Name"), resultSet.getDouble("star"));
							addPanel();
						}
					}
				}
				// 100-150
				else if (priceBox.getSelectedItem().equals("100~150")) {
					query1 += "AND `100-150` = 1 ";
					if (taiwanFoodBtn.isSelected()) {
						query1 += "AND Style = '�x/��/�䦡' ";
						ResultSet resultSet = statement.executeQuery(query1);
						while (resultSet.next()) {
							Choosing(resultSet.getString("Name"), resultSet.getDouble("star"));
							addPanel();
						}
					} else if (JapanKoreanFoodBtn.isSelected()) {
						query1 += "AND Style = '��/��/�F�n�Ȧ�' ";
						ResultSet resultSet = statement.executeQuery(query1);
						while (resultSet.next()) {
							Choosing(resultSet.getString("Name"), resultSet.getDouble("star"));
							addPanel();
						}
					} else if (EastenFoodBtn.isSelected()) {
						query1 += "AND Style = '�覡' ";
						ResultSet resultSet = statement.executeQuery(query1);
						while (resultSet.next()) {
							Choosing(resultSet.getString("Name"), resultSet.getDouble("star"));
							addPanel();
						}
					} else if (drinksBtn.isSelected()) {
						query1 += "AND Style = '���\/�I��/����' ";
						ResultSet resultSet = statement.executeQuery(query1);
						while (resultSet.next()) {
							Choosing(resultSet.getString("Name"), resultSet.getDouble("star"));
							addPanel();
						}
					}
				}
				// 150-200
				else if (priceBox.getSelectedItem().equals("150~200")) {
					query1 += "AND `150-200` = 1 ";
					if (taiwanFoodBtn.isSelected()) {
						query1 += "AND Style = '�x/��/�䦡' ";
						ResultSet resultSet = statement.executeQuery(query1);
						while (resultSet.next()) {
							Choosing(resultSet.getString("Name"), resultSet.getDouble("star"));
							addPanel();
						}
					} else if (JapanKoreanFoodBtn.isSelected()) {
						query1 += "AND Style = '��/��/�F�n�Ȧ�' ";
						ResultSet resultSet = statement.executeQuery(query1);
						while (resultSet.next()) {
							Choosing(resultSet.getString("Name"), resultSet.getDouble("star"));
							addPanel();
						}
					} else if (EastenFoodBtn.isSelected()) {
						query1 += "AND Style = '�覡' ";
						ResultSet resultSet = statement.executeQuery(query1);
						while (resultSet.next()) {
							Choosing(resultSet.getString("Name"), resultSet.getDouble("star"));
							addPanel();
						}
					} else if (drinksBtn.isSelected()) {
						query1 += "AND Style = '���\/�I��/����' ";
						ResultSet resultSet = statement.executeQuery(query1);
						while (resultSet.next()) {
							Choosing(resultSet.getString("Name"), resultSet.getDouble("star"));
							addPanel();
						}
					}
				}
				// 200-250
				else if (priceBox.getSelectedItem().equals("200~250")) {
					query1 += "AND `200-250` = 1 ";
					if (taiwanFoodBtn.isSelected()) {
						query1 += "AND Style = '�x/��/�䦡' ";
						ResultSet resultSet = statement.executeQuery(query1);
						while (resultSet.next()) {
							Choosing(resultSet.getString("Name"), resultSet.getDouble("star"));
							addPanel();
						}
					} else if (JapanKoreanFoodBtn.isSelected()) {
						query1 += "AND Style = '��/��/�F�n�Ȧ�' ";
						ResultSet resultSet = statement.executeQuery(query1);
						while (resultSet.next()) {
							Choosing(resultSet.getString("Name"), resultSet.getDouble("star"));
							addPanel();
						}
					} else if (EastenFoodBtn.isSelected()) {
						query1 += "AND Style = '�覡' ";
						ResultSet resultSet = statement.executeQuery(query1);
						while (resultSet.next()) {
							Choosing(resultSet.getString("Name"), resultSet.getDouble("star"));
							addPanel();
						}
					} else if (drinksBtn.isSelected()) {
						query1 += "AND Style = '���\/�I��/����' ";
						ResultSet resultSet = statement.executeQuery(query1);
						while (resultSet.next()) {
							Choosing(resultSet.getString("Name"), resultSet.getDouble("star"));
							addPanel();
						}
					}
				}
				// 250-300
				else if (priceBox.getSelectedItem().equals("250~300")) {
					query1 += "AND `250-300` = 1 ";
					if (taiwanFoodBtn.isSelected()) {
						query1 += "AND Style = '�x/��/�䦡' ";
						ResultSet resultSet = statement.executeQuery(query1);
						while (resultSet.next()) {
							Choosing(resultSet.getString("Name"), resultSet.getDouble("star"));
							addPanel();
						}
					} else if (JapanKoreanFoodBtn.isSelected()) {
						query1 += "AND Style = '��/��/�F�n�Ȧ�' ";
						ResultSet resultSet = statement.executeQuery(query1);
						while (resultSet.next()) {
							Choosing(resultSet.getString("Name"), resultSet.getDouble("star"));
							addPanel();
						}
					} else if (EastenFoodBtn.isSelected()) {
						query1 += "AND Style = '�覡' ";
						ResultSet resultSet = statement.executeQuery(query1);
						while (resultSet.next()) {
							Choosing(resultSet.getString("Name"), resultSet.getDouble("star"));
							addPanel();
						}
					} else if (drinksBtn.isSelected()) {
						query1 += "AND Style = '���\/�I��/����' ";
						ResultSet resultSet = statement.executeQuery(query1);
						while (resultSet.next()) {
							Choosing(resultSet.getString("Name"), resultSet.getDouble("star"));
							addPanel();
						}
					}
				}
				// 300-350
				else if (priceBox.getSelectedItem().equals("300~350")) {
					query1 += "AND `300-350` = 1 ";
					if (taiwanFoodBtn.isSelected()) {
						query1 += "AND Style = '�x/��/�䦡' ";
						ResultSet resultSet = statement.executeQuery(query1);
						while (resultSet.next()) {
							Choosing(resultSet.getString("Name"), resultSet.getDouble("star"));
							addPanel();
						}
					} else if (JapanKoreanFoodBtn.isSelected()) {
						query1 += "AND Style = '��/��/�F�n�Ȧ�' ";
						ResultSet resultSet = statement.executeQuery(query1);
						while (resultSet.next()) {
							Choosing(resultSet.getString("Name"), resultSet.getDouble("star"));
							addPanel();
						}
					} else if (EastenFoodBtn.isSelected()) {
						query1 += "AND Style = '�覡' ";
						ResultSet resultSet = statement.executeQuery(query1);
						while (resultSet.next()) {
							Choosing(resultSet.getString("Name"), resultSet.getDouble("star"));
							addPanel();
						}
					} else if (drinksBtn.isSelected()) {
						query1 += "AND Style = '���\/�I��/����' ";
						ResultSet resultSet = statement.executeQuery(query1);
						while (resultSet.next()) {
							Choosing(resultSet.getString("Name"), resultSet.getDouble("star"));
							addPanel();
						}
					}
				}
				// 350-400
				else if (priceBox.getSelectedItem().equals("350~400")) {
					query1 += "AND `350-400` = 1 ";
					if (taiwanFoodBtn.isSelected()) {
						query1 += "AND Style = '�x/��/�䦡' ";
						ResultSet resultSet = statement.executeQuery(query1);
						while (resultSet.next()) {
							Choosing(resultSet.getString("Name"), resultSet.getDouble("star"));
							addPanel();
						}
					} else if (JapanKoreanFoodBtn.isSelected()) {
						query1 += "AND Style = '��/��/�F�n�Ȧ�' ";
						ResultSet resultSet = statement.executeQuery(query1);
						while (resultSet.next()) {
							Choosing(resultSet.getString("Name"), resultSet.getDouble("star"));
							addPanel();
						}
					} else if (EastenFoodBtn.isSelected()) {
						query1 += "AND Style = '�覡' ";
						ResultSet resultSet = statement.executeQuery(query1);
						while (resultSet.next()) {
							Choosing(resultSet.getString("Name"), resultSet.getDouble("star"));
							addPanel();
						}
					} else if (drinksBtn.isSelected()) {
						query1 += "AND Style = '���\/�I��/����' ";
						ResultSet resultSet = statement.executeQuery(query1);
						while (resultSet.next()) {
							Choosing(resultSet.getString("Name"), resultSet.getDouble("star"));
							addPanel();
						}
					}
				}
				// 400up
				else if (priceBox.getSelectedItem().equals("400�H�W")) {
					query1 += "AND `above400` = 1 ";
					if (taiwanFoodBtn.isSelected()) {
						query1 += "AND Style = '�x/��/�䦡' ";
						ResultSet resultSet = statement.executeQuery(query1);
						while (resultSet.next()) {
							Choosing(resultSet.getString("Name"), resultSet.getDouble("star"));
							addPanel();
						}
					} else if (JapanKoreanFoodBtn.isSelected()) {
						query1 += "AND Style = '��/��/�F�n�Ȧ�' ";
						ResultSet resultSet = statement.executeQuery(query1);
						while (resultSet.next()) {
							Choosing(resultSet.getString("Name"), resultSet.getDouble("star"));
							addPanel();
						}
					} else if (EastenFoodBtn.isSelected()) {
						query1 += "AND Style = '�覡' ";
						ResultSet resultSet = statement.executeQuery(query1);
						while (resultSet.next()) {
							Choosing(resultSet.getString("Name"), resultSet.getDouble("star"));
							addPanel();
						}
					} else if (drinksBtn.isSelected()) {
						query1 += "AND Style = '���\/�I��/����' ";
						ResultSet resultSet = statement.executeQuery(query1);
						while (resultSet.next()) {
							Choosing(resultSet.getString("Name"), resultSet.getDouble("star"));
							addPanel();
						}
					}
				}

			}
			if (stoneBtn.isSelected()) {
				query1 += "AND Place = '�ոO'";
				if (priceBox.getSelectedItem().equals("50�H�U")) {
					query1 += "AND under50 = 1 ";
					if (taiwanFoodBtn.isSelected()) {
						query1 += "AND Style = '�x/��/�䦡' ";
						ResultSet resultSet = statement.executeQuery(query1);
						while (resultSet.next()) {
							Choosing(resultSet.getString("Name"), resultSet.getDouble("star"));
							addPanel();
						}
					} else if (JapanKoreanFoodBtn.isSelected()) {
						query1 += "AND Style = '��/��/�F�n�Ȧ�' ";
						ResultSet resultSet = statement.executeQuery(query1);
						while (resultSet.next()) {
							Choosing(resultSet.getString("Name"), resultSet.getDouble("star"));
							addPanel();
						}
					} else if (EastenFoodBtn.isSelected()) {
						query1 += "AND Style = '�覡' ";
						ResultSet resultSet = statement.executeQuery(query1);
						while (resultSet.next()) {
							Choosing(resultSet.getString("Name"), resultSet.getDouble("star"));
							addPanel();
						}
					} else if (drinksBtn.isSelected()) {
						query1 += "AND Style = '���\/�I��/����' ";
						ResultSet resultSet = statement.executeQuery(query1);
						while (resultSet.next()) {
							Choosing(resultSet.getString("Name"), resultSet.getDouble("star"));
							addPanel();
						}
					}
				}
				// 50-100
				else if (priceBox.getSelectedItem().equals("50~100")) {
					query1 += "AND `50-100` = 1 ";
					if (taiwanFoodBtn.isSelected()) {
						query1 += "AND Style = '�x/��/�䦡' ";
						ResultSet resultSet = statement.executeQuery(query1);
						while (resultSet.next()) {
							Choosing(resultSet.getString("Name"), resultSet.getDouble("star"));
							addPanel();
						}
					} else if (JapanKoreanFoodBtn.isSelected()) {
						query1 += "AND Style = '��/��/�F�n�Ȧ�' ";
						ResultSet resultSet = statement.executeQuery(query1);
						while (resultSet.next()) {
							Choosing(resultSet.getString("Name"), resultSet.getDouble("star"));
							addPanel();
						}
					} else if (EastenFoodBtn.isSelected()) {
						query1 += "AND Style = '�覡' ";
						ResultSet resultSet = statement.executeQuery(query1);
						while (resultSet.next()) {
							Choosing(resultSet.getString("Name"), resultSet.getDouble("star"));
							addPanel();
						}
					} else if (drinksBtn.isSelected()) {
						query1 += "AND Style = '���\/�I��/����' ";
						ResultSet resultSet = statement.executeQuery(query1);
						while (resultSet.next()) {
							Choosing(resultSet.getString("Name"), resultSet.getDouble("star"));
							addPanel();
						}
					}
				}
				// 100-150
				else if (priceBox.getSelectedItem().equals("100~150")) {
					query1 += "AND `100-150` = 1 ";
					if (taiwanFoodBtn.isSelected()) {
						query1 += "AND Style = '�x/��/�䦡' ";
						ResultSet resultSet = statement.executeQuery(query1);
						while (resultSet.next()) {
							Choosing(resultSet.getString("Name"), resultSet.getDouble("star"));
							addPanel();
						}
					} else if (JapanKoreanFoodBtn.isSelected()) {
						query1 += "AND Style = '��/��/�F�n�Ȧ�' ";
						ResultSet resultSet = statement.executeQuery(query1);
						while (resultSet.next()) {
							Choosing(resultSet.getString("Name"), resultSet.getDouble("star"));
							addPanel();
						}
					} else if (EastenFoodBtn.isSelected()) {
						query1 += "AND Style = '�覡' ";
						ResultSet resultSet = statement.executeQuery(query1);
						while (resultSet.next()) {
							Choosing(resultSet.getString("Name"), resultSet.getDouble("star"));
							addPanel();
						}
					} else if (drinksBtn.isSelected()) {
						query1 += "AND Style = '���\/�I��/����' ";
						ResultSet resultSet = statement.executeQuery(query1);
						while (resultSet.next()) {
							Choosing(resultSet.getString("Name"), resultSet.getDouble("star"));
							addPanel();
						}
					}
				}
				// 150-200
				else if (priceBox.getSelectedItem().equals("150~200")) {
					query1 += "AND `150-200` = 1 ";
					if (taiwanFoodBtn.isSelected()) {
						query1 += "AND Style = '�x/��/�䦡' ";
						ResultSet resultSet = statement.executeQuery(query1);
						while (resultSet.next()) {
							Choosing(resultSet.getString("Name"), resultSet.getDouble("star"));
							addPanel();
						}
					} else if (JapanKoreanFoodBtn.isSelected()) {
						query1 += "AND Style = '��/��/�F�n�Ȧ�' ";
						ResultSet resultSet = statement.executeQuery(query1);
						while (resultSet.next()) {
							Choosing(resultSet.getString("Name"), resultSet.getDouble("star"));
							addPanel();
						}
					} else if (EastenFoodBtn.isSelected()) {
						query1 += "AND Style = '�覡' ";
						ResultSet resultSet = statement.executeQuery(query1);
						while (resultSet.next()) {
							Choosing(resultSet.getString("Name"), resultSet.getDouble("star"));
							addPanel();
						}
					} else if (drinksBtn.isSelected()) {
						query1 += "AND Style = '���\/�I��/����' ";
						ResultSet resultSet = statement.executeQuery(query1);
						while (resultSet.next()) {
							Choosing(resultSet.getString("Name"), resultSet.getDouble("star"));
							addPanel();
						}
					}
				}
				// 200-250
				else if (priceBox.getSelectedItem().equals("200~250")) {
					query1 += "AND `200-250` = 1 ";
					if (taiwanFoodBtn.isSelected()) {
						query1 += "AND Style = '�x/��/�䦡' ";
						ResultSet resultSet = statement.executeQuery(query1);
						while (resultSet.next()) {
							Choosing(resultSet.getString("Name"), resultSet.getDouble("star"));
							addPanel();
						}
					} else if (JapanKoreanFoodBtn.isSelected()) {
						query1 += "AND Style = '��/��/�F�n�Ȧ�' ";
						ResultSet resultSet = statement.executeQuery(query1);
						while (resultSet.next()) {
							Choosing(resultSet.getString("Name"), resultSet.getDouble("star"));
							addPanel();
						}
					} else if (EastenFoodBtn.isSelected()) {
						query1+= "AND Style = '�覡' ";
						ResultSet resultSet = statement.executeQuery(query1);
						while (resultSet.next()) {
							Choosing(resultSet.getString("Name"), resultSet.getDouble("star"));
							addPanel();
						}
					} else if (drinksBtn.isSelected()) {
						query1+= "AND Style = '���\/�I��/����' ";
						ResultSet resultSet = statement.executeQuery(query1);
						while (resultSet.next()) {
							Choosing(resultSet.getString("Name"), resultSet.getDouble("star"));
							addPanel();
						}
					}
				}
				// 250-300
				else if (priceBox.getSelectedItem().equals("250~300")) {
					query1 += "AND `250-300` = 1 ";
					if (taiwanFoodBtn.isSelected()) {
						query1 += "AND Style = '�x/��/�䦡' ";
						ResultSet resultSet = statement.executeQuery(query1);
						while (resultSet.next()) {
							Choosing(resultSet.getString("Name"), resultSet.getDouble("star"));
							addPanel();
						}
					} else if (JapanKoreanFoodBtn.isSelected()) {
						query1 += "AND Style = '��/��/�F�n�Ȧ�' ";
						ResultSet resultSet = statement.executeQuery(query1);
						while (resultSet.next()) {
							Choosing(resultSet.getString("Name"), resultSet.getDouble("star"));
							addPanel();
						}
					} else if (EastenFoodBtn.isSelected()) {
						query1 += "AND Style = '�覡' ";
						ResultSet resultSet = statement.executeQuery(query1);
						while (resultSet.next()) {
							Choosing(resultSet.getString("Name"), resultSet.getDouble("star"));
							addPanel();
						}
					} else if (drinksBtn.isSelected()) {
						query1 += "AND Style = '���\/�I��/����' ";
						ResultSet resultSet = statement.executeQuery(query1);
						while (resultSet.next()) {
							Choosing(resultSet.getString("Name"), resultSet.getDouble("star"));
							addPanel();
						}
					}
				}
				// 300-350
				else if (priceBox.getSelectedItem().equals("300~350")) {
					query1 += "AND `300-350` = 1 ";
					if (taiwanFoodBtn.isSelected()) {
						query1 += "AND Style = '�x/��/�䦡' ";
						ResultSet resultSet = statement.executeQuery(query1);
						while (resultSet.next()) {
							Choosing(resultSet.getString("Name"), resultSet.getDouble("star"));
							addPanel();
						}
					} else if (JapanKoreanFoodBtn.isSelected()) {
						query1 += "AND Style = '��/��/�F�n�Ȧ�' ";
						ResultSet resultSet = statement.executeQuery(query1);
						while (resultSet.next()) {
							Choosing(resultSet.getString("Name"), resultSet.getDouble("star"));
							addPanel();
						}
					} else if (EastenFoodBtn.isSelected()) {
						query1 += "AND Style = '�覡' ";
						ResultSet resultSet = statement.executeQuery(query1);
						while (resultSet.next()) {
							Choosing(resultSet.getString("Name"), resultSet.getDouble("star"));
							addPanel();
						}
					} else if (drinksBtn.isSelected()) {
						query1 += "AND Style = '���\/�I��/����' ";
						ResultSet resultSet = statement.executeQuery(query1);
						while (resultSet.next()) {
							Choosing(resultSet.getString("Name"), resultSet.getDouble("star"));
							addPanel();
						}
					}
				}
				// 350-400
				else if (priceBox.getSelectedItem().equals("350~400")) {
					query1 += "AND `350-400` = 1 ";
					if (taiwanFoodBtn.isSelected()) {
						query1 += "AND Style = '�x/��/�䦡' ";
						ResultSet resultSet = statement.executeQuery(query1);
						while (resultSet.next()) {
							Choosing(resultSet.getString("Name"), resultSet.getDouble("star"));
							addPanel();
						}
					} else if (JapanKoreanFoodBtn.isSelected()) {
						query1 += "AND Style = '��/��/�F�n�Ȧ�' ";
						ResultSet resultSet = statement.executeQuery(query1);
						while (resultSet.next()) {
							Choosing(resultSet.getString("Name"), resultSet.getDouble("star"));
							addPanel();
						}
					} else if (EastenFoodBtn.isSelected()) {
						query1 += "AND Style = '�覡' ";
						ResultSet resultSet = statement.executeQuery(query1);
						while (resultSet.next()) {
							Choosing(resultSet.getString("Name"), resultSet.getDouble("star"));
							addPanel();
						}
					} else if (drinksBtn.isSelected()) {
						query1 += "AND Style = '���\/�I��/����' ";
						ResultSet resultSet = statement.executeQuery(query1);
						while (resultSet.next()) {
							Choosing(resultSet.getString("Name"), resultSet.getDouble("star"));
							addPanel();
						}
					}
				}
				// 400up
				else if (priceBox.getSelectedItem().equals("400�H�W")) {
					query1 += "AND `above400` = 1 ";
					if (taiwanFoodBtn.isSelected()) {
						query1 += "AND Style = '�x/��/�䦡' ";
						ResultSet resultSet = statement.executeQuery(query1);
						while (resultSet.next()) {
							Choosing(resultSet.getString("Name"), resultSet.getDouble("star"));
							addPanel();
						}
					} else if (JapanKoreanFoodBtn.isSelected()) {
						query1 += "AND Style = '��/��/�F�n�Ȧ�' ";
						ResultSet resultSet = statement.executeQuery(query1);
						while (resultSet.next()) {
							Choosing(resultSet.getString("Name"), resultSet.getDouble("star"));
							addPanel();
						}
					} else if (EastenFoodBtn.isSelected()) {
						query1 += "AND Style = '�覡' ";
						ResultSet resultSet = statement.executeQuery(query1);
						while (resultSet.next()) {
							Choosing(resultSet.getString("Name"), resultSet.getDouble("star"));
							addPanel();
						}
					} else if (drinksBtn.isSelected()) {
						query1+= "AND Style = '���\/�I��/����' ";
						ResultSet resultSet = statement.executeQuery(query1);
						while (resultSet.next()) {
							Choosing(resultSet.getString("Name"), resultSet.getDouble("star"));
							addPanel();
						}
					}
				}
				connection.close();
			}
			if (BuildingBtn.isSelected()) {
				query1 += "AND Place = '�ͽ��'";
				if (priceBox.getSelectedItem().equals("50�H�U")) {
					query1 += "AND under50 = 1 ";
					if (taiwanFoodBtn.isSelected()) {
						query1+= "AND Style = '�x/��/�䦡' ";
						ResultSet resultSet = statement.executeQuery(query1);
						while (resultSet.next()) {
							Choosing(resultSet.getString("Name"), resultSet.getDouble("star"));
							addPanel();
						}
					} else if (JapanKoreanFoodBtn.isSelected()) {
						query1 += "AND Style = '��/��/�F�n�Ȧ�' ";
						ResultSet resultSet = statement.executeQuery(query1);
						while (resultSet.next()) {
							Choosing(resultSet.getString("Name"), resultSet.getDouble("star"));
							addPanel();
						}
					} else if (EastenFoodBtn.isSelected()) {
						query1 += "AND Style = '�覡' ";
						ResultSet resultSet = statement.executeQuery(query1);
						while (resultSet.next()) {
							Choosing(resultSet.getString("Name"), resultSet.getDouble("star"));
							addPanel();
						}
					} else if (drinksBtn.isSelected()) {
						query1 += "AND Style = '���\/�I��/����' ";
						ResultSet resultSet = statement.executeQuery(query1);
						while (resultSet.next()) {
							Choosing(resultSet.getString("Name"), resultSet.getDouble("star"));
							addPanel();
						}
					}
				}
				// 50-100
				else if (priceBox.getSelectedItem().equals("50~100")) {
					query1 += "AND `50-100` = 1 ";
					if (taiwanFoodBtn.isSelected()) {
						query1+= "AND Style = '�x/��/�䦡' ";
						ResultSet resultSet = statement.executeQuery(query1);
						while (resultSet.next()) {
							Choosing(resultSet.getString("Name"), resultSet.getDouble("star"));
							addPanel();
						}
					} else if (JapanKoreanFoodBtn.isSelected()) {
						query1 += "AND Style = '��/��/�F�n�Ȧ�' ";
						ResultSet resultSet = statement.executeQuery(query1);
						while (resultSet.next()) {
							Choosing(resultSet.getString("Name"), resultSet.getDouble("star"));
							addPanel();
						}
					} else if (EastenFoodBtn.isSelected()) {
						query1 += "AND Style = '�覡' ";
						ResultSet resultSet = statement.executeQuery(query1);
						while (resultSet.next()) {
							Choosing(resultSet.getString("Name"), resultSet.getDouble("star"));
							addPanel();
						}
					} else if (drinksBtn.isSelected()) {
						query1 += "AND Style = '���\/�I��/����' ";
						ResultSet resultSet = statement.executeQuery(query1);
						while (resultSet.next()) {
							Choosing(resultSet.getString("Name"), resultSet.getDouble("star"));
							addPanel();
						}
					}
				}
				// 100-150
				else if (priceBox.getSelectedItem().equals("100~150")) {
					query1 += "AND `100-150` = 1 ";
					if (taiwanFoodBtn.isSelected()) {
						query1 += "AND Style = '�x/��/�䦡' ";
						ResultSet resultSet = statement.executeQuery(query1);
						while (resultSet.next()) {
							Choosing(resultSet.getString("Name"), resultSet.getDouble("star"));
							addPanel();
						}
					} else if (JapanKoreanFoodBtn.isSelected()) {
						query1 += "AND Style = '��/��/�F�n�Ȧ�' ";
						ResultSet resultSet = statement.executeQuery(query1);
						while (resultSet.next()) {
							Choosing(resultSet.getString("Name"), resultSet.getDouble("star"));
							addPanel();
						}
					} else if (EastenFoodBtn.isSelected()) {
						query1 += "AND Style = '�覡' ";
						ResultSet resultSet = statement.executeQuery(query1);
						while (resultSet.next()) {
							Choosing(resultSet.getString("Name"), resultSet.getDouble("star"));
							addPanel();
						}
					} else if (drinksBtn.isSelected()) {
						query1 += "AND Style = '���\/�I��/����' ";
						ResultSet resultSet = statement.executeQuery(query1);
						while (resultSet.next()) {
							Choosing(resultSet.getString("Name"), resultSet.getDouble("star"));
							addPanel();
						}
					}
				}
				// 150-200
				else if (priceBox.getSelectedItem().equals("150~200")) {
					query1 += "AND `150-200` = 1 ";
					if (taiwanFoodBtn.isSelected()) {
						query1 += "AND Style = '�x/��/�䦡' ";
						ResultSet resultSet = statement.executeQuery(query1);
						while (resultSet.next()) {
							Choosing(resultSet.getString("Name"), resultSet.getDouble("star"));
							addPanel();
						}
					} else if (JapanKoreanFoodBtn.isSelected()) {
						query1 += "AND Style = '��/��/�F�n�Ȧ�' ";
						ResultSet resultSet = statement.executeQuery(query1);
						while (resultSet.next()) {
							Choosing(resultSet.getString("Name"), resultSet.getDouble("star"));
							addPanel();
						}
					} else if (EastenFoodBtn.isSelected()) {
						query1 += "AND Style = '�覡' ";
						ResultSet resultSet = statement.executeQuery(query1);
						while (resultSet.next()) {
							Choosing(resultSet.getString("Name"), resultSet.getDouble("star"));
							addPanel();
						}
					} else if (drinksBtn.isSelected()) {
						query1 += "AND Style = '���\/�I��/����' ";
						ResultSet resultSet = statement.executeQuery(query1);
						while (resultSet.next()) {
							Choosing(resultSet.getString("Name"), resultSet.getDouble("star"));
							addPanel();
						}
					}
				}
				// 200-250
				else if (priceBox.getSelectedItem().equals("200~250")) {
					query1 += "AND `200-250` = 1 ";
					if (taiwanFoodBtn.isSelected()) {
						query1 += "AND Style = '�x/��/�䦡' ";
						ResultSet resultSet = statement.executeQuery(query1);
						while (resultSet.next()) {
							Choosing(resultSet.getString("Name"), resultSet.getDouble("star"));
							addPanel();
						}
					} else if (JapanKoreanFoodBtn.isSelected()) {
						query1 += "AND Style = '��/��/�F�n�Ȧ�' ";
						ResultSet resultSet = statement.executeQuery(query1);
						while (resultSet.next()) {
							Choosing(resultSet.getString("Name"), resultSet.getDouble("star"));
							addPanel();
						}
					} else if (EastenFoodBtn.isSelected()) {
						query1 += "AND Style = '�覡' ";
						ResultSet resultSet = statement.executeQuery(query1);
						while (resultSet.next()) {
							Choosing(resultSet.getString("Name"), resultSet.getDouble("star"));
							addPanel();
						}
					} else if (drinksBtn.isSelected()) {
						query1 += "AND Style = '���\/�I��/����' ";
						ResultSet resultSet = statement.executeQuery(query1);
						while (resultSet.next()) {
							Choosing(resultSet.getString("Name"), resultSet.getDouble("star"));
							addPanel();
						}
					}
				}
				// 250-300
				else if (priceBox.getSelectedItem().equals("250~300")) {
					query1 += "AND `250-300` = 1 ";
					if (taiwanFoodBtn.isSelected()) {
						query1 += "AND Style = '�x/��/�䦡' ";
						ResultSet resultSet = statement.executeQuery(query1);
						while (resultSet.next()) {
							Choosing(resultSet.getString("Name"), resultSet.getDouble("star"));
							addPanel();
						}
					} else if (JapanKoreanFoodBtn.isSelected()) {
						query1 += "AND Style = '��/��/�F�n�Ȧ�' ";
						ResultSet resultSet = statement.executeQuery(query1);
						while (resultSet.next()) {
							Choosing(resultSet.getString("Name"), resultSet.getDouble("star"));
							addPanel();
						}
					} else if (EastenFoodBtn.isSelected()) {
						query1 += "AND Style = '�覡' ";
						ResultSet resultSet = statement.executeQuery(query1);
						while (resultSet.next()) {
							Choosing(resultSet.getString("Name"), resultSet.getDouble("star"));
							addPanel();
						}
					} else if (drinksBtn.isSelected()) {
						query1 += "AND Style = '���\/�I��/����' ";
						ResultSet resultSet = statement.executeQuery(query1);
						while (resultSet.next()) {
							Choosing(resultSet.getString("Name"), resultSet.getDouble("star"));
							addPanel();
						}
					}
				}
				// 300-350
				else if (priceBox.getSelectedItem().equals("300~350")) {
					query1 += "AND `300-350` = 1 ";
					if (taiwanFoodBtn.isSelected()) {
						query1 += "AND Style = '�x/��/�䦡' ";
						ResultSet resultSet = statement.executeQuery(query1);
						while (resultSet.next()) {
							Choosing(resultSet.getString("Name"), resultSet.getDouble("star"));
							addPanel();
						}
					} else if (JapanKoreanFoodBtn.isSelected()) {
						query1 += "AND Style = '��/��/�F�n�Ȧ�' ";
						ResultSet resultSet = statement.executeQuery(query1);
						while (resultSet.next()) {
							Choosing(resultSet.getString("Name"), resultSet.getDouble("star"));
							addPanel();
						}
					} else if (EastenFoodBtn.isSelected()) {
						query1 += "AND Style = '�覡' ";
						ResultSet resultSet = statement.executeQuery(query1);
						while (resultSet.next()) {
							Choosing(resultSet.getString("Name"), resultSet.getDouble("star"));
							addPanel();
						}
					} else if (drinksBtn.isSelected()) {
						query1 += "AND Style = '���\/�I��/����' ";
						ResultSet resultSet = statement.executeQuery(query1);
						while (resultSet.next()) {
							Choosing(resultSet.getString("Name"), resultSet.getDouble("star"));
							addPanel();
						}
					}
				}
				// 350-400
				else if (priceBox.getSelectedItem().equals("350~400")) {
					query1 += "AND `350-400` = 1 ";
					if (taiwanFoodBtn.isSelected()) {
						query1 += "AND Style = '�x/��/�䦡' ";
						ResultSet resultSet = statement.executeQuery(query1);
						while (resultSet.next()) {
							Choosing(resultSet.getString("Name"), resultSet.getDouble("star"));
							addPanel();
						}
					} else if (JapanKoreanFoodBtn.isSelected()) {
						query1 += "AND Style = '��/��/�F�n�Ȧ�' ";
						ResultSet resultSet = statement.executeQuery(query1);
						while (resultSet.next()) {
							Choosing(resultSet.getString("Name"), resultSet.getDouble("star"));
							addPanel();
						}
					} else if (EastenFoodBtn.isSelected()) {
						query1 += "AND Style = '�覡' ";
						ResultSet resultSet = statement.executeQuery(query1);
						while (resultSet.next()) {
							Choosing(resultSet.getString("Name"), resultSet.getDouble("star"));
							addPanel();
						}
					} else if (drinksBtn.isSelected()) {
						query1 += "AND Style = '���\/�I��/����' ";
						ResultSet resultSet = statement.executeQuery(query1);
						while (resultSet.next()) {
							Choosing(resultSet.getString("Name"), resultSet.getDouble("star"));
							addPanel();
						}
					}
				}
				// 400up
				else if (priceBox.getSelectedItem().equals("400�H�W")) {
					query1 += "AND `above400` = 1 ";
					if (taiwanFoodBtn.isSelected()) {
						query1 += "AND Style = '�x/��/�䦡' ";
						ResultSet resultSet = statement.executeQuery(query1);
						while (resultSet.next()) {
							Choosing(resultSet.getString("Name"), resultSet.getDouble("star"));
							addPanel();
						}
					} else if (JapanKoreanFoodBtn.isSelected()) {
						query1 += "AND Style = '��/��/�F�n�Ȧ�' ";
						ResultSet resultSet = statement.executeQuery(query1);
						while (resultSet.next()) {
							Choosing(resultSet.getString("Name"), resultSet.getDouble("star"));
							addPanel();
						}
					} else if (EastenFoodBtn.isSelected()) {
						query1 += "AND Style = '�覡' ";
						ResultSet resultSet = statement.executeQuery(query1);
						while (resultSet.next()) {
							Choosing(resultSet.getString("Name"), resultSet.getDouble("star"));
							addPanel();
						}
					} else if (drinksBtn.isSelected()) {
						query1 += "AND Style = '���\/�I��/����' ";
						ResultSet resultSet = statement.executeQuery(query1);
						while (resultSet.next()) {
							Choosing(resultSet.getString("Name"), resultSet.getDouble("star"));
							addPanel();
						}
					}
				}
			}

		}
		// �[��
		else if (requireBox.getSelectedIndex()==1) {
			String query = "SELECT Name , star FROM totalinformation WHERE Form = '�[��'";
			if (outsideTheDormBtn.isSelected()) {
				query += "AND Place = '���~'";
				// 50
				if (priceBox.getSelectedItem().equals("50�H�U")) {
					query += "AND under50 = 1 ";
					if (taiwanFoodBtn.isSelected()) {
						query += "AND Style = '�x/��/�䦡' ";
						ResultSet resultSet = statement.executeQuery(query);
						while (resultSet.next()) {
							Choosing(resultSet.getString("Name"), resultSet.getDouble("star"));
							addPanel();
						}
					} else if (JapanKoreanFoodBtn.isSelected()) {
						query += "AND Style = '��/��/�F�n�Ȧ�' ";
						ResultSet resultSet = statement.executeQuery(query);
						while (resultSet.next()) {
							Choosing(resultSet.getString("Name"), resultSet.getDouble("star"));
							addPanel();
						}
					} else if (EastenFoodBtn.isSelected()) {
						query += "AND Style = '�覡' ";
						ResultSet resultSet = statement.executeQuery(query);
						while (resultSet.next()) {
							Choosing(resultSet.getString("Name"), resultSet.getDouble("star"));
							addPanel();
						}
					} else if (drinksBtn.isSelected()) {
						query += "AND Style = '���\/�I��/����' ";
						ResultSet resultSet = statement.executeQuery(query);
						while (resultSet.next()) {
							Choosing(resultSet.getString("Name"), resultSet.getDouble("star"));
							addPanel();
						}
					}
				}
				// 50-100
				else if (priceBox.getSelectedItem().equals("50~100")) {
					query += "AND `50-100` = 1 ";
					if (taiwanFoodBtn.isSelected()) {
						query += "AND Style = '�x/��/�䦡' ";
						ResultSet resultSet = statement.executeQuery(query);
						while (resultSet.next()) {
							Choosing(resultSet.getString("Name"), resultSet.getDouble("star"));
							addPanel();
						}
					} else if (JapanKoreanFoodBtn.isSelected()) {
						query += "AND Style = '��/��/�F�n�Ȧ�' ";
						ResultSet resultSet = statement.executeQuery(query);
						while (resultSet.next()) {
							Choosing(resultSet.getString("Name"), resultSet.getDouble("star"));
							addPanel();
						}
					} else if (EastenFoodBtn.isSelected()) {
						query += "AND Style = '�覡' ";
						ResultSet resultSet = statement.executeQuery(query);
						while (resultSet.next()) {
							Choosing(resultSet.getString("Name"), resultSet.getDouble("star"));
							addPanel();
						}
					} else if (drinksBtn.isSelected()) {
						query += "AND Style = '���\/�I��/����' ";
						ResultSet resultSet = statement.executeQuery(query);
						while (resultSet.next()) {
							Choosing(resultSet.getString("Name"), resultSet.getDouble("star"));
							addPanel();
						}
					}
				}
				// 100-150
				else if (priceBox.getSelectedItem().equals("100~150")) {
					query += "AND `100-150` = 1 ";
					if (taiwanFoodBtn.isSelected()) {
						query += "AND Style = '�x/��/�䦡' ";
						ResultSet resultSet = statement.executeQuery(query);
						while (resultSet.next()) {
							Choosing(resultSet.getString("Name"), resultSet.getDouble("star"));
							addPanel();
						}
					} else if (JapanKoreanFoodBtn.isSelected()) {
						query += "AND Style = '��/��/�F�n�Ȧ�' ";
						ResultSet resultSet = statement.executeQuery(query);
						while (resultSet.next()) {
							Choosing(resultSet.getString("Name"), resultSet.getDouble("star"));
							addPanel();
						}
					} else if (EastenFoodBtn.isSelected()) {
						query += "AND Style = '�覡' ";
						ResultSet resultSet = statement.executeQuery(query);
						while (resultSet.next()) {
							Choosing(resultSet.getString("Name"), resultSet.getDouble("star"));
							addPanel();
						}
					} else if (drinksBtn.isSelected()) {
						query += "AND Style = '���\/�I��/����' ";
						ResultSet resultSet = statement.executeQuery(query);
						while (resultSet.next()) {
							Choosing(resultSet.getString("Name"), resultSet.getDouble("star"));
							addPanel();
						}
					}
				}
				// 150-200
				else if (priceBox.getSelectedItem().equals("150~200")) {
					query += "AND `150-200` = 1 ";
					if (taiwanFoodBtn.isSelected()) {
						query += "AND Style = '�x/��/�䦡' ";
						ResultSet resultSet = statement.executeQuery(query);
						while (resultSet.next()) {
							Choosing(resultSet.getString("Name"), resultSet.getDouble("star"));
							addPanel();
						}
					} else if (JapanKoreanFoodBtn.isSelected()) {
						query += "AND Style = '��/��/�F�n�Ȧ�' ";
						ResultSet resultSet = statement.executeQuery(query);
						while (resultSet.next()) {
							Choosing(resultSet.getString("Name"), resultSet.getDouble("star"));
							addPanel();
						}
					} else if (EastenFoodBtn.isSelected()) {
						query += "AND Style = '�覡' ";
						ResultSet resultSet = statement.executeQuery(query);
						while (resultSet.next()) {
							Choosing(resultSet.getString("Name"), resultSet.getDouble("star"));
							addPanel();
						}
					} else if (drinksBtn.isSelected()) {
						query += "AND Style = '���\/�I��/����' ";
						ResultSet resultSet = statement.executeQuery(query);
						while (resultSet.next()) {
							Choosing(resultSet.getString("Name"), resultSet.getDouble("star"));
							addPanel();
						}
					}
				}
				// 200-250
				else if (priceBox.getSelectedItem().equals("200~250")) {
					query += "AND `200-250` = 1 ";
					if (taiwanFoodBtn.isSelected()) {
						query += "AND Style = '�x/��/�䦡' ";
						ResultSet resultSet = statement.executeQuery(query);
						while (resultSet.next()) {
							Choosing(resultSet.getString("Name"), resultSet.getDouble("star"));
							addPanel();
						}
					} else if (JapanKoreanFoodBtn.isSelected()) {
						query += "AND Style = '��/��/�F�n�Ȧ�' ";
						ResultSet resultSet = statement.executeQuery(query);
						while (resultSet.next()) {
							Choosing(resultSet.getString("Name"), resultSet.getDouble("star"));
							addPanel();
						}
					} else if (EastenFoodBtn.isSelected()) {
						query += "AND Style = '�覡' ";
						ResultSet resultSet = statement.executeQuery(query);
						while (resultSet.next()) {
							Choosing(resultSet.getString("Name"), resultSet.getDouble("star"));
							addPanel();
						}
					} else if (drinksBtn.isSelected()) {
						query += "AND Style = '���\/�I��/����' ";
						ResultSet resultSet = statement.executeQuery(query);
						while (resultSet.next()) {
							Choosing(resultSet.getString("Name"), resultSet.getDouble("star"));
							addPanel();
						}
					}
				}
				// 250-300
				else if (priceBox.getSelectedItem().equals("250~300")) {
					query += "AND `250-300` = 1 ";
					if (taiwanFoodBtn.isSelected()) {
						query += "AND Style = '�x/��/�䦡' ";
						ResultSet resultSet = statement.executeQuery(query);
						while (resultSet.next()) {
							Choosing(resultSet.getString("Name"), resultSet.getDouble("star"));
							addPanel();
						}
					} else if (JapanKoreanFoodBtn.isSelected()) {
						query += "AND Style = '��/��/�F�n�Ȧ�' ";
						ResultSet resultSet = statement.executeQuery(query);
						while (resultSet.next()) {
							Choosing(resultSet.getString("Name"), resultSet.getDouble("star"));
							addPanel();
						}
					} else if (EastenFoodBtn.isSelected()) {
						query += "AND Style = '�覡' ";
						ResultSet resultSet = statement.executeQuery(query);
						while (resultSet.next()) {
							Choosing(resultSet.getString("Name"), resultSet.getDouble("star"));
							addPanel();
						}
					} else if (drinksBtn.isSelected()) {
						query += "AND Style = '���\/�I��/����' ";
						ResultSet resultSet = statement.executeQuery(query);
						while (resultSet.next()) {
							Choosing(resultSet.getString("Name"), resultSet.getDouble("star"));
							addPanel();
						}
					}
				}
				// 300-350
				else if (priceBox.getSelectedItem().equals("300~350")) {
					query += "AND `300-350` = 1 ";
					if (taiwanFoodBtn.isSelected()) {
						query += "AND Style = '�x/��/�䦡' ";
						ResultSet resultSet = statement.executeQuery(query);
						while (resultSet.next()) {
							Choosing(resultSet.getString("Name"), resultSet.getDouble("star"));
							addPanel();
						}
					} else if (JapanKoreanFoodBtn.isSelected()) {
						query += "AND Style = '��/��/�F�n�Ȧ�' ";
						ResultSet resultSet = statement.executeQuery(query);
						while (resultSet.next()) {
							Choosing(resultSet.getString("Name"), resultSet.getDouble("star"));
							addPanel();
						}
					} else if (EastenFoodBtn.isSelected()) {
						query += "AND Style = '�覡' ";
						ResultSet resultSet = statement.executeQuery(query);
						while (resultSet.next()) {
							Choosing(resultSet.getString("Name"), resultSet.getDouble("star"));
							addPanel();
						}
					} else if (drinksBtn.isSelected()) {
						query += "AND Style = '���\/�I��/����' ";
						ResultSet resultSet = statement.executeQuery(query);
						while (resultSet.next()) {
							Choosing(resultSet.getString("Name"), resultSet.getDouble("star"));
							addPanel();
						}
					}
				}
				// 350-400
				else if (priceBox.getSelectedItem().equals("350~400")) {
					query += "AND `350-400` = 1 ";
					if (taiwanFoodBtn.isSelected()) {
						query += "AND Style = '�x/��/�䦡' ";
						ResultSet resultSet = statement.executeQuery(query);
						while (resultSet.next()) {
							Choosing(resultSet.getString("Name"), resultSet.getDouble("star"));
							addPanel();
						}
					} else if (JapanKoreanFoodBtn.isSelected()) {
						query += "AND Style = '��/��/�F�n�Ȧ�' ";
						ResultSet resultSet = statement.executeQuery(query);
						while (resultSet.next()) {
							Choosing(resultSet.getString("Name"), resultSet.getDouble("star"));
							addPanel();
						}
					} else if (EastenFoodBtn.isSelected()) {
						query += "AND Style = '�覡' ";
						ResultSet resultSet = statement.executeQuery(query);
						while (resultSet.next()) {
							Choosing(resultSet.getString("Name"), resultSet.getDouble("star"));
							addPanel();
						}
					} else if (drinksBtn.isSelected()) {
						query += "AND Style = '���\/�I��/����' ";
						ResultSet resultSet = statement.executeQuery(query);
						while (resultSet.next()) {
							Choosing(resultSet.getString("Name"), resultSet.getDouble("star"));
							addPanel();
						}
					}
				}
				// 400up
				else if (priceBox.getSelectedItem().equals("400�H�W")) {
					query += "AND `above400` = 1 ";
					if (taiwanFoodBtn.isSelected()) {
						query += "AND Style = '�x/��/�䦡' ";
						ResultSet resultSet = statement.executeQuery(query);
						while (resultSet.next()) {
							Choosing(resultSet.getString("Name"), resultSet.getDouble("star"));
							addPanel();
						}
					} else if (JapanKoreanFoodBtn.isSelected()) {
						query += "AND Style = '��/��/�F�n�Ȧ�' ";
						ResultSet resultSet = statement.executeQuery(query);
						while (resultSet.next()) {
							Choosing(resultSet.getString("Name"), resultSet.getDouble("star"));
							addPanel();
						}
					} else if (EastenFoodBtn.isSelected()) {
						query += "AND Style = '�覡' ";
						ResultSet resultSet = statement.executeQuery(query);
						while (resultSet.next()) {
							Choosing(resultSet.getString("Name"), resultSet.getDouble("star"));
							addPanel();
						}
					} else if (drinksBtn.isSelected()) {
						query += "AND Style = '���\/�I��/����' ";
						ResultSet resultSet = statement.executeQuery(query);
						while (resultSet.next()) {
							Choosing(resultSet.getString("Name"), resultSet.getDouble("star"));
							addPanel();
						}
					}
				}

			}
			if (mcdBtn.isSelected()) {
				query += "AND Place = '����'";
				if (priceBox.getSelectedItem().equals("50�H�U")) {
					query += "AND under50 = 1 ";
					if (taiwanFoodBtn.isSelected()) {
						query += "AND Style = '�x/��/�䦡' ";
						ResultSet resultSet = statement.executeQuery(query);
						while (resultSet.next()) {
							Choosing(resultSet.getString("Name"), resultSet.getDouble("star"));
							addPanel();
						}
					} else if (JapanKoreanFoodBtn.isSelected()) {
						query += "AND Style = '��/��/�F�n�Ȧ�' ";
						ResultSet resultSet = statement.executeQuery(query);
						while (resultSet.next()) {
							Choosing(resultSet.getString("Name"), resultSet.getDouble("star"));
							addPanel();
						}
					} else if (EastenFoodBtn.isSelected()) {
						query += "AND Style = '�覡' ";
						ResultSet resultSet = statement.executeQuery(query);
						while (resultSet.next()) {
							Choosing(resultSet.getString("Name"), resultSet.getDouble("star"));
							addPanel();
						}
					} else if (drinksBtn.isSelected()) {
						query += "AND Style = '���\/�I��/����' ";
						ResultSet resultSet = statement.executeQuery(query);
						while (resultSet.next()) {
							Choosing(resultSet.getString("Name"), resultSet.getDouble("star"));
							addPanel();
						}
					}
				}
				// 50-100
				else if (priceBox.getSelectedItem().equals("50~100")) {
					query += "AND `50-100` = 1 ";
					if (taiwanFoodBtn.isSelected()) {
						query += "AND Style = '�x/��/�䦡' ";
						ResultSet resultSet = statement.executeQuery(query);
						while (resultSet.next()) {
							Choosing(resultSet.getString("Name"), resultSet.getDouble("star"));
							addPanel();
						}
					} else if (JapanKoreanFoodBtn.isSelected()) {
						query += "AND Style = '��/��/�F�n�Ȧ�' ";
						ResultSet resultSet = statement.executeQuery(query);
						while (resultSet.next()) {
							Choosing(resultSet.getString("Name"), resultSet.getDouble("star"));
							addPanel();
						}
					} else if (EastenFoodBtn.isSelected()) {
						query += "AND Style = '�覡' ";
						ResultSet resultSet = statement.executeQuery(query);
						while (resultSet.next()) {
							Choosing(resultSet.getString("Name"), resultSet.getDouble("star"));
							addPanel();
						}
					} else if (drinksBtn.isSelected()) {
						query += "AND Style = '���\/�I��/����' ";
						ResultSet resultSet = statement.executeQuery(query);
						while (resultSet.next()) {
							Choosing(resultSet.getString("Name"), resultSet.getDouble("star"));
							addPanel();
						}
					}
				}
				// 100-150
				else if (priceBox.getSelectedItem().equals("100~150")) {
					query += "AND `100-150` = 1 ";
					if (taiwanFoodBtn.isSelected()) {
						query += "AND Style = '�x/��/�䦡' ";
						ResultSet resultSet = statement.executeQuery(query);
						while (resultSet.next()) {
							Choosing(resultSet.getString("Name"), resultSet.getDouble("star"));
							addPanel();
						}
					} else if (JapanKoreanFoodBtn.isSelected()) {
						query += "AND Style = '��/��/�F�n�Ȧ�' ";
						ResultSet resultSet = statement.executeQuery(query);
						while (resultSet.next()) {
							Choosing(resultSet.getString("Name"), resultSet.getDouble("star"));
							addPanel();
						}
					} else if (EastenFoodBtn.isSelected()) {
						query += "AND Style = '�覡' ";
						ResultSet resultSet = statement.executeQuery(query);
						while (resultSet.next()) {
							Choosing(resultSet.getString("Name"), resultSet.getDouble("star"));
							addPanel();
						}
					} else if (drinksBtn.isSelected()) {
						query += "AND Style = '���\/�I��/����' ";
						ResultSet resultSet = statement.executeQuery(query);
						while (resultSet.next()) {
							Choosing(resultSet.getString("Name"), resultSet.getDouble("star"));
							addPanel();
						}
					}
				}
				// 150-200
				else if (priceBox.getSelectedItem().equals("150~200")) {
					query += "AND `150-200` = 1 ";
					if (taiwanFoodBtn.isSelected()) {
						query += "AND Style = '�x/��/�䦡' ";
						ResultSet resultSet = statement.executeQuery(query);
						while (resultSet.next()) {
							Choosing(resultSet.getString("Name"), resultSet.getDouble("star"));
							addPanel();
						}
					} else if (JapanKoreanFoodBtn.isSelected()) {
						query += "AND Style = '��/��/�F�n�Ȧ�' ";
						ResultSet resultSet = statement.executeQuery(query);
						while (resultSet.next()) {
							Choosing(resultSet.getString("Name"), resultSet.getDouble("star"));
							addPanel();
						}
					} else if (EastenFoodBtn.isSelected()) {
						query += "AND Style = '�覡' ";
						ResultSet resultSet = statement.executeQuery(query);
						while (resultSet.next()) {
							Choosing(resultSet.getString("Name"), resultSet.getDouble("star"));
							addPanel();
						}
					} else if (drinksBtn.isSelected()) {
						query += "AND Style = '���\/�I��/����' ";
						ResultSet resultSet = statement.executeQuery(query);
						while (resultSet.next()) {
							Choosing(resultSet.getString("Name"), resultSet.getDouble("star"));
							addPanel();
						}
					}
				}
				// 200-250 test
				else if (priceBox.getSelectedItem().equals("200~250")) {
					query += "AND `200-250` = 1 ";
					if (taiwanFoodBtn.isSelected()) {
						query += "AND Style = '�x/��/�䦡' ";
						ResultSet resultSet = statement.executeQuery(query);
						while (resultSet.next()) {
							Choosing(resultSet.getString("Name"), resultSet.getDouble("star"));
							addPanel();
						}
					} else if (JapanKoreanFoodBtn.isSelected()) {
						query += "AND Style = '��/��/�F�n�Ȧ�' ";
						ResultSet resultSet = statement.executeQuery(query);
						while (resultSet.next()) {
							Choosing(resultSet.getString("Name"), resultSet.getDouble("star"));
							addPanel();
						}
					} else if (EastenFoodBtn.isSelected()) {
						query += "AND Style = '�覡' ";
						ResultSet resultSet = statement.executeQuery(query);
						while (resultSet.next()) {
							Choosing(resultSet.getString("Name"), resultSet.getDouble("star"));
							addPanel();
						}
					} else if (drinksBtn.isSelected()) {
						query += "AND Style = '���\/�I��/����' ";
						ResultSet resultSet = statement.executeQuery(query);
						while (resultSet.next()) {
							Choosing(resultSet.getString("Name"), resultSet.getDouble("star"));
							addPanel();
						}
					}
				}
				// 250-300
				else if (priceBox.getSelectedItem().equals("250~300")) {
					query += "AND `250-300` = 1 ";
					if (taiwanFoodBtn.isSelected()) {
						query += "AND Style = '�x/��/�䦡' ";
						ResultSet resultSet = statement.executeQuery(query);
						while (resultSet.next()) {
							Choosing(resultSet.getString("Name"), resultSet.getDouble("star"));
							addPanel();
						}
					} else if (JapanKoreanFoodBtn.isSelected()) {
						query += "AND Style = '��/��/�F�n�Ȧ�' ";
						ResultSet resultSet = statement.executeQuery(query);
						while (resultSet.next()) {
							Choosing(resultSet.getString("Name"), resultSet.getDouble("star"));
							addPanel();
						}
					} else if (EastenFoodBtn.isSelected()) {
						query += "AND Style = '�覡' ";
						ResultSet resultSet = statement.executeQuery(query);
						while (resultSet.next()) {
							Choosing(resultSet.getString("Name"), resultSet.getDouble("star"));
							addPanel();
						}
					} else if (drinksBtn.isSelected()) {
						query += "AND Style = '���\/�I��/����' ";
						ResultSet resultSet = statement.executeQuery(query);
						while (resultSet.next()) {
							Choosing(resultSet.getString("Name"), resultSet.getDouble("star"));
							addPanel();
						}
					}
				}
				// 300-350
				else if (priceBox.getSelectedItem().equals("300~350")) {
					query += "AND `300-350` = 1 ";
					if (taiwanFoodBtn.isSelected()) {
						query += "AND Style = '�x/��/�䦡' ";
						ResultSet resultSet = statement.executeQuery(query);
						while (resultSet.next()) {
							Choosing(resultSet.getString("Name"), resultSet.getDouble("star"));
							addPanel();
						}
					} else if (JapanKoreanFoodBtn.isSelected()) {
						query += "AND Style = '��/��/�F�n�Ȧ�' ";
						ResultSet resultSet = statement.executeQuery(query);
						while (resultSet.next()) {
							Choosing(resultSet.getString("Name"), resultSet.getDouble("star"));
							addPanel();
						}
					} else if (EastenFoodBtn.isSelected()) {
						query += "AND Style = '�覡' ";
						ResultSet resultSet = statement.executeQuery(query);
						while (resultSet.next()) {
							Choosing(resultSet.getString("Name"), resultSet.getDouble("star"));
							addPanel();
						}
					} else if (drinksBtn.isSelected()) {
						query += "AND Style = '���\/�I��/����' ";
						ResultSet resultSet = statement.executeQuery(query);
						while (resultSet.next()) {
							Choosing(resultSet.getString("Name"), resultSet.getDouble("star"));
							addPanel();
						}
					}
				}
				// 350-400
				else if (priceBox.getSelectedItem().equals("350~400")) {
					query += "AND `350-400` = 1 ";
					if (taiwanFoodBtn.isSelected()) {
						query += "AND Style = '�x/��/�䦡' ";
						ResultSet resultSet = statement.executeQuery(query);
						while (resultSet.next()) {
							Choosing(resultSet.getString("Name"), resultSet.getDouble("star"));
							addPanel();
						}
					} else if (JapanKoreanFoodBtn.isSelected()) {
						query += "AND Style = '��/��/�F�n�Ȧ�' ";
						ResultSet resultSet = statement.executeQuery(query);
						while (resultSet.next()) {
							Choosing(resultSet.getString("Name"), resultSet.getDouble("star"));
							addPanel();
						}
					} else if (EastenFoodBtn.isSelected()) {
						query += "AND Style = '�覡' ";
						ResultSet resultSet = statement.executeQuery(query);
						while (resultSet.next()) {
							Choosing(resultSet.getString("Name"), resultSet.getDouble("star"));
							addPanel();
						}
					} else if (drinksBtn.isSelected()) {
						query += "AND Style = '���\/�I��/����' ";
						ResultSet resultSet = statement.executeQuery(query);
						while (resultSet.next()) {
							Choosing(resultSet.getString("Name"), resultSet.getDouble("star"));
							addPanel();
						}
					}
				}
				// 400up
				else if (priceBox.getSelectedItem().equals("400�H�W")) {
					query += "AND `above400` = 1 ";
					if (taiwanFoodBtn.isSelected()) {
						query += "AND Style = '�x/��/�䦡' ";
						ResultSet resultSet = statement.executeQuery(query);
						while (resultSet.next()) {
							Choosing(resultSet.getString("Name"), resultSet.getDouble("star"));
							addPanel();
						}
					} else if (JapanKoreanFoodBtn.isSelected()) {
						query += "AND Style = '��/��/�F�n�Ȧ�' ";
						ResultSet resultSet = statement.executeQuery(query);
						while (resultSet.next()) {
							Choosing(resultSet.getString("Name"), resultSet.getDouble("star"));
							addPanel();
						}
					} else if (EastenFoodBtn.isSelected()) {
						query += "AND Style = '�覡' ";
						ResultSet resultSet = statement.executeQuery(query);
						while (resultSet.next()) {
							Choosing(resultSet.getString("Name"), resultSet.getDouble("star"));
							addPanel();
						}
					} else if (drinksBtn.isSelected()) {
						query += "AND Style = '���\/�I��/����' ";
						ResultSet resultSet = statement.executeQuery(query);
						while (resultSet.next()) {
							Choosing(resultSet.getString("Name"), resultSet.getDouble("star"));
							addPanel();
						}
					}
				}

			}
			if (stoneBtn.isSelected()) {
				query += "AND Place = '�ոO'";
				if (priceBox.getSelectedItem().equals("50�H�U")) {
					query += "AND under50 = 1 ";
					if (taiwanFoodBtn.isSelected()) {
						query += "AND Style = '�x/��/�䦡' ";
						ResultSet resultSet = statement.executeQuery(query);
						while (resultSet.next()) {
							Choosing(resultSet.getString("Name"), resultSet.getDouble("star"));
							addPanel();
						}
					} else if (JapanKoreanFoodBtn.isSelected()) {
						query += "AND Style = '��/��/�F�n�Ȧ�' ";
						ResultSet resultSet = statement.executeQuery(query);
						while (resultSet.next()) {
							Choosing(resultSet.getString("Name"), resultSet.getDouble("star"));
							addPanel();
						}
					} else if (EastenFoodBtn.isSelected()) {
						query += "AND Style = '�覡' ";
						ResultSet resultSet = statement.executeQuery(query);
						while (resultSet.next()) {
							Choosing(resultSet.getString("Name"), resultSet.getDouble("star"));
							addPanel();
						}
					} else if (drinksBtn.isSelected()) {
						query += "AND Style = '���\/�I��/����' ";
						ResultSet resultSet = statement.executeQuery(query);
						while (resultSet.next()) {
							Choosing(resultSet.getString("Name"), resultSet.getDouble("star"));
							addPanel();
						}
					}
				}
				// 50-100
				else if (priceBox.getSelectedItem().equals("50~100")) {
					query += "AND `50-100` = 1 ";
					if (taiwanFoodBtn.isSelected()) {
						query += "AND Style = '�x/��/�䦡' ";
						ResultSet resultSet = statement.executeQuery(query);
						while (resultSet.next()) {
							Choosing(resultSet.getString("Name"), resultSet.getDouble("star"));
							addPanel();
						}
					} else if (JapanKoreanFoodBtn.isSelected()) {
						query += "AND Style = '��/��/�F�n�Ȧ�' ";
						ResultSet resultSet = statement.executeQuery(query);
						while (resultSet.next()) {
							Choosing(resultSet.getString("Name"), resultSet.getDouble("star"));
							addPanel();
						}
					} else if (EastenFoodBtn.isSelected()) {
						query += "AND Style = '�覡' ";
						ResultSet resultSet = statement.executeQuery(query);
						while (resultSet.next()) {
							Choosing(resultSet.getString("Name"), resultSet.getDouble("star"));
							addPanel();
						}
					} else if (drinksBtn.isSelected()) {
						query += "AND Style = '���\/�I��/����' ";
						ResultSet resultSet = statement.executeQuery(query);
						while (resultSet.next()) {
							Choosing(resultSet.getString("Name"), resultSet.getDouble("star"));
							addPanel();
						}
					}
				}
				// 100-150
				else if (priceBox.getSelectedItem().equals("100~150")) {
					query += "AND `100-150` = 1 ";
					if (taiwanFoodBtn.isSelected()) {
						query += "AND Style = '�x/��/�䦡' ";
						ResultSet resultSet = statement.executeQuery(query);
						while (resultSet.next()) {
							Choosing(resultSet.getString("Name"), resultSet.getDouble("star"));
							addPanel();
						}
					} else if (JapanKoreanFoodBtn.isSelected()) {
						query += "AND Style = '��/��/�F�n�Ȧ�' ";
						ResultSet resultSet = statement.executeQuery(query);
						while (resultSet.next()) {
							Choosing(resultSet.getString("Name"), resultSet.getDouble("star"));
							addPanel();
						}
					} else if (EastenFoodBtn.isSelected()) {
						query += "AND Style = '�覡' ";
						ResultSet resultSet = statement.executeQuery(query);
						while (resultSet.next()) {
							Choosing(resultSet.getString("Name"), resultSet.getDouble("star"));
							addPanel();
						}
					} else if (drinksBtn.isSelected()) {
						query += "AND Style = '���\/�I��/����' ";
						ResultSet resultSet = statement.executeQuery(query);
						while (resultSet.next()) {
							Choosing(resultSet.getString("Name"), resultSet.getDouble("star"));
							addPanel();
						}
					}
				}
				// 150-200
				else if (priceBox.getSelectedItem().equals("150~200")) {
					query += "AND `150-200` = 1 ";
					if (taiwanFoodBtn.isSelected()) {
						query += "AND Style = '�x/��/�䦡' ";
						ResultSet resultSet = statement.executeQuery(query);
						while (resultSet.next()) {
							Choosing(resultSet.getString("Name"), resultSet.getDouble("star"));
							addPanel();
						}
					} else if (JapanKoreanFoodBtn.isSelected()) {
						query += "AND Style = '��/��/�F�n�Ȧ�' ";
						ResultSet resultSet = statement.executeQuery(query);
						while (resultSet.next()) {
							Choosing(resultSet.getString("Name"), resultSet.getDouble("star"));
							addPanel();
						}
					} else if (EastenFoodBtn.isSelected()) {
						query += "AND Style = '�覡' ";
						ResultSet resultSet = statement.executeQuery(query);
						while (resultSet.next()) {
							Choosing(resultSet.getString("Name"), resultSet.getDouble("star"));
							addPanel();
						}
					} else if (drinksBtn.isSelected()) {
						query += "AND Style = '���\/�I��/����' ";
						ResultSet resultSet = statement.executeQuery(query);
						while (resultSet.next()) {
							Choosing(resultSet.getString("Name"), resultSet.getDouble("star"));
							addPanel();
						}
					}
				}
				// 200-250
				else if (priceBox.getSelectedItem().equals("200~250")) {
					query += "AND `200-250` = 1 ";
					if (taiwanFoodBtn.isSelected()) {
						query += "AND Style = '�x/��/�䦡' ";
						ResultSet resultSet = statement.executeQuery(query);
						while (resultSet.next()) {
							Choosing(resultSet.getString("Name"), resultSet.getDouble("star"));
							addPanel();
						}
					} else if (JapanKoreanFoodBtn.isSelected()) {
						query += "AND Style = '��/��/�F�n�Ȧ�' ";
						ResultSet resultSet = statement.executeQuery(query);
						while (resultSet.next()) {
							Choosing(resultSet.getString("Name"), resultSet.getDouble("star"));
							addPanel();
						}
					} else if (EastenFoodBtn.isSelected()) {
						query += "AND Style = '�覡' ";
						ResultSet resultSet = statement.executeQuery(query);
						while (resultSet.next()) {
							Choosing(resultSet.getString("Name"), resultSet.getDouble("star"));
							addPanel();
						}
					} else if (drinksBtn.isSelected()) {
						query += "AND Style = '���\/�I��/����' ";
						ResultSet resultSet = statement.executeQuery(query);
						while (resultSet.next()) {
							Choosing(resultSet.getString("Name"), resultSet.getDouble("star"));
							addPanel();
						}
					}
				}
				// 250-300
				else if (priceBox.getSelectedItem().equals("250~300")) {
					query += "AND `250-300` = 1 ";
					if (taiwanFoodBtn.isSelected()) {
						query += "AND Style = '�x/��/�䦡' ";
						ResultSet resultSet = statement.executeQuery(query);
						while (resultSet.next()) {
							Choosing(resultSet.getString("Name"), resultSet.getDouble("star"));
							addPanel();
						}
					} else if (JapanKoreanFoodBtn.isSelected()) {
						query += "AND Style = '��/��/�F�n�Ȧ�' ";
						ResultSet resultSet = statement.executeQuery(query);
						while (resultSet.next()) {
							Choosing(resultSet.getString("Name"), resultSet.getDouble("star"));
							addPanel();
						}
					} else if (EastenFoodBtn.isSelected()) {
						query += "AND Style = '�覡' ";
						ResultSet resultSet = statement.executeQuery(query);
						while (resultSet.next()) {
							Choosing(resultSet.getString("Name"), resultSet.getDouble("star"));
							addPanel();
						}
					} else if (drinksBtn.isSelected()) {
						query += "AND Style = '���\/�I��/����' ";
						ResultSet resultSet = statement.executeQuery(query);
						while (resultSet.next()) {
							Choosing(resultSet.getString("Name"), resultSet.getDouble("star"));
							addPanel();
						}
					}
				}
				// 300-350
				else if (priceBox.getSelectedItem().equals("300~350")) {
					query += "AND `300-350` = 1 ";
					if (taiwanFoodBtn.isSelected()) {
						query += "AND Style = '�x/��/�䦡' ";
						ResultSet resultSet = statement.executeQuery(query);
						while (resultSet.next()) {
							Choosing(resultSet.getString("Name"), resultSet.getDouble("star"));
							addPanel();
						}
					} else if (JapanKoreanFoodBtn.isSelected()) {
						query += "AND Style = '��/��/�F�n�Ȧ�' ";
						ResultSet resultSet = statement.executeQuery(query);
						while (resultSet.next()) {
							Choosing(resultSet.getString("Name"), resultSet.getDouble("star"));
							addPanel();
						}
					} else if (EastenFoodBtn.isSelected()) {
						query += "AND Style = '�覡' ";
						ResultSet resultSet = statement.executeQuery(query);
						while (resultSet.next()) {
							Choosing(resultSet.getString("Name"), resultSet.getDouble("star"));
							addPanel();
						}
					} else if (drinksBtn.isSelected()) {
						query += "AND Style = '���\/�I��/����' ";
						ResultSet resultSet = statement.executeQuery(query);
						while (resultSet.next()) {
							Choosing(resultSet.getString("Name"), resultSet.getDouble("star"));
							addPanel();
						}
					}
				}
				// 350-400
				else if (priceBox.getSelectedItem().equals("350~400")) {
					query += "AND `350-400` = 1 ";
					if (taiwanFoodBtn.isSelected()) {
						query += "AND Style = '�x/��/�䦡' ";
						ResultSet resultSet = statement.executeQuery(query);
						while (resultSet.next()) {
							Choosing(resultSet.getString("Name"), resultSet.getDouble("star"));
							addPanel();
						}
					} else if (JapanKoreanFoodBtn.isSelected()) {
						query += "AND Style = '��/��/�F�n�Ȧ�' ";
						ResultSet resultSet = statement.executeQuery(query);
						while (resultSet.next()) {
							Choosing(resultSet.getString("Name"), resultSet.getDouble("star"));
							addPanel();
						}
					} else if (EastenFoodBtn.isSelected()) {
						query += "AND Style = '�覡' ";
						ResultSet resultSet = statement.executeQuery(query);
						while (resultSet.next()) {
							Choosing(resultSet.getString("Name"), resultSet.getDouble("star"));
							addPanel();
						}
					} else if (drinksBtn.isSelected()) {
						query += "AND Style = '���\/�I��/����' ";
						ResultSet resultSet = statement.executeQuery(query);
						while (resultSet.next()) {
							Choosing(resultSet.getString("Name"), resultSet.getDouble("star"));
							addPanel();
						}
					}
				}
				// 400up
				else if (priceBox.getSelectedItem().equals("400�H�W")) {
					query += "AND `above400` = 1 ";
					if (taiwanFoodBtn.isSelected()) {
						query += "AND Style = '�x/��/�䦡' ";
						ResultSet resultSet = statement.executeQuery(query);
						while (resultSet.next()) {
							Choosing(resultSet.getString("Name"), resultSet.getDouble("star"));
							addPanel();
						}
					} else if (JapanKoreanFoodBtn.isSelected()) {
						query += "AND Style = '��/��/�F�n�Ȧ�' ";
						ResultSet resultSet = statement.executeQuery(query);
						while (resultSet.next()) {
							Choosing(resultSet.getString("Name"), resultSet.getDouble("star"));
							addPanel();
						}
					} else if (EastenFoodBtn.isSelected()) {
						query += "AND Style = '�覡' ";
						ResultSet resultSet = statement.executeQuery(query);
						while (resultSet.next()) {
							Choosing(resultSet.getString("Name"), resultSet.getDouble("star"));
							addPanel();
						}
					} else if (drinksBtn.isSelected()) {
						query += "AND Style = '���\/�I��/����' ";
						ResultSet resultSet = statement.executeQuery(query);
						while (resultSet.next()) {
							Choosing(resultSet.getString("Name"), resultSet.getDouble("star"));
							addPanel();
						}
					}
				}
			}
			if (BuildingBtn.isSelected()) {
				query += "AND Place = '�ͽ��'";
				if (priceBox.getSelectedItem().equals("50�H�U")) {
					query += "AND under50 = 1 ";
					if (taiwanFoodBtn.isSelected()) {
						query += "AND Style = '�x/��/�䦡' ";
						ResultSet resultSet = statement.executeQuery(query);
						while (resultSet.next()) {
							Choosing(resultSet.getString("Name"), resultSet.getDouble("star"));
							addPanel();
						}
					} else if (JapanKoreanFoodBtn.isSelected()) {
						query += "AND Style = '��/��/�F�n�Ȧ�' ";
						ResultSet resultSet = statement.executeQuery(query);
						while (resultSet.next()) {
							Choosing(resultSet.getString("Name"), resultSet.getDouble("star"));
							addPanel();
						}
					} else if (EastenFoodBtn.isSelected()) {
						query += "AND Style = '�覡' ";
						ResultSet resultSet = statement.executeQuery(query);
						while (resultSet.next()) {
							Choosing(resultSet.getString("Name"), resultSet.getDouble("star"));
							addPanel();
						}
					} else if (drinksBtn.isSelected()) {
						query += "AND Style = '���\/�I��/����' ";
						ResultSet resultSet = statement.executeQuery(query);
						while (resultSet.next()) {
							Choosing(resultSet.getString("Name"), resultSet.getDouble("star"));
							addPanel();
						}
					}
				}
				// 50-100
				else if (priceBox.getSelectedItem().equals("50~100")) {
					query += "AND `50-100` = 1 ";
					if (taiwanFoodBtn.isSelected()) {
						query += "AND Style = '�x/��/�䦡' ";
						ResultSet resultSet = statement.executeQuery(query);
						while (resultSet.next()) {
							Choosing(resultSet.getString("Name"), resultSet.getDouble("star"));
							addPanel();
						}
					} else if (JapanKoreanFoodBtn.isSelected()) {
						query += "AND Style = '��/��/�F�n�Ȧ�' ";
						ResultSet resultSet = statement.executeQuery(query);
						while (resultSet.next()) {
							Choosing(resultSet.getString("Name"), resultSet.getDouble("star"));
							addPanel();
						}
					} else if (EastenFoodBtn.isSelected()) {
						query += "AND Style = '�覡' ";
						ResultSet resultSet = statement.executeQuery(query);
						while (resultSet.next()) {
							Choosing(resultSet.getString("Name"), resultSet.getDouble("star"));
							addPanel();
						}
					} else if (drinksBtn.isSelected()) {
						query += "AND Style = '���\/�I��/����' ";
						ResultSet resultSet = statement.executeQuery(query);
						while (resultSet.next()) {
							Choosing(resultSet.getString("Name"), resultSet.getDouble("star"));
							addPanel();
						}
					}
				}
				// 100-150
				else if (priceBox.getSelectedItem().equals("100~150")) {
					query += "AND `100-150` = 1 ";
					if (taiwanFoodBtn.isSelected()) {
						query += "AND Style = '�x/��/�䦡' ";
						ResultSet resultSet = statement.executeQuery(query);
						while (resultSet.next()) {
							Choosing(resultSet.getString("Name"), resultSet.getDouble("star"));
							addPanel();
						}
					} else if (JapanKoreanFoodBtn.isSelected()) {
						query += "AND Style = '��/��/�F�n�Ȧ�' ";
						ResultSet resultSet = statement.executeQuery(query);
						while (resultSet.next()) {
							Choosing(resultSet.getString("Name"), resultSet.getDouble("star"));
							addPanel();
						}
					} else if (EastenFoodBtn.isSelected()) {
						query += "AND Style = '�覡' ";
						ResultSet resultSet = statement.executeQuery(query);
						while (resultSet.next()) {
							Choosing(resultSet.getString("Name"), resultSet.getDouble("star"));
							addPanel();
						}
					} else if (drinksBtn.isSelected()) {
						query += "AND Style = '���\/�I��/����' ";
						ResultSet resultSet = statement.executeQuery(query);
						while (resultSet.next()) {
							Choosing(resultSet.getString("Name"), resultSet.getDouble("star"));
							addPanel();
						}
					}
				}
				// 150-200
				else if (priceBox.getSelectedItem().equals("150~200")) {
					query += "AND `150-200` = 1 ";
					if (taiwanFoodBtn.isSelected()) {
						query += "AND Style = '�x/��/�䦡' ";
						ResultSet resultSet = statement.executeQuery(query);
						while (resultSet.next()) {
							Choosing(resultSet.getString("Name"), resultSet.getDouble("star"));
							addPanel();
						}
					} else if (JapanKoreanFoodBtn.isSelected()) {
						query += "AND Style = '��/��/�F�n�Ȧ�' ";
						ResultSet resultSet = statement.executeQuery(query);
						while (resultSet.next()) {
							Choosing(resultSet.getString("Name"), resultSet.getDouble("star"));
							addPanel();
						}
					} else if (EastenFoodBtn.isSelected()) {
						query += "AND Style = '�覡' ";
						ResultSet resultSet = statement.executeQuery(query);
						while (resultSet.next()) {
							Choosing(resultSet.getString("Name"), resultSet.getDouble("star"));
							addPanel();
						}
					} else if (drinksBtn.isSelected()) {
						query += "AND Style = '���\/�I��/����' ";
						ResultSet resultSet = statement.executeQuery(query);
						while (resultSet.next()) {
							Choosing(resultSet.getString("Name"), resultSet.getDouble("star"));
							addPanel();
						}
					}
				}
				// 200-250
				else if (priceBox.getSelectedItem().equals("200~250")) {
					query += "AND `200-250` = 1 ";
					if (taiwanFoodBtn.isSelected()) {
						query += "AND Style = '�x/��/�䦡' ";
						ResultSet resultSet = statement.executeQuery(query);
						while (resultSet.next()) {
							Choosing(resultSet.getString("Name"), resultSet.getDouble("star"));
							addPanel();
						}
					} else if (JapanKoreanFoodBtn.isSelected()) {
						query += "AND Style = '��/��/�F�n�Ȧ�' ";
						ResultSet resultSet = statement.executeQuery(query);
						while (resultSet.next()) {
							Choosing(resultSet.getString("Name"), resultSet.getDouble("star"));
							addPanel();
						}
					} else if (EastenFoodBtn.isSelected()) {
						query += "AND Style = '�覡' ";
						ResultSet resultSet = statement.executeQuery(query);
						while (resultSet.next()) {
							Choosing(resultSet.getString("Name"), resultSet.getDouble("star"));
							addPanel();
						}
					} else if (drinksBtn.isSelected()) {
						query += "AND Style = '���\/�I��/����' ";
						ResultSet resultSet = statement.executeQuery(query);
						while (resultSet.next()) {
							Choosing(resultSet.getString("Name"), resultSet.getDouble("star"));
							addPanel();
						}
					}
				}
				// 250-300
				else if (priceBox.getSelectedItem().equals("250~300")) {
					query += "AND `250-300` = 1 ";
					if (taiwanFoodBtn.isSelected()) {
						query += "AND Style = '�x/��/�䦡' ";
						ResultSet resultSet = statement.executeQuery(query);
						while (resultSet.next()) {
							Choosing(resultSet.getString("Name"), resultSet.getDouble("star"));
							addPanel();
						}
					} else if (JapanKoreanFoodBtn.isSelected()) {
						query += "AND Style = '��/��/�F�n�Ȧ�' ";
						ResultSet resultSet = statement.executeQuery(query);
						while (resultSet.next()) {
							Choosing(resultSet.getString("Name"), resultSet.getDouble("star"));
							addPanel();
						}
					} else if (EastenFoodBtn.isSelected()) {
						query += "AND Style = '�覡' ";
						ResultSet resultSet = statement.executeQuery(query);
						while (resultSet.next()) {
							Choosing(resultSet.getString("Name"), resultSet.getDouble("star"));
							addPanel();
						}
					} else if (drinksBtn.isSelected()) {
						query += "AND Style = '���\/�I��/����' ";
						ResultSet resultSet = statement.executeQuery(query);
						while (resultSet.next()) {
							Choosing(resultSet.getString("Name"), resultSet.getDouble("star"));
							addPanel();
						}
					}
				}
				// 300-350
				else if (priceBox.getSelectedItem().equals("300~350")) {
					query += "AND `300-350` = 1 ";
					if (taiwanFoodBtn.isSelected()) {
						query += "AND Style = '�x/��/�䦡' ";
						ResultSet resultSet = statement.executeQuery(query);
						while (resultSet.next()) {
							Choosing(resultSet.getString("Name"), resultSet.getDouble("star"));
							addPanel();
						}
					} else if (JapanKoreanFoodBtn.isSelected()) {
						query += "AND Style = '��/��/�F�n�Ȧ�' ";
						ResultSet resultSet = statement.executeQuery(query);
						while (resultSet.next()) {
							Choosing(resultSet.getString("Name"), resultSet.getDouble("star"));
							addPanel();
						}
					} else if (EastenFoodBtn.isSelected()) {
						query += "AND Style = '�覡' ";
						ResultSet resultSet = statement.executeQuery(query);
						while (resultSet.next()) {
							Choosing(resultSet.getString("Name"), resultSet.getDouble("star"));
							addPanel();
						}
					} else if (drinksBtn.isSelected()) {
						query += "AND Style = '���\/�I��/����' ";
						ResultSet resultSet = statement.executeQuery(query);
						while (resultSet.next()) {
							Choosing(resultSet.getString("Name"), resultSet.getDouble("star"));
							addPanel();
						}
					}
				}
				// 350-400
				else if (priceBox.getSelectedItem().equals("350~400")) {
					query += "AND `350-400` = 1";
					if (taiwanFoodBtn.isSelected()) {
						query += "AND Style = '�x/��/�䦡' ";
						ResultSet resultSet = statement.executeQuery(query);
						while (resultSet.next()) {
							Choosing(resultSet.getString("Name"), resultSet.getDouble("star"));
							addPanel();
						}
					} else if (JapanKoreanFoodBtn.isSelected()) {
						query += "AND Style = '��/��/�F�n�Ȧ�' ";
						ResultSet resultSet = statement.executeQuery(query);
						while (resultSet.next()) {
							Choosing(resultSet.getString("Name"), resultSet.getDouble("star"));
							addPanel();
						}
					} else if (EastenFoodBtn.isSelected()) {
						query += "AND Style = '�覡' ";
						ResultSet resultSet = statement.executeQuery(query);
						while (resultSet.next()) {
							Choosing(resultSet.getString("Name"), resultSet.getDouble("star"));
							addPanel();
						}
					} else if (drinksBtn.isSelected()) {
						query += "AND Style = '���\/�I��/����' ";
						ResultSet resultSet = statement.executeQuery(query);
						while (resultSet.next()) {
							Choosing(resultSet.getString("Name"), resultSet.getDouble("star"));
							addPanel();
						}
					}
				}
				// 400up
				else if (priceBox.getSelectedItem().equals("400�H�W")) {
					query += "AND `above400` = 1 ";
					if (taiwanFoodBtn.isSelected()) {
						query += "AND Style = '�x/��/�䦡' ";
						ResultSet resultSet = statement.executeQuery(query);
						while (resultSet.next()) {
							Choosing(resultSet.getString("Name"), resultSet.getDouble("star"));
							addPanel();
						}
					} else if (JapanKoreanFoodBtn.isSelected()) {
						query += "AND Style = '��/��/�F�n�Ȧ�' ";
						ResultSet resultSet = statement.executeQuery(query);
						while (resultSet.next()) {
							Choosing(resultSet.getString("Name"), resultSet.getDouble("star"));
							addPanel();
						}
					} else if (EastenFoodBtn.isSelected()) {
						query += "AND Style = '�覡' ";
						ResultSet resultSet = statement.executeQuery(query);
						while (resultSet.next()) {
							Choosing(resultSet.getString("Name"), resultSet.getDouble("star"));
							addPanel();
						}
					} else if (drinksBtn.isSelected()) {
						query += "AND Style = '���\/�I��/����' ";
						ResultSet resultSet = statement.executeQuery(query);
						while (resultSet.next()) {
							Choosing(resultSet.getString("Name"), resultSet.getDouble("star"));
							addPanel();
						}
					}
				}

			}

		}
		connection.close();

	}

	

	public void Choosing(String name, double star) throws SQLException {
		jPanel = new JPanel();
		JRadioButton radioButton = new JRadioButton(name);
		JLabel label = new JLabel(" " + star);
		group1.add(radioButton);

		class RadioListener implements ActionListener {
			public void actionPerformed(ActionEvent e) {
				try {
					Connection connection = SimpleDataSourse.getConnection();
				//	Statement statement = connection.createStatement();
					PreparedStatement statement2 = connection
							.prepareStatement("SELECT imgsrc FROM image_src " + "WHERE Name = ? ");
					statement2.setString(1, name);
					ResultSet resultSet = statement2.executeQuery();
					resultSet.next();
					file = new File(resultSet.getString("imgsrc"));
					System.out.println(file.getPath());
					// imageIcon = new ImageIcon(resultSet.getString("imgsrc"));
					if (file != null) {
						textPane.setSize(AREA_WIDTH, AREA_HEIGHT);
						textPane.insertIcon(new ImageIcon(file.getPath()));
					}

				} catch (SQLException e1) {
					// TODO: handle exception
					e1.getMessage();
				}
			}
		}
		radioButton.addActionListener(new RadioListener());
		jPanel.setLayout(new GridLayout(1, 2));
		jPanel.add(radioButton);
		jPanel.add(label);
		System.out.println(name);
		InfoPanel.setLayout(new GridLayout(3, 3));
		InfoPanel.add(jPanel);
		

	}
	public void addPanel() {
		panel.add(InfoPanel);
	}

}
