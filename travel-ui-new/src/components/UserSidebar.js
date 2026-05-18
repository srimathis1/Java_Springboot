import {
    useNavigate,
    useLocation
} from "react-router-dom";

function UserSidebar() {

    const navigate =
        useNavigate();

    const location =
        useLocation();

    const user =
        JSON.parse(
            localStorage.getItem(
                "user"
            )
        );

    // =====================
    // LOGOUT
    // =====================

    const logout = () => {

        localStorage.removeItem(
            "user"
        );

        navigate("/");
    };

    // =====================
    // BUTTON STYLE
    // =====================

    const menuButton =
        (path) => ({

            width:
                "100%",

            padding:
                "16px",

            border:
                "none",

            borderRadius:
                "14px",

            marginBottom:
                "18px",

            cursor:
                "pointer",

            fontSize:
                "18px",

            fontWeight:
                "600",

            transition:
                "0.3s",

            background:
                location.pathname
                === path
                    ? "#4d4de3"
                    : "#2d2d74",

            color:
                "white",

            boxShadow:
                "0 4px 10px rgba(0,0,0,0.2)"
        });

    return (

        <div
            style={{
                width:
                    "240px",

                background:
                    "#15154d",

                color:
                    "white",

                minHeight:
                    "100vh",

                padding:
                    "28px",

                position:
                    "fixed",

                left: 0,

                top: 0
            }}
        >

            {/* TITLE */}

            <h1
                style={{
                    marginBottom:
                        "10px"
                }}
            >
                User Panel
            </h1>

            {/* USER */}

            <p
                style={{
                    opacity:
                        0.8,

                    marginBottom:
                        "20px"
                }}
            >
                Welcome,
                {" "}
                {
                    user?.username
                }
            </p>

            <hr
                style={{
                    border:
                        "1px solid rgba(255,255,255,0.2)",

                    marginBottom:
                        "25px"
                }}
            />

            {/* DASHBOARD */}

            <button
                style={menuButton(
                    "/user"
                )}

                onClick={() =>
                    navigate(
                        "/user"
                    )
                }
            >
                🏠 Dashboard
            </button>

            {/* PROFILE */}

            <button
                style={menuButton(
                    "/profile"
                )}

                onClick={() =>
                    navigate(
                        "/profile"
                    )
                }
            >
                👤 Profile
            </button>

            {/* BOOKINGS */}

            <button
                style={menuButton(
                    "/my-bookings"
                )}

                onClick={() =>
                    navigate(
                        "/my-bookings"
                    )
                }
            >
                📖 My Bookings
            </button>

            {/* LOGOUT */}

            <button
                style={{
                    ...menuButton(
                        ""
                    ),

                    background:
                        "#ff1a1a",

                    marginTop:
                        "30px"
                }}

                onClick={
                    logout
                }
            >
                🚪 Logout
            </button>

        </div>
    );
}

export default UserSidebar;