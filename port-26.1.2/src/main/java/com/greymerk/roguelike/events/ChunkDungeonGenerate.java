package com.greymerk.roguelike.events;

import com.greymerk.roguelike.dungeon.Dungeon;
import com.greymerk.roguelike.dungeon.DungeonPlacement;
import com.greymerk.roguelike.editor.Coord;
import com.greymerk.roguelike.editor.IWorldEditor;
import com.greymerk.roguelike.editor.IWorldInfo;
import com.greymerk.roguelike.editor.WorldEditor;
import com.greymerk.roguelike.gamerules.RoguelikeRules;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.ChunkPos;
import net.neoforged.neoforge.event.level.ChunkEvent;

public class ChunkDungeonGenerate {

	// NeoForge's ChunkEvent.Load reports isNewChunk() for chunks that were just generated,
	// which is the same condition Fabric's ServerChunkEvents.CHUNK_GENERATE fires under.
	public void onChunkGenerate(ChunkEvent.Load event) {
		if(!event.isNewChunk()) return;
		if(!(event.getLevel() instanceof ServerLevel world)) return;

		IWorldEditor editor = WorldEditor.of(world);
		IWorldInfo worldInfo = editor.getInfo();
		if(!worldInfo.getGameRules().get(RoguelikeRules.GEN_ROGUELIKE_DUNGEONS)) return;

		ChunkPos cpos = event.getChunk().getPos();
		if(!DungeonPlacement.validChunkPos(editor, cpos)) return;

		Coord pos = Coord.of(cpos.getMiddleBlockX(), worldInfo.getTopYInclusive(), cpos.getMiddleBlockZ()).freeze();
		editor.getInfo().getState().addDungeon(new Dungeon(pos));
	}
}
