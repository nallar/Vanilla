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
package org.spout.vanilla.material.block.ore;

import java.util.ArrayList;

import org.spout.api.geo.cuboid.Block;
import org.spout.api.inventory.ItemStack;

import org.spout.vanilla.material.Mineable;
import org.spout.vanilla.material.VanillaMaterials;
import org.spout.vanilla.material.block.Solid;
import org.spout.vanilla.material.item.tool.Pickaxe;
import org.spout.vanilla.material.item.tool.Tool;

public class IronBlock extends Solid implements Mineable {
	public IronBlock(String name, int id) {
		super(name, id);
		this.setHardness(5.0F).setResistance(10.0F);
	}

	@Override
	public short getDurabilityPenalty(Tool tool) {
		return tool instanceof Pickaxe ? (short) 1 : (short) 2;
	}

	@Override
	public ArrayList<ItemStack> getDrops(Block block, ItemStack holding) {
		ArrayList<ItemStack> drops = new ArrayList<ItemStack>();
		if (holding != null && holding.getMaterial().equals(VanillaMaterials.STONE_PICKAXE, VanillaMaterials.IRON_PICKAXE, VanillaMaterials.DIAMOND_PICKAXE)) {
			drops.add(new ItemStack(this, 1));
		}
		return drops;
	}
}
