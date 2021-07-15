package durummy.mob.additions;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.stat.Stats;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

public class PaperPlaneItem extends Item {
	public PaperPlaneItem(Settings settings) {
		super(settings);
	}


	@Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
		ItemStack itemStack = user.getStackInHand(hand); // creates a new ItemStack instance of the user's itemStack in-hand
		world.playSound(null, user.getX(), user.getY(), user.getZ(), SoundEvents.ITEM_BOOK_PAGE_TURN, SoundCategory.NEUTRAL, 0.5F, 1F); // plays a globalSoundEvent
		user.getItemCooldownManager().set(this, 30);

		if (!world.isClient) {
			EntityPaperPlane paperplaneEntity = new EntityPaperPlane(world, user);
			paperplaneEntity.setProperties(user,  user.pitch, user.yaw, 0.0F, 1.0F, .05f);
			paperplaneEntity.isCollidable();
			world.spawnEntity(paperplaneEntity); // spawns entity
		}

		user.incrementStat(Stats.USED.getOrCreateStat(this));
		if (!user.abilities.creativeMode) {
			itemStack.decrement(1); // decrements itemStack if user is not in creative mode
		}
		return TypedActionResult.success(itemStack, world.isClient());
	}
}