import {Link} from "react-router-dom";

function HomePage() {
    return (
    <div>
        <h1>VETMED HOMEPAGE</h1>
        <Link to="/edit">
            <button style={{padding: '10px 20px', fontSize: '16px'}}>
                EDITING
            </button>
        </Link>
        <Link to="/search">
            <button style={{padding: '10px 20px', fontSize: '16px'}}>
                SEARCH
            </button>
        </Link>
    </div>
)
}

export default HomePage;