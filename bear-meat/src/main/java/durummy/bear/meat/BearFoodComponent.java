package durummy.bear.meat;

import net.minecraft.item.FoodComponent;

public class BearFoodComponent {
    public static final FoodComponent BEAR_MEAT = (new FoodComponent.Builder().hunger(2).saturationModifier(0)).build();
    public static final FoodComponent COOKED_BEAR_MEAT = (new FoodComponent.Builder().hunger(11).saturationModifier(1)).build();
}
