import './App.css'
import {Navigate, Route, Routes} from "react-router-dom";
import HomePage from "./pages/HomePage.tsx";
import Navbar from "./pages/Navbar.tsx";
import LoginPage from "./pages/LoginPage.tsx";
import AboutPage from "./pages/AboutPage.tsx";
import PrivateRoute from "./components/PrivateRoute.tsx";
import EditingPage from "./pages/EditingPage.tsx";
import {useEffect, useState} from "react";
import SearchPage from "./pages/SearchPage.tsx";

function App() {
    const [isLoading, setIsLoading] = useState(true);

    useEffect(() => {
        setTimeout(() => setIsLoading(false), 500);
    }, []);

    if (isLoading) {
        return <h3>Обработка...</h3>;
    }

    return (
        <>
            <Navbar/>
            <Routes>
                <Route path="/" element={<Navigate to ="/home" replace />}/>
                <Route path="/home" element={<HomePage/>}/>
                <Route path="/login" element={<LoginPage/>}/>
                <Route path="/search" element={<SearchPage />}/>
                <Route element={<PrivateRoute/>}>
                    <Route path="/about" element={<AboutPage/>}/>
                    <Route path="/edit" element={<EditingPage/>}/>
                </Route>
            </Routes>
        </>
    )
}

export default App
