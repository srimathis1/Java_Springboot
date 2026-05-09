import React from "react";
import { useNavigate } from "react-router-dom";

function UserSidebar({ user, setUser }) {

    const navigate = useNavigate();

    const logout = () => {
        localStorage.removeItem("user");
        setUser(null);
        navigate("/");
    };

    return (
        <div className="user-sidebar">

            <h2>👤 User</h2>

            <div className="user-info">
                <p>{user?.username}</p>
                <small>{user?.email}</small>
            </div>

            <button
                className="sidebar-btn active-btn"
            >
                Search Routes
            </button>

            <button
                className="logout-btn"
                onClick={logout}
            >
                Logout
            </button>

        </div>
    );
}

export default UserSidebar;