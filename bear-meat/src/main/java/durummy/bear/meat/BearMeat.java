package durummy.bear.meat;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.loot.v1.FabricLootPoolBuilder;
import net.fabricmc.fabric.api.loot.v1.event.LootTableLoadingCallback;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.predicate.entity.EntityPredicate;
import net.minecraft.predicate.entity.EntityFlagsPredicate;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.minecraft.loot.context.LootContext;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.loot.function.FurnaceSmeltLootFunction;
import net.minecraft.loot.function.LootingEnchantLootFunction;
import net.minecraft.loot.provider.number.ConstantLootNumberProvider;
import net.minecraft.loot.provider.number.UniformLootNumberProvider;
import net.minecraft.loot.condition.EntityPropertiesLootCondition;


public class BearMeat implements ModInitializer {
	
	// Polar bear loot table
	private static final Identifier POLAR_BEAR_LOOT_TABLE_ID = EntityType.POLAR_BEAR.getLootTableId();
	@Override
	public void onInitialize() {

		RegisterItems.register();

		// Add meat drop to the polar_bear loot table
		LootTableLoadingCallback.EVENT.register((resourceManager, lootManager, id, table, setter) -> {
			if (POLAR_BEAR_LOOT_TABLE_ID.equals(id)) {
				FabricLootPoolBuilder poolBuilder = FabricLootPoolBuilder.builder()
						.rolls(ConstantLootNumberProvider.create(1))
						.with(ItemEntry.builder(RegisterItems.BEAR_MEAT))
						.rolls(UniformLootNumberProvider.create(1, 0.4f)) //fix!!!!!!!!!!!!!!
                        .with(ItemEntry.builder(RegisterItems.BEAR_FUR))
						.withFunction(LootingEnchantLootFunction.builder(UniformLootNumberProvider.create(0.0f, 1.7f)).build())
						.withFunction(FurnaceSmeltLootFunction.builder().conditionally(EntityPropertiesLootCondition.builder(LootContext.EntityTarget.THIS, EntityPredicate.Builder.create().flags(EntityFlagsPredicate.Builder.create().onFire(true).build()))).build());
				
				table.pool(poolBuilder);
			}
		});
	}

	public class RegisterItems {

		public static final Item BEAR_MEAT = new Item(new Item.Settings().group(ItemGroup.FOOD).food(BearFoodComponent.BEAR_MEAT));
		public static final Item COOKED_BEAR_MEAT = new Item(new Item.Settings().group(ItemGroup.FOOD).food(BearFoodComponent.COOKED_BEAR_MEAT));
		public static final Item BEAR_FUR = new Item(new Item.Settings().group(ItemGroup.MISC));
     
		public static final ArmorMaterial BEAR_FUR_MATERIAL = new BearFurArmorMaterial();

		public static final Item BEAR_FUR_HELMET = new ArmorItem(BEAR_FUR_MATERIAL, EquipmentSlot.HEAD, new Item.Settings().group(ItemGroup.COMBAT));
		public static final Item BEAR_FUR_CHESTPLATE = new ArmorItem(BEAR_FUR_MATERIAL, EquipmentSlot.CHEST, new Item.Settings().group(ItemGroup.COMBAT));
		public static final Item BEAR_FUR_LEGGINGS = new ArmorItem(BEAR_FUR_MATERIAL, EquipmentSlot.LEGS, new Item.Settings().group(ItemGroup.COMBAT));
		public static final Item BEAR_FUR_BOOTS = new ArmorItem(BEAR_FUR_MATERIAL, EquipmentSlot.FEET, new Item.Settings().group(ItemGroup.COMBAT));
		
		public static void register() {
			Registry.register(Registry.ITEM, new Identifier("bearmeat", "bear_meat"), RegisterItems.BEAR_MEAT);
			Registry.register(Registry.ITEM, new Identifier("bearmeat", "cooked_bear_meat"), RegisterItems.COOKED_BEAR_MEAT);
			
			Registry.register(Registry.ITEM, new Identifier("bearmeat", "bear_fur"), RegisterItems.BEAR_FUR);
			Registry.register(Registry.ITEM, new Identifier("bearmeat", "bear_fur_helmet"), RegisterItems.BEAR_FUR_HELMET);
			Registry.register(Registry.ITEM, new Identifier("bearmeat", "bear_fur_chestplate"), RegisterItems.BEAR_FUR_CHESTPLATE);
			Registry.register(Registry.ITEM, new Identifier("bearmeat", "bear_fur_leggings"), RegisterItems.BEAR_FUR_LEGGINGS);
			Registry.register(Registry.ITEM, new Identifier("bearmeat", "bear_fur_boots"), RegisterItems.BEAR_FUR_BOOTS);
		}
	
	}

}
