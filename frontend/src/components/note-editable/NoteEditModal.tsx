import Reactm, { useState } from "react";
import { postNoteRequest } from "../../requests/requests";
import Icon from "../note/Icon";
import './NoteEditModal.css'

interface NoteEditModalProps {
    triggerButton?: JSX.Element
}

export const NoteEditModal = (props: NoteEditModalProps) => {
    const [title, setTitle] = useState('');
    const [content, setContent] = useState('');

    const addNote = async () => {
        if (title != '' && content != '') {
            postNoteRequest({ title, content });
        }
    }

    return (
        <div className="modal">
            gffdfsdsdf
        </div>
        
    )
};

export default NoteEditModal;
