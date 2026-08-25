package com.dremixam.tectonicbigcontinents;

import net.minecraft.commands.CommandSourceStack;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.Identifier;
import net.msrandom.multiplatform.annotations.Actual;

public class TectonicActual {
    @Actual
    public static Identifier idVanilla(String name) {
        return Identifier.withDefaultNamespace(name);
    }

    @Actual
    public static Identifier id(String name) {
        return Identifier.fromNamespaceAndPath(Tectonic.MOD_ID, name);
    }

    @Actual
    public static int getBlendingVersion(CompoundTag tag) {
        return tag.getInt(Tectonic.BLENDING_KEY);
    }

    @Actual
    public static boolean canRunCommand(CommandSourceStack stack) {
        return stack.hasPermission(2);
    }
}
