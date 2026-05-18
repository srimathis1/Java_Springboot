import React, {
    useEffect,
    useState
} from "react";

import UserSidebar
    from "./UserSidebar";

function UserDashboard() {

    const storedUser =
        JSON.parse(
            localStorage.getItem(
                "user"
            )
        );

    const [vehicles,
        setVehicles] =
        useState([]);

    const [search,
        setSearch] =
        useState("");

    const [bookingDetails,
        setBookingDetails] =
        useState({});

    const [analytics,
        setAnalytics] =
        useState({
            totalTrips: 0,
            totalBookings: 0,
            totalFeedbacks: 0,
            mostBookedPlace: "-",
            topRatedPlace: "-"
        });

    // =====================
    // LOAD DATA
    // =====================

    useEffect(() => {

        fetch(
            "http://localhost:8080/vehicles"
        )
            .then((res) =>
                res.json()
            )
            .then((data) => {

                const activeTrips =
                    data.filter(
                        (v) =>
                            !v.booked
                    );

                setVehicles(
                    activeTrips
                );
            });

        fetch(
            "http://localhost:8080/analytics/dashboard"
        )
            .then((res) =>
                res.json()
            )
            .then((data) =>
                setAnalytics(
                    data
                )
            )
            .catch(() =>
                console.log(
                    "Analytics failed"
                )
            );

    }, []);

    // =====================
    // HANDLE INPUT
    // =====================

    const handleInputChange =
        (
            vehicleId,
            field,
            value
        ) => {

            setBookingDetails(
                (prev) => ({
                    ...prev,

                    [vehicleId]: {

                        ...prev[
                            vehicleId
                            ],

                        [field]:
                        value
                    }
                })
            );
        };

    // =====================
    // BOOKING FIXED
    // =====================

    const handleBooking =
        async (
            vehicleId
        ) => {

            const details =
                bookingDetails[
                    vehicleId
                    ];

            if (
                !details
                    ?.returnDate
            ) {

                alert(
                    "Please select return date"
                );

                return;
            }

            try {

                const response =
                    await fetch(

                        `http://localhost:8080/bookings/book?vehicleId=${vehicleId}&userId=${storedUser.id}&returnDate=${details.returnDate}&familyMembers=${details.familyMembers || 1}`,

                        {
                            method:
                                "POST"
                        }
                    );

                if (
                    response.ok
                ) {

                    alert(
                        "✅ Booking Successful"
                    );

                    window.location.reload();

                } else {

                    alert(
                        "❌ Booking Failed"
                    );
                }

            } catch {

                alert(
                    "❌ Server Error"
                );
            }
        };

    // =====================
    // SEARCH
    // =====================

    const filteredVehicles =
        vehicles.filter(

            (vehicle) =>

                vehicle.destination
                    ?.toLowerCase()
                    .includes(
                        search.toLowerCase()
                    )

                ||

                vehicle.source
                    ?.toLowerCase()
                    .includes(
                        search.toLowerCase()
                    )
        );

    return (

        <div
            style={{
                display:
                    "flex",

                background:
                    "#ececf1",

                minHeight:
                    "100vh"
            }}
        >

            <UserSidebar />

            <div
                style={{
                    flex: 1,
                    marginLeft:
                        "300px",
                    padding:
                        "35px",
                    boxSizing:
                        "border-box"
                }}
            >

                <h1
                    style={{
                        color:
                            "#1b1b78",
                        fontSize:
                            "46px"
                    }}
                >
                    Welcome {
                    storedUser?.username
                } 👋
                </h1>

                <p
                    style={{
                        color:
                            "#666",
                        marginBottom:
                            "35px"
                    }}
                >
                    Explore the best destinations
                </p>

                <div
                    style={{
                        display:
                            "grid",

                        gridTemplateColumns:
                            "repeat(auto-fit,minmax(240px,1fr))",

                        gap:
                            "22px",

                        marginBottom:
                            "40px"
                    }}
                >

                    <AnalyticsCard
                        title="Trips"
                        value={
                            analytics.totalTrips
                        }
                        icon="🚘"
                    />

                    <AnalyticsCard
                        title="Bookings"
                        value={
                            analytics.totalBookings
                        }
                        icon="📖"
                    />

                    <AnalyticsCard
                        title="Feedbacks"
                        value={
                            analytics.totalFeedbacks
                        }
                        icon="⭐"
                    />

                    <AnalyticsCard
                        title="Trending Place"
                        value={
                            analytics.mostBookedPlace
                        }
                        icon="🔥"
                    />

                </div>

                <input
                    type="text"

                    placeholder="🔍 Search Source or Destination"

                    value={
                        search
                    }

                    onChange={(e) =>
                        setSearch(
                            e.target.value
                        )
                    }

                    style={
                        searchStyle
                    }
                />

                <div
                    style={{
                        display:
                            "grid",

                        gridTemplateColumns:
                            "repeat(auto-fit,minmax(380px,1fr))",

                        gap:
                            "30px"
                    }}
                >

                    {filteredVehicles.map(

                        (
                            vehicle
                        ) => (

                            <div
                                key={
                                    vehicle.id
                                }

                                style={
                                    cardStyle
                                }
                            >

                                <h2>
                                    {
                                        vehicle.source
                                    }

                                    {" → "}

                                    {
                                        vehicle.destination
                                    }
                                </h2>

                                <p>
                                    🚘 {
                                    vehicle.vehicleType
                                }
                                </p>

                                <p>
                                    📅 {
                                    vehicle.departureDate
                                }
                                </p>

                                <p>
                                    🕒 {
                                    vehicle.departureTime
                                }
                                </p>

                                <p>
                                    💰 ₹{
                                    vehicle.price
                                }
                                </p>

                                <input
                                    type="date"

                                    onChange={(e) =>
                                        handleInputChange(
                                            vehicle.id,
                                            "returnDate",
                                            e.target.value
                                        )
                                    }

                                    style={
                                        inputStyle
                                    }
                                />

                                <input
                                    type="number"

                                    min="1"

                                    placeholder="Family Members"

                                    onChange={(e) =>
                                        handleInputChange(
                                            vehicle.id,
                                            "familyMembers",
                                            e.target.value
                                        )
                                    }

                                    style={
                                        inputStyle
                                    }
                                />

                                <button
                                    onClick={() =>
                                        handleBooking(
                                            vehicle.id
                                        )
                                    }

                                    style={
                                        bookBtn
                                    }
                                >
                                    Book Now
                                </button>

                            </div>
                        )
                    )}

                </div>

            </div>

        </div>
    );
}

function AnalyticsCard({
                           title,
                           value,
                           icon
                       }) {

    return (
        <div
            style={{
                background:
                    "white",

                borderRadius:
                    "22px",

                padding:
                    "25px"
            }}
        >
            <h3>
                {icon} {title}
            </h3>

            <h1>
                {value}
            </h1>
        </div>
    );
}

const cardStyle = {
    background: "white",
    borderRadius: "24px",
    padding: "28px"
};

const searchStyle = {
    width: "100%",
    padding: "18px",
    marginBottom: "35px"
};

const inputStyle = {
    width: "100%",
    padding: "14px",
    marginTop: "15px"
};

const bookBtn = {
    width: "100%",
    padding: "16px",
    background: "#1b1b78",
    border: "none",
    color: "white",
    borderRadius: "14px",
    marginTop: "18px",
    cursor: "pointer"
};

export default UserDashboard;