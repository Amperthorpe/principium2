package com.alleluid.tdfmod.event;

import net.java.games.input.Mouse;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.GuiScreenEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class EventKeyInput {
/*    @OnlyIn(Dist.CLIENT)
    @SubscribeEvent(priority = EventPriority.HIGH)
    public void onMouseEvent(GuiScreenEvent.MouseInputEvent event) {
        if (event.getGui() == null || !(event.getGui() instanceof GuiContainer)) {
            return;
        }
        GuiContainer gui = (GuiContainer) event.getGui();
        boolean rightClickDown = false;
        //   event
        try {
            //if you press and hold the button, eventButton becomes -1 even when buttonDown(1) is true
            //so event button is on the mouseDown and mouseUp triggers
            rightClickDown = (Mouse.getEventButton() == 1) && Mouse.isButtonDown(1);
        }
        catch (Exception e) { //array out of bounds, crazy weird unexpected mouse
            //EXAMPLE:  mod.chiselsandbits.bitbag.BagGui
            // so this fixes ithttps://github.com/PrinceOfAmber/Cyclic/issues/410
        }
        //    System.out.println(" Mouse.getEventButton() " + Mouse.getEventButton());
        try {
            if (rightClickDown && gui.getSlotUnderMouse() != null) {
                int slot = gui.getSlotUnderMouse().slotNumber;
                if (gui.inventorySlots != null && slot < gui.inventorySlots.inventorySlots.size() &&
                        gui.inventorySlots.getSlot(slot) != null && !gui.inventorySlots.getSlot(slot).getStack().isEmpty()) {
                    ItemStack maybeCharm = gui.inventorySlots.getSlot(slot).getStack();
                    if (maybeCharm.getItem() instanceof IHasClickToggle) {
                        //example: is a charm or something
                        ModCyclic.network.sendToServer(new PacketItemToggle(slot));
                        EntityPlayer player = ModCyclic.proxy.getClientPlayer();
                        UtilSound.playSound(player, SoundEvents.UI_BUTTON_CLICK);
                        event.setCanceled(true);
                    }
                }
            }
        }
        catch (Exception e) {//array out of bounds, or we are in a strange third party GUI that doesnt have slots like this
            //EXAMPLE:  mod.chiselsandbits.bitbag.BagGui
            // so this fixes ithttps://github.com/PrinceOfAmber/Cyclic/issues/410
        }
    }
    */
}
