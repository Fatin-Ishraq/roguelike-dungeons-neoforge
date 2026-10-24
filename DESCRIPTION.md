# Roguelike Dungeons Reforged

Procedurally generated dungeon complexes for **NeoForge**, ported from Greymerk's
**Roguelike Dungeons**. Stone towers rise out of the plains and dungeons open
underneath them: themed rooms, corridors, traps, scaled mobs and profile-based
loot, all placed a configurable distance from the nearest village.

Everything about how dungeons are generated — layouts, themes, rooms, loot, the
game rule, the configuration file and the commands — is carried over unchanged
from the upstream Fabric release. This port changes the loader, not the mod.

> Roguelike Dungeons is created by **Greymerk**. This is a port to NeoForge and is
> not endorsed by, sponsored by, or affiliated with the original author. Please
> support the original project: <https://github.com/Greymerk/minecraft-roguelike>

## Versions

One build per Minecraft version. Each jar declares a strict Minecraft range, so
install the one that matches your instance.

| Minecraft | NeoForge | Mod version | Java |
|---|---|---|---|
| 26.1.2 | 26.1.2.122 | `2.1.2+neoforge-26.1.2` | 25 |
| 26.2 | 26.2.0.93 | `2.1.2+neoforge-26.2` | 25 |
| 26.3 | 26.3.0.97 | `2.1.2+neoforge-26.3` | 25 |

**Requirements:** NeoForge only. No Fabric API, no library mods, no optional
dependencies — the mod declares dependencies on `minecraft` and `neoforge` alone.

## Features

* **Dungeon generation** — themed dungeon complexes placed in newly generated
  chunks, at a configurable chunk distance from villages.
* **Rooms, corridors and entrances** — the full upstream layout system, including
  themed room sets and difficulty scaling by depth.
* **Loot** — themed chest contents (armour, weapons, tools, blocks, food, brewing
  supplies, ores, music discs, starter kits) with roguelike item profiles, names
  and lore.
* **Mob scaling and equipment** — dungeon mobs are scaled by difficulty and
  equipped with profile-based gear.
* **Game rule** — `roguelike:roguelike_dungeons_generate` (default `true`) turns
  generation on or off.
* **Configuration** — `config/roguelike-dungeons.json`, hot-reloadable.

## Commands

Both commands require gamemaster permission (level 2).

| Command | Effect |
|---|---|
| `/roguelike dungeon here` | Generate a dungeon at the caller's position |
| `/roguelike config reload` | Reload `config/roguelike-dungeons.json` |

The game rule keeps upstream's namespaced identifier, so the full literal is
required:

```
/gamerule roguelike:roguelike_dungeons_generate false
/gamerule roguelike:roguelike_dungeons_generate true
```

## Configuration

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

## Notes

* **Existing chunks are not retro-generated.** Dungeons appear in chunks generated
  after the mod is installed. Use `/roguelike dungeon here` to place one where you
  already are.
* **Do not install alongside the Fabric build.** Both use the mod id `roguelike`,
  and they are alternative builds of the same mod, not add-ons.
* **Dedicated servers:** rooms are built over level ticks, so a server configured
  to pause when empty stops generation. Set `pause-when-empty-seconds=0` in
  `server.properties` if you test an unattended world.
* **Feature parity:** this port matches upstream **2.1.2**, the newest version
  published on Modrinth. Upstream tagged a Fabric-only 2.1.3 on 2026-10-13 that adds
  a `mending_loot` config option; a matching build will follow here.

## Credits and licence

* **Original mod, code and assets:** Greymerk — <https://github.com/Greymerk/minecraft-roguelike>
* **NeoForge port:** Fatin-Ishraq, 2026-10-24.

Distributed under the **GNU General Public License, version 3** (`GPL-3.0-only`),
the same licence as the upstream mod. The jar contains `LICENSE`, `NOTICE.md`,
`PORTING_NOTES.md` and `THIRD_PARTY_NOTICES.md`, which record the attribution and
every modification made by this port. The complete corresponding source — including
the build scripts — is at
<https://github.com/Fatin-Ishraq/roguelike-dungeons-neoforge>, and a `-sources.jar`
is published with each release.

Roguelike Dungeons and this port are not affiliated with, endorsed by, or associated
with Mojang Studios or Microsoft. Minecraft is a trademark of Mojang Studios.
