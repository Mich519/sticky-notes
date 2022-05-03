import React, { useState } from "react";
import './Login.css'
import '../components/common/Common.css'
import { Link } from "react-router-dom";
import { LOGIN_ENDPOINT } from "../BackendUrls";
import axios from 'axios';


const Login = () => {


    const [username, setUsername] = useState('');
    const [password, setPassword] = useState('');

    const sendLoginRequest = async () => {
        if (username !== '' && password !== '') {
            try {
                const response = await axios.post(LOGIN_ENDPOINT, {
                    username: username,
                    password: password,

                }, {
                    withCredentials: true
                })
                console.log(response);

            } catch (error) {
                console.log(error);
            }
        }
    }

    return (
        <div className="login-container">
            <form>
                <h3>Log in</h3>

                <div className="form-group">
                    <input type="text" placeholder="Enter username" onChange={e => setUsername(e.target.value)} />
                </div>

                <div className="form-group">
                    <input type="password" placeholder="Enter password" onChange={e => setPassword(e.target.value)} />
                </div>

                <div className="form-group">
                    <div className="custom-control custom-checkbox">
                    </div>
                </div>
                <button type="button" onClick={sendLoginRequest}>Sign in</button>
            </form>
            <span>
                Don't have an account?
            </span>
            <Link to="/signup">
                Sign up!
            </Link>
        </div>
    );
}

export default Login;
