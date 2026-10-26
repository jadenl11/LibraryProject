# Rubric (100 points)

Some categories are graded for your **whole group** and some for **you individually**.

| Category | Points | Graded for | What earns full credit |
|---|---|---|---|
| **Behavior** | 30 | Group | Visible tests: `01_basic`, `02_late_fees`, `03_errors`, `04_report`, `05_people_and_clock` (4 points each). Additional tests you have not seen (10) check the rules at their boundaries. |
| **Your half: design** | 25 | You | **Partner A:** `LibraryItem` holds all shared state and behavior. Subclasses contain only what differs. No duplicated code across subclasses. All instance fields are private. The rule methods are `protected`, not `public`. **Partner B:** `Library` never branches on an item's type to apply a rule. `Patron` protects its own state. Each public method is something a caller really needs. |
| **Exceptions** | 15 | You | Specific exception types, thrown by the class that detects the problem, with correct `throws` declarations. Partner A's `Main` catches in one place, continues after every error, prints the summary line from a `finally`, and handles the missing script file. No `catch (Exception e)` and no empty `catch` blocks. |
| **Contract and collaboration** | 15 | Group and you | The contract is specific (exact signatures, units, and messages) and was signed on time. You kept your promises. Changes and bug reports are logged. You edited only your own files. |
| **Design memo and reflections** | 10 | Group and you | Parts 1 to 3 are specific and use the vocabulary of promise versus detail. Parts 4 to 8 are written in your own words about your own half. |
| **Quality and process** | 5 | You | All four Canvas checkpoints handed in on time, each showing real progress. Clear names. Consistent formatting. No leftover debugging output. |

## Levels for the design categories

| | Needs work | Meets | Exceeds |
|---|---|---|---|
| **Hierarchy** | Subclasses copy shared code, or `LibraryItem` is nearly empty | Shared code lives in the parent; subclasses hold only differences | Parent and subclasses divide the work so cleanly that a new item type needs only one new class |
| **Access** | Public fields, or public rule methods | Private fields, protected rule methods | Every member's level is justified in the memo, including the ones you chose *not* to expose |
| **Exceptions** | One exception type used for everything, or catches that hide errors | Specific exception types, one place to catch | Messages and types make the program's failure modes easy to read; no information is lost between throw and catch |
| **Contract** | Vague ("a method that checks out"), or missing units and messages | Exact signatures, units, and messages for every method the other half calls | Also covers wrong-state cases and was updated whenever the code changed |
