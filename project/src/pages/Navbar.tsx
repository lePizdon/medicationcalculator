import {Link, useNavigate} from "react-router-dom";
import "../assets/styles/navbar.css";

function Navbar() {
    const navigate = useNavigate();

    const handleLogout = async () => {
        try {
            const formData = new URLSearchParams();
            formData.append("logout", "true")
            const response = await fetch("/api/logout", {
                method: "POST",
                credentials: "include",
                headers: {
                    "Authorization": `Bearer ${localStorage.getItem("token")}`,
                    "Content-Type": "application/w-xxx-form-urlencoded",
                },
                body: formData,
            });
            if (response.ok) {
                localStorage.removeItem("token");
                navigate("/login");
            } else {
                console.error("Ошибка выхода: ", response.statusText);
            }
        } catch (error) {
            console.error("Ошибка сети: ", error);
        }
    };
    return (
        <nav>
            <ul>
                <li>
                    <Link to="/home">HOME</Link>
                </li>
                <li>
                    <Link to="/about">ABOUT</Link>
                </li>
                <li>
                    <Link to="/search">SEARCH MEDICATION</Link>
                </li>
                <li>
                    <button onClick={handleLogout} className="logout-button">ВЫХОД</button>
                </li>
            </ul>
        </nav>
    );
}

export default Navbar;