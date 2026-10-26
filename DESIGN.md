# Design Memo

**Names (everyone in your group):**

Parts 1 to 3 are written **together**. Parts 4 to 8 are written **by each person**, about your own half, with your name before your answer.

Write parts 1 to 3 **before** you code, and parts 4 to 8 **after** you finish. Short, clear answers are better than long ones.

## 1. Promise or detail?

For each item, mark **P** if it is something the class **promises** to the rest of the program (the behavior), or **D** if it is a **detail** that could change without anyone outside noticing.

| | P or D? |
|---|---|
| A `LibraryItem` can be checked out and returned |P |
| A checked-out `Book` is due 21 days later |P |
| How you store the borrower's id inside an item |D |
| The text `Error: no such item X9.` |P |
| Whether `Library` keeps items in an `ArrayList` or a `HashMap` |D |
| The names of your own exception subclasses |D |

## 2. Class sketch

List every class you plan to write. For each, write one sentence: **what does this class promise?**

| Class | Package | Promises |
|---|---|---|
| `LibraryItem` | `library.model` |`Loan length + late fee` |
| `Book` | `library.items` |`Loan length + late fee` |
| `DVD` | `library.items` |`Loan length + late fee` |
| `Magazine` | `library.items` |`Loan length` |
| | | |

## 3. Access decisions (at least 8 rows)

| Member | Access (`private` / `protected` / `public`) | Why that level and no wider? |
|---|---|---|
| (example) `LibraryItem.dueDay` | `private` | Only the item should change it. `Library` asks with `getDueDay()`. |
| `LibraryItem.lateFee` | `private` | Only the item should change it. `Library` asks with `getLateFee()`. |
| `LibraryItem.feeRate` | `protected` | `It is a final variable`. |
| `Patron.itemCount` | `private` | `Only the patron should change it. Library needs to ask for it with getItemCount() `. |
| `Patron.balance` | `private` | `Only the patrons themselves should be able to modify their balance. Library needs to ask for it using getBalance()`. |
| `LibraryItem.dayCount` | `private` | `Only the library item should be able to change the day count specific to its ID`. |
| `Patron.name` | `private` | `Only the patron item should be able to access the name directly`. |
| | | |

## 4. After you finish: what did the rule methods teach you?

**Partner A:** Which of your methods are `protected`? What would go wrong if they were `public`? Give one concrete example.

**Partner B:** What would go wrong if `Library` could change a patron's fees directly, instead of only through `Patron`'s methods? Give one concrete example.

## 5. Exceptions

- Which exceptions did you create in your half? Why those names?
- Where do you **throw** them? Where do you **catch** them? Why there and not somewhere else?
- Which exception did Java throw for you (not one you created)? How did you handle it?

## 6. Change test

Suppose the library adds `AUDIOBOOK` (14-day loan, 50 cents per day, never more than 500 cents).

- Which files would have to change? List them. Which belong to your half and which to your partner's?
- If more than the new item class and the code that creates it would have to change, what could you change in your design so next time you would not need to?

## 7. One design decision you changed (each person writes their own)

Put your name before your answer.

What did you first plan? What did you do instead? What made you change your mind?

## 8. Working against a contract (each person writes their own)

Put your name before your answer.

- Name one promise **you** made in the contract. Did you keep it exactly? If not, what happened?
- Name one time your partner's half did something you did not expect. Was the contract unclear, or did the code not follow it? How did you find out?
- Did you change the contract after you signed it? What was the change, and why?
- What is one thing you would write differently in the contract if you started over?
