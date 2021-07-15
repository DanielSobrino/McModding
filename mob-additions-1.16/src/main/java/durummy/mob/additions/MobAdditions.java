package durummy.mob.additions;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.loot.v1.FabricLootPoolBuilder;
import net.fabricmc.fabric.api.loot.v1.event.LootTableLoadingCallback;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.loot.condition.EntityPropertiesLootCondition;
import net.minecraft.loot.context.LootContext;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.loot.function.FurnaceSmeltLootFunction;
import net.minecraft.loot.function.LootingEnchantLootFunction;
import net.minecraft.predicate.entity.EntityFlagsPredicate;
import net.minecraft.predicate.entity.EntityPredicate;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class MobAdditions implements ModInitializer {
	public static final Item PaperPlaneItem = new PaperPlaneItem(new Item.Settings().group(ItemGroup.MISC).maxCount(16));

	public static Identifier id(String name) {
		return new Identifier("mobadditions", name);
	}

	public static final EntityType<EntityPaperPlane> PAPER_PLANE = Registry.register(Registry.ENTITY_TYPE,
			new Identifier("mobadditions", "paper_plane"),
			FabricEntityTypeBuilder.<EntityPaperPlane>create(SpawnGroup.MISC, EntityPaperPlane::new)
					.dimensions(EntityDimensions.fixed(0.5F, 0.4F))
					.trackRangeBlocks(4).trackedUpdateRate(10) // necessary for all thrown projectiles (it prevents them from breaking)
					.build() // VERY IMPORTANT
	);



	@Override
	public void onInitialize() {
		RegisterItems.register();


	}
	

	public static class RegisterItems {

		public static final Item PAPER_PLANE = new Item(new Item.Settings().group(ItemGroup.MISC));
	
		
		public static void register() {
			Registry.register(Registry.ITEM, new Identifier("mobadditions", "paper_plane"), PaperPlaneItem);
			
		}
	}
}

