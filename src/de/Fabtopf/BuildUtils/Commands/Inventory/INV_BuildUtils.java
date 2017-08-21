package de.Fabtopf.BuildUtils.Commands.Inventory;

import de.Fabtopf.BuildUtils.API.Converter;
import de.Fabtopf.BuildUtils.API.Item;
import de.Fabtopf.BuildUtils.API.Manager.ItemManager;
import de.Fabtopf.BuildUtils.API.Manager.ModuleManager;
import de.Fabtopf.BuildUtils.API.Manager.WeltenManager;
import de.Fabtopf.BuildUtils.API.Module;
import de.Fabtopf.BuildUtils.API.Welt;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

import java.util.List;

/**
 * Created by Fabi on 20.08.2017.
 */
public class INV_BuildUtils {

    public static void openGUI(Player p) {

        int invsize = 45;
        Inventory inv = Bukkit.createInventory(null, invsize, "§cContrayBuild - Management");

        ItemStack filler = new ItemStack(Material.STAINED_GLASS_PANE, 1, (byte) 15);
        ItemMeta fillerMeta = filler.getItemMeta();
        fillerMeta.setDisplayName("§0");
        filler.setItemMeta(fillerMeta);

        ItemStack item;
        ItemMeta meta;

        for(int i = 0; i < invsize; i++) {
            switch(i) {

                case 11:
                    item = new ItemStack(Material.EMPTY_MAP);
                    meta = item.getItemMeta();
                    meta.setDisplayName("§e§lWorld-Management");
                    item.setItemMeta(meta);
                    break;
                case 15:
                    item = new ItemStack(Material.SKULL_ITEM, 1, (byte) 3);
                    meta = item.getItemMeta();
                    meta.setDisplayName("§a§lSpieler-Management");
                    item.setItemMeta(meta);
                    break;
                case 22:
                    item = new ItemStack(Material.NETHER_STAR);
                    meta = item.getItemMeta();
                    meta.setDisplayName("§6§lModule-Settings");
                    item.setItemMeta(meta);
                    break;
                case 29:
                    item = new ItemStack(Material.PAPER);
                    meta = item.getItemMeta();
                    meta.setDisplayName("§c§lItem-Management");
                    item.setItemMeta(meta);
                    break;
                case 33:
                    if(Bukkit.getPluginManager().isPluginEnabled("PlotSquared")) {
                        item = new ItemStack(Material.GRASS);
                        meta = item.getItemMeta();
                        meta.setDisplayName("§9§lFertige Plots");
                        item.setItemMeta(meta);
                    } else {
                        item = new ItemStack(Material.BARRIER);
                        meta = item.getItemMeta();
                        meta.setDisplayName("§4§lNicht verfügbar!");
                        meta.setLore(Converter.stringToList("§cBitte aktiviere das Plugin", "§cPlotSquared um diese", "§cFunktion freizuschalten!"));
                        item.setItemMeta(meta);
                    }
                    break;

                default:
                    item = filler;
                    break;

            }

            inv.setItem(i, item);
        }

        p.openInventory(inv);

    }

    public static void openWorldManagement(Player p, int page) {
        for(World world : Bukkit.getWorlds()) { WeltenManager.registerWelt(world.getName()); }

        List<Welt> welten = WeltenManager.getWeltenList();
        int invsize = 0;
        if(welten.size() - ((page - 1) * 36) > 36) invsize = 54;
        if(welten.size() == 0) invsize = 9;
        if(welten.size() - ((page - 1) * 36) < 36 && welten.size() > 0) invsize = ((int) (welten.size() - (((page - 1) * 36)) + 8) / 9) * 9 + 18;

        Inventory inv = Bukkit.createInventory(null, invsize, "§cContrayBuild - Welten");

        ItemStack filler = new ItemStack(Material.STAINED_GLASS_PANE, 1, (byte) 15);
        ItemMeta fillerMeta = filler.getItemMeta();
        fillerMeta.setDisplayName("§0");
        filler.setItemMeta(fillerMeta);

        ItemStack item = null;
        ItemMeta meta;

        for(int i = 0; i < invsize; i++) {
            if(i + ((page - 1) * 36) < welten.size() && i < 36) {
                item = new ItemStack(Material.GRASS);
                meta = item.getItemMeta();
                meta.setDisplayName("§e" + welten.get(i + (36 * (page - 1))).getName());
                item.setItemMeta(meta);
            } else if(i == invsize - 1 || i == invsize - 9 || (page - 1 > 0 && i == invsize - 6) || (welten.size() - (36 * page) > 0 && i == invsize - 4)) {
                if(i == invsize - 9) {
                    item = new ItemStack(Material.EMERALD);
                    meta = item.getItemMeta();
                    meta.setDisplayName("§aReload");
                    item.setItemMeta(meta);
                } else if(i == invsize - 1) {
                    item = new ItemStack(Material.BARRIER);
                    meta = item.getItemMeta();
                    meta.setDisplayName("§cZurück");
                    item.setItemMeta(meta);
                } else if(i == invsize - 6) {
                    item = new ItemStack(Material.SKULL_ITEM, 1, (byte) 3);
                    SkullMeta skull = (SkullMeta) item.getItemMeta();
                    skull.setDisplayName("§eZu Seite " + (page - 1));
                    skull.setOwner("MHF_ArrowLeft");
                    item.setItemMeta(skull);
                } else if(i == invsize - 4) {
                    item = new ItemStack(Material.SKULL_ITEM, 1, (byte) 3);
                    SkullMeta skull = (SkullMeta) item.getItemMeta();
                    skull.setDisplayName("§eZu Seite " + (page + 1));
                    skull.setOwner("MHF_ArrowRight");
                    item.setItemMeta(skull);
                }
            } else {
                item = filler;
            }

            inv.setItem(i, item);

        }

        p.openInventory(inv);

    }

    public static void openPlayerManagement(Player p) {

    }

    public static void openModuleManagement(Player p, int page) {

        List<Module> modules = ModuleManager.getModules();
        int invsize = 0;
        if(modules.size() - ((page - 1) * 36) > 36) invsize = 54;
        if(modules.size() == 0) invsize = 9;
        if(modules.size() - ((page - 1) * 36) < 36 && modules.size() > 0) invsize = ((int) (modules.size() - (((page - 1) * 36)) + 8) / 9) * 9 + 18;

        Inventory inv = Bukkit.createInventory(null, invsize, "§cContrayBuild - Modules");

        ItemStack filler = new ItemStack(Material.STAINED_GLASS_PANE, 1, (byte) 15);
        ItemMeta fillerMeta = filler.getItemMeta();
        fillerMeta.setDisplayName("§0");
        filler.setItemMeta(fillerMeta);

        ItemStack item = null;
        ItemMeta meta;

        for(int i = 0; i < invsize; i++) {
            if(i + ((page - 1) * 36) < modules.size() && i < 36) {
                if(modules.get(i + (36 * (page -1))).isEnabled()) {
                    item = new ItemStack(Material.WOOL, 1, (byte) 5);
                    meta = item.getItemMeta();
                    meta.setDisplayName("§a" + modules.get(i + (36 * (page - 1))).getName());
                    item.setItemMeta(meta);
                } else {
                    item = new ItemStack(Material.WOOL, 1, (byte) 14);
                    meta = item.getItemMeta();
                    meta.setDisplayName("§c" + modules.get(i + (36 * (page - 1))).getName());
                    item.setItemMeta(meta);
                }
            } else if(i == invsize - 1 || i == invsize - 9 || (page - 1 > 0 && i == invsize - 6) || (modules.size() - (36 * page) > 0 && i == invsize - 4)) {
                if(i == invsize - 9) {
                    item = new ItemStack(Material.EMERALD);
                    meta = item.getItemMeta();
                    meta.setDisplayName("§aReload");
                    item.setItemMeta(meta);
                } else if(i == invsize - 1) {
                    item = new ItemStack(Material.BARRIER);
                    meta = item.getItemMeta();
                    meta.setDisplayName("§cZurück");
                    item.setItemMeta(meta);
                } else if(i == invsize - 6) {
                    item = new ItemStack(Material.SKULL_ITEM, 1, (byte) 3);
                    SkullMeta skull = (SkullMeta) item.getItemMeta();
                    skull.setDisplayName("§eZu Seite " + (page - 1));
                    skull.setOwner("MHF_ArrowLeft");
                    item.setItemMeta(skull);
                } else if(i == invsize - 4) {
                    item = new ItemStack(Material.SKULL_ITEM, 1, (byte) 3);
                    SkullMeta skull = (SkullMeta) item.getItemMeta();
                    skull.setDisplayName("§eZu Seite " + (page + 1));
                    skull.setOwner("MHF_ArrowRight");
                    item.setItemMeta(skull);
                }
            } else {
                item = filler;
            }

            inv.setItem(i, item);

        }

        p.openInventory(inv);

    }

    public static void openItemManagement(Player p, int page) {

        List<Item> items = ItemManager.getItems();
        int invsize = 0;
        if(items.size() - ((page - 1) * 36) > 36) invsize = 54;
        if(items.size() == 0) invsize = 9;
        if(items.size() - ((page - 1) * 36) < 36 && items.size() > 0) invsize = ((int) (items.size() - (((page - 1) * 36)) + 8) / 9) * 9 + 18;

        Inventory inv = Bukkit.createInventory(null, invsize, "§cContrayBuild - Items");

        ItemStack filler = new ItemStack(Material.STAINED_GLASS_PANE, 1, (byte) 15);
        ItemMeta fillerMeta = filler.getItemMeta();
        fillerMeta.setDisplayName("§0");
        filler.setItemMeta(fillerMeta);

        ItemStack item = null;
        ItemMeta meta;

        for(int i = 0; i < invsize; i++) {
            if(i + ((page - 1) * 36) < items.size() && i < 36) {
                item = new ItemStack(Material.getMaterial(items.get(i + (36 * (page - 1))).getId()));
                meta = item.getItemMeta();
                meta.setDisplayName("§e" + Material.getMaterial(items.get(i + (36 * (page - 1))).getId()).name());
                item.setItemMeta(meta);
            } else if(i == invsize - 1 || i == invsize - 9 || i == invsize - 5 || (page - 1 > 0 && i == invsize - 6) || (items.size() - (36 * page) > 0 && i == invsize - 4)) {
                if(i == invsize - 9) {
                    item = new ItemStack(Material.EMERALD);
                    meta = item.getItemMeta();
                    meta.setDisplayName("§aReload");
                    item.setItemMeta(meta);
                } else if(i == invsize - 1) {
                    item = new ItemStack(Material.BARRIER);
                    meta = item.getItemMeta();
                    meta.setDisplayName("§cZurück");
                    item.setItemMeta(meta);
                } else if(i == invsize - 6) {
                    item = new ItemStack(Material.SKULL_ITEM, 1, (byte) 3);
                    SkullMeta skull = (SkullMeta) item.getItemMeta();
                    skull.setDisplayName("§eZu Seite " + (page - 1));
                    skull.setOwner("MHF_ArrowLeft");
                    item.setItemMeta(skull);
                } else if(i == invsize - 4) {
                    item = new ItemStack(Material.SKULL_ITEM, 1, (byte) 3);
                    SkullMeta skull = (SkullMeta) item.getItemMeta();
                    skull.setDisplayName("§eZu Seite " + (page + 1));
                    skull.setOwner("MHF_ArrowRight");
                    item.setItemMeta(skull);
                } else if(i == invsize - 5) {
                    item = new ItemStack(Material.SKULL_ITEM, 1, (byte) 3);
                    SkullMeta skull = (SkullMeta) item.getItemMeta();
                    skull.setDisplayName("§9Liste editieren");
                    skull.setOwner("MHF_Question");
                    item.setItemMeta(skull);
                }
            } else {
                item = filler;
            }

            inv.setItem(i, item);

        }

        p.openInventory(inv);

    }

    public static void openPlotManagement(Player p) {

    }

    public static void openItemManagementEdit(Player p) {

        Inventory inv = Bukkit.createInventory(null, InventoryType.CREATIVE, "§cContrayBuild - ItemEdit");

        p.openInventory(inv);

    }

}
