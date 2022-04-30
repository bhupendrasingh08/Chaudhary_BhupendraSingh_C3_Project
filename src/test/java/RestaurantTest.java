import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

class RestaurantTest {
    Restaurant restaurant;
    
    //REFACTOR ALL THE REPEATED LINES OF CODE
    @BeforeEach
    public void setup() {
    	LocalTime openingTime = LocalTime.parse("10:30:00");
        LocalTime closingTime = LocalTime.parse("22:00:00");
    	restaurant =new Restaurant("Amelie's cafe","Chennai",openingTime,closingTime);
    	restaurant.addToMenu("Sweet corn soup",119);
        restaurant.addToMenu("Vegetable lasagne", 269);
    }
    
    //>>>>>>>>>>>>>>>>>>>>>>>>>OPEN/CLOSED<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
    //-------FOR THE 2 TESTS BELOW, YOU MAY USE THE CONCEPT OF MOCKING, IF YOU RUN INTO ANY TROUBLE
    @Test
    public void is_restaurant_open_should_return_true_if_time_is_between_opening_and_closing_time(){
    	//WRITE UNIT TEST CASE HERE
    	Boolean isOpen = restaurant.isRestaurantOpen();
    	assertTrue(isOpen);
    }

    @Test
    public void is_restaurant_open_should_return_false_if_time_is_outside_opening_and_closing_time(){
        //WRITE UNIT TEST CASE HERE
    	Restaurant mockRestaurant = Mockito.spy(restaurant);
    	Mockito.when(mockRestaurant.getCurrentTime()).thenReturn(LocalTime.parse("09:00:00"));
    	Boolean isClose = mockRestaurant.isRestaurantOpen();
    	assertFalse(isClose);
    }

    //<<<<<<<<<<<<<<<<<<<<<<<<<OPEN/CLOSED>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>


    //>>>>>>>>>>>>>>>>>>>>>>>>>>>MENU<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
    @Test
    public void adding_item_to_menu_should_increase_menu_size_by_1(){
        int initialMenuSize = restaurant.getMenu().size();
        restaurant.addToMenu("Sizzling brownie",319);
        assertEquals(initialMenuSize+1,restaurant.getMenu().size());
    }
    @Test
    public void removing_item_from_menu_should_decrease_menu_size_by_1() throws itemNotFoundException {
        int initialMenuSize = restaurant.getMenu().size();
        restaurant.removeFromMenu("Vegetable lasagne");
        assertEquals(initialMenuSize-1,restaurant.getMenu().size());
    }
    @Test
    public void removing_item_that_does_not_exist_should_throw_exception() {
        assertThrows(itemNotFoundException.class,
                ()->restaurant.removeFromMenu("French fries"));
    }
    //<<<<<<<<<<<<<<<<<<<<<<<MENU>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
    
    
    //>>>>>>>>>>>>>>>>>>>>>>>>>>>>MENU ITEM<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
    @Test
    public void total_cost_should_be_0_if_no_item_is_selected(){
        List<String> selectedItems = new ArrayList<>();
        int total = restaurant.getTotalCost(selectedItems);
        assertEquals(0, total);
    }

     @Test
    public void if_items_are_selected_then_total_cost_should_be_sum_of_all_the_selected_items() {
    	List<String> selectedItems = new ArrayList<>();
    	selectedItems.add("Sweet corn soup");
    	selectedItems.add("Vegetable lasagne");
    	int total = restaurant.getTotalCost(selectedItems);
    	assertEquals(388, total);
    }
    //<<<<<<<<<<<<<<<<<<<<<<<<<<<MENU ITEM>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
    
}