import BookModel from "./BookModel.ts";
import './Book.css'
import axios from "axios";

type Book = {
    book: BookModel;
    getFavoriteBooks: () => void;
}
const Book = (props: Book) => {
    const safeSrc = "https://images.unsplash.com/photo-1556566952-11eff3d06ed4?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8OTB8fGJvb2t8ZW58MHx8MHx8fDA%3D&auto=format&fit=crop&w=800&q=60";


    const addToFavorites = async () => {
        try {
            const response = await axios.post(`http://localhost:8080/api/books/${props.book.id}/favorites`);
            console.log("add response", response)
        } catch (error) {
            console.error('Error fetching books:', error);
        }
    };

    const deleteFromFavorites = async () => {
        try {
            const response = await axios.delete(`http://localhost:8080/api/books/favorites/delete/${props.book.id}`);
            props.getFavoriteBooks();
            console.log("delete response ", response)
        } catch (error) {
            console.error('Error fetching books:', error);
        }
    };

    return (
        <div className="book">
            <img src={props.book.imageUrl ? props.book.imageUrl : safeSrc} alt={props.book.title}
                 onError={(e) => {
                     e.currentTarget.src = 'https://upload.wikimedia.org/wikipedia/en/c/c3/The_Martian_2014.jpg'}}/>

            <div className="book-title">
                <h4>{props.book.title}</h4>
            </div>
            <p>{(props.book.authors ?? []).map(author => <span>{author}</span>)}</p>
            <p>$ {props.book.price.toFixed(2)}</p>
            {window.location.pathname.includes("books")
                ? (<button className="add-to-favorites" onClick={addToFavorites}>add to favorites</button>)
            : (<button className="add-to-favorites" onClick={deleteFromFavorites}>delete</button>)}
        </div>
    );
};

export default Book;