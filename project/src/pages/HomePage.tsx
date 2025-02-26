import {Link} from "react-router-dom";
import "../assets/styles/home.css"

function HomePage() {
    return (
        <div className="home-page">
            <h1 className="home-page-header">VETMED HOMEPAGE</h1>
            <div className="home-page-buttons-container">
                <div className="home-page-button">
                    <Link to="/edit">
                        <button style={{padding: '10px 20px', fontSize: '16px'}}>
                            EDITING
                        </button>
                    </Link>
                </div>
                <div className="home-page-button">
                    <Link to="/search">
                        <button style={{padding: '10px 20px', fontSize: '16px'}}>
                            SEARCH
                        </button>
                    </Link>
                </div>
            </div>
        </div>
    )
}

export default HomePage;