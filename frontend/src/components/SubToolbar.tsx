import React, { useState } from "react";
import Icon from "./Icon";
import { postNoteRequest } from "../requests/requests";
import { useNavigate } from 'react-router-dom'

const SubToolbar = () => {
    const [title, setTitle] = useState('');
    const [content, setContent] = useState('');
    const [isModalVisible, setIsModalVisible] = useState(false);
    const navigator = useNavigate();

    const addNote = async () => {
        if (title != '' && content != '') {
            await postNoteRequest({ title, content });
            navigator('/', { replace: true });
            window.location.reload();
        }
    }

    return (
        <div className="subtoolbar">

            <div className='note-edit-container'>
                Add new note ...

                <div className='form-group'>
                    <input type="text" placeholder="Add title ..." onChange={(e) => setTitle(e.target.value)} />
                </div>
                <div className='form-group'>
                    <input type="text" placeholder="Add content ..." onChange={(e) => setContent(e.target.value)} />
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