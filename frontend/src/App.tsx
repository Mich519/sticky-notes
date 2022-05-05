import React from 'react';
import Board from './components/Board';
import Toolbar from './components/Toolbar';
import './assets/styles/Stylesheet.css'
import SubToolbar from './components/SubToolbar';
import AppRouter from './routers/AppRouter';

const App = () => {

    return (
        <div className="app">
            <AppRouter/>
        </div>
    );
}

export default App;
