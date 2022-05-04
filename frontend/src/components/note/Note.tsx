import React, { useState, useEffect, useId } from 'react';
import './Note.css'
import Icon from './Icon';
import internal from 'stream';
import { deleteNoteRequest } from '../../requests/requests';
import  { useNavigate } from 'react-router-dom' //TODO: move all redirects to one file

interface NoteProp {
    id: number;
    title: string;
    content: string;
}

const Note = (props: NoteProp) => {
    const [id, setId] = useState(props.id);
    const [isRendered, setIsRendered] = useState(true);
    const [isEditMode, setIsEditMode] = useState(false);
    const navigator = useNavigate();
    
    useEffect(() => {

    }, [isRendered]);


    const removeNote = async () => {
        const status = await deleteNoteRequest(id);
        console.log(status);
        if (status === 204) {
            navigator('/', {replace:true}); //todo: refresh after removing a note
        }
    }

    const editNote = () => {
        setIsEditMode(true);
    }

    return (
        <div className='note-container'>

            <div className='note-title'>
                {props.title}
            </div>

            <div className='note-content'>
                {props.content}
            </div>

            <div className='note-toolbox display-animation'>
                <Icon name="push_pin" />
                <Icon name="color_lens" />
                <Icon name="image" />
                <div onClick={removeNote}>
                    <Icon name="delete" />
                </div>
                <div onClick={editNote}>
                    <Icon name="edit" />
                </div>
            </div>
        </div>
    );
}

export default Note;