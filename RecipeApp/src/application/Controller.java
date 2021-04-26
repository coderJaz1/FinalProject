package application;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

import javax.swing.JOptionPane;
import javax.swing.JTextField;

/**
 * This is the Controller class, used as part of the Model View
 * Controller (MVC) pattern.  This class controls the flow of
 * the data into model objects and updates the view when changes
 * occur.
 * 
 * Requirements: 1.0.0, 2.0.0
 */
public class Controller {
	private Model model;
	private View view;
	private int index = 0;

/**
 * Sets instance of Controller to interact with the model and view.
 * @param model Instance of the model object
 * @param view	Instance of the view
 */
	public Controller(Model model, View view) {
		this.model = model;
		this.view = view;
	}

	/**
	 * Sets ActionListens for the various buttons in our View.
	 * 
	 * Requirements: 1.0.1, 1.0.3, 1.0.5, 1.0.6, 1.0.7, 1.0.8, 1.0.9,
	 * 1.0.10, 1.0.11
	 */
	public void initialize() {
		readFile();
		view.setVisible(true);

		view.getBtnHome().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				view.setCardLayoutHome();
				view.getBtnUpdatePlan().setVisible(true);
				view.getBtnSave().setVisible(false);
				view.getBtnDelete().setVisible(false);
				view.getBtnPrevious().setVisible(false);
				view.getBtnNext().setVisible(false);
			}
		});

		// Logic for the "View / Modify" button
		view.getBtnViewModify().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				view.setCardLayoutViewModify();
				view.getBtnUpdatePlan().setVisible(false);
				view.getBtnSave().setVisible(true);
				view.getBtnDelete().setVisible(true);
				view.getBtnPrevious().setVisible(true);
				view.getBtnNext().setVisible(true);
				updateViewModify();
			}
		});

		// Logic for the "Add New" button
		view.getBtnAddNew().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				// Change the layout and buttons
				view.setCardLayoutViewModify();
				view.getBtnUpdatePlan().setVisible(false);
				view.getBtnSave().setVisible(true);
				view.getBtnDelete().setVisible(false);
				view.getBtnPrevious().setVisible(false);
				view.getBtnNext().setVisible(false);

				// Clear fields if filled, set new automatic ID for the new Recipe
				clearFields();
				Integer high = 0;
				for (Recipe r : model.getRecipes()) {
					if (r.getId() > high) {
						high = r.getId();
					}
				}
				high++;
				view.getTxtId().setText(high.toString());
			}
		});

		// Logic for the "Save" button
		view.getBtnSave().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (validate()) {
					String temp = view.getTxtId().getText();
					Integer id = Integer.parseInt(temp);
					boolean found = false;

					// Get user input from the "Add New" fields
					Recipe currentRecipe = null;
					String title = view.getTxtTitle().getText();
					Integer prepTime = (Integer) view.getSpinnerPrep().getValue();
					Integer cookTime = (Integer) view.getSpinnerCook().getValue();
					String quantity = view.getTxtQuantity().getText();
					ArrayList<JTextField> ingredients = view.getIngredients();
					ArrayList<String> ingredientList = new ArrayList<String>();
					for (JTextField ingredient : ingredients) {
						if (!ingredient.getText().equals("")) {
							ingredientList.add(ingredient.getText().replaceAll(";", ","));
						}
					}
					ArrayList<JTextField> instructions = view.getInstructions();
					ArrayList<String> instructionList = new ArrayList<String>();
					for (JTextField instruction : instructions) {
						if (!instruction.getText().equals("")) {
							instructionList.add(instruction.getText().replaceAll(";", ""));
						}
					}
					String protein = view.getSpinnerProtein().getValue().toString();
					String category = view.getSpinnerCategory().getValue().toString();

					// See if we are editing an existing Recipe or adding a new one
					for (Recipe r : model.getRecipes()) {
						if (r.getId() == id) {
							currentRecipe = r;
							found = true;
						}
					}
					if (found) {
						// Editing existing Recipe
						currentRecipe.setTitle(title);
						currentRecipe.setPrepTime(prepTime);
						currentRecipe.setCookTime(cookTime);
						currentRecipe.setQuantity(quantity);
						currentRecipe.setIngredientList(ingredientList);
						currentRecipe.setInstructionList(instructionList);
						currentRecipe.setProtein(protein);
						currentRecipe.setCategory(category);
					} else {
						// Adding new Recipe
						currentRecipe = new Recipe(id, title, prepTime, cookTime, quantity, ingredientList,
								instructionList, protein, category);
						model.addRecipe(currentRecipe);

						// Clear fields if filled, set new automatic ID for the new Recipe
						clearFields();
						Integer high = 0;
						for (Recipe r : model.getRecipes()) {
							if (r.getId() > high) {
								high = r.getId();
							}
						}
						high++;
						view.getTxtId().setText(high.toString());
					}

					// Give pop up confirmation
					JOptionPane.showMessageDialog(view, "Save successful.", "Confirmation",
							JOptionPane.INFORMATION_MESSAGE);

					// Save changes to TSV file for data persistence
					writeFile();
				}
			}
		});

		// Logic for the "Delete" button
		view.getBtnDelete().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				// Give pop up warning
				int choice = JOptionPane.showConfirmDialog(view, "Do you really want to delete this recipe?", "Warning",
						JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);

				// 0 represents the "yes" choice
				if (choice == 0) {
					int length = model.getRecipes().size();

					// Don't let user delete their last recipe
					if (length > 1) {

						// Delete the recipe from the model
						model.removeRecipe(index);

						// Update the view
						if (index < length - 1) {
							updateViewModify();
						} else {
							index--;
							updateViewModify();
						}

						// Give pop up confirmation
						JOptionPane.showMessageDialog(view, "Deletion successful.", "Confirmation",
								JOptionPane.INFORMATION_MESSAGE);
					} else {
						JOptionPane.showMessageDialog(view, "You must keep at least one recipe!", "Error",
								JOptionPane.ERROR_MESSAGE);
					}

					// Save changes to TSV file for data persistence
					writeFile();
				}
			}
		});

		// Logic for the "Next" button
		view.getBtnNext().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (index < model.getRecipes().size() - 1) {
					index++;
					updateViewModify();
				}
			}
		});

		// Logic for the "Previous" button
		view.getBtnPrevious().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (index > 0) {
					index--;
					updateViewModify();
				}
			}
		});

		// Logic for the "Update Plan" button
		view.getBtnUpdatePlan().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				// Get selected proteins from our check boxes
				ArrayList<String> selectedProteins = new ArrayList<String>();
				if (view.getChckbxChicken().isSelected()) {
					selectedProteins.add("Chicken");
				}
				if (view.getChckbxBeef().isSelected()) {
					selectedProteins.add("Beef");
				}
				if (view.getChckbxPork().isSelected()) {
					selectedProteins.add("Pork");
				}
				if (view.getChckbxSeafood().isSelected()) {
					selectedProteins.add("Seafood");
				}
				if (view.getChckbxVegetable().isSelected()) {
					selectedProteins.add("Vegetable");
				}
				if (view.getChckbxOtherProtein().isSelected()) {
					selectedProteins.add("Other");
				}

				// Get selected categories from our check boxes
				ArrayList<String> selectedCategories = new ArrayList<String>();
				if (view.getChckbxEntree().isSelected()) {
					selectedCategories.add("Entree");
				}
				if (view.getChckbxAppetizer().isSelected()) {
					selectedCategories.add("Appetizer");
				}
				if (view.getChckbxDessert().isSelected()) {
					selectedCategories.add("Dessert");
				}
				if (view.getChckbxOtherCategory().isSelected()) {
					selectedCategories.add("Other");
				}

				// Find valid recipes
				ArrayList<Recipe> validRecipes = new ArrayList<Recipe>();
				for (Recipe r : model.getRecipes()) {
					if (selectedProteins.contains(r.getProtein()) && selectedCategories.contains(r.getCategory())) {
						validRecipes.add(r);
					}
				}

				// Proceed only if at least one protein and at least one category selected
				if (selectedProteins.size() > 0 && selectedCategories.size() > 0) {
					// If there aren't at least five valid recipes, the action fails
					if (validRecipes.size() < 5) {
						view.getTxtOutputArea().setText(
								"Please expand your selection or add more recipes then press \"Update Plan.\"");
						JOptionPane.showMessageDialog(view,
								"Could not find five valid recipes.\nPlease expand your selection or add more recipes.",
								"Error", JOptionPane.ERROR_MESSAGE);
					} else {

						// Display 5 random recipes
						Collections.shuffle(validRecipes);
						StringBuilder sb = new StringBuilder();
						for (int i = 0; i < 5; i++) {
							switch (i) {
							case 0:
								sb.append("********************\n");
								sb.append("MONDAY\n");
								sb.append("********************\n\n");
								break;
							case 1:
								sb.append("\n********************\n");
								sb.append("TUESDAY\n");
								sb.append("********************\n\n");
								break;
							case 2:
								sb.append("\n********************\n");
								sb.append("WEDNESDAY\n");
								sb.append("********************\n\n");
								break;
							case 3:
								sb.append("\n********************\n");
								sb.append("THURSDAY\n");
								sb.append("********************\n\n");
								break;
							case 4:
								sb.append("\n********************\n");
								sb.append("FRIDAY\n");
								sb.append("********************\n\n");
								break;
							}
							sb.append(validRecipes.get(i).toString() + "\n");
						}
						view.getTxtOutputArea().setText(sb.toString());

						// Give pop up confirmation
						JOptionPane.showMessageDialog(view, "Plan updated successfully.", "Confirmation",
								JOptionPane.INFORMATION_MESSAGE);
					}
				} else {

					// Don't display recipes if user hasn't selected both protein and category
					view.getTxtOutputArea().setText(
							"Please select at least one protein and at least one category then press \\\"Update Plan.\\\".");
					JOptionPane.showMessageDialog(view,
							"You must select at least one protein and at least one category.", "Error",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		});
	}

	/**
	 * Reads a Tab Separated Value (TSV) file into the Model.
	 * Requirement 1.0.2
	 */
	public void readFile() {
		try (Scanner fs = new Scanner(new File("recipes.txt"))) {
			fs.nextLine();
			while (fs.hasNextLine()) {
				String line = fs.nextLine();
				String[] values = line.split("\t");
				int id = Integer.parseInt(values[0].trim());
				String name = values[1].trim();
				int prepTime = Integer.parseInt(values[2].trim());
				int bakeTime = Integer.parseInt(values[3].trim());
				String quantity = values[4].trim();
				String[] ingredientsValues = values[5].trim().split(";");
				ArrayList<String> ingredients = new ArrayList<String>();
				for (String s : ingredientsValues) {
					ingredients.add(s);
				}
				String[] instructionsValues = values[6].trim().split(";");
				ArrayList<String> instructions = new ArrayList<String>();
				for (String s : instructionsValues) {
					s.trim();
					instructions.add(s);
				}
				String protein = values[7].trim();
				String category = values[8].trim();
				Recipe recipe = new Recipe(id, name, prepTime, bakeTime, quantity, ingredients, instructions, protein,
						category);
				model.addRecipe(recipe);
			}
			fs.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Writes the Model to a Tab Separated Value (TSV) file for data persistence.
	 * 
	 * Requirements: 1.0.8
	 */
	public void writeFile() {
		File recipeOld = new File("recipes.txt");
		recipeOld.delete();

		try (FileWriter fw = new FileWriter(new File("recipes.txt"))) {
			fw.write("ID\tName\tPrepTime\tBakeTime\tQuantity\tIngredients\tInstructions\tProtein\tCategory");
			for (Recipe r : model.getRecipes()) {
				fw.write("\n" + r.getTabbed());
			}
			fw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	/**
	 * Updates the "View / Modify" screen.
	 * 
	 * Requirement: 1.0.6
	 */
	private void updateViewModify() {
		Recipe currentRecipe = model.getRecipes().get(index);

		// Update recipe information section
		view.getTxtId().setText(String.valueOf(currentRecipe.getId()));
		view.getTxtTitle().setText(currentRecipe.getTitle());
		view.getSpinnerCook().setValue(currentRecipe.getCookTime());
		view.getSpinnerPrep().setValue(currentRecipe.getPrepTime());
		view.getTxtQuantity().setText(currentRecipe.getQuantity());
		view.getSpinnerProtein().setValue(currentRecipe.getProtein());
		view.getSpinnerCategory().setValue(currentRecipe.getCategory());

		// Update ingredients
		ArrayList<JTextField> ingredients = view.getIngredients();
		ArrayList<String> ingredientList = currentRecipe.getIngredientList();
		int i;
		for (i = 0; i < ingredientList.size(); i++) {
			if (i < 10) {
				ingredients.get(i).setText(ingredientList.get(i));
			}
		}
		while (i < 10) {
			ingredients.get(i).setText("");
			i++;
		}

		// Update instructions
		ArrayList<JTextField> instructions = view.getInstructions();
		ArrayList<String> instructionList = currentRecipe.getInstructionList();
		int j;
		for (j = 0; j < instructionList.size(); j++) {
			if (j < 10) {
				instructions.get(j).setText(instructionList.get(j));
			}
		}
		while (j < 10) {
			instructions.get(j).setText("");
			j++;
		}
	}

	/**
	 * Clears the on screen fields in the "view / modify" or "add new" screens.
	 */
	private void clearFields() {

		// Clear recipe information section
		view.getTxtId().setText("");
		view.getTxtTitle().setText("");
		view.getSpinnerCook().setValue(0);
		view.getSpinnerPrep().setValue(0);
		view.getTxtQuantity().setText("");
		view.getSpinnerProtein().setValue("Other");
		view.getSpinnerCategory().setValue("Other");

		// Clear ingredients
		ArrayList<JTextField> ingredients = view.getIngredients();
		for (int i = 0; i < 10; i++) {
			if (i < 10) {
				ingredients.get(i).setText("");
			}
		}

		// Clear instructions
		ArrayList<JTextField> instructions = view.getInstructions();
		for (int j = 0; j < 10; j++) {
			if (j < 10) {
				instructions.get(j).setText("");
			}
		}
	}

	/**
	 * Validates the data fields. Ensures title and quantity are not blank and that
	 * at least one ingredient and instruction are present. Sorts display fields.
	 * 
	 * Requirements: 1.0.6
	 * 
	 * @return True if data is valid, false if not.
	 */
	private boolean validate() {
		ArrayList<JTextField> ingredientList = view.getIngredients();
		ArrayList<JTextField> instructionList = view.getInstructions();
		ArrayList<String> ingredients = new ArrayList<String>();
		ArrayList<String> instructions = new ArrayList<String>();

		for (JTextField ing : ingredientList) {
			System.out.print(ing.getText());
			if (!ing.getText().equals("")) {
				ingredients.add(ing.getText());
			}
		}
		for (JTextField inst : instructionList) {
			System.out.print(inst.getText());
			if (!inst.getText().equals("")) {
				instructions.add(inst.getText());
			}
		}

		// Fail if there isn't at least one ingredient and at least one instruction
		if (ingredients.size() == 0 || instructions.size() == 0) {
			JOptionPane.showMessageDialog(view, "You must have at least one ingredient and at least one instruction!",
					"Error", JOptionPane.ERROR_MESSAGE);
			return false;
		} else {
			// Clear ingredients
			for (int i = 0; i < 10; i++) {
				if (i < 10) {
					ingredientList.get(i).setText("");
				}
			}

			// Clear instructions
			for (int j = 0; j < 10; j++) {
				if (j < 10) {
					instructionList.get(j).setText("");
				}
			}

			// Set values
			for (int i = 0; i < ingredients.size(); i++) {
				ingredientList.get(i).setText(ingredients.get(i));
			}
			for (int i = 0; i < instructions.size(); i++) {
				instructionList.get(i).setText(instructions.get(i));
			}
		}
		// Fail if there isn't a title
		if (view.getTxtTitle().getText().equals("")) {
			JOptionPane.showMessageDialog(view, "You must set the title!", "Error", JOptionPane.ERROR_MESSAGE);
			return false;
		}
		// Fail if there isn't a quantity
		if (view.getTxtQuantity().getText().equals("")) {
			JOptionPane.showMessageDialog(view, "You must set the quantity!", "Error", JOptionPane.ERROR_MESSAGE);
			return false;
		}
		return true;
	}
}
