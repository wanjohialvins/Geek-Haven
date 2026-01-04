🧠📚 MASTER BUILDER PROMPT — Geek Have Android App

You are building an Android application (Kotlin, Jetpack Compose) called Geekhaven .
This is a multi-format book tracking, reading, and listening app with local intelligence, web-backed facts, and AI-assisted interpretation.

You MUST implement all features and constraints below.
Do not omit, simplify, or substitute features unless explicitly stated.

1. CORE PURPOSE (NON-NEGOTIABLE)

BookTrail must:

Track all books a user has read, is reading, or plans to read

Support physical books, PDFs, EPUBs, and audiobooks

Scan local device storage for books

Infer reading/listening progress

Group the same book across multiple formats

Track series and upcoming sequels

Use web search for factual data

Use Gemini AI ONLY for interpretation, matching, and summarization

Respect Android sandboxing and permissions

2. PLATFORM & ARCHITECTURE REQUIREMENTS

Platform: Android

Language: Kotlin

UI: Jetpack Compose

Architecture: MVVM

Local DB: Room

File access: Storage Access Framework (SAF) + MediaStore

APIs:

Google Books API (primary book metadata)

Web search (for sequels & releases)

Gemini API (interpretation only)

3. STRICT AI RULES (CRITICAL)

Gemini AI:

❌ MUST NOT invent book titles, sequels, release dates, or facts

❌ MUST NOT act as a source of truth

✅ MAY:

Clean file names

Match local files to books

Group formats

Estimate equivalent progress

Summarize web search results

Generate spoiler-safe reminders

Recommend books with explanations

Every AI decision must:

Include a confidence score

Be reversible by the user

Be based on provided data only

4. DATA SOURCES & RESPONSIBILITIES
Google Books API (SOURCE OF TRUTH)

Used for:

Title, author, ISBN

Covers

Publication dates

Series metadata (when available)

Web Search (REQUIRED)

Used for:

Upcoming sequels

New releases

Series order confirmation

Author bibliographies

Flow:

Query Google Books API

If incomplete → perform web search

Pass results to Gemini for summarization

Cache results locally

Never display unverified AI guesses

5. SMART AUDIOBOOK PLAYER INTEGRATION (INDIRECT ONLY)

Constraints:

❌ No access to other apps’ internal databases

❌ No direct playback position syncing

Allowed:

Scan audiobook files (MP3, M4B)

Read duration & metadata

Infer progress

Ask user to confirm

6. IN-APP CONSUMPTION (REQUIRED)
A. PDF / EPUB Reader

Must support:

PDF and EPUB

Page tracking

Progress %

Reading time

Bookmarks

Manual separators (section markers)

Resume from last position

“Open in external app” fallback

No DRM support required.

B. Audiobook Player (Basic)

Must support:

MP3 / M4B playback

Play / pause / seek

Resume from last position

Progress tracking

Sync with unified book progress

Not intended to replace full audiobook apps.

7. ORGANIZATION & GROUPING (VERY IMPORTANT)
Book Grouping (“Book Hub”)

The app must group:

Same book across formats (PDF + EPUB + Audiobook + Physical)

Different editions

Box sets / translations

Grouping logic:

Title

Author

ISBN

AI-assisted matching

User must be able to:

Merge books

Split books

Override AI decisions

Each Book Hub contains:

All formats

Unified progress

Notes

Bookmarks

Separators

8. READING INTELLIGENCE FEATURES (ALL REQUIRED)
1. Reading Mode Awareness

The app learns locally how the user reads/listens.

Detect patterns such as:

Audiobooks at night

Short PDF sessions

Long weekend sessions

Adjust:

Recommendation length

Recommendation format (audio vs text)

Notification timing

All analysis must be on-device.

2. Format-Aware Progress Fusion

Progress must merge across formats.

Example:

30% ebook + audiobook listening → unified progress

Rules:

Gemini estimates equivalent progress

User confirms once

Manual override always allowed

3. “If You Stop Now…” Memory Feature

When resuming after a break:

Show last chapter/section

Show a short, spoiler-safe reminder:
“You left off right after X happened”

Optional and disable-able.

4. Reading DNA (Private Profile)

Generate a private, explainable profile showing:

Preferred genres

Reading pace

Complexity tolerance

Fiction vs nonfiction balance

Format preferences

Updated over time.
Not social. Not competitive.

9. BOOKMARKS, SEPARATORS, NAVIGATION
Bookmarks

Supported in PDFs, EPUBs, and audiobooks

Named bookmarks

Optional notes

Quick jump

Separators / Section Markers

User-defined markers (e.g., “Paused Here”, “Part 1”)

Visual dividers in timelines

Aid memory & navigation

10. SERIES & SEQUEL TRACKING

For each book:

Detect if part of a series

Identify next book

Track release dates

Re-check periodically via web search

Notify user of updates

Never guess sequels.

11. RECOMMENDATIONS

Recommendations must:

Be based on reading history + Reading DNA

Respect reading mode (audio vs text)

Explain why the book was suggested

Avoid recommending already-read books

12. USER FLOW (EXPECTED)

User launches app

Grants permissions

Selects folders

App scans files

Matches books

Groups formats

User confirms matches

Reads/listens in-app or externally

Progress updates automatically

App tracks series & suggests next reads

13. UI REQUIREMENTS (HIGH LEVEL)

Screens:

Home (currently reading + recommendations)

Library (all books)

Book Hub (grouped formats)

Reader / Player

Series view

Insights (Reading DNA)

Settings (folders, permissions, AI toggles)

14. LIMITATIONS TO RESPECT

Android sandboxing

Scoped storage

No DRM breaking

No other app data access

Offline-first where possible

15. FINAL INSTRUCTION

Do NOT simplify this app.
Do NOT remove features.
If a feature is complex:

Implement a minimal but functional version

Leave extension points documented

Build with clarity, explainability, and user control as top priorities.

BookTrail Design & Theme Brief

Theme: Nature × Technology (“Calm Intelligence”)

1. DESIGN PHILOSOPHY

BookTrail’s design should feel like:

A quiet digital library embedded in nature, powered by invisible intelligence.

Core Principles

Calm, not noisy

Organic shapes + precise motion

Readable first, decorative second

Technology should feel supportive, not loud

This is not:

Neon cyberpunk

Flat corporate UI

Social-media styled

2. COLOR PALETTE (NATURE-TECH HYBRID)
Primary Colors

Forest Green – grounding, focus

Soft Sage – backgrounds, cards

Off-White / Paper Cream – reading comfort

Accent Colors (Tech Layer)

Muted Teal or Cyan – progress, highlights

Warm Amber – bookmarks, memory markers

Soft Slate Gray – secondary text

⚠️ Avoid:

Pure black/white contrast

Neon colors

Over-saturated gradients

3. TYPOGRAPHY
Text Font (Reading-Friendly)

Humanist or serif-adjacent

Examples:

Inter

Source Serif (for book content)

Noto Serif

UI Font (Tech Layer)

Clean sans-serif

Used sparingly for:

Stats

Progress

Navigation

Rule:
Reading feels natural. Controls feel precise.

4. SHAPE LANGUAGE
Nature Influence

Rounded corners

Soft containers

Card layouts that feel like “leaves” or “pages”

Tech Influence

Clean grids

Subtle dividers

Precise spacing

Avoid

Sharp boxes everywhere

Overly playful blobs

5. ICONOGRAPHY
Style

Thin-line icons

Minimal fills

Organic curves

Visual Metaphors
Feature	Metaphor
Progress	Growing ring / vine
Book Hub	Tree with branches
Series	Path / trail
Bookmark	Leaf marker
Separator	Stone marker
6. MOTION & ANIMATIONS (VERY IMPORTANT)

Animations should feel like natural movement, not UI tricks.

Examples

Progress bar fills like growth

Page turns fade slightly

Book grouping gently “pulls together”

Transitions are slow-in, slow-out

No bounces. No snaps.

7. FEATURE-SPECIFIC DESIGN IDEAS
🌿 Book Hub (Grouped Formats)

Central “book card”

Formats shown as subtle badges or branches

Unified progress ring around the book

🌱 Reading Mode Awareness

Small icon indicating mode:

Moon → audio at night

Leaf → calm reading

Never intrusive

No popups unless user asks

🍃 “If You Stop Now…” Memory

Appears like a paper note

Soft background

Optional dismiss

🌳 Reading DNA Screen

Visualized like a tree

Branches = genres

Growth = reading time

Calm data, no numbers overload

📖 Reader / Player UI

Minimal controls

Focus on content

Background slightly textured (paper feel)

Night mode = deep green, not black

8. DARK MODE (NATURE-BASED)

Dark mode should feel like:

Reading outdoors at night, not staring into space.

Colors

Deep forest green instead of black

Warm gray text

Amber highlights

9. ACCESSIBILITY (REQUIRED)

Adjustable font sizes

High-contrast option

Reduced motion toggle

Clear focus states

Nature calm ≠ low contrast.

10. UI STRUCTURE (HIGH LEVEL)
Home
 ├── Currently Reading
 ├── Recommendations
 └── Reading Mode Indicator

Library
 ├── Book Hubs (grouped)
 └── Filters

Book Hub
 ├── Formats
 ├── Progress
 ├── Bookmarks
 └── Series Path

Insights
 └── Reading DNA

Settings
 ├── Appearance
 ├── Permissions
 └── AI Controls

11. DESIGN DO / DON’T SUMMARY
DO

✔ Calm colors
✔ Organic motion
✔ Clear hierarchy
✔ Explainable AI visuals

DON’T

✖ Gamification
✖ Loud badges
✖ Social feeds
✖ Neon accents