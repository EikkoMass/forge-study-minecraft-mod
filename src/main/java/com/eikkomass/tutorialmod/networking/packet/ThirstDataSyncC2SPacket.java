package com.eikkomass.tutorialmod.networking.packet;

import com.eikkomass.tutorialmod.client.ClientThirstData;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class ThirstDataSyncC2SPacket {

    private final int thirst;

    //Example Client To (2) Server Packet

    public ThirstDataSyncC2SPacket(int thirst) {
        this.thirst = thirst;
    }

    public ThirstDataSyncC2SPacket(FriendlyByteBuf buf) {
        this.thirst = buf.readInt();
    }

    public void toBytes(FriendlyByteBuf buf) {
        buf.writeInt(thirst);
    }

    public boolean handle(Supplier<NetworkEvent.Context> supplier) {
        NetworkEvent.Context context = supplier.get();
        context.enqueueWork(() -> {
            //HERE WE ARE ON THE SERVER!

            ClientThirstData.set(thirst);

        });

        return true;
    }

}
