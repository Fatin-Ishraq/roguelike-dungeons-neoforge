# Porting notes — Roguelike Dungeons Reforged for NeoForge

Unofficial NeoForge port of [Roguelike Dungeons](https://github.com/Greymerk/minecraft-roguelike)
by Greymerk, distributed under the name **Roguelike Dungeons Reforged**.

| | |
|---|---|
| Upstream repository | https://github.com/Greymerk/minecraft-roguelike |
| Upstream branch | `master` |
| Upstream reference commit for this target | `7923f3cda1be95b6c9995e5b7f879439d7db039c` |
| Upstream reference tree | `upstream/26.2` |
| Upstream mod version | 2.1.2 |
| This target | Minecraft 26.2, NeoForge 26.2.0.93, mod version `2.1.2+neoforge-26.2` |
| Port date | 2026-10-24 |

All modifications below were made on **2026-10-24**.

## Summary

The upstream mod is a Fabric mod. Its gameplay code — dungeon generation, layouts,
themes, loot, configuration and commands — is loader-agnostic and was carried over
unchanged. The port rewrites only the eight loader-coupled files, swaps the mod
metadata for NeoForge, and adjusts the unit-test setup so the upstream test suite
runs against the NeoForge classpath.

## Modified files

### `src/main/java/com/greymerk/roguelike/Roguelike.java`

Replaced the Fabric entrypoint with a NeoForge `@Mod` constructor that registers
the equivalent listeners on the game bus and the mod bus:

| Upstream (Fabric) | This port (NeoForge) |
|---|---|
| `ServerLifecycleEvents.SERVER_STARTED` | `LevelEvent.Load` |
| `ServerChunkEvents.CHUNK_GENERATE` | `ChunkEvent.Load` (guarded by `isNewChunk()`) |
| `ServerTickEvents.START_WORLD_TICK` | `LevelTickEvent.Pre` |
| `EntityJoinLevelEvent` (Fabric API) | `EntityJoinLevelEvent` (NeoForge) |
| `ServerLifecycleEvents` command registration | `RegisterCommandsEvent` |

### `src/main/java/com/greymerk/roguelike/gamerules/RoguelikeRules.java`

Fabric's `GameRule` was constructed with `GameRuleCategory.MISC` and registered by
direct registry mutation. NeoForge requires game rules to be registered through the
mod bus (`RegisterEvent` on `Registries.GAME_RULE`), so the rule is now registered
with a `ResourceKey` of `roguelike:roguelike_dungeons_generate`. The rule's default
value (`true`) and category are unchanged.

The identifier itself is upstream's: upstream Fabric registers the rule with
`Identifier.fromNamespaceAndPath("roguelike", "roguelike_dungeons_generate")`, and
the port uses the same identifier. Because Minecraft keeps the namespace for any
game rule outside the `minecraft` namespace, the rule's command name is the
qualified `roguelike:roguelike_dungeons_generate` on both loaders:
`/gamerule roguelike:roguelike_dungeons_generate false`. The unqualified
`roguelike_dungeons_generate` is not a valid argument in vanilla Minecraft and
never was on Fabric either.

### `src/main/java/com/greymerk/roguelike/commands/RoguelikeCommand.java`

The command tree is unchanged (`/roguelike dungeon here`, `/roguelike config reload`).
It is now registered through `RegisterCommandsEvent` instead of Fabric's
`ServerLifecycleEvents`.

### `src/main/java/com/greymerk/roguelike/events/ChunkDungeonGenerate.java`

Fabric's `ServerChunkEvents.CHUNK_GENERATE` fires *before* the chunk is added to the
level. The NeoForge equivalent, `ChunkEvent.Load`, fires *after*, so the handler
returns early unless `event.isNewChunk()` is true. This preserves upstream's
"generate only once, at first generation" semantics: a chunk that is merely reloaded
from disk does not re-run the placement check.

### `src/main/java/com/greymerk/roguelike/events/ChunkLoadRoomFlagEvent.java`

Fabric's `ServerChunkEvents.CHUNK_LOAD` fires on the server only. NeoForge raises
`ChunkEvent.Load` on the client as well, so the handler now returns early unless the
level is a `ServerLevel`. The flag itself is set exactly as upstream does.

### `src/main/java/com/greymerk/roguelike/events/EntityLoadEvent.java`

The callback signature changed from Fabric's `EntityJoinLevelEvent` to NeoForge's
`EntityJoinLevelEvent`; the body (skeleton/zombie mining-fatigue handling, difficulty
scaling and `MonsterProfile.equip`) is unchanged.

### `src/main/java/com/greymerk/roguelike/events/WorldTickGenerateRooms.java`

The tick source changed from `ServerTickEvents.START_WORLD_TICK` to
`LevelTickEvent.Pre`. The body — dungeon layout generation, room generation, filter
application and statistics merging — is unchanged.

### `src/main/java/com/greymerk/roguelike/state/ServerLoadListener.java`

The Fabric callback took a `MinecraftServer`; the NeoForge equivalent is
`LevelEvent.Load`. The upstream body of this listener is empty, so the ported
listener is empty too.

## Removed files

* `src/main/java/com/greymerk/roguelike/mixin/ChunkFeatureMixin.java` — upstream
  marks this class `@Deprecated`, and upstream `roguelike.mixins.json` declares an
  **empty** `mixins: []` list, so the mixin was never applied and never ran. It is
  removed rather than ported, because porting it would add behaviour that upstream
  does not have.
* `src/main/resources/roguelike.mixins.json` — the mixin configuration for the above.
* `src/main/resources/fabric.mod.json` — replaced by `META-INF/neoforge.mods.toml`.
* `src/client/` — upstream carries only the Fabric template's client tree:
  `com/example/ExampleModClient.java`, `com/example/mixin/client/ExampleClientMixin.java`
  and `modid.client.mixins.json`. Neither class ever runs: `fabric.mod.json` declares
  an **empty** `client` entrypoint list, and `modid.client.mixins.json` is not listed
  in `fabric.mod.json`'s `mixins` array. The mod has no client-side code, so the tree
  is removed rather than ported.
* `src/main/resources/assets/modid/icon.png` — leftover Fabric template asset,
  unused by the mod.

## Added files

* `META-INF/neoforge.mods.toml` — NeoForge mod metadata. Declares `javafml`,
  `GPL-3.0-only`, the `roguelike` mod id, and required dependencies on `neoforge`
  and `minecraft` only. **There is no Fabric API dependency.** The mod icon is
  declared with `logoFile`; NeoForge 26.2 deprecated that property in favour of
  `iconFile` (for square icons), so the 26.2 and 26.3 targets declare `iconFile`
  instead. That is the only metadata difference between the three targets.
* `build.gradle`, `settings.gradle`, `gradle.properties`, `gradle/wrapper/*` — the
  ModDevGradle (`net.neoforged.moddev` 2.0.148) build for this target.

## Changed files

### `src/main/resources/assets/roguelike/icon.png`

The upstream 128×128 in-game screenshot was upscaled to 512×512 with
nearest-neighbour sampling (keeping the pixel-art look) and a "REFORGED" label
band was drawn across the bottom, so that the listing and in-game icon identify
this port rather than being byte-identical to the upstream release. No new
artwork was added and the image remains derived entirely from the upstream
screenshot, so it stays GPL-3.0-only like the rest of the upstream project.

### `src/main/resources/assets/roguelike/lang/en_us.json`

Added one key:

```json
"gamerule.roguelike.roguelike_dungeons_generate": "Roguelike Dungeons generate randomly"
```

Upstream ships `"gamerule.roguelikeDungeonsGenerate"`. Minecraft derives a game
rule's translation key from its registry name, so on NeoForge the key that is
actually used is `gamerule.<namespace>.<path>` =
`gamerule.roguelike.roguelike_dungeons_generate`. The upstream key is stale for
modern Minecraft and is left in place so that nothing upstream ships is dropped.

### `src/test/com/greymerk/roguelike/editor/boundingbox/BoundingBoxTest.java`

Eight statements of the form

```java
pos.add(dir, 1);
```

became

```java
pos = pos.add(dir, 1);
```

`Coord.ZERO` is frozen, so `Coord.add(...)` returns a *new* coordinate instead of
mutating in place. Upstream discards that return value, so the test compares the
unchanged origin and fails. The fix is confined to the test source set; no mod
behaviour is changed.

## Build-system notes

* The test source set is rooted at `src/test` (upstream's layout) rather than
  `src/test/java`, and it is given the modding dependencies with
  `neoForge.addModdingDependenciesTo(sourceSets.test)` so the upstream tests can
  resolve Minecraft types.
* `neoForge.unitTest { enable(); testedMod = mods.roguelike }` is required. The
  upstream tests read vanilla registries directly, and vanilla registries cannot be
  bootstrapped unless the FML runtime is present in the test JVM
  (`Bootstrap.bootStrap()` fails with *"There is no current FML Loader"* otherwise).
  Enabling NeoForge's unit-test support brings that runtime up.
* The jar task attaches `LICENSE` to the jar as
  `LICENSE_roguelike-dungeons-neoforge`.
* `withSourcesJar()` is enabled so that every binary distribution ships the
  corresponding source, as GPL-3.0-only requires.

## Known deltas from upstream

Upstream `master` has two commits **after** the 26.3 release that are not part of
any released Fabric build (they bump the upstream version to 2.1.3) and are
therefore **not** included in this port:

| Commit | Date | Subject | Relevance |
|---|---|---|---|
| `d74062f9` | 2026-10-12 | added mending_loot config option | Adds a config option; not present in the 2.1.2 builds this port targets. |
| `bcad259f` | 2026-10-16 | limit dungeon generate to 50 rooms per tick | Touches `events/WorldTickGenerateRooms.java`, which this port rewrites. This port keeps the released 2.1.2 behaviour (no per-tick room cap). |

If upstream publishes a 2.1.3 Fabric build, these two changes should be re-based
onto the NeoForge listener wiring.
