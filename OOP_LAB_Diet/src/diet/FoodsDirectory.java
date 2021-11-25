//package diet;
//import java.util.*;
//
//final public class FoodsDirectory {
//	private static FoodsDirectory instance;
//	private Map<String, RawMaterials> rawmaterialsDirectory;
//	private Map<String, PackedProduct> packedproductsDirectory;
//	private Map<String, Recipe> recipesDirectory;
//	private Map<String, Menu> menuDirectory;
//	
//	private FoodsDirectory() {
//		rawmaterialsDirectory= new HashMap();
//		packedproductsDirectory = new HashMap();
//		recipesDirectory = new HashMap();
//		menuDirectory = new HashMap();
//	}
//	
//	static public FoodsDirectory getInstance() {
//		if(instance == null)
//			instance = new FoodsDirectory();
//
//		return instance;
//	}
//	
//	public boolean addRawMaterial(RawMaterials material) {
//		if(rawmaterialsDirectory.containsKey(material.name)) {
//			return false;
//		}else {
//			rawmaterialsDirectory.put(material.name,material);
//			return true;
//		}
//	}
//	
//	public boolean addPackedProduct(PackedProduct product) {
//		if(packedproductsDirectory.containsKey(product.name)) {
//			return false;
//		}else {
//			packedproductsDirectory.put(product.name,product);
//			return true;
//		}
//	}
//	
//	public Recipe addRecipe(Recipe product) {
//		if(recipesDirectory.containsKey(product.name)) {
//			return recipesDirectory.get(product.name);
//		}else {
//			recipesDirectory.put(product.name,product);
//			return recipesDirectory.get(product.name);
//		}
//	}
//	
//	public Menu addMenu(Menu product) {
//		if(menuDirectory.containsKey(product.name)) {
//			return menuDirectory.get(product.name);
//		}else {
//			menuDirectory.put(product.name,product);
//			return menuDirectory.get(product.name);
//		}
//	}
//
//
//	public RawMaterials getRawMaterial(String name) {
//		return rawmaterialsDirectory.get(name);
//	}
//	
//	public PackedProduct getPackedProduct(String name) {
//		return packedproductsDirectory.get(name);
//	}
//	
//	public Recipe getRecipe(String name) {
//		return recipesDirectory.get(name);
//	}
//	
//	public Menu getMenu(String name) {
//		return menuDirectory.get(name);
//	}
//	
//	public Collection<NutritionalElement> rawMaterials(){
//		List <NutritionalElement> temp = new ArrayList<>();
//		temp.addAll(rawmaterialsDirectory.values());
//		
//		temp.sort(new Comparator<NutritionalElement>() {
//
//			@Override
//			public int compare(NutritionalElement o1, NutritionalElement o2) {
//				// TODO Auto-generated method stub
//				return o1.getName().compareTo(o2.getName());
//			}
//		});
//		
////		System.out.println(temp.size());
////		for (NutritionalElement r : temp) {
////			System.out.println(r.getName());
////		}
//		return temp;
//	}
//	
//	public Collection<NutritionalElement> products(){
//		List <NutritionalElement> temp = new ArrayList<>();
//		temp.addAll(packedproductsDirectory.values());
//		
//		temp.sort(new Comparator<NutritionalElement>() {
//
//			@Override
//			public int compare(NutritionalElement o1, NutritionalElement o2) {
//				// TODO Auto-generated method stub
//				return o1.getName().compareTo(o2.getName());
//			}
//		});
//		
////		System.out.println(temp.size());
////		for (NutritionalElement r : temp) {
////			System.out.println(r.getName());
////		}
//		return temp;
//	}
//	
//	public Collection<NutritionalElement> recipes(){
//		List <NutritionalElement> temp = new ArrayList<>();
//		temp.addAll(rawmaterialsDirectory.values());
//		
//		temp.sort(new Comparator<NutritionalElement>() {
//
//			@Override
//			public int compare(NutritionalElement o1, NutritionalElement o2) {
//				// TODO Auto-generated method stub
//				return o1.getName().compareTo(o2.getName());
//			}
//		});
//		
////		System.out.println(temp.size());
////		for (NutritionalElement r : temp) {
////			System.out.println(r.getName());
////		}
//		return temp;
//	}
//}
