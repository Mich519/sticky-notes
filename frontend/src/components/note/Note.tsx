import React, { useState, useEffect, useId } from 'react';
import './Note.css'
import Icon from './Icon';
import internal from 'stream';

interface NoteProp {
    title: string;
    content: string;
}

const Note = (props: NoteProp) => {

    const [id, setId] = useState(useId());
    const [isRendered, setIsRendered] = useState(true);
    
    useEffect(() => {

    }, [isRendered]);


    const removeNote = () => {
        
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