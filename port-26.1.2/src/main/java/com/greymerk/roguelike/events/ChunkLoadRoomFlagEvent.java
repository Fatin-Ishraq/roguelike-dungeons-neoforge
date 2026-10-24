package com.greymerk.roguelike.events;

import com.greymerk.roguelike.state.RoguelikeState;

import net.minecraft.server.level.ServerLevel;
import net.neoforged.neoforge.event.level.ChunkEvent;

// Give the state class a heads up to check for rooms that need to generate
public class ChunkLoadRoomFlagEvent {

	public void onChunkLoad(ChunkEvent.Load event) {
		// NeoForge also raises this event on the client, where there is no dungeon state to update.
		if(!(event.getLevel() instanceof ServerLevel)) return;
		RoguelikeState.flagForGenerationCheck = true;
	}
}
