package com.raindelay;

import com.mojang.brigadier.arguments.IntegerArgumentType;
import com.raindelay.config.RainDelayConfig;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.minecraft.commands.Commands;
import net.minecraft.network.chat.Component;
import net.minecraft.server.permissions.Permissions;
import net.minecraft.util.valueproviders.IntProvider;
import net.minecraft.util.valueproviders.UniformInt;
import static java.util.Objects.requireNonNull;

public class RainDelay implements ModInitializer {
	public static final String MOD_ID = "rain-delay";
	public static RainDelayConfig CONFIG = RainDelayConfig.INSTANCE;
	public static IntProvider rainDelayProvider;
	public static IntProvider thunderDelayProvider;
	public static IntProvider rainDurationProvider;
	public static IntProvider thunderDurationProvider;

	@Override
	public void onInitialize() {
		initProviders();

		CommandRegistrationCallback.EVENT.register((dispatcher, registryAccess, environment) -> {
			dispatcher.register(Commands.literal("raindelay")
				.requires(source -> source.permissions().hasPermission(Permissions.COMMANDS_MODERATOR))
				.then(Commands.literal("min_rain_delay")
					.executes(context -> {
						int current = CONFIG.minTicksAfterRainEnds;
						context.getSource().sendSuccess(() ->
							Component.literal("Current min ticks after rain: " + current + " ticks (" + String.format("%.2f", current / 24000.0) + " days )"), false);
						return 1;
					})
					.then(Commands.argument("ticks", requireNonNull(IntegerArgumentType.integer()))
						.executes(context -> {
							int ticks = IntegerArgumentType.getInteger(context, "ticks");
							CONFIG.minTicksAfterRainEnds = ticks;
							CONFIG.save();
							context.getSource().sendSuccess(() -> Component.literal("Set min ticks after rain to " + ticks + " ticks (" + String.format("%.2f", ticks / 24000.0) + " days )"), false);
                    		return 1;
						})
					)
				)
				.then(Commands.literal("max_rain_delay")
					.executes(context -> {
						int current = CONFIG.maxTicksAfterRainEnds;
						context.getSource().sendSuccess(() ->
							Component.literal("Current max ticks after rain: " + current + " ticks (" + String.format("%.2f", current / 24000.0) + " days )"), false);
						return 1;
					})
					.then(Commands.argument("ticks", requireNonNull(IntegerArgumentType.integer()))
						.executes(context -> {
							int ticks = IntegerArgumentType.getInteger(context, "ticks");
							CONFIG.maxTicksAfterRainEnds = ticks;
							CONFIG.save();
							context.getSource().sendSuccess(() -> Component.literal("Set max ticks after rain to " + ticks + " ticks (" + String.format("%.2f", ticks / 24000.0) + " days )"), false);
                    		return 1;
						})
					)
				)
				.then(Commands.literal("min_thunder_delay")
					.executes(context -> {
						int current = CONFIG.minTicksAfterThunderEnds;
						context.getSource().sendSuccess(() ->
							Component.literal("Current min ticks after thunder: " + current + " ticks (" + String.format("%.2f", current / 24000.0) + " days )"), false);
						return 1;
					})
					.then(Commands.argument("ticks", requireNonNull(IntegerArgumentType.integer()))
						.executes(context -> {
							int ticks = IntegerArgumentType.getInteger(context, "ticks");
							CONFIG.minTicksAfterThunderEnds = ticks;
							CONFIG.save();
							context.getSource().sendSuccess(() -> Component.literal("Set min ticks after thunder to " + ticks + " ticks (" + String.format("%.2f", ticks / 24000.0) + " days )"), false);
                    		return 1;
						})
					)
				)
				.then(Commands.literal("max_thunder_delay")
					.executes(context -> {
						int current = CONFIG.maxTicksAfterThunderEnds;
						context.getSource().sendSuccess(() ->
							Component.literal("Current max ticks after thunder: " + current + " ticks (" + String.format("%.2f", current / 24000.0) + " days )"), false);
						return 1;
					})
					.then(Commands.argument("ticks", requireNonNull(IntegerArgumentType.integer()))
						.executes(context -> {
							int ticks = IntegerArgumentType.getInteger(context, "ticks");
							CONFIG.maxTicksAfterThunderEnds = ticks;
							CONFIG.save();
							context.getSource().sendSuccess(() -> Component.literal("Set max ticks after thunder to " + ticks + " ticks (" + String.format("%.2f", ticks / 24000.0) + " days )"), false);
                    		return 1;
						})
					)
				)
				.then(Commands.literal("min_rain_duration")
					.executes(context -> {
						int current = CONFIG.minRainDuration;
						context.getSource().sendSuccess(() ->
							Component.literal("Current min ticks of rain: " + current + " ticks (" + String.format("%.2f", current / 24000.0) + " days )"), false);
						return 1;
					})
					.then(Commands.argument("ticks", requireNonNull(IntegerArgumentType.integer()))
						.executes(context -> {
							int ticks = IntegerArgumentType.getInteger(context, "ticks");
							CONFIG.minRainDuration = ticks;
							CONFIG.save();
							context.getSource().sendSuccess(() -> Component.literal("Set min ticks of rain " + ticks + " ticks (" + String.format("%.2f", ticks / 24000.0) + " days )"), false);
                    		return 1;
						})
					)
				)
				.then(Commands.literal("max_rain_duration")
					.executes(context -> {
						int current = CONFIG.maxRainDuration;
						context.getSource().sendSuccess(() ->
							Component.literal("Current max ticks of rain: " + current + " ticks (" + String.format("%.2f", current / 24000.0) + " days )"), false);
						return 1;
					})
					.then(Commands.argument("ticks", requireNonNull(IntegerArgumentType.integer()))
						.executes(context -> {
							int ticks = IntegerArgumentType.getInteger(context, "ticks");
							CONFIG.maxRainDuration = ticks;
							CONFIG.save();
							context.getSource().sendSuccess(() -> Component.literal("Set max ticks of rain " + ticks + " ticks (" + String.format("%.2f", ticks / 24000.0) + " days )"), false);
                    		return 1;
						})
					)
				)
				.then(Commands.literal("min_thunder_duration")
					.executes(context -> {
						int current = CONFIG.minThunderDuration;
						context.getSource().sendSuccess(() ->
							Component.literal("Current min ticks of thunder: " + current + " ticks (" + String.format("%.2f", current / 24000.0) + " days )"), false);
						return 1;
					})
					.then(Commands.argument("ticks", requireNonNull(IntegerArgumentType.integer()))
						.executes(context -> {
							int ticks = IntegerArgumentType.getInteger(context, "ticks");
							CONFIG.minThunderDuration = ticks;
							CONFIG.save();
							context.getSource().sendSuccess(() -> Component.literal("Set min ticks of thunder " + ticks + " ticks (" + String.format("%.2f", ticks / 24000.0) + " days )"), false);
                    		return 1;
						})
					)
				)
				.then(Commands.literal("max_thunder_duration")
					.executes(context -> {
						int current = CONFIG.maxThunderDuration;
						context.getSource().sendSuccess(() ->
							Component.literal("Current max ticks of thunder: " + current + " ticks (" + String.format("%.2f", current / 24000.0) + " days )"), false);
						return 1;
					})
					.then(Commands.argument("ticks", requireNonNull(IntegerArgumentType.integer()))
						.executes(context -> {
							int ticks = IntegerArgumentType.getInteger(context, "ticks");
							CONFIG.maxThunderDuration = ticks;
							CONFIG.save();
							context.getSource().sendSuccess(() -> Component.literal("Set max ticks of thunder " + ticks + " ticks (" + String.format("%.2f", ticks / 24000.0) + " days )"), false);
                    		return 1;
						})
					)
				)
			);
		});

		System.out.println("[RainDelay] loaded");
	}

	public static void initProviders() {
		rainDelayProvider = UniformInt.of(CONFIG.minTicksAfterRainEnds, CONFIG.maxTicksAfterRainEnds);
		thunderDelayProvider = UniformInt.of(CONFIG.minTicksAfterThunderEnds, CONFIG.maxTicksAfterThunderEnds);
		rainDurationProvider = UniformInt.of(CONFIG.minRainDuration, CONFIG.maxRainDuration);
		thunderDurationProvider = UniformInt.of(CONFIG.minThunderDuration, CONFIG.maxThunderDuration);
	}
}