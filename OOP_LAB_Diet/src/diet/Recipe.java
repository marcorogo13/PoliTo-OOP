package diet;

import java.util.*;

/**
 * Represents a recipe of the diet.
 * 
 * A recipe consists of a a set of ingredients that are given amounts of raw materials.
 * The overall nutritional values of a recipe can be computed
 * on the basis of the ingredients' values and are expressed per 100g
 * 
 *
 */
public class Recipe implements NutritionalElement {
	
	public class IngredientTuple {
		NutritionalElement food;
		Double qty;
		
		public IngredientTuple(NutritionalElement food, Double qty) {
			this.food = food;
			this.qty = qty;
		}
		
	}
	Food food;
	String name;	
	List <IngredientTuple> ingredientsUsed = new ArrayList<>();
	
	public Recipe(String name, Food food) {
		this.name = name;
		this.food = food;
	}

	
	/**
	 * Adds a given quantity of an ingredient to the recipe.
	 * The ingredient is a raw material.
	 * 
	 * @param material the name of the raw material to be used as ingredient
	 * @param quantity the amount in grams of the raw material to be used
	 * @return the same Recipe object, it allows method chaining.
	 */
	public Recipe addIngredient(String material, double quantity) {
		IngredientTuple tmp = new IngredientTuple(food.getRawMaterial(material),quantity);
		ingredientsUsed.add(tmp);		
		return this;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public double getCalories() {
		
		double tot = 0;
		double toReturn = 0;
		for (IngredientTuple i : ingredientsUsed) {
			tot = tot + i.qty;
			toReturn = toReturn + i.food.getCalories() * i.qty;
		}
		
		return toReturn/tot;
	}

	@Override
	public double getProteins() {
		double tot = 0;
		double toReturn = 0;
		for (IngredientTuple i : ingredientsUsed) {
			tot = tot + i.qty;
			toReturn = toReturn + i.food.getProteins() * i.qty;
		}
		
		return toReturn/tot;
	}

	@Override
	public double getCarbs() {
		double tot = 0;
		double toReturn = 0;
		for (IngredientTuple i : ingredientsUsed) {
			tot = tot + i.qty;
			toReturn = toReturn + i.food.getCarbs() * i.qty;
		}
		
		return toReturn/tot;
	}

	@Override
	public double getFat() {
		double tot = 0;
		double toReturn = 0;
		for (IngredientTuple i : ingredientsUsed) {
			tot = tot + i.qty;
			toReturn = toReturn + i.food.getFat() * i.qty;
		}
		
		return toReturn/tot;
	}

	/**
	 * Indicates whether the nutritional values returned by the other methods
	 * refer to a conventional 100g quantity of nutritional element,
	 * or to a unit of element.
	 * 
	 * For the {@link Recipe} class it must always return {@code true}:
	 * a recipe expresses nutritional values per 100g
	 * 
	 * @return boolean indicator
	 */
	@Override
	public boolean per100g() {
		return true;
	}
	
	
	/**
	 * Returns the ingredients composing the recipe.
	 * 
	 * A string that contains all the ingredients, one per per line, 
	 * using the following format:
	 * {@code "Material : ###.#"} where <i>Material</i> is the name of the 
	 * raw material and <i>###.#</i> is the relative quantity. 
	 * 
	 * Lines are all terminated with character {@code '\n'} and the ingredients 
	 * must appear in the same order they have been added to the recipe.
	 */
	@Override
	public String toString() {
		StringBuilder toReturn = new StringBuilder();
		for (IngredientTuple i : ingredientsUsed) {
			toReturn.append(i.food.getName()).append(" : ").append(i.qty).append('\n');
		}
		return toReturn.toString();
	}
}

