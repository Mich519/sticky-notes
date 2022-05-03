import React, { useState } from "react";
import Icon from "../note/Icon";
import './SubToolbar.css'
import '../common/Common.css'
import axios from "axios";
import { MY_NOTES_URL } from "../../BackendUrls";
import { postNoteRequest } from "../../requests/requests";

const SubToolbar = () => {
    const [isVisible, setIsVisible] = useState(false);
    const [title, setTitle] = useState('');
    const [content, setContent] = useState('');

    const showAddNoteForm = () => {
        setIsVisible(!isVisible);
    }

    const addNote = async () => {
        if (title != '' && content != '') {
            postNoteRequest({title, content});
        }
    }

    return (
        <div className="subtoolbar">
            <div className="add-note-button-wrapper" onClick={showAddNoteForm}>
                {
                    isVisible ? <Icon name="remove" /> : <Icon name="add" />
                }

            </div>
            <div className="form-container" style={{ "display": isVisible ? 'flex' : 'none' }}>
                <div className="form">
                    Add new note ...

                    <label>
                        <input
                            type="text"
                            value={title}
                            onChange={(e) => setTitle(e.target.value)}
                            placeholder="Title ..."
                        />
                    </label>
                    
                    <label>
                        <input
                            type="text"
                            value={content}
                            onChange={(e) => setContent(e.target.value)}
                            placeholder="Content ..."
                        />
                    </label>
                    <div className="button-toolbar" onClick={addNote}>
                        <Icon name="note_add" />
                    </div>
                </div>
            </div>
        </div>
    );
}

export default SubToolbar;