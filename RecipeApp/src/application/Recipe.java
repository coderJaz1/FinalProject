package application;

import java.util.ArrayList;

/**
 * Holds a recipe data object for use in the model.
 * Requirements: 1.0.0, 1.0.2
 */
public class Recipe {
	private Integer id;
	private String title;
	private int prepTime;
	private int cookTime;
	private String quantity;
	private ArrayList<String> ingredients;
	private ArrayList<String> instructions;
	private String protein;
	private String category;

	/**
	 * Constructor for a Recipe object.
	 * 
	 * @param id           The ID of the Recipe.
	 * @param title        The title of the Recipe.
	 * @param prepTime     The prep time of the Recipe, in minutes.
	 * @param cookTime     The cook time of the Recipe, in minutes.
	 * @param quantity     The quantity made of the recipe (example: 3 servings.)
	 * @param ingredients  A list of ingredients used by the Recipe.
	 * @param instructions A list of instructions used to create the Recipe.
	 * @param protein      The type of protein (Chicken, Beef, Pork, Seafood,
	 *                     Vegetable, Other) used in the Recipe.
	 * @param category     The category (Appetizer, Entree, Dessert, Other) of the
	 *                     Recipe.
	 */
	public Recipe(int id, String title, int prepTime, int cookTime, String quantity, ArrayList<String> ingredients,
			ArrayList<String> instructions, String protein, String category) {
		this.id = id;
		this.title = title;
		this.prepTime = prepTime;
		this.cookTime = cookTime;
		this.quantity = quantity;
		this.ingredients = ingredients;
		this.instructions = instructions;
		this.protein = protein;
		this.category = category;
	}

	/**
	 * Return the ID.
	 * 
	 * @return id
	 */
	public int getId() {
		return id;
	}

	/**
	 * Set the ID.
	 * 
	 * @param id The ID of the recipe.
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * Return the title.
	 * 
	 * @return title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * Set the title.
	 * 
	 * @param title The title of the recipe.
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * Return the cook time.
	 * 
	 * @return cookTime
	 */
	public int getCookTime() {
		return cookTime;
	}

	/**
	 * Set the cook time.
	 * 
	 * @param cookTime The cook time of the Recipe, in minutes.
	 */
	public void setCookTime(int cookTime) {
		this.cookTime = cookTime;
	}

	/**
	 * Return the category.
	 * 
	 * @return category
	 */
	public String getCategory() {
		return category;
	}

	/**
	 * Set the category.
	 * 
	 * @param category The category (Appetizer, Entree, Dessert, Other) of the
	 *                 Recipe.
	 */
	public void setCategory(String category) {
		this.category = category;
	}

	/**
	 * Return the protein.
	 * 
	 * @return protein
	 */
	public String getProtein() {
		return protein;
	}

	/**
	 * Set the protein.
	 * 
	 * @param protein The type of protein (Chicken, Beef, Pork, Seafood, Vegetable,
	 *                Other) used in the Recipe.
	 */
	public void setProtein(String protein) {
		this.protein = protein;
	}

	/**
	 * Return the prep time.
	 * 
	 * @return prepTime
	 */
	public int getPrepTime() {
		return prepTime;
	}

	/**
	 * Set the prep time.
	 * 
	 * @param prepTime The prep time for the recipe, in minutes.
	 */
	public void setPrepTime(int prepTime) {
		this.prepTime = prepTime;
	}

	/**
	 * Return the ingredient list.
	 * 
	 * @return ingredientList
	 */
	public ArrayList<String> getIngredientList() {
		return ingredients;
	}

	/**
	 * Set the ingredient list.
	 * 
	 * @param ingredientList A list of ingredients used by the Recipe.
	 */
	public void setIngredientList(ArrayList<String> ingredientList) {
		this.ingredients = ingredientList;
	}

	/**
	 * Return the instruction list.
	 * 
	 * @return instructionList
	 */
	public ArrayList<String> getInstructionList() {
		return instructions;
	}

	/**
	 * Set the instruction list.
	 * 
	 * @param instructions A list of instructions used to create the Recipe.
	 */
	public void setInstructionList(ArrayList<String> instructions) {
		this.instructions = instructions;
	}

	/**
	 * Return the quantity.
	 * 
	 * @return quantity
	 */
	public String getQuantity() {
		return quantity;
	}

	/**
	 * Set the quantity.
	 * 
	 * @param quantity The quantity made of the recipe (example: 3 servings.)
	 */
	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}

	/**
	 * Returns the recipe in a format suitable for storage in a Tab Separated Value
	 * (TSV) formatted file.
	 * 
	 * @return String representing a recipe in TSV format.
	 */
	public String getTabbed() {
		StringBuilder sb = new StringBuilder();
		sb.append(id + "\t" + title + "\t" + prepTime + "\t" + cookTime + "\t" + quantity);
		boolean firstIngredient = true;
		for (String s : ingredients) {
			if (firstIngredient) {
				sb.append("\t" + s);
				firstIngredient = false;
			} else {
				sb.append(";" + s);
			}
		}
		boolean firstInstruction = true;
		for (String s : instructions) {
			if (firstInstruction) {
				sb.append("\t" + s);
				firstInstruction = false;
			} else {
				sb.append(";" + s);
			}
		}
		sb.append("\t" + protein + "\t" + category);
		return sb.toString();
	}

	/**
	 * Returns the recipe details in string form.
	 */
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("Title:\n" + title + "\n\nPrep Time:\n" + prepTime + " minutes \n\nBake Time:\n" + cookTime
				+ " minutes \n\nQuantity:\n" + quantity + "\n\nIngredients:\n");
		for (String s : ingredients) {
			sb.append("\u2022 " + s + "\n");
		}
		sb.append("\nInstructions:\n");
		boolean first = true;
		for (String s : instructions) {
			if (first) {
				sb.append("\u2022 " + s);
				first = false;
			} else {
				sb.append("\n\u2022 " + s);
			}
		}
		return sb.toString();
	}

}
