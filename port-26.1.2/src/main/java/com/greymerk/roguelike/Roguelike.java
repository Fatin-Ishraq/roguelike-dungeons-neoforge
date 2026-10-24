package com.greymerk.roguelike;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.greymerk.roguelike.commands.RoguelikeCommand;
import com.greymerk.roguelike.config.ConfigFile;
import com.greymerk.roguelike.config.ConfigSettings;
import com.greymerk.roguelike.events.ChunkDungeonGenerate;
import com.greymerk.roguelike.events.ChunkLoadRoomFlagEvent;
import com.greymerk.roguelike.events.EntityLoadEvent;
import com.greymerk.roguelike.events.WorldTickGenerateRooms;
import com.greymerk.roguelike.gamerules.RoguelikeRules;
import com.greymerk.roguelike.state.ServerLoadListener;

import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.RegisterCommandsEvent;
import net.neoforged.neoforge.event.entity.EntityJoinLevelEvent;
import net.neoforged.neoforge.event.level.ChunkEvent;
import net.neoforged.neoforge.event.level.LevelEvent;
import net.neoforged.neoforge.event.tick.LevelTickEvent;

@Mod(Roguelike.MODID)
public class Roguelike {

	public static final String MODID = "roguelike";
	public static final Logger LOGGER = LoggerFactory.getLogger(MODID);

	public Roguelike(IEventBus modBus) {
		RoguelikeRules.init();

		NeoForge.EVENT_BUS.addListener(LevelEvent.Load.class, new ServerLoadListener()::onLevelLoad);

		NeoForge.EVENT_BUS.addListener(EntityJoinLevelEvent.class, new EntityLoadEvent()::onEntityJoinLevel);
		NeoForge.EVENT_BUS.addListener(LevelTickEvent.Pre.class, new WorldTickGenerateRooms()::onStartTick);
		NeoForge.EVENT_BUS.addListener(ChunkEvent.Load.class, new ChunkLoadRoomFlagEvent()::onChunkLoad);
		NeoForge.EVENT_BUS.addListener(ChunkEvent.Load.class, new ChunkDungeonGenerate()::onChunkGenerate);

		NeoForge.EVENT_BUS.addListener(RegisterCommandsEvent.class, RoguelikeCommand.getListener());

		modBus.addListener(RoguelikeRules::register);

		ConfigSettings.init(new ConfigFile());
	}
}
