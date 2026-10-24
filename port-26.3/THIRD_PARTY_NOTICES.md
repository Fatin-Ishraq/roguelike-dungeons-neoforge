# Third-party notices — Roguelike Dungeons Reforged for NeoForge

This port redistributes only files that come from the upstream Roguelike Dungeons
project (GPL-3.0-only), plus the NeoForge mod metadata and the Gradle build
system. The itemised list below records the provenance and licence of every
non-source file in `src/main/resources`.

All items were carried over on **2026-10-24** from the upstream reference commit
recorded in [`NOTICE.md`](NOTICE.md).

## Upstream game data and assets

| Path | Provenance | Licence | Modified by this port |
|---|---|---|---|
| `assets/roguelike/icon.png` | Upstream project asset — an in-game screenshot of a generated dungeon rendered by the upstream author. No third-party art asset is embedded. | GPL-3.0-only (as part of the upstream project) | **Yes** (2026-10-24) — the upstream screenshot was upscaled from 128×128 to 512×512 with nearest-neighbour sampling and a "REFORGED" label band was drawn across the bottom. No other artwork was added. |
| `assets/roguelike/lang/en_us.json` | Upstream project file. | GPL-3.0-only | **Yes** — one key added, see [`PORTING_NOTES.md`](PORTING_NOTES.md). The upstream keys are kept unchanged. |
| `data/roguelike/loot_table/chests/*.json` (17 files) | Upstream project data files. | GPL-3.0-only | No — byte-identical to upstream. |
| `assets/modid/icon.png` | Leftover Fabric project-template asset. | — | Removed from this port; it was unused by the mod. |

## Minecraft / Mojang content

This mod contains **no** Mojang Studios or Microsoft assets, code or data. The
only appearance of Minecraft content is the pixel artwork inside
`assets/roguelike/icon.png`, which is an in-game screenshot produced by the
upstream author and is distributed here as part of the upstream project.

Minecraft itself, its assets and its EULA are the property of Mojang Studios and
Microsoft. This port neither redistributes nor grants any rights in them.

## Runtime dependencies (not redistributed)

| Dependency | Version for this target | Licence | Note |
|---|---|---|---|
| Minecraft | 26.3 | Minecraft EULA / proprietary | Not bundled; obtained by the user from Mojang. |
| NeoForge | 26.3.0.97 | LGPL-3.0-only | Not bundled; obtained by the user from the NeoForge distribution. |
| NeoForge ModDevGradle plugin | 2.0.148 | LGPL-3.0-only | Build-time only, pulled from `https://maven.neoforged.net/releases`. |
| JUnit 5 | 5.10.2 | EPL-2.0 | Build-time only, used by the test source set. |

The NeoForge build declares `neoforge` and `minecraft` as required dependencies
and declares **no** dependency on Fabric API. This mod does not require, and is
not compatible with, Fabric API.

## Upstream licence metadata inconsistency (noted, not resolved here)

Upstream ships two conflicting licence statements:

* `upstream/<target>/LICENSE` — the full text of the **GNU General Public
  License, version 3** (GPL-3.0-only).
* `upstream/<target>/src/main/resources/fabric.mod.json` — `"license":
  "CC0-1.0"`, which is stale metadata left over from an earlier release of the
  project.

This port follows the repository `LICENSE` file and declares **`GPL-3.0-only`**
everywhere (`neoforge.mods.toml`, `gradle.properties`, this repository's
`NOTICE.md`). The upstream `fabric.mod.json` is not copied into the port at all,
because it is the Fabric mod descriptor.

The CC0-1.0 claim was not verified against any statement from the upstream author
and should not be relied upon. Anyone redistributing this port must keep the
GPL-3.0-only terms of the upstream `LICENSE`.
