import React, { useEffect, useState } from "react";
import axios from "axios";

function UserRoutes({ user }) {

    const [routes, setRoutes] = useState([]);
    const [search, setSearch] = useState("");

    useEffect(() => {
        fetchRoutes();
    }, []);

    const fetchRoutes = async () => {
        try {
            const res = await axios.get("http://localhost:8080/routes");

            setRoutes(res.data);

        } catch (err) {
            console.log(err);
        }
    };

    const bookRoute = async (routeId) => {

        try {

            await axios.post(
                "http://localhost:8080/bookings/book",
                {
                    username: user.username,
                    routeId: routeId
                }
            );

            alert("✅ Booking Confirmed");

            fetchRoutes();

        } catch (err) {

            alert("❌ Booking Failed");

        }
    };

    const filteredRoutes = routes.filter((route) =>
        route.source.toLowerCase().includes(search.toLowerCase()) ||
        route.destination.toLowerCase().includes(search.toLowerCase())
    );

    return (
        <div>

            <div className="search-box">
                <input
                    type="text"
                    placeholder="Search source or destination"
                    value={search}
                    onChange={(e) => setSearch(e.target.value)}
                />
            </div>

            <div className="route-grid">

                {filteredRoutes.map((route) => (

                    <div className="route-card" key={route.id}>

                        <h3>
                            {route.source} → {route.destination}
                        </h3>

                        <p>{route.type}</p>

                        <p>₹{route.price}</p>

                        <p>
                            Seats Available:
                            <strong> {route.seats}</strong>
                        </p>

                        <button
                            className="book-btn"
                            disabled={route.seats <= 0}
                            onClick={() => bookRoute(route.id)}
                        >
                            {route.seats > 0
                                ? "Book Now"
                                : "Seats Full"}
                        </button>

                    </div>

                ))}

            </div>

        </div>
    );
}

export default UserRoutes;