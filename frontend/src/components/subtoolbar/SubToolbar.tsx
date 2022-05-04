import React, { useState } from "react";
import Icon from "../note/Icon";
import './SubToolbar.css'
import '../common/Common.css'
import axios from "axios";
import { MY_NOTES_URL } from "../../BackendUrls";
import { postNoteRequest } from "../../requests/requests";
import { useNavigate } from 'react-router-dom'
import NoteEditModal from "../note-editable/NoteEditModal";
import { JsxAttributeLike } from "typescript";

const SubToolbar = () => {
    const [isVisible, setIsVisible] = useState(false);
    const [title, setTitle] = useState('');
    const [content, setContent] = useState('');
    const navigator = useNavigate();

    const showAddNoteForm = () => {
        setIsVisible(!isVisible);
    }

    const addNote = async () => {
        if (title != '' && content != '') {
            postNoteRequest({ title, content });
            navigator('/', { replace: true });
        }
    }

    return (
        <div className="subtoolbar">
            <div className="add-note-button-wrapper" onClick={showAddNoteForm}>
                <Icon name="add" />
                <NoteEditModal />
            </div>
        </div >
    );
}

export default SubToolbar;