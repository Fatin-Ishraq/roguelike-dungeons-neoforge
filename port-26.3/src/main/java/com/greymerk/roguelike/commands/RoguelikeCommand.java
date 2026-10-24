package com.greymerk.roguelike.commands;

import java.util.function.Consumer;

import com.greymerk.roguelike.Roguelike;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;

import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.server.permissions.Permissions;
import net.neoforged.neoforge.event.RegisterCommandsEvent;

public class RoguelikeCommand {

	public static Consumer<RegisterCommandsEvent> getListener() {
		return event -> event.getDispatcher().register(getCommand());
	}

	public static LiteralArgumentBuilder<CommandSourceStack> getCommand(){
		return Commands.literal(Roguelike.MODID)
			.requires(source -> source.permissions().hasPermission(Permissions.COMMANDS_GAMEMASTER))
			.then(RoguelikeCommandDungeon.getDungeon())
			.then(RoguelikeCommandConfig.getConfig());
	}


}
