import React from "react";
import Board from "../components/board/Board";
import SubToolbar from "../components/subtoolbar/SubToolbar";
import Toolbar from "../components/toolbar/Toolbar";

const Dashboard = () => {
    return(
        <div>
            <Toolbar/>
            <SubToolbar/>
            <Board/>
        </div>
    );
}

export default Dashboard;