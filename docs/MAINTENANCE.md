# Maintaining the fork and saved versions

Run commands from the repository root; build paths below are relative to that root.

## Branches and tags

The user selected this workflow on 2026-11-07. Main contains current fork
development, the original code has its own mirror branch, and tags preserve
previous working versions. This replaces the earlier two-port-branch workflow.

| Ref | Purpose |
| --- | --- |
| [`main`](https://github.com/Mikkelka/plants-and-zombies/tree/main) | Current fork development, starting with our corrected Minecraft 26.3 compat1 version. |
| [`codex/upstream-mirror`](https://github.com/Mikkelka/plants-and-zombies/tree/codex/upstream-mirror) | Exact mirror of `joshxviii/plants-and-zombies:26.3`, currently `fbc1a3b6`. No fork code or docs. |
| Tag [`26.2`](https://github.com/Mikkelka/plants-and-zombies/tree/26.2) | Saved Minecraft 26.2 port at `c083f699`, mod `1.5.2+mc26.2`. |
| Tag [`26.3`](https://github.com/Mikkelka/plants-and-zombies/tree/26.3) | Saved Minecraft 26.3 compat1 baseline, mod `1.6-dev.fbc1a3b6.compat1+mc26.3`, including this workflow migration. |

Tags are immutable snapshots of complete source/history, not moving aliases for
"latest". Future updates receive new descriptive tags, for example
`26.3-compat2` or a tag including the new mod version. The tag `26.2` is not an
ancestor of the 26.3 main history; Git tags can preserve either development line.
Do not merge incompatible 26.2 code into main merely to include its tag.

The old `codex/fabric-26.2-v1.5` and `codex/fabric-26.3-upstream` branches remain
as legacy references. Current development has moved to main. The latter branch
still supplies historical draft PR #74 and does not automatically follow main.
Do not delete those references or historical archive tags without a request.

The mirror follows upstream `26.3` explicitly. Do not change its source just
because upstream changes its default branch. It intentionally excludes our
Fabric/initialization fixes; use the development build for the user's setup.

## Separate maintenance chats

This chat maintains current development on main, presently Minecraft 26.3.
Minecraft 26.2 is preserved for possible later use. If the user requests a 26.2
fix, its chat should start from tag `26.2` in a separate worktree/branch.

The current working baseline is `1.6-dev.fbc1a3b6.compat1+mc26.3`, runtime source
`63415ad4`, upstream `fbc1a3b6`. The user confirmed that it works on 2026-11-07.
Preserve the TerraBlender callback and other compatibility changes when importing
upstream updates. Import changes when requested; do not automatically change the
Minecraft target or update preserved versions.

Before editing or building, check the branch, working tree and target version.
Chats using one checkout share its active branch. Use separate worktrees for
concurrent work and never switch a checkout another chat is using. Keep build
artifacts, worlds, user files and untracked data safe.

For each update, record the upstream baseline, mod version, requirements and
validation, then commit on main. Push when requested. Tag chosen snapshots;
create/update a PR from the intended development branch only when requested.

## Current versions and validation

| | Minecraft 26.2 port | Minecraft 26.3 port |
| --- | --- | --- |
| Mod version | `1.5.2+mc26.2` (last observed) | `1.6-dev.fbc1a3b6.compat1+mc26.3` |
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

The 26.3 branch was rebuilt on 2026-11-07 after importing upstream `26.3` at
`fbc1a3b6`. The build, 464 resource/criterion checks and JAR inspection passed, with no Minecraft
launch or gameplay test. Existing 26.2 artifacts were preserved. See
[Minecraft 26.3 technical notes](ports/26.3.md) for the snapshot contents and compatibility fixes.

The user's subsequent startup test exposed premature TerraBlender registration
in the first snapshot (`TerraBlender.CONFIG` was null). `compat1` moves it to the
`terrablender` entrypoint. Its build/data checks passed; the corrected build
was then confirmed working by the user on 2026-11-07 in the Modrinth test
profile with Minecraft 26.3, Loader 0.19.5 and Fabric API 0.162.0+26.3.
This confirms the reported startup problem is resolved. Dedicated-server,
save migration, raids and other broad gameplay coverage were not separately
reported. `compat1` is the current working 26.3 baseline.

Upstream's imported translations still contain missing/unused keys relative to
the 449-key English file. The separate language check reports these differences;
they are not a failure of the build/resource validation. See the [26.3 validation notes](ports/26.3.md#validation).

The 26.2 branch was separately updated to 1.5.2 by its maintenance chat.
The 26.2 snapshot remains unchanged; main updates do not alter existing tags.

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

Start clean, inspect worktrees and fetch the remotes:

```powershell
git fetch origin --prune
git fetch upstream --tags
```

In a dedicated mirror checkout, update only from the selected original branch:

```powershell
git switch codex/upstream-mirror
git merge --ff-only upstream/26.3
git push origin codex/upstream-mirror
```

Verify that the mirror commit and tree equal the selected upstream commit. Keep
fork changes out of the mirror. In the development checkout, when requested:

```powershell
git switch main
git merge codex/upstream-mirror
```

Review the selected upstream release/commit before merging; do not automatically
import development content when the request is for a published release. Preserve
the intended Minecraft/Fabric/Loom/Gradle/TerraBlender versions and version range,
and recheck APIs, rendering, world generation, loot and advancement data. Update
[README](../README.md), this document, the [26.3 technical notes](ports/26.3.md)
and the [fork changelog](../CHANGELOG.md) as appropriate.

Set the mod version to the imported version plus the Minecraft/build suffix.
Use a new suffix for changed distributed builds. After build validation and
chosen manual testing, save a new annotated tag; never replace existing tags.

## Returning to a saved version

Use an isolated worktree to inspect/build an old version while main stays put:

```powershell
git worktree add --detach ../plants-and-zombies-26.2 26.2
```

Tag `26.3` works the same way. To make a fix, create a new branch in that
worktree. Each snapshot uses its own Java/Gradle/dependency settings and build
output; copy only the requested artifacts into the designated delivery folder.
Tags preserve source; ready-made JAR downloads come from retained CI artifacts
(subject to GitHub artifact retention) or a separately requested release. A tag
alone does not attach a binary or guarantee it can be downloaded forever.

## Building and checking a port

Use Java 25 and the wrapper from the selected branch:

```powershell
./gradlew.bat build --console=plain
git diff --check
```

On Linux use `./gradlew build --console=plain`. Outputs are in `build/libs/`:

- 26.2: `plants-and-zombies-1.5.2+mc26.2.jar` (last observed)
- 26.3: `plants-and-zombies-1.6-dev.fbc1a3b6.compat1+mc26.3.jar`

Names above describe the current versions. `build/libs/` may still contain older
builds, so check the versioned filename and the embedded `fabric.mod.json`.
Inspect its Minecraft range, entrypoints and nested TerraBlender JAR before
publishing. Keep build output out of Git; use CI artifacts for downloads.

For local delivery, keep the latest runtime JAR and matching `-sources.jar` for
each of 26.2 and 26.3 together in the designated `build/libs` folder. Rebuilding
one port must preserve the other port's files. Do not run an unconditional
`clean` there; use an isolated build directory or preserve and restore the other
version first. When cleanup is requested, remove only obsolete files for the
specified version/folder, after the replacement build has passed its checks.

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
build as a failure. Current development is published from main; never push fork commits to the mirror.
