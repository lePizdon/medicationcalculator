import {Navigate, Outlet, useLocation} from "react-router-dom";

function PrivateRoute() {
    const token = localStorage.getItem("token");
    const location = useLocation();

    return token ? <Outlet /> : <Navigate to="/login" replace state = {{ to: location.pathname }}/>;
}

export default PrivateRoute;