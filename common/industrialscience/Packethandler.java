package industrialscience;

import net.minecraft.network.INetworkManager;
import net.minecraft.network.packet.Packet250CustomPayload;
import cpw.mods.fml.common.network.IPacketHandler;
import cpw.mods.fml.common.network.Player;

public class Packethandler implements IPacketHandler {

    @Override
    public void onPacketData(INetworkManager manager,
            Packet250CustomPayload packet, Player player) {
        IndustrialScience.MODULES[Integer
                .parseInt(packet.channel.split(".")[1])].onPacketData(manager,
                packet, player);

    }

}
