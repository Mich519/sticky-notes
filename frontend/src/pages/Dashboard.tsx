import React, {useState} from "react";
import Board from "../components/board/Board";
import SubToolbar from "../components/subtoolbar/SubToolbar";
import Toolbar from "../components/toolbar/Toolbar";

const Dashboard = () => {

    const [test, setTest] = useState(false);

    const rerender = () => {
        setTest(!test);
    }

    return (
        <div>
            <Toolbar />
            <SubToolbar rerenderParentCallback={rerender}/>
            <Board/>
        </div>
    );
}

export default Dashboard;