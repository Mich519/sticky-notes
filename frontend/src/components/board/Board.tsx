import React, { useState, useEffect, useId } from "react";
import Note from "../note/Note";
import './Board.css';
import axios from "axios";
import { MY_NOTES_URL } from "../../BackendUrls";
import {getAllNotesRequest} from "../../requests/requests"
import { GetNotePayload } from "../../requests/payloads";


const Board = () => {

    const [noteElementList, setNoteElementList] = useState<Array<JSX.Element>>([]);

    const fetchAllNotes = async () => {
        const noteDtoList : Array<GetNotePayload> = await getAllNotesRequest();
        const temp = noteDtoList.map(noteDto => {
            return <Note id={noteDto.id} title={noteDto.title} content={noteDto.content}/>
        });
        setNoteElementList(temp);
    };

    useEffect(() => {fetchAllNotes()}, []);

    return (
        <div className="board-container">
            <div className="board">
                {noteElementList}
            </div>
        </div>
    );
}

export default Board;