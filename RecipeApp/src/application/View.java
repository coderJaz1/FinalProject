package application;

import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.CardLayout;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.JSpinner;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.SpinnerNumberModel;
import javax.swing.SpinnerListModel;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JCheckBox;
import javax.swing.UIManager;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

/**
 * This is the View class, used as part of the Model View
 * Controller (MVC) pattern. It controls the data flow into the model
 * object and updates the view whenever data changes by creating a view
 *  for our application through which all user interaction will be
 * facilitated.
 * 
 * Requirements: 1.0.0, 1.0.2
 */
public class View extends JFrame {
	private static final long serialVersionUID = -1927397423925984301L;

	// Items for the primary panel and cards
	private JPanel contentPane = new JPanel();
	private JPanel card1 = new JPanel();
	private JPanel card2 = new JPanel();
	private CardLayout cl_cardPanel = new CardLayout();

	// Recipe information on "View / Modify" card
	private JLabel lblRecipeInformation;
	private JLabel lblId;
	private JTextField txtId = new JTextField();
	private JLabel lblTitle;
	private JTextField txtTitle = new JTextField();
	private JLabel lblCookTime;
	private JSpinner spinnerCook = new JSpinner();
	private JLabel lblminutes1;
	private JLabel lblPrepTime;
	private JSpinner spinnerPrep = new JSpinner();
	private JLabel lblminutes2;
	private JLabel lblQuantity;
	private JTextField txtQuantity = new JTextField();
	private JLabel lblProtein;
	private JSpinner spinnerProtein = new JSpinner();
	private JLabel lblCategory;
	private JSpinner spinnerCategory = new JSpinner();

	// Ingredient list on "View / Modify" card
	private JLabel lblIngredients;
	private JTextField ingredientField_1;
	private JTextField ingredientField_2;
	private JTextField ingredientField_3;
	private JTextField ingredientField_4;
	private JTextField ingredientField_5;
	private JTextField ingredientField_6;
	private JTextField ingredientField_7;
	private JTextField ingredientField_8;
	private JTextField ingredientField_9;
	private JTextField ingredientField_10;
	private JLabel lblIngredient1;
	private JLabel lblIngredient2;
	private JLabel lblIngredient3;
	private JLabel lblIngredient4;
	private JLabel lblIngredient5;
	private JLabel lblIngredient6;
	private JLabel lblIngredient7;
	private JLabel lblIngredient8;
	private JLabel lblIngredient9;
	private JLabel lblIngredient10;

	// Instructions list on "View / Modify" card
	private JLabel lblInstructions;
	private JTextField instructionField_1;
	private JTextField instructionField_2;
	private JTextField instructionField_3;
	private JTextField instructionField_4;
	private JTextField instructionField_5;
	private JTextField instructionField_6;
	private JTextField instructionField_7;
	private JTextField instructionField_8;
	private JTextField instructionField_9;
	private JTextField instructionField_10;
	private JLabel lblInstructions1;
	private JLabel lblInstructions2;
	private JLabel lblInstructions3;
	private JLabel lblInstructions4;
	private JLabel lblInstructions5;
	private JLabel lblInstructions6;
	private JLabel lblInstructions7;
	private JLabel lblInstructions8;
	private JLabel lblInstructions9;
	private JLabel lblInstructions10;

	// Items used to create the "View / Modify" card tool bar
	private JToolBar toolBar = new JToolBar();
	private JButton btnHome = new JButton("Meal Plan");
	private JButton btnSave = new JButton("Save");
	private JButton btnDelete = new JButton("Delete");
	private JButton btnNext = new JButton("Next");
	private JButton btnPrevious = new JButton("Previous");
	private JPanel cardPanel;
	private JToolBar toolBarCategory;
	private JCheckBox chckbxChicken;
	private JCheckBox chckbxBeef;
	private JCheckBox chckbxPork;
	private JCheckBox chckbxSeafood;
	private JCheckBox chckbxVegetable;
	private JCheckBox chckbxOtherProtein;
	private JCheckBox chckbxEntree;
	private JCheckBox chckbxAppetizer;
	private JCheckBox chckbxDessert;
	private JCheckBox chckbxOtherCategory;
	private JLabel lblProtien;
	private JLabel lblCategory_1;
	private JToolBar toolBarProtein;
	private JButton btnViewModify;
	private JButton btnAddNew;
	private JButton btnUpdatePlan;
	private JScrollPane scrollPane;
	private JTextArea txtOutputArea;

	/**
	 * Create the frame.
	 */
	public View() {
		// Create main cards and content pane
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 650, 430);
		setTitle("Recipe App");
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		cardPanel = new JPanel();
		contentPane.add(cardPanel, BorderLayout.CENTER);
		cardPanel.setLayout(cl_cardPanel);
		cardPanel.add(card1, "1");
		card1.setLayout(new BorderLayout(0, 0));
		cardPanel.add(card2, "2");
		card2.setLayout(null);
		scrollPane = new JScrollPane();
		card1.add(scrollPane, BorderLayout.CENTER);
		txtOutputArea = new JTextArea();
		txtOutputArea.setLineWrap(true);
		txtOutputArea.setWrapStyleWord(true);
		txtOutputArea.setText(
				"Select your desired proteins and categories then press \"Update Plan.\" This application will then generate a meal plan based on your choices, providing one randomly selected recipe per weekday according to your selections.");
		scrollPane.setViewportView(txtOutputArea);

		// Create main tool bar used throughout the application
		toolBar.setFloatable(false);
		contentPane.add(toolBar, BorderLayout.NORTH);
		btnHome.setToolTipText("View your meal plan.");
		toolBar.add(btnHome);
		btnViewModify = new JButton("View / Modify");
		btnViewModify.setToolTipText("View, modify or delete an existing recipe.");
		btnViewModify.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		toolBar.add(btnViewModify);
		btnAddNew = new JButton("Add New");
		btnAddNew.setToolTipText("Add a new recipe.");
		toolBar.add(btnAddNew);
		btnUpdatePlan = new JButton("Update Plan");
		btnUpdatePlan.setToolTipText(
				"Updates your meal plan with five random meals according to your selection of protein and category.");
		btnUpdatePlan.setBackground(UIManager.getColor("Button.light"));
		toolBar.add(btnUpdatePlan);
		btnSave.setToolTipText("Save your changes");
		btnSave.setBackground(UIManager.getColor("Button.light"));
		toolBar.add(btnSave);
		btnDelete.setToolTipText("Delete the currently selected recipe.");
		btnDelete.setBackground(UIManager.getColor("Button.light"));
		toolBar.add(btnDelete);
		btnPrevious.setToolTipText("Go to the previous recipe.");
		btnPrevious.setBackground(UIManager.getColor("Button.light"));
		toolBar.add(btnPrevious);
		btnNext.setToolTipText("Go to the next recipe.");
		btnNext.setBackground(UIManager.getColor("Button.light"));
		toolBar.add(btnNext);

		// Create and label tool bar and check boxes to choose category
		toolBarCategory = new JToolBar();
		toolBarCategory.setFloatable(false);
		card1.add(toolBarCategory, BorderLayout.SOUTH);
		lblCategory_1 = new JLabel(" Category:  ");
		lblCategory_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		toolBarCategory.add(lblCategory_1);
		chckbxEntree = new JCheckBox("Entree");
		toolBarCategory.add(chckbxEntree);
		chckbxAppetizer = new JCheckBox("Appetizer");
		toolBarCategory.add(chckbxAppetizer);
		chckbxDessert = new JCheckBox("Dessert");
		toolBarCategory.add(chckbxDessert);
		chckbxOtherCategory = new JCheckBox("Other");
		toolBarCategory.add(chckbxOtherCategory);

		// Create and label tool bar and check boxes to choose protein
		toolBarProtein = new JToolBar();
		toolBarProtein.setBackground(UIManager.getColor("CheckBox.background"));
		toolBarProtein.setFloatable(false);
		card1.add(toolBarProtein, BorderLayout.NORTH);
		lblProtien = new JLabel(" Protein:  ");
		toolBarProtein.add(lblProtien);
		lblProtien.setFont(new Font("Tahoma", Font.BOLD, 11));
		chckbxChicken = new JCheckBox("Chicken");
		toolBarProtein.add(chckbxChicken);
		chckbxBeef = new JCheckBox("Beef");
		toolBarProtein.add(chckbxBeef);
		chckbxPork = new JCheckBox("Pork");
		toolBarProtein.add(chckbxPork);
		chckbxSeafood = new JCheckBox("Seafood");
		toolBarProtein.add(chckbxSeafood);
		chckbxVegetable = new JCheckBox("Vegetable");
		toolBarProtein.add(chckbxVegetable);
		chckbxOtherProtein = new JCheckBox("Other");
		toolBarProtein.add(chckbxOtherProtein);

		// Create the recipe information label
		lblRecipeInformation = new JLabel("Recipe Information");
		lblRecipeInformation.setHorizontalAlignment(SwingConstants.TRAILING);
		lblRecipeInformation.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblRecipeInformation.setBounds(10, 11, 156, 14);
		card2.add(lblRecipeInformation);

		// Create and label the ID field
		lblId = new JLabel("ID");
		lblId.setHorizontalAlignment(SwingConstants.CENTER);
		lblId.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblId.setBounds(10, 39, 60, 14);
		card2.add(lblId);
		txtId.setEditable(false);
		txtId.setBounds(80, 36, 86, 20);
		card2.add(txtId);
		txtId.setColumns(10);

		// Create and label the title field
		txtTitle.setBounds(80, 67, 86, 20);
		txtTitle.setColumns(10);
		lblTitle = new JLabel("Title");
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitle.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblTitle.setBounds(10, 70, 60, 14);
		card2.add(lblTitle);
		card2.add(txtTitle);

		// Create and label the prep time field
		lblPrepTime = new JLabel("Prep Time");
		lblPrepTime.setHorizontalAlignment(SwingConstants.CENTER);
		lblPrepTime.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblPrepTime.setBounds(10, 101, 60, 14);
		card2.add(lblPrepTime);
		spinnerPrep.setModel(new SpinnerNumberModel(0, 0, 999, 1));
		spinnerPrep.setBounds(80, 98, 40, 20);
		card2.add(spinnerPrep);
		lblminutes1 = new JLabel("minutes");
		lblminutes1.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblminutes1.setBounds(126, 107, 40, 14);
		card2.add(lblminutes1);

		// Create and label the cook time field
		lblCookTime = new JLabel("Cook Time");
		lblCookTime.setHorizontalAlignment(SwingConstants.CENTER);
		lblCookTime.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblCookTime.setBounds(10, 132, 60, 14);
		card2.add(lblCookTime);
		spinnerCook.setModel(new SpinnerNumberModel(0, 0, 999, 1));
		spinnerCook.setBounds(80, 129, 40, 20);
		card2.add(spinnerCook);
		lblminutes2 = new JLabel("minutes");
		lblminutes2.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblminutes2.setBounds(126, 132, 40, 14);
		card2.add(lblminutes2);

		// Create and label the quantity field
		lblQuantity = new JLabel("Quantity");
		lblQuantity.setHorizontalAlignment(SwingConstants.CENTER);
		lblQuantity.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblQuantity.setBounds(10, 163, 60, 14);
		card2.add(lblQuantity);
		txtQuantity = new JTextField();
		txtQuantity.setBounds(80, 160, 86, 20);
		card2.add(txtQuantity);
		txtQuantity.setColumns(10);

		// Create and label the category spinner
		spinnerCategory = new JSpinner();
		spinnerCategory.setModel(new SpinnerListModel(new String[] { "Other", "Entree", "Appetizer", "Dessert" }));
		spinnerCategory.setBounds(80, 225, 86, 20);
		card2.add(spinnerCategory);
		lblCategory = new JLabel("Category");
		lblCategory.setHorizontalAlignment(SwingConstants.CENTER);
		lblCategory.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblCategory.setBounds(10, 228, 60, 14);
		card2.add(lblCategory);

		// Create and label the protein spinner
		lblProtein = new JLabel("Protein");
		lblProtein.setHorizontalAlignment(SwingConstants.CENTER);
		lblProtein.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblProtein.setBounds(10, 197, 60, 14);
		card2.add(lblProtein);
		spinnerProtein = new JSpinner();
		spinnerProtein.setModel(
				new SpinnerListModel(new String[] { "Other", "Beef", "Chicken", "Pork", "Seafood", "Vegetable" }));
		spinnerProtein.setBounds(80, 191, 86, 20);
		card2.add(spinnerProtein);

		// Create and label the fields used for ingredient input and output
		ingredientField_1 = new JTextField();
		ingredientField_1.setColumns(10);
		ingredientField_1.setBounds(216, 36, 155, 20);
		card2.add(ingredientField_1);
		ingredientField_2 = new JTextField();
		ingredientField_2.setColumns(10);
		ingredientField_2.setBounds(216, 67, 155, 20);
		card2.add(ingredientField_2);
		ingredientField_3 = new JTextField();
		ingredientField_3.setColumns(10);
		ingredientField_3.setBounds(216, 98, 155, 20);
		card2.add(ingredientField_3);
		ingredientField_4 = new JTextField();
		ingredientField_4.setColumns(10);
		ingredientField_4.setBounds(216, 129, 155, 20);
		card2.add(ingredientField_4);
		ingredientField_5 = new JTextField();
		ingredientField_5.setColumns(10);
		ingredientField_5.setBounds(216, 160, 155, 20);
		card2.add(ingredientField_5);
		ingredientField_6 = new JTextField();
		ingredientField_6.setColumns(10);
		ingredientField_6.setBounds(216, 191, 155, 20);
		card2.add(ingredientField_6);
		ingredientField_7 = new JTextField();
		ingredientField_7.setColumns(10);
		ingredientField_7.setBounds(216, 222, 155, 20);
		card2.add(ingredientField_7);
		ingredientField_8 = new JTextField();
		ingredientField_8.setColumns(10);
		ingredientField_8.setBounds(216, 253, 155, 20);
		card2.add(ingredientField_8);
		ingredientField_9 = new JTextField();
		ingredientField_9.setColumns(10);
		ingredientField_9.setBounds(216, 284, 155, 20);
		card2.add(ingredientField_9);
		ingredientField_10 = new JTextField();
		ingredientField_10.setColumns(10);
		ingredientField_10.setBounds(216, 315, 155, 20);
		card2.add(ingredientField_10);
		lblIngredients = new JLabel("Ingredients");
		lblIngredients.setHorizontalAlignment(SwingConstants.TRAILING);
		lblIngredients.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblIngredients.setBounds(176, 11, 195, 14);
		card2.add(lblIngredients);
		lblIngredient1 = new JLabel("1");
		lblIngredient1.setHorizontalAlignment(SwingConstants.CENTER);
		lblIngredient1.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblIngredient1.setBounds(176, 39, 30, 14);
		card2.add(lblIngredient1);
		lblIngredient2 = new JLabel("2");
		lblIngredient2.setHorizontalAlignment(SwingConstants.CENTER);
		lblIngredient2.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblIngredient2.setBounds(176, 70, 30, 14);
		card2.add(lblIngredient2);
		lblIngredient3 = new JLabel("3");
		lblIngredient3.setHorizontalAlignment(SwingConstants.CENTER);
		lblIngredient3.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblIngredient3.setBounds(176, 101, 30, 14);
		card2.add(lblIngredient3);
		lblIngredient4 = new JLabel("4");
		lblIngredient4.setHorizontalAlignment(SwingConstants.CENTER);
		lblIngredient4.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblIngredient4.setBounds(176, 132, 30, 14);
		card2.add(lblIngredient4);
		lblIngredient5 = new JLabel("5");
		lblIngredient5.setHorizontalAlignment(SwingConstants.CENTER);
		lblIngredient5.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblIngredient5.setBounds(176, 163, 30, 14);
		card2.add(lblIngredient5);
		lblIngredient6 = new JLabel("6");
		lblIngredient6.setHorizontalAlignment(SwingConstants.CENTER);
		lblIngredient6.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblIngredient6.setBounds(176, 194, 30, 14);
		card2.add(lblIngredient6);
		lblIngredient7 = new JLabel("7");
		lblIngredient7.setHorizontalAlignment(SwingConstants.CENTER);
		lblIngredient7.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblIngredient7.setBounds(176, 225, 30, 14);
		card2.add(lblIngredient7);
		lblIngredient8 = new JLabel("8");
		lblIngredient8.setHorizontalAlignment(SwingConstants.CENTER);
		lblIngredient8.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblIngredient8.setBounds(176, 256, 30, 14);
		card2.add(lblIngredient8);
		lblIngredient9 = new JLabel("9");
		lblIngredient9.setHorizontalAlignment(SwingConstants.CENTER);
		lblIngredient9.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblIngredient9.setBounds(176, 287, 30, 14);
		card2.add(lblIngredient9);
		lblIngredient10 = new JLabel("10");
		lblIngredient10.setHorizontalAlignment(SwingConstants.CENTER);
		lblIngredient10.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblIngredient10.setBounds(176, 318, 30, 14);
		card2.add(lblIngredient10);

		// Create and label the fields used for instruction input and output
		lblInstructions = new JLabel("Instructions");
		lblInstructions.setHorizontalAlignment(SwingConstants.TRAILING);
		lblInstructions.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblInstructions.setBounds(381, 12, 195, 14);
		card2.add(lblInstructions);
		instructionField_1 = new JTextField();
		instructionField_1.setColumns(10);
		instructionField_1.setBounds(421, 36, 155, 20);
		card2.add(instructionField_1);
		instructionField_2 = new JTextField();
		instructionField_2.setColumns(10);
		instructionField_2.setBounds(421, 67, 155, 20);
		card2.add(instructionField_2);
		instructionField_3 = new JTextField();
		instructionField_3.setColumns(10);
		instructionField_3.setBounds(421, 98, 155, 20);
		card2.add(instructionField_3);
		instructionField_4 = new JTextField();
		instructionField_4.setColumns(10);
		instructionField_4.setBounds(421, 129, 155, 20);
		card2.add(instructionField_4);
		instructionField_5 = new JTextField();
		instructionField_5.setColumns(10);
		instructionField_5.setBounds(421, 160, 155, 20);
		card2.add(instructionField_5);
		instructionField_6 = new JTextField();
		instructionField_6.setColumns(10);
		instructionField_6.setBounds(421, 191, 155, 20);
		card2.add(instructionField_6);
		instructionField_7 = new JTextField();
		instructionField_7.setColumns(10);
		instructionField_7.setBounds(421, 225, 155, 20);
		card2.add(instructionField_7);
		instructionField_8 = new JTextField();
		instructionField_8.setColumns(10);
		instructionField_8.setBounds(421, 253, 155, 20);
		card2.add(instructionField_8);
		instructionField_9 = new JTextField();
		instructionField_9.setColumns(10);
		instructionField_9.setBounds(421, 287, 155, 20);
		card2.add(instructionField_9);
		instructionField_10 = new JTextField();
		instructionField_10.setColumns(10);
		instructionField_10.setBounds(421, 315, 155, 20);
		card2.add(instructionField_10);
		lblInstructions1 = new JLabel("1");
		lblInstructions1.setHorizontalAlignment(SwingConstants.CENTER);
		lblInstructions1.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblInstructions1.setBounds(381, 39, 30, 14);
		card2.add(lblInstructions1);
		lblInstructions2 = new JLabel("2");
		lblInstructions2.setHorizontalAlignment(SwingConstants.CENTER);
		lblInstructions2.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblInstructions2.setBounds(381, 70, 30, 14);
		card2.add(lblInstructions2);
		lblInstructions3 = new JLabel("3");
		lblInstructions3.setHorizontalAlignment(SwingConstants.CENTER);
		lblInstructions3.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblInstructions3.setBounds(381, 101, 30, 14);
		card2.add(lblInstructions3);
		lblInstructions4 = new JLabel("4");
		lblInstructions4.setHorizontalAlignment(SwingConstants.CENTER);
		lblInstructions4.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblInstructions4.setBounds(381, 132, 30, 14);
		card2.add(lblInstructions4);
		lblInstructions5 = new JLabel("5");
		lblInstructions5.setHorizontalAlignment(SwingConstants.CENTER);
		lblInstructions5.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblInstructions5.setBounds(381, 163, 30, 14);
		card2.add(lblInstructions5);
		lblInstructions6 = new JLabel("6");
		lblInstructions6.setHorizontalAlignment(SwingConstants.CENTER);
		lblInstructions6.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblInstructions6.setBounds(381, 194, 30, 14);
		card2.add(lblInstructions6);
		lblInstructions7 = new JLabel("7");
		lblInstructions7.setHorizontalAlignment(SwingConstants.CENTER);
		lblInstructions7.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblInstructions7.setBounds(381, 228, 30, 14);
		card2.add(lblInstructions7);
		lblInstructions8 = new JLabel("8");
		lblInstructions8.setHorizontalAlignment(SwingConstants.CENTER);
		lblInstructions8.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblInstructions8.setBounds(381, 256, 30, 14);
		card2.add(lblInstructions8);
		lblInstructions9 = new JLabel("9");
		lblInstructions9.setHorizontalAlignment(SwingConstants.CENTER);
		lblInstructions9.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblInstructions9.setBounds(381, 287, 30, 14);
		card2.add(lblInstructions9);
		lblInstructions10 = new JLabel("10");
		lblInstructions10.setHorizontalAlignment(SwingConstants.CENTER);
		lblInstructions10.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblInstructions10.setBounds(381, 318, 30, 14);
		card2.add(lblInstructions10);

		// Set the initial view for when the application is first opened
		cl_cardPanel.show(cardPanel, "1");
		btnUpdatePlan.setVisible(true);
		btnSave.setVisible(false);
		btnDelete.setVisible(false);
		btnPrevious.setVisible(false);
		btnNext.setVisible(false);
	}

	/**
	 * @return The content pane.
	 */
	public JPanel getContentPane() {
		return contentPane;
	}

	/**
	 * @return The card that corresponds to the "Meal Plan tab.
	 */
	public JPanel getCard1() {
		return card1;
	}

	/**
	 * @return The card that corresponds to the "View / Modify" and "Add New" tabs.
	 */
	public JPanel getCard2() {
		return card2;
	}

	/**
	 * @return The text field used to display and select the recipe ID.
	 */
	public JTextField getTxtId() {
		return txtId;
	}

	/**
	 * @return The text field used to display and select the recipe title.
	 */
	public JTextField getTxtTitle() {
		return txtTitle;
	}

	/**
	 * @return The spinner used to display and select the recipe cook time.
	 */
	public JSpinner getSpinnerCook() {
		return spinnerCook;
	}

	/**
	 * @return The spinner used to display and select the recipe prep time.
	 */
	public JSpinner getSpinnerPrep() {
		return spinnerPrep;
	}

	/**
	 * @return The text field used to display and select the recipe quantity.
	 */
	public JTextField getTxtQuantity() {
		return txtQuantity;
	}

	/**
	 * @return The spinner used to display and select the recipe protein.
	 */
	public JSpinner getSpinnerProtein() {
		return spinnerProtein;
	}

	/**
	 * @return The spinner used to display and select the recipe category.
	 */
	public JSpinner getSpinnerCategory() {
		return spinnerCategory;
	}

	/**
	 * @return List of ingredients.
	 */
	public ArrayList<JTextField> getIngredients() {
		ArrayList<JTextField> ingredients = new ArrayList<JTextField>();
		ingredients.add(ingredientField_1);
		ingredients.add(ingredientField_2);
		ingredients.add(ingredientField_3);
		ingredients.add(ingredientField_4);
		ingredients.add(ingredientField_5);
		ingredients.add(ingredientField_6);
		ingredients.add(ingredientField_7);
		ingredients.add(ingredientField_8);
		ingredients.add(ingredientField_9);
		ingredients.add(ingredientField_10);
		return ingredients;
	}

	/**
	 * @return List of instructions.
	 */
	public ArrayList<JTextField> getInstructions() {
		ArrayList<JTextField> instructions = new ArrayList<JTextField>();
		instructions.add(instructionField_1);
		instructions.add(instructionField_2);
		instructions.add(instructionField_3);
		instructions.add(instructionField_4);
		instructions.add(instructionField_5);
		instructions.add(instructionField_6);
		instructions.add(instructionField_7);
		instructions.add(instructionField_8);
		instructions.add(instructionField_9);
		instructions.add(instructionField_10);
		return instructions;
	}

	/**
	 * @return The Home button.
	 */
	public JButton getBtnHome() {
		return btnHome;
	}

	/**
	 * @return The Save button.
	 */
	public JButton getBtnSave() {
		return btnSave;
	}

	/**
	 * @return The Delete button.
	 */
	public JButton getBtnDelete() {
		return btnDelete;
	}

	/**
	 * @return The Next button.
	 */
	public JButton getBtnNext() {
		return btnNext;
	}

	/**
	 * @return The Previous button.
	 */
	public JButton getBtnPrevious() {
		return btnPrevious;
	}

	/**
	 * Sets the the card to home layout
	 */
	public void setCardLayoutHome() {
		cl_cardPanel.show(cardPanel, "1");
	}

	/**
	 * Sets the the card to view/modify layout
	 */
	public void setCardLayoutViewModify() {
		cl_cardPanel.show(cardPanel, "2");
	}

	/**
	 * @return The View / Modify button.
	 */
	public JButton getBtnViewModify() {
		return btnViewModify;
	}

	/**
	 * @return The Add New button.
	 */
	public JButton getBtnAddNew() {
		return btnAddNew;
	}

	/**
	 * @return The Update Plan button.
	 */
	public JButton getBtnUpdatePlan() {
		return btnUpdatePlan;
	}

	/**
	 * @return The check box used to select Chicken.
	 */
	public JCheckBox getChckbxChicken() {
		return chckbxChicken;
	}

	/**
	 * @return The check box used to select Beef.
	 */
	public JCheckBox getChckbxBeef() {
		return chckbxBeef;
	}

	/**
	 * @return The check box used to select Pork.
	 */
	public JCheckBox getChckbxPork() {
		return chckbxPork;
	}

	/**
	 * @return The check box used to select Seafood.
	 */
	public JCheckBox getChckbxSeafood() {
		return chckbxSeafood;
	}

	/**
	 * @return The check box used to select Vegetable.
	 */
	public JCheckBox getChckbxVegetable() {
		return chckbxVegetable;
	}

	/**
	 * @return The check box used to select Other under protein.
	 */
	public JCheckBox getChckbxOtherProtein() {
		return chckbxOtherProtein;
	}

	/**
	 * @return The check box used to select Entree.
	 */
	public JCheckBox getChckbxEntree() {
		return chckbxEntree;
	}

	/**
	 * @return The check box used to select Appetizer.
	 */
	public JCheckBox getChckbxAppetizer() {
		return chckbxAppetizer;
	}

	/**
	 * @return The check box used to select Dessert.
	 */
	public JCheckBox getChckbxDessert() {
		return chckbxDessert;
	}

	/**
	 * @return The check box used to select Other under category.
	 */
	public JCheckBox getChckbxOtherCategory() {
		return chckbxOtherCategory;
	}

	/**
	 * @return The text output area for displaying the meal plan.
	 */
	public JTextArea getTxtOutputArea() {
		return txtOutputArea;
	}
}
