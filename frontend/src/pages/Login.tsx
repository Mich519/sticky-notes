import React, { useState, useEffect } from "react";
import { Link } from "react-router-dom";
import { LOGIN_ENDPOINT } from "../BackendUrls";
import axios from 'axios';
import { useNavigate } from 'react-router-dom'


const Login = () => {
    const [username, setUsername] = useState('');
    const [password, setPassword] = useState('');
    const [showLoginFailureMessage, setShowLoginFailureMessage] = useState(false);
    const navigator = useNavigate();

    useEffect(() => {

    }, [showLoginFailureMessage]);

    const sendLoginRequest = async () => {
        if (username !== '' && password !== '') {
            try {
                const response = await axios.post(LOGIN_ENDPOINT, {
                    username: username,
                    password: password,

                }, {
                    withCredentials: true
                })

                if (response.status === 200) {
                    navigator('/', { replace: true });
                }

            } catch (error) {
                console.log(error);
                setShowLoginFailureMessage(true);
            }
        }
    }

    return (
        <div className="form-container">
            <div className="form">
                <h2>Log in</h2>

                <div className="form-group">
                    <input type="text" placeholder="Enter username" onChange={e => setUsername(e.target.value)} />
                </div>

                <div className="form-group">
                    <input type="password" placeholder="Enter password" onChange={e => setPassword(e.target.value)} />
                </div>
                <button type="button" className="form-button" onClick={sendLoginRequest}>Sign in</button>
                <span>
                    Don't have an account? &nbsp;
                    <Link to="/signup">
                        Sign up!
                    </Link>
                </span>
                <div>
                    {
                        showLoginFailureMessage ? <span> Login lub hasło jest nieprawidłowe!</span> : null
                    }
                </div>
            </div>
        </div>
    );
}

export default Login;
