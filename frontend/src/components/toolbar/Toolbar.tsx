import React from "react";
import Icon from "../note/Icon";
import './Toolbar.css'
import {
    BrowserRouter as Router,
    Routes,
    Route,
    Link,
    Navigate
} from "react-router-dom";

const Toolbar = () => {

    return (
        <div className="toolbar-container">
            <div className="header">
                <span>Sticky Notes :-D</span>
            </div>
            <div className="header-icons">
                <Link to="/login">
                    <Icon name="login" />
                </Link>
            </div>
        </div>
    );
}

export default Toolbar;