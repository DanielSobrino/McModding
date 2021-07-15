// Made with Blockbench 3.9.2
// Exported for Minecraft version 1.15
// Paste this class into your mod and generate all required imports

package durummy.mob.additions;

import net.minecraft.client.model.ModelPart;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.util.math.MatrixStack;

public class PaperPlaneModel extends EntityModel<EntityPaperPlane> {
	private final ModelPart paper_airplane;
	private final ModelPart left_wing;
	private final ModelPart right_wing;

//	public PaperPlaneModel(float yRotation) {
	public PaperPlaneModel() {
		textureWidth = 32;
		textureHeight = 32;
		paper_airplane = new ModelPart(this);
		//ACORDARSE DE MOVER EL PIVOT Y si está desplazado
		paper_airplane.setPivot(0.0F, 4.0F, 0.0F);
		setRotationAngle(paper_airplane,  3.1416F, 0.0F, -1.5708F);
		paper_airplane.setTextureOffset(0, 0).addCuboid(0.0F, -3.0F, 0.0F, 9.0F, 3.0F, 0.0F, 0.0F, false);

		left_wing = new ModelPart(this);
		left_wing.setPivot(0.0F, -3.0F, 0.0F);
		paper_airplane.addChild(left_wing);
		left_wing.setTextureOffset(0, 7).addCuboid(0.0F, 0.0F, 0.0F, 7.0F, 0.0F, 3.0F, 0.0F, false);

		right_wing = new ModelPart(this);
		right_wing.setPivot(0.0F, -3.0F, 0.0F);
		paper_airplane.addChild(right_wing);
		right_wing.setTextureOffset(0, 4).addCuboid(0.0F, 0.0F, -3.0F, 7.0F, 0.0F, 3.0F, 0.0F, false);
}
	
	@Override
	public void setAngles(EntityPaperPlane entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch){
			//previously the render function, render code was moved to a method below
	}
	@Override
	public void render(MatrixStack matrixStack, VertexConsumer	buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha){
			
		paper_airplane.render(matrixStack, buffer, packedLight, packedOverlay);
	}
	public void setRotationAngle(ModelPart bone, float x, float y, float z) {
			bone.pitch = x;
			bone.yaw = y;
			bone.roll = z;
	}

}