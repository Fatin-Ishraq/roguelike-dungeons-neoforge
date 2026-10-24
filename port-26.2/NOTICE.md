# NOTICE

## Roguelike Dungeons Reforged — Unofficial NeoForge Port

This is an **unofficial port** of the mod **Roguelike Dungeons** to NeoForge for
Minecraft 26.1.2, 26.2 and 26.3. It is distributed under the name
**Roguelike Dungeons Reforged**.

It is **not** endorsed, sponsored or approved by the original author, and it is
**not** an official release of Roguelike Dungeons. Please support the original
project.

### Original work and copyright

| | |
|---|---|
| Original mod | Roguelike Dungeons ("minecraft-roguelike") |
| Original author | Greymerk |
| Upstream repository | https://github.com/Greymerk/minecraft-roguelike |
| Upstream branch used as reference | `master` |
| Upstream reference commit for this target | `7923f3cda1be95b6c9995e5b7f879439d7db039c` |
| Upstream reference tree | `upstream/26.2` in this repository |
| Upstream mod version | 2.1.2 |
| This target | Minecraft 26.2, NeoForge 26.2.0.93, mod version `2.1.2+neoforge-26.2` |
| Authorship | Greymerk — the upstream sources carry no per-file copyright header; authorship is recorded in the upstream repository history and in upstream `fabric.mod.json` (`"authors": ["Greymerk"]`) |

Upstream Roguelike Dungeons is licensed under the **GNU General Public License,
version 3** (`GPL-3.0-only`). The full licence text is in [`LICENSE`](LICENSE).
This port is a derivative work and is distributed under the same terms.

### Port author

| | |
|---|---|
| NeoForge port | Fatin-Ishraq |
| Port date | 2026-10-24 |

### Modifications

This port modifies the upstream work. The complete, file-by-file list of
modifications is in [`PORTING_NOTES.md`](PORTING_NOTES.md). In summary, and all
dated **2026-10-24**:

* Rewrote the Fabric loader integration to the NeoForge event bus and mod bus:
  `Roguelike.java`, `commands/RoguelikeCommand.java`, `events/ChunkDungeonGenerate.java`,
  `events/ChunkLoadRoomFlagEvent.java`, `events/EntityLoadEvent.java`,
  `events/WorldTickGenerateRooms.java`, `gamerules/RoguelikeRules.java` and
  `state/ServerLoadListener.java`.
* Replaced `fabric.mod.json` with `META-INF/neoforge.mods.toml`.
* Upscaled `assets/roguelike/icon.png` from 128×128 to 512×512 and added a
  "REFORGED" label band, so the icon identifies this port. The image is still
  derived entirely from the upstream screenshot and stays GPL-3.0-only.
* Deleted the deprecated, inert `mixin/ChunkFeatureMixin.java` and
  `roguelike.mixins.json` (the upstream mixin list is empty, so the mixin never ran).
* Deleted the unused Fabric template client source tree and `assets/modid/icon.png`
  template asset.
* Added the NeoForge game-rule translation key `gamerule.roguelike.roguelike_dungeons_generate`
  to `assets/roguelike/lang/en_us.json` (the upstream key
  `gamerule.roguelikeDungeonsGenerate` is stale and is kept unchanged).
* Fixed eight statements in the upstream unit test
  `src/test/com/greymerk/roguelike/editor/boundingbox/BoundingBoxTest.java` that
  discarded the result of `Coord.add(...)` on the frozen `Coord.ZERO` constant.
  This is a test-only change; no mod behaviour is affected.
* Added the NeoForge/ModDevGradle build system (`build.gradle`, `settings.gradle`,
  `gradle.properties`, Gradle wrapper) for this target.

All gameplay logic, dungeon generation, layouts, themes, loot behaviour,
configuration and commands are unchanged from the upstream reference commit
listed above.

### Corresponding source

This port is distributed under `GPL-3.0-only`. Section 4 of the GPL requires the
complete corresponding source of the modified version to be made available.

* The public source repository for this port is
  <https://github.com/Fatin-Ishraq/roguelike-dungeons-neoforge>.
* The **complete corresponding source** of this target is the content of this
  directory, including `src/main`, `src/test`, `build.gradle`, `settings.gradle`
  and `gradle.properties`. The unmodified upstream reference for this target is
  `upstream/26.2` in the same repository, at the commit recorded above.
* A machine-readable archive of the same source is produced by the build:
  `./gradlew build` also writes `roguelike-dungeons-neoforge-<version>-sources.jar`,
  which contains the complete Java sources and resources of this port.
* Binary releases of this port must be accompanied by the corresponding source
  (or a written offer to provide it) as described below.

### Written offer (applies to binary distribution)

If you distribute a binary build of this port, you must convey the recipient
the opportunity to obtain the corresponding source. The corresponding source is
available, at no charge, from
<https://github.com/Fatin-Ishraq/roguelike-dungeons-neoforge> — the repository
that contains this directory. You
must also convey a copy of this `NOTICE`, the `LICENSE`, and the
`THIRD_PARTY_NOTICES.md`.

The author's own binary distribution of this port (Modrinth and CurseForge) is
accompanied by the same offer: under GPL section 6b the corresponding source is
offered to the public from the URL above, for at least three years and for as
long as the binaries remain available.

### Third-party content

See [`THIRD_PARTY_NOTICES.md`](THIRD_PARTY_NOTICES.md) for asset-specific and
third-party notices.

### Game mod disclaimer

Roguelike Dungeons and this port are not affiliated with, endorsed by, or
associated with Mojang Studios or Microsoft. Minecraft is a trademark of
Mojang Studios.
