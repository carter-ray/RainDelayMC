package com.raindelay.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import com.raindelay.RainDelay;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.util.valueproviders.IntProvider;

@Mixin(ServerLevel.class)
public abstract class ServerLevelMixin {

	@Redirect(
		method = "advanceWeatherCycle",
		at = @At(
			value = "INVOKE",
			target = "Lnet/minecraft/util/valueproviders/IntProvider;sample(Lnet/minecraft/util/RandomSource;)I",
			ordinal = 0
		)
	)
	private int redirectThunderDuration(IntProvider provider, RandomSource random) {
		return RainDelay.thunderDurationProvider.sample(random);
	}

	@Redirect(
		method = "advanceWeatherCycle",
		at = @At(
			value = "INVOKE",
			target = "Lnet/minecraft/util/valueproviders/IntProvider;sample(Lnet/minecraft/util/RandomSource;)I",
			ordinal = 1
		)
	)
	private int redirectThunderDelay(IntProvider provider, RandomSource random) {
		return RainDelay.thunderDelayProvider.sample(random);
	}

	@Redirect(
		method = "advanceWeatherCycle",
		at = @At(
			value = "INVOKE",
			target = "Lnet/minecraft/util/valueproviders/IntProvider;sample(Lnet/minecraft/util/RandomSource;)I",
			ordinal = 2
		)
	)
	private int redirectRainDuration(IntProvider provider, RandomSource random) {
		return RainDelay.rainDurationProvider.sample(random);
	}

	@Redirect(
		method = "advanceWeatherCycle",
		at = @At(
			value = "INVOKE",
			target = "Lnet/minecraft/util/valueproviders/IntProvider;sample(Lnet/minecraft/util/RandomSource;)I",
			ordinal = 3
		)
	)
	private int redirectRainDelay(IntProvider provider, RandomSource random) {
		return RainDelay.rainDelayProvider.sample(random);
	}

}