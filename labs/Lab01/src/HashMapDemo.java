import java.util.HashMap;
import java.util.Set;

public class HashMapDemo {
	
public static void main(String[] args) {
		
		HashMap<String,String> favFoodMap = new HashMap<>();
		//add the elements with key first and then value
		favFoodMap.put("Kathleen", "tacos");
		favFoodMap.put("Steve", "ribs");
		favFoodMap.put("John", "burgers");
		favFoodMap.put("Michelle", "pasta");
		favFoodMap.put("Patrick","meatballs");
		favFoodMap.put("AJ", "pizza");
		favFoodMap.put("Doug","pizza");
		favFoodMap.put("Kent", "pasta");
		
		Set<String> keys = favFoodMap.keySet();
		for(String key: keys) {
			//use the key to get the vale of favorite food
			//key is person name
			//value is favorite food
			String food = favFoodMap.get(key);
			System.out.println(key+ " fav food is "+food);
		}
		/*
		 * Problem: Count the number of times each food item appears in the array
		 * In array pizza and pasta appear twice, tacos once
		 * Store food item name as key and number of occurrences as value
		 * Map will look like: {pasta: 2, pizza:2, meatballs:1, ribs:1, burgers:1, tacos:1};
		 */
		//create new map that keeps count of fav foods
		String[] foods = {"pasta","meatballs","pasta","ribs","burgers","pizza","pizza","tacos"};
		HashMap<String, Integer> foodMap = new HashMap<>();
		//Use an iterator to iterate through array of food items
		for(String food: foods) {
			//if foodMap contains the food item increment the count in map
			if(foodMap.containsKey(food)) {
				int num = foodMap.get(food);
				foodMap.put(food, num+1);
			} else {
				//if food item is not in map yet, add it with value 1
				foodMap.put(food, 1);
			}
		}
		System.out.println(foodMap);	
	}



}
