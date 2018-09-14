package nl.marido.deluxevouchers;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import nl.marido.deluxevouchers.handlers.Commandos;
import nl.marido.deluxevouchers.handlers.Connections;
import nl.marido.deluxevouchers.handlers.DataHandler;
import nl.marido.deluxevouchers.handlers.UpdateHandler;
import nl.marido.deluxevouchers.inventory.Confirmation;
import nl.marido.deluxevouchers.inventory.VoucherEditor;
import nl.marido.deluxevouchers.liberaries.Bountiful;
import nl.marido.deluxevouchers.vouchers.ClickListener;

public class DeluxeVouchers extends JavaPlugin {

	public static DeluxeVouchers instance;
	public static ConsoleCommandSender console;

	// Thank you for purchasing DeluxeVouchers.
	public static String user = "%%__USER__%%";

	public void onEnable() {
		instance = this;
		DeluxeVouchers.enable();
		DeluxeVouchers.events();
		DataHandler.cacheData();
		Bountiful.findVersion();
		UpdateHandler.checker();
		Connections.openMySQL();
	}

	public void onDisable() {
		Connections.closeMySQL();
		DeluxeVouchers.disable();
	}

	public static void enable() {
		String version = DeluxeVouchers.getInstance().getDescription().getVersion();
		DeluxeVouchers.printConsole("�eDeluxeVouchers " + version + " has been enabled successfully.");
	}

	public static void disable() {
		String version = DeluxeVouchers.getInstance().getDescription().getVersion();
		DeluxeVouchers.printConsole("�eDeluxeVouchers " + version + " has been disabled successfully.");
	}

	public static void events() {
		Bukkit.getServer().getPluginCommand("deluxevouchers").setExecutor(new Commandos());
		PluginManager manager = Bukkit.getServer().getPluginManager();
		manager.registerEvents(new ClickListener(), DeluxeVouchers.getInstance());
		manager.registerEvents(new Confirmation(), DeluxeVouchers.getInstance());
		manager.registerEvents(new VoucherEditor(), DeluxeVouchers.getInstance());
		manager.registerEvents(new UpdateHandler(), DeluxeVouchers.getInstance());
	}

	public static DeluxeVouchers getInstance() {
		return instance;
	}

	public static String applyColor(String message) {
		return ChatColor.translateAlternateColorCodes('&', message);
	}

	public static void printConsole(String message) {
		console.sendMessage(applyColor(message));
	}

}