package diet;
import java.util.*;


/**
 * Represents a complete menu.
 * 
 * It can be made up of both packaged products and servings of given recipes.
 *
 */
public class Menu implements NutritionalElement, Comparable<Menu>{
	
	class ServingTuple {
		NutritionalElement food;
		Double qty;
		
		public ServingTuple(NutritionalElement food, Double qty) {
			this.food = food;
			this.qty = qty;
		}
	}

	
	Food food;
	String name;
	List<ServingTuple> servings = new ArrayList<ServingTuple>();
	
	public Menu(String name, Food food) {
		this.name = name;
		this.food = food;
	}

	/**
	 * Adds a given serving size of a recipe.
	 * 
	 * The recipe is a name of a recipe defined in the
	 * {@Link Food} in which this menu has been defined.
	 * 
	 * @param recipe the name of the recipe to be used as ingredient
	 * @param quantity the amount in grams of the recipe to be used
	 * @return the same Menu to allow method chaining
	 */
	public Menu addRecipe(String recipe, double quantity) {
		servings.add(new ServingTuple(food.getRecipe(recipe),quantity));
		
		return this;
	}

	/**
	 * Adds a unit of a packaged product.
	 * The product is a name of a product defined in the
	 * {@Link Food} in which this menu has been defined.
	 * 
	 * @param product the name of the product to be used as ingredient
	 * @return the same Menu to allow method chaining
	 */
	public Menu addProduct(String product) {
		servings.add(new ServingTuple(food.getProduct(product), food.getProduct(product).getCalories()));
		return this;
	}

	/**
	 * Name of the menu
	 */
	@Override
	public String getName() {
		return name;
	}

	/**
	 * Total KCal in the menu
	 */
	@Override
	public double getCalories() {
		double tot = 0;
		for (ServingTuple i : servings) {
			if ( i.food instanceof PackedProduct) {
				tot = tot + i.food.getCalories();
			}else {
				tot = tot + i.food.getCalories()*i.qty/100;
			}
		}
		System.out.println(tot);
		return tot;
	}

	/**
	 * Total proteins in the menu
	 */
	@Override
	public double getProteins() {
		double tot = 0;
		for (ServingTuple i : servings) {
			if ( i.food instanceof PackedProduct) {
				tot = tot + i.food.getProteins();
			}else {
				tot = tot + i.food.getProteins()*i.qty/100;
			}
		}
		
		return tot;
	}

	/**
	 * Total carbs in the menu
	 */
	@Override
	public double getCarbs() {
		double tot = 0;
		for (ServingTuple i : servings) {
			if ( i.food instanceof PackedProduct) {
				tot = tot + i.food.getCarbs();
			}else {
				tot = tot + i.food.getCarbs()*i.qty/100;
			}
		}
		
		return tot;
	}

	/**
	 * Total fats in the menu
	 */
	@Override
	public double getFat() {
		double tot = 0;
		for (ServingTuple i : servings) {
			if ( i.food instanceof PackedProduct) {
				tot = tot + i.food.getFat();
			}else {
				tot = tot + i.food.getFat()*i.qty/100;
			}
		}
		
		return tot;
	}

	/**
	 * Indicates whether the nutritional values returned by the other methods
	 * refer to a conventional 100g quantity of nutritional element,
	 * or to a unit of element.
	 * 
	 * For the {@link Menu} class it must always return {@code false}:
	 * nutritional values are provided for the whole menu.
	 * 
	 * @return boolean 	indicator
	 */
	@Override
	public boolean per100g() {
		// nutritional values are provided for the whole menu.
		return false;
	}

	@Override
	public int compareTo(Menu o) {
		// TODO Auto-generated method stub
		
		return this.name.compareTo(o.getName());
	}
	
	

}
