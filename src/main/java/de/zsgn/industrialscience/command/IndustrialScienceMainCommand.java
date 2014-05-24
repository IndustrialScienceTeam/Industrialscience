package de.zsgn.industrialscience.command;

import de.zsgn.industrialscience.IndustrialScience;
import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.util.ChatComponentText;

public class IndustrialScienceMainCommand extends CommandBase {

	@Override
	public String getCommandName() {
		return "is";
	}

	@Override
	public String getCommandUsage(ICommandSender var1) {
	 return "is version - prints the current IndustrialScience version";
	}

	@Override
	public void processCommand(ICommandSender var1, String[] var2) {
		if(var2.length==0){
			var1.addChatMessage(new ChatComponentText(getCommandUsage(var1)));
			}
		else if ("version".equalsIgnoreCase(var2[0])) {
			var1.addChatMessage(new ChatComponentText(IndustrialScience.VERSION));
		}else{
			var1.addChatMessage(new ChatComponentText(getCommandUsage(var1)));;
			}
		}

}
