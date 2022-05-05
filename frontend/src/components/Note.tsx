import React, { useState, useEffect, useId } from 'react';
import Icon from './Icon';
import { deleteNoteRequest } from '../requests/requests';
import  { useNavigate } from 'react-router-dom' //TODO: move all redirects to one file

interface NoteProp {
    id: number;
    title: string;
    content: string;
}

const Note = (props: NoteProp) => {
    const [id, setId] = useState(props.id);
    const navigator = useNavigate();

    const removeNote = async () => {
        const status = await deleteNoteRequest(id);
        console.log(status);
        if (status === 204) {
            navigator('/', {replace:true}); 
            window.location.reload();
        }
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
            </div>
        </div>
    );
}

export default Note;