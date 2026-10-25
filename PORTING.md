# Minecraft version branches

The fork uses these branches:

| Branch | Purpose |
| --- | --- |
| `main` | An exact mirror of `joshxviii/plants-and-zombies:main`, with no fork-specific commits. |
| `codex/fabric-26.2-v1.5` | Official mod release `v1.5` (`7e89d925`), ported to Minecraft 26.2. |
| `codex/fabric-26.3-upstream` | The existing Minecraft 26.3 port, maintained separately. |
| `codex/archive-main-before-upstream-sync-2026-10-25` | Preserves the previous 26.2 fork and its history at `1ed273d3`. |

The 26.2 port intentionally starts from the published `v1.5` tag. It does not
include the later changes being developed for mod version 1.5.1 on upstream main.

## Updating the fork

1. Fetch `upstream` and update local `main` with `git merge --ff-only upstream/main`.
   Keep `main` free of port-specific changes, including this document.
2. On the relevant Minecraft branch, merge the chosen upstream release tag or
   commit. Resolve conflicts while preserving that branch's Minecraft versions,
   dependency versions, metadata and API adaptations.
3. Build and validate each version branch separately. Minecraft 26.3 API and
   resource-format changes must not be copied wholesale into the 26.2 branch.
4. Publish only the intended branches. An upstream update or a successful build
   does not automatically update the other Minecraft port.

## Building the Minecraft 26.2 port

Use Java 25 and the checked-in Gradle wrapper:

```powershell
./gradlew.bat build --console=plain
```

The mod JAR is `build/libs/plants-and-zombies-1.5+mc26.2.jar`. Fabric metadata
accepts Minecraft 26.2 and excludes 26.3. TerraBlender 26.2 is bundled as its
original nested JAR; Fabric API and Fabric Language Kotlin must be installed.

`build` also runs `validatePortResources`, which decodes world generation,
loot tables, recipes, trades, predicates and advancement criteria with Minecraft
26.2 codecs, and checks required tag and local data references. Mod registry
values use vanilla stand-ins and custom item components are excluded, so this
does not verify every mod identity or component value.

Build and resource validation do not launch Minecraft or establish gameplay,
rendering, multiplayer or saved-world compatibility. Those require a separate
in-game check with backups of any worlds used for testing.
