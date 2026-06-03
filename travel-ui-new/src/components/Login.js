import React, { useState } from "react";
import { useNavigate } from "react-router-dom";

function Login({ setUser }) {

    const navigate = useNavigate();

    const [role, setRole] = useState("");
    const [isRegister, setIsRegister] =
        useState(false);

    const [form, setForm] = useState({
        username: "",
        email: "",
        password: ""
    });

    const handleChange = (e) => {
        setForm({
            ...form,
            [e.target.name]:
            e.target.value
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
                username:
                    "admin",
                role:
                    "ADMIN"
            };

            localStorage.setItem(
                "user",
                JSON.stringify(
                    adminUser
                )
            );

            setUser(adminUser);

            navigate("/admin");

        } else {

            alert(
                "Wrong password"
            );
        }
    };

    // ======================
    // USER LOGIN
    // ======================

    const userLogin =
        async () => {

            try {

                const res =
                    await fetch(

                        "http://localhost:8080/auth/login",

                        {
                            method:
                                "POST",

                            headers: {
                                "Content-Type":
                                    "application/json"
                            },

                            body:
                                JSON.stringify({
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
                    role:
                        "USER"
                };

                localStorage.setItem(
                    "user",
                    JSON.stringify(
                        userData
                    )
                );

                setUser(userData);

                navigate("/user");

            } catch {

                alert(
                    "Login Failed"
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
                    "Registered Successfully"
                );

                setIsRegister(
                    false
                );

            } catch {

                alert(
                    "Registration Failed"
                );
            }
        };

    return (

        <div
            style={{
                minHeight:
                    "100vh",

                display:
                    "flex",

                justifyContent:
                    "center",

                alignItems:
                    "center",

                flexWrap:
                    "wrap",

                gap:
                    "60px",

                padding:
                    "50px 8%",

                background:
                    "linear-gradient(135deg,#071952,#0B57D0,#3B82F6)",

                position:
                    "relative",

                overflow:
                    "hidden"
            }}
        >

            {/* FLOATING CIRCLES */}

            <div
                style={{
                    position:
                        "absolute",

                    width:
                        "300px",

                    height:
                        "300px",

                    borderRadius:
                        "50%",

                    background:
                        "rgba(255,255,255,0.08)",

                    top:
                        "-100px",

                    right:
                        "-80px",

                    animation:
                        "float 6s ease-in-out infinite"
                }}
            />

            <div
                style={{
                    position:
                        "absolute",

                    width:
                        "250px",

                    height:
                        "250px",

                    borderRadius:
                        "50%",

                    background:
                        "rgba(255,255,255,0.06)",

                    bottom:
                        "-100px",

                    left:
                        "-70px",

                    animation:
                        "float 7s ease-in-out infinite"
                }}
            />

            {/* LEFT SIDE */}

            <div
                style={{
                    flex:
                        "1",

                    minWidth:
                        "320px",

                    maxWidth:
                        "650px",

                    color:
                        "white",

                    zIndex:
                        2
                }}
            >

                <h1
                    style={{
                        fontSize:
                            "clamp(50px,6vw,80px)",

                        fontWeight:
                            "800",

                        marginBottom:
                            "20px",

                        letterSpacing:
                            "1px"
                    }}
                >
                    TravelEase
                </h1>

                <h2
                    style={{
                        fontSize:
                            "clamp(34px,4vw,58px)",

                        lineHeight:
                            "1.3",

                        marginBottom:
                            "25px",

                        fontWeight:
                            "700"
                    }}
                >
                    Discover India
                    With Comfort &
                    Confidence
                </h2>

                <p
                    style={{
                        fontSize:
                            "22px",

                        color:
                            "#dbeafe",

                        lineHeight:
                            "1.8",

                        marginBottom:
                            "40px"
                    }}
                >
                    Explore beautiful
                    destinations,
                    manage trips,
                    discover travel
                    analytics and
                    enjoy seamless
                    booking experiences.
                </p>

                {/* FEATURES */}

                <Feature
                    text="Smart Route Booking"
                />

                <Feature
                    text="Travel Analytics"
                />

                <Feature
                    text="Trip & Vehicle Management"
                />

                <Feature
                    text="Customer Reviews & Ratings"
                />

                {/* QUOTE */}

                <div
                    style={{
                        marginTop:
                            "50px",

                        padding:
                            "28px",

                        background:
                            "rgba(255,255,255,0.12)",

                        border:
                            "1px solid rgba(255,255,255,0.15)",

                        backdropFilter:
                            "blur(12px)",

                        borderRadius:
                            "28px",

                        maxWidth:
                            "550px"
                    }}
                >
                    <p
                        style={{
                            margin:
                                0,

                            fontSize:
                                "28px",

                            fontStyle:
                                "italic",

                            lineHeight:
                                "1.6",

                            color:
                                "#e0f2fe"
                        }}
                    >
                        "Journey beyond
                        destinations —
                        create memorable
                        experiences
                        with every trip."
                    </p>
                </div>

            </div>

            {/* LOGIN CARD */}

            <div
                style={{
                    width:
                        "100%",

                    maxWidth:
                        "420px",

                    background:
                        "rgba(255,255,255,0.14)",

                    backdropFilter:
                        "blur(18px)",

                    border:
                        "1px solid rgba(255,255,255,0.15)",

                    borderRadius:
                        "35px",

                    padding:
                        "45px",

                    boxShadow:
                        "0 20px 45px rgba(0,0,0,0.25)",

                    zIndex:
                        2
                }}
            >

                {!role && (
                    <>
                        <h1
                            style={{
                                textAlign:
                                    "center",

                                color:
                                    "white",

                                marginBottom:
                                    "10px",

                                fontSize:
                                    "42px"
                            }}
                        >
                            Welcome
                        </h1>

                        <p
                            style={{
                                textAlign:
                                    "center",

                                color:
                                    "#dbeafe",

                                marginBottom:
                                    "35px"
                            }}
                        >
                            Choose your role
                            to continue
                        </p>

                        <Button
                            text="Continue as User"
                            onClick={() =>
                                setRole(
                                    "USER"
                                )
                            }
                        />

                        <Button
                            text="Continue as Admin"
                            onClick={() =>
                                setRole(
                                    "ADMIN"
                                )
                            }
                        />
                    </>
                )}

                {role === "ADMIN" && (
                    <>
                        <Title
                            text="Admin Login"
                        />

                        <Input
                            type="password"
                            name="password"
                            placeholder="Enter Password"
                            onChange={
                                handleChange
                            }
                        />

                        <Button
                            text="Login"
                            onClick={
                                adminLogin
                            }
                        />

                        <Back
                            onClick={() =>
                                setRole("")
                            }
                        />
                    </>
                )}

                {role === "USER" && (
                    <>
                        <Title
                            text={
                                isRegister
                                    ? "Create Account"
                                    : "User Login"
                            }
                        />

                        <Input
                            name="username"
                            placeholder="Username"
                            onChange={
                                handleChange
                            }
                        />

                        {isRegister && (
                            <Input
                                name="email"
                                placeholder="Email"
                                onChange={
                                    handleChange
                                }
                            />
                        )}

                        <Input
                            type="password"
                            name="password"
                            placeholder="Password"
                            onChange={
                                handleChange
                            }
                        />

                        <Button
                            text={
                                isRegister
                                    ? "Register"
                                    : "Login"
                            }

                            onClick={
                                isRegister
                                    ? registerUser
                                    : userLogin
                            }
                        />

                        <p
                            onClick={() =>
                                setIsRegister(
                                    !isRegister
                                )
                            }

                            style={{
                                textAlign:
                                    "center",

                                color:
                                    "white",

                                cursor:
                                    "pointer"
                            }}
                        >
                            {
                                isRegister
                                    ? "Already have account? Login"
                                    : "New user? Register"
                            }
                        </p>

                        <Back
                            onClick={() =>
                                setRole("")
                            }
                        />
                    </>
                )}
            </div>

            <style>
                {`
                @keyframes float {
                    0% {
                        transform: translateY(0px);
                    }
                    50% {
                        transform: translateY(-20px);
                    }
                    100% {
                        transform: translateY(0px);
                    }
                }
                `}
            </style>
        </div>
    );
}

function Feature({ text }) {
    return (
        <div
            style={{
                display:
                    "flex",

                alignItems:
                    "center",

                gap:
                    "14px",

                marginBottom:
                    "22px",

                fontSize:
                    "22px",

                color:
                    "white"
            }}
        >
            <div
                style={{
                    width:
                        "12px",

                    height:
                        "12px",

                    borderRadius:
                        "50%",

                    background:
                        "#93C5FD"
                }}
            />

            {text}
        </div>
    );
}

function Button({
                    text,
                    onClick
                }) {

    return (
        <button
            onClick={onClick}
            style={{
                width:
                    "100%",

                padding:
                    "18px",

                marginBottom:
                    "18px",

                border:
                    "none",

                borderRadius:
                    "18px",

                background:
                    "linear-gradient(135deg,#2563EB,#3B82F6)",

                color:
                    "white",

                fontWeight:
                    "700",

                fontSize:
                    "18px",

                cursor:
                    "pointer",

                transition:
                    "0.3s",

                boxShadow:
                    "0 10px 25px rgba(37,99,235,0.35)"
            }}

            onMouseEnter={(e) => {
                e.target.style.transform =
                    "translateY(-3px)";
            }}

            onMouseLeave={(e) => {
                e.target.style.transform =
                    "translateY(0px)";
            }}
        >
            {text}
        </button>
    );
}

function Input(props) {

    return (
        <input
            {...props}
            style={{
                width:
                    "100%",

                padding:
                    "18px",

                marginBottom:
                    "18px",

                borderRadius:
                    "18px",

                border:
                    "1px solid rgba(255,255,255,0.15)",

                background:
                    "rgba(255,255,255,0.12)",

                color:
                    "white",

                fontSize:
                    "16px",

                outline:
                    "none",

                boxSizing:
                    "border-box"
            }}
        />
    );
}

function Title({ text }) {

    return (
        <h2
            style={{
                textAlign:
                    "center",

                color:
                    "white",

                marginBottom:
                    "25px"
            }}
        >
            {text}
        </h2>
    );
}

function Back({
                  onClick
              }) {

    return (
        <p
            onClick={onClick}
            style={{
                textAlign:
                    "center",

                color:
                    "#dbeafe",

                cursor:
                    "pointer"
            }}
        >
            ← Back
        </p>
    );
}

export default Login;