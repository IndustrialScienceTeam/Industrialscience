package de.zsgn.industrialscience.command;

import de.zsgn.industrialscience.IndustrialScience;
import de.zsgn.industrialsciencedungeonsystem.DungeonRoom;
import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.util.ChatComponentText;

public class CommandGenerateRoom extends CommandBase {

    @Override
    public String getCommandName() {
       return "is-generate";
    }

    @Override
    public String getCommandUsage(ICommandSender var1) {
        return "is-generate {x} {y} {z} {roomname}";
    }

    @Override
    public void processCommand(ICommandSender var1, String[] var2) {
        if(var2.length!=4){
            var1.addChatMessage(new ChatComponentText(getCommandUsage(var1)));
        }else{
            int x=Integer.parseInt(var2[0]);
            int y=Integer.parseInt(var2[1]);
            int z=Integer.parseInt(var2[2]);
            DungeonRoom room = IndustrialScience.getInstance().getDungeonRoom(var2[3]);
            if(room==null){
                var1.addChatMessage(new ChatComponentText("Room not found"));
                return;
            }
            room.generateRoom(x, y, z, var1.getEntityWorld(), null);
        }
    }

}
