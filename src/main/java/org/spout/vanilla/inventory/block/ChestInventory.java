/*
 * This file is part of Vanilla.
 *
 * Copyright (c) 2011-2012, SpoutDev <http://www.spout.org/>
 * Vanilla is licensed under the SpoutDev License Version 1.
 *
 * Vanilla is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * In addition, 180 days after any changes are published, you can use the
 * software, incorporating those changes, under the terms of the MIT license,
 * as described in the SpoutDev License Version 1.
 *
 * Vanilla is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License,
 * the MIT license and the SpoutDev License Version 1 along with this program.
 * If not, see <http://www.gnu.org/licenses/> for the GNU Lesser General Public
 * License and see <http://www.spout.org/SpoutDevLicenseV1.txt> for the full license,
 * including the MIT license.
 */
package org.spout.vanilla.inventory.block;

import org.spout.api.inventory.ItemStack;
import org.spout.api.inventory.special.InventoryRange;
import org.spout.vanilla.controller.block.Chest;
import org.spout.vanilla.controller.living.player.VanillaPlayer;
import org.spout.vanilla.inventory.WindowInventory;
import org.spout.vanilla.window.block.ChestWindow;
import org.spout.vanilla.window.Window;

public class ChestInventory extends WindowInventory {

	private static final long serialVersionUID = 1L;
	private final Chest owner;
	public static final int DOUBLE_CHEST_SIZE = 54, SINGLE_CHEST_SIZE = 27;
	private final InventoryRange[] halves;

	public ChestInventory(Chest owner) {
		this(owner, new ItemStack[owner.isDouble() ? DOUBLE_CHEST_SIZE : SINGLE_CHEST_SIZE]);
	}

	public ChestInventory(Chest owner, ItemStack[] contents) {
		super(contents);
		this.owner = owner;
		this.halves = new InventoryRange[owner.isDouble() ? 2 : 1];
		this.halves[0] = new InventoryRange(this, 0, SINGLE_CHEST_SIZE);
		if (owner.isDouble()) {
			this.halves[1] = new InventoryRange(this, SINGLE_CHEST_SIZE, SINGLE_CHEST_SIZE);
		}
	}

	@Override
	public void open(VanillaPlayer player) {
		super.open(player);
		this.getOwner().setOpened(this.hasViewingPlayers());
	}

	@Override
	public void close(VanillaPlayer player) {
		super.close(player);
		this.getOwner().setOpened(this.hasViewingPlayers());
	}

	public InventoryRange[] getHalves() {
		return this.halves;
	}
	
	public Chest getOwner() {
		return owner;
	}

	@Override
	public Window createWindow(VanillaPlayer player) {
		return new ChestWindow(player, this);
	}
}
