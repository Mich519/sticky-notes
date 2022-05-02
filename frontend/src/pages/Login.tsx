import React from "react";
import './Login.css'
import '../components/common/Common.css'
import { Link } from "react-router-dom";

const Login = () => {

    return (
        <div className="login-container">
            <form>
                <h3>Log in</h3>

                <div className="form-group">
                    <input type="email" placeholder="Enter email" />
                </div>

                <div className="form-group">
                    <input type="password" placeholder="Enter password" />
                </div>

                <div className="form-group">
                    <div className="custom-control custom-checkbox">
                    </div>
                </div>
                <button type="submit">Sign in</button>
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
