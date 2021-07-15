package durummy.mob.additions;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRenderDispatcher;
import net.minecraft.client.render.entity.EntityRenderer;
import net.minecraft.client.render.item.ItemRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.client.util.math.Vector3f;
import net.minecraft.util.BlockRotation;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.MathHelper;

import java.util.Random;


@Environment(EnvType.CLIENT)
public class PaperPlaneRenderer extends EntityRenderer<EntityPaperPlane>{
    public static final Identifier TEXTURE = new Identifier("mobadditions","textures/entity/paperplane/paper-plane.png");
//    float yRotation = EntityPaperPlane.randomRotation;
//    private final PaperPlaneModel model = new PaperPlaneModel(yRotation);
    private final PaperPlaneModel model = new PaperPlaneModel();

    public PaperPlaneRenderer(EntityRenderDispatcher entityRenderDispatcher) {
        super(entityRenderDispatcher);
    }

    public void render(EntityPaperPlane paperplaneEntity, float f, float g, MatrixStack matrixStack, VertexConsumerProvider vertexConsumerProvider, int i) {
        matrixStack.push();

//        if (!paperplaneEntity.hasCollided) {
            matrixStack.multiply(Vector3f.POSITIVE_Y.getDegreesQuaternion(MathHelper.lerp(g, paperplaneEntity.prevYaw, paperplaneEntity.yaw) - 90.0F));
            matrixStack.multiply(Vector3f.POSITIVE_Z.getDegreesQuaternion(MathHelper.lerp(g, paperplaneEntity.prevPitch, paperplaneEntity.pitch) + 90F));
//        }
//        else {
//            matrixStack.multiply(Vector3f.POSITIVE_Y.getDegreesQuaternion(paperplaneEntity.prevYaw - 90.0F));
//            matrixStack.multiply(Vector3f.POSITIVE_Z.getDegreesQuaternion(+ 90F));
//        }

        VertexConsumer vertexConsumer = ItemRenderer.getDirectItemGlintConsumer(vertexConsumerProvider, this.model.getLayer(this.getTexture(paperplaneEntity)), false, false);
        this.model.render(matrixStack, vertexConsumer, i, OverlayTexture.DEFAULT_UV, 1.0F, 1.0F, 1.0F, 1.0F);
        matrixStack.pop();
        super.render(paperplaneEntity, f, g, matrixStack, vertexConsumerProvider, i);

    }


    public Identifier getTexture(EntityPaperPlane paperplaneEntity) {
        return TEXTURE;
    }
}
