import React,
{
    useEffect,
    useState
}
    from "react";

import UserSidebar
    from "./UserSidebar";

function UserBookings() {

    const [
        bookings,
        setBookings
    ] = useState([]);

    const [
        feedbacks,
        setFeedbacks
    ] = useState({});

    const user =
        JSON.parse(
            localStorage.getItem(
                "user"
            )
        );

    useEffect(() => {

        fetchBookings();

    }, []);

    const fetchBookings =
        async () => {

            try {

                const res =
                    await fetch(

                        `http://localhost:8080/bookings/user/${user.id}`

                    );

                const data =
                    await res.json();

                setBookings(
                    Array.isArray(
                        data
                    )
                        ? data
                        : []
                );

            } catch (error) {

                console.log(
                    error
                );
            }
        };

    // =====================
    // FEEDBACK LOGIC
    // =====================

    const canGiveFeedback =
        (booking) => {

            const today =
                new Date();

            const returnDate =
                new Date(
                    booking.returnDate
                );

            return (

                booking.status ===
                "COMPLETED"

                &&

                today >=
                returnDate
            );
        };

    const handleFeedback =
        (
            bookingId,
            field,
            value
        ) => {

            setFeedbacks(
                prev => ({

                    ...prev,

                    [bookingId]: {

                        ...prev[
                            bookingId
                            ],

                        [field]:
                        value
                    }
                })
            );
        };

    const submitFeedback =
        async (
            booking
        ) => {

            const feedback =
                feedbacks[
                    booking.id
                    ];

            if (
                !feedback
                    ?.rating
            ) {

                alert(
                    "Select rating"
                );

                return;
            }

            try {

                await fetch(

                    "http://localhost:8080/feedback/add",

                    {
                        method:
                            "POST",

                        headers: {

                            "Content-Type":
                                "application/json"
                        },

                        body:
                            JSON.stringify({

                                userId:
                                booking.userId,

                                bookingId:
                                booking.id,

                                destination:
                                booking.destination,

                                rating:
                                    Number(
                                        feedback.rating
                                    ),

                                comments:
                                    feedback.comments
                                    || ""
                            })
                    }
                );

                alert(
                    "Feedback submitted successfully!"
                );

            } catch (error) {

                console.log(
                    error
                );

                alert(
                    "Failed to submit feedback"
                );
            }
        };

    return (

        <div
            style={{
                display:
                    "flex",

                background:
                    "#ececf2",

                minHeight:
                    "100vh"
            }}
        >

            <UserSidebar />

            <div
                style={{
                    marginLeft:
                        "280px",

                    width:
                        "100%",

                    padding:
                        "40px"
                }}
            >

                <h1
                    style={{
                        color:
                            "#1e2088",

                        fontSize:
                            "50px",

                        marginBottom:
                            "30px"
                    }}
                >
                    📖 My Bookings
                </h1>

                {bookings
                    .length ===
                0 ? (

                    <div
                        style={{
                            background:
                                "white",

                            padding:
                                "40px",

                            borderRadius:
                                "20px",

                            textAlign:
                                "center",

                            boxShadow:
                                "0 5px 20px rgba(0,0,0,0.1)"
                        }}
                    >
                        <h2>
                            No Bookings Yet
                        </h2>
                    </div>

                ) : (

                    bookings.map(
                        booking => (

                            <div
                                key={
                                    booking.id
                                }

                                style={{

                                    background:
                                        "white",

                                    borderRadius:
                                        "25px",

                                    padding:
                                        "35px",

                                    marginBottom:
                                        "35px",

                                    boxShadow:
                                        "0 6px 20px rgba(0,0,0,0.1)"
                                }}
                            >

                                <h2
                                    style={{
                                        color:
                                            "#1e2088"
                                    }}
                                >
                                    ✈️ Trip →
                                    {
                                        booking.destination
                                    }
                                </h2>

                                <p>
                                    🚘
                                    Vehicle:
                                    {" "}
                                    {
                                        booking.vehicleType
                                    }
                                </p>

                                <p>
                                    📅
                                    Departure:
                                    {" "}
                                    {
                                        booking.departureDate
                                    }
                                </p>

                                <p>
                                    ⏰
                                    Time:
                                    {" "}
                                    {
                                        booking.departureTime
                                    }
                                </p>

                                <p>
                                    🔁
                                    Return:
                                    {" "}
                                    {
                                        booking.returnDate
                                    }
                                </p>

                                <p>
                                    👨‍👩‍👧‍👦
                                    Family:
                                    {" "}
                                    {
                                        booking.familyMembers
                                    }
                                </p>

                                <p>
                                    💰 ₹
                                    {
                                        booking.price
                                    }
                                </p>

                                <button
                                    style={{
                                        background:
                                            booking.status ===
                                            "COMPLETED"

                                                ? "#1e2088"

                                                : "#555",

                                        color:
                                            "white",

                                        border:
                                            "none",

                                        padding:
                                            "14px 30px",

                                        borderRadius:
                                            "12px",

                                        fontWeight:
                                            "bold"
                                    }}
                                >
                                    {
                                        booking.status
                                    }
                                </button>

                                {/* FEEDBACK */}

                                {canGiveFeedback(
                                    booking
                                ) && (

                                    <div
                                        style={{
                                            marginTop:
                                                "35px",

                                            borderTop:
                                                "1px solid #ddd",

                                            paddingTop:
                                                "25px"
                                        }}
                                    >

                                        <h3>
                                            ⭐ Give
                                            Feedback
                                        </h3>

                                        <select

                                            onChange={
                                                (e) =>

                                                    handleFeedback(

                                                        booking.id,

                                                        "rating",

                                                        e.target.value
                                                    )
                                            }

                                            style={{

                                                width:
                                                    "100%",

                                                padding:
                                                    "15px",

                                                borderRadius:
                                                    "12px",

                                                marginTop:
                                                    "15px"
                                            }}
                                        >

                                            <option>
                                                Select Rating
                                            </option>

                                            <option value="5">
                                                ⭐⭐⭐⭐⭐
                                            </option>

                                            <option value="4">
                                                ⭐⭐⭐⭐
                                            </option>

                                            <option value="3">
                                                ⭐⭐⭐
                                            </option>

                                            <option value="2">
                                                ⭐⭐
                                            </option>

                                            <option value="1">
                                                ⭐
                                            </option>

                                        </select>

                                        <textarea

                                            placeholder=
                                                "Write feedback..."

                                            onChange={
                                                (e) =>

                                                    handleFeedback(

                                                        booking.id,

                                                        "comments",

                                                        e.target.value
                                                    )
                                            }

                                            style={{

                                                width:
                                                    "100%",

                                                marginTop:
                                                    "15px",

                                                padding:
                                                    "15px",

                                                borderRadius:
                                                    "12px",

                                                height:
                                                    "120px"
                                            }}
                                        />

                                        <button

                                            onClick={() =>
                                                submitFeedback(
                                                    booking
                                                )
                                            }

                                            style={{

                                                marginTop:
                                                    "15px",

                                                background:
                                                    "#1e2088",

                                                color:
                                                    "white",

                                                border:
                                                    "none",

                                                width:
                                                    "100%",

                                                padding:
                                                    "15px",

                                                borderRadius:
                                                    "12px",

                                                fontSize:
                                                    "18px",

                                                fontWeight:
                                                    "bold"
                                            }}
                                        >
                                            Submit
                                            Feedback
                                        </button>

                                    </div>
                                )}

                            </div>
                        )
                    )
                )}

            </div>
        </div>
    );
}

export default UserBookings;