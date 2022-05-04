import React, { useState } from "react";
import Icon from "../note/Icon";
import './SubToolbar.css'
import { postNoteRequest } from "../../requests/requests";
import { useNavigate } from 'react-router-dom'

interface SubToolbarProps {
    rerenderParentCallback : Function;
}

const SubToolbar = (props : SubToolbarProps) => {
    const [title, setTitle] = useState('');
    const [content, setContent] = useState('');
    const [isModalVisible, setIsModalVisible] = useState(false);
    const navigator = useNavigate();

    const addNote = async () => {
        if (title != '' && content != '') {
            await postNoteRequest({ title, content });
            navigator('/', { replace: true });
            props.rerenderParentCallback();
        }
    }

    return (
        <div className="subtoolbar">

            <div className='note-container'>
                Add new note ...

                <div className='note-title'>
                    <input type="text" placeholder="Add title" onChange={(e) => setTitle(e.target.value)} />
                </div>
                <div className='note-content'>
                    <input type="text" placeholder="Add content" onChange={(e) => setContent(e.target.value)} />
                </div>
                <div className="add-note-button-wrapper">
                    <div onClick={addNote}>
                        <Icon name="add" />
                    </div>

                </div>

            </div>
        </div>
    );
}

export default SubToolbar;