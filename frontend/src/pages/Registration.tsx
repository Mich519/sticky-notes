import React, { useState } from "react";
import './Registration.css'
import { REGISTRATION_ENDPOINT } from '../AppConfig'

const Registration = () => {

    const [username, setUsername] = useState('');
    const [mail, setMail] = useState('');
    const [password, setPassword] = useState('');


    const sendLoginRequest = () => {
        if (username != '' && mail != '' && password != '') {
            const requestOptions = {
                method: 'POST',
                headers: { 'Content-Type': 'application/json' },
                body: JSON.stringify(
                    {
                        username: username,
                        mail: mail,
                        password: password
                    }
                )
            };

            fetch(REGISTRATION_ENDPOINT, requestOptions)
                .then(response => console.log('response: ' + response.json()))
        }
    }

    return (
        <div className="registration-container">
            <form>
                <h3>Log in</h3>

                <div>
                    <input type="text" placeholder="Enter username" onChange={e => setUsername(e.target.value)} />
                </div>

                <div>
                    <input type="email" placeholder="Enter email" onChange={e => setMail(e.target.value)} />
                </div>

                <div>
                    <input type="password" placeholder="Enter password" onChange={e => setPassword(e.target.value)} />
                </div>

                <button type="button" onClick={sendLoginRequest}>Sign in </button>
            </form>
        </div>
    );
}

export default Registration;