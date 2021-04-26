package application;

import java.io.FileNotFoundException;

/**
 * The Comeback Kids Recipe and Meal Planner program implements an application
 * that displays a recipe view and meal planner option.  Users can view, edit,
 * and delete recipes, which will be used for meal planning. 
 * 
 * @author 	Christopher Maxwell
 * @version 1.0
 * @since	2021-04-25
 */
public class Main {

	/**
	 * This is the main method which makes use of the Model, View and Controller classes.
	 * @param args Default parameter.
	 * @throws FileNotFoundException Used to throw an exception if the file is not
	 *                               found.
	 */
	public static void main(String[] args) throws FileNotFoundException {
		Model model = new Model();
		View view = new View();
		Controller controller = new Controller(model, view);
		controller.initialize();
	}
}
