package com.greymerk.roguelike.gamerules;

import com.greymerk.roguelike.Roguelike;

import com.mojang.brigadier.arguments.BoolArgumentType;
import com.mojang.serialization.Codec;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.flag.FeatureFlagSet;
import net.minecraft.world.level.gamerules.GameRule;
import net.minecraft.world.level.gamerules.GameRuleCategory;
import net.minecraft.world.level.gamerules.GameRuleType;
import net.minecraft.world.level.gamerules.GameRuleTypeVisitor;
import net.neoforged.neoforge.registries.RegisterEvent;

public class RoguelikeRules {

	private static final String id = "roguelike_dungeons_generate";

	public static final GameRule<Boolean> GEN_ROGUELIKE_DUNGEONS = new GameRule<>(
			GameRuleCategory.MISC,
			GameRuleType.BOOL,
			BoolArgumentType.bool(),
			GameRuleTypeVisitor::visitBoolean,
			Codec.BOOL,
			b -> b ? 1 : 0,
			true,
			FeatureFlagSet.of());

	// used to force class loading
	public static void init() {}

	public static void register(RegisterEvent event) {
		event.register(Registries.GAME_RULE, helper -> helper.register(
				ResourceKey.create(Registries.GAME_RULE, Identifier.fromNamespaceAndPath(Roguelike.MODID, id)),
				GEN_ROGUELIKE_DUNGEONS));
	}
}
