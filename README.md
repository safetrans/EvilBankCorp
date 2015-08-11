# EvilBankCorp
You have secured a job as a developer for Evil Corp. 
Your assignment is to write an application that will take a list of checks, 
debit transactions and withdrawals and credit them against an account.

If the account has insufficient funds to process the transaction, 
allow them to overdraft their account and then charge the owner a $35 insufficient funds fee.

What You Should Do:
  +Normal banks process checks in the order they are dated, not the order in which they arrive. 
  +Sort the payments by date to find out the proper balance.
  +It would also be nice to show a list of all transactions as a summary.


Bonus:
  1. Evil Corp branches like to process checks in the order that generates the most fees. Can you figure out what that would be?

Take the application that you wrote for Evil Corps banking and add the following features:

Add the functionality so that the application stores its data to a file when it close and load the account data from a file when it open

List the current accounts and their balances before each major command 

(adding/removing an accout, processing a transaction)

Add the ability to make a deposit

Add Input validation

Add the ability to close an account, but they can only be closed if the value is 0.

