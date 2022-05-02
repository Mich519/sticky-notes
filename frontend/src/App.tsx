import React from 'react';
import Board from './components/board/Board';
import Toolbar from './components/toolbar/Toolbar';
import './App.css';
import SubToolbar from './components/subtoolbar/SubToolbar';
import AppRouter from './routers/AppRouter';

import {
    BrowserRouter as Router,
    Routes,
    Route,
    Link,
    Navigate
} from "react-router-dom";

const App = () => {

    return (
        <div className="App">
            <AppRouter/>
        </div>
    );
}

export default App;
