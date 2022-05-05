import React, { useState, useEffect} from "react";
import Note from "./Note";
import { getAllNotesRequest } from "../requests/requests"
import { GetNotePayload } from "../requests/payloads";
import { useNavigate } from 'react-router-dom'

const Board = () => {

    const [noteElementList, setNoteElementList] = useState<Array<JSX.Element>>([]);
    const [isVisible, setIsVisible] = useState(false);
    const navigator = useNavigate();

    const fetchAllNotes = async () => {
        try {
            //Array<GetNotePayload>
            const response = await getAllNotesRequest();
            const notePayloadList: Array<GetNotePayload> = response.data;
            const temp = notePayloadList.map(noteDto => {
                return <Note key={noteDto.id}
                    id={noteDto.id}
                    title={noteDto.title}
                    content={noteDto.content}
                />
            });
            setNoteElementList(temp);
        } catch (error) {
            navigator("/login");
        }

    };

    useEffect(() => {
        fetchAllNotes()
    }, []);

    return (
        <div>
            <div className="board">
                {noteElementList}
            </div>
        </div>
    );
}

export default Board;