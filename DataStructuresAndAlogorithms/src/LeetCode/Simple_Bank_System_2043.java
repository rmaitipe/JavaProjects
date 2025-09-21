package LeetCode;

public class Simple_Bank_System_2043 {
/*
You have been tasked with writing a program for a popular bank that will automate all its incoming transactions (transfer, deposit, and withdraw).
The bank has n accounts numbered from 1 to n. The initial balance of each account is stored in a 0-indexed integer array balance,
with the (i + 1)th account having an initial balance of balance[i].

Execute all the valid transactions. A transaction is valid if:
    The given account number(s) are between 1 and n, and
    The amount of money withdrawn or transferred from is less than or equal to the balance of the account.

Implement the Bank class:
    Bank(long[] balance) Initializes the object with the 0-indexed integer array balance.
    boolean transfer(int account1, int account2, long money) Transfers money dollars from the account numbered account1
        to the account numbered account2. Return true if the transaction was successful, false otherwise.
    boolean deposit(int account, long money) Deposit money dollars into the account numbered account.
        Return true if the transaction was successful, false otherwise.
    boolean withdraw(int account, long money) Withdraw money dollars from the account numbered account.
        Return true if the transaction was successful, false otherwise.
 */

        private final long[] balance;
        private final Object[] locks;

        public Simple_Bank_System_2043(long[] balance) {
            this.balance = balance;
            this.locks = new Object[balance.length];
            for (int i = 0; i < balance.length; i++) {
                locks[i] = new Object(); // Create a unique lock for each account
            }
        }

        public boolean transfer(int account1, int account2, long money) {
            if (!isValidAccount(account1) || !isValidAccount(account2)) {
                return false;
            }
            // Always lock in increasing order to prevent deadlock
            Object lock1 = account1 < account2 ? locks[account1 - 1] : locks[account2 - 1];
            Object lock2 = account1 < account2 ? locks[account2 - 1] : locks[account1 - 1];
            synchronized (lock1) {
                synchronized (lock2) {
                    if (balance[account1 - 1] >= money) {
                        balance[account1 - 1] -= money;
                        balance[account2 - 1] += money;
                        return true;
                    }
                    return false;
                }
            }
        }

        public boolean deposit(int account, long money) {
            if (!isValidAccount(account)) {
                return false;
            }
            synchronized (locks[account - 1]) {
                balance[account - 1] += money;
                return true;
            }
        }

        public boolean withdraw(int account, long money) {
            if (!isValidAccount(account)) {
                return false;
            }
            synchronized (locks[account - 1]) {
                if (balance[account - 1] >= money) {
                    balance[account - 1] -= money;
                    return true;
                }
                return false;
            }
        }

        private boolean isValidAccount(int account) {
            return account >= 1 && account <= balance.length;
        }

}
