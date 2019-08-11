package mc.arena.spleef;

import mc.alk.arena.BattleArena;
import mc.alk.arena.alib.arenaregenutil.ArenaRegenController;
import mc.alk.arena.alib.arenaregenutil.region.ArenaSelection;
import mc.alk.arena.objects.arenas.Arena;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class SpleefArenaEditor {

    SpleefArena arena;

    public SpleefArenaEditor(Arena arena) throws SpleefException {
        if (!(arena instanceof SpleefArena)) {
            throw new SpleefException("&cArena &6" + arena.getName() + "&c is not a spleef arena!");
        }
        this.arena = (SpleefArena) arena;
    }

    public void setLayer(Player sender, Integer layerIndex) throws Exception {
        if (layerIndex < 1 || layerIndex > Defaults.MAX_LAYERS) {
            throw new SpleefException("&cBad layer index, 1-" + Defaults.MAX_LAYERS);
        }
        ArenaSelection sel = ArenaRegenController.getSelection(sender);
        if (sel == null) {
            throw new SpleefException(ChatColor.RED + "Please select an area first using WorldEdit.");
        }

        arena.setRegion(sender, sel, layerIndex - 1);
        BattleArena.saveArenas(ArenaSpleef.getSelf());
    }

    public void setRegen(Integer layerIndex, Integer regenTime) throws Exception {
        if (layerIndex < 1 || layerIndex > Defaults.MAX_LAYERS) {
            throw new SpleefException("&cBad layer index, 1-" + Defaults.MAX_LAYERS);
        }
        if (regenTime < 1) {
            throw new SpleefException("&cYou can't set the regen time to less than 1 second!");
        }
        arena.setRegen(layerIndex - 1, regenTime);
        BattleArena.saveArenas(ArenaSpleef.getSelf());
    }

    public void deleteRegen(Integer layerIndex) throws Exception {
        if (layerIndex < 1 || layerIndex > Defaults.MAX_LAYERS) {
            throw new SpleefException("&cBad layer index, 1-" + Defaults.MAX_LAYERS);
        }
        arena.deleteRegen(layerIndex - 1);
        BattleArena.saveArenas(ArenaSpleef.getSelf());
    }
}
