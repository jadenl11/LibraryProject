# Interface Contract

**Partner A (Items and Main):**

**Partner B (Library and Patron):**

*Groups of three: write the third person's name and role here too (see README, Section 10).*

A **contract** is a promise between two programmers about how their code fits together. Write it **together, before either of you writes code**. Once you both sign it, you may only change it by writing the change in Part 6 and getting your partner to agree.

## How to start

Partner B's code will ask Partner A's items to do things. Go through every command in README Section 3 (`CHECKOUT`, `RETURN`, `STATUS`, `REPORT`). For each one, ask: **what does the Library need to ask an item, or find out from an item?** Each answer becomes a row in Part 2.

## 1. What each of us promises

| | Writes | Depends on |
|---|---|---|
| **Partner A** | `LibraryItem`, `Book`, `Dvd`, `Magazine`, A's exceptions, `Main` | `Library.execute(String)` (provided) |
| **Partner B** | `Library` commands, `Patron`, B's exceptions | Partner A's `LibraryItem` and its subclasses |

## 2. The `LibraryItem` contract (Partner A promises, Partner B depends on it)

One row for every method Partner B will call. Say **what it promises**, not how it works.

| Method (the exact signature) | What it promises | What it throws, and the exact message |
|---|---|---|
| *(example)* `public boolean isAvailable()` | `true` if the item is on the shelf, `false` if it is checked out | nothing |
| | | |
| | | |
| | | |
| | | |
| | | |
| | | |
| | | |

## 3. Creating items

How does Partner B create each kind of item? Write the exact constructor.

| Item | Constructor |
|---|---|
| Book | |
| DVD | |
| Magazine | |

## 4. Errors that cross the line

When an item breaks a rule, Partner A's code throws an exception, and the text of its message is exactly what gets printed after `Error: `. List each one.

| Situation | Exception class name | Exact message |
|---|---|---|
| | | |
| | | |

## 5. Questions to settle **before** you sign

Write your answer next to each.

1. **Units and numbers.** Is a fee in cents or dollars? Is "today" a day number like `25`? What does `daysLate` return for an item that is not late?
2. **Wrong state.** What happens if Partner B calls `returnItem` on an item that is not checked out? Which side checks, and what is thrown?
3. **Who builds the message?** For `"Dune" (B001) is already checked out.`, does A's code build the full message, or does B?
4. **Who may call what?** Which methods of `LibraryItem` must **not** be callable from `Library`? (Think about the rules each kind of item decides for itself.)
5. **Testing before the other half exists.** How will Partner A test without Partner B's `Library`? How will Partner B test without Partner A's finished items?

## 6. Change log (after you sign)

| Day | Who asked | What changed | Did both agree? |
|---|---|---|---|
| | | | |

## 7. Bug reports (when the other half does not keep its promise)

Write one entry each time. Be specific: your partner should be able to reproduce the problem.

| What I called, with what | What the contract says should happen | What actually happened | Who fixes it |
|---|---|---|---|
| | | | |

## Signed

Partner A: ______________________      Partner B: ______________________
