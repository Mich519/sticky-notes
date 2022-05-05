import React, { useState } from "react";
import { REGISTRATION_ENDPOINT } from '../BackendUrls'
import { Link } from "react-router-dom";
import { useNavigate } from 'react-router-dom'

const Registration = () => {

    const [username, setUsername] = useState('');
    const [password, setPassword] = useState('');
    const navigator = useNavigate();


    const sendSignupRequest = async () => {
        if (username != '' && password != '') {
            const requestOptions = {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify(
                    {
                        username: username,
                        password: password
                    }
                )
            };

            const response = await fetch(REGISTRATION_ENDPOINT, requestOptions);
            if (response.status == 201) {
                navigator('/login', { replace: true });
            }
            console.log(response);
        }
    }

    return (
        <div className="form-container">
            <div className="form">
                <h2>Sign up</h2>
                <div className="form-group">
                    <input type="text" placeholder="Enter username" onChange={e => setUsername(e.target.value)} />
                </div>
                <div className="form-group">
                    <input type="password" placeholder="Enter password" onChange={e => setPassword(e.target.value)} />
                </div>
                <button type="button" className="form-button" onClick={sendSignupRequest}>Sign up </button>
                <span>
                    Already have an account? &nbsp;
                    <Link to="/login">
                        Log in!
                    </Link>
                </span>
            </div>
        </div>
    );
}

export default Registration;