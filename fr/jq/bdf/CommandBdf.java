package fr.jq.bdf;

import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class CommandBdf implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if(!(sender instanceof Player)) {
            sender.sendMessage("You must be a player.");
        }

        int ironAmount = 0;
        Player p = (Player) sender;


        ItemStack[] contents = p.getInventory().getContents();
        int length = contents.length;

        for(int i = 0; i < length; i++) {

            if(contents[i] != null){

                ItemStack content = contents[i];
                if(content.getType() == Material.IRON_INGOT) {
                    ironAmount = ironAmount + content.getAmount();
                    p.getInventory().setItem(i, new ItemStack(Material.AIR));
                }
            }

        }

        p.updateInventory();

        int ironRest = ironAmount % 9;

        int totalBlocks = ironAmount / 9;
        int blockStacks = totalBlocks / 64;
        int blockRest = totalBlocks % 64;

        for(int n = 0; n <= blockStacks; n++) {
            if(n != 0){
                p.getInventory().addItem(new ItemStack(Material.IRON_BLOCK, 64));
            }

        }

        if(ironRest != 0) {
            p.getInventory().addItem(new ItemStack(Material.IRON_INGOT, ironRest));
        }

        if(blockRest != 0) {
            p.getInventory().addItem(new ItemStack(Material.IRON_BLOCK, blockRest));
        }

        p.updateInventory();

        p.sendMessage("§aVous avez échangé §4"+ ironAmount + " lingots de fer §acontre §2" + totalBlocks + " blocks de fer §aet il reste §4" + ironRest + " lingots.");

        return false;

    }

}
