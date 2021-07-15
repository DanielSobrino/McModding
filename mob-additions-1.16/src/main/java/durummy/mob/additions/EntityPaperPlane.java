package durummy.mob.additions;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.ArrowEntity;
import net.minecraft.entity.projectile.TridentEntity;
import net.minecraft.entity.projectile.thrown.ThrownEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.network.Packet;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.ItemScatterer;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraft.world.explosion.Explosion;

import java.util.Random;


public class EntityPaperPlane extends ThrownEntity {

//	public boolean hasCollided = false;
//	Random rand = new Random();
//	public static float randomRotation = 0.0F;

	public EntityPaperPlane(EntityType<? extends ThrownEntity> entityType, World world) {
		super(entityType, world);
	}

	public EntityPaperPlane(World world, LivingEntity owner) {
		super(MobAdditions.PAPER_PLANE, owner, world);
	}

	public EntityPaperPlane(World world, double x, double y, double z) {
		super(MobAdditions.PAPER_PLANE, x, y, z, world);
	}

	protected Item getDefaultItem() {
		return MobAdditions.PaperPlaneItem;
	}

	protected void onEntityHit(EntityHitResult entityHitResult) { // called on entity hit.
		super.onEntityHit(entityHitResult);
		Entity entity = entityHitResult.getEntity(); // sets a new Entity instance as the EntityHitResult (victim)


		if (entity instanceof LivingEntity) { // checks if entity is an instance of LivingEntity (meaning it is not a boat or mine cart)
            entity.damage(DamageSource.thrownProjectile(this, this.getOwner()), 1.0f); // deals damage

			 entity.playSound(SoundEvents.ITEM_BOOK_PAGE_TURN, 2F, 1F); // plays a sound for the entity hit only
		}
	}

	protected void onCollision(HitResult hitResult) { // called on collision with a block
//		randomRotation = (float) rand.nextDouble() * 5;

//		hasCollided = true;
		this.setVelocity(0,0,0);
//		this.setVelocity(-this.getVelocity().x,-this.getVelocity().y,-this.getVelocity().z);
		super.onCollision(hitResult);
//		this.setProperties(this,  this.pitch, this.yaw, 0.0F, 0.0F, 0.0f); //Stop the plane from moving
		if (!this.world.isClient) { // checks if the world is client
			this.world.sendEntityStatus(this, (byte)3); // particle?
//			world.createExplosion(this, getX(), getY(), getZ(), 3, Explosion.DestructionType.DESTROY);
			ItemScatterer.spawn(world, this.getX(), this.getY(), this.getZ(), new ItemStack(MobAdditions.PaperPlaneItem));
			this.remove(); // kills the projectile
//			this.kill(); // kills the projectile

		}
	}


	@Override
	protected void initDataTracker() {

	}

	@Override
	public Packet<?> createSpawnPacket() {
		return EntitySpawnPacket.create(this, PaperPlaneClient.PacketID);
	}

}