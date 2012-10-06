package net.mrblockplacer.JM.CuisineMod;

import java.util.Random;

import org.lwjgl.opengl.GL11;

import net.minecraft.src.Container;
import net.minecraft.src.GuiContainer;
import net.minecraft.src.InventoryPlayer;
import net.minecraft.src.StatCollector;

public class GuiSaltCollector extends GuiContainer {

	private TileEntitySaltCollector TE;

	public GuiSaltCollector(InventoryPlayer inventoryPlayer, TileEntitySaltCollector tileEntity) {
		super(new ContainerSaltCollector(inventoryPlayer, tileEntity));
		TE = tileEntity;
	}

	@Override
	protected void drawGuiContainerForegroundLayer() {
		fontRenderer.drawString("Salt Collector", 53, 6, 4210752);
		fontRenderer.drawString(StatCollector.translateToLocal("container.inventory"), 8, ySize - 96 + 2, 4210752);
	}

	/*
	 * new Gui Texture: overlay pos: 176, 0 overlay dim: 16, 40 corresponding
	 * silhouette pos: 80, 16
	 */

	@Override
	protected void drawGuiContainerBackgroundLayer(float par1, int par2, int par3) {

		int texture = mc.renderEngine.getTexture("/JMMods/Cuisine/Gui/SaltCollector.png");
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		this.mc.renderEngine.bindTexture(texture);
		int x = (width - xSize) / 2;
		int y = (height - ySize) / 2;
		this.drawTexturedModalRect(x, y, 0, 0, xSize, ySize);
		int part = TE.getTimeLeft();

		part = (int) part / (TE.TotalTime / 39);
		this.drawTexturedModalRect(x + 80, y + 16, 176, 0, 16, 40 - part);
	}
}
