package diet;

import java.util.*;


/**
 * Facade class for the diet management.
 * It allows defining and retrieving raw materials and products.
 *
 */
public class Food {

	
	
	private Map<String, RawMaterials> rawmaterials = new HashMap();
	private Map<String, PackedProduct> packedproducts = new HashMap();;
	private Map<String, Recipe> recipes = new HashMap();
	private Map<String, Menu> menu = new HashMap();
	
	
	/**
	 * Define a new raw material.
	 * 
	 * The nutritional values are specified for a conventional 100g amount
	 * @param name 		unique name of the raw material
	 * @param calories	calories per 100g
	 * @param proteins	proteins per 100g
	 * @param carbs		carbs per 100g
	 * @param fat 		fats per 100g
	 */
	public void defineRawMaterial(String name,
									  double calories,
									  double proteins,
									  double carbs,
									  double fat){
		
		RawMaterials material  = new RawMaterials(name, calories, proteins, carbs, fat);
		rawmaterials.put(material.name,material);
	
	}
	
	/**
	 * Retrieves the collection of all defined raw materials
	 * 
	 * @return collection of raw materials though the {@link NutritionalElement} interface
	 */
	public Collection<NutritionalElement> rawMaterials(){
		
		List <NutritionalElement> temp = new ArrayList<>();
		temp.addAll(rawmaterials.values());
		
		temp.sort(new Comparator<NutritionalElement>() {

			@Override
			public int compare(NutritionalElement o1, NutritionalElement o2) {
				// TODO Auto-generated method stub
				return o1.getName().compareTo(o2.getName());
			}
		});

		return temp;
	}
	
	/**
	 * Retrieves a specific raw material, given its name
	 * 
	 * @param name  name of the raw material
	 * 
	 * @return  a raw material though the {@link NutritionalElement} interface
	 */
	public NutritionalElement getRawMaterial(String name){
		return rawmaterials.get(name);
	}

	/**
	 * Define a new packaged product.
	 * The nutritional values are specified for a unit of the product
	 * 
	 * @param name 		unique name of the product
	 * @param calories	calories for a product unit
	 * @param proteins	proteins for a product unit
	 * @param carbs		carbs for a product unit
	 * @param fat 		fats for a product unit
	 */
	public void defineProduct(String name,
								  double calories,
								  double proteins,
								  double carbs,
								  double fat){
		
		PackedProduct product = new PackedProduct(name, calories, proteins, carbs, fat);
		packedproducts.put(product.name,product);
	}
	
	/**
	 * Retrieves the collection of all defined products
	 * 
	 * @return collection of products though the {@link NutritionalElement} interface
	 */
	public Collection<NutritionalElement> products(){
		List <NutritionalElement> temp = new ArrayList<>();
		temp.addAll(packedproducts.values());
		
		temp.sort(new Comparator<NutritionalElement>() {

			@Override
			public int compare(NutritionalElement o1, NutritionalElement o2) {
				// TODO Auto-generated method stub
				return o1.getName().compareTo(o2.getName());
			}
		});

		return temp;
	}
	
	/**
	 * Retrieves a specific product, given its name
	 * @param name  name of the product
	 * @return  a product though the {@link NutritionalElement} interface
	 */
	public NutritionalElement getProduct(String name){
		return packedproducts.get(name);
	}
	
	/**
	 * Creates a new recipe stored in this Food container.
	 *  
	 * @param name name of the recipe
	 * 
	 * @return the newly created Recipe object
	 */
	public Recipe createRecipe(String name) {
		Recipe recipe = new Recipe(name, this);
		recipes.put(name,recipe);
		return recipes.get(recipe.name);	}
	
	/**
	 * Retrieves the collection of all defined recipes
	 * 
	 * @return collection of recipes though the {@link NutritionalElement} interface
	 */
	public Collection<NutritionalElement> recipes(){
		List <NutritionalElement> temp = new ArrayList<>();
		
		temp.addAll(recipes.values());
		
		temp.sort(new Comparator<NutritionalElement>() {

			@Override
			public int compare(NutritionalElement o1, NutritionalElement o2) {
				// TODO Auto-generated method stub
				return o1.getName().compareTo(o2.getName());
			}
		});
		
		return temp;
	}
	
	/**
	 * Retrieves a specific recipe, given its name
	 * 
	 * @param name  name of the recipe
	 * 
	 * @return  a recipe though the {@link NutritionalElement} interface
	 */
	public NutritionalElement getRecipe(String name){		
		return recipes.get(name);
	}
	
	/**
	 * Creates a new menu
	 * 
	 * @param name name of the menu
	 * 
	 * @return the newly created menu
	 */
	public Menu createMenu(String name) {
		Menu tmp = new Menu(name, this);
		menu.put(name,tmp);
		return menu.get(name);		
	}
	
	
	
}
