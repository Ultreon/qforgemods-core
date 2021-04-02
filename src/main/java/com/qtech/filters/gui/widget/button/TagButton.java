package com.qtech.filters.gui.widget.button;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.platform.GlStateManager;
import com.qtech.filters.FilterEntry;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.widget.button.Button;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.StringTextComponent;
import org.jetbrains.annotations.NotNull;

/**
 * Author: MrCrayfish
 */
@SuppressWarnings({"SameParameterValue", "deprecation"})
public class TagButton extends Button
{
    private static final ResourceLocation TABS = new ResourceLocation("textures/gui/container/creative_inventory/tabs.png");

    private final FilterEntry category;
    private final ItemStack stack;
    private boolean toggled;

    public TagButton(int x, int y, FilterEntry filter, IPressable pressable)
    {
        super(x, y, 32, 28, new StringTextComponent(""), pressable);
        this.category = filter;
        this.stack = filter.getIcon();
        this.toggled = filter.isEnabled();
    }

    public FilterEntry getFilter()
    {
        return category;
    }

    @Override
    public void onPress()
    {
        this.toggled = !this.toggled;
        this.category.setEnabled(this.toggled);
        super.onPress();
    }

    @Override
    public void renderWidget(@NotNull MatrixStack matrixStack, int mouseX, int mouseY, float partialTicks)
    {
        Minecraft mc = Minecraft.getInstance();
        mc.getTextureManager().bindTexture(TABS);

        GlStateManager.color4f(1.0F, 1.0F, 1.0F, this.alpha);
        GlStateManager.disableLighting();
        GlStateManager.enableBlend();
        GlStateManager.blendFuncSeparate(GlStateManager.SourceFactor.SRC_ALPHA.param, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA.param, GlStateManager.SourceFactor.ONE.param, GlStateManager.DestFactor.ZERO.param);
        GlStateManager.blendFunc(GlStateManager.SourceFactor.SRC_ALPHA.param, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA.param);

        int width = this.toggled ? 32 : 28;
        int textureX = 28;
        int textureY = this.toggled ? 32 : 0;
        this.drawRotatedTexture(this.x, this.y, textureX, textureY, width, 28);

        GlStateManager.enableRescaleNormal();
        RenderHelper.enableStandardItemLighting();
        ItemRenderer renderer = mc.getItemRenderer();
        renderer.zLevel = 100.0F;
        renderer.renderItemAndEffectIntoGUI(this.stack, x + 8, y + 6);
        renderer.renderItemOverlays(mc.fontRenderer, this.stack, x + 8, y + 6);
        renderer.zLevel = 100.0F;
    }

    private void drawRotatedTexture(int x, int y, int textureX, int textureY, int width, int height)
    {
        float scaleX = 0.00390625F;
        float scaleY = 0.00390625F;
        Tessellator tessellator = Tessellator.getInstance();
        BufferBuilder bufferbuilder = tessellator.getBuffer();
        bufferbuilder.begin(7, DefaultVertexFormats.POSITION_TEX);
        bufferbuilder.pos(x, y + height, 0.0).tex(((float)(textureX + height) * scaleX), (float)(textureY) * scaleY).endVertex();
        bufferbuilder.pos(x + width, y + height, 0.0).tex(((float)(textureX + height) * scaleX), (float)(textureY + width) * scaleY).endVertex();
        bufferbuilder.pos(x + width, y, 0.0).tex(((float)(textureX) * scaleX), (float)(textureY + width) * scaleY).endVertex();
        bufferbuilder.pos(x, y, 0.0).tex(((float)(textureX) * scaleX), (float)(textureY) * scaleY).endVertex();
        tessellator.draw();
    }

    public void updateState()
    {
        this.toggled = category.isEnabled();
    }
}
