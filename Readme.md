# Scratch Card Game (Java CLI)

This is a console-based Scratch Card Game built in Java using pure OOP principles — no frameworks like Spring.

### Features:
- Generates a random matrix (4x4) based on symbol probabilities
- Detects winning combinations (symbol count & patterns)
- Applies bonus symbols (`10x`, `+1000`, `MISS`, etc.)
- Calculates and displays final reward
- Configurable via external JSON

### How to Run:

```bash
java -jar target/scratchgame-1.0-SNAPSHOT-jar-with-dependencies.jar --config config.json --betting-amount 100
