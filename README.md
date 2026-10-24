# Roguelike Dungeons Reforged

Procedurally generated dungeon complexes for **NeoForge** — a port of Greymerk's
[Roguelike Dungeons](https://github.com/Greymerk/minecraft-roguelike) to Minecraft
**26.1.2**, **26.2** and **26.3**.

This is a port, not an official release: it is not endorsed by, sponsored by, or
affiliated with the original author. Please support the original project.

| Directory | Minecraft | NeoForge | Mod version | Upstream reference |
|---|---|---|---|---|
| [`port-26.1.2`](port-26.1.2) | 26.1.2 | 26.1.2.122 | `2.1.2+neoforge-26.1.2` | `7e6f2a534cb1f6f0d04205d0f844cf2c852e755e` |
| [`port-26.2`](port-26.2) | 26.2 | 26.2.0.93 | `2.1.2+neoforge-26.2` | `7923f3cda1be95b6c9995e5b7f879439d7db039c` |
| [`port-26.3`](port-26.3) | 26.3 | 26.3.0.97 | `2.1.2+neoforge-26.3` | `e6d9a458eec3df0e17114762c96136feba6f2f4e` |

Each directory is an **independent Gradle project** for one Minecraft version. A
build of one target is not a build of the others, and each jar declares a strict
Minecraft range. **Fabric API is not required** — the mods declare dependencies on
`minecraft` and `neoforge` only.

## Layout

| Path | Contents |
|---|---|
| `port-<version>/` | The NeoForge port: sources, resources, build scripts and per-target notices |
| `upstream/<version>/` | The unmodified upstream Fabric sources each target was derived from, kept for provenance |
| [`DESCRIPTION.md`](DESCRIPTION.md) | The listing description published with the binaries |

## Building

Requires **JDK 25** and network access to `maven.neoforged.net` and Maven Central.

```bash
cd port-26.3
./gradlew build      # jar + sources jar in build/libs
./gradlew test       # 73 upstream tests against the NeoForge classpath
./gradlew runClient  # development client
./gradlew runServer  # development dedicated server
```

`./gradlew build` also writes `roguelike-dungeons-neoforge-<version>-sources.jar`,
which contains the complete Java sources and resources of that target.

## Verification

Each target has been built, unit-tested (73 tests, 0 failures) and run as a
dedicated server with focused world-generation checks: natural dungeon placement,
the `roguelike:roguelike_dungeons_generate` game rule (including the on/off A/B
comparison), the `/roguelike` commands and the config round-trip. 26.3 additionally
verified in a client.

## Documentation

* [`port-26.1.2/README.md`](port-26.1.2/README.md) — features, commands and configuration (per target).
* [`port-26.1.2/NOTICE.md`](port-26.1.2/NOTICE.md) — attribution, modifications and the source offer.
* [`port-26.1.2/PORTING_NOTES.md`](port-26.1.2/PORTING_NOTES.md) — file-by-file list of changes.
* [`port-26.1.2/THIRD_PARTY_NOTICES.md`](port-26.1.2/THIRD_PARTY_NOTICES.md) — asset and dependency notices.

## Licence

**GPL-3.0-only**, the same licence as the upstream mod. See
[`port-26.1.2/LICENSE`](port-26.1.2/LICENSE) and the per-target `NOTICE.md`, which
are also bundled inside every jar.

* Original mod, code and assets: **Greymerk** — <https://github.com/Greymerk/minecraft-roguelike>
* NeoForge port: **Fatin-Ishraq**, 2026-10-24.

Not affiliated with, endorsed by, or associated with Mojang Studios or Microsoft.
Minecraft is a trademark of Mojang Studios.
