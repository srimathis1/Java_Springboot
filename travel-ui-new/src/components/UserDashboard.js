import React from "react";
import UserSidebar from "./UserSidebar";
import UserRoutes from "./UserRoutes";
import "./User.css";

function UserDashboard({ user, setUser }) {
    return (
        <div className="user-layout">

            <UserSidebar user={user} setUser={setUser} />

            <div className="user-main">
                <div className="user-header">
                    <h1>🌍 Welcome, {user?.username}</h1>
                    <p>Smart AI Travel Booking Platform</p>
                </div>

                <UserRoutes user={user} />

            </div>

        </div>
    );
}

export default UserDashboard;