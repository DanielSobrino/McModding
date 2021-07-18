package me.durummy.doxite;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.screenhandler.v1.ScreenRegistry;

@Environment(EnvType.CLIENT)
public class DoxiteClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        ScreenRegistry.register(Doxite.RegisterItems.ENDRIUM_POWERED_SMITHING_TABLE_SCREEN_HANDLER, EndriumPoweredSmithingTableScreen::new);
    }
}
