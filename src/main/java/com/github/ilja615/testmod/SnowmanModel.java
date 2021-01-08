package com.github.ilja615.testmod;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;

public class SnowmanModel extends EntityModel<SnowmanEntity> {
    private final ModelRenderer piece2;
    private final ModelRenderer piece1;
    private final ModelRenderer head;
    private final ModelRenderer arm1;
    private final ModelRenderer arm2;

    public SnowmanModel() {
        textureWidth = 64;
        textureHeight = 64;

        piece2 = new ModelRenderer(this);
        piece2.setRotationPoint(0.0F, 24.0F, 0.0F);
        piece2.setTextureOffset(0, 36).addBox(-6.0F, -12.0F, -6.0F, 12.0F, 12.0F, 12.0F, -0.5F, false);

        piece1 = new ModelRenderer(this);
        piece1.setRotationPoint(0.0F, -11.0F, 0.0F);
        piece2.addChild(piece1);
        piece1.setTextureOffset(0, 16).addBox(-5.0F, -10.0F, -5.0F, 10.0F, 10.0F, 10.0F, -0.5F, false);

        head = new ModelRenderer(this);
        head.setRotationPoint(0.0F, -9.0F, 0.0F);
        piece1.addChild(head);
        head.setTextureOffset(0, 0).addBox(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, -0.5F, false);

        arm1 = new ModelRenderer(this);
        arm1.setRotationPoint(0.0F, -7.0F, 0.0F);
        piece1.addChild(arm1);
        setRotationAngle(arm1, 0.0F, 0.0F, 0.7854F);
        arm1.setTextureOffset(32, 0).addBox(1.0F, -4.0F, -1.0F, 12.0F, 2.0F, 2.0F, -0.5F, false);

        arm2 = new ModelRenderer(this);
        arm2.setRotationPoint(0.0F, -7.0F, 0.0F);
        piece1.addChild(arm2);
        setRotationAngle(arm2, 0.0F, 0.0F, 2.3562F);
        arm2.setTextureOffset(32, 0).addBox(1.0F, 2.0F, -1.0F, 12.0F, 2.0F, 2.0F, -0.5F, false);
    }

    @Override
    public void setRotationAngles(SnowmanEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch){
        //previously the render function, render code was moved to a method below
    }

    @Override
    public void render(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha){
        piece2.render(matrixStack, buffer, packedLight, packedOverlay);
    }

    public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.rotateAngleX = x;
        modelRenderer.rotateAngleY = y;
        modelRenderer.rotateAngleZ = z;
    }
}
