import React, { useState, useEffect } from "react";
import Board from "../components/board/Board";
import SubToolbar from "../components/subtoolbar/SubToolbar";
import Toolbar from "../components/toolbar/Toolbar";
import axios from "axios";
import { USERS_URL } from "../BackendUrls";
import { getAllNotesRequest } from "../requests/requests";

const Dashboard = () => {

    return (
        <div>
            <Toolbar />
            <SubToolbar />
            <Board/>
        </div>
    );
}

export default Dashboard;