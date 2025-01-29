package repositories;

import models.Transaction;

public interface ITransactionsRepository {
    boolean borrowBook(Transaction transaction);
    String getDueDate(int book_id);
    boolean returnBook(Transaction transaction);

}
