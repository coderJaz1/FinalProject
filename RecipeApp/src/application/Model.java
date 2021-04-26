package application;

import java.util.ArrayList;

/**
 * This is the Model class, used as part of the Model View
 * Controller (MVC) pattern.  This class represents an object
 * used to store recipe data for the application and updates the
 * controller if the data changes.
 */
public class Model {

	// Stores the recipes
	private ArrayList<Recipe> recipes = new ArrayList<Recipe>();

	/**
	 * Return a list of all recipes.
	 * 
	 * Requirements: 1.0.0, 1.0.1, 1.0.2
	 * 
	 * @return recipes
	 */
	public ArrayList<Recipe> getRecipes() {
		return recipes;
	}

	/**
	 * Add a recipe
	 * 
	 * Requirements: 1.0.0, 1.0.3
	 * 
	 * @param recipe The Recipe object to add.
	 */
	public void addRecipe(Recipe recipe) {
		this.recipes.add(recipe);
	}

	/**
	 * Remove a recipe at index
	 * 
	 * Requirements: 1.0.0, 1.0.9
	 * 
	 * @param index The index of the Recipe in the recipes ArrayList.
	 */
	public void removeRecipe(int index) {
		this.recipes.remove(index);
	}

}
