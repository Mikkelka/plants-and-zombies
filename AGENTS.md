# Working on this fork

- Read `PORTING.md` before updating a Minecraft port. Inspect the current branch,
  working tree, remotes and upstream baseline before editing.
- `main` mirrors `joshxviii/plants-and-zombies:main` exactly. Add fork changes to
  a Minecraft port branch; never merge port commits or fork-only docs into main.
- Maintain `codex/fabric-26.2-v1.5` for Minecraft 26.2 and
  `codex/fabric-26.3-upstream` for Minecraft 26.3. Keep the existing branches
  when importing later mod releases, and update the documented baseline/version.
- Preserve target-version configuration and API/resource adaptations during
  upstream merges. Do not copy the other port's code or data changes blindly.
- Build with Java 25 and `./gradlew.bat build --console=plain` (Windows) or
  `./gradlew build --console=plain` (Linux). Run `git diff --check`, inspect the
  versioned JAR metadata and bundled TerraBlender, and keep generated files out
  of commits. Preserve user files, worlds and untracked data.
- Routine verification is build/data validation. Do not launch Minecraft or run
  `runClient` unless the user explicitly asks. Attribute reported gameplay
  results to the user and the version actually tested.
- Update the fork section of `CHANGELOG.md`, README and `PORTING.md` when the
  baseline, dependencies, installation requirements or validation status change.
- Push only when requested. When publishing, verify the destination branch and
  exact commit, then inspect CI for that commit. Keep the historical archive.
