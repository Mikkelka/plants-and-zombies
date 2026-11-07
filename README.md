# Plants & Zombies - Minecraft 26.3 port

This branch maintains an unofficial Fabric port of **Plants & Zombies** to
**Minecraft 26.3**. Its upstream baseline is the original project's `26.3`
branch at `fbc1a3b6` (2026-11-07). This includes 1.5.1/1.5.2 and ongoing 1.6
development, so this snapshot is versioned `1.6-dev.fbc1a3b6.compat1+mc26.3`.

**Current 26.3 baseline:** use the `compat1` build for normal use and future
maintenance. The user confirmed that it works after the TerraBlender startup
fix on 2026-11-07. Continue development on `main` and preserve that fix.

- **Current development:** [`main`](https://github.com/Mikkelka/plants-and-zombies/tree/main), currently our corrected Minecraft 26.3 version.
- **Saved Minecraft 26.2:** tag [`26.2`](https://github.com/Mikkelka/plants-and-zombies/tree/26.2), mod `1.5.2+mc26.2`.
- **Saved Minecraft 26.3:** tag [`26.3`](https://github.com/Mikkelka/plants-and-zombies/tree/26.3), mod `1.6-dev.fbc1a3b6.compat1+mc26.3`.
- **Exact upstream 26.3 mirror:** [`codex/upstream-mirror`](https://github.com/Mikkelka/plants-and-zombies/tree/codex/upstream-mirror), synchronized to `fbc1a3b6` on 2026-11-07.

Tags are fixed snapshots; future builds receive new tags. Use the mirror to
fetch original updates, then merge requested changes into main while retaining
our compatibility fixes. The older version branches remain as legacy references.

Install Java 25, Fabric Loader, Fabric API for Minecraft 26.3 and Fabric
Language Kotlin. TerraBlender is bundled. Build with
`./gradlew.bat build --console=plain`; the mod JAR is
`build/libs/plants-and-zombies-1.6-dev.fbc1a3b6.compat1+mc26.3.jar`.

This fork retains Loader 0.19.5, Fabric API 0.162.0+26.3, Kotlin runtime
1.13.8+kotlin.2.3.0 and bundled TerraBlender. The imported build has passed
compilation, 464 data checks and packaged-JAR inspection. The user confirmed
successful startup/use of `compat1`; broader gameplay scenarios remain unverified.
The `compat1` build fixes the reported startup crash by registering the
graveyard through TerraBlender's initialization entrypoint after its config is ready.

See [PORTING.md](PORTING.md) for version requirements, validation status,
CI downloads and the workflow for importing future upstream updates.
See [CHANGELOG.md](CHANGELOG.md) for port changes and the original changelog.

This chat now maintains development on main. Minecraft 26.2 is preserved by
its tag; any later fix for that target starts from the tag in a separate
worktree/branch. See [maintenance responsibilities](PORTING.md#separate-maintenance-chats).

---

<p align="center">
  <img width="672" height="447" alt="PandZLOGO" src="https://github.com/user-attachments/assets/7d4aa7bc-a0fc-401e-9c11-dc74f4e627a5" />
</p>

<p align="center"><font size=6>A Plants vs. Zombies mod for Minecraft.</font></p>
<p align="center">
  <a href="https://modrinth.com/mod/plants-and-zombies" target="_blank">
    <img alt="modrinth" height="56" src="https://cdn.jsdelivr.net/npm/@intergrav/devins-badges@3/assets/cozy/available/modrinth_vector.svg">
  </a>
  <a href="https://www.curseforge.com/minecraft/mc-mods/plants-and-zombies" target="_blank">
    <img alt="curseforge" height="56" src="https://cdn.jsdelivr.net/npm/@intergrav/devins-badges@3/assets/cozy/available/curseforge_vector.svg">
  </a>
</p>

Plants & Zombies is a Plants vs. Zombies themed mod that aims to add mechanics and characters from the game into Minecraft in a totally integrated, survival friendly way. 
</p>

<p align="center">
  <img alt="Page Break" src="https://cdn.modrinth.com/data/cached_images/1b02c40d42345d59d1bbd86422d32ad842158f5e.png" />
