package com.qtech.forgemods.core.network;

import com.qtech.forgemods.core.QFMCore;
import net.minecraftforge.fml.network.FMLHandshakeHandler;
import net.minecraftforge.fml.network.NetworkRegistry;
import net.minecraftforge.fml.network.simple.SimpleChannel;

import java.util.Objects;

@SuppressWarnings("UnusedAssignment")
public final class Network {
    private static final String VERSION = "qforgemod-net1";

    public static SimpleChannel channel;

    static {
        int id = 0;
        channel = NetworkRegistry.ChannelBuilder.named(QFMCore.rl("network"))
                .clientAcceptedVersions(s -> Objects.equals(s, VERSION))
                .serverAcceptedVersions(s -> Objects.equals(s, VERSION))
                .networkProtocolVersion(() -> VERSION)
                .simpleChannel();

        channel.messageBuilder(SetRedstoneModePacket.class, id++)
                .decoder(SetRedstoneModePacket::fromBytes)
                .encoder(SetRedstoneModePacket::toBytes)
                .consumer(SetRedstoneModePacket::handle)
                .add();
        channel.messageBuilder(LoginPacket.Reply.class, id++)
                .loginIndex(LoginPacket::getLoginIndex, LoginPacket::setLoginIndex)
                .decoder(buffer -> new LoginPacket.Reply())
                .encoder((msg, buffer) -> {
                })
                .consumer(FMLHandshakeHandler.indexFirst((hh, msg, ctx) -> msg.handle(ctx)))
                .add();
        channel.messageBuilder(ModuleChangePacket.class, id++)
                .decoder(ModuleChangePacket::fromBytes)
                .encoder(ModuleChangePacket::toBytes)
                .consumer(ModuleChangePacket::handle)
                .add();
        id++;
        id++;
        channel.messageBuilder(OreProfilePacket.class, id++)
                .decoder(OreProfilePacket::fromBytes)
                .encoder(OreProfilePacket::toBytes)
                .consumer(OreProfilePacket::handle)
                .add();
    }

    private Network() {
    }

    public static void init() {
    }
}
