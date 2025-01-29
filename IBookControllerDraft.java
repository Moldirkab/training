package controllers;
import models.Book;
public interface IBookControllerDraft {
    Book searchByName(String title);
}
