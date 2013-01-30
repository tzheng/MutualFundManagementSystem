Web Application Development
Mutual Fund Management System for Carnegie Financial Service
============================================================
Carnegie Mellon University
Team Snipers
============================================================

Before deploying the system, please create a database "cfsdb"

Default employee account:
username: admin
password: 111

============================================================

Use cases of the system:


Here are descriptions of the use cases for customers: 
•  Login -- Customers can login to the system. Their accounts are set up by employees, so there is no registration use case for customers.

•	Change Password -- Logged in customers can change their own passwords.

•	View Account -- After successfully logging in, a customer sees his account, including his name, address, date of the last trading day, cash balance, number of shares of each fund owned and the value of that position (shares times price of fund as of the last trading day). From this view there will be links to most other operations.

•	Buy Fund – Logged-in customers can buy shares a mutual fund. These funds are created by employees. Customers can buy shares in funds they do not already own as well as additional shares in a fund they already own. The customer specifies the fund he wishes to purchase and the dollar amount. The number of shares purchased depends on the closing price of the fund that night. The money used to buy the shares comes out of the buying customer's cash balance when the transaction is processed. (Cash is created when an employee deposits into a customer's account.)  You must ensure that this transaction (plus other pending buy orders and check requests) will not cause the cash balance to go negative.  If there are no errors, the buy order is queued as a pending transaction.  (See the “Transition Day” use case for details on the processing of pending transactions.)

•	Sell Fund – Logged-in customers can sell shares of a mutual fund they own. The customer specifies the fund and the number of shares to sell.  You must check to ensure that this transaction (plus other pending sell orders) will not cause the share balance to go negative for the customer’s position in this fund.  If there are no errors, the sell order is queued as a pending transaction.  Proceeds from the sale go into the selling customer's cash balance when the transaction is processed.

•	Request Check – Logged-in customers can withdraw money from their cash balances by requesting a check. You must check to ensure that this transaction (plus other pending check requests and buy orders) will not cause the cash balance to go negative.  If there are no errors, the check request is queued as a pending transaction.  

•	Transaction History – Logged-in customers can see a history of their transactions. The history should show the transaction date, the operation (buy, sell, request check, deposit), the fund name, the number of shares, the share price, and the dollar amount. For cash operations (deposit and check requests) the number of shares and share price columns are left blank or not provided. All pending transactions (to buy, sell, check request, deposit) must also appear in the transaction history as pending with the appropriate columns left blank or marked pending.
•	Research Fund -- Customers can research funds. This will show some statistics about the performance of each fund.
•	Logout -- Logged in customers can log out.
 
Employee use cases are: 
•  Login -- Employees can login to the system. Their accounts are set up by other employees, so there is no registration use case for employees. The initial employee account is set up when the system is initialized.

•	Change Password – Logged-in employees can change their own passwords.

•	Create Employee Account – Logged-in employees can create accounts for new employees.

•	Create Customer Account – Logged-in employees can create accounts for new customers.

•	Reset Customer Password – Logged-in employees can reset the password for a customer's account.

•	View Customer Account – Logged-in employees can view a customer's account information, including a customer's name, address, date of the last trading day, cash balance, number of shares of each fund owned and the value of that position.

•	View Customer Transaction History – Logged-in employees can view a customer's transaction history, as described above.

•	Deposit Check -- When the company receives a check from a customer, a logged-in employee deposits the cash into the customer's account. This operation is queued as a pending transaction which will be processed during Transition Day.

•	Create Fund – Logged-in employees can create new mutual funds.  The employee is prompted for the fund name and its ticker (a short one to five character identifier which may be used when entering orders or displaying positions in a concise format).  The fund is created immediately, although the first closing day price for the fund is not provided until the next Transition Day.  So immediately after creating a fund, customers can queue up buy transactions.

•	Transition Day -- To simulate trading days, a logged-in employee “transitions the system” to end each trading day. The employee enters the date of the trading day that has just ended (greater than the date of previously ended trading day).  The employee also provides the closing prices for each fund for this trading day.  Pending transactions are then executed in the order in which they were queued using the new closing fund prices and this trading date.  The database is updated accordingly.  (The database should store a null execution date to indicate that a transaction is pending.  When a pending transaction is processed, the transaction date is changed to this trading day’s date and the customer’s cash balance and share balance in the effected fund are updated accordingly.)

•	Logout -- Logged in employees can log out.

Fund shares are tracked to three decimal places and stored as (long) integers in the database. Cash and fund share prices are tracked to two decimal places and also stored as (long) integers in the database.

