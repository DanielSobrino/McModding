package me.durummy.doxite;


import net.minecraft.block.Block;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

import java.util.List;

public class EndriumPoweredSmithingTableItem extends BlockItem {

    public EndriumPoweredSmithingTableItem(Block endriumPoweredSmithingTable, Settings settings) {
        super(endriumPoweredSmithingTable, settings);
    }

//    @Override
    public void appendTooltip(ItemStack itemStack, World world, List<Text> tooltip, TooltipContext tooltipContext) {
        tooltip.add( new TranslatableText("item.doxite.endrium_powered_smithing_table.tooltip") );
    }
}
