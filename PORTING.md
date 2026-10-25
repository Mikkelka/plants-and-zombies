# Maintaining the Minecraft ports

## Branch structure

| Branch | Purpose |
| --- | --- |
| [`main`](https://github.com/Mikkelka/plants-and-zombies/tree/main) | Exact mirror of [`joshxviii/plants-and-zombies:main`](https://github.com/joshxviii/plants-and-zombies/tree/main). No fork-specific commits or port merges. |
| [`codex/fabric-26.2-v1.5`](https://github.com/Mikkelka/plants-and-zombies/tree/codex/fabric-26.2-v1.5) | Maintained Minecraft 26.2 port. Starts from the official `v1.5` release, `7e89d925`. |
| [`codex/fabric-26.3-upstream`](https://github.com/Mikkelka/plants-and-zombies/tree/codex/fabric-26.3-upstream) | Maintained Minecraft 26.3 port. Its initial upstream baseline is `ddf05fb7`, including fixes after `v1.5`. |
| [`codex/archive-main-before-upstream-sync-2026-10-25`](https://github.com/Mikkelka/plants-and-zombies/tree/codex/archive-main-before-upstream-sync-2026-10-25) | Historical 26.2 fork at `1ed273d3`, preserved before replacing fork main with upstream. Not an active port. |

Use the two port branches for ongoing work, including later mod releases. The
`v1.5` suffix in the 26.2 branch name records its starting point; the current mod
version comes from `gradle.properties`. Do not merge either port back into main
or copy Minecraft-specific changes wholesale between the ports.

## Current versions and validation

| | Minecraft 26.2 port | Minecraft 26.3 port |
| --- | --- | --- |
| Mod version | `1.5+mc26.2` | `1.5+mc26.3` |
| Java | 25 | 25 |
| Gradle wrapper / Loom | 9.6.1 / 1.17.17 | 9.8.0 / 1.19.3 |
| Fabric Loader used to build | 0.19.3 | 0.19.5 |
| Fabric API used to build | `0.156.0+26.2` | `0.162.0+26.3` |
| TerraBlender (bundled) | `26.2-26.2.0.0.2` | `26.3-26.3.0.0.9` |

On 2026-10-25 the user confirmed that the Minecraft 26.2 build works in game.
The build also passed resource validation (377 resources/criteria) and a static
check of 40 Mixin member, descriptor and instruction references. Specific
multiplayer or saved-world migration scenarios were not separately recorded.
This report applies to the 26.2 port; it does not establish gameplay validation
for the independently maintained 26.3 port.

The 26.2 port currently contains the released `v1.5` content, not the later
1.5.1 work on upstream main. Updating main does not automatically update a port.

## Installing a port

Use the JAR for your exact Minecraft version. Install Fabric Loader, the
matching Fabric API and Fabric Language Kotlin. TerraBlender is bundled as its
original nested JAR, including its own metadata and nested libraries.
`iris_version` is an optional compile-only API dependency; Iris is not required
for ordinary play and is not bundled. Shader compatibility needs a separate
check with an Iris version intended for the target Minecraft version.

Do not load both port JARs together: they have the same mod ID, `plantz`.
A successful GitHub Actions build provides JARs under that run's **Artifacts**;
download the mod JAR, not the `-sources.jar`.

## Taking an upstream update

Start with a clean working tree and preserve user files. Sync the mirror:

```powershell
git fetch origin
git fetch upstream --tags
git switch main
git merge --ff-only upstream/main
git push origin main
```

Then switch to the Minecraft branch being updated. Review and merge the chosen
upstream release tag or commit into that branch; do not automatically take all
of upstream main when the intended base is a published release. Record the
selected upstream baseline in this document and the fork section of
`CHANGELOG.md`.

When resolving conflicts, preserve the target Minecraft version, compatible
Fabric/Loom/Gradle/TerraBlender dependencies and `fabric.mod.json` version range.
Recheck movement controls, advancement predicates, rendering, knockback,
world generation and loot formats against the target Minecraft API. Keep
upstream gameplay and assets unless a compatibility change requires otherwise.
Minecraft 26.3 resource formats are not interchangeable with 26.2 formats.

Set `mod_version` to the imported upstream version with `+mc26.2` or `+mc26.3`.
Update the version table and README when dependencies or the baseline change.
Do not overwrite an already distributed version with materially different code;
use an additional compatibility/build suffix when needed.

## Building and checking a port

Use Java 25 and the wrapper from the selected branch:

```powershell
./gradlew.bat build --console=plain
git diff --check
```

On Linux use `./gradlew build --console=plain`. Outputs are in `build/libs/`:

- 26.2: `plants-and-zombies-1.5+mc26.2.jar`
- 26.3: `plants-and-zombies-1.5+mc26.3.jar`

Names above describe the current versions. `build/libs/` may still contain older
builds, so check the versioned filename and the embedded `fabric.mod.json`.
Inspect its Minecraft range, entrypoints and nested TerraBlender JAR before
publishing. Keep build output out of Git; use CI artifacts for downloads.

`build` runs `validatePortResources`, which decodes world generation, loot
tables, recipes, trades, predicates and advancement criteria with that branch's
Minecraft codecs. It also checks required tags and local data references.
Mod registry values use vanilla stand-ins and custom item components are
excluded, so it does not verify every mod identity or component value.

Do not start Minecraft or run `runClient` as part of routine build validation
unless the user asks. Record in-game confirmation separately from build/data
validation, and retain which port/build was tested.

Commit and push only the intended branch, then inspect the GitHub Actions build
for that exact commit. Treat a queued build as pending and a completed failed
build as a failure. Never merge a port into main to publish it.
