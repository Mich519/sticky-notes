import React from "react";
import {
    BrowserRouter as Router,
    Routes,
    Route,
    Link,
    Navigate
} from "react-router-dom";
import { TypePredicateKind } from "typescript";
import Dashboard from "../pages/Dashboard";
import Login from "../pages/Login";
import Registration from "../pages/Registration";

const AppRouter = () => {
    return (
        <Router>
            <Routes>
                <Route path="/" element={<Dashboard />}>
                </Route>
                <Route path="/login" element={<Login />}>
                </Route>
                <Route path="/signup" element={<Registration />}>
                </Route>
            </Routes>
        </Router>
    );
}

export default AppRouter;