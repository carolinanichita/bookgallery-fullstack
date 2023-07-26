import BookModel from "./BookModel.ts";
import Book from "./Book.tsx";
import {Link} from "react-router-dom";
import {useEffect, useState} from "react";
import axios from "axios";
import "./FavoriteBooks.css"

const FavoriteBooks = () => {
    const [favoriteBooks, setFavoriteBooks] = useState<BookModel[]>([]);

    useEffect(() => {
        console.log("fetch books")
        getFavoriteBooks();
    }, []);

    const getFavoriteBooks = async () => {
        try {
            const response = await axios.get('http://localhost:8080/api/books/favorites');
            setFavoriteBooks(response.data);
            console.log("response")
        } catch (error) {
            console.error('Error fetching books:', error);
        }
    };
    console.log("Path name: ", window.location.pathname)
    console.log("href name: ", window.location.href)

    return (
        <>
            {favoriteBooks.length
                ? <div className="cards">
                    {favoriteBooks.map((book) => (<Book key={book.id} book={book} getFavoriteBooks={() => getFavoriteBooks()}/>))}
                </div>
                : <div className="no-found">
                    <p>books not found</p>
                    <Link to="/books">go to gallery</Link>
                </div>
            }
        </>
    )
}
export default FavoriteBooks
