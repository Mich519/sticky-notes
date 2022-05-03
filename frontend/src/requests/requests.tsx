import axios from "axios"
import { MY_NOTES_URL } from "../BackendUrls";
import { NoteDto } from "./dtos";

export const getAllNotesRequest = async () => {
    const response = await axios.get(
        MY_NOTES_URL,
        {
            withCredentials: true
        }
    );
    return response.data;
}

export const postNoteRequest = async (noteDto: NoteDto) => {
    const response = await axios.post(
        MY_NOTES_URL,
        noteDto,
        {
            withCredentials: true
        }
    );
    return response.data;
}