import React, { useState } from "react";
import './Registration.css'
import { REGISTRATION_ENDPOINT } from '../AppConfig'
import { Link } from "react-router-dom";

const Registration = () => {

    const [username, setUsername] = useState('');
    const [password, setPassword] = useState('');


    const sendLoginRequest = async () => {
        if (username != '' && password != '') {
            const requestOptions = {
                method: 'POST',
                headers: { 'Content-Type': 'application/json' },
                body: JSON.stringify(
                    {
                        username: username,
                        password: password
                    }
                )
            };

            const response = await fetch(REGISTRATION_ENDPOINT, requestOptions);
            console.log('response: ' + response.json());
        }
    }

    return (
        <div className="registration-container">
            <form>
                <h3>Sign up</h3>

                <div>
                    <input type="text" placeholder="Enter username" onChange={e => setUsername(e.target.value)} />
                </div>
                <div>
                    <input type="password" placeholder="Enter password" onChange={e => setPassword(e.target.value)} />
                </div>
                <button type="button" onClick={sendLoginRequest}>Sign in </button>
                <span>
                    Already have an account? 
                </span>
                <Link to="/login">
                    Log in!
                </Link>
            </form>
        </div>
    );
}

export default Registration;