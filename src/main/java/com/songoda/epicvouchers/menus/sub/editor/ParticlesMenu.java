package com.songoda.epicvouchers.menus.sub.editor;

import com.songoda.epicvouchers.EpicVouchers;
import com.songoda.epicvouchers.libraries.ItemBuilder;
import com.songoda.epicvouchers.libraries.inventory.IconInv;
import com.songoda.epicvouchers.libraries.inventory.icons.IntegerIcon;
import com.songoda.epicvouchers.libraries.inventory.icons.StringIcon;
import com.songoda.epicvouchers.menus.VoucherEditorMenu;
import com.songoda.epicvouchers.voucher.Voucher;
import org.bukkit.Material;

import static org.bukkit.ChatColor.*;

public class ParticlesMenu extends IconInv {
    public ParticlesMenu(EpicVouchers instance, Voucher voucher) {
        super(9, "Particles");

        addIcon(1, new StringIcon(instance, "Particle", voucher.getParticle(), (player, editString) -> {
            voucher.setParticle(editString).saveSetting("particles.particle", editString);
            new ParticlesMenu(instance, voucher).open(player);
        }));

        addIcon(2, new IntegerIcon(instance, "Stay", voucher.getParticleAmount(), (player, number) -> {
            voucher.setParticleAmount(number).saveSetting("particles.amount", number);
            new ParticlesMenu(instance, voucher).open(player);
        }));

        addIcon(0, new ItemBuilder(Material.BARRIER)
                .name(YELLOW + "Return")
                .lore(GRAY + "Return to the editor")
                .build(), event -> new VoucherEditorMenu(instance, voucher).open(event.getPlayer()));

    }
}