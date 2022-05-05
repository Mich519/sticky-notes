import Board from "../components/Board";
import SubToolbar from "../components/SubToolbar";
import Toolbar from "../components/Toolbar";

const Dashboard = () => {
    return (
        <div>
            <Toolbar />
            <SubToolbar/>
            <Board/>
        </div>
    );
}

export default Dashboard;