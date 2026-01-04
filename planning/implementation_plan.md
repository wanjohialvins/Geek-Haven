# Geek Haven - Implementation Plan

## Goal Description
Build "Geek Haven" (formerly BookTrail), a native Android application using Kotlin and Jetpack Compose. The app serves as a smart book tracking and reading hub that unifies physical, digital (PDF/EPUB), and audio formats. It leans heavily on "Calm Intelligence" — using AI (Gemini) strictly for interpretation/matching and Google Books API/Web Search for facts, packaged in a nature-inspired aesthetic.

## User Review Required
> [!IMPORTANT]
> **GitHub Integration**: I will initialize a local Git repository. To push to your GitHub account, I will need you to provide the remote repository URL after I set up the local repo, or I can try using `gh` CLI if you are authenticated.
> **AI Keys**: Usage of Gemini API and Google Books API will require API keys. Placeholders will be used in the code (e.g., `local.properties`).
> **Scaffolding**: Since this is a brand new Android project, I will generate the standard directory structure and build files (`build.gradle.kts`, `settings.gradle.kts`, `AndroidManifest.xml`) manually. This guarantees control but might require a "Sync Project with Gradle Files" in Android Studio when you open it.

## Proposed Changes

### Project Structure (Root)
#### [NEW] `build.gradle.kts` (Project level)
#### [NEW] `settings.gradle.kts`
#### [NEW] `app/build.gradle.kts` (Module level)
#### [NEW] `app/src/main/AndroidManifest.xml`

### Source Code (`app/src/main/java/com/geekhaven/app/`)
#### [NEW] `GeekHavenApp.kt`
- Application class, Hilt setup.

#### [NEW] `ui/theme/`
- `Theme.kt`, `Color.kt`, `Type.kt`, `Shape.kt`: Implementing the "Nature x Technology" design system.

#### [NEW] `data/`
- `local/`: Room Database setup (`AppDatabase`, `BookDao`, `BookEntity`).
- `remote/`: Retrofit services for Google Books and Web Search.
- `repository/`: `BookRepository` managing data from local DB and remote sources.

#### [NEW] `domain/`
- Use cases (e.g., `ScanLocalBooksUseCase`, `MergeFormatsUseCase`).

#### [NEW] `ui/`
- `home/`, `library/`, `reader/`: Compose screens and ViewModels.
- `navigation/`: NavHost implementation.

### Resources (`app/src/main/res/`)
#### [NEW] `values/strings.xml`, `values/colors.xml`
#### [NEW] `drawable/`: Icons (Vector assets).

## Verification Plan

### Automated Tests
- I will create unit tests for the complex grouping logic (e.g., matching a PDF to an Audiobook entity).
- Example: `GroupingTest.kt` to verify that "Harry Potter 1" PDF and "Sorcerer's Stone" MP3 merge.

### Manual Verification
- **Build verification**: Ensure `./gradlew assembleDebug` runs without errors (I will simulate this by checking file validity).
- **UI Preview**: Since I cannot run an emulator, I will rely on rigorous code correctness for Compose. You will need to open the project in Android Studio to see the UI.
- **Workflow**:
    1.  Check if git init works.
    2.  Check if file structure matches Android standards.
    3.  Verify `task.md` updates.
