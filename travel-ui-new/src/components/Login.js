import React, { useState } from "react";
import { useNavigate } from "react-router-dom";

function Login({ setUser }) {

    const navigate = useNavigate();

    const [role, setRole] = useState("");
    const [isRegister, setIsRegister] = useState(false);

    const [form, setForm] = useState({
        username: "",
        email: "",
        password: ""
    });

    const handleChange = (e) => {
        setForm({
            ...form,
            [e.target.name]: e.target.value
        });
    };

    // ======================
    // ADMIN LOGIN
    // ======================

    const adminLogin = () => {

        if (
            form.password ===
            "admin123"
        ) {

            const adminUser = {
                username: "admin",
                role: "ADMIN"
            };

            localStorage.setItem(
                "user",
                JSON.stringify(adminUser)
            );

            setUser(adminUser);

            navigate("/admin");

        } else {

            alert(
                "Wrong password ❌"
            );
        }
    };

    // ======================
    // USER LOGIN
    // ======================

    const userLogin = async () => {

        try {

            const res =
                await fetch(

                    "http://localhost:8080/auth/login",

                    {
                        method: "POST",

                        headers: {
                            "Content-Type":
                                "application/json"
                        },

                        body: JSON.stringify({

                            username:
                            form.username,

                            password:
                            form.password
                        })
                    }
                );

            if (!res.ok)
                throw new Error();

            const data =
                await res.json();

            const userData = {

                ...data,

                role: "USER"
            };

            localStorage.setItem(

                "user",

                JSON.stringify(userData)
            );

            setUser(userData);

            navigate("/user");

        } catch {

            alert(
                "Login failed ❌"
            );
        }
    };

    // ======================
    // REGISTER
    // ======================

    const registerUser =
        async () => {

            try {

                const res =
                    await fetch(

                        "http://localhost:8080/auth/register",

                        {
                            method:
                                "POST",

                            headers: {
                                "Content-Type":
                                    "application/json"
                            },

                            body:
                                JSON.stringify(
                                    form
                                )
                        }
                    );

                if (!res.ok)
                    throw new Error();

                alert(
                    "Registered Successfully ✅"
                );

                setIsRegister(false);

            } catch {

                alert(
                    "Registration Failed ❌"
                );
            }
        };

    return (

        <div style={styles.container}>

            {/* LEFT SIDE */}

            <div style={styles.leftSection}>

                <h1 style={styles.logo}>
                    🌍 TravelEase
                </h1>

                <h2 style={styles.heading}>
                    Explore India With Comfort
                </h2>

                <p style={styles.text}>
                    Book smart, travel safe
                    and discover amazing
                    destinations with ease.
                </p>

                <div style={styles.features}>

                    <p>
                        ✈ Smart Booking
                    </p>

                    <p>
                        🛡 Safe Travel
                    </p>

                    <p>
                        🌄 Best Destinations
                    </p>

                    <p>
                        📊 Analytics Based
                        Recommendations
                    </p>

                </div>

                <h3 style={styles.quote}>
                    "Your Journey Starts Here"
                </h3>

            </div>

            {/* RIGHT CARD */}

            <div style={styles.card}>

                {!role && (

                    <>

                        <h1
                            style={{
                                color:
                                    "#1e2088"
                            }}
                        >
                            Welcome
                        </h1>

                        <p
                            style={{
                                marginBottom:
                                    "25px"
                            }}
                        >
                            Choose your role
                            to continue
                        </p>

                        <button
                            style={
                                styles.button
                            }

                            onClick={() =>
                                setRole(
                                    "USER"
                                )
                            }
                        >
                            👤 Continue as User
                        </button>

                        <button
                            style={
                                styles.button
                            }

                            onClick={() =>
                                setRole(
                                    "ADMIN"
                                )
                            }
                        >
                            🛠 Continue as Admin
                        </button>

                    </>
                )}

                {/* ADMIN */}

                {
                    role ===
                    "ADMIN" && (

                        <>
                            <h2>
                                Admin Login
                            </h2>

                            <input
                                type="password"
                                name="password"
                                placeholder="Enter Password"
                                style={
                                    styles.input
                                }
                                onChange={
                                    handleChange
                                }
                            />

                            <button
                                style={
                                    styles.button
                                }

                                onClick={
                                    adminLogin
                                }
                            >
                                Login
                            </button>

                            <p
                                style={
                                    styles.back
                                }

                                onClick={() =>
                                    setRole("")
                                }
                            >
                                ← Back
                            </p>
                        </>
                    )
                }

                {/* USER */}

                {
                    role ===
                    "USER" && (

                        <>
                            <h2>
                                {
                                    isRegister
                                        ? "Create Account"
                                        : "User Login"
                                }
                            </h2>

                            <input
                                name="username"
                                placeholder="Username"
                                style={
                                    styles.input
                                }
                                onChange={
                                    handleChange
                                }
                            />

                            {
                                isRegister && (

                                    <input
                                        name="email"
                                        placeholder="Email"
                                        style={
                                            styles.input
                                        }
                                        onChange={
                                            handleChange
                                        }
                                    />
                                )
                            }

                            <input
                                type="password"
                                name="password"
                                placeholder="Password"
                                style={
                                    styles.input
                                }
                                onChange={
                                    handleChange
                                }
                            />

                            <button
                                style={
                                    styles.button
                                }

                                onClick={
                                    isRegister
                                        ? registerUser
                                        : userLogin
                                }
                            >
                                {
                                    isRegister
                                        ? "Register"
                                        : "Login"
                                }
                            </button>

                            <p
                                style={
                                    styles.link
                                }

                                onClick={() =>
                                    setIsRegister(

                                        !isRegister
                                    )
                                }
                            >
                                {
                                    isRegister

                                        ?

                                        "Already have account? Login"

                                        :

                                        "New User? Register"
                                }
                            </p>

                            <p
                                style={
                                    styles.back
                                }

                                onClick={() =>
                                    setRole("")
                                }
                            >
                                ← Back
                            </p>
                        </>
                    )
                }

            </div>
        </div>
    );
}

const styles = {

    container: {

        minHeight: "100vh",

        display: "flex",

        justifyContent:
            "space-between",

        alignItems: "center",

        padding: "60px",

        background:
            "linear-gradient(to right,#1e2088,#8bb3d9)"
    },

    leftSection: {

        width: "50%",

        color: "white"
    },

    logo: {

        fontSize: "50px",

        marginBottom: "20px"
    },

    heading: {

        fontSize: "42px",

        marginBottom: "15px"
    },

    text: {

        fontSize: "18px",

        lineHeight: "30px"
    },

    features: {

        marginTop: "30px",

        fontSize: "20px",

        lineHeight: "40px"
    },

    quote: {

        marginTop: "50px",

        fontStyle: "italic"
    },

    card: {

        width: "400px",

        background: "white",

        padding: "40px",

        borderRadius: "25px",

        boxShadow:
            "0 8px 30px rgba(0,0,0,0.2)",

        textAlign: "center"
    },

    input: {

        width: "100%",

        padding: "15px",

        marginBottom: "15px",

        borderRadius: "12px",

        border:
            "1px solid #ccc",

        fontSize: "16px"
    },

    button: {

        width: "100%",

        padding: "15px",

        background:
            "#1e2088",

        color: "white",

        border: "none",

        borderRadius: "12px",

        fontSize: "17px",

        cursor: "pointer",

        marginBottom: "15px"
    },

    link: {

        color: "#1e2088",

        cursor: "pointer"
    },

    back: {

        cursor: "pointer",

        marginTop: "15px",

        color: "#666"
    }
};

export default Login;