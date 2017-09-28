package model;

public class Book {
    private String name;
    private String author;
    private String year;
    private String genre;

    private Book(Builder builder) {
        this.name = builder.name;
        this.author = builder.author;
        this.year = builder.year;
        this.genre = builder.genre;
    }

    @Override
    public String toString() {
        return "Book: " + name
                + " author: " + author
                + " year: " + year
                + " genre: " + genre;
    }


    public static class Builder {
        private String name;
        private String author;
        private String year;
        private String genre;

        public Builder() {

        }

        public Builder setName(String name) {
            this.name = name;
            return this;
        }

        public Builder setAuthor(String author) {
            this.author = author;
            return this;
        }

        public Builder setYear(String year) {
            this.year = year;
            return this;
        }

        public Builder setGenre(String genre) {
            this.genre = genre;
            return this;
        }

        public Book build() {
            return new Book(Builder.this);
        }
    }
}
