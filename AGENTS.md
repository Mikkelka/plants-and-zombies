# Working on this fork

- Read `docs/MAINTENANCE.md` before updating a Minecraft port. Inspect the current branch,
  working tree, remotes and upstream baseline before editing.
- `main` is the fork's active development branch, currently Minecraft 26.3.
  The user selected this branch/tag workflow on 2026-11-07, superseding the old
  rule that main was an upstream mirror. Fork code and documentation belong here.
- `codex/upstream-mirror` mirrors `joshxviii/plants-and-zombies:26.3` exactly,
  currently `fbc1a3b6`. Fast-forward it only from that selected upstream source;
  never merge fork commits/docs into it or automatically change its source.
- Keep annotated tags immutable: `26.2` preserves `c083f699` (1.5.2+mc26.2),
  and `26.3` preserves the migrated compat1 development baseline. New updates
  receive new descriptive tags; never move an existing tag to mean "latest".
- The old `codex/fabric-26.2-v1.5` and `codex/fabric-26.3-upstream` branches are
  retained as legacy references. Do not develop there or delete them without a
  request; the latter remains the head of historical draft PR #74.
- This chat maintains current development on main. Keep 26.2 available through
  its tag; use an isolated worktree/branch for any explicitly requested 26.2 fix.
  Import upstream changes when requested; do not automatically change targets.
- The current working 26.3 baseline is `1.6-dev.fbc1a3b6.compat1+mc26.3`, runtime
  source `63415ad4`, upstream `26.3` at `fbc1a3b6`. The user confirmed it works
  on 2026-11-07 and selected it for continued maintenance on main.
  Preserve the TerraBlender callback initialization fix during future imports.
- Chats sharing a checkout also share its active branch. Check it before every
  update and do not switch a checkout another chat is using. Use separate Git
  worktrees when the two chats work concurrently.
- Preserve target-version configuration and API/resource adaptations during
  upstream merges. Do not copy the other port's code or data changes blindly.
- Build with Java 25 and `./gradlew.bat build --console=plain` (Windows) or
  `./gradlew build --console=plain` (Linux). Run `git diff --check`, inspect the
  versioned JAR metadata and bundled TerraBlender, and keep generated files out
  of commits. Preserve user files, worlds and untracked data.
- Routine verification is build/data validation. Do not launch Minecraft or run
  `runClient` unless the user explicitly asks. Attribute reported gameplay
  results to the user and the version actually tested.
- Update the fork section of `CHANGELOG.md`, README and `docs/MAINTENANCE.md` when the
  baseline, dependencies, installation requirements or validation status change.
- Keep `README.md`, `AGENTS.md` and `CHANGELOG.md` in the root. Put detailed
  guidance in `docs/`, with version-specific technical notes in `docs/ports/`.
  Keep GitHub templates in `.github/` and update relative links when moving docs.
- Keep the current 26.2 and 26.3 runtime/source JARs together in the designated
  `build/libs` output folder. Do not erase the other port with `clean`; if a clean
  build is needed, isolate it or preserve and restore the other port's artifacts.
  Remove obsolete builds only from the folder/version the user requests.
- Push only when requested. When publishing, verify the destination branch and
  exact commit, then inspect CI for that commit. Keep the historical archive.
