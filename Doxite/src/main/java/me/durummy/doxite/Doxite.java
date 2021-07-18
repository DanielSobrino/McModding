package me.durummy.doxite;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.fabricmc.fabric.api.screenhandler.v1.ScreenHandlerRegistry;
import net.fabricmc.fabric.api.tool.attribute.v1.FabricToolTags;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.Material;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class Doxite implements ModInitializer {

    @Override
    public void onInitialize() {
        RegisterItems.register();
    }

    public static final ScreenHandlerType<EndriumPoweredSmithingTableScreenHandler> ENDRIUM_POWERED_SMITHING_TABLE_SCREEN_HANDLER;
    public static final BlockEntityType<EndriumPoweredSmithingTableEntity> ENDRIUM_POWERED_SMITHING_TABLE_ENTITY;

    static {
        ENDRIUM_POWERED_SMITHING_TABLE_SCREEN_HANDLER = ScreenHandlerRegistry.registerSimple(RegisterItems.ENDRIUM_POWERED_SMITHING_TABLE_ID, EndriumPoweredSmithingTableScreenHandler::new);
//        ENDRIUM_POWERED_SMITHING_TABLE_ENTITY = Registry.register(Registry.BLOCK_ENTITY_TYPE, RegisterItems.ENDRIUM_POWERED_SMITHING_TABLE_ID, BlockEntityType.Builder.create(EndriumPoweredSmithingTableEntity::new, RegisterItems.ENDRIUM_POWERED_SMITHING_TABLE).build(null));
        ENDRIUM_POWERED_SMITHING_TABLE_ENTITY = register("endrium_powered_smithing_table_entity", FabricBlockEntityTypeBuilder.create(EndriumPoweredSmithingTableEntity::new, RegisterItems.ENDRIUM_POWERED_SMITHING_TABLE).build(null));
    }
    private static <T extends BlockEntity> BlockEntityType<T> register(String name, BlockEntityType<T> type) {
        return Registry.register(Registry.BLOCK_ENTITY_TYPE, "durummy", type);
    }


    public static class RegisterItems {
        public static final Item ENDRIUM = new Item(new Item.Settings().group(ItemGroup.MATERIALS));
        public static final Item DOXITE = new Item(new Item.Settings().group(ItemGroup.MATERIALS));
        public static final Block ENDRIUM_POWERED_SMITHING_TABLE = new EndriumPoweredSmithingTable(FabricBlockSettings.of(Material.METAL).strength(6.0f).breakByTool(FabricToolTags.PICKAXES, 2).requiresTool().sounds(BlockSoundGroup.COPPER));
        public static final BlockEntityType<EndriumPoweredSmithingTableEntity> ENDRIUM_POWERED_SMITHING_TABLE_ENTITY = null;
        public static final String MOD_ID = "doxite";
        public static final ScreenHandlerType<EndriumPoweredSmithingTableScreenHandler> ENDRIUM_POWERED_SMITHING_TABLE_SCREEN_HANDLER = null;
        public static final Identifier ENDRIUM_POWERED_SMITHING_TABLE_ID = new Identifier(MOD_ID, "endrium_powered_smithing_table");


        public static void register() {
            Registry.register(Registry.ITEM, new Identifier("doxite", "endrium"), RegisterItems.ENDRIUM);
            Registry.register(Registry.ITEM, new Identifier("doxite", "doxite"), RegisterItems.DOXITE);
            Registry.register(Registry.BLOCK, ENDRIUM_POWERED_SMITHING_TABLE_ID, ENDRIUM_POWERED_SMITHING_TABLE);
            Registry.register(Registry.ITEM, ENDRIUM_POWERED_SMITHING_TABLE_ID, new EndriumPoweredSmithingTableItem(ENDRIUM_POWERED_SMITHING_TABLE, new FabricItemSettings().group(ItemGroup.DECORATIONS)));

        }
    }


}
