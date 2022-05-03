import React, { useState, useEffect, useId } from "react";
import Note from "../note/Note";
import './Board.css';
import axios from "axios";
import { MY_NOTES_URL } from "../../BackendUrls";
import { NoteDto } from "../../requests/dtos";
import {getAllNotesRequest} from "../../requests/requests"

const Board = () => {

    const [noteElementList, setNoteElementList] = useState<Array<JSX.Element>>([]);

    const fetchAllNotes = async () => {
        const noteDtoList : Array<NoteDto> = await getAllNotesRequest();
        const temp = noteDtoList.map(noteDto => {
            return <Note title={noteDto.title} content={noteDto.content}/>
        });
        setNoteElementList(temp);
    };

    useEffect(() => {fetchAllNotes()}, []);
    useEffect(() => {fetchAllNotes()}, noteElementList);

    return (
        <div className="board-container">
            <div className="board">
                {noteElementList}
                <Note title="title" content="content" />
            </div>
        </div>
    );
}

export default Board;