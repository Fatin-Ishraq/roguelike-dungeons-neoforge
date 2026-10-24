# Roguelike Dungeons Reforged

An **unofficial** NeoForge port of [Roguelike Dungeons](https://github.com/Greymerk/minecraft-roguelike)
by **Greymerk** for Minecraft **26.1.2**, **26.2** and **26.3**.

> This port is not endorsed, sponsored or approved by Greymerk, and it is not an
> official release of Roguelike Dungeons. Please support the original project:
> <https://github.com/Greymerk/minecraft-roguelike>

The mod adds randomly generated dungeon complexes to the world. Everything about
how dungeons are generated — layouts, themes, rooms, loot, the game rule, the
configuration file and the commands — is carried over from the upstream release.

## Targets

This repository contains one independent project per Minecraft target. Each is
built and verified separately; a build of one target is not a build of the others.

| Directory | Minecraft | NeoForge | Mod version | Upstream reference |
|---|---|---|---|---|
| [`port-26.1.2`](../port-26.1.2) | 26.1.2 | 26.1.2.122 | `2.1.2+neoforge-26.1.2` | upstream `7e6f2a534cb1f6f0d04205d0f844cf2c852e755e` |
| [`port-26.2`](../port-26.2) | 26.2 | 26.2.0.93 | `2.1.2+neoforge-26.2` | upstream `7923f3cda1be95b6c9995e5b7f879439d7db039c` |
| [`port-26.3`](../port-26.3) | 26.3 | 26.3.0.97 | `2.1.2+neoforge-26.3` | upstream `e6d9a458eec3df0e17114762c96136feba6f2f4e` |

The upstream reference trees are kept unmodified in
[`../upstream/26.1.2`](../upstream/26.1.2), [`../upstream/26.2`](../upstream/26.2)
and [`../upstream/26.3`](../upstream/26.3).

**Fabric API is not required.** The NeoForge builds declare dependencies on
`minecraft` and `neoforge` only.

## Building

Requirements: **JDK 25** (the toolchain is pinned in `build.gradle`) and network
access to `maven.neoforged.net` and Maven Central.

```bash
cd port-26.2
./gradlew build
```

The mod jar is written to `build/libs/`, alongside a `-sources.jar` containing the
complete corresponding source (required by GPL-3.0-only).

## Running

```bash
./gradlew runClient     # development client
./gradlew runServer     # development dedicated server
```

## Testing

The upstream JUnit suite is included and runs against the NeoForge classpath:

```bash
./gradlew test
```

NeoForge's unit-test support is enabled because the upstream tests read vanilla
registries directly, and vanilla registries cannot be bootstrapped without the
FML runtime present in the test JVM.

## Features

* **Procedurally generated dungeons** placed in the world at a configurable
  distance from villages, with themed rooms, corridors and entrances.
* **Loot**: themed chest contents, including armour, weapons, tools, blocks,
  food, brewing supplies, ores, music discs and starter kits.
* **Mob scaling and equipment**: dungeon mobs are scaled by difficulty and
  equipped with profile-based gear.
* **Game rule** `roguelike:roguelike_dungeons_generate` (default `true`) — turns
  dungeon generation on or off. The rule keeps upstream's namespaced identifier, so
  the command is `/gamerule roguelike:roguelike_dungeons_generate` — the same on
  Fabric and on NeoForge.
* **Configuration file** `config/roguelike-dungeons.json`:

  | Key | Type | Default | Meaning |
  |---|---|---|---|
  | `mob_drops` | boolean | `true` | Whether dungeon mobs drop loot |
  | `frequency` | double | `1.0` | Dungeon generation frequency |
  | `debug` | boolean | `false` | Write per-dungeon loot statistics to `<world>/debug` |
  | `below_sea_level` | boolean | `true` | Whether dungeons may generate below sea level |
  | `rooms_per_level` | integer | `20` | Maximum number of rooms per level |
  | `roguelike_loot` | boolean | `true` | Whether to use the roguelike loot tables |
  | `deterministic` | boolean | `true` | Whether generation is deterministic |
  | `distance_to_village` | integer | `6` | Chunk distance from a village at which dungeons may spawn |

* **Commands** (permission level 2 / gamemaster):
  * `/roguelike dungeon here` — generate a dungeon at the caller's position.
  * `/roguelike config reload` — reload `config/roguelike-dungeons.json`.

## Credits

* **Original mod and code**: Greymerk — <https://github.com/Greymerk/minecraft-roguelike>
* **NeoForge port**: Fatin-Ishraq, 2026-10-24.

## Licence

Licensed under the **GNU General Public License, version 3** (`GPL-3.0-only`),
the same licence as the upstream mod.

* [`LICENSE`](LICENSE) — the full licence text.
* **Source repository:** <https://github.com/Fatin-Ishraq/roguelike-dungeons-neoforge>
* [`NOTICE.md`](NOTICE.md) — attribution, modifications, and the corresponding
  source offer.
* [`PORTING_NOTES.md`](PORTING_NOTES.md) — the file-by-file list of changes.
* [`THIRD_PARTY_NOTICES.md`](THIRD_PARTY_NOTICES.md) — asset and dependency
  notices.

Roguelike Dungeons and this port are not affiliated with, endorsed by, or
associated with Mojang Studios or Microsoft. Minecraft is a trademark of
Mojang Studios.
