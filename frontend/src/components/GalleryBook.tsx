import {useEffect, useState} from "react";
import Book from "./Book.tsx";
import BookModel from "./BookModel.ts";
import './GalleryBook.css'
import axios from "axios";
import SearchBar from "./SearchBar.tsx";

const GalleryBook = () => {
    const [books, setBooks] = useState<BookModel[]>([]);
    const [filteredBooks, setFilteredBooks] = useState<BookModel[]>([]);

    const filterBooks = (value: string) => {
        console.log("Value is:", value);
        const filtered = books.filter((book) => {
            return book.title.toLowerCase().includes(value.toLowerCase());
        });
        console.log("Filtered is:", filtered)
        setFilteredBooks(filtered);
    }

    useEffect(() => {
        console.log("fetch books")
        getBooks();
    }, []);

    const getBooks = async () => {
        try {
            const response = await axios.get('http://localhost:8080/api/books');
            setBooks(response.data);
            setFilteredBooks(response.data)
            console.log("response")
        } catch (error) {
            console.error('Error fetching books:', error);
        }
    };

    const showAllBooks = () => {
        setFilteredBooks(books);
    }
    console.log("filteredBooks: ", filteredBooks)

    return (
        <div className="gallery">
            <h1 className="gallery__name">gallery books</h1>
            <SearchBar searchBook={(value) => filterBooks(value)}/>
            {filteredBooks.length ? <div className="cards">{filteredBooks.map((book) => (
                    <Book key={book.id} book={book} getFavoriteBooks={() => {
                    }}/>))}</div>
                : <div className="no-found">
                    <p>books not found</p>
                    <button onClick={() => showAllBooks()}>show all books</button>
                </div>}
        </div>
    );
};
export default GalleryBook
