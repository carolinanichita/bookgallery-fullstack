import './App.css'

import GalleryBook from "./components/GalleryBook.tsx";
import {Routes, Route, BrowserRouter, Link} from "react-router-dom";
import Home from "./components/Home.tsx";
import FavoriteBooks from "./components/FavoriteBooks.tsx";
function App() {
    return (
        <BrowserRouter>
            <nav>
                <ul>
                    <li><Link to="/">home</Link></li>
                    <li><Link to="/books">gallery</Link></li>
                    <li><Link to="/favorites">favorite</Link></li>
                </ul>
                <Routes>
                    <Route path="/books" element={<GalleryBook />}></Route>
                    <Route path="/favorites" element={<FavoriteBooks />}></Route>
                    <Route path="/" element={<Home />}></Route>
                </Routes>
            </nav>
        </BrowserRouter>);
}
export default App
