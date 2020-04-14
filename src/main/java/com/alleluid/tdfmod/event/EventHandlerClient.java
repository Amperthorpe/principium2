package com.alleluid.tdfmod.event;

import com.alleluid.tdfmod.items.IClickable;
import com.alleluid.tdfmod.network.ClickableHandler;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.inventory.ContainerScreen;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.PlayerContainer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.SoundEvents;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.GuiScreenEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class EventHandlerClient {

    @OnlyIn(Dist.CLIENT)
    @SubscribeEvent
    public void onMouseEvent(GuiScreenEvent.MouseClickedEvent event)
    {
        if (event.getGui() == null || !(event.getGui() instanceof ContainerScreen)) {
            return;
        }

        if (((ContainerScreen) event.getGui()).getContainer() instanceof Container) {
            ContainerScreen<Container> gui = ((ContainerScreen<Container>) event.getGui());

            boolean rightClickDown = event.getButton() == 1;

            try {
                if (rightClickDown && gui.getSlotUnderMouse() != null) {
                    int slot = gui.getSlotUnderMouse().slotNumber;
                    Container container = gui.getContainer();
                    if (container != null && slot < container.inventorySlots.size() &&
                            container.getSlot(slot) != null && !container.getSlot(slot).getStack().isEmpty()) {
                        ItemStack item = gui.getContainer().getSlot(slot).getStack();
                        if (item.getItem() instanceof IClickable) {
                            //example: is a charm or something
                            ClickableHandler.INSTANCE.sendToServer(new ClickableHandler.ClickActionMessage(slot, true));
                            Minecraft.getInstance().player.playSound(SoundEvents.UI_BUTTON_CLICK, 1, 1);
                            event.setCanceled(true);
                        }
                    }
                }
            } catch (Exception e) {

            }
        }

    }


}
