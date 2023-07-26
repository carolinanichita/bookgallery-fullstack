import {Button} from "react-bootstrap";
import {useState} from "react";
import "./SearchBar.css"

type SearchBarProps = {
    searchBook: (input: string) => void;
};

const SearchBar = (props: SearchBarProps) => {
    const [userInput, setUserInput] = useState("");

    const onSubmit = () => {
        props.searchBook(userInput);
    };

    return (
        <div className="d-flex j mb-3 w-50 mx-auto search-bar">
            <input
                className="form-control form-control-sm py-1 fs-4 text-capitalize border border-3 border-dark me-2"
                type="text"
                placeholder="Search by title"
                value={userInput}
                onChange={(e) => setUserInput(e.target.value)}
            />
            <Button className="btn btn-primary button-search" onClick={() => onSubmit()}>
                Search
            </Button>
        </div>
    );
};

export default SearchBar;
