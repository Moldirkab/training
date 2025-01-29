package controllers;

import repositories.IBookRepositoryDraft;

import models.Book;

public class BookControllerDraft implements IBookControllerDraft {
    private final IBookRepositoryDraft repo;
    public BookControllerDraft(IBookRepositoryDraft repo) {
        this.repo = repo;
    }

    @Override
    public Book searchByName(String title) {
        Book book = repo.searchByName(title);
        return book;
    }
}
