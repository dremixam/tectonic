package com.dremixam.tectonicbigcontinents;

import dev.worldgen.lithostitched.registry.LithostitchedBuiltInRegistries;
import com.dremixam.tectonicbigcontinents.command.TectonicCommand;
import com.dremixam.tectonicbigcontinents.config.ConfigHandler;
import com.dremixam.tectonicbigcontinents.lithostitched.SetHeightLimitsModifier;
import com.dremixam.tectonicbigcontinents.worldgen.densityfunction.ConfigClamp;
import com.dremixam.tectonicbigcontinents.worldgen.densityfunction.ConfigConstant;
import com.dremixam.tectonicbigcontinents.worldgen.densityfunction.ConfigNoise;
import com.dremixam.tectonicbigcontinents.worldgen.densityfunction.Invert;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.fabricmc.fabric.api.resource.ResourceManagerHelper;
import net.fabricmc.fabric.api.resource.ResourcePackActivationType;
import net.fabricmc.fabric.api.resource.conditions.v1.ResourceConditions;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;

import static com.dremixam.tectonicbigcontinents.Tectonic.id;

public class TectonicFabric implements ModInitializer {
    @Override
    public void onInitialize() {
        Tectonic.init(FabricLoader.getInstance().getConfigDir());

        CommandRegistrationCallback.EVENT.register((dispatcher, context, selection) -> TectonicCommand.register(dispatcher));

        ResourceConditions.register(ConfigResourceCondition.TYPE);

        Registry.register(BuiltInRegistries.DENSITY_FUNCTION_TYPE, id("config_clamp"), ConfigClamp.CODEC_HOLDER.codec());
        Registry.register(BuiltInRegistries.DENSITY_FUNCTION_TYPE, id("config_constant"), ConfigConstant.CODEC_HOLDER.codec());
        Registry.register(BuiltInRegistries.DENSITY_FUNCTION_TYPE, id("config_noise"), ConfigNoise.CODEC_HOLDER.codec());
        Registry.register(BuiltInRegistries.DENSITY_FUNCTION_TYPE, id("invert"), Invert.CODEC_HOLDER.codec());

        Registry.register(LithostitchedBuiltInRegistries.MODIFIER_TYPE, id("set_height_limits"), SetHeightLimitsModifier.CODEC);

        if (ConfigHandler.getState().general.modEnabled) {
            ResourceManagerHelper.registerBuiltinResourcePack(
                id("tectonic_bigcontinents"),
                FabricLoader.getInstance().getModContainer("tectonic_bigcontinents").get(),
                Component.literal("Tectonic"),
                ResourcePackActivationType.ALWAYS_ENABLED
            );
        }
    }
}