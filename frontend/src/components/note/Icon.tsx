import React from "react";
import './Note.css'
import './Icon.css'
import 'material-icons/iconfont/filled.css';

interface IconProp {
    name: string;
}

const Icon = (prop : IconProp) => {
    return(
        <div>
            <span className="material-icons icon">{prop.name}</span>
        </div>
    );
}

export default Icon;