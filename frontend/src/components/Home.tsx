import './Home.css'
import {Link} from "react-router-dom";
const Home = () => {
    return (
        <div className="home">
            <h1>welcome to my book gallery</h1>
            <Link to="/books">visit the gallery</Link>
        </div>
    )
}
export default Home
