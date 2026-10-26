# Library Checkout System

## The big picture

In this project you build the "brain" of a small library. Your program keeps track of three things: the **books, DVDs, and magazines** the library owns, the **patrons** (people) who borrow them, and the **late fees** that pile up when something comes back late.

There is no screen and there are no buttons. Your program is driven by a **script file**: a plain text file where each line is one command, such as "this patron checks out this book" or "25 days go by". For each command, your program prints one line saying what happened. Here is a tiny script and what your program must print for it.

**The script**

```
PATRON P1 Ana
BOOK B001 Dune
CHECKOUT P1 B001
ADVANCE 25
RETURN B001
```

**What your program prints**

```
Added patron P1 (Ana).
Added BOOK B001 "Dune".
Ana checked out "Dune" (B001), due day 22.
Advanced 25 day(s). Today is day 26.
Ana returned "Dune" (B001), 4 day(s) late. Fee: $1.00.
Processed 5 commands (0 errors).
```

Look at the last two commands. The clock moved forward 25 days. The book was due on day 22, so it came back 4 days late, and a book costs 25 cents for each late day. Your program has to work all of that out.

### Why this is a design project

Any single command is easy. The challenge is building the whole program well, and that comes down to three questions:

1. **How will you avoid writing the same code three times?** Books, DVDs, and magazines are alike in most ways. They differ only in how long you can keep them and how much a late return costs.
2. **What should happen when something goes wrong?** A script might ask for an item that does not exist, or one that is already checked out. Your program has to report each problem and **keep going**.
3. **Which parts of your code should be allowed to use which other parts?** Each kind of item decides its own loan length and late fee. Think about which other parts of the program should be able to use those decisions, and which should not.

How you answer each question is up to you. Your design memo (`DESIGN.md`) is where you explain your choices. Section 6 lists the few design rules that every solution must follow.

### What you will do

| Step | What you do |
|---|---|
| 1. Understand | Work out the output for a sample script by hand, using only the rules in this document. |
| 2. Design together | With your partner, sketch your classes. Decide what each one **promises** and what it **hides**. Then write a **contract**: the promises each of your halves makes to the other. |
| 3. Build your half | You and your partner each write your own half of the project (see Section 10). |
| 4. Put the halves together | Test them together, find where one half does not keep its promise, and fix it with your partner. |
| 5. Test and finish | Run the grader, fix what it finds, and finish your design memo and reflections. |

This project is built the way many real programs are. **You and your partner each write half, and the two halves have to fit together.** Your code has to do what your partner's code expects, and your partner's code has to do what yours expects. The agreement between you is the **contract**.

You will hand in your work in **four checkpoints** in Canvas (see Section 8).

### How to read the rest of this document

Everything after this point is the **specification**: the exact commands, the exact output, and the exact error messages. Your program must produce **exactly** that output.

How you design the classes to get there is up to you. Two students can design completely different classes and both be right, as long as the output is identical. That is the point of this project:

> **The behavior is specified. The design is yours.**

---

## 1. How the program runs

Your `Main` reads a script file named on the command line, runs each command in order, and prints one result per command. Each line of the script is one command.

- Blank lines are ignored.
- Lines that start with `#` are comments and are ignored.
- Words in a command are separated by spaces. Commands and IDs are **case-sensitive**.

The library has a clock that counts in whole **days**. It starts on **day 1**.

### Required classes

The grader looks for these exact names. You may add as many other classes as you like.

| Class | Package |
|---|---|
| `LibraryItem` | `library.model` |
| `LibraryException` | `library.model` |
| `Book`, `Dvd`, `Magazine` | `library.items` |

### Already written for you

These files are in your starter folder. You do not have to write them.

| File | What it does |
|---|---|
| `Money` | Turns cents into text: `Money.format(1075)` gives `$10.75`. |
| `LibraryException` | The base type for every problem your program reports. You write the more specific subclasses. |
| `Library.execute` and its two helpers | Read one command line, check how many words it has, and call the right method. You write the methods it calls. |
| `Grader` and the `tests` folder | Check your work. |
| `Library` | `library.app` |
| `Main` (has `public static void main`) | `library.app` |

---

## 2. The rules

### Items

| Type | Loan length | Late fee |
|---|---|---|
| `BOOK` | 21 days | 25 cents for each day late |
| `DVD` | 7 days | 100 cents for each day late, but never more than 1000 cents |
| `MAGAZINE` | 14 days | no fee, ever |

- A checked-out item is **due** on `(day it was checked out) + (loan length)`.
- **Days late** = `(day it is returned) - (due day)`, or 0 if that is negative. An item returned **on** its due day is on time.
- Money is always **whole cents** inside your program. Use `Money.format(cents)` (provided) to print it: `1075` becomes `$10.75`.

### Patrons

- A patron may have at most **3** items checked out at once.
- Late fees are added to the patron's balance when the item is **returned**.
- A patron who owes **$5.00 (500 cents) or more** may not check anything out until they pay down below that.

---

## 3. The commands

Every command prints its result on one line (except `STATUS` and `REPORT`, which print several). Items are always listed in the order they were added.

| Command | What it does | Success output |
|---|---|---|
| `PATRON <id> <name>` | Adds a patron. The name is one word. | `Added patron P1 (Ana).` |
| `BOOK <id> <title>` | Adds a book. The title is **everything after the id**. | `Added BOOK B001 "Dune".` |
| `DVD <id> <title>` | Adds a DVD. | `Added DVD D001 "Alien".` |
| `MAGAZINE <id> <title>` | Adds a magazine. | `Added MAGAZINE M001 "Wired".` |
| `CHECKOUT <patronId> <itemId>` | The patron borrows the item today. | `Ana checked out "Dune" (B001), due day 22.` |
| `RETURN <itemId>` | The item comes back today. | on time: `Ana returned "Dune" (B001) on time.` <br> late: `Ana returned "Dune" (B001), 3 day(s) late. Fee: $0.75.` |
| `ADVANCE <days>` | Moves the clock forward. | `Advanced 5 day(s). Today is day 6.` |
| `PAY <patronId> <cents>` | The patron pays toward their fees. | `Ana paid $2.50. Balance: $3.00.` |
| `STATUS <patronId>` | Shows one patron. | see below |
| `REPORT` | Shows every item, then the total fees owed. | see below |

For titles, words are joined by **single spaces**: `BOOK B001 The   Hobbit` prints `"The Hobbit"`.

A late return always uses the `N day(s) late. Fee: $X.XX.` form, even when the fee is `$0.00`.

### `STATUS`

```
Ana (P1): owes $0.75, 2 item(s) checked out
  B001 "Dune" due day 22
  D001 "Alien" due day 8 (OVERDUE)
```

- The first line is always printed. Then one indented line (two spaces) for each checked-out item, in the order they were checked out.
- Add ` (OVERDUE)` if **today is after** the due day. On the due day itself, it is not overdue.

### `REPORT`

```
B001 BOOK "Dune" - available
D001 DVD "Alien" - checked out by P2, due day 8
M001 MAGAZINE "Wired" - available
Total fees owed: $0.75
```

One line per item in the order added, then the total fees owed by **all** patrons. (No period after the total.)

---

## 4. The errors

When something is wrong, the program prints `Error: ` followed by the message below, and **keeps going**. Messages are exact.

| Situation | Message |
|---|---|
| Wrong number of words in a command | `wrong number of arguments for CHECKOUT.` |
| Unknown command word | `unknown command FOO.` (shows the word as typed) |
| Patron id already used | `patron P1 already exists.` |
| Item id already used | `item B001 already exists.` |
| Patron id not found | `no such patron P9.` |
| Item id not found | `no such item X9.` |
| Patron owes $5.00 or more | `Ana owes $5.50 and cannot check out items.` |
| Patron already has 3 items | `Ana already has 3 items checked out.` |
| Item is already checked out | `"Dune" (B001) is already checked out.` |
| Item is not checked out (on `RETURN`) | `"Dune" (B001) is not checked out.` |
| `ADVANCE` with something that is not a positive whole number | `days must be a positive whole number.` |
| `PAY` with something that is not a positive whole number | `amount must be a positive whole number of cents.` |
| `PAY` more than the patron owes | `Ana only owes $3.00.` |

### Order of checks

When a command breaks more than one rule, report the **first** problem in this list.

| Command | Check in this order |
|---|---|
| any | 1. wrong number of arguments (`BOOK`, `DVD`, `MAGAZINE` need an id **and** at least one word of title) |
| `PATRON`, `BOOK`, `DVD`, `MAGAZINE` | 2. id already exists |
| `CHECKOUT` | 2. no such patron, 3. no such item, 4. patron owes $5.00 or more, 5. patron has 3 items, 6. item already checked out |
| `RETURN` | 2. no such item, 3. item is not checked out |
| `ADVANCE` | 2. days is not a positive whole number |
| `PAY` | 2. no such patron, 3. amount is not a positive whole number, 4. amount is more than owed |
| `STATUS` | 2. no such patron |

---

## 5. Main

1. If the script file cannot be opened, print `Error: could not open script file <name>.` (using the name exactly as given) and stop. Nothing else is printed.
2. Otherwise run every command. After the **last** command, print one more line:

```
Processed 13 commands (2 errors).
```

`commands` counts every command line (not blank lines or comments). `errors` counts the commands that printed an `Error:` message. This line must print **even if your program fails unexpectedly part-way through**.

> Do **not** call `System.exit` anywhere. The grader runs your `Main` directly.

---

## 6. Design rules

These are part of the assignment, the same way the output is. The grader checks some of them.

1. `LibraryItem` is **abstract**. It holds everything the item types share. The subclasses contain **only what differs**. Copying the same code into `Book`, `Dvd`, and `Magazine` is a design bug.
2. `LibraryItem` has **at least two abstract methods**: the rules each type decides for itself (loan length and late fee).
3. **Only an item may apply its own rules.** No class outside the item hierarchy (for example `Library`) should be able to call the rule methods. The rule methods are `protected`, not `public`.
4. **All instance fields are `private`.** Other classes use methods.
5. `Library` must never ask "what type of item is this?" to apply a rule. It should ask the item to do the work itself. This does not apply to adding an item to `Library`.
6. Every broken rule is reported by throwing a **checked** exception that extends `LibraryException`. Create at least **two** subclasses of your own, and choose them so the names tell the reader what went wrong. (The provided `Library.execute` throws a plain `LibraryException` for an unknown command or the wrong number of words. You may replace those with a more specific exception of your own.)
7. A rule violation is **caught in one place**, where it is turned into an `Error:` line. Never write `catch (Exception e)`. Never leave a `catch` block empty.

---

## 7. Running and testing

The `tests` folder has scripts (`.txt`) and the exact output your program must produce (`.expected`).

**To check everything:** run `library/grader/Grader.java` in VS Code (the Run button above `main`). It runs every script through your `Main`, compares the output, and checks the design rules. Each check prints `PASS` or `FAIL`, and a failing test shows the **first line that differs**.

**To run just one check:** open the Run and Debug panel in VS Code (the sidebar icon), choose **Grader: one test (edit args)**, and press the green play button. To pick a different test, open `.vscode/launch.json` and change the word in `args`. Use the start of a test's file name, like `03_errors` or just `03`. Two special words also work: `design` runs only the design checks, and `missing` runs only the missing script file test.

To see the raw output of a script without comparing it to the expected output, choose **Run one script (edit args)** instead and change the file name in `args`. (Mac/Linux terminal option: `javac -d out $(find src -name "*.java")` and then `java -cp out library.app.Main tests/01_basic.txt`.)

Some tests need both halves. Two checks work with only **one** half: the **design checks** and the **missing script file** test look at Partner A's half, and `05_people_and_clock` only needs Partner B's half. Use them to test your half before your partner is ready.

The tests you can see are only **some** of the tests your project will face. Read the rules in this document, and write your own scripts to test boundaries (for example: what happens at exactly $5.00 owed?).

---

## 8. Checkpoints (Canvas)

You hand in your work in **four Canvas assignments**, one for each checkpoint. Look for these exact names in Canvas.

| Canvas assignment | Goal | What you upload |
|---|---|---|
| **Library Project, Checkpoint 1: Design and Contract** | You can work out the output of `tests/01_basic.txt` by hand and explain every line. Your design sketch is done and your **contract is signed**. | `DESIGN.md` with parts 1 to 3 filled in, and the signed contract (`CONTRACT.md`, or a clear photo of your signed paper contract) |
| **Library Project, Checkpoint 2: My Half** | **Your half** works on its own. Partner A: the design checks pass, and so does the missing script file test. Partner B: `05_people_and_clock` passes. | **Each of you hands in your own.** A zip of your project folder, plus a screenshot of the grader showing your passing checks |
| **Library Project, Checkpoint 3: Integration** | The two halves work together. `01_basic` and `03_errors` pass. Your change log and bug reports are up to date. | A zip of your project folder, plus a screenshot of the grader's **BEHAVIOR TESTS** |
| **Library Project, Checkpoint 4: Final Submission** | All five visible tests pass. `DESIGN.md` and `CONTRACT.md` are finished, including each person's reflections. | A zip of your finished project folder, with `DESIGN.md` and `CONTRACT.md` inside it |

**Markdown files.** `DESIGN.md`, `CONTRACT.md`, and `RUBRIC.md` are Markdown files. To read one formatted, open it in VS Code and press Ctrl+Shift+V (Cmd+Shift+V on a Mac). To type in the file and watch the preview update, press Ctrl+K and then V (Cmd+K then V on a Mac) to open the preview beside it.

## 9. Submitting in Canvas

Every upload from Checkpoint 2 on is a **zip of your whole project folder**: the folder that has `src`, `tests`, and `DESIGN.md` inside it.

- **Windows:** right-click the project folder, choose **Send to**, then **Compressed (zipped) folder**.
- **Mac:** right-click the project folder, then choose **Compress**.

Name the file `LastName_Library_CP2.zip`, and use `CP3` and `CP4` for the later checkpoints. Upload it to the matching Canvas assignment. Keep working in the same folder. You will zip it again at each checkpoint.

Checkpoint 2 is handed in by **each person**. The other checkpoints are handed in **once per group**.

To take a screenshot of the grader, run `Grader` in VS Code and capture the output panel.

Section 10 explains how the work is split. Your teacher will explain how Canvas is set up for group and individual submissions.

Hand in each checkpoint on time, even if the work is not perfect yet. Each checkpoint is meant to get you ready for the next one.

## 10. Splitting the work

This project is split the way many real programs are. **You and your partner each write half, and the two halves have to fit together.** The agreement between you is the contract (`CONTRACT.md`).

**On the first day,** your teacher may have you draft the contract on paper. Once you have both signed it, type the agreed contract into `CONTRACT.md`. That file is the one you keep up to date (the change log and the bug reports) and hand in.

### Who writes what

| | Partner A: Items and Main | Partner B: Library and Patron |
|---|---|---|
| You write | `library/model/LibraryItem.java`<br>`library/items/Book.java`, `Dvd.java`, `Magazine.java`<br>`library/app/Main.java`<br>any exception your files throw | `library/app/Library.java` (the command methods)<br>`library/app/Patron.java`<br>any exception your files throw |
| You depend on | `Library.execute` (provided) | Partner A's `LibraryItem` and its subclasses |

**Groups of three:** the third person writes `Patron` and `Main`, and the exceptions they throw. Partner B then writes only `Library`. Your group has two contracts: one between the items and the library, and one between the library and `Patron`.

Exceptions you create live in your own half: Partner A's in `library/model/`, Partner B's in `library/app/`.

### The rules of working against a contract

1. **Skeleton first.** At the start of the build time, Partner A shares a version of `LibraryItem` and the three item classes that **compiles**, has every method from the contract, and uses dummy bodies. Until it exists, Partner B's code cannot even compile. Fill in the real behavior after that.
2. **Edit only your own files.** Never change your partner's file. If you need something different, write it in the change log (`CONTRACT.md`, Part 6) and ask.
3. **Change the contract only together.** Both of you agree, and the change goes in the log.
4. **Report bugs, do not fix them.** When your partner's code does not do what the contract says, write a bug report (`CONTRACT.md`, Part 7). Your partner fixes it.
5. **Test your half alone first.** See Section 7 for the checks that work with one half.
6. **Share files by copying.** Because you each edit different files, putting the halves together means copying your files into one project folder. Your teacher will tell you where to share them.

### Everyone writes their own reflection

Put every group member's name at the top of `DESIGN.md`. Parts 7 and 8 are written separately by each person, in your own words, with your name before your answer. Your teacher may ask any member of your group to explain any part of your design, including the half you did not write.

## 11. Optional extensions

Only after everything above works:

- **`RENEW <itemId>`**: pushes the due date out by the loan length once per checkout. Magazines cannot be renewed.
- **Patron types:** Students may borrow 3 items. Teachers may borrow 6. Design this as a second abstract class.
- **A new item type:** add `AUDIOBOOK` (14-day loan, 50 cents per day, never more than 500 cents). Then answer the question in `DESIGN.md` part 6.
- **`HOLD <patronId> <itemId>`**: a first-come queue for each item. (Java's built-in `Queue` is allowed.)
